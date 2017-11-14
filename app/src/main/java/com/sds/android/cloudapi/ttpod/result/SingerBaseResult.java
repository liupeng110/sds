package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.BaseResult;

public class SingerBaseResult extends BaseResult {
    @c(a = "pageCount")
    private int mPageCount;
    @c(a = "totalCount")
    private int mTotalCount;
    @c(a = "version")
    private String mVersion = "";

    public int getTotalCount() {
        return this.mTotalCount;
    }

    public int getPageCount() {
        return this.mPageCount;
    }

    public String getVersion() {
        return this.mVersion;
    }
}
