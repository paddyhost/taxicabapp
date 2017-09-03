package com.example.administrator.taxicab.ConfirmBooking.apihelper;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.taxicab.App.MyApplication;
import com.example.administrator.taxicab.App.PrefManager;
import com.example.administrator.taxicab.ConfirmBooking.database.Booking_Table;
import com.example.administrator.taxicab.ConfirmBooking.database.Booking_Table_Helper;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

import static com.example.administrator.taxicab.App.Constants.API_HOST_ADDRESS;

/**
 * Created by shree on 03/09/2017.
 */

public class WebBookingHelper
{
    public static String urlAddNewRide = API_HOST_ADDRESS+"/addNewRide";
    public static String urlUpdateRide = API_HOST_ADDRESS+"/updateRide";

    public static boolean addNewRide(final Activity activity, final Booking_Table booking_table)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, urlAddNewRide, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if(responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("Ride registered successfully"))
                        {
                            JSONArray rs=responce.getJSONArray("result");
                            JSONObject result=rs.getJSONObject(0);

                                booking_table.setRIDE_ID_VALUE(result.getString("id"));
                                booking_table.setSOURCE_LONGITUDE_VALUE(result.getString("s_langitude"));
                                booking_table.setSOURCE_LATITUDE_VALUE(result.getString("s_latitude"));
                                booking_table.setDESTINATION_LONGITUDE_VALUE(result.getString("d_langitude"));
                                booking_table.setDESTINATION_LATITUDE_VALUE(result.getString("d_latitude"));
                                booking_table.setBOOKING_TIME_VALUE(result.getString("BokingTime"));
                            booking_table.setDRIVE_ID_VALUE(result.getString("Driver_id"));
                            booking_table.setDISTANCE_VALUE(result.getString("distance"));
                            booking_table.setAMOUNT_VALUE(result.getString("amount"));
                            booking_table.setSTATUS_VALUE(result.getString("status"));
                            booking_table.setVEHICLE_ID_VALUE(result.getString("vehicle_id"));
                            booking_table.setRIDE_TYPE_VALUE(result.getString("ride_type"));
                            booking_table.setRIDE_DATETIME_VALUE(result.getString("ride_datetime"));

                            if(Booking_Table_Helper.insertNewRide(activity,booking_table))
                            {
                                Toast.makeText(activity,"Booking  Done!",Toast.LENGTH_SHORT).show();
                                booking_table.setBOOK_RIDE_STATUS(Booking_Table.BOOKING_SUCCESS);
                                EventBus.getDefault().post(booking_table);

                                updateRideStatus(activity,booking_table,"FC");

                            }
                            else
                            {
                                Toast.makeText(activity,"Booking  Failed",Toast.LENGTH_SHORT).show();
                            }


                        }

                        else
                        {
                            //Toast.makeText(activity,"Registration Failed",Toast.LENGTH_SHORT).show();
                            booking_table.setBOOK_RIDE_STATUS(Booking_Table.BOOKING_FAILED);
                            EventBus.getDefault().post(booking_table);

                        }


                    }
                    else
                    {
                        //Toast.makeText(activity,"Registration Failed",Toast.LENGTH_SHORT).show();
                        booking_table.setBOOK_RIDE_STATUS(Booking_Table.BOOKING_FAILED);
                        EventBus.getDefault().post(booking_table);

                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    booking_table.setBOOK_RIDE_STATUS(Booking_Table.BOOKING_FAILED);
                    EventBus.getDefault().post(booking_table);

                }
            }
        }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> params = new Hashtable<String, String>();
                params.put("key", "1234");
                params.put("format", "json");

                params.put("client_id",new PrefManager(activity).getclient_master_id());
                params.put("s_langitude",booking_table.getDESTINATION_LONGITUDE_VALUE());
                params.put("s_latitude",booking_table.getDESTINATION_LATITUDE_VALUE());
                params.put("d_langitude",booking_table.getDESTINATION_LONGITUDE_VALUE());
                params.put("d_latitude",booking_table.getDESTINATION_LATITUDE_VALUE());
                params.put("BokingTime",booking_table.getBOOKING_TIME_VALUE());
                params.put("driver_id",booking_table.getDRIVE_ID_VALUE());
                params.put("distance",booking_table.getDISTANCE_VALUE());
                params.put("amount",booking_table.getAMOUNT_VALUE());
                params.put("status",booking_table.getSTATUS_VALUE());
                params.put("vehicle_id",booking_table.getVEHICLE_ID_VALUE());
                params.put("ride_type",booking_table.getRIDE_TYPE_VALUE());


                //returning parameters
                return params;
            }


        };

        // Adding request to request queue
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        strReq.setRetryPolicy(policy);
        MyApplication.getInstance().addToRequestQueue(strReq);
        return  true;


    }

    public static boolean updateRideStatus(final Activity activity, final Booking_Table booking_table, final String status)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, urlUpdateRide, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if(responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("Ride updated successfully"))
                        {
                            JSONArray rs=responce.getJSONArray("result");
                            JSONObject result=rs.getJSONObject(0);

                            booking_table.setRIDE_ID_VALUE(result.getString("id"));
                            booking_table.setSOURCE_LONGITUDE_VALUE(result.getString("s_langitude"));
                            booking_table.setSOURCE_LATITUDE_VALUE(result.getString("s_latitude"));
                            booking_table.setDESTINATION_LONGITUDE_VALUE(result.getString("d_langitude"));
                            booking_table.setDESTINATION_LATITUDE_VALUE(result.getString("d_latitude"));
                            booking_table.setBOOKING_TIME_VALUE(result.getString("BokingTime"));
                            booking_table.setDRIVE_ID_VALUE(result.getString("Driver_id"));
                            booking_table.setDISTANCE_VALUE(result.getString("distance"));
                            booking_table.setAMOUNT_VALUE(result.getString("amount"));
                            booking_table.setSTATUS_VALUE(result.getString("status"));
                            booking_table.setVEHICLE_ID_VALUE(result.getString("vehicle_id"));
                            booking_table.setRIDE_TYPE_VALUE(result.getString("ride_type"));
                            booking_table.setRIDE_DATETIME_VALUE(result.getString("ride_datetime"));

                            if(Booking_Table_Helper.updateRideDetails(activity,booking_table))
                            {
                                Toast.makeText(activity,"Booking  Updated !",Toast.LENGTH_SHORT).show();
                                booking_table.setBOOKING_STATUS(Booking_Table.STATUS_UPDATE_SUCCESS);
                                EventBus.getDefault().post(booking_table);

                            }
                            else
                            {
                                Toast.makeText(activity,"Booking update Failed",Toast.LENGTH_SHORT).show();
                            }

                        }

                        else
                        {
                            //Toast.makeText(activity,"Registration Failed",Toast.LENGTH_SHORT).show();
                            booking_table.setBOOKING_STATUS(Booking_Table.STATUS_UPDATE_FAILED);
                            EventBus.getDefault().post(booking_table);

                        }


                    }
                    else
                    {
                        //Toast.makeText(activity,"Registration Failed",Toast.LENGTH_SHORT).show();
                        booking_table.setBOOKING_STATUS(Booking_Table.STATUS_UPDATE_FAILED);
                        EventBus.getDefault().post(booking_table);

                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    booking_table.setBOOKING_STATUS(Booking_Table.STATUS_UPDATE_FAILED);
                    EventBus.getDefault().post(booking_table);

                }
            }
        }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> params = new Hashtable<String, String>();
                params.put("key", "1234");
                params.put("format", "json");
                params.put("client_id",new PrefManager(activity).getclient_master_id());
                params.put("status",status);
                params.put("ride_id",booking_table.getRIDE_ID_VALUE());

                //returning parameters
                return params;
            }


        };

        // Adding request to request queue
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        strReq.setRetryPolicy(policy);
        MyApplication.getInstance().addToRequestQueue(strReq);
        return  true;


    }

}
