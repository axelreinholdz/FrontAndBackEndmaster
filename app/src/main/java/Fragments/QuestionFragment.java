package Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.melker.mapping.MainActivity;
import com.example.melker.mapping.R;

import controller.QuestionManager;
import model.Question;

/**
 * Created by Melker on 2015-11-16.
 */
public class QuestionFragment extends Fragment{

     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootVier = inflater.inflate(R.layout.question_page
                , container, false);


        final FragmentManager fm = getFragmentManager();

        TextView questionTextView = (TextView) rootVier.findViewById(R.id.textView_question);
        final EditText answerEditText = (EditText) rootVier.findViewById(R.id.editText_answer);


         QuestionManager qm = new QuestionManager();
         final Question q = qm.getQuestionByNumber(1, getActivity());

         questionTextView.setText(q.getQuestionText());


        ImageButton submitBtn = (ImageButton) rootVier.findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String answer = answerEditText.getText().toString();

                Log.d("Anwser",answer);

                if(answer.equalsIgnoreCase(q.getAnswerText())){

                    fm.beginTransaction().replace(R.id.content_frame, new AnswerFragment()).commit();
                }
                else{
                    Toast.makeText(getActivity(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return rootVier;


    }
}
