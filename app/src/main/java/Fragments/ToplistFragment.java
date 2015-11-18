package Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.melker.mapping.MainActivity;
import com.example.melker.mapping.R;
import com.example.melker.mapping.StringArrayAdapter;
import com.example.melker.mapping.Users;

import java.util.ArrayList;
import java.util.List;

import controller.GameRegistrationManager;
import controller.UserManager;
import model.Game;
import model.GameRegistration;
import model.User;

/**
 * Created by Melker on 2015-11-02.
 */
public class ToplistFragment extends Fragment {

    private ArrayList<String> myData;
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootVier = inflater.inflate(R.layout.toplistfragment, container, false);

        UserManager userManager = new UserManager();
        ArrayList<User> topList = userManager.getAllUsersSortedByShortestGameDuration(getActivity());

        String [] topListArray = new String [topList.size()];

        int i = 0;

        for(User u: topList){
            topListArray [i] = u.getName();
            i++;
        }

        ListView lv = (ListView) rootVier.findViewById(R.id.listView_topList);

        StringArrayAdapter ad = new StringArrayAdapter(topListArray, getActivity());
        lv.setAdapter(ad);

        return rootVier;



    }

}
