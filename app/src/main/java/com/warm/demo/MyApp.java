package com.warm.demo;

import android.app.Application;

import com.warm.someaop.Data;
import com.warm.someaop.Track;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Track.init(new DefaultTracker());

        Data.putEvent("AppCompatButton:kktitle$LinearLayout:-1$MainActivity$MainActivity", "eventId", "点击标题");

        Data.putEvent("AppCompatCheckBox:cbAddress$LinearLayout:-1$MainActivity$MainActivity", "eventId", "选中#未选中");

        Data.putEvent("AppCompatButton:tvDialog$LinearLayout:-1$MainActivity$MainActivity","eventId","打开弹框");

    }
}
