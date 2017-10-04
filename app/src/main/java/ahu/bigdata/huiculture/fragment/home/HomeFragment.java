package ahu.bigdata.huiculture.fragment.home;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youdu.okhttp.CommonOkHttpClient;
import com.youdu.okhttp.listener.DisposeDataListener;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.SearchActivity;
import ahu.bigdata.huiculture.constant.Constant;
import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.network.http.RequestCenter;
import ahu.bigdata.huiculture.utils.L;

/**
 * Created by ych10 on 2017/9/21.
 * Function:主页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    /**
     * UI
     */
    private View mContentView;
    private TextView mQRCodeView;
    private TextView mSearchView;
    private TextView mCategoryView;
    private ImageView mLoadingView;
    private ListView mListView;

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
                L.e("Success:"+responseObj.toString());
            }

            @Override
            public void onFailure(Object reasonObj) {
                //提示用户网络有问题
                L.e("OnFailure:"+reasonObj.toString());
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView= inflater.inflate(R.layout.fragment_home,null);
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

    }
}
