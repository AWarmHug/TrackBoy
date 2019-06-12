package com.warm.demo;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.warm.trackboy.BuildConfig;
import com.warm.trackboy.Data;
import com.warm.trackboy.Trace;
import com.warm.trackboy.core.BaseCore;
import com.warm.trackboy.utils.Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ApplyLayoutCore extends BaseCore {

    /**
     * 这是自定义注解的切点，如果在方法上加入了{@link com.warm.trackboy.annotation.Event},就认定是一个切点
     */
    @Pointcut("execution(@com.warm.someaop.annotation.Event * *(..))")
    public void method() {

    }

    /**
     * {@link android.view.View.OnClickListener#onClick(View)}的切点
     * 第二段为lambda的写法，
     * @param view
     */
    @Pointcut(value = "((execution(* android.view.View.OnClickListener.onClick(android.view.View))&&args(view))||(execution(void *..lambda*(android.view.View))&&args(view)))&&within(com.warm.demo.ApplyLayout)")
    public void onApplyClick(View view) {

    }

    @After("onApplyClick(view)&&!method()&&this(obj)")
    public void injectOnApplyClick(JoinPoint joinPoint, View view, Object obj) throws Throwable {
        Trace trace = Data.getEvent(getName(joinPoint, view));
        if (trace != null) {
            String info = null;
            if (view.getParent() instanceof ViewGroup){

                info=((EditText)((ViewGroup)view.getParent()).findViewById(R.id.etInfo)).getText().toString();
            }
            track(trace.getId(), trace.getValue()+info);
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
