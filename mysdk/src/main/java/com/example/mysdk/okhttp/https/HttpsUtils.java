package com.example.mysdk.okhttp.https;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by wwk on 17/6/11.
 */

public class HttpsUtils {

    /**
     * 相信所有https证书（包括自定义证书或官方证书）
     * @return
     */
    public static SSLSocketFactory getSslSocketFactory() {

        // 1. 生成一个信任管理器类
        X509TrustManager mTrustManager = new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

        };

        // 2.创建SSLContext(加密上下文)
        SSLContext sslContext = null;
        try {
            // 与服务器保持一致的算法类型
            sslContext = SSLContext.getInstance("SSL"); // 若服务器使用SSL就用SSL，若服务器是TXL，则为TXL
            X509TrustManager[] xTrustArray = new X509TrustManager[] {mTrustManager};
            sslContext.init(null, xTrustArray, new SecureRandom());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sslContext.getSocketFactory();
    }
}
