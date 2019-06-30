package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Spinner;

import com.warm.library_plugin.helper.AccessibilityDelegateHelper;
import com.warm.track.Track;

public class TSpinner extends Spinner {
    public TSpinner(Context context) {
        super(context);
    }

    public TSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        Track.getTrack().getViewTracker().performClick(this);
        return click;
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        AccessibilityDelegateHelper.onViewAdded(child);
    }
}
