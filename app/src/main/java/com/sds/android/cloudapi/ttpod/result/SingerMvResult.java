package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.ArrayList;
import java.util.List;

public class SingerMvResult extends BaseResult {
    @c(a = "data")
    private List<SingerMvData> mMvDataList = new ArrayList();
    @c(a = "page")
    private int mPage = 0;
    @c(a = "pageCount")
    private int mPageCount = 0;
    @c(a = "size")
    private int mSize = 0;
    @c(a = "totalCount")
    private int mTotalCount = 0;

    public List<SingerMvData> getMvDataList() {
        return this.mMvDataList;
    }

    public void setMvDataList(List<SingerMvData> list) {
        this.mMvDataList = list;
    }

    public int getPageCount() {
        return this.mPageCount;
    }

    public void setPageCount(int i) {
        this.mPageCount = i;
    }

    public int getTotalCount() {
        return this.mTotalCount;
    }

    public void setTotalCount(int i) {
        this.mTotalCount = i;
    }

    public int getPage() {
        return this.mPage;
    }

    public void setPage(int i) {
        this.mPage = i;
    }

    public int getSize() {
        return this.mSize;
    }

    public void setSize(int i) {
        this.mSize = i;
    }
}
