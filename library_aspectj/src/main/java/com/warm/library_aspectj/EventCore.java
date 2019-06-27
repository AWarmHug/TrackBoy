package com.warm.library_aspectj;

import android.util.Log;

import com.warm.track.annotation.Event;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
public class EventCore extends BaseCore{

    @Pointcut("execution(@com.warm.track.annotation.Event * *(..))")
    public void method() {

    }

    @Pointcut("execution(@com.warm.track.annotation.Event *.new(..))")
    public void constructor() {

    }


    @After("method()||constructor()")
    public void inject(JoinPoint joinPoint) throws Throwable {
        Event event = getMethodAnnotation(joinPoint, Event.class);
        if (event != null) {
            track(event);
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

    protected void track(Event debugLog) {
        if (debugLog != null) {
            Log.d(TAG, "track: "+debugLog.value());
            track(debugLog.eventId(), debugLog.value());
        }
    }
}
