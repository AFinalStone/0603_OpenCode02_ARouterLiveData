package com.example.livedata.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.livedata.R;
import com.example.manager.LiveDataManager;
import com.example.manager.MutableLiveData2;

public class LiveDataManagerActivity extends AppCompatActivity {
    private String TAG = "MainActivity======";
    MutableLiveData2<String> mLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_manager);
        mLiveData = LiveDataManager.getInstance().with("LiveDataManagerActivity");
        initView();
    }

    private void initView() {
        mLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "我是观察者A：" + s);
            }
        }, false);
        mLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "我是观察者B：" + s);
            }
        }, true);
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