package com.sds.android.ttpod.framework.modules.search;

import com.sds.android.sdk.lib.c.a.a.b;

public class SearchMediaLinkInfo {
    public static final int DB_VERSION = 16777216;
    private String mArtist;
    private Long mArtistSearchTime;
    private String mLyricPath;
    private Long mLyricSearchTime;
    private String mMediaId;

    @b(a = 0, b = 16777216, c = true)
    public String getMediaId() {
        return this.mMediaId;
    }

    public void setMediaId(String str) {
        this.mMediaId = str;
    }

    @b(a = 1, b = 16777216)
    public String getLyricPath() {
        return this.mLyricPath;
    }

    public void setLyricPath(String str) {
        this.mLyricPath = str;
    }

    @b(a = 2, b = 16777216)
    public Long getLyricSearchTime() {
        return this.mLyricSearchTime;
    }

    public void setLyricSearchTime(Long l) {
        this.mLyricSearchTime = l;
    }

    @b(a = 3, b = 16777216)
    public String getArtist() {
        return this.mArtist;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    @b(a = 4, b = 16777216)
    public Long getArtistSearchTime() {
        return this.mArtistSearchTime;
    }

    public void setArtistSearchTime(Long l) {
        this.mArtistSearchTime = l;
    }
}
