package com.warm.trackboy.core;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;

import java.util.concurrent.TimeUnit;

@Aspect
public class LogCore {

    @Pointcut("execution(@com.example.library.annotation.Log * *(..))")
    public void method() {

    }

    @Pointcut("execution(@com.example.library.annotation.Log *.new(..))")
    public void constructor() {

    }

    @Pointcut("execution(* android.app.Activity.on**(..))")
    public void pointcutOn() {

    }

    @Around("method()||constructor()")
    public Object logDebug(ProceedingJoinPoint joinPoint) throws Throwable {
        return doLog(joinPoint);
    }

    private Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        Class<?> cls = codeSignature.getDeclaringType();
        String methodName = codeSignature.getName();
        long startNanos = System.nanoTime();
        Object result = joinPoint.proceed();
        long endNanos = System.nanoTime();
        long executeTime = TimeUnit.NANOSECONDS.toMillis(endNanos - startNanos);


        StringBuilder sb = new StringBuilder();
        sb.append("methodName:").append(methodName)
                .append("return:").append(result == null ? "null" : result.toString())
                .append("\n")
                .append("executeTime:").append(executeTime);
        Log.d(asTag(cls), sb.toString());



        return result;
    }


    private String asTag(Class<?> cls) {
        if (cls.isAnonymousClass()) {
            return asTag(cls.getEnclosingClass());
        }
        return cls.getName();
    }



}
