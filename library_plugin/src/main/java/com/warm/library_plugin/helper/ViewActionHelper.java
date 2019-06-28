package com.warm.library_plugin.helper;

import android.view.View;

import com.warm.track.Track;
import com.warm.track.ViewFinder;

/**
 * 作者：warm
 * 时间：2019-06-28 20:04
 * 描述：
 */
public class ViewActionHelper {

    public static void performClick(View view) {

        String name = ViewFinder.getName(view);
        Object o = Track.getD().find(name);
        Track.getTracker().track(o);

    }


    public static void setChecked(View view, boolean checked) {
        String name = ViewFinder.getName(view);

        Object o = Track.getD().find(name);

        Track.getTracker().track(o);

    }


}
