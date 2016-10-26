package cn.renyuzhuo.rlib;

import cn.renyuzhuo.rlog.rlog;

/**
 * Created by renyuzhuo on 16-10-21.
 * Exception
 */
public class RLibException extends Exception {
    public static Exception ContextNotInitException() {
        rlog.e("ContextNotInitException");
        return null;
    }
}
