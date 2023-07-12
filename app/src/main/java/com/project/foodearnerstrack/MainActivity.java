package com.project.foodearnerstrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
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
//        webView = findViewById(R.id.webView);

        try {
            if (isConnected()) {
//              webView = findViewById(R.id.webView);
                internetConnected();
            } else if (!isConnected()) {
//              webView = findViewById(R.id.webView);
                noInternetConnection();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        internetConnected();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            noInternetConnection();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    internetConnected();
                }
            });
        }

//        if (isConnected()){
////            webView = findViewById(R.id.webView);
//            webView.setWebViewClient(new WebViewClient());
//            webView.loadUrl("https://www.foodearnerstrack.com/login.php");
//            WebSettings webSettings = webView.getSettings();
//            webSettings.setJavaScriptEnabled(true);
//        }else if(!isConnected()) {
////            webView = findViewById(R.id.webView);
//            webView.setVisibility(View.GONE);
//            imageView.setVisibility(View.VISIBLE);
//            textView1.setVisibility(View.VISIBLE);
//            textView2.setVisibility(View.VISIBLE);
//            textView3.setVisibility(View.VISIBLE);
//            button.setVisibility(View.VISIBLE);
//
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    webView.setWebViewClient(new WebViewClient());
//                    webView.loadUrl("https://www.foodearnerstrack.com/login.php");
//                    WebSettings webSettings = webView.getSettings();
//                    webSettings.setJavaScriptEnabled(true);
//                }
//            });
//        }

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

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    private void internetConnected() {
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.foodearnerstrack.com/login.php");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    private void noInternetConnection() {
        webView.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        textView1.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        textView3.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
    }
}