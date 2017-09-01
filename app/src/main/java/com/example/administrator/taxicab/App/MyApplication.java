package com.example.administrator.taxicab.App;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.administrator.taxicab.Utils.LruBitmapCache;

/**
 * Created by shree on 28/08/2017.
 */

public class MyApplication  extends MultiDexApplication {


    public static final String TAG = MyApplication.class
            .getSimpleName();

    public static RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static MyApplication mInstance;
    public static final int LOCATION_SETTINGS_REQUEST = 1;
    public static final int WIFI_MOBILE_REQUEST = 12;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        mInstance = this;
        //mRequestQueue = Volley.newRequestQueue(MyApplication.this);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
            mRequestQueue.getCache().clear();
        }
        mRequestQueue.getCache().clear();

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        req.setShouldCache(false);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        req.setShouldCache(false);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.getCache().clear();
            mRequestQueue.cancelAll(tag);
        }
    }

    public static  boolean isInternetConnected(Activity context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiInfo != null && wifiInfo.isConnected()) || (mobileInfo != null && mobileInfo.isConnected()))
        {
            return true;
        } else
        {
            showDialog(context);
            return false;
        }
    }
    private static void showDialog(final Activity context)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Please turn on your mobile data or Wi-Fi to send information.")
                .setTitle("Unable to connect")
                .setCancelable(false)
                .setPositiveButton("Settings",

                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                Intent i = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                context.startActivityForResult(i,WIFI_MOBILE_REQUEST);
                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }

    public static boolean checkLocationServicePermissions(final Activity context)
    {
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;

        try
        {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }


        if(!gps_enabled)
        {
            showLocationPermissionDialog(context);
            return gps_enabled;
        }
        else
        {
            return gps_enabled;
        }
    }

   public static void showLocationPermissionDialog(final Activity context)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("GPS is not enabled. Please enable GPS services to use this app !")
                .setTitle("Unable to locate GPS Settings")
                .setCancelable(false)
                .setPositiveButton("Settings",

                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                context.startActivityForResult(myIntent,LOCATION_SETTINGS_REQUEST);
                                //get gps
                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.show();

    }

    //if (isAppInstalled("com.gau.go.launcherex"))
    //check if application is installed or not
    private boolean isAppInstalled(String uri) {
        PackageManager pm = getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }


}
