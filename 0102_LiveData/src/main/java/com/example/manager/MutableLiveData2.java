package com.example.manager;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class MutableLiveData2<T> extends MutableLiveData<T> {
    private String TAG = "MutableLiveData2======";

    public MutableLiveData2(T value) {
        super(value);
    }

    public MutableLiveData2() {
        super();
    }

    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer, boolean stickiness) {
        super.observe(owner, observer);
        if (!stickiness) {
            try {
                hook(observer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void hook(Observer<? super T> observer) throws Exception {
        //拿到当前的mVersion
        Field file_mVersion = LiveData.class.getDeclaredField("mVersion");
        file_mVersion.setAccessible(true);
        Integer mVersion = (Integer) file_mVersion.get(this);
        Log.d(TAG, "mVersion：" + mVersion);
        //拿到当前事件的lastVersion
        Field field_mObservers = LiveData.class.getDeclaredField("mObservers");
        field_mObservers.setAccessible(true);
        Object mObservers = field_mObservers.get(this);
        Method method_get = mObservers.getClass().getDeclaredMethod("get", Object.class);
        method_get.setAccessible(true);
        Object invokeEntry = method_get.invoke(mObservers, observer);
        Object objectObserverWrapper = null;
        if (invokeEntry instanceof Map.Entry) {
            objectObserverWrapper = ((Map.Entry<?, ?>) invokeEntry).getValue();
        }
        Class clazzObserverWrapper = objectObserverWrapper.getClass().getSuperclass();
        Field field_mLastVersion = clazzObserverWrapper.getDeclaredField("mLastVersion");
        field_mLastVersion.setAccessible(true);
        field_mLastVersion.set(objectObserverWrapper, mVersion);
        Log.d(TAG, "mLastVersion：" + mVersion);
    }
}