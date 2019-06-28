package com.warm.library_plugin.helper;

import android.view.View;

import com.warm.library_plugin.TrackingAccessibilityDelegate;

/**
 * 作者：warm
 * 时间：2019-06-28 19:53
 * 描述：
 */
public class AccessibilityDelegateHelper {

    public static void onViewAdded(View child) {
        if (child.getClass().getName().contains("android.widget") || child.getClass().getName().contains("android.view")) {
            child.setAccessibilityDelegate(new TrackingAccessibilityDelegate());
        }
    }

}
