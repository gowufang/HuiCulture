package ahu.bigdata.huiculture.fragment.cloud;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.WebViewActivity;
import ahu.bigdata.huiculture.adapter.WechatAdapter;
import ahu.bigdata.huiculture.constant.Constant;
import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.module.wechat.WeChatData;
import ahu.bigdata.huiculture.utils.L;

/**
 * Created by YCH on 2017/10/6.
 * Function:推荐界面
 */
public class RecommendFragment extends BaseFragment {


    /**
     *UI
     */
    private ListView mListView;
    private List<WeChatData> mlist=new ArrayList<>();
    //标题
    private List<String> mListTitle = new ArrayList<>();
    //地址
    private List<String> mListUrl = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wechat, null);

        findView(view);
        return view;
    }

    private void findView(View view) {


        mListView = (ListView) view.findViewById(R.id.mListView);
        String url = "http://v.juhe.cn/weixin/query?key=" + Constant.WECHAT_KEY;
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                L.i("Json:"+t);
                parseJson(t);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.i("position:"+position);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("title", mListTitle.get(position));
                L.i("Tittle:"+mListTitle.get(position));
                intent.putExtra("url", mListUrl.get(position));
                startActivity(intent);
            }
        });
    }

    private void parseJson(String t) {
        try {

            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonresult = jsonObject.getJSONObject("result");
            JSONArray jsonlist = jsonresult.getJSONArray("list");
            for (int i = 0; i < jsonlist.length(); i++) {
                JSONObject json = (JSONObject) jsonlist.get(i);
                WeChatData data = new WeChatData();
                String Title = json.getString("title");
                String Url = json.getString("url");
                data.setTitle(Title);
                data.setSource(json.getString("source"));
                data.setImgUrl(json.getString("firstImg"));
                mlist.add(data);
                mListTitle.add(Title);
                mListUrl.add(Url);
            }
            WechatAdapter adapter = new WechatAdapter(getActivity(), mlist);
            mListView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    }


