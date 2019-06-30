package com.warm.track;

public class Track<T> {
    private static Track mTrack;

    private ViewTracker<T> mViewTracker;

    public static <T> void init(ViewTracker<T> viewTracker) {
        mTrack = new Track();
        mTrack.mViewTracker = viewTracker;

    }

    public static Track getTrack() {
        return mTrack;
    }


    public ViewTracker<T> getViewTracker() {
        return mViewTracker;
    }
}
