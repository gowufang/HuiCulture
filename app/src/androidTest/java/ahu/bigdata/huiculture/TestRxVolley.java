package ahu.bigdata.huiculture;

import android.graphics.Color;
import android.os.Bundle;
import android.support.test.espresso.core.deps.guava.cache.Cache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnImageClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.module.recommand.ArticleModule;
import ahu.bigdata.huiculture.module.recommand.VideoModule;
import ahu.bigdata.huiculture.utils.L;

import static android.R.attr.tag;

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
//
//    /**
//     * 测试TextView
//     */
//    /**
//     * UI
//     */
//    private TextView mTextView;
//    private List<ArticleModule> mList=new ArrayList<>();
//    private View mContentView;
//    @Override
//    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
//        mContext = getActivity();
//        mContentView= inflater.inflate(R.layout.fragment_person_layout,null);
//        initView();
//        return mContentView;
//    }
//
//    private void initView() {
//
//        mTextView = (TextView) mContentView.findViewById(R.id.mText);
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
//        String url = "http://hz-api.cnwangjie.com/api/article/lastest";
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
//                L.d("RxVolley请求失败了:"+strMsg);
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
//                //实例化
//                ArticleModule article = new ArticleModule();
//
//                int id = json.getInt("id");
//                String title = json.getString("title");
//                String content = json.getString("content");
//                String author = json.getString("author");
//                int createAt = json.getInt("createdAt");
//                int updateAt = json.getInt("updatedAt");
//
//                article.setId(id);
//                article.setTitle(title);
//                article.setContent(content);
//                article.setAuthor(author);
//                article.setCreateAt(createAt);
//                article.setUpdatedAt(updateAt);
//
//                mList.add(article);
//            }
//            RichText.from(mList.get(0).getContent())
//                    .autoFix(true) // 是否自动修复，默认true
//                    .autoPlay(true) // gif图片是否自动播放
//                    .showBorder(true) // 是否显示图片边框
//                    .borderColor(Color.RED) // 图片边框颜色
//                    .borderSize(10) // 边框尺寸
//                    .borderRadius(50) // 图片边框圆角弧度
//                    .scaleType(ImageHolder.ScaleType.FIT_CENTER) // 图片缩放方式
//                    .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT) // 图片占位区域的宽高
//                    .fix(imageFixCallback) // 设置自定义修复图片宽高
//                    .fixLink(linkFixCallback) // 设置链接自定义回调
//                    .noImage(true) // 不显示并且不加载图片
//                    .resetSize(false) // 默认false，是否忽略img标签中的宽高尺寸（只在img标签中存在宽高时才有效），true：忽略标签中的尺寸并触发SIZE_READY回调，false：使用img标签中的宽高尺寸，不触发SIZE_READY回调
//                    .clickable(true) // 是否可点击，默认只有设置了点击监听才可点击
//                    .imageClick(new OnImageClickListener() {
//                        @Override
//                        public void imageClicked(List<String> imageUrls, int position) {
//
//                        }
//                    }) // 设置图片点击回调
//                    .urlClick(onURLClickListener) // 设置链接点击回调
//                    .urlLongClick(onUrlLongClickListener) // 设置链接长按回调
//                    .placeHolder(placeHolder) // 设置加载中显示的占位图
//                    .error(errorImage) // 设置加载失败的错误图
//                    .cache(Cache.ALL) // 缓存类型，默认为Cache.ALL（缓存图片和图片大小信息和文本样式信息）
//                    .imageGetter(yourImageGetter) // 设置图片加载器，默认为DefaultImageGetter，使用okhttp实现
//                    .bind(tag) // 绑定richText对象到某个object上，方便后面的清理
//                    .done(callback) // 解析完成回调
//                    .into(mTextView);
////            mTextView.setText(mList.get(0).getContent());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        RichText.recycle();
//
//    }

}
