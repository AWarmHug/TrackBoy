package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.warm.library_plugin.proxy.OnClickListenerProxy;

public class TEditText extends AppCompatEditText {
    public TEditText(Context context) {
        super(context);
    }

    public TEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
