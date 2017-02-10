package com.bawei.test.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.test.R;
import com.bawei.test.fragment.Fragment5;
import com.bawei.test.fragment.Fragment6;
import com.bawei.test.fragment.Fragment7;

public class MainActivity extends FragmentActivity {

    private ViewPager vp;
    private SharedPreferences sp;
    private SharedPreferences.Editor et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("1502H",MODE_PRIVATE);
        et = sp.edit();
        vp = (ViewPager) findViewById(R.id.wai_vp);

        boolean b = sp.getBoolean("flag", false);
        if(b==false){
            et.putBoolean("flag",true).commit();
            vp.setAdapter(new MyAdapter(getSupportFragmentManager()));

        }else{
            Intent in = new Intent(MainActivity.this,MainActivity_yindao.class);
            startActivity(in);
            finish();
        }
    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = new Fragment5();
                    break;

                case 1:
                    fragment = new Fragment6();
                    break;

                case 2:
                    fragment = new Fragment7();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
