package ahu.bigdata.huiculture.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.WebViewActivity;
import ahu.bigdata.huiculture.adapter.GridViewAdapter;
import ahu.bigdata.huiculture.constant.Constant;
import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.module.cloud.GridItem;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_cloud, null);
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
        intent.putExtra("title", Constant.LogoTextUrl[position]);
        startActivity(intent);
    }
}
