package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MultiAutoCompleteTextView;

import com.warm.library_plugin.helper.ViewActionHelper;

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
    public boolean performClick() {
        boolean click = super.performClick();
        ViewActionHelper.performClick(this);
        return click;
    }

}
