package com.warm.someaop.core;

import android.util.Log;
import android.view.View;


import com.warm.someaop.R;
import com.warm.someaop.TrackView;
import com.warm.someaop.annotation.Track;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
public class TrackCore {

    @Pointcut("execution(@com.example.library.annotation.Track * *(..))")
    public void method() {

    }

    @Pointcut("execution(@com.example.library.annotation.Track *.new(..))")
    public void constructor() {

    }

    @Pointcut("execution(* android.view.View.OnClickListener.onClick(..))")
    public void onClick() {

    }

    @After("method()||constructor()")
    public void inject(JoinPoint joinPoint) throws Throwable {
        Track track = getMethodAnnotation(joinPoint, Track.class);
        if (track != null) {
            track(track);
        }
    }

    @After("onClick()&&!method()")
    public void injectOnClick(JoinPoint joinPoint) throws Throwable {
        Object[] o = joinPoint.getArgs();
        if (o.length == 1 && o[0] instanceof View) {
            View view = (View) o[0];
            TrackView.Event event = (TrackView.Event) view.getTag(R.id.key_track);
            if (event!=null) {
                track(event.getEventId(), event.getEvents());
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

    private void track(Track debugLog) {
        if (debugLog != null) {
            track(debugLog.eventId(), debugLog.value());
        }
    }

    private void track(String eventId, String... strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }

        Log.d("Track", "track: eventId=" + eventId + ",action=" + sb.toString());

    }

}
