package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.MVCategoryDimension;
import com.sds.android.sdk.lib.b.b;
import java.util.ArrayList;
import java.util.List;

public class MVCategoryDimensionResult extends b {
    @c(a = "code")
    private int mCode;
    @c(a = "data")
    private List<MVCategoryDimension> mData = new ArrayList();
    @c(a = "msg")
    private String mMsg = "";
    @c(a = "page")
    private long mPage = 0;
    @c(a = "pageCount")
    private long mPageCount = 0;
    @c(a = "size")
    private long mSize = 0;
    @c(a = "totalCount")
    private long mTotalCount = 0;

    public int getResultCode() {
        return this.mCode;
    }

    public String getMsg() {
        return this.mMsg;
    }

    public List<MVCategoryDimension> getDimensions() {
        return this.mData;
    }

    public void setDimensions(List<MVCategoryDimension> list) {
        this.mData = list;
    }
}
