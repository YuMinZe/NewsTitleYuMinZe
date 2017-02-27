package com.bawei.test.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bawei.test.R;
import com.bawei.test.bean.DataBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/17 15:05
 */
public class ItemLayout extends BaseAdapter {
    private Context context;
    private ArrayList<DataBean> list = new ArrayList<>();
    private final int ITEM_TYPE1 = 0;
    private final int ITEM_TYPE2 = 1;

    public ItemLayout(Context contex) {
        this.context = contex;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ViewHolder2 holder2 = null;
        if(convertView==null){
            int type = getItemViewType(position);
            switch (type){
                case ITEM_TYPE1:
                    holder = new ViewHolder();
                    convertView = View.inflate(context, R.layout.act_duotiaomu1,null);
                    holder.item1_tv1 = (TextView) convertView.findViewById(R.id.item1_tv1);
                    holder.item1_tv2 = (TextView) convertView.findViewById(R.id.item1_tv2);
                    holder.item1_tv3 = (TextView) convertView.findViewById(R.id.item1_tv3);
                    holder.item1_iv = (ImageView) convertView.findViewById(R.id.item1_iv);
                    convertView.setTag(holder);
                    break;

                case ITEM_TYPE2:
                    holder2 = new ViewHolder2();
                    convertView = View.inflate(context, R.layout.act_duotiaomu2,null);
                    holder2.item2_tv1 = (TextView) convertView.findViewById(R.id.item2_tv1);
                    holder2.item2_tv2 = (TextView) convertView.findViewById(R.id.item2_tv2);
                    holder2.item2_tv3 = (TextView) convertView.findViewById(R.id.item2_tv3);
                    holder2.item2_iv1 = (ImageView) convertView.findViewById(R.id.item2_iv1);
                    holder2.item2_iv2 = (ImageView) convertView.findViewById(R.id.item2_iv2);
                    holder2.item2_iv3 = (ImageView) convertView.findViewById(R.id.item2_iv3);
                    convertView.setTag(holder2);
                    break;
            }
        }else{
            int type = getItemViewType(position);
            switch (type) {
                case ITEM_TYPE1:
                    holder = (ViewHolder) convertView.getTag();
                    break;
                case ITEM_TYPE2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
            }

        }

        int type = getItemViewType(position);
        switch (type) {
            case ITEM_TYPE1:
                holder.item1_tv1.setText(list.get(position).getTitle());
                holder.item1_tv2.setText(list.get(position).getSource()+"        ");
                holder.item1_tv3.setText(list.get(position).getPtime());
                ImageLoader.getInstance().displayImage(list.get(position).getImgsrc(),holder.item1_iv);
                break;
            case ITEM_TYPE2:
                holder2.item2_tv1.setText(list.get(position).getTitle());
                holder2.item2_tv2.setText(list.get(position).getSource()+"        ");
                holder2.item2_tv3.setText(list.get(position).getPtime());
                ImageLoader.getInstance().displayImage(list.get(position).getImgextra().get(0).getImgsrc(),holder2.item2_iv1);
                ImageLoader.getInstance().displayImage(list.get(position).getImgextra().get(1).getImgsrc(),holder2.item2_iv2);
                ImageLoader.getInstance().displayImage(list.get(position).getImgsrc(),holder2.item2_iv3);

                break;
        }


        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        DataBean item = getItem(position);
        if (item.getImgextra()!=null && item.getImgextra().size() > 0) {
            return ITEM_TYPE2;
        } else {
            return ITEM_TYPE1;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    public void setlist(List<DataBean> data, boolean isNeed) {
        if(data!=null){
            if(isNeed){
                list.clear();
            }
            list.addAll(data);
        }
    }

    static class ViewHolder{
        TextView item1_tv1;
        TextView item1_tv2;
        TextView item1_tv3;
        ImageView item1_iv;
    }

    static class ViewHolder2{
        TextView item2_tv1;
        TextView item2_tv2;
        TextView item2_tv3;
        ImageView item2_iv1;
        ImageView item2_iv2;
        ImageView item2_iv3;

    }
}
