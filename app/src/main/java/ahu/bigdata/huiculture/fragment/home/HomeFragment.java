package ahu.bigdata.huiculture.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.youdu.activity.AdBrowserActivity;
import com.youdu.okhttp.listener.DisposeDataListener;

import java.util.ArrayList;
import java.util.List;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.PhotoViewActivity;
import ahu.bigdata.huiculture.activity.SearchActivity;
import ahu.bigdata.huiculture.adapter.CourseAdapter;
import ahu.bigdata.huiculture.constant.Constant;
import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.module.recommand.BaseRecommandModel;
import ahu.bigdata.huiculture.module.recommand.RecommandBodyValue;
import ahu.bigdata.huiculture.module.recommand.VideoModule;
import ahu.bigdata.huiculture.network.http.HttpConstants;
import ahu.bigdata.huiculture.network.http.RequestCenter;
import ahu.bigdata.huiculture.utils.L;
import ahu.bigdata.huiculture.view.home.HomeHeaderLayout;
import ahu.bigdata.huiculture.zxing.app.CaptureActivity;

/**
 * Created by ych10 on 2017/9/21.
 * Function:主页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final int REQUEST_QRCODE =0x01 ;
    /**
     * UI
     */
    private View mContentView;
    private TextView mQRCodeView;
    private TextView mSearchView;
    private TextView mCategoryView;
    private ImageView mLoadingView;
    private ListView mListView;

    /**
     * data
     */
    private BaseRecommandModel mRecommanddata;
    private CourseAdapter mAdapter;
    private VideoModule mVideoModule;
    private List<VideoModule> mVideoList=new ArrayList<>();
    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();
    }
    //发送推荐产品的请求
    private void requestRecommandData() {

        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                //完成真正逻辑
//                L.e(L.TAG+"------Success:------"+responseObj.toString());
                /**
                 *获取数据，更新UI
                 */
                mRecommanddata = (BaseRecommandModel) responseObj;
                showSuccessView();

            }

            @Override
            public void onFailure(Object reasonObj) {
                //提示用户网络有问题
                L.e(L.TAG+"------OnFailure:-------:"+reasonObj.toString());
                Toast.makeText(mContext, "请检查网络设置哦", Toast.LENGTH_SHORT).show();
            }
        });

//        RequestCenter.requestVideoData(new DisposeDataListener() {
//            @Override
//            public void onSuccess(Object responseObj) {
//                L.d("Video   Success!");
//                mVideoModule = (VideoModule) responseObj;
//                mVideoList.add(mVideoModule);
//                L.d(L.TAG+mVideoList.get(0).getUrl());
//            }
//
//            @Override
//            public void onFailure(Object reasonObj) {
//                L.d("Video failure!");
//            }
//        });
    }

    /**
     * 请求成功
     */
    private void showSuccessView() {

        if (mRecommanddata != null && mRecommanddata.data.list.size() > 0) {
            mLoadingView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            //添加自定义头布局，ListView的add可重复添加，set会覆盖
            mListView.addHeaderView(new HomeHeaderLayout(mContext,mRecommanddata.data.head));
            //创建Adapter
            mAdapter = new CourseAdapter(mContext,mRecommanddata.data.list);
            mListView.setAdapter(mAdapter);
        } else {
            showErrorView();
        }

    }

    /**
     * 请求失败
     */
    private void showErrorView() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView= inflater.inflate(R.layout.fragment_home_layout,null);
        initView();
        return mContentView;
    }

    private void initView() {

        mQRCodeView = (TextView) mContentView.findViewById(R.id.qrcode_view);
        mQRCodeView.setOnClickListener(this);

        mSearchView = (TextView) mContentView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);

        mCategoryView = (TextView) mContentView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);

        mLoadingView = (ImageView) mContentView.findViewById(R.id.loading_view);
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();

        mListView = (ListView) mContentView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qrcode_view:
                if (hasPermission(Constant.HARDWEAR_CAMERA_PERMISSION)) {
                    doOpenCamera();
                } else {
                    requestPermission(Constant.HARDWEAR_CAMERA_CODE, Constant.HARDWEAR_CAMERA_PERMISSION);
                }
                break;
            case R.id.search_view:
                Intent searchIntent = new Intent(mContext, SearchActivity.class);
                mContext.startActivity(searchIntent);
                break;
            case R.id.category_view:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RecommandBodyValue value = (RecommandBodyValue) mAdapter.getItem(position - mListView.getHeaderViewsCount());
        if (value.type != 0) {
            Intent intent = new Intent(mContext, PhotoViewActivity.class);
            intent.putStringArrayListExtra(PhotoViewActivity.PHOTO_LIST, value.url);
            startActivity(intent);
        }
    }

    @Override
    public void doOpenCamera() {
        startActivityForResult(new Intent(mContext, CaptureActivity.class),REQUEST_QRCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //扫码处理逻辑
            case REQUEST_QRCODE:
                if (resultCode == Activity.RESULT_OK) {
                    String code = data.getStringExtra("SCAN_RESULT");
                    if (code.contains("http") || code.contains("https")) {
                        Intent intent = new Intent(mContext, AdBrowserActivity.class);
                        intent.putExtra(AdBrowserActivity.KEY_URL, code);
                        startActivity(intent);
                    } else {
                        Toast.makeText(mContext, code, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
