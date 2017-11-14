package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.BaseResult;

public class StringResult extends BaseResult {
    @c(a = "page")
    private int mPage = 0;
    @c(a = "pageCount")
    private int mPageCount = 0;
    @c(a = "size")
    private int mSize = 0;
    @c(a = "data")
    private String mStringData;
    @c(a = "totalCount")
    private int mTotalCount = 0;

    public String getStringData() {
        return this.mStringData == null ? "" : this.mStringData;
    }

    public int getPageCount() {
        return this.mPageCount;
    }

    public int getTotalCount() {
        return this.mTotalCount;
    }

    public int getPage() {
        return this.mPage;
    }

    public int getSize() {
        return this.mSize;
    }
}
