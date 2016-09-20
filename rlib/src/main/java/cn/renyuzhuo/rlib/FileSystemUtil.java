package cn.renyuzhuo.rlib;

import android.os.Environment;

import java.io.File;

/**
 * Created by renyuzhuo on 16-8-29.
 * <p/>
 * 权限:{@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}
 */
public class FileSystemUtil {

    private static final String TAG = "FileSystemUtil";

    /**
     * 获取内置SD卡路径，缺少权限仍可获得，但是打开文件出错：FileNotFoundException(Permission denied)
     *
     * @return String SD卡路径
     */
    public static String getSDCardPath() {
        String sdCardPath = Environment.getExternalStorageDirectory().getPath();
        rlog.d(TAG, "sdCardPath:" + sdCardPath);
        return sdCardPath;
    }

    public static File getSDCardFile(String fileName) {
        return getFileByUrl(getSDCardPath() + "/" + fileName);
    }

    public static File getFileByUrl(String fileUrl) {
        rlog.d(TAG, "fileUrl:" + fileUrl);
        return new File(fileUrl);
    }
}
