package com.warm.demo;

import android.util.Log;

import com.warm.track.Tracker;

public class DefaultTracker implements Tracker {
    private MyApp mMyApp;

    public DefaultTracker(MyApp myApp) {
        mMyApp = myApp;
    }

    @Override
    public void track(String eventId, String value) {

//        Toast.makeText(mMyApp, "track: eventId=" + eventId + ",action=" + value, Toast.LENGTH_SHORT).show();
        Log.d("Track", "track: eventId=" + eventId + ",action=" + value);

    }
}
