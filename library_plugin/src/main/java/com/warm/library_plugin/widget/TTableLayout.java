package com.warm.library_plugin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TableLayout;

import androidx.annotation.Nullable;

import com.warm.track.proxy.OnClickListenerProxy;

public class TTableLayout extends TableLayout {


    public TTableLayout(Context context) {
        super(context);
    }

    public TTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
    }
}
