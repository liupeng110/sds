package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class RadioCategoryChannel implements Serializable {
    @c(a = "pic_url_240_200")
    private String m240X200PicUrl;
    @c(a = "tag_id")
    private int mCategoryChannelId;
    @c(a = "tag_name")
    private String mCategoryChannelName;
    @c(a = "quantity")
    private int mCount;
    @c(a = "large_pic_url")
    private String mLargePicUrl;
    @c(a = "small_pic_url")
    private String mSmallPicUrl;

    public int getCount() {
        return this.mCount;
    }

    public void setCount(int i) {
        this.mCount = i;
    }

    public RadioCategoryChannel(int i, String str, String str2, String str3, String str4) {
        this.mCategoryChannelId = i;
        this.mCategoryChannelName = str;
        this.mSmallPicUrl = str2;
        this.mLargePicUrl = str3;
        this.m240X200PicUrl = str4;
    }

    public int getCategoryChannelId() {
        return this.mCategoryChannelId;
    }

    public String getCategoryChannelName() {
        return this.mCategoryChannelName;
    }

    public String getSmallPicUrl() {
        return this.mSmallPicUrl;
    }

    public String getLargePicUrl() {
        return this.mLargePicUrl;
    }

    public String get240X200PicUrl() {
        return this.m240X200PicUrl;
    }
}
