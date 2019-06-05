package com.warm.demo;

import android.app.Application;

import com.warm.trackboy.Data;
import com.warm.trackboy.Track;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Track.init(new DefaultTracker());

        Data.putEvent("AppCompatButton:kktitle$LinearLayout:-1$MainActivity$MainActivity", "eventId", "点击标题");

        Data.putEvent("9e6b11c0db8d57f4794faf0625913f87", "eventId", "选中#未选中");

        Data.putEvent("c6fb01aa89e4a01cb4d40304f0c9e27a","eventId","打开弹框");

    }
}
