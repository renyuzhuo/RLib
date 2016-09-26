package cn.renyuzhuo.rlib;

/**
 * 时间相关
 */
public class Time {

    /**
     * 获取系统时间
     *
     * @return 系统时间
     */
    public static long getSystemTimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }
}
