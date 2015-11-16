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

        String[] vals = {"Melker", "Axel", "Viet", "Iris","Testsson"};
        ListView lv = (ListView)rootVier.findViewById(R.id.listView);
        StringArrayAdapter ad = new StringArrayAdapter(vals, getActivity() );
        lv.setAdapter(ad);

        return rootVier;



    }

}
