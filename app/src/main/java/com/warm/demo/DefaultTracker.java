package com.warm.demo;

import android.util.Log;

import com.warm.trackboy.Tracker;

public class DefaultTracker implements Tracker {
    @Override
    public void track(String eventId, String value) {

        Log.d("Trace", "track: eventId=" + eventId + ",action=" + value);

    }
}
