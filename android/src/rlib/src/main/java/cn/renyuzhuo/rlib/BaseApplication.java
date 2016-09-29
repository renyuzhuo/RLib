package cn.renyuzhuo.rlib;

import android.app.Application;

/**
 * Created by renyuzhuo on 16-9-27.
 * <br/>
 * Email: rwebrtc@gmail.com
 * <br/>
 * 基本Application，添加非捕捉异常格式化输出
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new BaseUncaughtException());
    }
}
