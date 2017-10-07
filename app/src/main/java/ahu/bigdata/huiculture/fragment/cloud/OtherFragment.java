package ahu.bigdata.huiculture.fragment.cloud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.fragment.BaseFragment;

/**
 * Created by YCH on 2017/10/7.
 * Function:
 */
public class OtherFragment extends BaseFragment {

    private View mContentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_other, null);
        return mContentView;
    }
}
