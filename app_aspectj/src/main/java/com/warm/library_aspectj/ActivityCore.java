package com.warm.library_aspectj;

import android.util.Log;

import com.warm.track.Data;
import com.warm.track.Trace;
import com.warm.track.utils.Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ActivityCore extends BaseCore {


    @Pointcut("execution(void com.warm.demo.base.BaseActivity.onCreate(android.os.Bundle))&&within(com.warm.demo.base.BaseActivity)")
    public void onCreate() {

    }

    @Pointcut("execution(void com.warm.demo.*.*Activity.onDestroy())&&within(com.warm.demo.base.BaseActivity)")
    public void onDestroy() {

    }

    @After("onCreate()||onDestroy()")
    public void injectOnCreate(JoinPoint joinPoint) {
        Trace trace = Data.getEvent(getName(joinPoint));
        if (trace != null) {
            track(trace.getId(), trace.getValue());
        }
    }

    private String getName(JoinPoint joinPoint) {

        StringBuilder sb = new StringBuilder();
        sb.append(joinPoint.toShortString())
                .append("$")
                .append(getClassName(joinPoint.getTarget().getClass()));

        String md5 = Utils.toMD5(sb.toString());

        Log.d(TAG, "getName: " + sb.toString() + ",MD5: " + md5);

        return md5;
    }

}
