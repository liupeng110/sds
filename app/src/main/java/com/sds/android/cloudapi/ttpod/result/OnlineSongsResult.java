package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.OnlineSongItem;
import java.util.ArrayList;
import java.util.List;

public class OnlineSongsResult extends SingerBaseResult {
    @c(a = "data")
    private List<OnlineSongItem> mOnlineSongItems;
    @c(a = "page")
    private int mPage;
    @c(a = "size")
    private int mSize;

    public List<OnlineSongItem> getOnlineSongItems() {
        if (this.mOnlineSongItems == null) {
            this.mOnlineSongItems = new ArrayList();
        }
        return this.mOnlineSongItems;
    }

    public int getPage() {
        return this.mPage;
    }

    public int getSize() {
        return this.mSize;
    }
}
