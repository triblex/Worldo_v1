package com.co.triblex.worldo_v01;

/**
 * Created by Triblex on 01-12-2017.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String helpHtml = "<html><body>"
                + "<h1>Here you can find help for the application</h1>"
                + "<p>Right now, no helping text is implemented.<br>It is a work in progress.</p>";

        WebView wv = new WebView(this);
        wv.loadData(helpHtml, "text/html", null);

        setContentView(wv);
    }
}
