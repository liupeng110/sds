package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class RelatedSingerItem implements ISinger, Serializable {
    @c(a = "albumsCount")
    private int mAlbumNum;
    @c(a = "name")
    private String mName = "";
    @c(a = "picUrl")
    private String mPicUrl = "";
    @c(a = "singerId")
    private long mSingerId;
    @c(a = "songsCount")
    private int mSongNum;

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public String getSingerName() {
        return this.mName;
    }

    public long getSingerId() {
        return this.mSingerId;
    }

    public int getSongCount() {
        return this.mSongNum;
    }

    public int getAlbumCount() {
        return this.mAlbumNum;
    }
}
