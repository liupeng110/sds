package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.BaseResult;

public class AudioEffectAddResult extends BaseResult {
    @c(a = "data")
    private String mResult = "";

    public void setAeId(String str) {
        this.mResult = str;
    }

    public String getAeId() {
        return this.mResult;
    }
}
