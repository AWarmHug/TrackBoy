package com.warm.library_plugin.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.warm.library_plugin.proxy.OnClickListenerProxy;

public class TScrollView extends ScrollView {
    public TScrollView(Context context) {
        super(context);
    }

    public TScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
