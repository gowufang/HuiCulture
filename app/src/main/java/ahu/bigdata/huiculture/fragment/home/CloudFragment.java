package ahu.bigdata.huiculture.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.WebViewActivity;
import ahu.bigdata.huiculture.adapter.GridViewAdapter;
import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.module.cloud.GridItem;
import ahu.bigdata.huiculture.utils.L;

/**
 * Created by ych10 on 2017/9/21.
 * Function:徽云Fragment
 */
public class CloudFragment extends BaseFragment {


    /**
     * 徽商、徽州宗族、新安理学、新安医学、新安画派、徽州文书、徽派朴学、徽派版画、徽派篆刻、
     *徽州戏剧、徽州教育、徽州刻书、徽州科技、徽派建筑、徽州三雕、徽州民俗、徽州方言、徽菜、新安围棋、
     *文房四宝、徽派盆景、徽漆及各种竹、木编织工艺、徽州历史人物
     */

    /**
     * UI
     */

    private View mContentView;
    private String[] localCartoonText = {"徽商", "徽州宗族", "新安理学", "新安医学", "新安画派",
            "徽州文书", "徽州朴学", "徽派版画", "徽派建筑", "徽州三雕", "文房四宝", "历史人物"};

    private String[] imgURL = {"http://hz.cnwangjie.com/static/%E5%BE%BD%E5%95%86.jpg",
            "http://hz.cnwangjie.com/static/%E5%BE%BD%E5%B7%9E%E5%AE%97%E6%97%8F.jpg",
            "http://hz.cnwangjie.com/static/%E6%96%B0%E5%AE%89%E7%90%86%E5%AD%A6.jpg",
            "http://hz.cnwangjie.com/static/%E6%96%B0%E5%AE%89%E5%8C%BB%E5%AD%A6.jpg",
            "http://hz.cnwangjie.com/static/%E6%96%B0%E5%AE%89%E7%94%BB%E6%B4%BE.jpg",
            "http://hz.cnwangjie.com/static/%E5%BE%BD%E5%B7%9E%E6%96%87%E4%B9%A6.jpg",
            "http://hz.cnwangjie.com/static/%E5%BE%BD%E6%B4%BE%E6%9C%B4%E5%AD%A6.jpg",
            "http://hz.cnwangjie.com/static/%E5%BE%BD%E6%B4%BE%E7%89%88%E7%94%BB.jpg",
            "http://hz.cnwangjie.com/static/%E5%BE%BD%E6%B4%BE%E5%BB%BA%E7%AD%91.jpg",
            "http://hz.cnwangjie.com/static/%E5%BE%BD%E5%B7%9E%E4%B8%89%E9%9B%95.jpg",
            "http://hz.cnwangjie.com/static/%E6%96%87%E6%88%BF%E5%9B%9B%E5%AE%9D.jpg",
            "http://hz.cnwangjie.com/static/%E5%BE%BD%E5%B7%9E%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9.jpg"
    };
    private GridView mGridView = null;
    private GridViewAdapter mGridViewAdapter = null;
    private ArrayList<GridItem> mGridData = null;
    //标题
    private List<String> mListTitle = new ArrayList<>();
    //地址
    private List<String> mListUrl = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();

        mContentView = inflater.inflate(R.layout.fragment_cloud, null);
        initView();
        return mContentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {

        mGridData = new ArrayList<GridItem>();
        for (int i = 0; i < imgURL.length; i++) {
            GridItem item = new GridItem();
            item.setTitle(localCartoonText[i]);
            mListTitle.add(localCartoonText[i]);
            item.setImage(imgURL[i]);
            mGridData.add(item);
        }
        /**
         * 静态写死
         */
        mListUrl.add("http://hz.cnwangjie.com/#/class/徽商");
        mListUrl.add("http://hz.cnwangjie.com/#/class/徽州宗族");
        mListUrl.add("http://hz.cnwangjie.com/#/class/新安理学");
        mListUrl.add("http://hz.cnwangjie.com/#/class/新安医学");
        mListUrl.add("http://hz.cnwangjie.com/#/class/新安画派");
        mListUrl.add("http://hz.cnwangjie.com/#/class/新安文书");
        mListUrl.add("http://hz.cnwangjie.com/#/class/新安朴学");
        mListUrl.add("http://hz.cnwangjie.com/#/class/新安版画");
        mListUrl.add("http://hz.cnwangjie.com/#/class/徽派建筑");
        mListUrl.add("http://hz.cnwangjie.com/#/class/徽州三雕");
        mListUrl.add("http://hz.cnwangjie.com/#/class/文房四宝");
        mListUrl.add("http://hz.cnwangjie.com/#/class/徽州历史人物");

    }

    private void initView() {

        mGridView = (GridView) mContentView.findViewById(R.id.mGridView);
        mGridViewAdapter = new GridViewAdapter(mContext, R.layout.gridview_item, mGridData);

        mGridView.setAdapter(mGridViewAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {

                    case 0:
                        intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;
                    case 2:
                         intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;
                    case 3:
                       intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;
                    case 6:
                         intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;
                    case 9:
                        intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;
                    case 10:
                        intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;
                    case 11:
                        intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;
                    case 12:
                        intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;
                    case 13:
                        intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;
                    case 14:
                        intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", mListUrl.get(position));
                        intent.putExtra("title", mListTitle.get(position));
                        startActivity(intent);
                        break;

                }

            }
        });
    }


}
