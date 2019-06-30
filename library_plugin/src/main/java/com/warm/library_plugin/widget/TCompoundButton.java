package com.warm.library_plugin.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import androidx.annotation.RequiresApi;

import com.warm.track.Track;

/**
 * 作者：warm
 * 时间：2019-06-29 11:20
 * 描述：
 */
public abstract class TCompoundButton extends CompoundButton {

    public TCompoundButton(Context context) {
        super(context);
    }

    public TCompoundButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TCompoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TCompoundButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
