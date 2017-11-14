package com.tencent.weiyun;

import com.tencent.tauth.UiError;
import java.util.List;

/* ProGuard */
public interface IGetFileListListener {
    void onComplete(List<WeiyunFile> list);

    void onError(UiError uiError);
}
