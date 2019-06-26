package com.warm.demo.main;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentPagerAdapter;

import com.warm.demo.R;
import com.warm.demo.base.BaseActivity;
import com.warm.demo.databinding.ActivityMainBinding;

import java.util.Date;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.POSITION_NONE));
        mBinding.tab.setupWithViewPager(mBinding.pager);
    }

}
