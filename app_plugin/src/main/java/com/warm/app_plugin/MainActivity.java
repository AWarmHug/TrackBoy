package com.warm.app_plugin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.warm.app_plugin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("**************", "onClick: ssss");
            }
        });
        mBinding.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("**************", "onClick: ssss");
            }
        });
        for (int i = 0; i < 5; i++) {
            AppCompatButton button=new AppCompatButton(this);
            button.setText("这是"+i);
            button.setOnClickListener(v -> {
                Log.d("**************", "这是");
            });
            mBinding.line.addView(button);
        }



    }

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        return super.getDelegate();
    }
}
