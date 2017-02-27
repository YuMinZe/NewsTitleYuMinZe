package com.bawei.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.test.R;
import com.bawei.test.activity.MainTitle;
import com.bawei.test.activity.Mainpindao;
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
    String[] title = {"推荐","足球","娱乐","体育","财经","科技","电影","汽车","笑话","游戏","时尚","情感","精选","电台","NBA","数码","移动","彩票","教育","论坛","旅游","手机","博客","社会","家居","暴雪游戏","亲子","CBA","消息","军事"};
    String[] title_id = {"T1348647909107","T1399700447917","T1348648517839","T1348649079062","T1348648756099","T1348649580692","T1348648650048","T1348654060988","T1350383429665","T1348654151579","T1348650593803","T1348650839000","T1370583240249","T1379038288239","T1348649145984","T1348649776727","T1351233117091","T1356600029035","T1348654225495","T1349837670307","T1348654204705","T1348649654285","T1349837698345","T1348648037603","T1348654105308","T1397016069906","T1397116135282","T1348649475931","T1371543208049","T1348648141035"};
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
        ImageView iv = (ImageView) view.findViewById(R.id.wai_title_iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), MainTitle.class);

                startActivity(in);
            }
        });

        ImageView imageView = (ImageView) view.findViewById(R.id.nei_fragment1_pindao);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Mainpindao.class);

                startActivity(in);
            }
        });

    }

    private void initdata() {
        list = new ArrayList<Fragment>();
        //根据title的大小  创建fragment  并赋值给list
        for (int i=0;i<title.length;i++){

            Bundle bun = new Bundle();
            Fragment1_neirong fragment = new Fragment1_neirong();
            bun.putString("flag",title_id[i]);
            fragment.setArguments(bun);
            list.add(fragment);
        }
    }
}
