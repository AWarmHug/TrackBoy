package com.warm.someaop.core;

import android.util.Log;
import android.view.View;

import com.warm.someaop.BuildConfig;
import com.warm.someaop.Data;
import com.warm.someaop.Trace;
import com.warm.someaop.utils.Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ViewCore extends BaseCore {

    @Pointcut("execution(@com.warm.someaop.annotation.Event * *(..))")
    public void method() {

    }

    @Pointcut("execution(* android.view.View.OnClickListener.onClick(..))||execution(void *..lambda*(android.view.View))")
    public void onClick() {

    }


    @After("onClick()&&!method()")
    public void injectOnClick(JoinPoint joinPoint) throws Throwable {
        Object[] o = joinPoint.getArgs();
        if (o.length == 1 && o[0] instanceof View) {
            View view = (View) o[0];
            Trace trace = Data.getEvent(getName(joinPoint, view));
            if (trace != null) {
                track(trace.getId(), trace.getValue());
            }
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
