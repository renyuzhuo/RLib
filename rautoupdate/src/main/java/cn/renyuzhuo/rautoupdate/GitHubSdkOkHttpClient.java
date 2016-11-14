package cn.renyuzhuo.rautoupdate;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by renyuzhuo on 16-11-14.
 * <p>
 * GitHub 升级网络访问接口
 */
public class GitHubSdkOkHttpClient {

    private static GitHubSdkOkHttpClient gitHubSdkOkHttpClient = null;
    private static Context context;

    private GitHubSdkOkHttpClient() {

    }

    public static GitHubSdkOkHttpClient getInstance(Context context) {
        GitHubSdkOkHttpClient.context = context;
        if (gitHubSdkOkHttpClient == null) {
            gitHubSdkOkHttpClient = new GitHubSdkOkHttpClient();
            return gitHubSdkOkHttpClient;
        } else {
            return gitHubSdkOkHttpClient;
        }
    }

    public okhttp3.OkHttpClient build() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        builder.addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(3 * 60, TimeUnit.SECONDS)
                .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024))
                .build();
        return enrichBuilder(builder).build();
    }

    protected okhttp3.OkHttpClient.Builder enrichBuilder(okhttp3.OkHttpClient.Builder builder) {
        return builder;
    }
}