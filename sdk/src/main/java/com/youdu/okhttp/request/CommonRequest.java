package com.youdu.okhttp.request;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author vision
 * @function build the request
 */
public class CommonRequest {
    /**
     * create the key-value Request
     *
     * @param url
     * @param params
     * @return 返回一个创建好的Request对象
     */
    public static Request createPostRequest(String url, RequestParams params) {

        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if (params != null) {
            /**
             *  for (循环变量类型 循环变量名称 : 要被遍历的对象) 循环体
             */
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {

                //将请求参数遍历添加到请求构建类中
                mFormBodyBuild.add(entry.getKey(), entry.getValue());
            }
        }
        //通过请求构建类的build方法获得真正的请求体对象
        FormBody mFormBody = mFormBodyBuild.build();
        return new Request.Builder().url(url).post(mFormBody).build();
    }

    /**
     * @param url
     * @param params
     * @return 返回一个创建好的Get对象,将Url和params生成字符串再生成Get类型Request的get对象
     */
    public static Request createGetRequest(String url,RequestParams params) {

        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params!=null) {

            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }

        }
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).get().build();

    }


    /**可以带请求头的Post请求
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params, RequestParams headers) {
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if (params != null) {
            /**
             *  for (循环变量类型 循环变量名称 : 要被遍历的对象) 循环体
             */
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {

                //将请求参数遍历添加到请求构建类中
                mFormBodyBuild.add(entry.getKey(), entry.getValue());
            }
        }
        //添加请求头
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody mFormBody = mFormBodyBuild.build();
        Headers mHeader = mHeaderBuild.build();
        Request request = new Request.Builder().url(url).
                post(mFormBody).
                headers(mHeader)
                .build();
        return request;
    }


    /**
     * 可以带请求头的Get请求
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params, RequestParams headers) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        //添加请求头
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }
        Headers mHeader = mHeaderBuild.build();
        return new Request.Builder().
                url(urlBuilder.substring(0, urlBuilder.length() - 1))
                .get()
                .headers(mHeader)
                .build();
    }

    /**
     * @param url
     * @param params
     * @return
     */
    public static Request createMonitorRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("&");
        if (params != null && params.hasParams()) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).get().build();
    }

//    /**
//     * 文件上传请求
//     *
//     * @return
//     */
//    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");
//
//    public static Request createMultiPostRequest(String url, RequestParams params) {
//
//        MultipartBody.Builder requestBody = new MultipartBody.Builder();
//        requestBody.setType(MultipartBody.FORM);
//        if (params != null) {
//
//            for (Map.Entry<String, Object> entry : params.fileParams.entrySet()) {
//                if (entry.getValue() instanceof File) {
//                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
//                            RequestBody.create(FILE_TYPE, (File) entry.getValue()));
//                } else if (entry.getValue() instanceof String) {
//
//                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
//                            RequestBody.create(null, (String) entry.getValue()));
//                }
//            }
//        }
//        return new Request.Builder().url(url).post(requestBody.build()).build();
//    }
}