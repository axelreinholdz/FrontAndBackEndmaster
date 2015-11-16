package Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.melker.mapping.R;

/**
 * Created by Melker on 2015-11-01.
 */
public class ProfileFragment extends Fragment {

    Button camBtn;
    ImageView imgView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootVier = inflater.inflate(R.layout.profile_page, container, false);

        camBtn = (Button) rootVier.findViewById(R.id.cptBtn);
        imgView =(ImageView) rootVier.findViewById(R.id.image_view);



        return rootVier;
    }
}
