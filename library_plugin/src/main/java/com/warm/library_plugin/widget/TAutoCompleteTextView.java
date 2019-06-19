package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;

import com.warm.library_plugin.proxy.OnClickListenerProxy;

public class TAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    public TAutoCompleteTextView(Context context) {
        super(context);
    }

    public TAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
