package com.warm.library_aspectj;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.warm.demo.BuildConfig;
import com.warm.track.Data;
import com.warm.track.Trace;
import com.warm.track.utils.Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect
public class CompoundButtonCore extends BaseCore {

    @Pointcut("execution(@Event * *(..))")
    public void method() {

    }

    @Pointcut("execution(* android.widget.CompoundButton.OnCheckedChangeListener.onCheckedChanged(android.widget.CompoundButton,boolean))&&args(buttonView,isChecked)||(execution(void *..lambda*(android.widget.CompoundButton,boolean))&&args(buttonView,isChecked))")
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @After("onCheckedChanged(buttonView,isChecked)&&!method()")
    public void injectOnCheckedChanged(JoinPoint joinPoint, CompoundButton buttonView, boolean isChecked) throws Throwable {

        Trace trace = Data.getEvent(getName(joinPoint, buttonView));

        if (trace != null) {
            if (!TextUtils.isEmpty(trace.getValue()) && trace.getValue().contains(Trace.or)) {
                String[] checkValue = trace.getValue().split(Trace.or);
                track(trace.getId(), isChecked ? checkValue[0] : checkValue[1]);
            } else {
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
