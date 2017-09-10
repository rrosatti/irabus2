package com.example.rodri.irabus2.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.rodri.irabus2.model.City;
import com.example.rodri.irabus2.model.CityPeriod;
import com.example.rodri.irabus2.model.CityPeriodSchedule;
import com.example.rodri.irabus2.model.Period;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rrosatti on 9/9/17.
 */

public class MyDataSource {

    private SQLiteDatabase db;
    private MySQLiteHelper helper;
    private String[] cityColumns = {
            helper.KEY_ID,
            helper.KEY_NAME
    };
    private String[] periodColumns = {
            helper.KEY_ID,
            helper.KEY_NAME
    };
    private String[] cityPeriodColumns = {
            helper.KEY_ID,
            helper.COLUMN_CITY_ID,
            helper.COLUMN_PERIOD_ID
    };
    private String[] cityPeriodScheduleColumns = {
            helper.KEY_ID,
            helper.COLUMN_CITY_PERIOD_ID,
            helper.COLUMN_TIME
    };

    public MyDataSource(Context context) {
        helper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        db = helper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    /** --------- CREATE --------- */

    /** --------- CURSOR TO --------- */
    public City cursorToCity(Cursor cursor) {
        City city = new City();
        city.setId(cursor.getInt(0));
        city.setName(cursor.getString(1));
        return city;
    }

    public Period cursorToPeriod(Cursor cursor) {
        Period period = new Period();
        period.setId(cursor.getInt(0));
        period.setName(cursor.getString(1));
        return period;
    }

    public CityPeriod cursorToCityPeriod(Cursor cursor) {
        CityPeriod cityPeriod = new CityPeriod();
        cityPeriod.setId(cursor.getInt(0));
        cityPeriod.setCityId(cursor.getInt(1));
        cityPeriod.setPeriodId(cursor.getInt(2));
        return cityPeriod;
    }

    public CityPeriodSchedule cursorToCityPeriodSchedule(Cursor cursor) {
        CityPeriodSchedule cityPeriodSchedule = new CityPeriodSchedule();
        cityPeriodSchedule.setId(cursor.getInt(0));
        cityPeriodSchedule.setCityPeriodId(cursor.getInt(1));
        cityPeriodSchedule.setTime(cursor.getLong(2));
        return cityPeriodSchedule;
    }

    /** --------- GET DATA --------- */

    public List<City> getCities() {
        List<City> cities = new ArrayList<>();

        Cursor cursor = db.query(helper.TABLE_CITY, cityColumns,
                null, null, null, null, null, null);

        if (isCursorEmpty(cursor)) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            cities.add(cursorToCity(cursor));
            cursor.moveToNext();
        }
        cursor.close();

        return cities;
    }

    public List<Period> getPeriods() {
        List<Period> periods = new ArrayList<>();
        Cursor cursor = db.query(helper.TABLE_PERIOD, periodColumns,
                null, null, null, null, null, null);

        if (isCursorEmpty(cursor)) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            periods.add(cursorToPeriod(cursor));
            cursor.moveToNext();
        }
        cursor.close();

        return periods;
    }

    public CityPeriod getCityPeriod(int cityId, int periodId) {
        Cursor cursor = db.query(helper.TABLE_CITY_PERIOD, cityPeriodColumns,
                helper.COLUMN_CITY_ID + " = " + cityId + " AND " +
                helper.COLUMN_PERIOD_ID + " = " + periodId,
                null, null, null, null, null);

        if (isCursorEmpty(cursor)) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();

        CityPeriod cityPeriod = cursorToCityPeriod(cursor);
        cursor.close();
        return cityPeriod;
    }

    public List<CityPeriodSchedule> getCityPeriodSchedules(int cityId, int periodId) {
        System.out.println("cityId = " + cityId + " periodId = " + periodId);
        CityPeriod cityPeriod = getCityPeriod(cityId, periodId);
        List<CityPeriodSchedule> cityPeriodSchedules = new ArrayList<>();
        Cursor cursor = db.query(helper.TABLE_CITY_PERIOD_SCHEDULE, cityPeriodScheduleColumns,
                helper.COLUMN_CITY_PERIOD_ID + " = " + cityPeriod.getId(),
                null, null, null, null, null);

        if (isCursorEmpty(cursor)) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            cityPeriodSchedules.add(cursorToCityPeriodSchedule(cursor));
            cursor.moveToNext();
        }
        cursor.close();

        return cityPeriodSchedules;
    }

    /** --------- DELETE --------- */

    /** --------- UPDATE --------- */

    /** --------- LIST --------- */

    /** --------- UTILS --------- */

    private boolean isCursorEmpty (Cursor cursor) {
        if (cursor.moveToFirst())
            return false;
        else
            System.out.println("Cursor is empty!");
            return true;
    }
}
