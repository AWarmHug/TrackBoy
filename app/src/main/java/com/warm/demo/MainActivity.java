package com.warm.demo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.warm.demo.databinding.ActivityMainBinding;
import com.warm.someaop.TrackView;
import com.warm.someaop.annotation.Event;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        TrackView.bind(mBinding.tv,"eventId","点击","弹屏");
        mBinding.tv.setOnClickListener(new View.OnClickListener() {
            @Event(eventId = "123",value = "点击")
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "弹一下", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
