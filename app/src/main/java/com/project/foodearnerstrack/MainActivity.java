package com.project.foodearnerstrack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Context context;
    TextView textView1, textView2, textView3;
    Button button;
    ImageView imageView;
    WebView webView;
//    String wUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setActionBar();

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        webView = findViewById(R.id.webView);

//        wUrl = "https://api.whatsapp.com/send?phone=2348169822413&text=Hi+Food+Earners+track,+I+want+to+make+an+inquiry+about";
//        try {
//            if (isConnected()) {
////              webView = findViewById(R.id.webView);
//                internetConnected();
//            } else if (!isConnected()) {
////              webView = findViewById(R.id.webView);
//                noInternetConnection();
//
//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        internetConnected();
//                    }
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            noInternetConnection();
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    internetConnected();
//                }
//            });
//        }

        if (isConnected()){
            webView = findViewById(R.id.webView);
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    return super.shouldOverrideUrlLoading(view, url);
                    if(url.equals(UrlConst.whatsappUrl) || url.startsWith("tel:") || url.startsWith("whatsapp:")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                    if (!isConnected()){
                        webView.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                        textView1.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.VISIBLE);
                        button.setVisibility(View.VISIBLE);

                        return true;
                    }
                    return false;
                }
            });
            webView.loadUrl(UrlConst.login);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }else if(!isConnected()) {
            webView = findViewById(R.id.webView);
            webView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    webView.setWebViewClient(new WebViewClient(){
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                            return super.shouldOverrideUrlLoading(view, url);
                            if(url.equals(UrlConst.whatsappUrl) || url.startsWith("tel:") || url.startsWith("whatsapp:")) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);
                                return true;
                            }

                            return false;
                        }
                    });
                    webView.loadUrl(UrlConst.login);
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                }
            });
        }

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }


    private void setActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().
                getSystemService(context.CONNECTIVITY_SERVICE);

        if(connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected() &&
                connectivityManager.getActiveNetworkInfo().isAvailable())
        {
            return true;
        }
        return false;
    }

//    private void internetConnected() {
//        webView = findViewById(R.id.webView);
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                return super.shouldOverrideUrlLoading(view, url);
//                if(url.equals(UrlConst.whatsappUrl) || url.startsWith("tel:") || url.startsWith("whatsapp:")) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(url));
//                    startActivity(intent);
//                    return true;
//                }
//                return false;
//            }
//        });
//        webView.loadUrl(UrlConst.login);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//    }

//    private void noInternetConnection() {
//        webView.setVisibility(View.GONE);
//        imageView.setVisibility(View.VISIBLE);
//        textView1.setVisibility(View.VISIBLE);
//        textView2.setVisibility(View.VISIBLE);
//        textView3.setVisibility(View.VISIBLE);
//        button.setVisibility(View.VISIBLE);
//    }


    private void testMethod(){
        String test = "234";
    }
}