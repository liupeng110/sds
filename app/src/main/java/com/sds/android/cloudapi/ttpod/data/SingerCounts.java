package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class SingerCounts implements Serializable {
    @c(a = "albumsCount")
    private int mAlbumsCount = 0;
    @c(a = "songsCount")
    private int mSongsCount = 0;

    public int getAlbumsCount() {
        return this.mAlbumsCount;
    }

    public int getSongsCount() {
        return this.mSongsCount;
    }
}
