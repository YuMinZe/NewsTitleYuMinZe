package com.bawei.test.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.test.R;
import com.bawei.test.bean.DataBeansp;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/21 20:32
 */
public class ItemLayoutsp extends BaseAdapter{
    private Context context;
    private ArrayList<DataBeansp> list = new ArrayList<>();
    public ItemLayoutsp(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        if(convertView==null){
            holder = new Viewholder();
            convertView = View.inflate(context, R.layout.act_video,null);
            holder.jCVideoPlayer = (JCVideoPlayer)convertView.findViewById(R.id.nei_fragment2_video);

            holder.tv1 = (TextView) convertView.findViewById(R.id.nei_video_tv1);
            holder.tv2 = (TextView)convertView.findViewById(R.id.nei_video_tv2);
            convertView.setTag(holder);
        }else{
            holder = (Viewholder) convertView.getTag();
        }


        holder.jCVideoPlayer.setUp(list.get(position).getMp4_url(),list.get(position).getTitle());


        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
        holder.jCVideoPlayer.ivThumb.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.getInstance().displayImage(list.get(position).getCover(),holder.jCVideoPlayer.ivThumb,options);

        holder.tv1.setText(list.get(position).getTopicName());
        holder.tv2.setText("评论:0");
        return convertView;
    }

    public void setData(ArrayList<DataBeansp> list2, boolean isNeeds) {
        if(list2!=null){
            if(isNeeds){
                list.clear();
            }
            list.addAll(list2);
        }
    }

    static class Viewholder{
        private JCVideoPlayer jCVideoPlayer;
        private TextView tv1;
        private TextView tv2;
    }
}
