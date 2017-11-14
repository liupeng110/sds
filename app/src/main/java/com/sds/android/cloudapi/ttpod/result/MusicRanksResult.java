package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.sdk.lib.request.d;
import java.io.Serializable;

public class MusicRanksResult extends d<MusicRank> implements Serializable {
    @c(a = "pages")
    int mPages;

    public int getPages() {
        return this.mPages;
    }

    public void setPages(int i) {
        this.mPages = i;
    }
}
