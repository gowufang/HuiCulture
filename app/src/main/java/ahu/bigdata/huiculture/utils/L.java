package ahu.bigdata.huiculture.utils;

import android.util.Log;

/**
 * Created by YCH on 2017/9/22.
 * Function:LOG封装类
 */
public class L {
    //开关
    public static final boolean DEBUG = true;
    //TAG
    public static final String TAG="HuiCulture";
    //五个等级 DWIE
    public static void d(String text){
        if (DEBUG){
            Log.d(TAG, text);
        }

    }
    public static void w(String text){
        if (DEBUG){
            Log.w(TAG, text);
        }

    }
    public static void i(String text){
        if (DEBUG){
            Log.i(TAG, text);
        }

    }
    public static void e(String text){
        if (DEBUG){
            Log.e(TAG, text);
        }

    }
}
