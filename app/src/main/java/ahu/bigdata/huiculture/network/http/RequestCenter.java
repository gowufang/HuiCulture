package ahu.bigdata.huiculture.network.http;

import com.youdu.okhttp.CommonOkHttpClient;
import com.youdu.okhttp.listener.DisposeDataHandle;
import com.youdu.okhttp.listener.DisposeDataListener;
import com.youdu.okhttp.listener.DisposeDownloadListener;
import com.youdu.okhttp.request.CommonRequest;
import com.youdu.okhttp.request.RequestParams;
import ahu.bigdata.huiculture.module.recommand.BaseRecommandModel;
import ahu.bigdata.huiculture.module.update.UpdateModel;

/**
 * Created by ych10 on 2017/10/3.
 * Function:应用层封装
 */
public class RequestCenter {

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    /**
     * 应用版本号请求
     *
     * @param listener
     */
    public static void checkVersion(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.CHECK_UPDATE, null, listener, UpdateModel.class);
    }

    /**
     * @param listener
     * @function 发送首页请求
     */
    public static void requestRecommandData(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND, null, listener, BaseRecommandModel.class);
    }

    public static void downloadFile(String url, String path, DisposeDownloadListener listener) {
        CommonOkHttpClient.downloadFile(CommonRequest.createGetRequest(url, null),
                new DisposeDataHandle(listener, path));
    }

}
