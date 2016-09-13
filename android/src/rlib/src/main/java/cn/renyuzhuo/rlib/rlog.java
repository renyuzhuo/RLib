package cn.renyuzhuo.rlib;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Created by renyuzhuo on 16-8-22.
 */
public class rlog {

    private static String TAG = "rlog";
    private static String TAG_INFO = TAG + ".i";
    private static String TAG_DEBUG = TAG + ".d";
    private static String TAG_WARN = TAG + ".w";
    private static String TAG_ERROR = TAG + ".e";

    private static int debugLevel = DEBUG_LEVEL.info;

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

    public static void d() {
        d("----------just space----------");
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

    // objects
    private static Object object;

    public static void objects(Object... objects) {
        if (objects == null || objects.length == 0) {
            d("---------------------------------------------------");
            return;
        } else {
            d("--------------------Objects begin------------------");
            for (int i = 0; i < objects.length; i++) {
                object = objects[i];
                if (object == null) {
                    d("args[" + i + "] == null");
                } else {
                    d("args[" + i + "] == " + String.valueOf(object));
                }
            }
            d("--------------------Objects end--------------------");
        }
    }

    // 以Debug级别输出json
    public static void json(String json) {
        json(DEBUG_LEVEL.debug, json);
    }

    // 以debug_level级别输出json
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

    // 以debug级别输出xml
    public static void xml(String xml) {
        xml(DEBUG_LEVEL.debug, xml);
    }

    // 以debug_level级别输出xml
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

    public interface DEBUG_LEVEL {
        int info = 0, debug = 1, warn = 2, error = 3, none = 7;
    }

    public static void setTAG(String tag) {
        TAG = tag;
        TAG_INFO = tag + ".i";
        TAG_DEBUG = tag + ".d";
        TAG_WARN = tag + ".w";
        TAG_ERROR = tag + ".e";
    }

    public static void setDebugLever(int level) {
        debugLevel = level;
    }
}
