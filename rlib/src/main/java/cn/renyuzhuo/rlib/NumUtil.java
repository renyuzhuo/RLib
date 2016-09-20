package cn.renyuzhuo.rlib;

import java.util.Random;

/**
 * Created by renyuzhuo on 16-9-9.
 */
public class NumUtil {

    public static long getRandomNum() {
        return getRandomNum(100000000);
    }

    public static long getRandomNum(int num) {
        return new Random().nextInt(num);
    }

}
