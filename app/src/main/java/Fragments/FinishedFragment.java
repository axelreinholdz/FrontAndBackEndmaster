package Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.melker.mapping.R;

/**
 * Created by axelreinholdz on 2015-11-18.
 */
public class FinishedFragment extends Fragment {

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootVier = inflater.inflate(R.layout.finished_page, container, false);
        final FragmentManager fm = getFragmentManager();



        ImageButton restartBtn = (ImageButton) rootVier.findViewById(R.id.imageButton_restart);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();
            }
        });


        ImageButton toplistBtn = (ImageButton) rootVier.findViewById(R.id.imageButton_toplist);
        toplistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.content_frame, new ToplistFragment()).commit();
            }
        });






        return rootVier;
    }
}
