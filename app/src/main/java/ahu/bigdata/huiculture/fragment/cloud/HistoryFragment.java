package ahu.bigdata.huiculture.fragment.cloud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youdu.okhttp.listener.DisposeDataListener;
import com.zzhoujay.richtext.RichText;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.constant.Constant;
import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.network.http.RequestCenter;
import ahu.bigdata.huiculture.utils.L;

/**
 * Created by YCH on 2017/10/6.
 * Function:历史
 */
public class HistoryFragment extends BaseFragment {
    private View mContentView;
    private TextView mTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView= inflater.inflate(R.layout.fragment_history, null);
        initView();
        return mContentView;
    }

    private void initView() {

        mTextView = (TextView) mContentView.findViewById(R.id.mTextView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestRecommandData();
//        RichText.fromHtml(Constant.History).into(mTextView);
    }

    private void requestRecommandData() {

        RequestCenter.requestHistoryData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                L.d("请求数据成功！!!!!!!!!!!!!");
                showSuccessView();
            }

            @Override
            public void onFailure(Object reasonObj) {
                L.d("请求数据失败!!!!!!!!!!!!"+reasonObj);
            }
        });
    }

    private void showSuccessView() {

//        RichText.fromHtml().into(mTextView);
    }
}