package com.warm.library_plugin.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.warm.track.Data;
import com.warm.track.Trace;
import com.warm.track.Track;
import com.warm.track.utils.Utils;

public class TCheckBox extends CheckBox {
    public TCheckBox(Context context) {
        super(context);
    }

    public TCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        Trace trace = Data.getEvent(getName(this));

        if (trace != null) {
            if (!TextUtils.isEmpty(trace.getValue()) && trace.getValue().contains(Trace.or)) {
                String[] checkValue = trace.getValue().split(Trace.or);
                Track.getTracker().track(trace.getId(), checked ? checkValue[0] : checkValue[1]);
            } else {
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
