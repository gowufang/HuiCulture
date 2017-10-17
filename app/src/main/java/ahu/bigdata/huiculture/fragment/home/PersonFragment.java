package ahu.bigdata.huiculture.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.fragment.BaseFragment;

/**
 * Created by ych10 on 2017/9/21.
 * Function:个人中心界面
 */
public class PersonFragment extends BaseFragment{

    /**
     * UI
     */
    private View mContentView;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView= inflater.inflate(R.layout.fragment_person_layout,null);
        initView();
        return mContentView;
    }
    private void initView() {


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {

    }
}
