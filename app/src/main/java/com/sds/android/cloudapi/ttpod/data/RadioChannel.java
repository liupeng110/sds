package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class RadioChannel implements Serializable {
    @c(a = "pic_url_240_200")
    private String m240X200PicUrl;
    @c(a = "details")
    private String mDetails;
    @c(a = "large_pic_url")
    private String mLargePicUrl;
    @c(a = "parentname")
    private String mParentName;
    @c(a = "quantity")
    private int mQuantity;
    @c(a = "small_pic_url")
    private String mSmallPicUrl;
    @c(a = "songlist_id")
    private int mSongListId;
    @c(a = "songlist_name")
    private String mSongListName;
    @c(a = "url")
    private String mUrl;

    public RadioChannel(int i, String str, String str2, String str3, String str4, String str5) {
        this.mSongListId = i;
        this.mParentName = str;
        this.mSongListName = str2;
        this.mLargePicUrl = str3;
        this.mSmallPicUrl = str4;
        this.m240X200PicUrl = str5;
    }

    public String getParentName() {
        return this.mParentName;
    }

    public void setParentName(String str) {
        this.mParentName = str;
    }

    public int getChannelId() {
        return this.mSongListId;
    }

    public String getChannelName() {
        return this.mSongListName;
    }

    public String getDetails() {
        return this.mDetails;
    }

    public int getQuantity() {
        return this.mQuantity;
    }

    public String getLargePicUrl() {
        return this.mLargePicUrl;
    }

    public String getSmallPicUrl() {
        return this.mSmallPicUrl;
    }

    public String get240X200PicUrl() {
        return this.m240X200PicUrl;
    }

    public void setSongListId(int i) {
        this.mSongListId = i;
    }

    public void setSongListName(String str) {
        this.mSongListName = str;
    }

    public void set240X200PicUrl(String str) {
        this.m240X200PicUrl = str;
    }

    public String getUrl() {
        return this.mUrl;
    }
}
