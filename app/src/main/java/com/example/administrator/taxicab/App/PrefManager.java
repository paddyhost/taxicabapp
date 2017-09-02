package com.example.administrator.taxicab.App;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;


public class PrefManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    public static  int PRIVATE_MODE = 0;

    // Shared preferences file name
    public static final String PREF_NAME = "TaxiCab";

    // All Shared Preferences Keys
    private static final String KEY_MOBILE_NUMBER = "mobile_number";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_IS_LOGGED_OUT = "isLoggedOut";
    private static final String ClIENT_MASTER_ID = "clientmaster_id";
    private static final String UNIQUE_ID = "uniqueid";
    private static final String KEY_MOBILE = "mobile";




    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setMobileNumber(String mobileNumber) {
        editor.putString(KEY_MOBILE_NUMBER, mobileNumber);
        editor.commit();
    }

    public String getMobileNumber()
    {
        return pref.getString(KEY_MOBILE_NUMBER, null);
    }

    public void createLogin( String mobile) {

        editor.putString(KEY_MOBILE, mobile);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.commit();
    }




    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }
    public boolean isLoggedOut()
    {
        return pref.getBoolean(KEY_IS_LOGGED_OUT, false);
    }
    public void setLoggedOut()
    {
        editor.putBoolean(KEY_IS_LOGGED_IN,false);
        editor.commit();
    }

    public void clearSession()
    {
        editor.remove(PREF_NAME);
        editor.remove(KEY_MOBILE_NUMBER);
        editor.remove(KEY_MOBILE);
        editor.remove(KEY_IS_LOGGED_IN);
        editor.remove(KEY_IS_LOGGED_OUT);
        editor.remove(ClIENT_MASTER_ID);
        editor.remove(UNIQUE_ID);
        editor.remove(pref.getAll().toString());
        editor.clear();
        editor.apply();
        editor.commit();
    }


    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> profile = new HashMap<>();
       // profile.put("name", pref.getString(KEY_NAME, null));
       // profile.put("email", pref.getString(KEY_EMAIL, null));
        profile.put("mobile", pref.getString(KEY_MOBILE, null));
        return profile;
    }

    public void setregistrationSucess( String client_master_id,String unique_id) {

        editor.putString(ClIENT_MASTER_ID, client_master_id);
        editor.putString(UNIQUE_ID, unique_id);
        editor.commit();
    }
    public String getclient_master_id()
    {
        return pref.getString(ClIENT_MASTER_ID, null);
    }

    public String getunique_id()
    {
        return pref.getString(UNIQUE_ID, null);
    }


}
