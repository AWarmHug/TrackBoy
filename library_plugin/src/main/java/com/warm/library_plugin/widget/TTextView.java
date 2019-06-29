package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.warm.track.Track;

public class TTextView extends TextView {
    public TTextView(Context context) {
        super(context);
    }

    public TTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        Track.getTrack().getViewAction().performClick(this);
        return click;
    }
}
