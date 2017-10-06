package ahu.bigdata.huiculture.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by YCH on 2017/9/27.
 * Function:Picasso实体类封装
 */

public class PicassoUtils {
/**
 *默认加载图片
 */
    public static void loadImagView(Context mContex, String Url, ImageView imageView) {

        /**
         *加载Picasso
         */
        Picasso.with(mContex).load(Url).into(imageView);
    }
    /**
     *默认加载指定大小图片
     */
    public static void loadImagViewSize(Context mContex, String Url,int width,int height, ImageView imageView) {
        Picasso.with(mContex).load(Url).resize(width, height).centerCrop().into(imageView);
    }
    /**
     *加载图片有默认图片
     */
    public static void loadImagViewHolder(Context mContex, String Url,int loadImg,int errorImg, ImageView imageView) {
        Picasso.with(mContex).load(Url).placeholder(loadImg).error(errorImg).into(imageView);
    }
    /**
     * 裁剪图片
     */
    public static void loadImgViewCrop(Context mContext,String Url,ImageView imageView) {

        Picasso.with(mContext).load(Url).transform(new CropSquareTransformation()).into(imageView);


    }
    public static class CropSquareTransformation implements Transformation {
        @Override public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override public String key() {
            return "square()"; }
    }
}
