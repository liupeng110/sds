package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.b.b;

public class Account extends b {
    @c(a = "id")
    private long mId = 0;
    @c(a = "token")
    private String mToken = "";

    public long getId() {
        return this.mId;
    }

    public String getToken() {
        return this.mToken;
    }
}
