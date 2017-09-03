package com.example.administrator.taxicab.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.taxicab.App.MyApplication;
import com.example.administrator.taxicab.App.PrefManager;
import com.example.administrator.taxicab.UserRegistration.activity.Login;
import com.example.administrator.taxicab.R;

/**
 * Created by shree on 31/08/2017.
 */

public class Splashscreen extends AppCompatActivity
{
    private PrefManager pref;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        pref = new PrefManager(this);

        new Handler().postDelayed(new Runnable() {

            //*
            //Showing splash screen with a timer. This will be useful when you
            // want to show case your app logo / company
            //*

            @Override
            public void run()
            {
                checkUserLoginStatus();
            }
        }, SPLASH_TIME_OUT);


    }

    private void checkUserLoginStatus()
    {
        // Checking for user session
        // if user is already logged in, take him to main activity
        if (pref.isLoggedIn())
        {
            if(MyApplication.isInternetConnected(Splashscreen.this)) {
                    if (MyApplication.checkLocationServicePermissions(Splashscreen.this)) {

                            Intent myintent = new Intent(Splashscreen.this, HomeScreen.class);
                            myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(myintent);
                            finish();
                        }
             }

        }
        else
        {
            if(MyApplication.isInternetConnected(Splashscreen.this)) {
                    if (MyApplication.checkLocationServicePermissions(Splashscreen.this)) {

                            Intent myintent = new Intent(Splashscreen.this, Login.class);
                            myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(myintent);
                            finish();
                        }
                    }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MyApplication.LOCATION_SETTINGS_REQUEST) {
            // user is back from location settings - check if location services are now enabled
            checkUserLoginStatus();
        }
        if(requestCode == MyApplication.WIFI_MOBILE_REQUEST)
        {
            checkUserLoginStatus();
        }
    }
}
