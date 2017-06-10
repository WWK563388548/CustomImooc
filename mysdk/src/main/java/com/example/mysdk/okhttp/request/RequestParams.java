package com.example.mysdk.okhttp.request;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wwk on 17/6/10.
 *
 * 封装所有请求参数到HashMap中，方便使用
 */

public class RequestParams {

    public ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<String, String>();
    public ConcurrentHashMap<String, Object> fileParams = new ConcurrentHashMap<String, Object>();

    /**
     * 构建一个新的 {@code RequestParams} 实例.
     */
    public RequestParams() {
        this((Map<String, String>) null);
    }

    /**
     * 构建一个新的equestParams实例，它包含从特定图中得到的键/值对参数(字符串)
     *
     * @param source 键/值对的来源
     */
    public RequestParams(Map<String, String> source) {
        if (source != null) {
            for (Map.Entry<String, String> entry : source.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 构建一个新的RequestParams实例，然后加入一个作为初始参数的键/值对
     *
     * @param key   初始参数的名字
     * @param value 初始参数的字符串值
     */
    public RequestParams(final String key, final String value) {
        this(new HashMap<String, String>() {
            {
                put(key, value);
            }
        });
    }

    /**
     * 向请求添加键/值对
     *
     * @param key   新参数的名字
     * @param value 新参数的字符串值
     */
    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }

    public void put(String key, Object object) throws FileNotFoundException {

        if (key != null) {
            fileParams.put(key, object);
        }
    }

    public boolean hasParams() {
        if(urlParams.size() > 0 || fileParams.size() > 0){

            return true;
        }
        return false;
    }
}
