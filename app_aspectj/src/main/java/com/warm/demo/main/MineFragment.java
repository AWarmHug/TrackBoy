package com.warm.demo.main;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.color.MaterialColors;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapePath;
import com.google.android.material.shape.ShapePathModel;
import com.google.android.material.shape.TriangleEdgeTreatment;
import com.warm.demo.R;
import com.warm.demo.base.BaseFragment;
import com.warm.demo.databinding.FragmentMainBinding;
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
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        materialShapeDrawable.initializeElevationOverlay(getContext());
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(ContextCompat.getColor(getContext(),R.color.colorPrimary)));
//        materialShapeDrawable.setShadowColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
        materialShapeDrawable.setElevation(20);

        ViewCompat.setBackground(mBinding.bt,materialShapeDrawable);
    }

    @Nullable
    @Override
    public CharSequence getTitle() {
        return "我的";
    }
}
