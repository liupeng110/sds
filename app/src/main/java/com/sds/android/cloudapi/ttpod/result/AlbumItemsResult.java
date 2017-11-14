package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.sdk.lib.request.d;

public class AlbumItemsResult extends d<AlbumItem> {
    @c(a = "pageCount")
    private int mAllPage;
    @c(a = "totalCount")
    private int mCount;

    public int getCount() {
        return this.mCount;
    }

    public int getAllPage() {
        return this.mAllPage;
    }
}
