package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class AudioEffectPickLruItem {
    @c(a = "_id")
    private long mId = 0;
    @c(a = "nick_name")
    private String mNickName = "";
    @c(a = "timestamp")
    private long mTimeStamp = 0;

    public long getId() {
        return this.mId;
    }

    public void setId(long j) {
        this.mId = j;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public void setNickName(String str) {
        this.mNickName = str;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public void setTimeStamp(long j) {
        this.mTimeStamp = j;
    }
}
