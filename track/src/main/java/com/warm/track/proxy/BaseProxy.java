package com.warm.track.proxy;

import android.view.View;

import com.warm.track.Track;

public class BaseProxy {
    protected static final String TAG = "Track----";


    protected String getClassName(Class<?> clazz) {
        return Track.getClassName(clazz);
    }

    protected String getViewName(View view) {
        return Track.getViewName(view);
    }


    protected void track(String id, String value) {

        Track.getTracker().track(id, value);
    }
}
