package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.warm.track.Track;

public class TButton extends Button {
    public TButton(Context context) {
        super(context);
    }

    public TButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        Track.getTrack().getViewAction().performClick(this);
        return click;
    }


}
