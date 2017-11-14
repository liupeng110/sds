package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.AlbumSearchItem;
import com.sds.android.sdk.lib.request.d;

public class AlbumSearchItemsResult extends d<AlbumSearchItem> {
    @c(a = "all_page")
    private int mAllPage;
    @c(a = "count")
    private int mCount;

    public int getCount() {
        return this.mCount;
    }

    public int getAllPage() {
        return this.mAllPage;
    }
}
