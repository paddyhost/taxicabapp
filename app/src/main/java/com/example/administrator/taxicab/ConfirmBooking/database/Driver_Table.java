package com.example.administrator.taxicab.ConfirmBooking.database;

/**
 * Created by shree on 03/09/2017.
 */

public class Driver_Table
{
    public static String DRIVER_TABLE="Driver_Table";

    public static String CL_DRIVER_ID="Driver_Id",CL_DRIVER_NAME="driver_name",CL_VEHICLE_ID="Vehicle_Id",
            CL_VEHICLE_NAME="vehicle_name",CL_CAB_TYPE="cab_type", CL_CAB_NO="cab_no", IS_CAB_AC="is_cab_Ac",
            CL_SEATER="seater",CL_DRIVER_MOBILE="driver_mobile",CL_LATITUDE="driver_latitude",
            CL_LONGITUDE="driver_longitude";

    public static final String CREATE_DRIVER_TABLE = "CREATE TABLE " + DRIVER_TABLE +
            "("+CL_DRIVER_ID+" TEXT , "+CL_DRIVER_NAME+" TEXT, "+CL_VEHICLE_ID+" TEXT, " +CL_VEHICLE_NAME+" TEXT, "
            +CL_CAB_TYPE+" TEXT, "+CL_CAB_NO+" TEXT, "+CL_LONGITUDE+" TEXT, "+CL_LATITUDE+" TEXT, "
            +IS_CAB_AC+" TEXT, " +CL_SEATER+" TEXT, " +CL_DRIVER_MOBILE+" TEXT)";

    private String DRIVER_ID_VALUE, DRIVER_NAME_VALUE, VEHICLE_ID_VALUE, VEHICLE_NAME_VALUE, CAB_TYPE_VALUE,
            CAB_NO_VALUE, IS_CAB_AC_VALUE, SEATER_VALUE, DRIVER_MOBILE_VALUE,LATITUDE_VALUE,LONGITUDE_VALUE;

    public Driver_Table(String DRIVER_ID_VALUE,String DRIVER_NAME_VALUE,String VEHICLE_ID_VALUE, String VEHICLE_NAME_VALUE,
                        String CAB_TYPE_VALUE,String CAB_NO_VALUE,String LATITUDE_VALUE,String LONGITUDE_VALUE,
                        String IS_CAB_AC_VALUE,String SEATER_VALUE,String DRIVER_MOBILE_VALUE)
    {
        this.DRIVER_ID_VALUE=DRIVER_ID_VALUE;
        this.DRIVER_NAME_VALUE=DRIVER_NAME_VALUE;
        this.VEHICLE_ID_VALUE=VEHICLE_ID_VALUE;
        this.VEHICLE_NAME_VALUE=VEHICLE_NAME_VALUE;
        this.CAB_TYPE_VALUE=CAB_TYPE_VALUE;
        this.CAB_NO_VALUE=CAB_NO_VALUE;
        this.IS_CAB_AC_VALUE=IS_CAB_AC_VALUE;
        this.SEATER_VALUE=SEATER_VALUE;
        this.DRIVER_MOBILE_VALUE=DRIVER_MOBILE_VALUE;
        this.LATITUDE_VALUE=LATITUDE_VALUE;
        this.LONGITUDE_VALUE=LONGITUDE_VALUE;
    }

    public Driver_Table()
    {

    }

    public String getDRIVER_ID_VALUE() {
        return DRIVER_ID_VALUE;
    }

    public void setDRIVER_ID_VALUE(String DRIVER_ID_VALUE) {
        this.DRIVER_ID_VALUE = DRIVER_ID_VALUE;
    }

    public String getDRIVER_NAME_VALUE() {
        return DRIVER_NAME_VALUE;
    }

    public void setDRIVER_NAME_VALUE(String DRIVER_NAME_VALUE) {
        this.DRIVER_NAME_VALUE = DRIVER_NAME_VALUE;
    }

    public String getVEHICLE_ID_VALUE() {
        return VEHICLE_ID_VALUE;
    }

    public void setVEHICLE_ID_VALUE(String VEHICLE_ID_VALUE) {
        this.VEHICLE_ID_VALUE = VEHICLE_ID_VALUE;
    }

    public String getVEHICLE_NAME_VALUE() {
        return VEHICLE_NAME_VALUE;
    }

    public void setVEHICLE_NAME_VALUE(String VEHICLE_NAME_VALUE) {
        this.VEHICLE_NAME_VALUE = VEHICLE_NAME_VALUE;
    }

    public String getCAB_TYPE_VALUE() {
        return CAB_TYPE_VALUE;
    }

    public void setCAB_TYPE_VALUE(String CAB_TYPE_VALUE) {
        this.CAB_TYPE_VALUE = CAB_TYPE_VALUE;
    }

    public String getCAB_NO_VALUE() {
        return CAB_NO_VALUE;
    }

    public void setCAB_NO_VALUE(String CAB_NO_VALUE) {
        this.CAB_NO_VALUE = CAB_NO_VALUE;
    }

    public String getIS_CAB_AC_VALUE() {
        return IS_CAB_AC_VALUE;
    }

    public void setIS_CAB_AC_VALUE(String IS_CAB_AC_VALUE) {
        this.IS_CAB_AC_VALUE = IS_CAB_AC_VALUE;
    }

    public String getSEATER_VALUE() {
        return SEATER_VALUE;
    }

    public void setSEATER_VALUE(String SEATER_VALUE) {
        this.SEATER_VALUE = SEATER_VALUE;
    }

    public String getDRIVER_MOBILE_VALUE() {
        return DRIVER_MOBILE_VALUE;
    }

    public void setDRIVER_MOBILE_VALUE(String DRIVER_MOBILE_VALUE) {
        this.DRIVER_MOBILE_VALUE = DRIVER_MOBILE_VALUE;
    }

    public String getLATITUDE_VALUE() {
        return LATITUDE_VALUE;
    }

    public void setLATITUDE_VALUE(String LATITUDE_VALUE) {
        this.LATITUDE_VALUE = LATITUDE_VALUE;
    }

    public String getLONGITUDE_VALUE() {
        return LONGITUDE_VALUE;
    }

    public void setLONGITUDE_VALUE(String LONGITUDE_VALUE) {
        this.LONGITUDE_VALUE = LONGITUDE_VALUE;
    }
}
