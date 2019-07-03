package com.warm.library_aspectj;

import android.os.Trace;
import android.util.Log;

import com.warm.track.utils.Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 作者：warm
 * 时间：2019-06-26 21:18
 * 描述：
 */
@Aspect
public class FragmentCore {
    private static final String TAG = "FragmentCore";

    /**
     * @Override public void onResume() {
     * super.onResume();
     * }
     * @Override public void onPause() {
     * super.onPause();
     * }
     * @Override public void setUserVisibleHint(boolean isVisibleToUser) {
     * super.setUserVisibleHint(isVisibleToUser);
     * }
     * @Override public void onHiddenChanged(boolean hidden) {
     * super.onHiddenChanged(hidden);
     * }
     */

    @Pointcut("execution(void androidx.fragment.app.Fragment.onResume())&&within(androidx.fragment.app.Fragment)")
    public void onResume() {

    }

    @Pointcut("execution(void androidx.fragment.app.Fragment.onPause())&&within(androidx.fragment.app.Fragment)")
    public void onPause() {

    }

    @Pointcut("(execution(void androidx.fragment.app.Fragment.setUserVisibleHint(boolean))&&args(isVisibleToUser))&&within(androidx.fragment.app.Fragment)")
    public void setUserVisibleHint(boolean isVisibleToUser) {

    }

    @Pointcut("(execution(void androidx.fragment.app.Fragment.onHiddenChanged(boolean))&&args(hidden))&&within(androidx.fragment.app.Fragment)")
    public void onHiddenChanged(boolean hidden) {

    }

    @After("onResume()||onPause()")
    public void onLife(JoinPoint joinPoint) {

    }

    @After("setUserVisibleHint(isVisibleToUser)")
    public void injectSetUserVisibleHint(JoinPoint joinPoint, boolean isVisibleToUser) {
        Log.d(TAG, " isVisibleToUser=" + isVisibleToUser);

    }

    @After("onHiddenChanged(hidden)")
    public void injectOnHiddenChanged(JoinPoint joinPoint, boolean hidden) {
        Log.d(TAG, " hidden=" + hidden);

    }


    private String getName(JoinPoint joinPoint) {

        StringBuilder sb = new StringBuilder();
//        sb.append(joinPoint.toShortString())
//                .append("$")
//                .append(getClassName(joinPoint.getTarget().getClass()));

        String md5 = Utils.toMD5(sb.toString());

        Log.d(TAG, "getName: " + sb.toString() + ",MD5: " + md5);

        return md5;
    }

}
