package com.warm.someaop;

import android.app.Application;

public class Track {
    private static Tracker mTracker;
    public static void init(Tracker tracker) {
        mTracker=tracker;
    }

    public static Tracker getTracker(){
        if (mTracker==null){
            throw new RuntimeException("请先init");
        }
        return mTracker;
    }

}
