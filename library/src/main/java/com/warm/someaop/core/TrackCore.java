package com.warm.someaop.core;

import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;


import com.warm.someaop.Data;
import com.warm.someaop.Trace;
import com.warm.someaop.Track;
import com.warm.someaop.annotation.Event;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
public class TrackCore {

    @Pointcut("execution(@com.warm.someaop.annotation.Trace * *(..))")
    public void method() {

    }

    @Pointcut("execution(@com.warm.someaop.annotation.Trace *.new(..))")
    public void constructor() {

    }

    @Pointcut("execution(* android.view.View.OnClickListener.onClick(..))")
    public void onClick() {

    }

    @Pointcut("execution(* android.widget.CompoundButton.OnCheckedChangeListener.onCheckedChanged(..))")
    public void onCheckedChanged() {

    }


    @After("method()||constructor()")
    public void inject(JoinPoint joinPoint) throws Throwable {
        Event event = getMethodAnnotation(joinPoint, Event.class);
        if (event != null) {
            track(event);
        }
    }

    @After("onClick()&&!method()")
    public void injectOnClick(JoinPoint joinPoint) throws Throwable {
        Object[] o = joinPoint.getArgs();
        if (o.length == 1 && o[0] instanceof View) {
            View view = (View) o[0];
            Trace trace = Data.getEvent(getViewName(joinPoint, view));
            if (trace != null) {
                track(trace.getEventId(), trace.getValue());
            }
        }
    }

    @After("onCheckedChanged()&&!method()")
    public void injectOnCheckedChanged(JoinPoint joinPoint) throws Throwable {
        Object[] o = joinPoint.getArgs();
        if (o.length == 2 && o[0] instanceof CompoundButton) {
            View view = (View) o[0];

            boolean isChecked = (boolean) o[1];

            Trace trace = Data.getEvent(getViewName(joinPoint, view));

            if (trace != null) {
                if (!TextUtils.isEmpty(trace.getValue()) && trace.getValue().contains("#")) {
                    String[] checkValue = trace.getValue().split("#");
                    track(trace.getEventId(), isChecked ? checkValue[0] : checkValue[1]);
                } else {
                    track(trace.getEventId(), trace.getValue());
                }
            }
        }
    }


    private <A extends Annotation> A getMethodAnnotation(JoinPoint joinPoint, Class<A> a) {
        if (joinPoint.getSignature() instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            return method.getAnnotation(a);
        }
        return null;
    }


    private String getViewName(JoinPoint joinPoint, View view) {
        StringBuilder sb = new StringBuilder();

        Class<?> EnclosingClass = joinPoint.getTarget().getClass().getEnclosingClass();
        if (EnclosingClass != null) {
            sb.append(EnclosingClass.getName());
        } else {
            sb.append(joinPoint.getTarget().getClass().getName());
        }
        sb.append("$");
        sb.append(view.getResources().getResourceEntryName(view.getId()));
        return sb.toString();
    }

    private void track(Event debugLog) {
        if (debugLog != null) {
            track(debugLog.eventId(), debugLog.value());
        }
    }

    private void track(String eventId, String... events) {
        Track.getTracker().track(eventId, events);
    }

}
