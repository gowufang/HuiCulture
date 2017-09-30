package com.youdu.imageloader;

import android.graphics.Bitmap;
import android.view.View;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by YCH on 2017/9/30.
 * Function:
 */
public class ImgageLoader {

    private void testApi() {

        /**
         * 为ImageLoader配置参数
         */
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context).build();
        /**
         *获取ImageLoader实例
         */
        ImageLoader imageLoader = ImageLoader.getInstance();

        /**
         *  在显示图片时配置
         */
        DisplayImageOptions options = new DisplayImageOptions.Builder().build();

        /**
         *  使用DisplayImage显示图片
         */
        imageLoader.displayImage("url",imageview,options,new SimpleImageLoadingListener(){

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                super.onLoadingCancelled(imageUri, view);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                super.onLoadingFailed(imageUri, view, failReason);
            }

            @Override
            public void onLoadingStarted(String imageUri, View view) {
                super.onLoadingStarted(imageUri, view);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
            }
        });
    }
}
