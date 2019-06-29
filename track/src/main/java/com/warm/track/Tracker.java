package com.warm.track;

import androidx.annotation.Nullable;

public interface Tracker<T> {
    void track(@Nullable T t);

    void track(@Nullable T t, boolean checked);

}
