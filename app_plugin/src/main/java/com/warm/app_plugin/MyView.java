package com.warm.app_plugin;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new OnClickListenerProxy(l));
    }

    class OnClickListenerProxy implements OnClickListener {
        OnClickListener l;

        OnClickListenerProxy(OnClickListener l) {
            this.l = l;
        }

        @Override
        public void onClick(View v) {
            l.onClick(v);
            Log.d("**********", "setOnClickListener: 我的点击事件");
        }
    }
}
