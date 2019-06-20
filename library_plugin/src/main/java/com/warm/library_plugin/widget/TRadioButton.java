package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;

import com.warm.library_plugin.proxy.OnClickListenerProxy;

public class TRadioButton extends RadioButton {
    public TRadioButton(Context context) {
        super(context);
    }

    public TRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }
}
