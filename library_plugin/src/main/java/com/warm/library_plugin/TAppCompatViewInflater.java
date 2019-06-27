package com.warm.library_plugin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatViewInflater;

import com.google.android.material.theme.MaterialComponentsViewInflater;
import com.warm.library_plugin.widget.TAbsoluteLayout;
import com.warm.library_plugin.widget.TFrameLayout;
import com.warm.library_plugin.widget.TLinearLayout;
import com.warm.library_plugin.widget.TRelativeLayout;
import com.warm.library_plugin.widget.TScrollView;
import com.warm.library_plugin.widget.TTableLayout;

public class TAppCompatViewInflater extends MaterialComponentsViewInflater {

    @Nullable
    @Override
    protected View createView(Context context, String name, AttributeSet attrs) {
        switch (name) {
            case "LinearLayout":
                return new TLinearLayout(context, attrs);
            case "FrameLayout":
                return new TFrameLayout(context, attrs);
            case "TableLayout":
                return new TTableLayout(context, attrs);
            case "RelativeLayout":
                return new TRelativeLayout(context, attrs);
            case "TScrollView":
                return new TScrollView(context, attrs);
            case "AbsoluteLayout":
                return new TAbsoluteLayout(context, attrs);
            default:
                return null;
        }

    }
}
