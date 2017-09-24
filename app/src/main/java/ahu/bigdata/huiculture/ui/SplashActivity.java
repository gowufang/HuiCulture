package ahu.bigdata.huiculture.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import ahu.bigdata.huiculture.MainActivity;
import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.base.BaseActivity;
import ahu.bigdata.huiculture.utils.ShareUtils;
import ahu.bigdata.huiculture.utils.StaticClass;

/**
 * Created by YCH on 2017/9/22.
 * Function:    1.延迟2000ms
 *              2.判断是否第一次运行
 *              3.自定义字体
 *              4.Avtivity全屏主题
 */
public class SplashActivity extends BaseActivity {

    /*1.延迟2000ms
    * 2.判断是否第一次运行
    * 3.自定义字体
    * 4.Avtivity主题*/
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否第一次运行
                    if (isFirst()) {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    }
                    finish();
                    break;

            }
        }
        //判断程序是否第一次运行
        private boolean isFirst() {

            boolean isFirst= ShareUtils.getBoolean(SplashActivity.this, StaticClass.SHARE_IS_FIRST, true);
            if (isFirst) {
                //更改标记
                ShareUtils.putBoolean(SplashActivity.this,StaticClass.SHARE_IS_FIRST,false);
                //是第一次运行
                return true;
            }else {

                return false;
            }
        }
    };
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }
    //初始化View
    private void initView() {
        //延迟2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);
//        tv_splash.setImageResource(R.drawable.ic_home_selected);
        //设置字体
//        UtilTools.setFonts(this,tv_splash);

    }

    //禁止返回键

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

    }

}
