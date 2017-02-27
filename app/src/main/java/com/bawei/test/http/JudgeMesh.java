package com.bawei.test.http;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/24 10:30
 */
public class JudgeMesh {
    public static boolean detect(Activity act) {

        ConnectivityManager manager = (ConnectivityManager) act
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            return false;
        }

        NetworkInfo networkinfo = manager.getActiveNetworkInfo();

        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }

        return true;
    }

}
