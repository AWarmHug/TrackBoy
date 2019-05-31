package com.warm.demo;

import android.app.Application;

import com.warm.someaop.Track;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Track.init(new DefaultTracker());

    }
}
