package com.example.mysdk.okhttp.listener;

/**
 * Created by wwk on 17/6/14.
 * 自定义事件监听
 */

public interface DisposeDataListener {

    /**
     * 请求成功回调事件处理
     */
    public void onSuccess(Object responseObj);

    /**
     * 请求失败回调事件处理
     */
    public void onFailure(Object responseObj);

}
