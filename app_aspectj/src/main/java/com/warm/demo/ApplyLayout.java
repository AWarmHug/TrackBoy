package com.warm.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class ApplyLayout extends LinearLayout {
    private EditText etInfo;
    private Button btApply;

    private OnAppleClickListener mOnAppleClickListener;


    public void setOnAppleClickListener(OnAppleClickListener onAppleClickListener) {
        mOnAppleClickListener = onAppleClickListener;
    }

    public ApplyLayout(Context context) {
        this(context, null);
    }

    public ApplyLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ApplyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.select_item_layout, this);
        etInfo = findViewById(R.id.etInfo);
        btApply = findViewById(R.id.btApply);
        btApply.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnAppleClickListener!=null){
                    mOnAppleClickListener.onAppleClick(v);
                }
            }
        });
    }




    public interface OnAppleClickListener{
        void onAppleClick(View view);
    }

}
