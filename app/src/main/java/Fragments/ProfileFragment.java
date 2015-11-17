package Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.melker.mapping.R;

import org.w3c.dom.Text;

import controller.UserManager;
import model.User;

/**
 * Created by Melker on 2015-11-01.
 */
public class ProfileFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootVier = inflater.inflate(R.layout.profile_page, container, false);

        String email = getActivity().getIntent().getStringExtra("email");


        TextView textViewUserName = (TextView) rootVier.findViewById(R.id.textView_username);
        TextView textViewEmail = (TextView) rootVier.findViewById(R.id.textView_email);

        UserManager userManager = new UserManager();
        User user = userManager.getUserByEmail(email,getActivity());

        textViewEmail.setText(user.getEmail());
        textViewUserName.setText(user.getName());


        return rootVier;
    }
}
