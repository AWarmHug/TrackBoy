package com.warm.library_aspectj;

import android.content.DialogInterface;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.warm.track.BuildConfig;
import com.warm.track.Data;
import com.warm.track.Trace;
import com.warm.track.utils.Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DialogCore extends BaseCore {

    @Pointcut("execution(@com.warm.someaop.annotation.Event * *(..))")
    public void method() {

    }

    @Pointcut(value = "execution(void android.content.DialogInterface.OnClickListener.onClick(..))||execution(void *..lambda*(android.content.DialogInterface,int))")
    public void dialogOnClick() {

    }

    @After("dialogOnClick()&&!method()")
    public void injectDialogOnClick(JoinPoint joinPoint) {
        Object[] o = joinPoint.getArgs();
        if (o.length == 2 && o[0] instanceof DialogInterface) {
            DialogInterface dialogInterface = (DialogInterface) o[0];
            String name = getName(joinPoint, dialogInterface, (int) o[1]);
            Trace trace = Data.getEvent(name);
            if (trace != null) {
                track(trace.getId(), trace.getValue());
            }
        }
    }


    private String getName(JoinPoint joinPoint, DialogInterface dialogInterface, int which) {

        StringBuilder sb = new StringBuilder();

        if (dialogInterface instanceof AlertDialog) {
            AlertDialog dialog = (AlertDialog) dialogInterface;
            sb.append(getViewName(dialog.getButton(which)))
                    .append("$")
                    .append(getClassName(joinPoint.getTarget().getClass()));
            TextView message = dialog.findViewById(android.R.id.message);
            if (message != null) {
                String msgStr = message.getText().toString();
                if (msgStr.length() > 10) {
                    msgStr = msgStr.substring(0, 10);
                }
                sb.append("$")
                        .append(msgStr);

            }
        }

        String md5 = Utils.toMD5(sb.toString());
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "getName: " + sb.toString() + ",MD5: " + md5);
        }
        return md5;

    }

}
