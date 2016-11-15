package cn.renyuzhuo.rlog;

public final class Settings {

    private static Settings settings;

    private int methodCount = 4;
    private boolean showThreadInfo = true;
    private int methodOffset = 0;
    private LogAdapter logAdapter;
    private static String TAG = "rlog";

    private Settings() {

    }

    public static Settings getInstance() {
        if (settings == null) {
            settings = new Settings();
            return settings;
        }
        return settings;
    }

    /**
     * Determines to how logs will be printed
     */
    private LogLevel logLevel = LogLevel.FULL;

    public Settings hideThreadInfo() {
        showThreadInfo = false;
        return this;
    }

    public Settings methodCount(int methodCount) {
        if (methodCount < 0) {
            methodCount = 0;
        }
        this.methodCount = methodCount;
        return this;
    }

    public Settings logLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    public Settings methodOffset(int offset) {
        this.methodOffset = offset;
        return this;
    }

    public Settings logAdapter(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
        return this;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public boolean isShowThreadInfo() {
        return showThreadInfo;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public int getMethodOffset() {
        return methodOffset;
    }

    public LogAdapter getLogAdapter() {
        if (logAdapter == null) {
            logAdapter = new AndroidLogAdapter();
        }
        return logAdapter;
    }

    public void reset() {
        methodCount = 2;
        methodOffset = 0;
        showThreadInfo = true;
        logLevel = LogLevel.FULL;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public String getTAG() {
        return TAG;
    }

}
