package cn.renyuzhuo.rlib;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by renyuzhuo on 16-9-27.
 * <br/>
 * Email: rwebrtc@gmail.com
 * <br/>
 * <p>
 * 未捕捉异常
 * <p>
 * 可以使Application继承自BaseApplication自动引用，或在Application中使用：Thread.setDefaultUncaughtExceptionHandler(new BaseUncaughtException());引用
 */
public class BaseUncaughtException implements Thread.UncaughtExceptionHandler {
    private static Thread.UncaughtExceptionHandler mDefaultHandler;
    private static ByteArrayOutputStream baos;
    private static PrintStream printStream;
    private static byte[] data;
    private static String info;

    /**
     * 未捕捉异常捕捉构造器
     */
    public BaseUncaughtException() {
    }

    /**
     * 未捕捉异常输出
     */
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(mDefaultHandler);
        baos = new ByteArrayOutputStream();
        printStream = new PrintStream(baos);
        throwable.printStackTrace(printStream);
        data = baos.toByteArray();
        info = new String(data);
        printInfo(info);
        if (!BuildConfig.DEBUG) {
            System.exit(0);
        }
    }

    /**
     * 输出错误信息块
     *
     * @param info
     */
    void printInfo(String info) {
        Log.d("rlog", info);
    }
}
