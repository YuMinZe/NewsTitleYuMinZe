package com.bawei.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.test.R;

/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/12 18:53
 */
public class Fragment1_neirong extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_neirong, null, false);
        return view;
    }
}
