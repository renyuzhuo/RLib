package cn.renyuzhuo.rlog;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * 日志输出工具类
 * <p>
 * i()系列方法输出info信息
 * <p>
 * d()系列方法输出debug信息
 * <p>
 * w()系列方法输出warn信息
 * <p>
 * e()系列方法输出error信息
 */
public class rlog {

    private static String TAG = "rlog";
    private static String TAG_INFO = TAG + ".i";
    private static String TAG_DEBUG = TAG + ".d";
    private static String TAG_WARN = TAG + ".w";
    private static String TAG_ERROR = TAG + ".e";

    private static int debugLevel = DEBUG_LEVEL.debug;

    // Log.info
    public static void i(String tag, String message) {
        switch (debugLevel) {
            case DEBUG_LEVEL.info: {
                Log.i(tag, message);
                break;
            }
        }
    }

    public static void i(String message) {
        i(TAG_INFO, message);
    }

    public static void i(Object o) {
        if (o == null) {
            e("object == null");
            return;
        }
        i(o.toString());
    }

    // Log.debug
    public static void d(String tag, String message) {
        switch (debugLevel) {
            case DEBUG_LEVEL.info:
            case DEBUG_LEVEL.debug: {
                Log.d(tag, message);
            }
        }
    }

    public static void d(String message) {
        d(TAG_DEBUG, message);
    }

    /**
     * 输出空行------
     */
    public static void d() {
        d("------------------------------");
    }

    /**
     * debug级别语句块开始
     */
    private static void dbegin(String s) {
        d("↓↓↓↓↓" + s + "↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
    }

    /**
     * debug级别语句块开始
     */
    public static void dbegin() {
        d("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
    }

    /**
     * debug级别语句块结束
     */
    private static void dend(String s) {
        d("↑↑↑↑↑" + s + "↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
    }

    /**
     * debug级别语句块结束
     */
    public static void dend() {
        d("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
    }

    public static void d(char c) {
        d(TAG_DEBUG + ".char", String.valueOf(c));
    }

    public static void d(int numInt) {
        d(TAG_DEBUG + ".numInt", String.valueOf(numInt));
    }

    public static void d(long numLong) {
        d(TAG_DEBUG + ".numLong", String.valueOf(numLong));
    }

    public static void d(float numFloat) {
        d(TAG_DEBUG + ".numFloat", String.valueOf(numFloat));
    }

    public static void d(double numDouble) {
        d(TAG_DEBUG + ".numDouble", String.valueOf(numDouble));
    }

    public static void d(Object o) {
        if (o == null) {
            e("object == null");
            return;
        }
        d(TAG_DEBUG, o.toString());
    }

    public static void d(Object[] objects) {
        if (objects == null) {
            e("array == null");
            return;
        }

        if (objects.length == 0) {
            d(TAG_DEBUG + ".array", "[]");
            return;
        }

        String temp = "[";
        for (Object o : objects) {
            temp += o + ", ";
        }
        // remove the last','
        temp = temp.substring(0, temp.length() - 2);
        temp += "]";

        d(TAG_DEBUG + ".array", temp);
    }

    // warn
    public static void w(String tag, String message) {
        switch (debugLevel) {
            case DEBUG_LEVEL.info:
            case DEBUG_LEVEL.debug:
            case DEBUG_LEVEL.warn: {
                Log.w(tag, message);
            }
        }
    }

    // warn
    public static void w(String message) {
        w(TAG_WARN, message);
    }

    // error
    public static void e(String tag, String message) {
        switch (debugLevel) {
            case DEBUG_LEVEL.info:
            case DEBUG_LEVEL.debug:
            case DEBUG_LEVEL.warn:
            case DEBUG_LEVEL.error: {
                Log.e(tag, message);
            }
        }
    }

    public static void e(String message) {
        e(TAG_ERROR, message);
    }

    /**
     * 错误语句块开始
     */
    private static void ebegin(String s) {
        e("↓↓↓↓↓" + s + "↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
    }

    /**
     * 错误语句快开始
     */
    public static void ebegin() {
        e("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
    }

    /**
     * 错误语句块结束
     */
    private static void eend(String s) {
        e("↑↑↑↑↑" + s + "↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
    }

    /**
     * 错误语句块结束
     */
    public static void eend() {
        e("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
    }

    // objects
    private static Object object;

    /**
     * 分别输出objects
     *
     * @param objects objects
     */
    public static void objects(Object... objects) {
        if (objects == null || objects.length == 0) {
            d();
            return;
        } else {
            dbegin("Objects begin");
            for (int i = 0; i < objects.length; i++) {
                object = objects[i];
                if (object == null) {
                    d("args[" + i + "] == null");
                } else {
                    d("args[" + i + "] == " + String.valueOf(object));
                }
            }
            dend("Objects end");
        }
    }

    /**
     * 以Debug级别输出json
     */
    public static void json(String json) {
        json(DEBUG_LEVEL.debug, json);
    }

    /**
     * 以debug_level级别输出json
     *
     * @param debug_level 日志级别
     * @param json        json字符串
     */
    public static void json(int debug_level, String json) {
        if (json == null) {
            e("json == null");
            return;
        }
        if (json.length() == 0) {
            e("json.length() == 0");
            return;
        }
        // https://github.com/orhanobut/logger
        json = json.trim();
        int JSON_INDENT = 2;

        String logString = null;
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                logString = jsonObject.toString(JSON_INDENT);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                logString = jsonArray.toString(JSON_INDENT);
            }
        } catch (JSONException e) {
            e("json err:" + json);
        }

        switch (debug_level) {
            case DEBUG_LEVEL.info: {
                i(TAG_INFO + ".json", logString);
                break;
            }
            case DEBUG_LEVEL.debug: {
                d(TAG_INFO + ".json", logString);
                break;
            }
            case DEBUG_LEVEL.warn: {
                w(TAG_INFO + ".json", logString);
                break;
            }
            case DEBUG_LEVEL.error: {
                e(TAG_INFO + ".json", logString);
                break;
            }
            default: {
                break;
            }
        }
    }

    /**
     * 以debug级别输出xml
     */
    public static void xml(String xml) {
        xml(DEBUG_LEVEL.debug, xml);
    }

    /**
     * 以debug_level级别输出xml
     *
     * @param debug_level 日志级别
     * @param xml         xml字符串
     */
    public static void xml(int debug_level, String xml) {
        if (xml == null) {
            e("xml == null");
        } else if (xml.length() == 0) {
            e("xml.length() == 0");
        } else {
            try {
                // https://github.com/orhanobut/logger
                Source xmlInput = new StreamSource(new StringReader(xml));
                StreamResult xmlOutput = new StreamResult(new StringWriter());
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.transform(xmlInput, xmlOutput);
                switch (debug_level) {
                    case DEBUG_LEVEL.info: {
                        i(TAG_INFO + ".xml", xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
                        break;
                    }
                    case DEBUG_LEVEL.debug: {
                        d(TAG_INFO + ".xml", xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
                        break;
                    }
                    case DEBUG_LEVEL.warn: {
                        w(TAG_INFO + ".xml", xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
                        break;
                    }
                    case DEBUG_LEVEL.error: {
                        e(TAG_INFO + ".xml", xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
                        break;
                    }
                    default: {
                        break;
                    }
                }

            } catch (TransformerException e) {
                e("xml err:" + xml);
            }
        }
    }

    /**
     * 日志级别
     */
    public interface DEBUG_LEVEL {
        /**
         * info级别
         */
        int info = 0,
        /**
         * debug级别
         */
        debug = 1,
        /**
         * warn级别
         */
        warn = 2,
        /**
         * error级别
         */
        error = 3,
        /**
         * 不输出日志
         */
        none = 7;
    }

    /**
     * 设置日志输出tag
     *
     * @param tag TAG
     */
    public static void setTAG(String tag) {
        TAG = tag;
        TAG_INFO = tag + ".i";
        TAG_DEBUG = tag + ".d";
        TAG_WARN = tag + ".w";
        TAG_ERROR = tag + ".e";
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

    /**
     * 设置日志输出级别
     *
     * @param level 日志级别
     */
    public static void setDebugLever(int level) {
        debugLevel = level;
    }
}
