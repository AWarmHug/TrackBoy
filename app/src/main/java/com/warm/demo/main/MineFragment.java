package com.warm.demo.main;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.shape.MaterialShapeDrawable;
import com.warm.demo.R;
import com.warm.demo.base.BaseFragment;
import com.warm.demo.databinding.FragmentMineBinding;

public class MineFragment extends BaseFragment {
    private FragmentMineBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Drawable background = mBinding.tv1.getBackground();
        if (background != null && !(background instanceof ColorDrawable)) {
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        int backgroundColor =
                background != null ? ((ColorDrawable) background).getColor() : Color.TRANSPARENT;
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(backgroundColor));
        materialShapeDrawable.initializeElevationOverlay(getContext());
        materialShapeDrawable.setElevation(30);
        ViewCompat.setBackground(mBinding.tv1,materialShapeDrawable);
        mBinding.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), mBinding.tv1.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public CharSequence getTitle() {
        return "我的";
    }
}
