package com.ttfm.android.sdk.entity;

public class MusicInfoGetResult {
    private int Code;
    private TTFMSongEntity Music;

    public int getCode() {
        return this.Code;
    }

    public void setCode(int i) {
        this.Code = i;
    }

    public TTFMSongEntity getMusic() {
        return this.Music;
    }

    public void setMusic(TTFMSongEntity tTFMSongEntity) {
        this.Music = tTFMSongEntity;
    }
}
