package Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.melker.mapping.R;

import controller.GPSManager;
import controller.GPSTracker;
import controller.QuestionManager;
import model.Question;

/**
 * Created by axelreinholdz on 2015-11-17.
 */
public class LocationFragment extends Fragment{

    public static final int ACTIVITY_START_CAMERA_APP = 0;
    private ImageView photoCapturedImageView;
    static final int Cam_Request = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootVier = inflater.inflate(R.layout.location_page, container, false);
        final FragmentManager fm = getFragmentManager();

        photoCapturedImageView = (ImageView) rootVier.findViewById(R.id.imageView_picture);

        ImageButton takePictureButton = (ImageButton) rootVier.findViewById(R.id.imageButton_takePicture);
        ImageButton sendLocationButton = (ImageButton) rootVier.findViewById(R.id.imageButton_sendLocation);
        final TextView textViewTakePicture = (TextView) rootVier.findViewById(R.id.textView_takePicture);

        final String email = getActivity().getIntent().getStringExtra("email");

        QuestionManager qm = new QuestionManager();
        final Question q = qm.getQuestionByNumber(1, getActivity());

        textViewTakePicture.setText("Take a picture when you're at "+q.getAnswerText());

        sendLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkLocation

                GPSManager gpsManager = new GPSManager();
                double longitude = q.getLocationLongitude();
                double latitude = q.getLocationLatitude();

                boolean checkLocation ;

                checkLocation = gpsManager.isAtRightLocation(getActivity(),latitude,longitude,email);

                if(checkLocation){
                    fm.beginTransaction().replace(R.id.content_frame, new QuestionFragment()).commit();
                }
                else{
                    Toast.makeText(getActivity(), "Wrong Location", Toast.LENGTH_SHORT).show();
                }


            }
        });

        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto(rootVier);
            }
        });

        return rootVier;
    }

    public void takePhoto(View view){
        Intent callCameraApplicationIntent = new Intent();
        callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(callCameraApplicationIntent, ACTIVITY_START_CAMERA_APP);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == ACTIVITY_START_CAMERA_APP && resultCode == getActivity().RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap photoCapturedBitMap = (Bitmap) extras.get("data");
            photoCapturedImageView.setImageBitmap(photoCapturedBitMap);
        }
    }

}
