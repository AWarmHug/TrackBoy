package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.warm.library_plugin.helper.ViewActionHelper;

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
    public boolean performClick() {
        boolean click = super.performClick();
        ViewActionHelper.performClick(this);
        return click;
    }

    @Override
    public void setChecked(boolean checked) {
        boolean c = isChecked() != checked;
        super.setChecked(checked);
        if (c) {
            ViewActionHelper.setChecked(this, checked);
        }
    }

}
