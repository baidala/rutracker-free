package ru.jehy.proxy_rutracker;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RunWebView();
    }

    public void RunWebView() {
        WebView myWebView = (WebView) findViewById(R.id.webView);
        //if (Build.VERSION.SDK_INT >= 21)
            myWebView.setWebViewClient(new MyWebViewClient());
        //else
        //    myWebView.setWebViewClient(new MyWebViewClientOld());

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setDisplayZoomControls(false);
        CookieManager.getInstance().setAcceptCookie(true);
        String url = "http://rutracker.org/forum/index.php";
        //String url = "http://innadibaqia.tumblr.com/post/128834398496/%D0%BD%D0%B5%D1%82-%D0%BE%D0%BF%D1%80%D0%B0%D0%B2%D0%B4%D0%B0%D0%BD%D0%B8%D1%8F-%D0%BD%D0%B8%D0%BA%D0%BE%D0%BC%D1%83-%D0%B8%D0%B7-%D0%BC%D1%83%D1%81%D1%83%D0%BB%D1%8C%D0%BC%D0%B0%D0%BD-%D1%81%D0%BF%D0%BE%D1%81%D0%BE%D0%B1%D0%BD%D1%8B%D1%85-%D0%BD%D0%B0";

        //String url = "http://myip.ru/";

        //request_header_add Proxy-Authorization "SpdyProxy ps=\"1390372720-748089166-1671804897-22716992\", sid=\"95b3da26c6bfc85b64b4768b7e683000\""

        Log.d("RunWebView", "Opening: " + url);
        //myWebView.loadUrl(url,extraHeaders);
        myWebView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    WebView myWebView = (WebView) findViewById(R.id.webView);
                    if (myWebView.canGoBack()) {
                        myWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}

