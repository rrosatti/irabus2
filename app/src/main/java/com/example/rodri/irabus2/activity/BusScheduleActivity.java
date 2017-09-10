package com.example.rodri.irabus2.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.rodri.irabus2.R;
import com.example.rodri.irabus2.adapter.CityPeriodScheduleAdapter;
import com.example.rodri.irabus2.database.MyDataSource;
import com.example.rodri.irabus2.helpers.Utils;
import com.example.rodri.irabus2.model.City;
import com.example.rodri.irabus2.model.CityPeriodSchedule;
import com.example.rodri.irabus2.model.Period;

import java.util.ArrayList;
import java.util.List;

public class BusScheduleActivity extends AppCompatActivity {

    private RadioGroup rgCities;
    private Spinner spinnerPeriods;
    private GridView listBusSchedule;
    private FloatingActionButton btUpdate;
    private MyDataSource dataSource;
    private List<City> cities = new ArrayList<>();
    private List<Period> periods = new ArrayList<>();
    private List<CityPeriodSchedule> cityPeriodSchedules = new ArrayList<>();
    private int selectedCity = 0;
    private int selectedPeriod = 0;
    private CityPeriodScheduleAdapter cityPeriodScheduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedule);

        iniViews();
        dataSource = new MyDataSource(this);
        dataSource.open();

        fillRadioGroup();
        selectedCity = rgCities.getCheckedRadioButtonId();
        fillSpinner();
        fillListView();

        spinnerPeriods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPeriod = i;
                fillListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rgCities.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                selectedCity = i;
                fillListView();
            }
        });
    }

    private void iniViews() {
        rgCities = (RadioGroup) findViewById(R.id.rgCities);
        spinnerPeriods = (Spinner) findViewById(R.id.spinnerPeriods);
        listBusSchedule = (GridView) findViewById(R.id.listBusSchedule);
        btUpdate = (FloatingActionButton) findViewById(R.id.btUpdate);
    }

    private void fillRadioGroup() {
        cities = dataSource.getCities();

        for (City city : cities) {
            RadioButton rb = new RadioButton(BusScheduleActivity.this);
            rb.setText(city.getName());
            rb.setTextSize(20);
            rb.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            rgCities.addView(rb);
        }
        ((RadioButton)rgCities.getChildAt(0)).setChecked(true);
    }

    private void fillSpinner() {
        periods = dataSource.getPeriods();
        List<String> periodNames = new ArrayList<>();

        for (Period period : periods) {
            periodNames.add(period.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                BusScheduleActivity.this, android.R.layout.simple_spinner_dropdown_item, periodNames);

        spinnerPeriods.setAdapter(adapter);
    }

    private void fillListView() {
        cityPeriodSchedules = dataSource.getCityPeriodSchedules(selectedCity, selectedPeriod+1);
        cityPeriodScheduleAdapter = new CityPeriodScheduleAdapter(BusScheduleActivity.this, cityPeriodSchedules);
        listBusSchedule.setAdapter(cityPeriodScheduleAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        dataSource.close();
        System.out.println("onStop() was called!");
    }
}
