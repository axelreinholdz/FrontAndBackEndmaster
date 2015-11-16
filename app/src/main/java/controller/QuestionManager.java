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

        try {

            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if(networkInfo != null && networkInfo.isConnected()){
                result = new DownloadTask().execute(url).get();
                Log.d("CONNECTION","Connection made");
            }
            else {
                result = "No network connection available";
            }

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

    private String convertInputStreamToString(InputStream inputStream)
            throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }


    private String downloadUrl(String myurl) throws IOException{
        InputStream is = null;

        String result = "";

        try{
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();
            int response = conn.getResponseCode();

            is = conn.getInputStream();

            result = convertInputStreamToString(is);

            return result;

        }
        finally {
            if(is != null){
                is.close();
            }
        }


    }




    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls){
            try{
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve question. Url may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result) {

        }


    }
}
