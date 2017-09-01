package com.example.administrator.taxicab.Database.UserDatabase;

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

   /* private static void insertDefoultvalue(Context context)
    {
        //insert chat default notifications
        Notification_Table notification_table_chat=Notification_Table.getNotification_table();
        notification_table_chat.setCATEGORY_VALUE(Notification_Table.CHAT);
        notification_table_chat.setSTATUS_VALUE(Notification_Table.ACTIVE);
        notification_table_chat.setPOPUP_TYPE_VALUE(Notification_Table.POPALWAYSdISPLAY);
        notification_table_chat.setVIBRATE_TYPE_VALUE(Vibrate.VIBRATE_DEFAULT);
        notification_table_chat.setRINGTONE_VALUE("Defoult");
        NotificationHelper.setNotificationSetting(context,notification_table_chat);

        //insert group default notifications
        Notification_Table notification_table_group=Notification_Table.getNotification_table();
        notification_table_group.setCATEGORY_VALUE(Notification_Table.GROUP);
        notification_table_group.setSTATUS_VALUE(Notification_Table.ACTIVE);
        notification_table_group.setPOPUP_TYPE_VALUE(Notification_Table.POPALWAYSdISPLAY);
        notification_table_group.setVIBRATE_TYPE_VALUE(Vibrate.VIBRATE_DEFAULT);
        notification_table_group.setRINGTONE_VALUE("Defoult");
        NotificationHelper.setNotificationSetting(context,notification_table_group);

        //insert number default notifications
        Notification_Table notification_table_number=Notification_Table.getNotification_table();
        notification_table_number.setCATEGORY_VALUE(Notification_Table.NUMBER);
        notification_table_number.setSTATUS_VALUE(Notification_Table.DESABLE);
        notification_table_number.setPOPUP_TYPE_VALUE(Notification_Table.POPALWAYSdISPLAY);
        notification_table_number.setVIBRATE_TYPE_VALUE(Vibrate.VIBRATE_DEFAULT);
        notification_table_number.setRINGTONE_VALUE("Defoult");
        NotificationHelper.setNotificationSetting(context,notification_table_number);

        //setting default privacy settings
        PrivacySetting_Table privacy_setting_table=PrivacySetting_Table.getPrivacySettingObject();
        Privacy_Setting_Helper.setPrivacySetting(context,privacy_setting_table);

        //setting default font style
        Table_Font_Settings font_style_settings=Table_Font_Settings.getFontSettings_table();
        font_style_settings.setFontStyleValue(Typeface.NORMAL);
        font_style_settings.setFontSettingTypeValue(Table_Font_Settings.FONT_STYLE);
        Font_Settings_Helper.setFontSettings(context,font_style_settings);

        //setting default font size
        Table_Font_Settings font_size_settings=Table_Font_Settings.getFontSettings_table();
        font_size_settings.setFontSizeValue(16);
        font_size_settings.setFontSettingTypeValue(Table_Font_Settings.FONT_SIZE);
        Font_Settings_Helper.setFontSettings(context,font_size_settings);


    }*/
}
