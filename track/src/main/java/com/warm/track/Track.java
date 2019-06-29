package com.warm.track;

public class Track<T> {
    private static Track mTrack;

    private ViewAction<T> mViewAction;

    public static <T> void init(ViewAction<T> viewAction) {
        mTrack = new Track();
        mTrack.mViewAction = viewAction;

    }

    public static Track getTrack() {
        return mTrack;
    }


    public ViewAction<T> getViewAction() {
        return mViewAction;
    }
}
