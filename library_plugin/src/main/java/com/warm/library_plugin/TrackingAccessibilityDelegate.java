package com.warm.library_plugin;

import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

import com.warm.track.Data;
import com.warm.track.Trace;
import com.warm.track.Track;
import com.warm.track.utils.Utils;

public class TrackingAccessibilityDelegate extends View.AccessibilityDelegate {

    @Override
    public void sendAccessibilityEvent(View host, int eventType) {
        super.sendAccessibilityEvent(host, eventType);
        if (eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            Trace trace = Data.getEvent(getName(host));
            if (trace != null) {
                Track.getTracker().track(trace.getId(), trace.getValue());
            }
        }
    }

    private String getName(View view) {

        StringBuilder sb = new StringBuilder();

        sb.append(Track.getViewName(view))
//                .append("$")
//                .append(getClassName(l.getClass()))
                .append("$")
                .append(Track.getClassName(view.getContext().getClass()));

        String md5 = Utils.toMD5(sb.toString());

//        if (BuildConfig.DEBUG) {
        Log.d("Track", "getName: " + sb.toString() + ",MD5: " + md5);
//        }

        return md5;
    }

}
