package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class ChannelOutline implements Serializable {
    @c(a = "pid")
    private long mChannelId = 0;
    @c(a = "name")
    private String mName = "";
    @c(a = "selected")
    private boolean mPrefered = false;

    public String getName() {
        return this.mName;
    }

    public long getChannelId() {
        return this.mChannelId;
    }

    public boolean isPrefered() {
        return this.mPrefered;
    }
}
