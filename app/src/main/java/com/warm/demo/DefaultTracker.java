package com.warm.demo;

import android.util.Log;

import com.warm.someaop.Tracker;

public class DefaultTracker implements Tracker {
    @Override
    public void track(String eventId, String[] events) {
        StringBuilder sb = new StringBuilder();
        for (String str : events) {
            sb.append(str);
        }
        Log.d("Event", "track: eventId=" + eventId + ",action=" + sb.toString());

    }
}
