package cn.renyuzhuo.rautoupdate;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by renyuzhuo on 16-11-14.
 * <p>
 * GitHub 升级文件访问接口
 */
public class GitHubContentBase {

    public static GitHubContentBase gitHubContentBase = null;
    private static Context context;

    private GitHubContentBase() {

    }

    public static GitHubContentBase getInstance(Context context) {
        GitHubContentBase.context = context;
        if (gitHubContentBase == null) {
            gitHubContentBase = new GitHubContentBase();
            return gitHubContentBase;
        } else {
            return gitHubContentBase;
        }
    }

    public String getBaseUrl() {
        return "https://raw.githubusercontent.com/";
    }

    public Retrofit build() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(getBaseUrl())
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return enrichBuilder(builder).build();
    }

    public Retrofit.Builder enrichBuilder(Retrofit.Builder builder) {
        return builder;
    }

    public OkHttpClient getOkHttpClient() {
        return GitHubSdkOkHttpClient.getInstance(context).build();
    }

}
