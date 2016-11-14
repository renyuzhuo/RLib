package cn.renyuzhuo.rautoupdate;

import okhttp3.ResponseBody;

/**
 * Created by renyuzhuo on 16-11-10.
 * <p>
 * GitHub 升级网络访问监听
 */
public interface UpdateClientListener {
    void onGetVersion(ResponseBody responseBody);

    void onAcceptUpdate();

    void onDoNotAcceptUpdate();
}
