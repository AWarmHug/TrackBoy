package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.warm.library_plugin.helper.ViewActionHelper;

public class TImageButton extends ImageButton {
    public TImageButton(Context context) {
        super(context);
    }

    public TImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        ViewActionHelper.performClick(this);
        return click;
    }

}
