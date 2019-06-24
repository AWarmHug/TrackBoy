package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.warm.track.proxy.OnClickListenerProxy;

public class TButton extends Button {
    public TButton(Context context) {
        super(context);
    }

    public TButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }

    @Override
    public boolean performLongClick() {
        return super.performLongClick();
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public void sendAccessibilityEvent(int eventType) {
        super.sendAccessibilityEvent(eventType);
    }
}
