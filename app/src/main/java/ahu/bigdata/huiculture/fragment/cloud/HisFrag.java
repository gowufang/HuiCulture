package ahu.bigdata.huiculture.fragment.cloud;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.utils.Json;

/**
 * Created by YCH on 2017/10/6.
 * Function:历史
 */
public class HisFrag extends BaseFragment {
    private Context mContex;
    private View mContent;
    private WebView mwebView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContent= inflater.inflate(R.layout.fragment_his, null);
        return mContent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {

        mwebView= (WebView) mContent.findViewById(R.id.web_his);
        mwebView.getSettings().setJavaScriptEnabled(true);
        mwebView.getSettings().setAppCacheEnabled(true);
        mwebView.getSettings().setLoadWithOverviewMode(true);
        mwebView.setWebViewClient(new WebViewClient());
        // OR, you can also load from an HTML string:
        String summary = Json.overview;
        mwebView.loadData(summary, "text/html", "utf-8");

    }
}