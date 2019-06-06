package com.warm.trackboy.core;

import android.app.Activity;
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
public class ViewCore extends BaseCore {

    @Pointcut("execution(@com.warm.someaop.annotation.Event * *(..))")
    public void method() {

    }

    @Pointcut(value = "(execution(* android.view.View.OnClickListener.onClick(android.view.View))&&args(view))||(execution(void *..lambda*(android.view.View))&&args(view))")
    public void onClick(View view) {

    }


    @After("onClick(view)&&!method()&&this(obj)")
    public void injectOnClick(JoinPoint joinPoint, View view,Object obj) throws Throwable {
        Log.d(TAG, "injectOnClick: "+obj.getClass().getSimpleName());
        Trace trace = Data.getEvent(getName(joinPoint, view));
        if (trace != null) {
            track(trace.getId(), trace.getValue());
        }
    }


    private String getName(JoinPoint joinPoint, View view) {

        StringBuilder sb = new StringBuilder();

        sb.append(getViewName(view))
                .append("$")
                .append(getClassName(joinPoint.getTarget().getClass()))
                .append("$")
                .append(getClassName(view.getContext().getClass()));

        String md5 = Utils.toMD5(sb.toString());

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "getName: " + sb.toString() + ",MD5: " + md5);
        }

        return md5;
    }

}
