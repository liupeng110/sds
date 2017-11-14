package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.DailyHotMVData;
import com.sds.android.sdk.lib.b.b;
import java.util.ArrayList;
import java.util.List;

public class DailyHotMVDataResult extends b {
    @c(a = "code")
    private int mCode;
    @c(a = "data")
    private List<DailyHotMVData> mDailyHotMVList = new ArrayList();
    @c(a = "msg")
    private String mMsg = "";
    @c(a = "pageCount")
    private int mPageCount;
    @c(a = "totalCount")
    private int mTotalCount;
    @c(a = "version")
    private String mVersion = "";

    public int getResultCode() {
        return this.mCode;
    }

    public String getMsg() {
        return this.mMsg;
    }

    public List<DailyHotMVData> getDailyHotMVList() {
        return this.mDailyHotMVList;
    }

    public void setDailyHotMVList(List<DailyHotMVData> list) {
        this.mDailyHotMVList = list;
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

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String str) {
        this.mVersion = str;
    }
}
