package ahu.bigdata.huiculture.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.tencent.bugly.crashreport.CrashReport;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.activity.base.BaseActivity;
import ahu.bigdata.huiculture.fragment.home.CloudFragment;
import ahu.bigdata.huiculture.fragment.home.HomeFragment;
import ahu.bigdata.huiculture.fragment.home.PersonFragment;

/**
 * 创建所有Fragment，Fragment的切换
 */
public class HomeActivity extends BaseActivity {
    /**
     * BottomNavigationView
     */
    BottomNavigationView navigation;
    /**
     * Fragment
     */
    private FragmentManager fm;
    private HomeFragment mHomeFragment;
    private CloudFragment mCloudFragment;
    private PersonFragment mPersonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initData();
        initView();

    }

    private void initData() {

        /**
         * 添加默认显示的Fragment
         */
        mHomeFragment = new HomeFragment();
        fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content, mHomeFragment);
        fragmentTransaction.commit();
        /**
         *状态栏、ActionBar设置
         */
        //取消状态栏透明
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //显示状态栏
        //添加Flag把状态栏设为可绘制模式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置系统状态栏处于可见状态
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置状栏颜色为灰色
            getWindow().setStatusBarColor(Color.GRAY);
        }
        getSupportActionBar().hide();//隐藏ActionBar

    }
    private void initView() {

        /**
         * 初始化页面中所得控件
         */
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            navigation.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY<0) {

                        navigation.setVisibility(View.GONE);
                    }
                }
            });
        }
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            /**
             * 用来隐藏Fragment
             */
            private void hideFragment(Fragment Fragment, FragmentTransaction ft) {
                if (Fragment != null) {
                    ft.hide(Fragment);
                }
            }

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.navigation_home:

                        //防止重影
                        hideFragment(mCloudFragment, fragmentTransaction);
                        hideFragment(mPersonFragment, fragmentTransaction);
                        //显示HomeFragment
                        if (mHomeFragment == null) {
                            mHomeFragment = new HomeFragment();
                            fragmentTransaction.add(R.id.content, mHomeFragment);
                        } else {
                            //已经创建
                            fragmentTransaction.show(mHomeFragment);
                            //隐藏其他两个Fragment
                            hideFragment(mCloudFragment, fragmentTransaction);
                            hideFragment(mPersonFragment, fragmentTransaction);
                        }
                        break;

                    case R.id.navigation_cloud:
                        hideFragment(mHomeFragment, fragmentTransaction);
                        hideFragment(mPersonFragment, fragmentTransaction);
                        //显示CloudFragment
                        if (mCloudFragment == null) {
                            mCloudFragment = new CloudFragment();
                            fragmentTransaction.add(R.id.content, mCloudFragment);
                        } else {
                            //已经创建
                            //隐藏其他两个Fragment
                            hideFragment(mHomeFragment, fragmentTransaction);
                            hideFragment(mPersonFragment, fragmentTransaction);
                            fragmentTransaction.show(mCloudFragment);

                        }
                        break;
                    case R.id.navigation_person:
                        //隐藏其他两个Fragment
                        hideFragment(mHomeFragment, fragmentTransaction);
                        hideFragment(mCloudFragment, fragmentTransaction);
                        //显示PersonFragment
                        if (mPersonFragment == null) {
                            mPersonFragment = new PersonFragment();
                            fragmentTransaction.add(R.id.content, mPersonFragment);
                        } else {
                            //已经创建
                            //隐藏其他两个Fragment
                            hideFragment(mHomeFragment, fragmentTransaction);
                            hideFragment(mCloudFragment, fragmentTransaction);
                            fragmentTransaction.show(mPersonFragment);

                        }
                        break;
                }
                fragmentTransaction.commit();
                return true;
            }
        });

   }

}
