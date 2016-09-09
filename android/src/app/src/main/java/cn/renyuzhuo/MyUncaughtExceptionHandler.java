package cn.renyuzhuo;

import cn.renyuzhuo.rlib.rlog;

/**
 * Created by renyuzhuo on 16-8-22.
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        rlog.e("Exception");
    }
}
