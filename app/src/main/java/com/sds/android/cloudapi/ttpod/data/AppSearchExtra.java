package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class AppSearchExtra {
    @c(a = "all_page")
    private int mAllPage;
    @c(a = "count")
    private int mCount;
    @c(a = "no-result")
    private boolean mNoResult;

    public int getCount() {
        return this.mCount;
    }

    public int getAllPage() {
        return this.mAllPage;
    }

    public boolean isNoResult() {
        return this.mNoResult;
    }
}
