package com.warm.track;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.warm.track.utils.Utils;

/**
 * 作者：warm
 * 时间：2019-06-28 22:44
 * 描述：
 */
public class ViewFinder {


    public static String getName(View view) {

        StringBuilder sb = new StringBuilder();

        sb.append(getViewName(view))
                .append("$")
                .append(getActivityName(view));

        String md5 = Utils.toMD5(sb.toString());

//        if (BuildConfig.DEBUG) {
        Log.d("Track", "getName: " + sb.toString() + ",MD5: " + md5);
//        }

        return md5;
    }


    public static String getActivityName(View view) {
        Activity activity = getActivity(view);
        if (activity != null) {
            return activity.getClass().getSimpleName();
        } else {
            return view.getContext().getClass().getSimpleName();
        }
    }

    @Nullable
    public static Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static String getClassName(Class<?> clazz) {

        if (clazz.getEnclosingClass() != null) {
            return getClassName(clazz.getEnclosingClass());
        } else {
            return clazz.getSimpleName();
        }
    }

    public static String getViewName(View view) {
        StringBuilder sb = new StringBuilder();
        appendName(sb, view);
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view.getParent();
            while (parent != null && parent.getId() != android.R.id.content) {
                appendName(sb, parent);
                if (parent instanceof ViewGroup) {
                    parent = (ViewGroup) parent.getParent();
                } else {
                    break;
                }
            }
        }
        return sb.toString();
    }


    private static void appendName(StringBuilder sb, View view) {
        if (view.getTag(R.id.key_fragment_name) != null) {
            sb.append("$")
                    .append(view.getTag(R.id.key_fragment_name));
            ViewGroup viewGroup = (ViewGroup) view.getParent();

            if (viewGroup instanceof ViewPager) {
                sb.append(":");
                sb.append(((ViewPager) viewGroup).getCurrentItem());
            }
        } else {
            if (view.getId() != View.NO_ID) {
                sb.append("$");
                sb.append(view.getClass().getSimpleName())
                        .append(":")
                        .append(view.getResources().getResourceEntryName(view.getId()));
            } else {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                sb.append("$");
                sb.append(view.getClass().getSimpleName())
                        .append(":");
                if (viewGroup instanceof RecyclerView) {
                    sb.append(((RecyclerView) viewGroup).getChildAdapterPosition(view));
                } else {
                    sb.append(viewGroup.indexOfChild(view));
                }
            }

        }
    }

}
