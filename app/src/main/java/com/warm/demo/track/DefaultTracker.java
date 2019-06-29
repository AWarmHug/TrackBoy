package com.warm.demo.track;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import com.warm.demo.MyApp;
import com.warm.track.Tracker;

public class DefaultTracker implements Tracker<Trace> {
    private MyApp mMyApp;

    public DefaultTracker(MyApp myApp) {
        mMyApp = myApp;
    }

    @Override
    public void track(@Nullable Trace trace) {

        if (trace != null) {
            Log.d("Track", "track: eventId=" + trace.getId() + ",action=" + trace.getValue());
        }

    }

    @Override
    public void track(@Nullable Trace trace, boolean checked) {

        if (trace != null) {
            if (!TextUtils.isEmpty(trace.getValue()) && trace.getValue().contains(Trace.or)) {
                String[] checkValue = trace.getValue().split(Trace.or);
                track(new Trace(trace.getId(), checked ? checkValue[0] : checkValue[1]));
            } else {
                track(trace);
            }
        }

    }
}
