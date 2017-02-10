package com.bawei.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;

import com.bawei.test.R;

/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/10 19:41
 */
public class MainActivity_yindao extends Activity{
    private Handler han = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent in = new Intent(MainActivity_yindao.this,MainActivity_shouye.class);
            startActivity(in);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yindao);
        han.sendEmptyMessageDelayed(0,2000);
    }
}
