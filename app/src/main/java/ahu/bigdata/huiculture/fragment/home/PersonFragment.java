package ahu.bigdata.huiculture.fragment.home;

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

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.module.recommand.ArticleModule;
import ahu.bigdata.huiculture.utils.L;

/**
 * Created by ych10 on 2017/9/21.
 * Function:
 */
public class PersonFragment extends BaseFragment{
    /**
     * UI
     */
    private TextView mTextView;
    private List<ArticleModule> mList=new ArrayList<>();
    private View mConentView;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mContext = getActivity();
        mConentView= inflater.inflate(R.layout.fragment_person_layout,null);
        initView();
        return mConentView;
    }

    private void initView() {

        mTextView = (TextView) mContext.findViewById(R.id.mText);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {

        String url = "http://hz-api.cnwangjie.com/api/resource/list";
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
                //实例化
                ArticleModule article = new ArticleModule();
                String title = json.getString("title");
                int id = json.getInt("id");
                String content = json.getString("name");
                String author = json.getString("author");
                int createAt = json.getInt("createdAt");
                int updateAt = json.getInt("updatedAt");

                article.setId(id);
                article.setTitle(title);
                article.setContent(content);
                article.setAuthor(author);
                article.setCreateAt(createAt);
                article.setUpdatedAt(updateAt);

                mList.add(article);
            }
            mTextView.setText(mList.get(0).getContent());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
