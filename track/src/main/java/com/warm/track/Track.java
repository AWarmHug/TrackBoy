package com.warm.track;

public class Track {

    private static D mD;

    private static Tracker mTracker;

    public static void init(Tracker tracker, D d) {
        mTracker = tracker;
        mD = d;
    }

    public static Tracker getTracker() {
        if (mTracker == null) {
            throw new RuntimeException("请先init");
        }
        return mTracker;
    }

    public static D getD() {
        return mD;
    }


}
