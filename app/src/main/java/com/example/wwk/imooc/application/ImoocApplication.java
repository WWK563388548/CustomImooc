package com.example.wwk.imooc.application;

import android.app.Application;

/**
 * Created by wwk on 17/6/9.
 *
 * 1. 整个程序的入口
 * 2. 初始化工作
 * 3. 为整个应用的其他模块提供上下文
 */

public class ImoocApplication extends Application {

    private static ImoocApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static ImoocApplication getInstance() {
        return mApplication;
    }
}
