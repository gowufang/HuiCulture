package ahu.bigdata.huiculture.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.youdu.activity.AdBrowserActivity;
import com.youdu.adutil.Utils;
import com.youdu.core.AdContextInterface;
import com.youdu.core.video.VideoAdContext;

import java.util.ArrayList;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.module.recommand.RecommandBodyValue;
import ahu.bigdata.huiculture.share.ShareDialog;
import ahu.bigdata.huiculture.utils.ImageLoaderManager;
import ahu.bigdata.huiculture.utils.Util;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.internal.platform.Platform;

/**
 * Created by ych10 on 2017/10/7.
 * Function:
 */
public class CourseAdapter extends BaseAdapter{

    /**
     * 不同类型的Item类型
     */
    private static final int CARD_COUNT = 4;
    private static final int VIDOE_TYPE = 0x00;
    private static final int CARD_MULTI_PIC = 0x01;//多图Item
    private static final int CARD_SINGNAL_PIC = 0x02;//单图Item
    private static final int CARD_VIEW_PAGER = 0x03;

    private Context mContext;
    private ViewHolder mViewHolder;
    private LayoutInflater mInflate;
    
    private ArrayList<RecommandBodyValue> mData;
    private ImageLoaderManager mImagerLoader;
    private VideoAdContext mAdsdkContext;
    /**
     * 构造方法
     * @param context
     * @param data
     */
    public CourseAdapter(Context context, ArrayList<RecommandBodyValue> data) {
        mContext = context;
        mData = data;
        mInflate = LayoutInflater.from(mContext);
        mImagerLoader = ImageLoaderManager.getInstance(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *四种类型Item
     */
    @Override
    public int getViewTypeCount() {
        return CARD_COUNT;
    }

    /**
     * 通知Adapter使用哪种类型的Adapter
     * @param position
     * @return type
     */
    @Override
    public int getItemViewType(int position) {
        RecommandBodyValue value = (RecommandBodyValue) getItem(position);
        return value.type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //1.获取数据类型
        int type = getItemViewType(position);
        //2.获取数据
        final RecommandBodyValue value = (RecommandBodyValue) getItem(position);
        //3.判断缓存是否为空
        if (convertView == null) {
            /**
             *初始化
             */
                switch (type) {
                    //多图Item
                    case CARD_MULTI_PIC:
                        mViewHolder = new ViewHolder();
                        //root会协助linearlayout的根节点生成布局参数
                        convertView = mInflate.inflate(R.layout.item_product_card_multi_layout, parent, false);
                        //初始化控件
                        mViewHolder.mLogoView = (CircleImageView) convertView.findViewById(R.id.item_logo_view);
                        mViewHolder.mTitleView = (TextView) convertView.findViewById(R.id.item_title_view);
                        mViewHolder.mInfoView = (TextView) convertView.findViewById(R.id.item_info_view);
                        mViewHolder.mFooterView = (TextView) convertView.findViewById(R.id.item_footer_view);
                        mViewHolder.mPriceView = (TextView) convertView.findViewById(R.id.item_price_view);
                        mViewHolder.mFromView = (TextView) convertView.findViewById(R.id.item_from_view);
                        mViewHolder.mZanView = (TextView) convertView.findViewById(R.id.item_zan_view);
                        mViewHolder.mProductLayout = (LinearLayout) convertView.findViewById(R.id.product_photo_layout);
                        break;
                //单图Item
                case CARD_SINGNAL_PIC:
                    mViewHolder = new ViewHolder();
                    convertView = mInflate.inflate(R.layout.item_product_card_singal_layout, parent, false);
                    mViewHolder.mLogoView = (CircleImageView) convertView.findViewById(R.id.item_logo_view);
                    //初始化控件
                    mViewHolder.mTitleView = (TextView) convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = (TextView) convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = (TextView) convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mPriceView = (TextView) convertView.findViewById(R.id.item_price_view);
                    mViewHolder.mFromView = (TextView) convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mZanView = (TextView) convertView.findViewById(R.id.item_zan_view);
                    mViewHolder.mProductView = (ImageView) convertView.findViewById(R.id.product_photo_view);
                    break;
                case CARD_VIEW_PAGER:
                    mViewHolder = new ViewHolder();
                    convertView = mInflate.inflate(R.layout.item_product_card_view_pager_layout,parent,false);
                    mViewHolder.mViewPager = (ViewPager) convertView.findViewById(R.id.pager);
                    mViewHolder.mViewPager.setPageMargin(Utils.dip2px(mContext,12));
                    /**
                     * 为ViewPager填充数据
                     */
                    ArrayList<RecommandBodyValue> RecommandBodyList = Util.handleData(value);
                    mViewHolder.mViewPager.setAdapter(new HotSalePagerAdapter(mContext,RecommandBodyList));
                    /**
                     * 让ViewPager一开始就从中间开始，即可实现从两个方向滑动
                     */
                    mViewHolder.mViewPager.setCurrentItem(RecommandBodyList.size()*100);
                    break;
                case VIDOE_TYPE:
                    //显示video卡片
                    mViewHolder = new ViewHolder();
                    convertView = mInflate.inflate(R.layout.item_video_layout, parent, false);
                    mViewHolder.mVieoContentLayout = (RelativeLayout)
                            convertView.findViewById(R.id.video_ad_layout);
                    mViewHolder.mLogoView = (CircleImageView) convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView = (TextView) convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = (TextView) convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = (TextView) convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mShareView = (ImageView) convertView.findViewById(R.id.item_share_view);
                    //为对应布局创建播放器
                    mAdsdkContext = new VideoAdContext(mViewHolder.mVieoContentLayout,
                            new Gson().toJson(value), null);
                    mAdsdkContext.setAdResultListener(new AdContextInterface() {
                        @Override
                        public void onAdSuccess() {
                        }

                        @Override
                        public void onAdFailed() {
                        }

                        @Override
                        public void onClickVideo(String url) {
                            Intent intent = new Intent(mContext, AdBrowserActivity.class);
                            intent.putExtra(AdBrowserActivity.KEY_URL, url);
                            mContext.startActivity(intent);
                        }
                    });
                    break;

            }
            convertView.setTag(mViewHolder);
        }//有tag时
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        /**
        /**
         * 绑定数据
         */
        switch (type) {

            case CARD_MULTI_PIC:
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                mViewHolder.mPriceView.setText(value.price);
                mViewHolder.mFromView.setText(value.from);
                mViewHolder.mZanView.setText(mContext.getString(R.string.dian_zan).concat(value.zan));
                /**
                 * 加载Image到ImageView
                 */
                mImagerLoader.displayImage(mViewHolder.mLogoView, value.logo);
                /**
                 * 因为ListView会复用coverView,每次划入划出都会添加其他多图Item中添加进来
                 */
               mViewHolder.mProductLayout.removeAllViews();//删除已有图片
                //动态添加多个imageview到水平的ScrollView中
                for (String url : value.url) {
                    mViewHolder.mProductLayout.addView(createImageView(url));
                }
                break;
            case CARD_SINGNAL_PIC:
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                mViewHolder.mPriceView.setText(value.price);
                mViewHolder.mFromView.setText(value.from);
                mViewHolder.mZanView.setText(mContext.getString(R.string.dian_zan).concat(value.zan));
                mImagerLoader.displayImage(mViewHolder.mLogoView, value.logo);
                //为单个ImageView加载远程图片
                mImagerLoader.displayImage(mViewHolder.mProductView, value.url.get(0));

            case CARD_VIEW_PAGER:
                break;

            case VIDOE_TYPE:
                mImagerLoader.displayImage(mViewHolder.mLogoView, value.logo);
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                    mViewHolder.mShareView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            ShareDialog dialog = new ShareDialog(mContext, false);
//                            dialog.setShareType(Platform.SHARE_VIDEO);
//                            dialog.setShareTitle(value.title);
//                            dialog.setShareTitleUrl(value.site);
//                            dialog.setShareText(value.text);
//                            dialog.setShareSite(value.title);
//                            dialog.setShareTitle(value.site);
//                            dialog.setUrl(value.resource);
//                            dialog.show();
                        }
                    });
                break;
        }
        return convertView;
    }

   /**
     *用代码实现动态添加ImageView
     */
    private ImageView createImageView(String url) {
        ImageView photoView = new ImageView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.
                LayoutParams(Utils.dip2px(mContext, 100),
                LinearLayout.LayoutParams.MATCH_PARENT);//宽、高
        params.leftMargin = Utils.dip2px(mContext, 5);
        photoView.setLayoutParams(params);
        mImagerLoader.displayImage(photoView, url);
        return photoView;
    }
   /**
    *缓存
    */
    private static class ViewHolder {
        //所有Card共有属性
        private CircleImageView mLogoView;
        private TextView mTitleView;
        private TextView mInfoView;
        private TextView mFooterView;
        //Video Card特有属性
        private RelativeLayout mVieoContentLayout;
        private ImageView mShareView;

        //Video Card外所有Card具有属性
        private TextView mPriceView;
        private TextView mFromView;
        private TextView mZanView;
        //Card One特有属性
        private LinearLayout mProductLayout;
        //Card Two特有属性
        private ImageView mProductView;
        //Card Three特有属性
        private ViewPager mViewPager;
    }


}
