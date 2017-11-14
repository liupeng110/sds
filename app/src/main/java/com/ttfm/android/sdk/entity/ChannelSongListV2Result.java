package com.ttfm.android.sdk.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ChannelSongListV2Result implements Serializable {
    private static final long serialVersionUID = 1;
    private long CacheDuration;
    private ArrayList<TTFMSongEntity> CacheList;
    private int Code;
    private String CodeMsg;
    private long updateTime;

    public long getDuration() {
        return this.CacheDuration;
    }

    public void setDuration(long j) {
        this.CacheDuration = j;
    }

    public ArrayList<TTFMSongEntity> getList() {
        return this.CacheList;
    }

    public void setList(ArrayList<TTFMSongEntity> arrayList) {
        this.CacheList = arrayList;
    }

    public int getCode() {
        return this.Code;
    }

    public void setCode(int i) {
        this.Code = i;
    }

    public String getCodeMsg() {
        return this.CodeMsg;
    }

    public void setCodeMsg(String str) {
        this.CodeMsg = str;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(long j) {
        this.updateTime = j;
    }

    public boolean isSuccess() {
        return this.Code == 200;
    }
}
