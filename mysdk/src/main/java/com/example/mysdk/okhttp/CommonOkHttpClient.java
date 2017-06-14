package com.example.mysdk.okhttp;

import com.example.mysdk.okhttp.https.HttpsUtils;
import com.example.mysdk.okhttp.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by wwk on 17/6/10.
 *
 * OkHttp的核心
 * 发送get, post请求的工具类,
 * 设置一些请求的共用参数
 * (所有头参数放入request中)
 */

public class CommonOkHttpClient {

    // 超时参数
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    // 为Client配置参数
    static {
        // 创建Client对象的构建者
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        // 为构建者填充超时时间
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);

        okHttpBuilder.followRedirects(true);

        // 添加https支持
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        okHttpBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory());
        // 生成Client对象
        mOkHttpClient = okHttpBuilder.build();
    }

    /**
     * 发送具体的http/https请求
     * @param request
     * @param commonCallback
     * @return Call
     */
    public static Call sendRequest(Request request, CommonJsonCallback commonCallback) {

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commonCallback);

        return call;
    }
}
