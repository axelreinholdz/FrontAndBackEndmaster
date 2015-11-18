package Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.melker.mapping.MainActivity;
import com.example.melker.mapping.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import controller.QuestionManager;
import model.Question;

/**
 * Created by Melker on 2015-11-16.
 */
public class AnswerFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootVier = inflater.inflate(R.layout.answer_page, container, false);
        final TextView textViewFunFact = (TextView) rootVier.findViewById(R.id.textView_funFact);
        final TextView textViewInstruction = (TextView) rootVier.findViewById(R.id.textView_instruction);
        final TextView textViewLocation = (TextView) rootVier.findViewById(R.id.textView_location);
        ImageView questionImageView = (ImageView) rootVier.findViewById(R.id.imageView_questionPicture) ;

        final FragmentManager fm = getFragmentManager();


        QuestionManager qm = new QuestionManager();
        final Question q = qm.getQuestionByNumber(1, getActivity());

        textViewFunFact.setText("Fun fact: "+q.getHint1());

        textViewLocation.setText("Welcome to: "+q.getAnswerText());

        textViewInstruction.setText("Go to "+q.getAnswerText()+" to get your next location");

        ImageButton nextbtn = (ImageButton) rootVier.findViewById(R.id.nextqBtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.content_frame, new LocationFragment()).commit();

            }
        });

        return rootVier;


    }
}
