package com.example.livedata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.livedata.activity.LiveDataManagerActivity;
import com.example.livedata.activity.MutableLiveData2Activity;
import com.example.livedata.activity.MutableLiveDataActivity;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity======";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test_mutable_live_data).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MutableLiveDataActivity.class));
        });
        findViewById(R.id.test_mutable_live_data2).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MutableLiveData2Activity.class)));
        findViewById(R.id.test_live_data_manager).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LiveDataManagerActivity.class)));

    }

}