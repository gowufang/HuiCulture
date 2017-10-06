package ahu.bigdata.huiculture.fragment.cloud;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.WebViewActivity;
import ahu.bigdata.huiculture.fragment.BaseFragment;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by YCH on 2017/10/6.
 * Function:历史
 */
public class HisFrag extends BaseFragment {
    //网页
    private WebView mWebView;
    private Context mContex=getActivity();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test, null);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mWebView = (WebView)mContext.findViewById(R.id.mWebView);


        final String Url =
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
        mWebView.setWebChromeClient(new WebViewActivity.WebViewClient());
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

    public class WebViewClient extends WebChromeClient {

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
}