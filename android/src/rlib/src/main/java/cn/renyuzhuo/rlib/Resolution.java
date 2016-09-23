package cn.renyuzhuo.rlib;

import android.content.Context;
import android.util.TypedValue;

/**
 * 单位转换
 */
public class Resolution {

    /**
     * dp转换成px
     *
     * @param context  Context
     * @param dipValue dip
     * @return pix, context为空时，返回dipValue
     */
    public static float dipToPixels(Context context, float dipValue) {
        if (context == null) {
            return dipValue;
        }
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context.getResources().getDisplayMetrics());
    }

    /**
     * dp转换成px
     *
     * @param context  Context
     * @param dipValue dip
     * @return pix, context为空时，返回dipValue
     */
    public static float dipToPixels(Context context, int dipValue) {
        if (context == null) {
            return dipValue;
        }
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context.getResources().getDisplayMetrics());
    }

    /**
     * dp转换成px
     *
     * @param context  Context
     * @param dipValue dip
     * @return pix, context为空时，返回dipValue
     */
    public static int dipToPixelsInt(Context context, float dipValue) {
        return (int) dipToPixels(context, dipValue);
    }

    /**
     * dp转换成px
     *
     * @param context  Context
     * @param dipValue dip
     * @return pix, context为空时，返回dipValue
     */
    public static int dipToPixelsInt(Context context, int dipValue) {
        return (int) dipToPixels(context, dipValue);
    }
}
