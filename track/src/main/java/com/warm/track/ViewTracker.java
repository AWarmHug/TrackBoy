package com.warm.track;

import android.view.View;

import androidx.annotation.Nullable;

/**
 * 作者：warm
 * 时间：2019-06-29 11:55
 * 描述：
 */
public abstract class ViewTracker<T> {


    public abstract T find(View view);

    public abstract void track(@Nullable T t);

    public abstract void track(@Nullable T t, boolean checked);


    public void performClick(View view) {
        T t = find(view);
        track(t);
    }


    public void setChecked(View view, boolean checked) {
        T t = find(view);
        track(t, checked);
    }


}
