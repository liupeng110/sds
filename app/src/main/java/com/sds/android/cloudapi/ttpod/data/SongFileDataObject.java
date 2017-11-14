package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class SongFileDataObject implements Serializable {
    @c(a = "bitRate")
    private int mBitRate;
    @c(a = "duration")
    private int mDuration;
    @c(a = "size")
    private long mSize;
    @c(a = "suffix")
    private String mSuffix;
    @c(a = "typeDescription")
    private String mTypeDescription;
    @c(a = "url")
    private String mUrl;

    public String getTypeDescription() {
        return this.mTypeDescription;
    }

    public int getBitRate() {
        return this.mBitRate;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getSuffix() {
        return this.mSuffix;
    }

    public long getSize() {
        return this.mSize;
    }

    public int getDuration() {
        return this.mDuration;
    }
}
