package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsoluteLayout;

import androidx.annotation.Nullable;

import com.warm.track.proxy.OnClickListenerProxy;

@Deprecated
public class TAbsoluteLayout extends AbsoluteLayout {
    public TAbsoluteLayout(Context context) {
        super(context);
    }

    public TAbsoluteLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TAbsoluteLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
