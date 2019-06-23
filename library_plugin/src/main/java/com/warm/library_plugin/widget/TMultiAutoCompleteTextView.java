package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.Nullable;

import com.warm.track.proxy.OnClickListenerProxy;

public class TMultiAutoCompleteTextView extends MultiAutoCompleteTextView {
    public TMultiAutoCompleteTextView(Context context) {
        super(context);
    }

    public TMultiAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TMultiAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
