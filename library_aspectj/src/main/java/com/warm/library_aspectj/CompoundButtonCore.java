package com.warm.library_aspectj;

import android.widget.CompoundButton;

import com.warm.track.Track;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect
public class CompoundButtonCore {

    @Pointcut("execution(@Event * *(..))")
    public void method() {

    }

    @Pointcut("execution(* android.widget.CompoundButton.OnCheckedChangeListener.onCheckedChanged(android.widget.CompoundButton,boolean))&&args(buttonView,isChecked)||(execution(void *..lambda*(android.widget.CompoundButton,boolean))&&args(buttonView,isChecked))")
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @After("onCheckedChanged(buttonView,isChecked)&&!method()")
    public void injectOnCheckedChanged(JoinPoint joinPoint, CompoundButton buttonView, boolean isChecked) throws Throwable {
        Track.getTrack().getViewTracker().setChecked(buttonView, isChecked);
    }


}
