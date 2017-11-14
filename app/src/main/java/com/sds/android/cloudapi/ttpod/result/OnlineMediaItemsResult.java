package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.request.g;

public class OnlineMediaItemsResult extends g<OnlineMediaItem> {
    @c(a = "pages")
    private int mPages;
    @c(a = "rows")
    private int mRows;

    public int getPages() {
        return this.mPages;
    }

    public int getCount() {
        return this.mRows;
    }
}
