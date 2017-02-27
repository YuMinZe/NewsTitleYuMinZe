package com.bawei.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.test.R;
import com.bawei.test.adapter.ItemLayoutsp;
import com.bawei.test.bean.DataBean;
import com.bawei.test.bean.DataBeansp;
import com.bawei.test.http.CallbackNewsData;
import com.bawei.test.http.HttpUtils;
import com.bawei.test.http.JudgeMesh;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/21 20:23
 */
public class Fragment2_neirong extends Fragment implements PullToRefreshListView.OnRefreshListener2<ListView>, CallbackNewsData<DataBeansp>{
    private View view;
    private PullToRefreshListView pulllistview;
    private String flag;
    private int num=10;
    private String url;
    private ItemLayoutsp adapter;
    private boolean isNeeds;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2_neirong, null, false);

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
        flag = arguments.getString("flag2");

        url = "http://c.3g.163.com/nc/video/list/"+flag+"/n/"+num+"-10.html";

        HttpUtils.loadDataFromServer(url,DataBeansp.class,this);
    }

    private void initview() {
        adapter = new ItemLayoutsp(getActivity());
        pulllistview.setAdapter(adapter);
    }
    @Override
    public void success(ArrayList<DataBeansp> newsContents) {
        adapter.setData(newsContents,isNeeds);
        adapter.notifyDataSetChanged();
        pulllistview.onRefreshComplete();
    }

    private void initdata() {
        pulllistview = (PullToRefreshListView) view.findViewById(R.id.fragment2_pullListView);
        pulllistview.setMode(PullToRefreshBase.Mode.BOTH);
        pulllistview.setOnRefreshListener(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        isNeeds=true;
        num+=10;
        url = "http://c.3g.163.com/nc/video/list/"+flag+"/n/"+num+"-10.html";

        HttpUtils.loadDataFromServer(url,DataBeansp.class,this);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        isNeeds=false;
        num+=10;
        url = "http://c.3g.163.com/nc/video/list/"+flag+"/n/"+num+"-10.html";

        HttpUtils.loadDataFromServer(url,DataBeansp.class,this);
    }
}
