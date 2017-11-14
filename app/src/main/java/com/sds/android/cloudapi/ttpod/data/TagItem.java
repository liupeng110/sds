package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class TagItem implements Serializable {
    @c(a = "count")
    private int mCount = 0;
    @c(a = "status")
    private int mStatus = 0;
    @c(a = "tag")
    private Tag mTag = new Tag();

    public Tag getTagItem() {
        return this.mTag;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public int getCount() {
        return this.mCount;
    }
}
