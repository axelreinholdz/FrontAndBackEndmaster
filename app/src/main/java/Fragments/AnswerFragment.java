package Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.melker.mapping.R;

/**
 * Created by Melker on 2015-11-16.
 */
public class AnswerFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootVier = inflater.inflate(R.layout.answer_page, container, false);

        final FragmentManager fm = getFragmentManager();

        ImageButton nextbtn = (ImageButton) rootVier.findViewById(R.id.nextqBtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm.beginTransaction().replace(R.id.content_frame, new QuestionFragment()).commit();

            }
        });

        return rootVier;


    }
}
