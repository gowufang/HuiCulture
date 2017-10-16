package ahu.bigdata.huiculture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.module.recommand.VideoModule;
import ahu.bigdata.huiculture.utils.L;

/**
 * Created by YCH on 2017/10/16.
 * Function:
 */
public class TestRxVolley extends BaseFragment{

    /**
     * 测试Video
     */
//    /**
//     * UI
//     */
//    private TextView mTextView;
//    private List<VideoModule> mList=new ArrayList<>();
//    private View mConentView;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mContext = getActivity();
//        mConentView= inflater.inflate(R.layout.fragment_person_layout,null);
//        initView();
//        return mConentView;
//    }
//
//    private void initView() {
//
//        mTextView = (TextView) mContext.findViewById(R.id.test_tv);
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initData();
//    }
//
//    private void initData() {
//
//        String url = "http://hz-api.cnwangjie.com/api/resource/list";
//        RxVolley.get(url, new HttpCallback() {
//            @Override
//            public void onSuccess(String t) {
//                super.onSuccess(t);
//                L.i("RxVolley请求成功的Json:"+t);
//                parseJson(t);
//            }
//
//            @Override
//            public void onFailure(int errorNo, String strMsg) {
//                super.onFailure(errorNo, strMsg);
//                L.d("RxVolley请求失败了"+strMsg);
//            }
//        });
//
//    }
//
//
//    /**
//     * @param t 返回的Json数据
//     * @fuction 解析Json
//     */
//    private void parseJson(String t) {
////
//        try {
//            JSONArray list = new JSONArray(t);
//            for (int i = 0; i < list.length(); i++) {
//                JSONObject json = (JSONObject) list.get(i);
//                //如果取到的是目录，不做任何操作
//                if (json.getBoolean("isdir") == true) {
//                } else {
//                    //实例化
//                    VideoModule data = new VideoModule();
//                    String path = json.getString("path");
//                    int size = json.getInt("size");
//                    String name = json.getString("name");
//                    int modified = json.getInt("modified");
//                    boolean isdir = json.getBoolean("isdir");
//                    String url = json.getString("url");
//
//                    data.setPath(path);
//                    data.setSize(size);
//                    data.setName(name);
//                    data.setModified(modified);
//                    data.setIsdir(isdir);
//                    data.setUrl(url);
//                    mList.add(data);
//                }
//            }
//            //初始化数据源，记载数据源
//            L.d("视频1：" + mList.get(0).getUrl());
//            L.d("视频1：" + mList.get(1).getUrl());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    /**
     * 测试TextView
     */
    private TextView mTextView;
    private List<VideoModule> mList=new ArrayList<>();
    private View mConentView;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mContext = getActivity();
        mConentView= inflater.inflate(R.layout.test_layout,null);
        initView();
        return mConentView;
    }

    private void initView() {

        mTextView = (TextView) mContext.findViewById(R.id.test_tv);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {

        String url = "http://hz-api.cnwangjie.com/api/article/lastest";
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                L.i("RxVolley请求成功的Json:"+t);
                parseJson(t);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                L.d("RxVolley请求失败了"+strMsg);
            }
        });

    }


    /**
     * @param t 返回的Json数据
     * @fuction 解析Json
     */
    private void parseJson(String t) {
//
        try {
            JSONArray list = new JSONArray(t);
            for (int i = 0; i < list.length(); i++) {
                JSONObject json = (JSONObject) list.get(i);
                //如果取到的是目录，不做任何操作
                if (json.getBoolean("isdir") == true) {
                } else {
                    //实例化
                    VideoModule data = new VideoModule();
                    String path = json.getString("path");
                    int size = json.getInt("size");
                    String name = json.getString("name");
                    int modified = json.getInt("modified");
                    boolean isdir = json.getBoolean("isdir");
                    String url = json.getString("url");

                    data.setPath(path);
                    data.setSize(size);
                    data.setName(name);
                    data.setModified(modified);
                    data.setIsdir(isdir);
                    data.setUrl(url);
                    mList.add(data);
                }
            }
            //初始化数据源，记载数据源
            L.d("视频1：" + mList.get(0).getUrl());
            L.d("视频1：" + mList.get(1).getUrl());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
