package com.androidbpn.bpn;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView mywebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //starts the webview to access your wordpresspage
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mywebView = findViewById(R.id.webview);
        mywebView.getSettings().setAppCacheEnabled(true);
        WebSettings webSettings= mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mywebView.loadUrl("http://blaupause-nuernberg.serveblog.net/");
        // Line of Code for opening links in app. Look at CustomWebViewClient-Class
        mywebView.setWebViewClient(new com.androidbpn.bpn.CustomWebViewClient());
    }

    //Code For Back Button
    @Override
    public void onBackPressed() {
        if(mywebView.canGoBack())
        {
            mywebView.goBack();
        }

        else
        {
            super.onBackPressed();
        }
    }

}