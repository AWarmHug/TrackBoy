package com.warm.library_plugin;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.LayoutInflaterFactory;

import com.warm.library_plugin.widget.TTextView;

public class TAppCompatViewInflater extends AppCompatViewInflater {

    @NonNull
    @Override
    protected AppCompatTextView createTextView(Context context, AttributeSet attrs) {
        return new TTextView(context, attrs);
    }

    @Nullable
    @Override
    protected View createView(Context context, String name, AttributeSet attrs) {
        Log.d("*****************", "createView: ");
        return super.createView(context, name, attrs);
    }
}
