package com.warm.demo.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.warm.demo.R;

public class BaseFragment extends Fragment {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setTag(R.id.key_extra_name,getClass().getSimpleName());
    }

    @Nullable
    public CharSequence getTitle(){
        return null;
    }
}
