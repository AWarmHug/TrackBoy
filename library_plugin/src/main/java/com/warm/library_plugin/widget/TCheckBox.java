package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.warm.track.Track;

public class TCheckBox extends CheckBox {
    public TCheckBox(Context context) {
        super(context);
    }

    public TCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean checked) {
        boolean c = isChecked() != checked;
        super.setChecked(checked);
        if (c) {
            Track.getTrack().getViewTracker().setChecked(this, checked);
        }
    }

}
