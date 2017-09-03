package com.example.administrator.taxicab.ConfirmBooking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.taxicab.Activity.HomeScreen;
import com.example.administrator.taxicab.R;

public class BookingDetails extends AppCompatActivity
{
    private Button goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        goback = (Button) findViewById(R.id.goback_bookingdetails);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingDetails.this,HomeScreen.class);
                startActivity(intent);
            }
        });
    }
}
