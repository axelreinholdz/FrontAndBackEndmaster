package controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import model.Question;

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

    public Question getQuestionById(int QuestionNo, Context context) {
        //id from SYMPLCMS, set STRING to avoid CONVERTING INT TO STRING
        String[] questionArray = {"1248"};
        String url = "http://161.202.13.188:9000/api/object/get/";
        url+=questionArray[QuestionNo - 1];
        InputStream inputStream = null;
        Question newQuestion = new Question();
        String result = "";

        HttpUtility httpU = new HttpUtility();

        try {

            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if(networkInfo != null && networkInfo.isConnected()){
                result = httpU.download(url);
                Log.d("CONNECTION","Connection made");
            }
            else {
                result = "No network connection available";
            }

            Log.d("STRING",result);
            //parse String to JSONObject
            JSONObject obj = new JSONObject(result);
            String QuestionText = obj.getString("QuestionText");
            String AnswerText = obj.getString("AnswerText");
            String Hint1 = obj.getString("Hint1");
            String Hint2 = obj.getString("Hint2");
            String Hint3 = obj.getString("Hint3");
            //String questionPic = "https://s3-ap-southeast-1.amazonaws.com/symplcms/symplCMSTest/" + obj.getJSONObject("QuestionPic").getJSONObject("file").getString("name");
            newQuestion = new Question(QuestionNo, QuestionText, AnswerText, Hint1, Hint2, Hint3, null);

        } catch (Exception e) {
           Log.d("InputStream", e.getLocalizedMessage());
        }

        return newQuestion;

    }
}
