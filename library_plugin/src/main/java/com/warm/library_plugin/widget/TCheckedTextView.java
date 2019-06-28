package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

import com.warm.library_plugin.helper.ViewActionHelper;

public class TCheckedTextView extends CheckedTextView {
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
    public void setChecked(boolean checked) {
        boolean c = isChecked() != checked;
        super.setChecked(checked);
        if (c) {
            ViewActionHelper.setChecked(this, checked);
        }
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        ViewActionHelper.performClick(this);
        return click;
    }
}
