package com.warm.demo.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.warm.demo.R;
import com.warm.demo.base.BaseFragment;
import com.warm.demo.databinding.FragmentMallBinding;
import com.warm.demo.detail.DetailListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MallFragment extends BaseFragment {
    private FragmentMallBinding mBinding;
    private DetailListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mall,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> nameList=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            nameList.add("张三"+i);
        }
        mAdapter=new DetailListAdapter(nameList);
        mBinding.list.setAdapter(mAdapter);
        mBinding.list.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public CharSequence getTitle() {
        return "商场";
    }
}
