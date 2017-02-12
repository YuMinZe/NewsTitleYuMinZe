package com.bawei.test.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/12 18:56
 */
public class Fragment1_adapter extends FragmentPagerAdapter {
    private  Context context;
    private  String[] title;
    private List<Fragment> list;
    //接收  传值
    public Fragment1_adapter(FragmentManager fm, Context context, String[] title) {
        super(fm);
        this.context = context;
        this.title = title;
    }
    public void setlist(List<Fragment> list){
        this.list = list;
    }
    //viewpager与title关联
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
    //适配
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
    //根据list 中fragment数目  判断viewpager的大小
    @Override
    public int getCount() {
        return list.size();
    }
}
