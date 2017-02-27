package com.bawei.test.Application;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/17 14:32
 */
public class MyApplication extends Application {

    public static boolean isFalg;
    @Override
    public void onCreate() {
        super.onCreate();
        isFalg=true;
        x.Ext.init(this);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        // 默认设置为日间模式
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }
}
