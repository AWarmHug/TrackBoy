package com.warm.demo.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.warm.demo.R;
import com.warm.demo.base.BaseFragment;
import com.warm.demo.databinding.FragmentMainBinding;
import com.warm.demo.detail.DetailActivity;
import com.warm.track.annotation.Event;

public class XMLViewFragment extends BaseFragment {
    private FragmentMainBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.bt.setOnClickListener(v -> Toast.makeText(getContext(), "按钮", Toast.LENGTH_SHORT).show());

        mBinding.tv.setOnClickListener(new View.OnClickListener() {

            @Event(eventId = "eventId", value = "点了")
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "弹一下", Toast.LENGTH_SHORT).show();
            }
        });
        mBinding.tvDialog.setOnClickListener(v -> new AlertDialog.Builder(getContext())
                .setTitle("温馨提示")
                .setMessage("确定删除该好友吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show());

        mBinding.cbAddress.setOnCheckedChangeListener((buttonView, isChecked) -> Toast.makeText(getContext(), isChecked ? "选中" : "未选中", Toast.LENGTH_SHORT).show());

        mBinding.edName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Toast.makeText(getContext(), hasFocus ? "焦點" : "无焦点", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.tvTitle1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        mBinding.btGoDetail.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            startActivity(intent);
        });

        mBinding.applyLayout.setOnAppleClickListener(view1 -> {
        });
        mBinding.applyLayout.setOnClickListener(v -> {
        });

    }


    @Nullable
    @Override
    public CharSequence getTitle() {
        return "XMLView";
    }
}
