package com.warm.demo;

import android.util.Log;

import com.warm.demo.data.Trace;
import com.warm.track.Tracker;

public class DefaultTracker implements Tracker<Trace> {
    private MyApp mMyApp;

    public DefaultTracker(MyApp myApp) {
        mMyApp = myApp;
    }

    @Override
    public void track(Trace trace) {

        if (trace==null){
            return;
        }
//        Toast.makeText(mMyApp, "track: eventId=" + eventId + ",action=" + value, Toast.LENGTH_SHORT).show();
        Log.d("Track", "track: eventId=" + trace.getId() + ",action=" + trace.getValue());

//        if (!TextUtils.isEmpty(trace.getValue()) && trace.getValue().contains(Trace.or)) {
//            String[] checkValue = trace.getValue().split(Trace.or);
//            Track.getTracker().track(trace.getId(), checked ? checkValue[0] : checkValue[1]);
//        } else {
//            Track.getTracker().track(trace.getId(), trace.getValue());
//        }

    }
}
