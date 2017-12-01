package com.co.triblex.worldo_v01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by Triblex on 01-12-2017.
 */

public class WebViewActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        webView.loadUrl("https://www.momondo.com");
        setContentView(webView);
    }
}
