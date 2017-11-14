package com.ttfm.android.sdk.entity;

public class NextSongGetResult {
    private int CacheDuration;
    private int Code;
    private String CodeMsg;
    private TTFMSongEntity Next;
    private TTFMSongEntity Next2;

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

    public TTFMSongEntity getNext() {
        return this.Next;
    }

    public void setNext(TTFMSongEntity tTFMSongEntity) {
        this.Next = tTFMSongEntity;
    }

    public TTFMSongEntity getNext2() {
        return this.Next2;
    }

    public void setNext2(TTFMSongEntity tTFMSongEntity) {
        this.Next2 = tTFMSongEntity;
    }

    public int getCacheDuration() {
        return this.CacheDuration;
    }

    public void setCacheDuration(int i) {
        this.CacheDuration = i;
    }
}
