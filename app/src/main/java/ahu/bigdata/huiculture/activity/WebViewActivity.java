package ahu.bigdata.huiculture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.base.BaseActivity;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by YCH on 2017/9/27.
 * Function:网络详情页
 */

public class WebViewActivity extends BaseActivity {

    //进度条
    private ProgressBar mProgrecessBar;
    //网页
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();
    }

    private void initView() {

        mProgrecessBar = (ProgressBar) findViewById(R.id.mProgressBar);
        mWebView = (WebView) findViewById(R.id.mWebView);

        Intent intent = getIntent();

        final String Url = intent.getStringExtra("url");
        //设置标题
        getSupportActionBar().setTitle(intent.getStringExtra("title"));

        //进行加载网页的逻辑
        mWebView.getSettings().setJavaScriptEnabled(true);
        //支持缩放
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        //声明WebSettings子类
        WebSettings webSettings = mWebView.getSettings();

        //优先使用缓存:
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //接口回调
        mWebView.setWebChromeClient(new WebViewClient());
        //加载网页
        mWebView.loadUrl(Url);

        //本地显示
        mWebView.setWebViewClient(new android.webkit.WebViewClient(){
            /**
             *一直加载会跳转浏览器，ture表示我接受这个事件
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                view.loadUrl(Url);
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KEYCODE_BACK&&mWebView.canGoBack()) {

        mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class WebViewClient extends WebChromeClient{

        //进度变化的监听
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            if (newProgress==100) {
                mProgrecessBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}
