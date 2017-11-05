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
    public static final String BUGLY_APP_ID="10bcd16993";
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

    /**
     * 以下为Clound界面的静态常量，方便扩展
     */

    public static  final int logoCount=12;
    //Cloud页面圆形图片加载地址
    public static final String[] LogoImgUrl = new String[]{

            "http://hz.cnwangjie.com/static/%E5%BE%BD%E5%95%86.jpg", "http://hz.cnwangjie.com/static/%E5%BE%BD%E5%B7%9E%E5%AE%97%E6%97%8F.jpg",
            "http://hz.cnwangjie.com/static/%E6%96%B0%E5%AE%89%E7%90%86%E5%AD%A6.jpg", "http://hz.cnwangjie.com/static/%E6%96%B0%E5%AE%89%E5%8C%BB%E5%AD%A6.jpg",
            "http://hz.cnwangjie.com/static/%E6%96%B0%E5%AE%89%E7%94%BB%E6%B4%BE.jpg", "http://hz.cnwangjie.com/static/%E5%BE%BD%E5%B7%9E%E6%96%87%E4%B9%A6.jpg",
            "http://hz.cnwangjie.com/static/%E5%BE%BD%E6%B4%BE%E6%9C%B4%E5%AD%A6.jpg", "http://hz.cnwangjie.com/static/%E5%BE%BD%E6%B4%BE%E7%89%88%E7%94%BB.jpg",
            "http://hz.cnwangjie.com/static/%E5%BE%BD%E6%B4%BE%E5%BB%BA%E7%AD%91.jpg", "http://hz.cnwangjie.com/static/%E5%BE%BD%E5%B7%9E%E4%B8%89%E9%9B%95.jpg",
            "http://hz.cnwangjie.com/static/%E6%96%87%E6%88%BF%E5%9B%9B%E5%AE%9D.jpg", "http://hz.cnwangjie.com/static/%E5%BE%BD%E5%B7%9E%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9.jpg"
    };

    public static final String[] LogoTextUrl = new String[]{
            "徽商", "徽州宗族",
            "新安理学", "新安医学",
            "新安画派", "徽州文书",
            "徽州朴学", "徽派版画",
            "徽派建筑", "徽州三雕",
            "文房四宝", "历史人物"
    };

    public static final String[] LogoWebUrl = new String[]{
            "http://hz.cnwangjie.com/#/class/徽商",
            "http://hz.cnwangjie.com/#/class/徽州宗族",
            "http://hz.cnwangjie.com/#/class/新安理学",
            "http://hz.cnwangjie.com/#/class/新安医学",
            "http://hz.cnwangjie.com/#/class/新安画派",
            "http://hz.cnwangjie.com/#/class/新安文书",
            "http://hz.cnwangjie.com/#/class/新安朴学",
            "http://hz.cnwangjie.com/#/class/新安版画",
            "http://hz.cnwangjie.com/#/class/徽派建筑",
            "http://hz.cnwangjie.com/#/class/徽州三雕",
            "http://hz.cnwangjie.com/#/class/文房四宝",
            "http://hz.cnwangjie.com/#/class/徽州历史人物"
    };
}
