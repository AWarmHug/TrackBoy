package com.warm.trackboy.core;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.warm.trackboy.Data;
import com.warm.trackboy.Trace;
import com.warm.trackboy.Track;

public class BaseCore {
    protected static final String TAG = "Track----";


    protected String getClassName(Class<?> clazz) {

        if (clazz.getEnclosingClass() != null) {
            return getClassName(clazz.getEnclosingClass());
        } else {
            return clazz.getSimpleName();
        }
    }

    protected String getViewName(View view) {
        StringBuilder sb = new StringBuilder();
        sb.append(view.getClass().getSimpleName())
                .append(":")
                .append(view.getResources().getResourceEntryName(view.getId()));
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view.getParent();

            while (parent != null && parent.getId() != android.R.id.content) {
                if (parent.getId() != View.NO_ID) {
                    sb.append("$");
                    sb.append(parent.getClass().getSimpleName())
                            .append(":")
                            .append(parent.getResources().getResourceEntryName(parent.getId()));
                } else {
                    sb.append("$");
                    sb.append(parent.getClass().getSimpleName())
                            .append(":")
                            .append(View.NO_ID);
                }
                if (parent instanceof ViewGroup) {
                    parent = (ViewGroup) parent.getParent();
                } else {
                    break;
                }
            }
        }
        return sb.toString();


    }


    protected void track(String id, String value) {

        Track.getTracker().track(id, value);
    }

}
