package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.sdk.lib.b.b;
import java.util.ArrayList;
import java.util.List;

public class MvCategoryResult extends b {
    @c(a = "code")
    private int mCode;
    @c(a = "id")
    private int mId;
    @c(a = "msg")
    private String mMsg;
    @c(a = "data")
    private List<MvData> mMvCategoryList = new ArrayList();
    @c(a = "pageCount")
    private int mPageCount;
    @c(a = "totalCount")
    private int mTotalCount;

    public int getResultCode() {
        return this.mCode;
    }

    public String getMsg() {
        return this.mMsg;
    }

    public int getPageCount() {
        return this.mPageCount;
    }

    public int getTotalCount() {
        return this.mTotalCount;
    }

    public int getId() {
        return this.mId;
    }

    public List<MvData> getMvCategoryList() {
        return this.mMvCategoryList;
    }
}
