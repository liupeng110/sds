package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dimension implements Serializable {
    @c(a = "options")
    private List<ChannelOutline> mChannelOutlines = new ArrayList();
    @c(a = "cid")
    private long mDimensionId = 0;
    @c(a = "name")
    private String mName = "";

    public String getName() {
        return this.mName;
    }

    public long getDimensionId() {
        return this.mDimensionId;
    }

    public List<ChannelOutline> getChannelOutlines() {
        return this.mChannelOutlines;
    }
}
