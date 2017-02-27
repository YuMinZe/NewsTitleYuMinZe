package com.bawei.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.bawei.test.R;

/**
 * 1. 类的用途
 * 2. @author $Yuminze
 * 3. @date 2017/2/18 09:57
 */
public class MainWebview extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        WebView web = (WebView) findViewById(R.id.webview);
        Intent intent = getIntent();
        String weburl = intent.getStringExtra("weburl");
        web.loadUrl(weburl);
    }
}
