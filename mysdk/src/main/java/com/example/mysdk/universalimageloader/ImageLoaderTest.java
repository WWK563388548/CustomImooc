package com.example.mysdk.universalimageloader;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by wwk on 17/6/20.
 */

public class ImageLoaderTest {

    private void testApi() {

        /**
         * 为ImageLoader去配置参数
         * 使用构建者模式，来添加各种各样的属性
         * 看这里的源码，来学习构建者模式
         */
         // ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
         //       .Builder(context)
         //       .build();
        /**
         * 获取ImageLoader的实例
         * 默认配置
         */
        ImageLoader imageLoader = ImageLoader.getInstance();

        /**
         * 为显示图片进行配置
         * 使用构建者模式，来添加各种各样的属性
         */
        DisplayImageOptions options = new DisplayImageOptions.Builder().build();

        /**
         * 使用displayImage去加载图片
         * url 不仅可以是网络链接，也可以是本地图片的地址
         * SimpleImageLoadingListener可以监听图片的下载过程，例如：下载中，下载完成等等
         */
         // imageLoader.displayImage("url", imageView, options,
         //       new SimpleImageLoadingListener(){
                    // 常用方法, 接口回调

         //           @Override
         //           public void onLoadingCancelled(String imageUri, View view) {
         //               super.onLoadingCancelled(imageUri, view);
         //           }

         //           @Override
         //           public void onLoadingStarted(String imageUri, View view) {
         //               super.onLoadingStarted(imageUri, view);
         //           }

         //           @Override
         //           public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
         //               super.onLoadingComplete(imageUri, view, loadedImage);
         //           }

         //           @Override
         //           public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
         //               super.onLoadingFailed(imageUri, view, failReason);
         //           }
         //       });
    }
}
