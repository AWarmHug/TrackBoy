package com.warm.library_plugin.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

public abstract class TViewGroup extends ViewGroup {
    public TViewGroup(Context context) {
        super(context);
    }

    public TViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
