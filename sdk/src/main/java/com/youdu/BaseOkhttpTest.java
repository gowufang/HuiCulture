package com.youdu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.youdu.okhttp.CommonOkHttpClient;
import com.youdu.okhttp.listener.DisposeDataHandle;
import com.youdu.okhttp.listener.DisposeDataListener;
import com.youdu.okhttp.request.CommonRequest;
import com.youdu.okhttp.response.CommonJsonCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by YCH on 2017/9/30.
 * Function:测试Okhttp
 */
public class BaseOkhttpTest extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *用Okhttp发送一个基本的请求
     */

    private  void sendRequest(){

        //创建Okhttp对象
        OkHttpClient mOkhttpClient = new OkHttpClient();
        //构建者模式，创建一个Request对象
        Request request = new Request.Builder().url("https://www.imooc.com/").build();
        //new Call
        Call call = mOkhttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {

            //请求失败
            @Override
            public void onFailure(Call call, IOException e) {

            }
            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
    private void test() {

        /**
         *无论第三方控件用什么
         */
        CommonOkHttpClient.sendRequest(CommonRequest.createGetRequest("http://www.baidu.com", null),
                new CommonJsonCallback(new DisposeDataHandle(new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {

                    }

                    @Override
                    public void onFailure(Object reasonObj) {

                    }
                })));

    }
}
