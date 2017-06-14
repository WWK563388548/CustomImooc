package com.example.mysdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.example.mysdk.okhttp.exception.OkHttpException;
import com.example.mysdk.okhttp.listener.DisposeDataHandle;
import com.example.mysdk.okhttp.listener.DisposeDataListener;
import com.example.mysdk.util.ResponseEntityToModule;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by wwk on 17/6/14.
 */

public class CommonJsonCallback implements Callback {

    // 与服务器返回的字段进行对应
    protected final String RESULT_CODE = "ecode";
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    /**
     * 自定义的异常类型
     *
     * NETWORK_ERROR: 网络错误
     * JSON_ERROR: Json错误
     * OTHER_ERROR: 未知的错误
     */
    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;

    // 将其它线程的数据转发到UI线程
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {

        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    // 处理请求失败
    @Override
    public void onFailure(final Call call, final IOException e) {

        // 此时还在非UI线程，因此要转发消息
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    // 处理响应
    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    /**
     * 处理服务器返回的响应数据
     * @param responseObj
     */
    private void handleResponse(Object responseObj) {

        // 保证健壮性
        if (responseObj == null && responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

        try {
            JSONObject result = new JSONObject(responseObj.toString());
            // 开始解析Json
            if (result.has(RESULT_CODE)) {
                // 从Json中获取响应码，若为0，则为正确的响应
                if (result.getInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    // 不需要解析，直接返回数据到应用层
                    if (mClass == null) {
                        mListener.onSuccess(responseObj);
                    } else {
                        // 需要将Json对象，转换为实体对象
                        Object obj = ResponseEntityToModule.parseJsonObjectToModule(result, mClass);
                        // 不为空表明正确的转为了实体对象
                        if (obj != null) {
                            mListener.onSuccess(obj);
                        } else {
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                } else {
                    mListener.onFailure(new OkHttpException(OTHER_ERROR, result.get(RESULT_CODE)));
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }

    }
}
