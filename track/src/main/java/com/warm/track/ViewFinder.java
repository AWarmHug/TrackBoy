package com.warm.track;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.warm.track.utils.Utils;

/**
 * 作者：warm
 * 时间：2019-06-28 22:44
 * 描述：
 */
public abstract class ViewFinder<T> {


    public abstract T find(View view);


    protected String getName(View view) {

        StringBuilder sb = new StringBuilder();

        sb.append(getViewName(view))
                .append("$")
                .append(getActivityName(view));

        String md5 = Utils.toMD5(sb.toString());

        Log.d("Track", "getExtra: " + sb.toString() + ",MD5: " + md5);

        return md5;
    }


    private String getActivityName(View view) {

        Activity activity = getActivity(view);
        if (activity != null) {
            StringBuilder sb = new StringBuilder(activity.getClass().getSimpleName());
            if (activity instanceof TrackExtraName) {
                TrackExtraName extraName = (TrackExtraName) activity;
                if (!TextUtils.isEmpty(extraName.getExtraName())) {
                    sb.append(extraName.getExtraName());
                }
            }

            return sb.toString();
        } else {
            return view.getContext().getClass().getSimpleName();
        }
    }

    @Nullable
    protected Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }


    protected String getViewName(View view) {
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


    protected void appendName(StringBuilder sb, View view) {
        if (view.getTag(R.id.key_extra_name) != null) {
            sb.append("$")
                    .append(view.getTag(R.id.key_extra_name));
            ViewGroup viewGroup = (ViewGroup) view.getParent();

            if (viewGroup instanceof ViewPager) {
                sb.append(":");
                sb.append(((ViewPager) viewGroup).getCurrentItem());
            }
        } else {
            if (view.getId() != View.NO_ID) {
                String idName;
                try {
                    idName = view.getResources().getResourceEntryName(view.getId());
                } catch (Resources.NotFoundException exception) {
                    idName = null;
                }

                if (!TextUtils.isEmpty(idName)) {
                    appendIDView(sb, view, idName);

                    if (view.getId() == androidx.appcompat.R.id.parentPanel) {
                        TextView message = view.findViewById(android.R.id.message);
                        if (message != null) {
                            String msgStr = message.getText().toString();
                            if (msgStr.length() > 10) {
                                msgStr = msgStr.substring(0, 10);
                            }
                            sb.append(msgStr);
                        }
                    }

                } else {
                    appendNoIDView(sb, view);
                }

            } else {
                appendNoIDView(sb, view);
            }

        }
    }

    private void appendIDView(StringBuilder sb, View view, String idName) {
        sb.append("$");
        sb.append(view.getClass().getSimpleName())
                .append(":")
                .append(idName);
    }

    private void appendNoIDView(StringBuilder sb, View view) {
        sb.append("$");
        sb.append(view.getClass().getSimpleName());
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (Track.isChildNeedIndex(viewGroup) || view.getClass().getName().equals("com.google.android.material.tabs.TabLayout$TabView")) {
            sb.append(":");
            if (viewGroup instanceof RecyclerView) {
                sb.append(((RecyclerView) viewGroup).getChildAdapterPosition(view));
            } else if (viewGroup instanceof AdapterView) {
                sb.append(((AdapterView) viewGroup).getPositionForView(view));
            } else {
                sb.append(getSameIndex(view, viewGroup));
            }
        }
    }


    /**
     * 优化index，View相对于同层级的相同类型的view来说排在第几个；
     *
     * @param view
     * @param viewGroup
     * @return
     */
    private int getSameIndex(View view, ViewGroup viewGroup) {
        int index = 0;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View cView = viewGroup.getChildAt(i);
            if (view.getClass().equals(cView.getClass())) {
                if (view.equals(cView)) {
                    return index;
                }
                index++;
            }
        }
        return index;
    }

}
