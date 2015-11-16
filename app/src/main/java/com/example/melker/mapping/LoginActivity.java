package com.example.melker.mapping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/*
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;*/

public class LoginActivity extends Activity {
   // LoginButton loginButton;
   // CallbackManager callbackManager;
    private TextView info;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start);

        /* facebook stuff
        FacebookSdk.sdkInitialize(this.getApplicationContext());

        callbackManager = CallbackManager.Factory.create();


        info = (TextView)findViewById(R.id.info);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException exception) {

                    }

                });*/

        ImageButton btnsignup = (ImageButton) findViewById(R.id.signup_button);
        btnsignup.setOnClickListener(new View.OnClickListener() {

                                         public void onClick(View vi) {

                                             Intent intent = new Intent(vi.getContext(), SignupActivity.class);
                                             startActivityForResult(intent, 0);

                                         }
                                     }
        );

        ImageButton btn = (ImageButton) findViewById(R.id.signin_button);
        btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

              Intent intent = new Intent(v.getContext(), MainActivity.class);
              startActivityForResult(intent, 0);
              finish();
              }
           }
        );


    }
}
