package com.warm.demo.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.warm.demo.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter {
    List<BaseFragment> mFragments=new ArrayList<>();

    public MainPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mFragments.add(new XMLViewFragment());
        mFragments.add(new AddViewFragment());
        mFragments.add(new RecyclerViewFragment());
        mFragments.add(new CustomFragment());
        mFragments.add(new CustomFragment());
        mFragments.add(new CustomFragment());
        mFragments.add(new CustomFragment());

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
