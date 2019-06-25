package com.warm.demo.detail;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.warm.demo.R;
import com.warm.demo.base.BaseActivity;
import com.warm.demo.databinding.ActivityDetailBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity {
    private ActivityDetailBinding mBinding;

    private DetailListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_detail);
        List<String> nameList=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            nameList.add("张三"+i);
        }
        mAdapter=new DetailListAdapter(nameList);
        mBinding.list.setAdapter(mAdapter);
        mBinding.list.setLayoutManager(new LinearLayoutManager(this));

    }

}
