package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class FirstPublishAlbumData implements Serializable {
    private static final String KEY_DESC = "desc";
    private static final String KEY_ID = "_id";
    private static final String KEY_MSG_ID = "msg_id";
    private static final String KEY_PIC = "pic";
    private static final String KEY_PIC_URL = "pic_url";
    private static final String KEY_STARTING = "starting";
    private static final String KEY_TITLE = "title";
    private static final String KEY_WEEK = "week";
    private static final String KEY_YEAR = "year";
    @c(a = "desc")
    private String mDesc = "";
    @c(a = "starting")
    private boolean mFirstPublish;
    @c(a = "_id")
    private long mId;
    @c(a = "msg_id")
    private long mMsgId;
    @c(a = "pic")
    private String mPicUrl = "";
    @c(a = "pic_url")
    private String mPicUrlNew = "";
    @c(a = "title")
    private String mTitle = "";
    @c(a = "week")
    private int mWeek;
    @c(a = "year")
    private int mYear;

    public long getId() {
        return this.mId;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public String getPicUrlNew() {
        return this.mPicUrlNew;
    }

    public int getWeek() {
        return this.mWeek;
    }

    public int getYear() {
        return this.mYear;
    }

    public long getMsgId() {
        return this.mMsgId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean isFirstPublish() {
        return this.mFirstPublish;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }
}
