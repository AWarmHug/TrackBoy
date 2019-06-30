package com.warm.demo.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.warm.demo.R;
import com.warm.demo.base.BaseFragment;
import com.warm.demo.databinding.FragmentMineBinding;

public class CustomFragment extends BaseFragment {
    private FragmentMineBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), mBinding.title.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public CharSequence getTitle() {
        return "自定义控件";
    }
}
