package com.warm.demo.track;

import android.content.Context;

public class ViewTrace {
    private Context mContext;
    private Trace mTrace;

    public ViewTrace(Context context, Trace trace) {
        mContext = context;
        mTrace = trace;
    }

    public Context getContext() {
        return mContext;
    }

    public Trace getTrace() {
        return mTrace;
    }


    public static class Trace {
        public static final String or = "#";

        public static final String r = "^^r^$";


        private String id;
        private String value;

        public String getId() {
            return id;
        }

        public String getValue() {
            return value;
        }

        public Trace(String id, String value) {
            this.id = id;
            this.value = value;
        }

    }
}
