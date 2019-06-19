package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.warm.library_plugin.proxy.OnClickListenerProxy;

public class TTextView extends AppCompatTextView {
    public TTextView(Context context) {
        super(context);
    }

    public TTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
