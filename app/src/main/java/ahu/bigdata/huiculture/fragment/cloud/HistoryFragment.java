package ahu.bigdata.huiculture.fragment.cloud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.fragment.BaseFragment;

/**
 * Created by YCH on 2017/10/6.
 * Function:历史
 */
public class HistoryFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, null);
        return view;
    }
}