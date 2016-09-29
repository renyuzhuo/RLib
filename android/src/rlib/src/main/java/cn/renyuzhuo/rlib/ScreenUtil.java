package cn.renyuzhuo.rlib;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by renyuzhuo on 16-9-29.
 * <br/>
 * Email: rwebrtc@gmail.com
 * <br/>
 * 屏幕工具类
 */
public class ScreenUtil {

    private static WindowManager wm;
    private static Display display;
    private static DisplayMetrics metrics;
    private static int[] widthHeight = null;

    /**
     * 获取屏幕宽度
     *
     * @param context Context
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        getScreenWidthHeight(context);
        if (widthHeight != null) {
            return widthHeight[0];
        } else {
            return 0;
        }
    }

    /**
     * 获取屏幕高度
     *
     * @param context Context
     * @return 屏幕高度
     */
    public static int getScreenHeight(Context context) {
        getScreenWidthHeight(context);
        if (widthHeight != null) {
            return widthHeight[1];
        } else {
            return 0;
        }
    }

    /**
     * 获取屏幕宽度和高度
     *
     * @param context
     * @return
     */
    public static int[] getScreenWidthHeight(Context context) {
        if (widthHeight != null) {
            return widthHeight;
        }
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return widthHeight = new int[]{metrics.widthPixels, metrics.heightPixels};
    }
}
