package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.warm.library_plugin.helper.ViewActionHelper;

public class TAutoCompleteTextView extends AutoCompleteTextView {
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
    public boolean performClick() {
        boolean click = super.performClick();
        ViewActionHelper.performClick(this);
        return click;
    }

}
