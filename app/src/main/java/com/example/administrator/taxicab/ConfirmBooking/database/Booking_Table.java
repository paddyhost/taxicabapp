package com.example.administrator.taxicab.ConfirmBooking.database;

/**
 * Created by shree on 03/09/2017.
 */

public class Booking_Table
{
    public static String BOOKING_TABLE="Booking_Table";

    public static String CL_RIDE_ID="ride_id",CL_SOURCE_LONGITUDE="source_longitude",CL_SOURCE_LATITUDE="source_latitude",
                        CL_DESTINATION_LONGITUDE="destination_longitude",CL_DESTINATION_LATITUDE="destination_latitude",
                        CL_BOOKING_TIME="booking_datetime",CL_DRIVER_ID="driver_id",CL_DISTANCE="distance",
                        CL_AMOUNT="amount",CL_STATUS="booking_status",CL_VEHICLE_ID="vehicle_id",CL_RIDE_TYPE="ride_type",
                        CL_RIDE_DATETIME="ride_datetime";

    public static final String CREATE_BOOKING_TABLE = "CREATE TABLE " + BOOKING_TABLE +
                    "("+CL_RIDE_ID+" TEXT , "+CL_SOURCE_LONGITUDE+" TEXT, "+CL_SOURCE_LATITUDE+" TEXT, "
                    +CL_DESTINATION_LONGITUDE+" TEXT, " +CL_DESTINATION_LATITUDE+" TEXT, "+CL_BOOKING_TIME+" TEXT, "
                    +CL_DRIVER_ID+" TEXT, " +CL_DISTANCE+" TEXT, " +CL_AMOUNT+" TEXT, " +CL_STATUS+" TEXT, "
                     +CL_VEHICLE_ID+" TEXT, " +CL_RIDE_TYPE+" TEXT, "+CL_RIDE_DATETIME+" TEXT)";

    private String RIDE_ID_VALUE, SOURCE_LONGITUDE_VALUE, SOURCE_LATITUDE_VALUE, DESTINATION_LONGITUDE_VALUE,
            DESTINATION_LATITUDE_VALUE, BOOKING_TIME_VALUE, DRIVE_ID_VALUE, DISTANCE_VALUE, AMOUNT_VALUE,
            STATUS_VALUE, VEHICLE_ID_VALUE, RIDE_TYPE_VALUE, RIDE_DATETIME_VALUE;

    public static final int BOOKING_SUCCESS=1,BOOKING_FAILED=0;
    public static final String RIDE_NOW="now",RIDE_LATER="later";
    public static final int STATUS_UPDATE_SUCCESS=2, STATUS_UPDATE_FAILED=3;

    private int BOOK_RIDE_STATUS;
    private int BOOKING_STATUS;

    public Booking_Table()
    {

    }

    public Booking_Table(String RIDE_ID_VALUE,String SOURCE_LONGITUDE_VALUE,String SOURCE_LATITUDE_VALUE,
                         String DESTINATION_LONGITUDE_VALUE, String DESTINATION_LATITUDE_VALUE,
                         String BOOKING_TIME_VALUE,String DRIVE_ID_VALUE,String DISTANCE_VALUE,
                         String AMOUNT_VALUE, String STATUS_VALUE,String VEHICLE_ID_VALUE,
                         String RIDE_TYPE_VALUE, String RIDE_DATETIME_VALUE)
    {
        this.RIDE_ID_VALUE=RIDE_ID_VALUE;
        this.SOURCE_LONGITUDE_VALUE=SOURCE_LONGITUDE_VALUE;
        this.SOURCE_LATITUDE_VALUE=SOURCE_LATITUDE_VALUE;
        this.DESTINATION_LONGITUDE_VALUE=DESTINATION_LONGITUDE_VALUE;
        this.DESTINATION_LATITUDE_VALUE=DESTINATION_LATITUDE_VALUE;
        this.BOOKING_TIME_VALUE=BOOKING_TIME_VALUE;
        this.DRIVE_ID_VALUE=DRIVE_ID_VALUE;
        this.DISTANCE_VALUE=DISTANCE_VALUE;
        this.AMOUNT_VALUE=AMOUNT_VALUE;
        this.STATUS_VALUE=STATUS_VALUE;
        this.VEHICLE_ID_VALUE=VEHICLE_ID_VALUE;
        this.RIDE_TYPE_VALUE=RIDE_TYPE_VALUE;
        this.RIDE_DATETIME_VALUE=RIDE_DATETIME_VALUE;
    }

    public String getRIDE_ID_VALUE() {
        return RIDE_ID_VALUE;
    }

    public void setRIDE_ID_VALUE(String RIDE_ID_VALUE) {
        this.RIDE_ID_VALUE = RIDE_ID_VALUE;
    }

    public String getSOURCE_LONGITUDE_VALUE() {
        return SOURCE_LONGITUDE_VALUE;
    }

    public void setSOURCE_LONGITUDE_VALUE(String SOURCE_LONGITUDE_VALUE) {
        this.SOURCE_LONGITUDE_VALUE = SOURCE_LONGITUDE_VALUE;
    }

    public String getSOURCE_LATITUDE_VALUE() {
        return SOURCE_LATITUDE_VALUE;
    }

    public void setSOURCE_LATITUDE_VALUE(String SOURCE_LATITUDE_VALUE) {
        this.SOURCE_LATITUDE_VALUE = SOURCE_LATITUDE_VALUE;
    }

    public String getDESTINATION_LONGITUDE_VALUE() {
        return DESTINATION_LONGITUDE_VALUE;
    }

    public void setDESTINATION_LONGITUDE_VALUE(String DESTINATION_LONGITUDE_VALUE) {
        this.DESTINATION_LONGITUDE_VALUE = DESTINATION_LONGITUDE_VALUE;
    }

    public String getDESTINATION_LATITUDE_VALUE() {
        return DESTINATION_LATITUDE_VALUE;
    }

    public void setDESTINATION_LATITUDE_VALUE(String DESTINATION_LATITUDE_VALUE) {
        this.DESTINATION_LATITUDE_VALUE = DESTINATION_LATITUDE_VALUE;
    }

    public String getBOOKING_TIME_VALUE() {
        return BOOKING_TIME_VALUE;
    }

    public void setBOOKING_TIME_VALUE(String BOOKING_TIME_VALUE) {
        this.BOOKING_TIME_VALUE = BOOKING_TIME_VALUE;
    }

    public String getDRIVE_ID_VALUE() {
        return DRIVE_ID_VALUE;
    }

    public void setDRIVE_ID_VALUE(String DRIVE_ID_VALUE) {
        this.DRIVE_ID_VALUE = DRIVE_ID_VALUE;
    }

    public String getDISTANCE_VALUE() {
        return DISTANCE_VALUE;
    }

    public void setDISTANCE_VALUE(String DISTANCE_VALUE) {
        this.DISTANCE_VALUE = DISTANCE_VALUE;
    }

    public String getAMOUNT_VALUE() {
        return AMOUNT_VALUE;
    }

    public void setAMOUNT_VALUE(String AMOUNT_VALUE) {
        this.AMOUNT_VALUE = AMOUNT_VALUE;
    }

    public String getSTATUS_VALUE() {
        return STATUS_VALUE;
    }

    public void setSTATUS_VALUE(String STATUS_VALUE) {
        this.STATUS_VALUE = STATUS_VALUE;
    }

    public String getVEHICLE_ID_VALUE() {
        return VEHICLE_ID_VALUE;
    }

    public void setVEHICLE_ID_VALUE(String VEHICLE_ID_VALUE) {
        this.VEHICLE_ID_VALUE = VEHICLE_ID_VALUE;
    }

    public String getRIDE_TYPE_VALUE() {
        return RIDE_TYPE_VALUE;
    }

    public void setRIDE_TYPE_VALUE(String RIDE_TYPE_VALUE) {
        this.RIDE_TYPE_VALUE = RIDE_TYPE_VALUE;
    }

    public String getRIDE_DATETIME_VALUE() {
        return RIDE_DATETIME_VALUE;
    }

    public void setRIDE_DATETIME_VALUE(String RIDE_DATETIME_VALUE) {
        this.RIDE_DATETIME_VALUE = RIDE_DATETIME_VALUE;
    }


    public int getBOOK_RIDE_STATUS() {
        return BOOK_RIDE_STATUS;
    }

    public void setBOOK_RIDE_STATUS(int BOOK_RIDE_STATUS) {
        this.BOOK_RIDE_STATUS = BOOK_RIDE_STATUS;
    }

    public int getBOOKING_STATUS() {
        return BOOKING_STATUS;
    }

    public void setBOOKING_STATUS(int BOOKING_STATUS) {
        this.BOOKING_STATUS = BOOKING_STATUS;
    }
}
