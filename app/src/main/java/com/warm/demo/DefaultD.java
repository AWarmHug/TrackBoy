package com.warm.demo;

import com.warm.demo.data.Data;
import com.warm.demo.data.Trace;
import com.warm.track.D;

/**
 * 作者：warm
 * 时间：2019-06-29 00:43
 * 描述：
 */
public class DefaultD extends D<Trace> {
    @Override
    public Trace find(String name) {
        return Data.getEvent(name);
    }
}
