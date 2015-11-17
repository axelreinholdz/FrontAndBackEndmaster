package controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Iris on 14/11/2015.
 */
public class QuestionManager {
    public QuestionManager() {
    }

    public Question getQuestionByNumber (int QuestionNo, Context context) {
        Question theQuestion = new Question();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        HttpUtility httpUtility = new HttpUtility();
        String result = "";

        try {
            // wrap property to a JSON Array
            jsonObject.put("QuestionNo", QuestionNo);
            jsonArray.put(jsonObject);

            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            // pass property to get user back
            if(networkInfo != null && networkInfo.isConnected()){
                result=httpUtility.getObjectByProperty("109",jsonArray.toString());
            }
            JSONArray questionArray = new JSONArray(result);
            JSONObject obj = questionArray.getJSONObject(0);

            // change JSONObject to Java Class
            theQuestion = convertJSONObjectToQuestion(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return theQuestion;
    }

    private Question convertJSONObjectToQuestion(JSONObject obj) throws JSONException {
        // change JSONObject to Java Class
        int QuestionNo = obj.getInt("QuestionNo");
        String QuestionText = obj.getString("QuestionText");
        String AnswerText = obj.getString("AnswerText");
        String Hint1 = obj.getString("Hint1");
        double LocationLatitude = obj.getDouble("LocationLatitude");
        double LocationLongitude = obj.getDouble("LocationLongitude");
        String questionPic = "https://s3-ap-southeast-1.amazonaws.com/symplcms/symplCMSTest/" + obj.getJSONObject("QuestionPic").getJSONObject("file").getString("name");
        return new Question(QuestionNo, QuestionText, AnswerText, Hint1, LocationLatitude, LocationLongitude, questionPic);
    }

    public Question getQuestionById(int QuestionNo, Context context) {
        //id from SYMPLCMS, set STRING to avoid CONVERTING INT TO STRING
        String url = "";
        if (QuestionNo == 1) {
            url = "http://161.202.13.188:9000/api/object/get/1324";
        } else if (QuestionNo == 2) {
            url = "http://161.202.13.188:9000/api/object/get/1250";
        } else if (QuestionNo == 3) {
            url = "http://161.202.13.188:9000/api/object/get/1316";
        }

        Question newQuestion = new Question();
        String result = "";
        try {

            // GET object String
            HttpUtility httpUtility = new HttpUtility();

            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if(networkInfo != null && networkInfo.isConnected()){
                result = httpUtility.download(url);
                Log.d("CONNECTION","Connection made");
            }
            else {
                result = "No network connection available";
            }

            // parse String to JSONObject
            JSONObject obj = new JSONObject(result);

            // change JSONObject to Java Class
            String QuestionText = obj.getString("QuestionText");
            String AnswerText = obj.getString("AnswerText");
            String Hint1 = obj.getString("Hint1");
            double LocationLatitude = obj.getDouble("LocationLatitude");
            double LocationLongitude = obj.getDouble("LocationLongitude");
            String questionPic = "https://s3-ap-southeast-1.amazonaws.com/symplcms/symplCMSTest/" + obj.getJSONObject("QuestionPic").getJSONObject("file").getString("name");
            newQuestion = new Question(QuestionNo, QuestionText, AnswerText, Hint1, LocationLatitude, LocationLongitude, questionPic);
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return newQuestion;
    }
}
