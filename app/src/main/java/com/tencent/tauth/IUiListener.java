package com.tencent.tauth;

/* ProGuard */
public interface IUiListener {
    void onCancel();

    void onComplete(Object obj);

    void onError(UiError uiError);
}
