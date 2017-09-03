package com.example.administrator.taxicab.ConfirmBooking.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.administrator.taxicab.Database.DatabaseHandler;

import java.util.ArrayList;

/**
 * Created by shree on 03/09/2017.
 */

public class Driver_Table_Helper
{
    public static boolean insertDriverDetails(Context context, Driver_Table driver)
    {
        try
        {
            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Driver_Table.CL_DRIVER_ID, driver.getDRIVER_ID_VALUE());
            values.put(Driver_Table.CL_DRIVER_NAME, driver.getDRIVER_NAME_VALUE());
            values.put(Driver_Table.CL_VEHICLE_ID, driver.getVEHICLE_ID_VALUE());
            values.put(Driver_Table.CL_VEHICLE_NAME, driver.getVEHICLE_NAME_VALUE());
            values.put(Driver_Table.CL_CAB_TYPE, driver.getCAB_TYPE_VALUE());
            values.put(Driver_Table.CL_CAB_NO, driver.getCAB_NO_VALUE());
            values.put(Driver_Table.IS_CAB_AC, driver.getIS_CAB_AC_VALUE());
            values.put(Driver_Table.CL_SEATER, driver.getSEATER_VALUE());
            values.put(Driver_Table.CL_DRIVER_MOBILE, driver.getDRIVER_MOBILE_VALUE());
            values.put(Driver_Table.CL_LATITUDE, driver.getLATITUDE_VALUE());
            values.put(Driver_Table.CL_LONGITUDE, driver.getLONGITUDE_VALUE());


            // Inserting Row
            db.insert(Driver_Table.DRIVER_TABLE, null, values);
            db.close(); // Closing database connection

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

    public static Driver_Table getDriverDetails(Context context,String driver_id)
    {
        Driver_Table driver_info=null;
        try
        {

            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM "+Driver_Table.DRIVER_TABLE+" WHERE "+ Driver_Table.CL_DRIVER_ID+" = '"+driver_id+"'",null);
            driver_info  = new Driver_Table();
            if (cursor != null)
            {
                cursor.moveToFirst();

                driver_info.setDRIVER_ID_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_DRIVER_ID)));
                driver_info.setDRIVER_NAME_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_DRIVER_NAME)));
                driver_info.setVEHICLE_ID_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_VEHICLE_ID)));
                driver_info.setVEHICLE_NAME_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_VEHICLE_NAME)));
                driver_info.setCAB_TYPE_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_CAB_TYPE)));
                driver_info.setCAB_NO_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_CAB_NO)));
                driver_info.setIS_CAB_AC_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.IS_CAB_AC)));
                driver_info.setSEATER_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_SEATER)));
                driver_info.setDRIVER_MOBILE_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_DRIVER_MOBILE)));
                driver_info.setLATITUDE_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_LATITUDE)));
                driver_info.setLONGITUDE_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_LONGITUDE)));

            }

            return driver_info;

        }
        catch (Exception e)
        {
            return driver_info;

        }
    }

    public static boolean updateDriverDetails(Context context,Driver_Table driver)
    {
        try
        {
            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Driver_Table.CL_DRIVER_ID, driver.getDRIVER_ID_VALUE());
            values.put(Driver_Table.CL_DRIVER_NAME, driver.getDRIVER_NAME_VALUE());
            values.put(Driver_Table.CL_VEHICLE_ID, driver.getVEHICLE_ID_VALUE());
            values.put(Driver_Table.CL_VEHICLE_NAME, driver.getVEHICLE_NAME_VALUE());
            values.put(Driver_Table.CL_CAB_TYPE, driver.getCAB_TYPE_VALUE());
            values.put(Driver_Table.CL_CAB_NO, driver.getCAB_NO_VALUE());
            values.put(Driver_Table.IS_CAB_AC, driver.getIS_CAB_AC_VALUE());
            values.put(Driver_Table.CL_SEATER, driver.getSEATER_VALUE());
            values.put(Driver_Table.CL_DRIVER_MOBILE, driver.getDRIVER_MOBILE_VALUE());
            values.put(Driver_Table.CL_LATITUDE, driver.getLATITUDE_VALUE());
            values.put(Driver_Table.CL_LONGITUDE, driver.getLONGITUDE_VALUE());

            // upadating Row
            db.update(Driver_Table.DRIVER_TABLE, values, Driver_Table.CL_DRIVER_ID+" = "+driver.getDRIVER_ID_VALUE(), null);
            db.close(); // Closing database connection
            return true;
        }
        catch (Exception e)
        {
            return false;
        }



    }

    public static ArrayList<Driver_Table> getDriverList(Context context)
    {
        try {
            ArrayList<Driver_Table> array_list = new ArrayList<Driver_Table>();
            SQLiteDatabase db = DatabaseHandler.getInstance(context).getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + Booking_Table.BOOKING_TABLE, null);
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                Driver_Table driver_info = new Driver_Table();

                driver_info.setDRIVER_ID_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_DRIVER_ID)));
                driver_info.setDRIVER_NAME_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_DRIVER_NAME)));
                driver_info.setVEHICLE_ID_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_VEHICLE_ID)));
                driver_info.setVEHICLE_NAME_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_VEHICLE_NAME)));
                driver_info.setCAB_TYPE_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_CAB_TYPE)));
                driver_info.setCAB_NO_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_CAB_NO)));
                driver_info.setIS_CAB_AC_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.IS_CAB_AC)));
                driver_info.setSEATER_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_SEATER)));
                driver_info.setDRIVER_MOBILE_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_DRIVER_MOBILE)));
                driver_info.setLATITUDE_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_LATITUDE)));
                driver_info.setLONGITUDE_VALUE(cursor.getString(cursor.getColumnIndex(Driver_Table.CL_LONGITUDE)));

                array_list.add(driver_info);
                cursor.moveToNext();

            }

            return array_list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteDriver(Context context,Driver_Table driver_table)
    {
        try
        {
            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + Driver_Table.DRIVER_TABLE + " WHERE " + Driver_Table.CL_DRIVER_ID + "='" + driver_table.getDRIVER_ID_VALUE() + "'");


            Toast.makeText(context,"Driver Deleted Successfully",Toast.LENGTH_SHORT).show();


            db.close();

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean deleteAllDriverDetails(Context context)
    {
        try
        {
            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();

            db.execSQL("DELETE FROM "+ Driver_Table.DRIVER_TABLE); //delete all rows in a table


            Toast.makeText(context,"All Drivers Deleted Successfully",Toast.LENGTH_SHORT).show();


            db.close();

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
