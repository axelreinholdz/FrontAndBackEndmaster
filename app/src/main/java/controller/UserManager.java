package controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONObject;

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
        String url = "";
        if (theEmail.equalsIgnoreCase("iris.lijingyu@gmail.com")){
            url = "http://161.202.13.188:9000/api/object/get/1320";
        }
        User theUser = new User();
        String result = "";

        HttpUtility httpUtility = new HttpUtility();
        try{

            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if(networkInfo != null && networkInfo.isConnected()){
                result = httpUtility.download(url);
                Log.d("CONNECTION", "Connection made");
            }
            else {
                result = "No network connection available";
            }

            // parse String to JSONObject
            System.out.println(result);
            JSONObject obj = new JSONObject(result);

            // change JSONObject to Java Class
            int userId = obj.getInt("userId");
            String name = obj.getString("name");
            String email = obj.getString("email");
            String password = obj.getString("password");
            String location = obj.getString("location");
            theUser = new User(userId,name,email,password,location);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return theUser;
    }
}
