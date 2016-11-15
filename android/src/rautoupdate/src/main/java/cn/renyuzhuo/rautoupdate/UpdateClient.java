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
        final GitHubContentBase[] gitHubContentBase = {GitHubContentBase.getInstance(context)};
        UpdateService updateService = gitHubContentBase[0].build().create(UpdateService.class);
        String timeStamp = String.valueOf(System.currentTimeMillis());
        updateService.ifNeedUpdate(username, repo, branch, file, timeStamp)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        gitHubContentBase[0] = null;
                        Log.d("UpdateClient", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        gitHubContentBase[0] = null;
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
