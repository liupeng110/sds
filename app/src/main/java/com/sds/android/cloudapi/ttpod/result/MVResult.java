package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.sdk.lib.request.BaseResult;

public class MVResult extends BaseResult {
    @c(a = "data")
    private MvData mMVData;
    @c(a = "page")
    private int mPage = 0;
    @c(a = "pageCount")
    private int mPageCount = 0;
    @c(a = "size")
    private int mSize = 0;
    @c(a = "totalCount")
    private int mTotalCount = 0;

    public MvData getMVData() {
        return this.mMVData == null ? new MvData() : this.mMVData;
    }

    public void setMvDataList(SingerMvData singerMvData) {
        this.mMVData = singerMvData;
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
