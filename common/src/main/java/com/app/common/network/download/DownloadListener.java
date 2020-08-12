package com.app.common.network.download;

/**
 * Created by ${user} on 2018/9/6.
 * QinQin
 */

public interface DownloadListener {
    void onStartDownload();

    void onProgress(int progress);

    void onFinishDownload();

    void onFail(String errorInfo);
}
