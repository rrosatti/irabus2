package com.example.rodri.irabus2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rodri.irabus2.MainActivity;
import com.example.rodri.irabus2.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent inStart = new Intent(this, MainActivity.class);
        startActivity(inStart);
        finish();
    }
}
