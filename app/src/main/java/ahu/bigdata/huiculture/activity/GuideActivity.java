package ahu.bigdata.huiculture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.base.BaseActivity;
import ahu.bigdata.huiculture.utils.L;

/**
 * Created by YCH on 2017/9/22.
 * Function:引导页
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    //容器
    private List<View> mlist = new ArrayList<>();
    private View view1,view2,view3;
    //小圆点
    ImageView point1,point2,point3;
    //跳过
    private  ImageView iv_back;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        InitView();
    }

    private void InitView() {

        point1 = (ImageView) findViewById(R.id.point1);
        point2 = (ImageView) findViewById(R.id.point2);
        point3 = (ImageView) findViewById(R.id.point3);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);

        //设置默认图片
        setPointImage(true,false,false);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        view1 = View.inflate(this,R.layout.pager_item_one,null);
        view2 = View.inflate(this,R.layout.pager_item_two,null);
        view3 = View.inflate(this,R.layout.pager_item_three,null);
        //给进入主页按钮注册点击事件
        view3.findViewById(R.id.btn_start).setOnClickListener(this);

        mlist.add(view1);
        mlist.add(view2);
        mlist.add(view3);
        //设置适配器
        mViewPager.setAdapter(new GuideAdapter());
        //监听ViewPager的滑动
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //Page切换的回调
            @Override
            public void onPageSelected(int position) {
                L.i("position："+position);
                switch (position){
                    case 0:
                        setPointImage(true,false,false);
                        iv_back.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        setPointImage(false,true,false);
                        iv_back.setVisibility(View.VISIBLE);
                        iv_back.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        setPointImage(false,false,true);
                        iv_back.setVisibility(View.GONE);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /*点击进入主页或者跳过*/
            case R.id.btn_start:
            case R.id.iv_back:
                startActivity(new Intent(this,HomeActivity.class));
                finish();
                break;
        }
    }

    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mlist.get(position));
            return mlist.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mlist.get(position));
            //super.destroyItem(container, position, object);
        }
    }
    //设置小圆点的选中效果
    private void setPointImage(Boolean isChecked1,Boolean isChecked2,Boolean isChecked3){

        if(isChecked1){
            point1.setBackgroundResource(R.drawable.point_off);
        }else{
            point1.setBackgroundResource(R.drawable.point_on);
        }

        if(isChecked2){
            point2.setBackgroundResource(R.drawable.point_off);
        }else{
            point2.setBackgroundResource(R.drawable.point_on);
        }


        if(isChecked3){
            point3.setBackgroundResource(R.drawable.point_off);
        }else{
            point3.setBackgroundResource(R.drawable.point_on);
        }


    }
}
