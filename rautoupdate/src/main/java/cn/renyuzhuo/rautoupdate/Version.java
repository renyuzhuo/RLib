package cn.renyuzhuo.rautoupdate;

import com.google.gson.annotations.Expose;

/**
 * Created by renyuzhuo on 16-11-10.
 * <p>
 * 返回数据类型 Json 串
 * <p>
 * {"versionCode":8,"versionName":"1.0.8","description":"1.0.8 添加 Issues，基本可用","url":"https://coding.net/u/rwebrtc/p/RGitHub/git/raw/master/app-release.apk"}
 */
public class Version {
    @Expose
    private int versionCode;
    @Expose
    private String versionName;
    @Expose
    private String description;
    @Expose
    private String url;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Version{" +
                "versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
