package com.example.mysdk.okhttp;

import com.example.mysdk.okhttp.listener.DisposeDataHandle;
import com.example.mysdk.okhttp.listener.DisposeDataListener;
import com.example.mysdk.okhttp.request.CommonRequest;
import com.example.mysdk.okhttp.response.CommonJsonCallback;

/**
 * Created by wwk on 17/6/11.
 */

public class BaseOkhttpTest {

    public void test() {
        CommonOkHttpClient.sendRequest(CommonRequest.createGetRequest("http://www.imooc.com", null),
                new CommonJsonCallback(new DisposeDataHandle(new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        
                    }

                    @Override
                    public void onFailure(Object responseObj) {

                    }
                })));
    }
}
