package com.warm.demo.track;

import android.util.Log;
import android.view.View;

import com.warm.demo.data.Data;
import com.warm.track.ViewFinder;

/**
 * 作者：warm
 * 时间：2019-06-29 00:43
 * 描述：
 */
public class DefaultViewFinder extends ViewFinder<ViewTrace> {

    @Override
    public ViewTrace find(View view) {
        return new ViewTrace(view.getContext(), Data.getEvent(getName(view)));
    }

}
