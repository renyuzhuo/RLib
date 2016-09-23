package cn.renyuzhuo.rlib;

import java.util.Random;

/**
 * 数字相关
 */
public class NumUtil {

    /**
     * 获取随机数
     *
     * @return 随机数
     */
    public static long getRandomNum() {
        return getRandomNum(100000000);
    }

    /**
     * 获取一定范围内的随机数
     *
     * @param num 范围最大值
     * @return 随机数
     */
    public static long getRandomNum(int num) {
        return new Random().nextInt(num);
    }

}
