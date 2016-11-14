package cn.renyuzhuo.rautoupdate;

import android.content.Context;
import android.util.Log;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-11-10.
 * <p>
 * GitHub 升级网络访问
 */
public class UpdateClient {

    public static void ifNeedUpdate(Context context, final UpdateClientListener updateClientListener,
                                    String username, String repo, String branch, String file) {
        UpdateService updateService = GitHubContentBase.getInstance(context).build().create(UpdateService.class);
        updateService.ifNeedUpdate(username, repo, branch, file)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        Log.d("UpdateClient", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("UpdateClient.onError", "NetWork ERR");
                        Log.e("UpdateClient", "↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
                        e.printStackTrace();
                        Log.e("UpdateClient", "↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (updateClientListener != null) {
                            updateClientListener.onGetVersion(responseBody);
                        }
                    }
                });
    }

}
