package com.example.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Main2Activity extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        webView=findViewById(R.id.webview);
        String path = getIntent().getStringExtra("path");
        webView.loadUrl(path);
    }
}
