package com.bawei.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.test.R;
import com.bawei.test.activity.MainWebview;
import com.bawei.test.adapter.ItemLayout;
import com.bawei.test.bean.DataBean;
import com.bawei.test.http.CallbackNewsData;
import com.bawei.test.http.HttpUtils;
import com.bawei.test.http.JudgeMesh;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/12 18:53
 */
public class Fragment1_neirong extends Fragment implements PullToRefreshListView.OnRefreshListener2<ListView>, CallbackNewsData<DataBean>{

    private View view;
    private int num = 0;
    private ItemLayout adapter;
    private PullToRefreshListView pulllistview;
    private boolean isNeed ;
    private String url;
    private String flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1_neirong, null, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initdata();
        if(JudgeMesh.detect(getActivity())==true){
            initview();
        }else{
            Toast.makeText(getActivity(),"没有可用网络",Toast.LENGTH_LONG).show();
        }

        Bundle arguments = getArguments();
        flag = arguments.getString("flag");

        url = "http://c.m.163.com/nc/article/headline/"+ flag +"/"+num+"-20.html";


        HttpUtils.loadDataFromServer(url,DataBean.class,this);

    }

    private void initdata() {
        pulllistview = (PullToRefreshListView) view.findViewById(R.id.item_pullListView);
        pulllistview.setMode(PullToRefreshBase.Mode.BOTH);
        pulllistview.setOnRefreshListener(this);


    }

    private void initview() {
        adapter = new ItemLayout(getActivity());
        pulllistview.setAdapter(adapter);
    }

    @Override
    public void success(ArrayList<DataBean> newsContents) {
        adapter.setlist(newsContents,isNeed);
        adapter.notifyDataSetChanged();
        pulllistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getActivity(), MainWebview.class);
                in.putExtra("weburl",adapter.getItem(position-1).getUrl());
                startActivity(in);
            }
        });
        pulllistview.onRefreshComplete();

    }
    //xia
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

        isNeed=true;
        num+=5;
        url = "http://c.m.163.com/nc/article/headline/"+flag+"/"+num+"-20.html";
        HttpUtils.loadDataFromServer(url,DataBean.class,this);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        isNeed=false;
        num+=5;
        url = "http://c.m.163.com/nc/article/headline/"+flag+"/"+num+"-20.html";
        HttpUtils.loadDataFromServer(url,DataBean.class,this);
    }
}
