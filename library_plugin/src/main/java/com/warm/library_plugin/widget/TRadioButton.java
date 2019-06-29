package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.warm.track.Track;

public class TRadioButton extends RadioButton {
    public TRadioButton(Context context) {
        super(context);
    }

    public TRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public void setChecked(boolean checked) {
        boolean c = isChecked() != checked;
        super.setChecked(checked);
        if (c) {
            Track.getTrack().getViewAction().setChecked(this, checked);
        }
    }

}
