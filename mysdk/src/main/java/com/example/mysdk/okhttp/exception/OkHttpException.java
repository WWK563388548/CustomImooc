package com.example.mysdk.okhttp.exception;

/**
 * Created by wwk on 17/6/14.
 * 自定义异常类,返回ecode,emsg到业务层
 */

public class OkHttpException extends Exception{

    private static final long serialVersionUID = 1L;

    /**
     * 服务器返回代码
     */
    private int ecode;

    /**
     * 服务器返回的错误信息
     */
    private Object emsg;

    public OkHttpException(int ecode, Object emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }
}
