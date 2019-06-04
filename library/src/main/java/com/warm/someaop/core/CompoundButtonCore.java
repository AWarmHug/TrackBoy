package com.warm.someaop.core;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.warm.someaop.BuildConfig;
import com.warm.someaop.Data;
import com.warm.someaop.Trace;
import com.warm.someaop.utils.Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CompoundButtonCore extends BaseCore {


    @Pointcut("execution(* android.widget.CompoundButton.OnCheckedChangeListener.onCheckedChanged(..))")
    public void onCheckedChanged() {

    }

    @After("onCheckedChanged()&&!within(EventCore)")
    public void injectOnCheckedChanged(JoinPoint joinPoint) throws Throwable {
        Object[] o = joinPoint.getArgs();
        if (o.length == 2 && o[0] instanceof CompoundButton) {
            View view = (View) o[0];

            boolean isChecked = (boolean) o[1];

            Trace trace = Data.getEvent(getName(joinPoint, view));

            if (trace != null) {
                if (!TextUtils.isEmpty(trace.getValue()) && trace.getValue().contains(Trace.or)) {
                    String[] checkValue = trace.getValue().split(Trace.or);
                    track(trace.getId(), isChecked ? checkValue[0] : checkValue[1]);
                } else {
                    track(trace.getId(), trace.getValue());
                }
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
