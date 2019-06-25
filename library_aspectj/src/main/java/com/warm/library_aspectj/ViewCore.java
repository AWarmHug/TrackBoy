package com.warm.library_aspectj;

import android.view.View;

import com.warm.library_aspectj.proxy.OnClickListenerProxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ViewCore extends BaseCore {

    @Pointcut("call(* android.view.View.setOnClickListener(android.view.View.OnClickListener))&&args(clickListener)")
    public void setOnClickListener(View.OnClickListener clickListener) {

    }

    @Around("setOnClickListener(clickListener)")
    public Object injectSetOnClickListener(ProceedingJoinPoint joinPoint, View.OnClickListener clickListener) throws Throwable {
        return joinPoint.proceed(new Object[]{new OnClickListenerProxy(clickListener)});
    }


//    /**
//     * 这是自定义注解的切点，如果在方法上加入了{@link Event},就认定是一个切点
//     */
//    @Pointcut("execution(@Event * *(..))")
//    public void method() {
//
//    }
//
//    /**
//     * {@link android.view.View.OnClickListener#onClick(View)}的切点
//     * 第二段为lambda的写法，
//     * @param view
//     */
//    @Pointcut(value = "((execution(* android.view.View.OnClickListener.onClick(android.view.View))&&args(view))||(execution(void *..lambda*(android.view.View))&&args(view)))&&!within(com.warm.demo.ApplyLayout)")
//    public void onClick(View view) {
//
//    }
//
//    /**
//     * 具体的通知方法，当Pointcut中的方法被调用之后，触发该方法对一些信息进行拦截
//     * @param joinPoint
//     * @param view
//     * @param obj
//     * @throws Throwable
//     */
//    @After("onClick(view)&&!method()&&this(obj)")
//    public void injectOnClick(JoinPoint joinPoint, View view, Object obj) throws Throwable {
//        Trace trace = Data.getEvent(getName(joinPoint, view));
//        if (trace != null) {
//            track(trace.getId(), trace.getValue());
//        }
//    }
//
//
//    private String getName(JoinPoint joinPoint, View view) {
//
//        StringBuilder sb = new StringBuilder();
//
//        sb.append(getViewName(view))
//                .append("$")
//                .append(getClassName(joinPoint.getTarget().getClass()))
//                .append("$")
//                .append(getClassName(view.getContext().getClass()));
//
//        String md5 = Utils.toMD5(sb.toString());
//
//        if (BuildConfig.DEBUG) {
//            Log.d(TAG, "getName: " + sb.toString() + ",MD5: " + md5);
//        }
//
//        return md5;
//    }

}
