package com.warm.demo;

import android.app.Application;
import android.content.Intent;
import android.view.View;

import com.warm.demo.detail.DetailActivity;
import com.warm.trackboy.Data;
import com.warm.trackboy.Track;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Track.init(new DefaultTracker());

        Data.putEvent("AppCompatButton:kktitle$LinearLayout:-1$MainActivity$MainActivity", "00001", "点击标题");

        Data.putEvent("9e6b11c0db8d57f4794faf0625913f87", "00002", "选中#未选中");

        Data.putEvent("c6fb01aa89e4a01cb4d40304f0c9e27a","00003","打开弹框");

        Data.putEvent("e5cfec6bddcbc26958c45e124c371226","00003","申请信息：");

    }

    public void goDetail(View v) {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}
