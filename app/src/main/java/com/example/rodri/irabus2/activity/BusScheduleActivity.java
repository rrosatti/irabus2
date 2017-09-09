package com.example.rodri.irabus2.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.rodri.irabus2.R;

public class BusScheduleActivity extends AppCompatActivity {

    private RadioGroup rgCities;
    private Spinner spinnerPeriods;
    private GridView listBusSchedule;
    private FloatingActionButton btUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedule);

        iniViews();
    }

    private void iniViews() {
        rgCities = (RadioGroup) findViewById(R.id.rgCities);
        spinnerPeriods = (Spinner) findViewById(R.id.spinnerPeriods);
        listBusSchedule = (GridView) findViewById(R.id.listBusSchedule);
        btUpdate = (FloatingActionButton) findViewById(R.id.btUpdate);
    }

}
