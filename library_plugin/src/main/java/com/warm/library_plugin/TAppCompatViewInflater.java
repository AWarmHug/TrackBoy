package com.warm.library_plugin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import com.warm.library_plugin.widget.TAbsoluteLayout;
import com.warm.library_plugin.widget.TAutoCompleteTextView;
import com.warm.library_plugin.widget.TButton;
import com.warm.library_plugin.widget.TCheckBox;
import com.warm.library_plugin.widget.TCheckedTextView;
import com.warm.library_plugin.widget.TEditText;
import com.warm.library_plugin.widget.TFrameLayout;
import com.warm.library_plugin.widget.TImageButton;
import com.warm.library_plugin.widget.TImageView;
import com.warm.library_plugin.widget.TLinearLayout;
import com.warm.library_plugin.widget.TMultiAutoCompleteTextView;
import com.warm.library_plugin.widget.TRadioButton;
import com.warm.library_plugin.widget.TRatingBar;
import com.warm.library_plugin.widget.TRelativeLayout;
import com.warm.library_plugin.widget.TScrollView;
import com.warm.library_plugin.widget.TSeekBar;
import com.warm.library_plugin.widget.TSpinner;
import com.warm.library_plugin.widget.TTableLayout;
import com.warm.library_plugin.widget.TTextView;

public class TAppCompatViewInflater extends AppCompatViewInflater {

    @Nullable
    @Override
    protected View createView(Context context, String name, AttributeSet attrs) {

        switch (name) {
            case "LinearLayout":
                return new TLinearLayout(context, attrs);
            case "FrameLayout":
                return new TFrameLayout(context, attrs);
            case "TableLayout":
                return new TTableLayout(context, attrs);
            case "RelativeLayout":
                return new TRelativeLayout(context, attrs);
            case "TScrollView":
                return new TScrollView(context, attrs);
            case "AbsoluteLayout":
                return new TAbsoluteLayout(context, attrs);
            default:
                return null;
        }

    }
}
