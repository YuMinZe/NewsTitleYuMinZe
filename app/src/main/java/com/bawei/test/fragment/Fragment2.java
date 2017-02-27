package com.bawei.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.test.R;
import com.bawei.test.adapter.Fragment2_adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/10 09:35
 */
public class Fragment2 extends Fragment {

    private View view;
    private TabLayout tab;
    private ViewPager vp;
    String[] title = {"热点视频","娱乐视频","搞笑视频","精品视频"};
    String[] title_id = {"V9LG4B3A0","V9LG4CHOR","V9LG4E6VR","00850FRB"};
    private List<Fragment> list;
    private Fragment2_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment2,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tab = (TabLayout) view.findViewById(R.id.nei_fragment2_tab);
        vp = (ViewPager) view.findViewById(R.id.nei_fragment2_vp);
        initdata();
        adapter = new Fragment2_adapter(getActivity().getSupportFragmentManager(),getActivity(),title);
        adapter.setlist(list);
        vp.setAdapter(adapter);
        //关联viewpager
        tab.setupWithViewPager(vp);
        //tablayout的模式
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);


    }

    private void initdata() {
        list = new ArrayList<Fragment>();
        //根据title的大小  创建fragment  并赋值给list
        for (int i=0;i<title.length;i++){

            Bundle bun = new Bundle();
            Fragment2_neirong fragment = new Fragment2_neirong();
            bun.putString("flag2",title_id[i]);
            fragment.setArguments(bun);
            list.add(fragment);
        }
    }
}
