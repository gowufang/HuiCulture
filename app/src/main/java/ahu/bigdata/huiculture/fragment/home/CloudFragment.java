package ahu.bigdata.huiculture.fragment.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.youdu.activity.AdBrowserActivity;

import java.util.ArrayList;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.WebViewActivity;
import ahu.bigdata.huiculture.adapter.GridViewAdapter;
import ahu.bigdata.huiculture.constant.Constant;
import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.module.cloud.GridItem;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

/**
 * Created by ych10 on 2017/9/21.
 * Function:徽云Fragment
 */
public class CloudFragment extends BaseFragment implements AdapterView.OnItemClickListener, WaveSwipeRefreshLayout.OnRefreshListener {

    /**
     * UI
     */
    private View mContentView;
    private GridView mGridView = null;
    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
    /**
     * Data
     */
    private GridViewAdapter mGridViewAdapter = null;
    private ArrayList<GridItem> mGridData = null;


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

    }

    private void initView() {

        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) mContentView.findViewById(R.id.main_swipe);
        //设置中间小圆从白色到黑色
        mWaveSwipeRefreshLayout.setColorSchemeColors(Color.WHITE, Color.BLACK);
        //设置整体的颜色
        mWaveSwipeRefreshLayout.setWaveColor(Color.argb(100, 0, 0, 0));
        mWaveSwipeRefreshLayout.setOnRefreshListener(this);

        mGridView = (GridView) mContentView.findViewById(R.id.mGridView);
        mGridViewAdapter = new GridViewAdapter(mContext, R.layout.gridview_item, mGridData);

        mGridView.setAdapter(mGridViewAdapter);
        mGridView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       conveyURL(position);
    }

    @Override
    public void onRefresh() {
        // Do work to refresh the list here.
        new Task().execute();
    }

    private class Task extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            initData();
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] result) {
            // Call setRefreshing(false) when the list has been refreshed.
            mWaveSwipeRefreshLayout.setRefreshing(false);
            super.onPostExecute(result);
        }
    }
    /**
     * 传递Url给WebView
     */
    private void conveyURL(int position) {
        Intent intent = new Intent(mContext, AdBrowserActivity.class);
        intent.putExtra(AdBrowserActivity.KEY_URL, Constant.LogoWebUrl[position]);
        mContext.startActivity(intent);
        startActivity(intent);
    }
}
