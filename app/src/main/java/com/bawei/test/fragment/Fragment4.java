package com.bawei.test.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.test.R;
import com.bawei.test.activity.MainActivity_shouye;


/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/10 09:35
 */
public class Fragment4 extends Fragment {
    private View view;
    private LinearLayout ll;
    MainActivity_shouye activity = (MainActivity_shouye) getActivity();
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private ImageView iv_photo;
    private TextView tv;
    private boolean mode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment4, null);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sp = getActivity().getSharedPreferences("xiaoyu",Context.MODE_PRIVATE);
        edit = sp.edit();
        ll = (LinearLayout) view.findViewById(R.id.nei_fragment4_yj);
        iv_photo = (ImageView) view.findViewById(R.id.nei_fragment4_photo);
        tv = (TextView) view.findViewById(R.id.nei_fragment4_tv);
        mode = sp.getBoolean("mode", true);
        if(mode == true){
            iv_photo.setImageResource(R.drawable.wai_wode_yj);
            tv.setText("夜间");
        }else{
            iv_photo.setImageResource(R.drawable.wai_wode_rj);
            tv.setText("日间");
        }

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mode == true){

                    edit.putBoolean("mode",false).commit();
                }else{

                    edit.putBoolean("mode",true).commit();
                }
                int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                ((MainActivity_shouye)getActivity()).getDelegate().setLocalNightMode(currentNightMode == Configuration.UI_MODE_NIGHT_NO
                        ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                // 同样需要调用recreate方法使之生效
                recreate();

            }

            private void recreate() {

            }
        });
    }
}
