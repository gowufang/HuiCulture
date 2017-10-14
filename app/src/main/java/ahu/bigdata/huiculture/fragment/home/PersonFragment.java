package ahu.bigdata.huiculture.fragment.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kymjs.rxvolley.RxVolley;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.fragment.BaseFragment;

/**
 * Created by ych10 on 2017/9/21.
 * Function:
 */
public class PersonFragment extends BaseFragment{

    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_person_layout,null);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
