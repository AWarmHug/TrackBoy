package com.warm.demo.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.warm.demo.R;
import com.warm.demo.base.BaseFragment;
import com.warm.demo.databinding.FragmentMainBinding;
import com.warm.demo.detail.DetailActivity;
import com.warm.library_aspectj.annotation.Event;

public class HomeFragment extends BaseFragment {
    private FragmentMainBinding mBinding;

    private AlertDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = DataBindingUtil.bind(view);

        dialog = new AlertDialog.Builder(getContext())
                .setTitle("标题")
                .setMessage("这是内容")
                .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "灌灌灌灌", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        Log.d("Trace", "onCreate: " + mBinding.kktitle.toString());
        mBinding.kktitle.setOnClickListener(v -> Toast.makeText(getContext(), "标题", Toast.LENGTH_SHORT).show());
//        mBinding.kktitle.setOnLongClickListener(v -> {
//            Log.d(TAG, "onCreate: sss");
//        });
        //        TrackView.bind(mBinding.tv, "eventId", "点击", "弹屏");
        mBinding.tv.setOnClickListener(new View.OnClickListener() {

            @Event(eventId = "eventId", value = "点了")
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "弹一下", Toast.LENGTH_SHORT).show();
            }
        });
        mBinding.tvDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

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
                Toast.makeText(getContext(), "hhhh", Toast.LENGTH_SHORT).show();

            }
        });

        mBinding.btGoDetail.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            startActivity(intent);
        });
        mBinding.btThis.setOnClickListener(this::onBtThisClick);
        Button bt = new Button(getContext());
        bt.setText("痛痛痛");
        bt.setOnClickListener(v -> Toast.makeText(getContext(), "痛痛痛", Toast.LENGTH_SHORT).show());
        mBinding.line.addView(bt);

        mBinding.applyLayout.setOnAppleClickListener(view1 -> {
            Toast.makeText(getContext(), "hhhh", Toast.LENGTH_SHORT).show();
        });

    }

    public void onBtThisClick(View view) {
        Toast.makeText(getContext(), "onBtThisClick", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public CharSequence getTitle() {
        return "主页";
    }
}
