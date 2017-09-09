package com.example.rodri.irabus2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.rodri.irabus2.R;
import com.example.rodri.irabus2.helpers.Utils;

/**
 * Created by rodri on 3/10/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    private Context context;
    private SQLiteDatabase db;

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
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_TABLE_CITY);
        db.execSQL(CREATE_TABLE_PERIOD);
        db.execSQL(CREATE_TABLE_CITY_PERIOD);
        db.execSQL(CREATE_TABLE_CITY_PERIOD_SCHEDULE);
        fillDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), "Upgrading from version " + oldVersion + " to " + newVersion
                + ". The old date will be deleted.");

        if (newVersion > oldVersion) {
            // Do something
        }
    }

    private void fillDatabase() {
        String[] iracemapolisLimeiraMondayToFriday = context.getResources()
                .getStringArray(R.array.iracemapolis_limeira_monday_to_friday);
        String[] iracemapolisLimeiraSaturday = context.getResources()
                .getStringArray(R.array.iracemapolis_limeira_saturday);
        String[] iracemapolisLimeiraSundayAndHolidays = context.getResources()
                .getStringArray(R.array.iracemapolis_limeira_sunday_and_holidays);
        String[] limeiraIracemapolisMondayToFriday = context.getResources()
                .getStringArray(R.array.limeira_iracemapolis_monday_to_saturday);
        String[] limeiraIracemapolisSaturday = context.getResources()
                .getStringArray(R.array.limeira_iracemapolis_saturday);
        String[] limeiraIracemapolisSundayAndHolidays = context.getResources()
                .getStringArray(R.array.limeira_iracemapolis_sunday_and_holidays);

        String FILL_CITIES = "INSERT INTO " + TABLE_CITY + " (" + KEY_NAME + ") VALUES ('Iracemapolis'), ('Limeira');";
        String FILL_PERIODS = "INSERT INTO " + TABLE_PERIOD + " (" + KEY_NAME + ")" +
                " VALUES ('Monday to Friday'), ('Saturday'), ('Sunday and Holidays');";
        String FILL_CITY_PERIODS = "INSERT INTO " + TABLE_CITY_PERIOD + " (" + COLUMN_CITY_ID + "" +
                ", " + COLUMN_PERIOD_ID + ") VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (2, 3);";

        db.execSQL(FILL_CITIES);
        db.execSQL(FILL_PERIODS);
        db.execSQL(FILL_CITY_PERIODS);

        fillCityPeriodSchedule(iracemapolisLimeiraMondayToFriday, 1);
        fillCityPeriodSchedule(iracemapolisLimeiraSaturday, 2);
        fillCityPeriodSchedule(iracemapolisLimeiraSundayAndHolidays, 3);
        fillCityPeriodSchedule(limeiraIracemapolisMondayToFriday, 4);
        fillCityPeriodSchedule(limeiraIracemapolisSaturday, 5);
        fillCityPeriodSchedule(limeiraIracemapolisSundayAndHolidays, 6);

    }

    private void fillCityPeriodSchedule(String[] schedule, int scheduleId) {
        int n = schedule.length;
        Utils utils = new Utils();

        for (int i = 0; i<n; i++) {
            String FILL_CITY_PERIOD_SCHEDULE = "INSERT INTO " + TABLE_CITY_PERIOD_SCHEDULE +
                    " (" + COLUMN_CITY_PERIOD_ID + ", " + COLUMN_TIME + ") VALUES" +
                    " (" + scheduleId + ", " + utils.convertFromTimeToMilliseconds(schedule[i]) + ");";
            db.execSQL(FILL_CITY_PERIOD_SCHEDULE);
        }
    }
}
