package com.warm.demo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.warm.demo.databinding.ActivityBinding;
import com.warm.someaop.annotation.Event;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityBinding mBinding;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity);

        dialog = new AlertDialog.Builder(this)
                .setTitle("标题")
                .setMessage("这是内容")
                .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "灌灌灌灌", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        Log.d("Trace", "onCreate: " + mBinding.kktitle.toString());
        mBinding.kktitle.setOnClickListener(v -> Toast.makeText(MainActivity.this, "标题", Toast.LENGTH_SHORT).show());
//        TrackView.bind(mBinding.tv, "eventId", "点击", "弹屏");
        mBinding.tv.setOnClickListener(new View.OnClickListener() {

            @Event(eventId = "eventId", value = "点了")
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "弹一下", Toast.LENGTH_SHORT).show();
            }
        });
        mBinding.tvDialog.setOnClickListener(this);

        mBinding.cbAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, isChecked ? "选中" : "未选中", Toast.LENGTH_SHORT).show();

            }
        });

        mBinding.edName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Toast.makeText(MainActivity.this, hasFocus ? "焦點" : "无焦点", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tvTitle1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "hhhh", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onClick(View v) {
        dialog.show();

    }
}
