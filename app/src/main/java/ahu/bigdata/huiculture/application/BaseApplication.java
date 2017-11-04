package ahu.bigdata.huiculture.application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.tencent.bugly.crashreport.CrashReport;

import ahu.bigdata.huiculture.constant.Constant;
import cn.bmob.v3.Bmob;

/**
 * Created by ych10 on 2017/9/21.
 */
public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), Constant.BUGLY_APP_ID,true);

        //初始化Bmob
        Bmob.initialize(this, Constant.BMOB_APP_ID);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
