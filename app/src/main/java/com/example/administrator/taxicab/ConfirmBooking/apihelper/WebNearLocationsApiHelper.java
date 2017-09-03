package com.example.administrator.taxicab.ConfirmBooking.apihelper;

import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.taxicab.App.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

import static com.example.administrator.taxicab.App.Constants.API_HOST_ADDRESS;

/**
 * Created by shree on 03/09/2017.
 */

public class WebNearLocationsApiHelper
{
    public static final String urlNearLocations=API_HOST_ADDRESS+"/getNearVehicles";

    public static boolean getNearLocations(final Activity activity, final String latitude, final String longitude)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, urlNearLocations, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if(responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("Client registered successfully"))
                        {
                            JSONArray rs=responce.getJSONArray("result");
                            JSONObject result=rs.getJSONObject(0);

                        }

                        else
                        {
                            //Toast.makeText(activity,"Registration Failed",Toast.LENGTH_SHORT).show();

                        }


                    }
                    else
                    {
                        //Toast.makeText(activity,"Registration Failed",Toast.LENGTH_SHORT).show();

                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();

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
                params.put("langitude",longitude);
                params.put("latitude",latitude);
                params.put("format", "json");


                //registration time not added in api


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
