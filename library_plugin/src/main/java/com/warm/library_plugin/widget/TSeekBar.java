package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSeekBar;

import com.warm.library_plugin.proxy.OnClickListenerProxy;

public class TSeekBar extends SeekBar {
    public TSeekBar(Context context) {
        super(context);
    }

    public TSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
