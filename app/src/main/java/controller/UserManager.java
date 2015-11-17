package controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import model.Friends;
import model.User;

/**
 * Created by Iris on 14/11/2015.
 */
public class UserManager {
    private User user;

    public boolean login(String email, String password, Context context){
        boolean isLogin = false;
        User user = getUserByEmail(email,context);
        if (user!=null && password.equalsIgnoreCase(user.getPassword())){
            isLogin = true;
        }
        return isLogin;
    }

    public User getUserByEmail(String theEmail, Context context){

        User theUser = new User();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        HttpUtility httpUtility = new HttpUtility();
        String result = "";

        try {
            // wrap property to a JSON Array
            jsonObject.put("email", theEmail);
            jsonArray.put(jsonObject);

            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            // pass property to get user back
            if(networkInfo != null && networkInfo.isConnected()){
                result=httpUtility.getObjectByProperty("112",jsonArray.toString());
            }
            JSONArray userArray = new JSONArray(result);
            JSONObject obj = userArray.getJSONObject(0);

            // change JSONObject to Java Class
            theUser = convertJSONObjectToUser(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return theUser;
    }

    public ArrayList<User> getAllUsers(Context context){
        ArrayList<User> allUsers = new ArrayList<User>();

        String result = "";
        HttpUtility httpUtility = new HttpUtility();
        try{

            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if(networkInfo != null && networkInfo.isConnected()){
                result = httpUtility.download("http://161.202.13.188:9000/api/object/get/app/32/objecttype/112");
                Log.d("CONNECTION", "Connection made");
            }
            else {
                result = "No network connection available";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray array = new JSONArray();

        try {
            array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                User user = convertJSONObjectToUser(obj);
                allUsers.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    public ArrayList<User> getFriendsByUserEmail (String theEmail, Context context) {
        ArrayList<User> friends = new ArrayList<User>();

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        HttpUtility httpUtility = new HttpUtility();
        String result = "";

        try {
            // wrap property to a JSON Array
            jsonObject.put("userEmail", theEmail);
            jsonArray.put(jsonObject);

            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if(networkInfo != null && networkInfo.isConnected()){
                result = httpUtility.getObjectByProperty("115", jsonArray.toString());
                Log.d("CONNECTION", "Connection made");
            }
            else {
                result = "No network connection available";
            }
            Log.d("httpOutput",result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray array = new JSONArray();

        try {
            array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Friends friendObject = convertJSONObjectToFriends(obj);
                User friendUser = getUserByEmail(friendObject.getFriendEmail(),context);
                friends.add(friendUser);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return friends;

    }

    private User convertJSONObjectToUser (JSONObject obj) throws JSONException {
        // change JSONObject to Java Class
        String userId = obj.getString("userId");
        String name = obj.getString("name");
        String email = obj.getString("email");
        String password = obj.getString("password");
        String location = obj.getString("location");
        return new User(userId,name,email,password,location);
    }

    private Friends convertJSONObjectToFriends (JSONObject obj) throws JSONException {
        String userEmail = obj.getString("userEmail");
        String friendEmail = obj.getString("friendEmail");
        return new Friends(userEmail,friendEmail);
    }
}
