package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class AlikeTTPodUser extends TTPodUser {
    private static final String KEY_SAME_SINGER_COUNT = "same_singer_num";
    private static final String KEY_SAME_SONG_COUNT = "same_song_num";
    @c(a = "same_singer_num")
    private int mSameSingerCount;
    @c(a = "same_song_num")
    private int mSameSongCount;

    public int getSameSongCount() {
        return this.mSameSongCount;
    }

    public int getSameSingerCount() {
        return this.mSameSingerCount;
    }
}
