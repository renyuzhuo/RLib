package cn.renyuzhuo.rlog;

import android.util.Log;

import java.util.Random;

/**
 * rlog.getSettings().logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
 * <p>
 * Thanks https://github.com/orhanobut/logger
 */
public final class rlog {

    private static Printer printer = new LoggerPrinter();

    public static Printer t(String tag) {
        return printer.t(tag, printer.getSettings().getMethodCount());
    }

    public static Printer t(int methodCount) {
        return printer.t(null, methodCount);
    }

    public static Printer t(String tag, int methodCount) {
        return printer.t(tag, methodCount);
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        printer.log(priority, tag, message, throwable);
    }

    public static void d(String message, Object... args) {
        printer.d(message, args);
    }

    public static void d(Object object) {
        printer.d(object);
    }

    public static void e(String message, Object... args) {
        printer.e(null, message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        printer.e(throwable, message, args);
    }

    public static void i(String message, Object... args) {
        printer.i(message, args);
    }

    public static void v(String message, Object... args) {
        printer.v(message, args);
    }

    public static void w(String message, Object... args) {
        printer.w(message, args);
    }

    public static void wtf(String message, Object... args) {
        printer.wtf(message, args);
    }

    public static void log(Object o) {
        if (settings.getLogLevel() == LogLevel.NONE) {
            return;
        }
        if (o == null) {
            Log.d(settings.getTAG(), "null");
        } else {
            Log.d(settings.getTAG(), o.toString());
        }
    }

    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    public static void json(String json) {
        printer.json(json);
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        printer.xml(xml);
    }

    private static final Settings settings = Settings.getInstance();

    /**
     * 设置日志输出tag
     *
     * @param tag TAG
     */
    public static void setTAG(String tag) {
        Settings.getInstance().setTAG(tag);
    }

    /**
     * 设置是否添加日志随机后缀，添加后，每次应用打开时，日志tag添加随机数，用于区分
     *
     * @param isRandom 是否添加随机数后缀，默认不添加
     */
    public static void setBeRandom(boolean isRandom) {
        setBeRandom("rlog", isRandom);
    }

    /**
     * 设置tag和是否添加随机数后缀
     *
     * @param tag      日志TAG
     * @param isRandom 是否添加随机数后缀
     */
    public static void setBeRandom(String tag, boolean isRandom) {
        if (isRandom) {
            setTAG(tag + "." + new Random().nextInt(1000));
        } else {
            setTAG(tag);
        }
    }

    public static Settings getSettings() {
        return Settings.getInstance();
    }

}

