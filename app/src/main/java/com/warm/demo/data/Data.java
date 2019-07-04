package com.warm.demo.data;

import androidx.collection.LruCache;

import com.warm.demo.track.ViewTrace;

public class Data {

    private static LruCache<String,  ViewTrace.Trace> sLruCache = new LruCache<>(200);

    public static void putEvent(String view, String eventId, String value) {
        putEvent(view, new  ViewTrace.Trace(eventId, value));
    }

    public static void putEvent(String view,  ViewTrace.Trace event) {
        sLruCache.put(view, event);
    }

    public static  ViewTrace.Trace getEvent(String view) {
        return sLruCache.get(view);
    }
}
