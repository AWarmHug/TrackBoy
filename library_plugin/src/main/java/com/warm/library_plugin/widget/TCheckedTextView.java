package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatTextView;

import com.warm.library_plugin.proxy.OnClickListenerProxy;

public class TCheckedTextView extends AppCompatCheckedTextView {
    public TCheckedTextView(Context context) {
        super(context);
    }

    public TCheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
