package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.warm.library_plugin.proxy.OnClickListenerProxy;

public class TView extends View {
    public TView(Context context) {
        super(context);
    }

    public TView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
