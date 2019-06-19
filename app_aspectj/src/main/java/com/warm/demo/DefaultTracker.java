package com.warm.demo;

import android.util.Log;

import com.warm.track.Tracker;

public class DefaultTracker implements Tracker {
    @Override
    public void track(String eventId, String value) {

        Log.d("Track", "track: eventId=" + eventId + ",action=" + value);

    }
}
