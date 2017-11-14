package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class LyricItem implements Serializable {
    @c(a = "content_url")
    private String mContentUrl;
    @c(a = "duration_ms")
    private long mDurationMs;
    @c(a = "_id")
    private long mId;
    @c(a = "singer_name")
    private String mSingerName;
    @c(a = "song_name")
    private String mSongName;
    @c(a = "trc")
    private boolean mTrc;

    public boolean isTrc() {
        return this.mTrc;
    }

    public void setTrc(boolean z) {
        this.mTrc = z;
    }

    public long getDurationMilliseconds() {
        return this.mDurationMs;
    }

    public void setDurationMilliseconds(long j) {
        this.mDurationMs = j;
    }

    public long getId() {
        return this.mId;
    }

    public void setId(long j) {
        this.mId = j;
    }

    public String getSingerName() {
        return this.mSingerName;
    }

    public void setSingerName(String str) {
        this.mSingerName = str;
    }

    public String getSongName() {
        return this.mSongName;
    }

    public void setSongName(String str) {
        this.mSongName = str;
    }

    public String getContentUrl() {
        return this.mContentUrl;
    }

    public void setContentUrl(String str) {
        this.mContentUrl = str;
    }
}
