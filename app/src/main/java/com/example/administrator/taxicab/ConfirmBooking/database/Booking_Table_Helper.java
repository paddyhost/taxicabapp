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

public class Booking_Table_Helper
{
    public static boolean insertNewRide(Context context, Booking_Table booking)
    {
        try
        {
            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Booking_Table.CL_RIDE_ID, booking.getRIDE_ID_VALUE());
            values.put(Booking_Table.CL_SOURCE_LONGITUDE, booking.getSOURCE_LONGITUDE_VALUE());
            values.put(Booking_Table.CL_SOURCE_LATITUDE, booking.getSOURCE_LATITUDE_VALUE());
            values.put(Booking_Table.CL_DESTINATION_LONGITUDE, booking.getDESTINATION_LONGITUDE_VALUE());
            values.put(Booking_Table.CL_DESTINATION_LATITUDE, booking.getDESTINATION_LATITUDE_VALUE());
            values.put(Booking_Table.CL_BOOKING_TIME, booking.getBOOKING_TIME_VALUE());
            values.put(Booking_Table.CL_DRIVER_ID, booking.getDRIVE_ID_VALUE());
            values.put(Booking_Table.CL_DISTANCE, booking.getDISTANCE_VALUE());
            values.put(Booking_Table.CL_AMOUNT, booking.getAMOUNT_VALUE());
            values.put(Booking_Table.CL_STATUS, booking.getSTATUS_VALUE());
            values.put(Booking_Table.CL_VEHICLE_ID, booking.getVEHICLE_ID_VALUE());
            values.put(Booking_Table.CL_RIDE_TYPE, booking.getRIDE_TYPE_VALUE());
            values.put(Booking_Table.CL_RIDE_DATETIME, booking.getRIDE_DATETIME_VALUE());
            // Inserting Row
            db.insert(Booking_Table.BOOKING_TABLE, null, values);
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

    public static Booking_Table getRideDetails(Context context,String ride_id)
    {
        Booking_Table booking_info=null;
        try
        {

            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM "+Booking_Table.BOOKING_TABLE+" WHERE "+ Booking_Table.CL_RIDE_ID+" = '"+ride_id+"'",null);
            booking_info  = new Booking_Table();
            if (cursor != null) {
                cursor.moveToFirst();
                booking_info.setRIDE_ID_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_RIDE_ID)));
                booking_info.setSOURCE_LONGITUDE_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_SOURCE_LONGITUDE)));
                booking_info.setSOURCE_LATITUDE_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_SOURCE_LATITUDE)));
                booking_info.setDESTINATION_LONGITUDE_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_DESTINATION_LONGITUDE)));
                booking_info.setDESTINATION_LATITUDE_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_DESTINATION_LATITUDE)));
                booking_info.setBOOKING_TIME_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_BOOKING_TIME)));
                booking_info.setDRIVE_ID_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_DRIVER_ID)));
                booking_info.setDISTANCE_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_DISTANCE)));
                booking_info.setAMOUNT_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_AMOUNT)));
                booking_info.setSTATUS_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_STATUS)));
                booking_info.setVEHICLE_ID_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_VEHICLE_ID)));
                booking_info.setRIDE_TYPE_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_RIDE_TYPE)));
                booking_info.setRIDE_DATETIME_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_RIDE_DATETIME)));

            }

            return booking_info;

        }
        catch (Exception e)
        {
            return booking_info;

        }
    }

    public static boolean updateRideDetails(Context context,Booking_Table booking)
    {
        try
        {

            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Booking_Table.CL_RIDE_ID, booking.getRIDE_ID_VALUE());
            values.put(Booking_Table.CL_SOURCE_LONGITUDE, booking.getSOURCE_LONGITUDE_VALUE());
            values.put(Booking_Table.CL_SOURCE_LATITUDE, booking.getSOURCE_LATITUDE_VALUE());
            values.put(Booking_Table.CL_DESTINATION_LONGITUDE, booking.getDESTINATION_LONGITUDE_VALUE());
            values.put(Booking_Table.CL_DESTINATION_LATITUDE, booking.getDESTINATION_LATITUDE_VALUE());
            values.put(Booking_Table.CL_BOOKING_TIME, booking.getBOOKING_TIME_VALUE());
            values.put(Booking_Table.CL_DRIVER_ID, booking.getDRIVE_ID_VALUE());
            values.put(Booking_Table.CL_DISTANCE, booking.getDISTANCE_VALUE());
            values.put(Booking_Table.CL_AMOUNT, booking.getAMOUNT_VALUE());
            values.put(Booking_Table.CL_STATUS, booking.getSTATUS_VALUE());
            values.put(Booking_Table.CL_VEHICLE_ID, booking.getVEHICLE_ID_VALUE());
            values.put(Booking_Table.CL_RIDE_TYPE, booking.getRIDE_TYPE_VALUE());
            values.put(Booking_Table.CL_RIDE_DATETIME, booking.getRIDE_DATETIME_VALUE());


            // upadating Row
            db.update(Booking_Table.BOOKING_TABLE, values, Booking_Table.CL_RIDE_ID+" = "+booking.getRIDE_ID_VALUE(), null);
            db.close(); // Closing database connection
            return true;
        }
        catch (Exception e)
        {
            return false;
        }



    }

    public static ArrayList<Booking_Table> getRidesList(Context context)
    {
        try {
            ArrayList<Booking_Table> array_list = new ArrayList<Booking_Table>();
            SQLiteDatabase db = DatabaseHandler.getInstance(context).getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + Booking_Table.BOOKING_TABLE, null);
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                Booking_Table booking_info = new Booking_Table();

                booking_info.setRIDE_ID_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_RIDE_ID)));
                booking_info.setSOURCE_LONGITUDE_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_SOURCE_LONGITUDE)));
                booking_info.setSOURCE_LATITUDE_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_SOURCE_LATITUDE)));
                booking_info.setDESTINATION_LONGITUDE_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_DESTINATION_LONGITUDE)));
                booking_info.setDESTINATION_LATITUDE_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_DESTINATION_LATITUDE)));
                booking_info.setBOOKING_TIME_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_BOOKING_TIME)));
                booking_info.setDRIVE_ID_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_DRIVER_ID)));
                booking_info.setDISTANCE_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_DISTANCE)));
                booking_info.setAMOUNT_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_AMOUNT)));
                booking_info.setSTATUS_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_STATUS)));
                booking_info.setVEHICLE_ID_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_VEHICLE_ID)));
                booking_info.setRIDE_TYPE_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_RIDE_TYPE)));
                booking_info.setRIDE_DATETIME_VALUE(cursor.getString(cursor.getColumnIndex(Booking_Table.CL_RIDE_DATETIME)));

                array_list.add(booking_info);
                cursor.moveToNext();

            }

            return array_list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteRide(Context context,Booking_Table booking_table)
    {
        try
        {
            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + Booking_Table.BOOKING_TABLE + " WHERE " + Booking_Table.CL_RIDE_ID + "='" + booking_table.getRIDE_ID_VALUE() + "'");


            Toast.makeText(context,"Ride Deleted Successfully",Toast.LENGTH_SHORT).show();


            db.close();

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean deleteAllRideDetails(Context context)
    {
        try
        {
            SQLiteDatabase db =  DatabaseHandler.getInstance(context).getWritableDatabase();

            db.execSQL("DELETE FROM "+ Booking_Table.BOOKING_TABLE); //delete all rows in a table


            Toast.makeText(context,"All Rides Deleted Successfully",Toast.LENGTH_SHORT).show();


            db.close();

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
