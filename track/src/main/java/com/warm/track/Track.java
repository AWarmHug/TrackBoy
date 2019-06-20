package com.warm.track;

import android.view.View;
import android.view.ViewGroup;

public class Track {


    private static Tracker mTracker;

    public static void init(Tracker tracker) {
        mTracker = tracker;
    }

    public static Tracker getTracker() {
        if (mTracker == null) {
            throw new RuntimeException("请先init");
        }
        return mTracker;
    }


    public static String getClassName(Class<?> clazz) {

        if (clazz.getEnclosingClass() != null) {
            return getClassName(clazz.getEnclosingClass());
        } else {
            return clazz.getSimpleName();
        }
    }

    public static String getViewName(View view) {
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


    private static void appendName(StringBuilder sb,View view){
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


}