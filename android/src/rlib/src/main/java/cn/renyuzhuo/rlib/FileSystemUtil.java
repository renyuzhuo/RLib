package cn.renyuzhuo.rlib;

import android.os.Environment;

import java.io.File;

/**
 * 文件系统相关
 * <p>
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
        return sdCardPath;
    }

    /**
     * 获取SD卡文件路径
     *
     * @param fileName 文件名
     * @return 文件对象
     */
    public static File getSDCardFile(String fileName) {
        return getFileByUrl(getSDCardPath() + "/" + fileName);
    }

    /**
     * 通过文件路径获取文件
     *
     * @param fileUrl 文件路径
     * @return 文件对象
     */
    public static File getFileByUrl(String fileUrl) {
        return new File(fileUrl);
    }
}
