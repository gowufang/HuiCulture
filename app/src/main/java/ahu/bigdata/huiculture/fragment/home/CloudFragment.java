package ahu.bigdata.huiculture.fragment.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;

import java.util.ArrayList;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.WebViewActivity;
import ahu.bigdata.huiculture.adapter.GridViewAdapter;
import ahu.bigdata.huiculture.constant.Constant;
import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.module.cloud.GridItem;
import ahu.bigdata.huiculture.web.SonicJavaScriptInterface;
import ahu.bigdata.huiculture.web.SonicRuntimeImpl;

import static android.R.attr.mode;

/**
 * Created by ych10 on 2017/9/21.
 * Function:徽云Fragment
 */
public class CloudFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    /**
     * UI
     */
    private View mContentView;
    private GridView mGridView = null;
    /**
     * Data
     */
    private GridViewAdapter mGridViewAdapter = null;
    private ArrayList<GridItem> mGridData = null;
    public static final int MODE_DEFAULT = 0;

    public static final int MODE_SONIC = 1;

    public static final int MODE_SONIC_WITH_OFFLINE_CACHE = 2;

    private static final int PERMISSION_REQUEST_CODE_STORAGE = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_cloud_layout, null);
        initView();
        return mContentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            initData();
    }
    //初始化数据源
    private void initData() {

        mGridData = new ArrayList<>();
        for (int i = 0; i < Constant.logoCount; i++) {
            GridItem item = new GridItem();
            item.setTitle(Constant.LogoTextUrl[i]);
            item.setImage(Constant.LogoImgUrl[i]);
            mGridData.add(item);
        }

        if (hasPermission()) {
            init();
        } else {
            requestPermission();
        }


    }

    private void initView() {

        mGridView = (GridView) mContentView.findViewById(R.id.mGridView);
        mGridViewAdapter = new GridViewAdapter(mContext, R.layout.gridview_item, mGridData);

        mGridView.setAdapter(mGridViewAdapter);
        mGridView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       conveyURL(position);
    }

    /**
     * 传递Url给WebView
     */
    private void conveyURL(int position) {
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra("url",Constant.LogoWebUrl[position]);
        intent.putExtra(WebViewActivity.PARAM_MODE, MODE_SONIC_WITH_OFFLINE_CACHE);
        intent.putExtra("title", Constant.LogoTextUrl[position]);
        intent.putExtra(SonicJavaScriptInterface.PARAM_CLICK_TIME, System.currentTimeMillis());
        startActivityForResult(intent,-1);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PERMISSION_REQUEST_CODE_STORAGE == requestCode) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                requestPermission();
            } else {
                init();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void init() {
        // init sonic engine
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(mContext), new SonicConfig.Builder().build());
        }
    }

    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfP ermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE_STORAGE);
        }
    }

}
