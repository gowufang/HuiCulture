package ahu.bigdata.huiculture.network.http;

import ahu.bigdata.huiculture.constant.Constant;

/**
 * Created by ych10 on 2017/10/3.
 * Function:所有请求相关地址
 */
public class HttpConstants {

    /**
     * 测试网址
     */
    private static final String TEST_URL = "hz-api.cnwangjie.com";
    /**
     * 所有静态资源列表
     */
    public static final String VIDEO_LIST = "/api/resource/list";
    /**
     * 最新文章列表
     */
    public static final String LASTED_ARTICLE = "/api/article/lastest";


    /**
     * 方便切换服务器地址
     */
    private static final String ROOT_URL = "http://imooc.com/api";

    /**
     * 请求本地产品列表
     */
    public static String PRODUCT_LIST = ROOT_URL + "/fund/search.php";

    /**
     * 本地产品列表更新时间措请求
     */
    public static String PRODUCT_LATESAT_UPDATE = ROOT_URL + "/fund/upsearch.php";

    /**
     * 登陆接口
     */
    public static String LOGIN = ROOT_URL + "/user/login_phone.php";

    /**
     * 检查更新接口
     */
    public static String CHECK_UPDATE = ROOT_URL + "/config/check_update.php";

    /**
     * 首页产品请求接口
     */
    public static String HOME_RECOMMAND = ROOT_URL + "/product/home_recommand.php";

    /**
     * 课程详情接口
     */
    public static String COURSE_DETAIL = ROOT_URL + "/product/course_detail.php";


    public static String HISTORY_PAGE = ROOT_URL + "/history/history.php";
}
