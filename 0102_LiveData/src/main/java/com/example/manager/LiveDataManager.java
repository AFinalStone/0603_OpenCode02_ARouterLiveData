package com.example.manager;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

public class LiveDataManager {

    private static class LiveDataManagerHolder {
        private static LiveDataManager INSTANCE = new LiveDataManager();
    }

    public static LiveDataManager getInstance() {
        return LiveDataManagerHolder.INSTANCE;
    }

    private Map<String, MutableLiveData<Object>> mLiveDataMap;

    public LiveDataManager() {
        this.mLiveDataMap = new HashMap<>();
    }

    public <T> MutableLiveData2<T> with(String key) {
        if (!mLiveDataMap.containsKey(key)) {
            mLiveDataMap.put(key, new MutableLiveData2<Object>());
        }
        return (MutableLiveData2<T>) mLiveDataMap.get(key);
    }


}
