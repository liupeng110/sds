package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.LyricItem;
import com.sds.android.sdk.lib.request.d;

public class LyricResult extends d<LyricItem> {
    @c(a = "code")
    private int mCode;
    @c(a = "pages")
    private int mPages;
    @c(a = "rows")
    private int mRows;

    public void setPages(int i) {
        this.mPages = i;
    }

    public void setRows(int i) {
        this.mRows = i;
    }

    public int getCode() {
        return this.mCode;
    }

    public void setCode(int i) {
        this.mCode = i;
    }

    public int getPages() {
        return this.mPages;
    }

    public int getRows() {
        return this.mRows;
    }
}
