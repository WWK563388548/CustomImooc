package com.example.mysdk.universalimageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.mysdk.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by wwk on 17/6/21.
 *
 * @function: 初始化UniversalImageLoader，并用来加载网络图片
 */

public class ImageLoaderManager {

    /**
     * 默认的参数值
     */
    // 表明最多线程数
    private static final int THREAD_COUNT = 4;
    // 图片加载优先级
    private static final int PROPRITY = 2;
    // 最多缓存图片数量
    private static final int DISK_CACHE_SIZE = 50 * 1024;
    // 连接超时时间（10 seconds）
    private static final int CONNECTION_TIME_OUT = 10 * 1000;
    // 读取超时时间 (30 seconds)
    private static final int READ_TIME_OUT = 30 * 1000;

    private static ImageLoader mLoader = null;
    private static ImageLoaderManager mInstance = null;

    public static ImageLoaderManager getmInstance(Context context) {

        if (mInstance == null) {
            synchronized (ImageLoaderManager.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 私有构造方法完成初始化
     * @param context
     */
    private ImageLoaderManager(Context context) {

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                // 配置图片下载线程的最大数量
                .threadPoolSize(THREAD_COUNT)
                .threadPriority(Thread.NORM_PRIORITY - PROPRITY)
                // 防止在内存中缓存多套尺寸的图片
                .denyCacheImageMultipleSizesInMemory()
                // 使用弱引用内存缓存，内存不足时会自动释放资源
                .memoryCache(new WeakMemoryCache())
                // 分配硬盘缓存大小
                .diskCacheSize(DISK_CACHE_SIZE)
                // 使用MD5命名文件
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                // 图片下载顺序
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                // 默认的图片加载的options
                .defaultDisplayImageOptions(getDefaultOptions())
                // 设置图片下载器
                .imageDownloader(new BaseImageDownloader(context, CONNECTION_TIME_OUT, READ_TIME_OUT))
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(configuration);
        mLoader = ImageLoader.getInstance();
    }

    /**
     * 默认的图片显示Options,可设置图片的缓存策略，编解码方式等，非常重要
     * @return
     */
    private DisplayImageOptions getDefaultOptions() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // 加载的图片为空的时候，加载错误图片
                .showImageForEmptyUri(R.drawable.xadsdk_img_error)
                // 下载图片失败的时候，加载错误图片
                .showImageOnFail(R.drawable.xadsdk_img_error)
                // 设置图片可以缓存在内存
                .cacheInMemory(true)
                // 设置图片可以缓存在硬盘
                .cacheOnDisk(true)
                // 设置图片的解码类型
                .bitmapConfig(Bitmap.Config.RGB_565)
                // 设置图片的解码配置
                .decodingOptions(new BitmapFactory.Options())
                .build();

        return options;
    }

    /**
     * 加载图片API
     * @param imageView
     * @param url
     * @param options
     * @param listener
     */
    public void displayImage(ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener listener) {

        if (mLoader != null) {
            mLoader.displayImage(url, imageView, options, listener);
        }
    }

    public void displayImage(ImageView imageView, String url, ImageLoadingListener listener) {

        displayImage(imageView, url, null, listener);
    }

    public void displayImage(ImageView imageView, String url) {

        displayImage(imageView, url, null);
    }
}
