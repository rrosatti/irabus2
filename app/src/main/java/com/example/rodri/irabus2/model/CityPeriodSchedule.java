package com.example.rodri.irabus2.model;

/**
 * Created by rrosatti on 9/9/17.
 */

public class CityPeriodSchedule {

    private int id;
    private int cityPeriodId;
    private long time;

    public CityPeriodSchedule() {}

    public CityPeriodSchedule(int id, int cityPeriodId, long time) {
        this.id = id;
        this.cityPeriodId = cityPeriodId;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityPeriodId() {
        return cityPeriodId;
    }

    public void setCityPeriodId(int cityPeriodId) {
        this.cityPeriodId = cityPeriodId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
