package cn.renyuzhuo.rlib;

import android.app.Application;

/**
 * Created by renyuzhuo on 16-9-27.
 * <br/>
 * Email: rwebrtc@gmail.com
 * <br/>
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new BaseUncaughtException());
    }
}
