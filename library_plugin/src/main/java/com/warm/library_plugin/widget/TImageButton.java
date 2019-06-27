package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.warm.track.Data;
import com.warm.track.Trace;
import com.warm.track.Track;
import com.warm.track.utils.Utils;

public class TImageButton extends ImageButton {
    public TImageButton(Context context) {
        super(context);
    }

    public TImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();

        if (click) {
            Trace trace = Data.getEvent(getName(this));
            if (trace != null) {
                Track.getTracker().track(trace.getId(), trace.getValue());
            }
        }
        return click;

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
