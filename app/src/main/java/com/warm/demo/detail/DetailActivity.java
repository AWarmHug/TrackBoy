package com.warm.demo.detail;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.warm.demo.R;
import com.warm.demo.base.BaseActivity;
import com.warm.demo.databinding.ActivityDetailBinding;
import com.warm.track.TrackExtraName;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity implements TrackExtraName {
    public static final String KEY_TYPE = "TYPE";

    public static final int TYPE_TICKET = 1;
    public static final int TYPE_BUS = 2;

    private ActivityDetailBinding mBinding;

    private DetailListAdapter mAdapter;

    private int mType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getIntent().getIntExtra(KEY_TYPE, TYPE_TICKET);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            nameList.add("张三" + i);
        }
        mAdapter = new DetailListAdapter(nameList);
        mBinding.list.setAdapter(mAdapter);
        mBinding.list.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        switch (mType) {
            case TYPE_TICKET:
                setTitle("门票详情");
                break;
            case TYPE_BUS:
                setTitle("车票详情");
                break;
        }

    }

    @Override
    public String getExtraName() {
        return String.valueOf(mType);
    }
}
