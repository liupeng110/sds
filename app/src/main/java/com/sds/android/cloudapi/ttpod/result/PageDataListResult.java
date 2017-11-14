package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.d;

public class PageDataListResult<D> extends d<D> {
    @c(a = "pages")
    private int mPages;
    @c(a = "rows")
    private int mRows;

    public int getPages() {
        return this.mPages;
    }

    public int getRows() {
        return this.mRows;
    }
}
