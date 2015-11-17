package model;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by IITSLoan on 14/11/15.
 */
public class Question {
    private int QuestionNo;
    private String QuestionText;
    private String AnswerText;
    private String Hint1;
    private double LocationLatitude;
    private double LocationLongitude;
    private String QuestionPic;

    public Question(int QuestionNo,
                    String QuestionText,
                    String AnswerText,
                    String Hint1,
                    double LocationLatitude,
                    double LocationLongitude,
                    String QuestionPic){
        this.QuestionNo = QuestionNo;
        this.QuestionText  = QuestionText;
        this.AnswerText = AnswerText;
        this.Hint1 = Hint1;
        this.LocationLatitude = LocationLatitude;
        this.LocationLongitude = LocationLongitude;
        this.QuestionPic = QuestionPic;
    }

    public Question(){

    }

    //Get method - Haven't thought any cases where we need to use SET methods
    public int getQuestionNo(){
        return QuestionNo;
    }

    public String getQuestionText(){
        return QuestionText;
    }

    public String getAnswerText(){
        return AnswerText;
    }

    public String getHint1(){
        return Hint1;
    }

    public double getLocationLatitude(){
        return LocationLatitude;
    }

    public double getLocationLongitude(){
        return LocationLongitude;
    }

    public String getQuestionPic(){
        return QuestionPic;
    }
}
