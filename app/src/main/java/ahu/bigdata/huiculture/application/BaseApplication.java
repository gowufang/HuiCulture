package ahu.bigdata.huiculture.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.tencent.bugly.crashreport.CrashReport;

import ahu.bigdata.huiculture.constant.Constant;

/**
 * Created by ych10 on 2017/9/21.
 */
public class BaseApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), Constant.BUGLY_APP_ID,true);
    }

}
