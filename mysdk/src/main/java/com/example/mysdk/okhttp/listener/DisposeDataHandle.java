package com.example.mysdk.okhttp.listener;

/**
 * Created by wwk on 17/6/14.
 * 预处理CommonJsonCallback
 */

public class DisposeDataHandle {

    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;

    public DisposeDataHandle(DisposeDataListener listener) {

        this.mListener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener, Class<?> clazz) {

        this.mListener = listener;
        this.mClass = clazz;
    }
}
