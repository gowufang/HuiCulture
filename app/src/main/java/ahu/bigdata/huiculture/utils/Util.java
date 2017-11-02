package ahu.bigdata.huiculture.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import ahu.bigdata.huiculture.module.recommand.RecommandBodyValue;

/**
 * Created by ych10 on 2017/9/21.
 * Function:工具统一类
 */
public class Util {

    /**
     * 将拼接后的数据再拆分成ArrayList
     */
    //为ViewPager结构化数据
    public static ArrayList<RecommandBodyValue> handleData(RecommandBodyValue value) {
        ArrayList<RecommandBodyValue> values = new ArrayList<>();
        String[] titles = value.title.split("@");
        String[] infos = value.info.split("@");
        String[] prices = value.price.split("@");
        String[] texts = value.text.split("@");
        ArrayList<String> urls = value.url;
        int start = 0;
        for (int i = 0; i < titles.length; i++) {
            RecommandBodyValue tempValue = new RecommandBodyValue();
            tempValue.title = titles[i];
            tempValue.info = infos[i];
            tempValue.price = prices[i];
            tempValue.text = texts[i];
            tempValue.url = extractData(urls, start, 3);
            start += 3;

            values.add(tempValue);
        }
        return values;
    }

    private static ArrayList<String> extractData(ArrayList<String> source, int start, int interval) {
        ArrayList<String> tempUrls = new ArrayList<>();
        for (int i = start; i < start + interval; i++) {
            tempUrls.add(source.get(i));
        }
        return tempUrls;
    }

    public static void hideSoftInputMethod(Context context, View v) {
        /* 隐藏软键盘 */
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }



     /****************Smartbutler*****************/
    //设置字体
    public static void setFonts(Context mContext, TextView textView){

        Typeface fontType= Typeface.createFromAsset(mContext.getAssets(),"fonts/FONT.TTF");
        textView.setTypeface(fontType);
    }
    //保存图片到ShareUtils下
    public static void putImageToShare(Context Contex,ImageView imageView){

        BitmapDrawable Drawable= (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap=Drawable.getBitmap();
        //1.将bitmap强转成字节输出流
        ByteArrayOutputStream byStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byStream);
        //2.利用Base64将我们的字节数组输出流转化成String
        byStream.toByteArray();
        byte[] byteArray = byStream.toByteArray();
        String imgString= new String(Base64.encode(byteArray, Base64.DEFAULT));
        //3.将String 保存ShareUtils
        ShareUtils.putString(Contex,"image_title",imgString);


    }
    //读取图片
    public static void getImageToshare(Context Context,ImageView imageView){

     /*1.拿到String*/
        String imgString=ShareUtils.getString( Context, "image_title", "");
        if (imgString!=null) {
         /*2.利用Base64将我们的String 转化*/
            byte [] byteArray= Base64.decode(imgString, Base64.DEFAULT);
            ByteArrayInputStream byStream = new ByteArrayInputStream(byteArray);
            /*3.生成bitmap*/
            Bitmap bitmap= BitmapFactory.decodeStream(byStream);
            imageView.setImageBitmap(bitmap);
        }

    }
}
