package com.example.administrator.taxicab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.taxicab.R;

import java.util.Set;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SetPassword extends AppCompatActivity {

    private Button confirm_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        confirm_signup = (Button) findViewById(R.id.confirm_signup);
        confirm_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(SetPassword.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Congratulations !")
                        .setContentText("Your registration successful !")
                        .setConfirmText("Ok")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                Intent intent = new Intent(SetPassword.this,HomeScreen.class);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });
    }
}
