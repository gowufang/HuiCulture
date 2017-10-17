package com.youdu.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.youdu.adutil.ResponseEntityToModule;
import com.youdu.okhttp.exception.OkHttpException;
import com.youdu.okhttp.listener.DisposeDataHandle;
import com.youdu.okhttp.listener.DisposeDataListener;
import com.youdu.okhttp.listener.DisposeHandleCookieListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * @author vision
 * @function 专门处理JSON的回调
 */
public class CommonJsonCallback implements Callback {

    /**
     * 与服务器返回的字段一个的对应关系
     * the logic layer exception, may alter in different app
     */
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
    // can has the value of
    // set-cookie2

    /**
     * 自定义异常类型
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;//进行消息的转发，将子线程的消息转发到UI线程
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(final Call call, final IOException ioexception) {
        /**
         * 此时还在非UI线程，因此要转发
         */
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception));
            }
        });
    }

    //真正的相应处理函数
    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        //Json的处理
        final String result = response.body().string();
        final ArrayList<String> cookieLists = handleCookie(response.headers());
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
                /**
                 * handle the cookie
                 */
                if (mListener instanceof DisposeHandleCookieListener) {
                    ((DisposeHandleCookieListener) mListener).onCookie(cookieLists);
                }
            }
        });
    }

    private ArrayList<String> handleCookie(Headers headers) {
        ArrayList<String> tempList = new ArrayList<String>();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)) {
                tempList.add(headers.value(i));
            }
        }
        return tempList;
    }

    /**
     * 处理服务器返回的响应数据
     */
    private void handleResponse(Object responseObj) {

        //为了保证代码的健壮性
        if (responseObj == null || responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
//
//        /****************************我自己的********************************/
//        //尝试解析
//        try {
//            JSONArray jsonArray = new JSONArray(responseObj.toString());
//            for (int i = 0; i < jsonArray.length(); i++) {
//                //尝试解析
//                try {
//                    JSONObject result = jsonArray.getJSONObject(i);
//                    if (mClass == null) {
//                        mListener.onSuccess(responseObj);
//                    } else {
//
//                        if (result.has("isdir")) {
//                            if (result.getBoolean("isdir") == false) {
//                                //实例化
//                                Object obj = ResponseEntityToModule.parseJsonObjectToModule(result, mClass);
//                                if (obj != null) {
//                                    mListener.onSuccess(obj);
//                                } else {
//                                    mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
//                                }
//                            } else {
//                                //如果是目录不实例化
//                            }
//
//                        } else {
//                            //实例化
//                            Object obj = ResponseEntityToModule.parseJsonObjectToModule(result, mClass);
//                            if (obj != null) {
//                                mListener.onSuccess(obj);
//                            } else {
//                                mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
//                            }
//                        }
//                    }
//                } catch (JSONException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        /****************************以下为老师的******************************/
        try {
            /**
             * 协议确定后看这里如何修改
             */
            JSONObject result = new JSONObject(responseObj.toString());
            //开始尝试解析
            if (result.has(RESULT_CODE)) {
                //从Json对象中取出响应码，若为0，则是正确的响应。
                if (result.getInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    //不需要解析
                    if (mClass == null) {
                        mListener.onSuccess(responseObj);
                    } else {
                        //需要我们将实体转化成实体对象
                        Object obj = ResponseEntityToModule.parseJsonObjectToModule(result, mClass);
                        //表明正确的转化成了实体对象
                        if (obj != null) {
                            mListener.onSuccess(obj);
                        } else {
                            //返回的不是合法的Json
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                }

            } else {
                //将服务器返回给我们的异常回调到应用中处理
                mListener.onFailure(new OkHttpException(OTHER_ERROR, result));
            }
        } catch (Exception e) {
            //健壮性
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }





    }
    }
