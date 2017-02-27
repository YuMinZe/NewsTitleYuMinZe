package com.bawei.test.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bawei.test.Application.MyApplication;
import com.bawei.test.R;
import com.bawei.test.fragment.Fragment1;
import com.bawei.test.fragment.Fragment2;
import com.bawei.test.fragment.Fragment3;
import com.bawei.test.fragment.Fragment4;

/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/10 19:41
 */
public class MainActivity_shouye extends AppCompatActivity implements View.OnClickListener {
    private long exitTime = 0;
    private RadioGroup rg;
    private FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private LinearLayout wai_shouye_ll;
    private LinearLayout wai_shipin_ll;
    private LinearLayout wai_guanzhu_ll;
    private LinearLayout wai_wode_ll;
    private ImageView wai_shouye_iv;
    private ImageView wai_shipin_iv;
    private ImageView wai_guanzhu_iv;
    private ImageView wai_wode_iv;
    private Button wai_shouye;
    private Button wai_shipin;
    private Button wai_guanzhu;
    private Button wai_wode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouye);

        fragmentManager = getSupportFragmentManager();

        //找到控件
        wai_shouye_ll = (LinearLayout) findViewById(R.id.wai_shouye_ll);
        wai_shipin_ll = (LinearLayout) findViewById(R.id.wai_shipin_ll);
        wai_guanzhu_ll = (LinearLayout) findViewById(R.id.wai_guanzhu_ll);
        wai_wode_ll = (LinearLayout) findViewById(R.id.wai_wode_ll);

        wai_shouye_iv = (ImageView) findViewById(R.id.wai_shouye_iv);
        wai_shipin_iv = (ImageView) findViewById(R.id.wai_shipin_iv);
        wai_guanzhu_iv = (ImageView) findViewById(R.id.wai_guanzhu_iv);
        wai_wode_iv = (ImageView) findViewById(R.id.wai_wode_iv);

        wai_shouye = (Button) findViewById(R.id.wai_shouye);
        //给初始颜色
        wai_shouye.setTextColor(Color.RED);
        wai_shipin = (Button) findViewById(R.id.wai_shipin);
        wai_guanzhu = (Button) findViewById(R.id.wai_guanzhu);
        wai_wode = (Button) findViewById(R.id.wai_wode);

        wai_shouye_ll.setOnClickListener(this);
        wai_shipin_ll.setOnClickListener(this);
        wai_guanzhu_ll.setOnClickListener(this);
        wai_wode_ll.setOnClickListener(this);

        if(MyApplication.isFalg){
            //初始值
            Fragment1 fragment1 = new Fragment1();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.wai_fm,fragment1).commit();
            wai_shouye_iv.setImageResource(R.drawable.wai_yesshouye);
            wai_shouye.setTextColor(Color.RED);

            wai_wode_iv.setImageResource(R.drawable.wai_nowode);
            wai_wode.setTextColor(Color.BLACK);
            MyApplication.isFalg=false;
        }else {
            wai_wode_iv.setImageResource(R.drawable.wai_yeswode);
            wai_wode.setTextColor(Color.RED);
            wai_shouye_iv.setImageResource(R.drawable.wai_noshouye);
            wai_shouye.setTextColor(Color.BLACK);
        }


    }



    //两次退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wai_shouye_ll:
                Fragment1 fragment1 = new Fragment1();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.wai_fm,fragment1).commit();

                wai_shouye_iv.setImageResource(R.drawable.wai_yesshouye);
                wai_shipin_iv.setImageResource(R.drawable.wai_noshipin);
                wai_guanzhu_iv.setImageResource(R.drawable.wai_noguanzhu);
                wai_wode_iv.setImageResource(R.drawable.wai_nowode);

                wai_shouye.setTextColor(Color.RED);
                wai_shipin.setTextColor(Color.BLACK);
                wai_guanzhu.setTextColor(Color.BLACK);
                wai_wode.setTextColor(Color.BLACK);
                break;

            case R.id.wai_shipin_ll:
                Fragment2 fragment2 = new Fragment2();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.wai_fm,fragment2).commit();

                wai_shouye_iv.setImageResource(R.drawable.wai_noshouye);
                wai_shipin_iv.setImageResource(R.drawable.wai_yesshipin);
                wai_guanzhu_iv.setImageResource(R.drawable.wai_noguanzhu);
                wai_wode_iv.setImageResource(R.drawable.wai_nowode);

                wai_shouye.setTextColor(Color.BLACK);
                wai_shipin.setTextColor(Color.RED);
                wai_guanzhu.setTextColor(Color.BLACK);
                wai_wode.setTextColor(Color.BLACK);
                break;

            case R.id.wai_guanzhu_ll:
                Fragment3 fragment3 = new Fragment3();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.wai_fm,fragment3).commit();

                wai_shouye_iv.setImageResource(R.drawable.wai_noshouye);
                wai_shipin_iv.setImageResource(R.drawable.wai_noshipin);
                wai_guanzhu_iv.setImageResource(R.drawable.wai_yesguanzhu);
                wai_wode_iv.setImageResource(R.drawable.wai_nowode);

                wai_shouye.setTextColor(Color.BLACK);
                wai_shipin.setTextColor(Color.BLACK);
                wai_guanzhu.setTextColor(Color.RED);
                wai_wode.setTextColor(Color.BLACK);
                break;

            case R.id.wai_wode_ll:
                Fragment4 fragment4 = new Fragment4();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.wai_fm,fragment4).commit();

                wai_shouye_iv.setImageResource(R.drawable.wai_noshouye);
                wai_shipin_iv.setImageResource(R.drawable.wai_noshipin);
                wai_guanzhu_iv.setImageResource(R.drawable.wai_noguanzhu);
                wai_wode_iv.setImageResource(R.drawable.wai_yeswode);

                wai_shouye.setTextColor(Color.BLACK);
                wai_shipin.setTextColor(Color.BLACK);
                wai_guanzhu.setTextColor(Color.BLACK);
                wai_wode.setTextColor(Color.RED);
                break;
        }
    }
}
