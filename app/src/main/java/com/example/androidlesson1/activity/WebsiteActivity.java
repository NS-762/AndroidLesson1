package com.example.androidlesson1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.androidlesson1.OkHttpRequester;
import com.example.androidlesson1.R;

public class WebsiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_site);

        final WebView page = findViewById(R.id.page);

        OkHttpRequester requester = new OkHttpRequester(new OkHttpRequester.OnResponseCompleted() {
            @Override
            public void onCompleted(String content) {
                WebSettings settings = page.getSettings();
                settings.setDefaultTextEncodingName("utf-8");
                page.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
//                page.loadData(content, "text/html; charset=utf-8", null);
            }
        });
        requester.run("https://lenta.ru/");
    }
}