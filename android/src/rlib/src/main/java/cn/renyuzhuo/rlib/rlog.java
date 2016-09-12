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

    private static int debugLever = DEBUG_LEVER.info;

    // Log.info
    public static void i(String tag, String message) {
        switch (debugLever) {
            case DEBUG_LEVER.info: {
                Log.i(TAG_INFO + tag, message);
            }
        }
    }

    public static void i(String message) {
        switch (debugLever) {
            case DEBUG_LEVER.info: {
                Log.i(TAG_INFO, message);
            }
        }
    }

    public static void i(Object o) {
        switch (debugLever) {
            case DEBUG_LEVER.info: {
                if (o == null) {
                    e("object == null");
                    return;
                }
                Log.i(TAG_INFO, o.toString());
            }
        }
    }

    // Log.debug
    public static void d(String tag, String message) {
        switch (debugLever) {
            case DEBUG_LEVER.info:
            case DEBUG_LEVER.debug: {
                Log.d(TAG_DEBUG + "." + tag, message);
            }
        }
    }

    public static void d(String message) {
        switch (debugLever) {
            case DEBUG_LEVER.info:
            case DEBUG_LEVER.debug: {
                Log.d(TAG_DEBUG, message);
            }
        }
    }

    public static void d() {
        switch (debugLever) {
            case DEBUG_LEVER.info:
            case DEBUG_LEVER.debug:
            case DEBUG_LEVER.warn:
            case DEBUG_LEVER.error: {
                Log.d(TAG_DEBUG + "", "----------just space----------");
            }
        }
    }

    public static void d(char c) {
        switch (debugLever) {
            case DEBUG_LEVER.info:
            case DEBUG_LEVER.debug: {
                Log.d(TAG_DEBUG + ".char", String.valueOf(c));
            }
        }
    }

    public static void d(int numInt) {
        switch (debugLever) {
            case DEBUG_LEVER.info:
            case DEBUG_LEVER.debug: {
                Log.d(TAG_DEBUG + ".numInt", String.valueOf(numInt));
            }
        }

    }

    public static void d(long numLong) {
        switch (debugLever) {
            case DEBUG_LEVER.info:
            case DEBUG_LEVER.debug: {
                Log.d(TAG_DEBUG + ".numLong", String.valueOf(numLong));
            }
        }

    }

    public static void d(float numFloat) {
        switch (debugLever) {
            case DEBUG_LEVER.info:
            case DEBUG_LEVER.debug: {
                Log.d(TAG_DEBUG + ".numFloat", String.valueOf(numFloat));
            }
        }
    }

    public static void d(double numDouble) {
        switch (debugLever) {
            case DEBUG_LEVER.info:
            case DEBUG_LEVER.debug: {
                Log.d(TAG_DEBUG + ".numDouble", String.valueOf(numDouble));
            }
        }
    }

    public static void d(Object o) {
        switch (debugLever) {
            case DEBUG_LEVER.info:
            case DEBUG_LEVER.debug: {
                if (o == null) {
                    e("object == null");
                    return;
                }
                Log.d(TAG_DEBUG, o.toString());
            }
        }
    }

    public static void d(Object[] objects) {
        switch (debugLever) {
            case DEBUG_LEVER.info:
            case DEBUG_LEVER.debug: {
                if (objects == null) {
                    e("array == null");
                    return;
                }

                String temp = "[";
                for (Object o : objects) {
                    temp += o + ", ";
                }
                // remove the last','
                temp = temp.substring(0, temp.length() - 2);
                temp += "]";

                Log.i(TAG_DEBUG + ".array", temp);
            }
        }
    }

    // warn
    public static void w(String message) {
        switch (debugLever) {
            case DEBUG_LEVER.info:
            case DEBUG_LEVER.debug:
            case DEBUG_LEVER.warn: {
                Log.w(TAG_WARN, message);
            }
        }
    }

    // error
    public static void e(String message) {
        switch (debugLever) {
            case DEBUG_LEVER.info:
            case DEBUG_LEVER.debug:
            case DEBUG_LEVER.warn:
            case DEBUG_LEVER.error: {
                Log.e(TAG_ERROR, message);
            }
        }
    }

    // objects
    private static Object object;

    public static void objects(Object... objects) {
        switch (debugLever) {
            case DEBUG_LEVER.info:
            case DEBUG_LEVER.debug: {
                if (objects == null || objects.length == 0) {
                    Log.d(TAG_DEBUG, "---------------------------------------------------");
                    return;
                } else {
                    Log.d(TAG_DEBUG, "--------------------Objects begin------------------");
                    for (int i = 0; i < objects.length; i++) {
                        object = objects[i];
                        if (object == null) {
                            Log.d(TAG_DEBUG, "args[" + i + "] == null");
                        } else {
                            Log.d(TAG_DEBUG, "args[" + i + "] == " + String.valueOf(object));
                        }
                    }
                    Log.d(TAG_DEBUG, "--------------------Objects end--------------------");
                }
            }
        }
    }

    // json
    public static void json(String json) {
        switch (debugLever) {
            case DEBUG_LEVER.info: {
                if (json == null) {
                    e("json == null");
                } else if (json.length() == 0) {
                    e("json.length() == 0");
                } else {
                    try {
                        // https://github.com/orhanobut/logger
                        json = json.trim();
                        int JSON_INDENT = 2;
                        if (json.startsWith("{")) {
                            JSONObject jsonObject = new JSONObject(json);
                            String message = jsonObject.toString(JSON_INDENT);
                            Log.i(TAG_INFO + ".json", message);
                            return;
                        }
                        if (json.startsWith("[")) {
                            JSONArray jsonArray = new JSONArray(json);
                            String message = jsonArray.toString(JSON_INDENT);
                            Log.i(TAG_INFO + ".json", message);
                            return;
                        }
                    } catch (JSONException e) {
                        e("json err:" + json);
                    }

                }
            }
        }
    }

    // xml
    public static void xml(String xml) {
        switch (debugLever) {
            case DEBUG_LEVER.info: {
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
                        Log.i(TAG_INFO + ".xml", xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
                    } catch (TransformerException e) {
                        e("xml err:" + xml);
                    }
                }
            }
        }
    }

    public interface DEBUG_LEVER {
        int info = 0, debug = 1, warn = 2, error = 3, none = 7;
    }

    public static void setTAG(String TAG) {
        rlog.TAG = TAG;
        TAG_INFO = TAG + ".i";
        TAG_DEBUG = TAG + ".d";
        TAG_WARN = TAG + ".w";
        TAG_ERROR = TAG + ".e";
    }

    public static void setDebugLever(int debugLever) {
        rlog.debugLever = debugLever;
    }
}
