package com.warm.demo.track;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.warm.track.ViewTracker;
import com.warm.track.ViewFinder;

/**
 * 作者：warm
 * 时间：2019-06-30 13:42
 * 描述：
 */
public class DefaultViewTracker extends ViewTracker<Trace> {
    private ViewFinder<Trace> mViewFinder;

    public DefaultViewTracker(ViewFinder<Trace> viewFinder) {
        mViewFinder = viewFinder;
    }

    @Override
    public Trace find(View view) {
        return mViewFinder.find(view);
    }

    @Override
    public void track(Trace trace) {

        if (trace != null) {
            Log.d("Track", "track: eventId=" + trace.getId() + ",action=" + trace.getValue());
        }

    }

    @Override
    public void track(Trace trace, boolean checked) {

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
