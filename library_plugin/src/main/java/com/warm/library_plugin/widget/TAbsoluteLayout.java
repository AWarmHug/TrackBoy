package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsoluteLayout;

import androidx.annotation.Nullable;

import com.warm.library_plugin.helper.AccessibilityDelegateHelper;
import com.warm.library_plugin.helper.ViewActionHelper;

@Deprecated
public class TAbsoluteLayout extends AbsoluteLayout {
    public TAbsoluteLayout(Context context) {
        super(context);
    }

    public TAbsoluteLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TAbsoluteLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        ViewActionHelper.performClick(this);
        return click;
    }


    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        AccessibilityDelegateHelper.onViewAdded(child);
    }


}
