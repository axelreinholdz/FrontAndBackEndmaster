package Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import android.app.Fragment;

import com.example.melker.mapping.R;
import com.example.melker.mapping.StringArrayAdapter;

import java.util.ArrayList;

import controller.UserManager;
import model.User;


public class FriendsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootVier = inflater.inflate(R.layout.friendsfragment, container, false);


        String email = getActivity().getIntent().getStringExtra("email");

        UserManager userManager = new UserManager();
        ArrayList<User> friendsList = userManager.getFriendsByUserEmail(email ,getActivity());

        String [] friendsArray = new String [friendsList.size()];

        int i = 0;

        for(User u : friendsList){
            friendsArray [i] = u.getName();
            i++;
        }

        ListView lv = (ListView) rootVier.findViewById(R.id.listView_friendsList);

        StringArrayAdapter ad = new StringArrayAdapter(friendsArray, getActivity());
        lv.setAdapter(ad);

        return rootVier;
    }


}
