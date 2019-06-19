package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;

import com.warm.library_plugin.proxy.OnClickListenerProxy;

public class TSpinner extends AppCompatSpinner {
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
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
