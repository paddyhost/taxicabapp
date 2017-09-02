package com.example.administrator.taxicab.Database.UserDatabase.WebUserRegistrationAPI;

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
import com.example.administrator.taxicab.Database.UserDatabase.UserRegistration_Helper;
import com.example.administrator.taxicab.Database.UserDatabase.UserTable;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

import static com.example.administrator.taxicab.App.Constants.API_HOST_ADDRESS;

/**
 * Created by shree on 28/08/2017.
 */

public class WebUserRegistrationHelper
{
    public static String urlRegistration = API_HOST_ADDRESS+"/clientRgistration";
    public static String urlLogin = API_HOST_ADDRESS+"/login";

    public static boolean newUserRegistration(final Activity activity, final UserTable userTable)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, urlRegistration, new Response.Listener<String>() {
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
                            if(UserRegistration_Helper.insertUserRegistrationData(activity,userTable))
                            {
                                Toast.makeText(activity,"Registration Successful",Toast.LENGTH_SHORT).show();
                                new PrefManager(activity).createLogin(new PrefManager(activity).getMobileNumber());

                                userTable.setUSERID_VALUE(result.getString("id"));
                                userTable.setUSER_NAME_VALUE(result.getString("Name"));
                                userTable.setEMAIL_VALUE(result.getString("Email"));
                                userTable.setMOBILE_NO_VALUE(result.getString("mobile"));
                                userTable.setPASSWORD_VALUE(result.getString("password"));
                                //userTable.setIMAGE_VALUE(result.getString("photo"));
                                userTable.setLANGUTUDE_VALUE(result.getString("langitude"));
                                userTable.setLATITUDE_VALUE(result.getString("latitude"));
                                UserRegistration_Helper.updateUserInfo(activity,userTable);


                                userTable.setUSER_REGISTER_STATUS(UserTable.USER_REGISTER_SUCCESS);
                                EventBus.getDefault().post(userTable);


                              }
                            else
                            {
                                Toast.makeText(activity,"Local Registration Failed",Toast.LENGTH_SHORT).show();
                            }


                        }

                        else
                        {
                            //Toast.makeText(activity,"Registration Failed",Toast.LENGTH_SHORT).show();
                            userTable.setUSER_REGISTER_STATUS(UserTable.USER_REGISTER_FAILED);
                            EventBus.getDefault().post(userTable);

                        }


                    }
                    else
                    {
                        //Toast.makeText(activity,"Registration Failed",Toast.LENGTH_SHORT).show();
                        userTable.setUSER_REGISTER_STATUS(UserTable.USER_REGISTER_FAILED);
                        EventBus.getDefault().post(userTable);

                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    userTable.setUSER_REGISTER_STATUS(UserTable.USER_REGISTER_FAILED);
                    EventBus.getDefault().post(userTable);

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

                params.put("name", userTable.getUSER_NAME_VALUE());
                params.put("mobile",new PrefManager(activity).getMobileNumber());
                params.put("password",userTable.getPASSWORD_VALUE());
                params.put("email",userTable.getEMAIL_VALUE());
                params.put("langitude","1111");
                params.put("latitude","2222");
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

    public static boolean userLogin(final Activity activity, final UserTable userTable)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, urlLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if(responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("Login successfully"))
                        {
                            Toast.makeText(activity,"Login Successful",Toast.LENGTH_SHORT).show();
                            new PrefManager(activity).createLogin(new PrefManager(activity).getMobileNumber());

                            userTable.setUSER_LOGIN_STATUS(UserTable.USER_LOGIN_SUCCESS);
                            EventBus.getDefault().post(userTable);

                        }

                        else
                        {
                            Toast.makeText(activity,"Login Failed",Toast.LENGTH_SHORT).show();
                            userTable.setUSER_LOGIN_STATUS(UserTable.USER_LOGIN_FAILED);
                            EventBus.getDefault().post(userTable);

                        }


                    }
                    else
                    {
                        //Toast.makeText(activity,"Registration Failed",Toast.LENGTH_SHORT).show();
                        userTable.setUSER_LOGIN_STATUS(UserTable.USER_LOGIN_FAILED);
                        EventBus.getDefault().post(userTable);

                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    userTable.setUSER_LOGIN_STATUS(UserTable.USER_LOGIN_FAILED);
                    EventBus.getDefault().post(userTable);

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
                params.put("format","json");
                params.put("mobile",new PrefManager(activity).getMobileNumber());
                params.put("password",userTable.getPASSWORD_VALUE());

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
