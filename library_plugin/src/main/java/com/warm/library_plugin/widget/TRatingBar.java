package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RatingBar;

import androidx.annotation.Nullable;

import com.warm.track.proxy.OnClickListenerProxy;

public class TRatingBar extends RatingBar {
    public TRatingBar(Context context) {
        super(context);
    }

    public TRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
