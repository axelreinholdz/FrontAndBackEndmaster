package controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

import model.GameRegistration;

/**
 * Created by axelreinholdz on 2015-11-17.
 */
public class GameRegistrationManager {
    private GameRegistration gameRegistration;

    public GameRegistrationManager() {
    }

    public GameRegistration getGameRegistrationByUserIdAndGameId(String userId, int gameId, Context context){

        GameRegistration gameRegistration = new GameRegistration();
        JSONArray jsonArray = new JSONArray();
        HttpUtility httpUtility = new HttpUtility();
        String result = "";

        // wrap properties to a JSON Array
        try {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("userId", userId);
            jsonArray.put(jsonObject1);
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("gameId", gameId);
            jsonArray.put(jsonObject2);

            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            // pass property to get gameRegistration back
            if(networkInfo != null && networkInfo.isConnected()){
                result=httpUtility.getObjectByProperty("111",jsonArray.toString());
            }
            Log.d("httpOutput", result);
            JSONArray gameRegistrationArray = new JSONArray(result);
            JSONObject obj = gameRegistrationArray.getJSONObject(0);

            gameRegistration = convertJSONObjectToGameRegistration(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return gameRegistration;
    }

    private GameRegistration convertJSONObjectToGameRegistration(JSONObject obj) throws JSONException, ParseException {
        String userId = obj.getString("userId");
        int gameId = obj.getInt("gameId");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = null;
        parsedDate = dateFormat.parse(obj.getString("startDate"));
        Date startDate = new java.sql.Date(parsedDate.getTime());
        Time startTime = Time.valueOf(obj.getString("startTime"));
        String dateString = obj.getString("endDate");
        Date endDate = null;
        if (!dateString.isEmpty()){
            parsedDate = dateFormat.parse(dateString);
            endDate = new java.sql.Date(parsedDate.getTime());
        }
        Time endTime = null;
        String timeString = obj.getString("endTime");
        if (!timeString.isEmpty()){
            endTime = Time.valueOf(timeString);
        }
        int currentQuestionNo = obj.getInt("currentQuestionNo");
        return new GameRegistration(userId,gameId,startDate,startTime,endDate,endTime,currentQuestionNo);
    }
}
