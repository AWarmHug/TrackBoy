package com.warm.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.warm.demo.R;
import com.warm.demo.databinding.ItemBinding;

/**
 * 作者：warm
 * 时间：2019-06-03 19:20
 * 描述：
 */
public class ItemLayout extends LinearLayout {
    public ItemBinding mBinding;

    public ItemLayout(Context context) {
        this(context, null);
    }

    public ItemLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemLayout(final Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item, this, true);
        mBinding.tvTitle1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "哈哈哈哈", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
