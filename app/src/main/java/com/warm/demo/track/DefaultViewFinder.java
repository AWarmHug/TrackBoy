package com.warm.demo.track;

import com.warm.demo.data.Data;
import com.warm.track.ViewFinder;

/**
 * 作者：warm
 * 时间：2019-06-29 00:43
 * 描述：
 */
public class DefaultViewFinder extends ViewFinder<Trace> {
    @Override
    protected Trace find(String name) {
        return Data.getEvent(name);
    }
}
