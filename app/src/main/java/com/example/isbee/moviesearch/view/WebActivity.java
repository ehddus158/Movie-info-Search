package com.example.isbee.moviesearch.view;

import android.os.Bundle;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.isbee.moviesearch.R;
import com.example.isbee.moviesearch.databinding.WebViewBinding;

public class WebActivity extends AppCompatActivity {

    private WebViewBinding webViewBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webViewBinding = DataBindingUtil.setContentView(this, R.layout.web_view);

        webViewBinding.webView.setWebViewClient(new WebViewClient());
        webViewBinding.webView.getSettings().setJavaScriptEnabled(true);

        String linkUrl = getIntent().getStringExtra("link");
        webViewBinding.setLinkUrl(linkUrl);
    }
}
