package ahu.bigdata.huiculture.constant;

import android.Manifest;
import android.os.Environment;

/**
 * Created by ych10 on 2017/9/21.
 * Function:数据、常量
 */
public class Constant {

    //闪屏页的延迟
    public static final int HANDLER_SPLASH=1001;
    //判断程序是否第一次运行
    public static final String SHARE_IS_FIRST = "isFrst";
    //Bugly key
    public static final String BUGLY_APP_ID="97e1c61a8d";
    //微信精选key
    public static final String WECHAT_KEY="e7db9fe647fdfaade42f16f60b90dbaf";


    /**
     * 权限常量相关
     */
    public static final int WRITE_READ_EXTERNAL_CODE = 0x01;
    public static final String[] WRITE_READ_EXTERNAL_PERMISSION = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    public static final int HARDWEAR_CAMERA_CODE = 0x02;
    public static final String[] HARDWEAR_CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};

    //整个应用文件下载保存路径
    public static String APP_PHOTO_DIR = Environment.
            getExternalStorageDirectory().getAbsolutePath().
            concat("/imooc_business/photo/");


    public static String URL=""
}
