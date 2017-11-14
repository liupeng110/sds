package com.sds.android.sdk.lib.request;

import com.b.a.a.c;

/* ExtraDataListResultMock */
public class h<D> extends g<D> {
    @c(a = "pages")
    private int mPages;
    @c(a = "rows")
    private int mRows;

    public f getExtra() {
        return new f(this.mPages, this.mRows);
    }
}
