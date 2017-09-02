package com.example.administrator.taxicab.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.taxicab.Database.UserDatabase.UserTable;

/**
 * Created by shree on 27/08/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    Context context;
    // Database Name
    public static final String DATABASE_NAME = "TaxiCab";
    private static DatabaseHandler sInstance;
    // Contacts table name

    public static synchronized DatabaseHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }


    private DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(UserTable.CREATE_USER_REGISTRATION_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " +UserTable.TABLE_USER_REGISTRATION);
        // Create tables again
        onCreate(db);
    }

}