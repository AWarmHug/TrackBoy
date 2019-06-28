package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.warm.library_plugin.helper.ViewActionHelper;

public class TView extends View {
    public TView(Context context) {
        super(context);
    }

    public TView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        ViewActionHelper.performClick(this);
        return click;
    }


}
