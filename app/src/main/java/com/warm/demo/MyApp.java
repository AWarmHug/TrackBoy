package com.warm.demo;

import android.app.Application;

import com.warm.someaop.Data;
import com.warm.someaop.Track;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Track.init(new DefaultTracker());

        Data.putEvent("com.warm.demo.MainActivity$kktitle", "eventId", "点击标题");

        Data.putEvent("com.warm.demo.MainActivity$cbAddress", "eventId", "选中#未选中");

        Data.putEvent("com.warm.demo.MainActivity$tvDialog","eventId","打开弹框");

    }
}
