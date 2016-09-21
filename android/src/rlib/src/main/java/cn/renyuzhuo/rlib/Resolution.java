package cn.renyuzhuo.rlib;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by renyuzhuo on 16-9-21.
 * 单位转换库
 */
public class Resolution {

    /**
     * dp转换成px
     *
     * @param context  Context
     * @param dipValue dip
     * @return pix
     */
    public static float dipToPixels(Context context, float dipValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context.getResources().getDisplayMetrics());
    }
}
