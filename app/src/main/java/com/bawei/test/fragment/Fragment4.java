package com.bawei.test.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.test.R;
import com.bawei.test.activity.MainActivity_shouye;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;


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
    private ImageView iv;

    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mInfo;
    private String APPID="1105692541";

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
        mTencent = Tencent.createInstance(APPID,getActivity().getApplication());
        iv = (ImageView) view.findViewById(R.id.fragment4_qq);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                mTencent.login(getActivity(),"all", mIUiListener);
            }
        });
    }

    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
            Log.e("tag", "response:" + response);
            JSONObject jo = (JSONObject) response;

            try {
                String openID = jo.getString("openid");
                String accessToken = jo.getString("access_token");
                String expires = jo.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mInfo = new UserInfo(getActivity(), qqToken);
                mInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e("BaseUiListener", "成功"+response.toString());
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e("BaseUiListener", "失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e("BaseUiListener", "取消");
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(getActivity(), "登录取消", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TAG", "-->onActivityResult " + requestCode  + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
