package cn.renyuzhuo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by renyuzhuo on 16-9-27.
 * <br/>
 * Email: rwebrtc@gmail.com
 * <br/>
 */
public class RlibApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
