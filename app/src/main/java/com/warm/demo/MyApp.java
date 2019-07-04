package com.warm.demo;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.warm.demo.data.Data;
import com.warm.demo.track.DefaultViewFinder;
import com.warm.demo.track.DefaultViewTracker;
import com.warm.demo.track.ViewTrace;
import com.warm.track.Track;
import com.warm.track.ViewTracker;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, "5d1d62223fc1956f0e000837", null, UMConfigure.DEVICE_TYPE_PHONE, null);
        ViewTracker<ViewTrace> viewTracker = new DefaultViewTracker(new DefaultViewFinder());
        Track.init(viewTracker);

        Data.putEvent("79b9a7987b4b786a4123da40c9e1dff1", "00001", "按钮");

        Data.putEvent("fce08ae6d559915746f72c32937efcb3", "00002", "选中#未选中");

        Data.putEvent("c6fb01aa89e4a01cb4d40304f0c9e27a", "00003", "打开弹框");

        Data.putEvent("e5cfec6bddcbc26958c45e124c371226", "00004", "申请信息：");

        Data.putEvent("6b4ea1b0816ceb9ecfc646dcd724dc09", "00005", "看我");

        Data.putEvent("ddfbc6841607928a9adb84a969724335", "00006", "看我");


    }

}
