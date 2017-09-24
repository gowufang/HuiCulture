package ahu.bigdata.huiculture.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by YCH on 2017/9/22.
 * Function:SharedPreferences封装
 */
public class ShareUtils {



    public static final String NAME = "config";

    public static void putString(Context mContext, String key, String values){

        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putString(key, values).commit();
    }
    //通过键 获取默认值
    public static String getString (Context mContext,String key,String defValues){

        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);

        return sp.getString(key, defValues);
    }


    public static void putInt(Context mContext,String key,int values){

        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putInt(key, values).commit();
    }
    //通过键 获取默认值
    public static int getString (Context mContext,String key,int defValues){

        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);

        return sp.getInt(key, defValues);
    }


    public static void putBoolean(Context mContext,String key,Boolean values){

        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, values).commit();
    }
    //通过键 获取默认值
    public static Boolean getBoolean (Context mContext,String key,Boolean defValues){

        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);

        return sp.getBoolean(key, defValues);
    }
    //删除 单个
    public static void delShar(Context mContext,String key){

        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }
    //删除全部
    public static void delAll(Context mContext){

        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    /* private void test(Context context){

        SharedPreferences sp=context.getSharedPreferences("config",context.MODE_PRIVATE);
        sp.getString("key","未获取到")
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("key","value");
        editor.commit();

    }*/

}
