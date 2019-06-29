package com.warm.track;

import android.view.View;

/**
 * 作者：warm
 * 时间：2019-06-29 11:55
 * 描述：
 */
public class ViewAction<T> {

    private Tracker<T> mTracker;

    private ViewFinder<T> mViewFinder;

    public ViewAction(Tracker<T> tracker, ViewFinder<T> viewFinder) {
        mTracker = tracker;
        mViewFinder = viewFinder;
    }

    public void performClick(View view) {
        T t = mViewFinder.find(view);
        mTracker.track(t);

    }

    public void setChecked(View view, boolean checked) {
        T t = mViewFinder.find(view);
        mTracker.track(t,checked);

    }


}
