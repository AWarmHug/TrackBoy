package com.warm.demo.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.warm.demo.R;
import com.warm.demo.base.BaseFragment;
import com.warm.demo.databinding.FragmentAddViewBinding;

/**
 * 作者：warm
 * 时间：2019-06-28 21:29
 * 描述：
 */
public class AddViewFragment extends BaseFragment {
    private FragmentAddViewBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_view, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button bt = new Button(getContext());
        bt.setText("按钮");
        bt.setOnClickListener(v -> Toast.makeText(getContext(), bt.getText(), Toast.LENGTH_SHORT).show());
        mBinding.line.addView(bt);


        TextView tv = new TextView(getContext());
        tv.setText("文字");
        mBinding.line.addView(tv);


        CheckBox cb = new CheckBox(getContext());
        cb.setText("选择框");
        cb.setOnClickListener(v -> Toast.makeText(getContext(), cb.getText(), Toast.LENGTH_SHORT).show());
        mBinding.line.addView(cb);


    }

    @Nullable
    @Override
    public CharSequence getTitle() {
        return "代码添加View";
    }
}
