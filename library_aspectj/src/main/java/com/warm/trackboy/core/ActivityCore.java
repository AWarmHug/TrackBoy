package com.warm.trackboy.core;

import android.util.Log;
import android.view.View;

import com.warm.trackboy.BuildConfig;
import com.warm.trackboy.Data;
import com.warm.trackboy.Trace;
import com.warm.trackboy.utils.Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ActivityCore extends BaseCore {


    @Pointcut("execution(void android.app.Activity.onDestroy(..))")
    public void onDestroy() {

    }

    @After("onDestroy()&&within(android.app.Activity)")
    public void injectOnDestroy(JoinPoint joinPoint) {
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

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "getName: " + sb.toString() + ",MD5: " + md5);
        }

        return md5;
    }

}
