package ahu.bigdata.huiculture.application;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import ahu.bigdata.huiculture.constant.Constant;
import cn.bmob.v3.Bmob;

/**
 * Created by ych10 on 2017/9/21.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), Constant.BUGLY_APP_ID,true);

        //初始化Bmob
        Bmob.initialize(this, Constant.BMOB_APP_ID);
    }
}
