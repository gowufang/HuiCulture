package ahu.bigdata.huiculture.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import ahu.bigdata.huiculture.R;

/**
 * @author: ych10.
 * @date: 2017/10/2.
 * @Function: 初始化UniverseImageLoader, 加载网络图片
 */
public class ImageLoaderManager {

    private static final int THREAD_COUNT = 4;//UIL最多可有多少条线程
    private static final int PROPRITY = 2;//加载图片的优先级。比版本高
    private static final int DISK_CACHE_SIZE = 50 * 1024;//表明UIL可以存多少张图片
    private static final int CONNECTION_TIME_OUT = 5 * 1000;//连接的超时时间
    private static final int READ_TIME_OUT = 30 * 1000;//读取的超时时间

    private static ImageLoader mImageLoader = null;
    private static ImageLoaderManager mInstance = null;

    public static ImageLoaderManager getInstance(Context mContex) {
        if (mContex == null) {
            synchronized (ImageLoaderManager.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderManager(mContex);
                }

            }
        }
        return mInstance;
    }

    /**
     * Created by ych10 on 2017/10/2
     * Fuction:单例模式的私有构造方法
     */
    private ImageLoaderManager(Context contex) {

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.
                Builder(contex).
                threadPoolSize(THREAD_COUNT).//配置图片下载线程的最大数量
                threadPriority(Thread.NORM_PRIORITY - PROPRITY).//系统不同，优先级不同，降级
                denyCacheImageMultipleSizesInMemory().//防止缓存多套尺寸图片
                memoryCache(new WeakMemoryCache()).//使用弱引用，在内存不足时回收图片
                diskCacheSize(DISK_CACHE_SIZE).//分配硬盘缓存的大小
                diskCacheFileNameGenerator(new Md5FileNameGenerator()).//使用MD5命名文件，更安全
                tasksProcessingOrder(QueueProcessingType.FIFO).//图片下载次序
                defaultDisplayImageOptions(getDefultOption()).//默认图片下载Option
                imageDownloader(new BaseImageDownloader(contex, CONNECTION_TIME_OUT, READ_TIME_OUT)).//设置图片下载器
                writeDebugLogs().//debug环境下会输出日志
                build();
        ImageLoader.getInstance().init(configuration);
        mImageLoader = ImageLoader.getInstance();


    }

    /**
     * 实现我们默认的Option
     *
     * @return
     */
    private DisplayImageOptions getDefultOption() {

        DisplayImageOptions option = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.anhui)
                .showImageOnFail(R.drawable.anhui)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)//使用的解码类型,降低图片色彩，减少内存占用
                .decodingOptions(new BitmapFactory.Options())//系统自带图片解码配置
                .build();
        return option;
    }


    /**
     * 加载图片API
     *
     * @param imageView
     * @param url
     * @param options
     * @param listener
     */
    public void DispalyImage(ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener listener) {

        if (mImageLoader != null) {

            mImageLoader.displayImage(url, imageView, options, listener);

        }
    }

    public void displayImage(ImageView imageview, String url, ImageLoadingListener listener) {

        DispalyImage(imageview, url, null, listener);

    }

    public void displayImage(ImageView ImageView, String url) {

        DispalyImage(ImageView,url,null,null);
    }

}
