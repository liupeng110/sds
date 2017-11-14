package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.SplashItem;
import com.sds.android.sdk.lib.request.d;

public class SplashDataResult extends d<SplashItem> {
    @c(a = "version")
    private int mVersion;

    public int getVersion() {
        return this.mVersion;
    }
}
