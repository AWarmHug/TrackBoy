package com.warm.library_aspectj.proxy;

import android.os.Trace;
import android.util.Log;
import android.view.View;

import com.warm.track.Track;
import com.warm.track.utils.Utils;

public class OnClickListenerProxy implements View.OnClickListener {
    View.OnClickListener l;

    public OnClickListenerProxy(View.OnClickListener l) {
        this.l = l;
    }

    @Override
    public void onClick(View v) {
        l.onClick(v);
        Track.getTrack().getViewTracker().performClick(v);
    }


}
