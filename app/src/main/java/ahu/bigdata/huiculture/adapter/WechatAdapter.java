package ahu.bigdata.huiculture.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.module.wechat.WeChatData;
import ahu.bigdata.huiculture.utils.PicassoUtils;

/**
 * Created by YCH on 2017/9/25.
 */

public class WechatAdapter extends BaseAdapter {

    private Context mContex;
    private LayoutInflater inflater;
    private List<WeChatData> mList;
    private WeChatData data;


    public WechatAdapter(Context mContex, List<WeChatData> mList) {
        this.mContex = mContex;
        this.mList = mList;
        inflater = (LayoutInflater) mContex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /**
        *Create by YCH on 18:11
        *Function:判断是否缓存,View中的setTag（Object）表示给View添加一个格外的数据，以后可以用getTag()将这个数据取出来。
        */
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.wechat_item,null);
            viewHolder.iv_img = (ImageView) view.findViewById(R.id.iv_img);
            viewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            viewHolder.tv_source = (TextView) view.findViewById(R.id.tv_source);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        data = mList.get(i);
        viewHolder.tv_source.setText(data.getSource());
        viewHolder.tv_title.setText(data.getTitle());
        /**
         *加载Picasso
         */
        PicassoUtils.loadImagViewSize(mContex,data.getImgUrl(),200,200,viewHolder.iv_img);
        return view;
    }
    class ViewHolder{
        private ImageView iv_img;
        private TextView tv_title;
        private TextView tv_source;
    }

}
