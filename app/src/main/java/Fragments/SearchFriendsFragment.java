package Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.melker.mapping.R;
import com.example.melker.mapping.StringArrayAdapter;

import java.util.ArrayList;

import controller.UserManager;
import model.User;

/**
 * Created by axelreinholdz on 2015-11-18.
 */
public class SearchFriendsFragment extends Fragment{

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootVier = inflater.inflate(R.layout.searchfriendfragment, container, false);
        final FragmentManager fm = getFragmentManager();


        UserManager userManager = new UserManager();
        ArrayList<User> userList = userManager.getAllUsers(getActivity());

        String [] usersArray = new String [userList.size()];

        int i = 0;

        for(User u : userList){
            usersArray [i] = u.getEmail();
            i++;
        }

        ListView lv = (ListView) rootVier.findViewById(R.id.listView_usersList);

        StringArrayAdapter ad = new StringArrayAdapter(usersArray, getActivity());
        lv.setAdapter(ad);

        return rootVier;
    }
}
