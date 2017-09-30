package com.youdu.okhttp;

import com.youdu.okhttp.cookie.SimpleCookieJar;
import com.youdu.okhttp.https.HttpsUtils;
import com.youdu.okhttp.listener.DisposeDataHandle;
import com.youdu.okhttp.response.CommonFileCallback;
import com.youdu.okhttp.response.CommonJsonCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author qndroid
 * @function 请求的发送，请求参数的配置，https的配置
 */
public class CommonOkHttpClient {

    private static final int TIME_OUT = 30;//超时参数
    private static OkHttpClient mOkHttpClient;

    /**
     *采用静态语句块给Okhttp配置一致参数
     */
    static {
        //创建Client对象构建者
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        //为个构建者填充超时时间
        okHttpClientBuilder.cookieJar(new SimpleCookieJar());
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        //允许重定向
        okHttpClientBuilder.followRedirects(true);

        //https支持
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        /**
         *  为所有请求添加请求头，看个人需求
         */
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("User-Agent", "Imooc-Mobile") // 标明发送本次请求的客户端
                        .build();
                return chain.proceed(request);
            }
        });

        /**
         * trust all the https point
         */
        okHttpClientBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
        //生成Client对象
        mOkHttpClient = okHttpClientBuilder.build();
    }

    /**
     * 通过构造好的Request,Callback去发送请求
     *
     * @param request
     * @param commCallback
     */
    public static Call sendRequest(Request request, CommonJsonCallback commCallback) {

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commCallback);
        return call;

    }


    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

//    /**
//     * 指定cilent信任指定证书
//     *
//     * @param certificates
//     */
//    public static void setCertificates(InputStream... certificates) {
//        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null)).build();
//    }


    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call downloadFile(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }
}