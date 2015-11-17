package controller;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by axelreinholdz on 2015-11-16.
 */
public class HttpUtility {
    private static HttpURLConnection httpConn;

    public String download(String url) throws ExecutionException, InterruptedException {
        return new DownloadTask().execute(url).get();
    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls){
            try{
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve object. Url may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
        }
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

    public String getObjectByProperty(String objectTypeId, String jsonInput) throws ExecutionException, InterruptedException {
        return new httpPostTask().execute(objectTypeId, jsonInput).get();
    }

    private class httpPostTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... args){
            String objectTypeId = args[0];
            String jsonInput = args[1];
            Log.d("jsonInput", jsonInput);
            String requestURL = "http://161.202.13.188:9000/api/object/get/app/32/objecttype/"+objectTypeId+"/properties";
            String result = "";

            try {
                HttpPost httpPost = new HttpPost(requestURL);
                httpPost.setEntity(new StringEntity(jsonInput));
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                HttpResponse response =  new DefaultHttpClient().execute(httpPost);
//                System.out.println(response.getStatusLine().getStatusCode());

                InputStream inputStream = response.getEntity().getContent();
                result=convertInputStreamToString(inputStream);
                System.out.println("HTTP CALL IS INITIATING");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
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

}
