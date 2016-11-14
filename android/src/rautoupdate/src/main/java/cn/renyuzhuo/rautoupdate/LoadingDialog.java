package cn.renyuzhuo.rautoupdate;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by renyuzhuo on 16-11-1.
 * <p>
 * 下载弹窗
 */
public class LoadingDialog {

    static MaterialDialog updateDialog;

    public static void openDownloadDialog(final UpdateClientListener updateClientListener, Context context, String content) {
        updateDialog = new MaterialDialog.Builder(context)
                .title(R.string.new_download)
                .content(content)
                .positiveText(R.string.agree)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        updateClientListener.onAcceptUpdate();
                        updateDialog = null;
                    }
                })
                .negativeText(R.string.disagree)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        updateClientListener.onDoNotAcceptUpdate();
                        updateDialog = null;
                    }
                })
                .show();
    }
}
