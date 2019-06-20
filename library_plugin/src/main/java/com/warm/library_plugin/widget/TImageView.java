package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import com.warm.library_plugin.proxy.OnClickListenerProxy;

public class TImageView extends ImageView {
    public TImageView(Context context) {
        super(context);
    }

    public TImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
