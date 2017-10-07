package ahu.bigdata.huiculture.fragment.cloud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.fragment.BaseFragment;

/**
 * Created by YCH on 2017/10/7.
 * Function:新安模块
 */
public class XinAnFragment extends BaseFragment {


    /**
     *  onCreate是指创建该fragment类似于Activity.onCreate，
     * 你可以在其中初始化除了view之外的东西，onCreateView是创建该fragment对应的视图，
     * 你必须在这里创建自己的视图并返回给调用者，例如
     *  return inflater.inflate(R.layout.fragment_settings, container, false);。
        super.onCreateView有没有调用都无所谓，因为super.onCreateView是直接返回null的。
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_xinan, null);
        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
