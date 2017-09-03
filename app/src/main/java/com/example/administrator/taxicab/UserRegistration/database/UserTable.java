package com.example.administrator.taxicab.UserRegistration.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by shree on 27/08/2017.
 */

public class UserTable
{
    public static UserTable usertable;

    public UserTable()
    {

    }

    public static final String TABLE_USER_REGISTRATION = "User_Table";

    //attributes
    public static final String CL_USERID="User_ID",CL_USER_NAME="F_Name",CL_MOBILE_NO="Mobile_No",CL_IMAGE="Image" ,
            CL_EMAIL="Email_ID",CL_PASSWORD="Password",CL_REGISTRATION_TIME="Registration_Time",
            CL_LANGUTUDE="Langutude",CL_LATITUDE="Latitude";


    public static final String CREATE_USER_REGISTRATION_TABLE =
            "CREATE TABLE " + TABLE_USER_REGISTRATION +
                    "("+CL_USERID+" Bigint PRIMARY KEY, "+CL_USER_NAME+" TEXT, "+CL_IMAGE+" blob,"
                    +CL_MOBILE_NO+" TEXT, " +CL_PASSWORD+" TEXT, "+CL_EMAIL+" TEXT,"+CL_REGISTRATION_TIME+" TEXT,"
                    +CL_LANGUTUDE+" TEXT,"+CL_LATITUDE+" TEXT)";

    private String USERID_VALUE,USER_NAME_VALUE,MOBILE_NO_VALUE,EMAIL_VALUE,
            PASSWORD_VALUE,REGISTRATION_TIME_VALUE, LANGUTUDE_VALUE,LATITUDE_VALUE;

    private byte[] IMAGE_VALUE;

    public static final int USER_REGISTER_SUCCESS=1,USER_REGISTER_FAILED=0,USER_LOGIN_SUCCESS=2,USER_LOGIN_FAILED=3;

    private int USER_REGISTER_STATUS,USER_LOGIN_STATUS;


    private UserTable(String USER_NAME_VALUE,String MOBILE_NO_VALUE,String USER_PASSWORD, byte[] IMAGE_VALUE, String EMAIL_VALUE, String REGISTRATION_TIME_VALUE, String USERID_VALUE ,String LANGUTUDE_VALUE,String LATITUDE_VALUE ) {
        this.USER_NAME_VALUE = USER_NAME_VALUE;
        this.MOBILE_NO_VALUE=MOBILE_NO_VALUE;
        this.PASSWORD_VALUE=USER_PASSWORD;
        this.IMAGE_VALUE = IMAGE_VALUE;
        this.EMAIL_VALUE = EMAIL_VALUE;
        this.REGISTRATION_TIME_VALUE = REGISTRATION_TIME_VALUE;
        this.USERID_VALUE = USERID_VALUE;
        this.LANGUTUDE_VALUE=LANGUTUDE_VALUE;
        this.LATITUDE_VALUE=LATITUDE_VALUE;
    }


    public static UserTable getUsertable() {
        if(usertable==null)
        {
            usertable=new UserTable();
        }

        return usertable;
    }
    public static UserTable getUsertable(String USER_NAME_VALUE,String MOBILE_NO_VALUE, String USER_PASSWORD, Bitmap IMAGE_VALUE, String EMAIL_VALUE, String REGISTRATION_TIME_VALUE, String USERID_VALUE ,String LANGUTUDE_VALUE,String LATITUDE_VALUE ) {
        if(usertable==null)
        {   byte[] byteArray=null;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            if(IMAGE_VALUE!=null) {
                IMAGE_VALUE.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();
            }
            usertable=new UserTable(USER_NAME_VALUE,MOBILE_NO_VALUE, USER_PASSWORD,byteArray, EMAIL_VALUE, REGISTRATION_TIME_VALUE, USERID_VALUE,LANGUTUDE_VALUE,LATITUDE_VALUE);
        }

        return usertable;
    }

    public Bitmap get_Bitmap_IMAGE_VALUE() {

        try {
            Bitmap   bmpNew = BitmapFactory.decodeByteArray(IMAGE_VALUE, 0, IMAGE_VALUE.length);
            return bmpNew;
        }
        catch (Exception e)
        {
            return null;

        }


    }



    public byte[] getIMAGE_VALUE() {
        return IMAGE_VALUE;
    }

    public void setIMAGE_VALUE(byte[] IMAGE_VALUE) {
        this.IMAGE_VALUE = IMAGE_VALUE;
    }


    public String getUSERID_VALUE() {
        return USERID_VALUE;
    }

    public void setUSERID_VALUE(String USERID_VALUE) {
        this.USERID_VALUE = USERID_VALUE;
    }

    public String getUSER_NAME_VALUE() {
        return USER_NAME_VALUE;
    }

    public void setUSER_NAME_VALUE(String USER_NAME_VALUE) {
        this.USER_NAME_VALUE = USER_NAME_VALUE;
    }

    public String getMOBILE_NO_VALUE() {
        return MOBILE_NO_VALUE;
    }

    public void setMOBILE_NO_VALUE(String MOBILE_NO_VALUE) {
        this.MOBILE_NO_VALUE = MOBILE_NO_VALUE;
    }

    public String getEMAIL_VALUE() {
        return EMAIL_VALUE;
    }

    public void setEMAIL_VALUE(String EMAIL_VALUE) {
        this.EMAIL_VALUE = EMAIL_VALUE;
    }

    public String getPASSWORD_VALUE() {
        return PASSWORD_VALUE;
    }

    public void setPASSWORD_VALUE(String PASSWORD_VALUE) {
        this.PASSWORD_VALUE = PASSWORD_VALUE;
    }

    public String getREGISTRATION_TIME_VALUE() {
        return REGISTRATION_TIME_VALUE;
    }

    public void setREGISTRATION_TIME_VALUE(String REGISTRATION_TIME_VALUE) {
        this.REGISTRATION_TIME_VALUE = REGISTRATION_TIME_VALUE;
    }

    public String getLANGUTUDE_VALUE() {
        return LANGUTUDE_VALUE;
    }

    public void setLANGUTUDE_VALUE(String LANGUTUDE_VALUE) {
        this.LANGUTUDE_VALUE = LANGUTUDE_VALUE;
    }

    public String getLATITUDE_VALUE() {
        return LATITUDE_VALUE;
    }

    public void setLATITUDE_VALUE(String LATITUDE_VALUE) {
        this.LATITUDE_VALUE = LATITUDE_VALUE;
    }

    public int getUSER_REGISTER_STATUS() {
        return USER_REGISTER_STATUS;
    }

    public void setUSER_REGISTER_STATUS(int USER_REGISTER_STATUS) {
        this.USER_REGISTER_STATUS = USER_REGISTER_STATUS;
    }

    public int getUSER_LOGIN_STATUS() {
        return USER_LOGIN_STATUS;
    }

    public void setUSER_LOGIN_STATUS(int USER_LOGIN_STATUS) {
        this.USER_LOGIN_STATUS = USER_LOGIN_STATUS;
    }
}
