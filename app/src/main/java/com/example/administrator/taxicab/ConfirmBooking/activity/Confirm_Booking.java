package com.example.administrator.taxicab.ConfirmBooking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.taxicab.ConfirmBooking.apihelper.WebBookingHelper;
import com.example.administrator.taxicab.ConfirmBooking.database.Booking_Table;
import com.example.administrator.taxicab.ConfirmBooking.database.Booking_Table_Helper;
import com.example.administrator.taxicab.R;
import com.example.administrator.taxicab.UserRegistration.database.UserRegistration_Helper;
import com.example.administrator.taxicab.UserRegistration.database.UserTable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Confirm_Booking extends AppCompatActivity {
    private Button confirmbooking;

    private SweetAlertDialog confirmDialog;

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/


    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.

    }


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
                confirmDialog= new SweetAlertDialog(Confirm_Booking.this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("Loading").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        });

                confirmDialog.show();
                confirmDialog.setCancelable(false);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String currentDateandTime = sdf.format(new Date());

                Booking_Table bookingDetails=new Booking_Table("","11","22","33","44",currentDateandTime,"12","2","200",
                           "P","2334","now",currentDateandTime);
                WebBookingHelper.addNewRide(Confirm_Booking.this,bookingDetails);

            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Booking_Table bookingTable)
    {
        switch (bookingTable.getBOOK_RIDE_STATUS()) {
            case Booking_Table.BOOKING_FAILED:
                confirmDialog.setTitleText("Failed!")
                        .setConfirmText("OK")
                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);


                break;
            case Booking_Table.BOOKING_SUCCESS:
                confirmDialog.setTitleText("Success!")
                        .setConfirmText("OK")
                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                Intent intent = new Intent(Confirm_Booking.this,BookingDetails.class);
                startActivity(intent);
                this.finish();

                break;

        }

        switch (bookingTable.getBOOKING_STATUS()) {
            case Booking_Table.STATUS_UPDATE_SUCCESS:
                confirmDialog.setTitleText("Failed!")
                        .setConfirmText("OK")
                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);


                break;
            case Booking_Table.STATUS_UPDATE_FAILED:
                confirmDialog.setTitleText("Success!")
                        .setConfirmText("OK")
                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                break;

        }


    }

}
