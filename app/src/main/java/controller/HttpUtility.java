package controller;

import android.os.AsyncTask;

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

    public String download(String url) throws ExecutionException, InterruptedException {
        return new DownloadTask().execute(url).get();
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
