package com.example.livedata.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.livedata.R;

public class MutableLiveData2Activity extends AppCompatActivity {
    private String TAG = "MainActivity======";
    MutableLiveData<String> mLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLiveData = new MutableLiveData<>();

        SharedPreferences sp = getSharedPreferences("fileName", Context.MODE_PRIVATE);
        sp.edit().putBoolean("key", true).commit();
    }

    private void test() {
        mLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "我是观察者A：" + s);
            }
        });
        mLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "我是观察者B：" + s);
            }
        });
        findViewById(R.id.btn_send_post_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiveData.postValue("测试PostValue");
            }
        });
        findViewById(R.id.btn_send_set_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiveData.setValue("测试SetValue");
            }
        });
        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {

            }
        });
    }



}