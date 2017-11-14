package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class FindSongCommonCategory implements Serializable {
    @c(a = "details")
    private String mDetail;
    @c(a = "id")
    private int mId;
    @c(a = "pic_url")
    private String mPicUrl;
    @c(a = "style")
    private int mStyle;
    @c(a = "time")
    private String mTime;
    @c(a = "title")
    private String mTitle;
    @c(a = "type")
    private int mType;

    public int getId() {
        return this.mId;
    }

    public int getType() {
        return this.mType;
    }

    public int getStyle() {
        return this.mStyle;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public String getDetail() {
        return this.mDetail;
    }

    public String getTime() {
        return this.mTime;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public void setStyle(int i) {
        this.mStyle = i;
    }
}
