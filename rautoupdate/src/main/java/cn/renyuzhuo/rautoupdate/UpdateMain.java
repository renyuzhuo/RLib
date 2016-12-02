package cn.renyuzhuo.rautoupdate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;

import com.google.gson.Gson;

import okhttp3.ResponseBody;

/**
 * Created by renyuzhuo on 16-11-10.
 * <p>
 * GitHub 升级主控函数
 */
public class UpdateMain implements UpdateClientListener {
    private static UpdateMain updateMain;
    Context context;
    Version version;

    public UpdateMain(Context context) {
        this.context = context;
    }

    public static UpdateMain getUpdateMain(Context context) {
        if (updateMain == null) {
            updateMain = new UpdateMain(context);
            return updateMain;
        } else {
            return updateMain;
        }
    }

    /**
     * 是否需要升级，主接口
     *
     * @param username 仓库用户名
     * @param repo     仓库名
     * @param branch   仓库分支
     * @param file     仓库版本文件
     * @return 是够需要升级
     */
    public final boolean ifNeedUpdate(String username, String repo, String branch, String file) {
        UpdateClient.ifNeedUpdate(context, this, username, repo, branch, file);
        return false;
    }

    public final boolean ifCodingNeedUpdate(String username, String repo, String branch, String file) {
        UpdateClient.ifCodingNeedUpdate(context, this, username, repo, branch, file);
        return false;
    }

    @Override
    public void onGetVersion(ResponseBody responseBody) {
        try {
            String response = responseBody.string();
            version = new Gson().fromJson(response, Version.class);
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            int buildVersionCode = pi.versionCode;
            if (version.getVersionCode() > buildVersionCode) {
                LoadingDialog.openDownloadDialog(this, context, version.getDescription());
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAcceptUpdate() {
        if (version != null && version.getUrl() != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(version.getUrl()));
            context.startActivity(intent);
        }
        updateMain = null;
    }

    @Override
    public void onDoNotAcceptUpdate() {
        updateMain = null;
    }
}
