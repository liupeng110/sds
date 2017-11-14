package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.BaseResult;

public class PostFileResult extends BaseResult {
    @c(a = "url")
    private String mFileUrl;

    public String getUrl() {
        return this.mFileUrl;
    }
}
