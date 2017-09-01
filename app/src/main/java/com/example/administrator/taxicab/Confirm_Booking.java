package com.example.administrator.taxicab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Confirm_Booking extends AppCompatActivity {
    private Button confirmbooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        confirmbooking = (Button)findViewById(R.id.confirm_booking);
        confirmbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(Confirm_Booking.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Congratulations !")
                        .setContentText("Your booking confirmed")
                        .setConfirmText("Done !")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                Intent intent = new Intent(Confirm_Booking.this,BookingDetails.class);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });
    }
}
