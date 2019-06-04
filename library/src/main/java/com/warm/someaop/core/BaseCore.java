package com.warm.someaop.core;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.warm.someaop.BuildConfig;
import com.warm.someaop.Data;
import com.warm.someaop.Trace;
import com.warm.someaop.Track;
import com.warm.someaop.annotation.Event;
import com.warm.someaop.utils.Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class BaseCore {
    protected static final String TAG = "Track----";


    protected String getClassName(Class<?> clazz) {

        if (clazz.getEnclosingClass() != null) {
            return getClassName(clazz.getEnclosingClass());
        } else {
            return clazz.getSimpleName();
        }
    }

    protected String getViewName(View view) {
        StringBuilder sb = new StringBuilder();
        sb.append(view.getClass().getSimpleName())
                .append(":")
                .append(view.getResources().getResourceEntryName(view.getId()));
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view.getParent();

            while (parent != null && parent.getId() != android.R.id.content) {
                if (parent.getId() != View.NO_ID) {
                    sb.append("$");
                    sb.append(parent.getClass().getSimpleName())
                            .append(":")
                            .append(parent.getResources().getResourceEntryName(parent.getId()));
                } else {
                    sb.append("$");
                    sb.append(parent.getClass().getSimpleName())
                            .append(":")
                            .append(View.NO_ID);
                }
                if (parent instanceof ViewGroup) {
                    parent = (ViewGroup) parent.getParent();
                } else {
                    break;
                }
            }
        }
        return sb.toString();


    }


    protected void track(String eventId, String events) {
        Track.getTracker().track(eventId, events);
    }

}
