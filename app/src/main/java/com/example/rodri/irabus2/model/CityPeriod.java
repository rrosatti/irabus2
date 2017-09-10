package com.example.rodri.irabus2.model;

/**
 * Created by rrosatti on 9/9/17.
 */

public class CityPeriod {

    private int id;
    private int cityId;
    private int periodId;

    public CityPeriod() {}

    public CityPeriod(int id, int cityId, int periodId) {
        this.id = id;
        this.cityId = cityId;
        this.periodId = periodId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }
}
