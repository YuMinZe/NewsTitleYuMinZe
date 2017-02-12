package com.bawei.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.test.R;
import com.bawei.test.adapter.Fragment1_adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/10 09:35
 */
public class Fragment1 extends Fragment {

    private View view;
    private TabLayout tab;
    private ViewPager vp;
    String[] title = {"推荐","社会","图片","财经","游戏","美女","养生","电影","北京","娱乐","科技","问答","汽车"};
    private List<Fragment> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment1,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tab = (TabLayout) view.findViewById(R.id.nei_fragment1_tab);
        vp = (ViewPager) view.findViewById(R.id.nei_fragmen1_vp);
        initdata();
        //实例化   传值
        Fragment1_adapter adapter = new Fragment1_adapter(getActivity().getSupportFragmentManager(),getActivity(),title);
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
            Fragment1_neirong fragment = new Fragment1_neirong();
            list.add(fragment);
        }
    }
}
