package com.warm.library_aspectj.proxy;

import android.util.Log;
import android.view.View;

import com.warm.track.Data;
import com.warm.track.Trace;
import com.warm.track.utils.Utils;

public class OnClickListenerProxy extends BaseProxy implements View.OnClickListener {
    View.OnClickListener l;

    public OnClickListenerProxy(View.OnClickListener l) {
        this.l = l;
    }

    @Override
    public void onClick(View v) {
        l.onClick(v);
        Trace trace = Data.getEvent(getName(l, v));
        if (trace != null) {
            track(trace.getId(), trace.getValue());
        }
    }

    private String getName(View.OnClickListener l, View view) {

        StringBuilder sb = new StringBuilder();

        sb.append(getViewName(view))
//                .append("$")
//                .append(getClassName(l.getClass()))
                .append("$")
                .append(getClassName(view.getContext().getClass()));

        String md5 = Utils.toMD5(sb.toString());

//        if (BuildConfig.DEBUG) {
            Log.d(TAG, "getName: " + sb.toString() + ",MD5: " + md5);
//        }

        return md5;
    }

}
