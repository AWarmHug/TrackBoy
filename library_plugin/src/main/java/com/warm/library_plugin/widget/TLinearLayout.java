package com.warm.library_plugin.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.warm.library_plugin.TrackingAccessibilityDelegate;

public class TLinearLayout extends LinearLayout {
    public TLinearLayout(Context context) {
        super(context);
    }

    public TLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public TLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        if (child.getClass().getSimpleName().equals("Button")){
            child.setAccessibilityDelegate(new TrackingAccessibilityDelegate());
        }

    }
}
