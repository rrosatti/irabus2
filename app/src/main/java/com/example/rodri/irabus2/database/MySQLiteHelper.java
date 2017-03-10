package com.example.rodri.irabus2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by rodri on 3/10/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database name
    private static final String DATABASE_NAME = "irabus.db";

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_CITY = "city";
    public static final String TABLE_PERIOD = "period";
    public static final String TABLE_CITY_PERIOD = "city_period";
    public static final String TABLE_CITY_PERIOD_SCHEDULE = "city_period_schedule";

    // Common column names:
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";

    // City: column names { id, name }

    // Period: column names { id, name }

    // CityPeriod: column names { id, ... }
    public static final String COLUMN_CITY_ID = "city_id";
    public static final String COLUMN_PERIOD_ID = "period_id";

    // CityPeriodSchedule { id, ... }
    public static final String COLUMN_CITY_PERIOD_ID = "city_period_id";
    public static final String COLUMN_TIME = "time";


    /** -------  CREATE TABLES ------- **/

    public static final String CREATE_TABLE_CITY =
            "CREATE TABLE " + TABLE_CITY + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT NOT NULL);";

    public static final String CREATE_TABLE_PERIOD =
            "CREATE TABLE " + TABLE_PERIOD + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT NOT NULL);";

    public static final String CREATE_TABLE_CITY_PERIOD =
            "CREATE TABLE " + TABLE_CITY_PERIOD + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CITY_ID + " INTEGER NOT NULL, "
            + COLUMN_PERIOD_ID + " INTEGER NOT NULL, "
            + "FOREIGN KEY (" + COLUMN_CITY_ID + ") REFERENCES " + TABLE_CITY + " (" + KEY_ID + "), "
            + "FOREIGN KEY (" + COLUMN_PERIOD_ID + ") REFERENCES " + TABLE_PERIOD + " (" + KEY_ID + "));";

    public static final String CREATE_TABLE_CITY_PERIOD_SCHEDULE =
            "CREATE TABLE " + TABLE_CITY_PERIOD_SCHEDULE + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CITY_PERIOD_ID + " INTEGER NOT NULL, "
            + COLUMN_TIME + " INTEGER NOT NULL, "
            + "FOREIGN KEY (" + COLUMN_CITY_PERIOD_ID + ") REFERENCES " + TABLE_CITY_PERIOD + " (" + KEY_ID + "));";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CITY);
        db.execSQL(CREATE_TABLE_PERIOD);
        db.execSQL(CREATE_TABLE_CITY_PERIOD);
        db.execSQL(CREATE_TABLE_CITY_PERIOD_SCHEDULE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), "Upgrading from version " + oldVersion + " to " + newVersion
                + ". The old date will be deleted.");

        if (newVersion > oldVersion) {
            // Do something
        }
    }
}
