package com.warm.library_plugin.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class TRelativeLayout extends RelativeLayout {
    public TRelativeLayout(Context context) {
        super(context);
    }

    public TRelativeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TRelativeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
