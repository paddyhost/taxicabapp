package com.example.administrator.taxicab.UserRegistration.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.administrator.taxicab.Database.DatabaseHandler;

/**
 * Created by shree on 27/08/2017.
 */

public class UserRegistration_Helper
{
    private UserRegistration_Helper()
    {

    }

    public static boolean insertUserRegistrationData(Context context, UserTable user)
    {
        try
        {
            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(UserTable.CL_USERID, user.getUSERID_VALUE());
            values.put(UserTable.CL_IMAGE,user.getIMAGE_VALUE());
            values.put(UserTable.CL_EMAIL, user.getEMAIL_VALUE());
            values.put(UserTable.CL_REGISTRATION_TIME, user.getREGISTRATION_TIME_VALUE());
            values.put(UserTable.CL_LANGUTUDE, user.getLANGUTUDE_VALUE());
            values.put(UserTable.CL_LATITUDE, user.getLATITUDE_VALUE());
            values.put(UserTable.CL_USER_NAME,user.getUSER_NAME_VALUE());
            values.put(UserTable.CL_MOBILE_NO,user.getMOBILE_NO_VALUE());
            values.put(UserTable.CL_PASSWORD,user.getPASSWORD_VALUE());
            // Inserting Row
            db.insert(UserTable.TABLE_USER_REGISTRATION, null, values);
            db.close(); // Closing database connection
            //insertDefoultvalue(context);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
        finally
        {

        }
    }
    public static UserTable getUserInfo(Context context) {
        UserTable user_info=null;
        try
        {

            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM "+UserTable.TABLE_USER_REGISTRATION,null);
            //+" WHERE "+User_Table.CL_USERID+"="+new PrefManager(context).getMobileNumber()
            user_info  =UserTable.getUsertable();
            if (cursor != null) {
                cursor.moveToFirst();
                user_info.setUSERID_VALUE(cursor.getString(cursor.getColumnIndex(UserTable.CL_USERID)));
                user_info.setUSER_NAME_VALUE(cursor.getString(cursor.getColumnIndex(UserTable.CL_USER_NAME)));
                user_info.setIMAGE_VALUE(cursor.getBlob(cursor.getColumnIndex(UserTable.CL_IMAGE)));
                user_info.setEMAIL_VALUE(cursor.getString(cursor.getColumnIndex(UserTable.CL_EMAIL)));
                user_info.setREGISTRATION_TIME_VALUE(cursor.getString(cursor.getColumnIndex(UserTable.CL_REGISTRATION_TIME)));
                user_info.setLANGUTUDE_VALUE(cursor.getString(cursor.getColumnIndex(UserTable.CL_LANGUTUDE)));
                user_info.setLATITUDE_VALUE(cursor.getString(cursor.getColumnIndex(UserTable.CL_LATITUDE)));
                user_info.setMOBILE_NO_VALUE(cursor.getString(cursor.getColumnIndex(UserTable.CL_MOBILE_NO)));
                user_info.setPASSWORD_VALUE(cursor.getString(cursor.getColumnIndex(UserTable.CL_PASSWORD)));

            }

            return user_info;

        }
        catch (Exception e)
        {
            return user_info;

        }

        // return contact

    }
    public static boolean updateUserInfo(Context context,UserTable user) {
        try
        {

            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(UserTable.CL_USERID, user.getUSERID_VALUE());
            values.put(UserTable.CL_USER_NAME,user.getUSER_NAME_VALUE());
            values.put(UserTable.CL_IMAGE,user.getIMAGE_VALUE());
            values.put(UserTable.CL_EMAIL, user.getEMAIL_VALUE());
            values.put(UserTable.CL_REGISTRATION_TIME, user.getREGISTRATION_TIME_VALUE());
            //Google Adress Field Added New
            values.put(UserTable.CL_MOBILE_NO, user.getMOBILE_NO_VALUE());
            values.put(UserTable.CL_LANGUTUDE, user.getLANGUTUDE_VALUE());
            values.put(UserTable.CL_LATITUDE, user.getLATITUDE_VALUE());
            values.put(UserTable.CL_PASSWORD,user.getPASSWORD_VALUE());
            // upadating Row
            db.update(UserTable.TABLE_USER_REGISTRATION, values, UserTable.CL_USERID+"="+user.getUSERID_VALUE(), null);
            db.close(); // Closing database connection
            return true;
        }
        catch (Exception e)
        {
            return false;
        }


        // return contact


    }
    public static boolean updateMobileInfo(Context context,UserTable user,String oldMobile) {
        try
        {
            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(UserTable.CL_USER_NAME,user.getUSER_NAME_VALUE());
            values.put(UserTable.CL_IMAGE,user.getIMAGE_VALUE());
            values.put(UserTable.CL_EMAIL, user.getEMAIL_VALUE());
            values.put(UserTable.CL_REGISTRATION_TIME, user.getREGISTRATION_TIME_VALUE());
            values.put(UserTable.CL_MOBILE_NO, user.getMOBILE_NO_VALUE());
            values.put(UserTable.CL_PASSWORD,user.getPASSWORD_VALUE());
            values.put(UserTable.CL_LANGUTUDE, user.getLANGUTUDE_VALUE());
            values.put(UserTable.CL_LATITUDE, user.getLATITUDE_VALUE());
            values.put(UserTable.CL_USERID, user.getUSERID_VALUE());

            // upadating Row
            db.update(UserTable.TABLE_USER_REGISTRATION, values, UserTable.CL_USERID+"="+oldMobile, null);
            db.close(); // Closing database connection
            return true;
        }
        catch (Exception e)
        {
            return false;
        }


        // return contact


    }


    public static boolean deleteAllUserInfo(Context context)
    {
        try
        {
            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();

            db.execSQL("DELETE FROM "+ UserTable.TABLE_USER_REGISTRATION); //delete all rows in a table

            Toast.makeText(context,"User Deleted Successfully",Toast.LENGTH_SHORT).show();


            db.close();

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

  }
