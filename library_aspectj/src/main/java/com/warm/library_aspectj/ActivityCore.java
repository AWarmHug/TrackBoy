package com.warm.library_aspectj;

import android.util.Log;

import com.warm.track.utils.Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ActivityCore  {


    @Pointcut("execution(void com.warm.demo.base.BaseActivity.onCreate(android.os.Bundle))&&within(com.warm.demo.base.BaseActivity)")
    public void onCreate() {

    }

    @Pointcut("execution(void com.warm.demo.*.*Activity.onDestroy())&&within(com.warm.demo.base.BaseActivity)")
    public void onDestroy() {

    }

    @After("onCreate()||onDestroy()")
    public void injectOnCreate(JoinPoint joinPoint) {

    }


}
