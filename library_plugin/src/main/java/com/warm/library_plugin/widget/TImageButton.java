package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import com.warm.library_plugin.proxy.OnClickListenerProxy;

public class TImageButton extends ImageButton {
    public TImageButton(Context context) {
        super(context);
    }

    public TImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
