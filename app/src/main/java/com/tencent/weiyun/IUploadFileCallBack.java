package com.tencent.weiyun;

import com.tencent.tauth.UiError;

/* ProGuard */
public interface IUploadFileCallBack {
    void onError(UiError uiError);

    void onPrepareStart();

    void onUploadProgress(int i);

    void onUploadStart();

    void onUploadSuccess();
}
