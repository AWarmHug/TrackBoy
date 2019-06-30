package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.warm.track.Track;

public class TEditText extends EditText {
    public TEditText(Context context) {
        super(context);
    }

    public TEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        Track.getTrack().getViewTracker().performClick(this);
        return click;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean didTouchFocusSelect() {
        return super.didTouchFocusSelect();
    }
}
