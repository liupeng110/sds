package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.HashMap;

public class SingerPicItem implements Serializable {
    @c(a = "alias_names")
    private String mAliasNames;
    @c(a = "_id")
    private long mId;
    @c(a = "pic_size_map")
    private HashMap<Integer, Integer> mPicSizeMap = new HashMap();
    @c(a = "pic_url_pattern")
    private String mPicUrlPattern;
    @c(a = "singer_id")
    private long mSingerId;
    @c(a = "singer_name")
    private String mSingerName;

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

    public String getAliasNames() {
        return this.mAliasNames;
    }

    public void setAliasNames(String str) {
        this.mAliasNames = str;
    }

    public long getSingerId() {
        return this.mSingerId;
    }

    public void setSingerId(long j) {
        this.mSingerId = j;
    }

    public HashMap<Integer, Integer> getPicSizeMap() {
        return this.mPicSizeMap;
    }

    public void setPicSizeMap(HashMap<Integer, Integer> hashMap) {
        this.mPicSizeMap = hashMap;
    }

    public String getPicUrlPattern() {
        return this.mPicUrlPattern;
    }

    public void setPicUrlPattern(String str) {
        this.mPicUrlPattern = str;
    }
}
