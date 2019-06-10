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
        appendName(sb,view);
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view.getParent();
            while (parent != null && parent.getId() != android.R.id.content) {
                appendName(sb,view);
                if (parent instanceof ViewGroup) {
                    parent = (ViewGroup) parent.getParent();
                } else {
                    break;
                }
            }
        }
        return sb.toString();
    }


    private void appendName(StringBuilder sb,View view){
        if (view.getId() != View.NO_ID) {
            sb.append("$");
            sb.append(view.getClass().getSimpleName())
                    .append(":")
                    .append(view.getResources().getResourceEntryName(view.getId()));
        } else {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            sb.append("$");
            sb.append(view.getClass().getSimpleName())
                    .append(":")
                    .append(viewGroup.indexOfChild(view));
        }
    }

    protected void track(String id, String value) {

        Track.getTracker().track(id, value);
    }

}
