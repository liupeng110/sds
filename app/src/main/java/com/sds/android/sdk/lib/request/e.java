package com.sds.android.sdk.lib.request;

import com.b.a.a.c;

/* DataResult */
public class e<D> extends BaseResult {
    @c(a = "data")
    private D mData;

    public D getData() {
        return this.mData;
    }

    public void setData(D d) {
        this.mData = d;
    }
}
