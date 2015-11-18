package Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.melker.mapping.MainActivity;
import com.example.melker.mapping.R;

import controller.GameRegistrationManager;
import controller.QuestionManager;
import controller.UserManager;
import model.Question;
import model.User;

/**
 * Created by Melker on 2015-11-01.
 */
public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootVier = inflater.inflate(R.layout.start_game, container, false);
        final FragmentManager fm = getFragmentManager();
        String email = getActivity().getIntent().getStringExtra("email");

        UserManager userManager = new UserManager();
        final User u  = userManager.getUserByEmail(email,getActivity());

        ImageButton startBtn = (ImageButton) rootVier.findViewById(R.id.newGame_button);

        final GameRegistrationManager gameRegistrationManager = new GameRegistrationManager();



        //if user already have unfinished game continue button visible true

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add game and user to gameRegistration

                gameRegistrationManager.createGameRegistration(u.getUserId());

                fm.beginTransaction().replace(R.id.content_frame, new QuestionFragment()).commit();
            }
        });

        return rootVier;
    }
}
