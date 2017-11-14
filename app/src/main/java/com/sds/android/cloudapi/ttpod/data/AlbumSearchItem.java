package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.io.Serializable;
import java.util.List;

public class AlbumSearchItem implements Serializable {
    @c(a = "desc")
    private String mDesc;
    @c(a = "_id")
    private long mId;
    @c(a = "lang")
    private String mLang;
    @c(a = "name")
    private String mName;
    @c(a = "pic200")
    private String mPic200;
    @c(a = "pic500")
    private String mPic500;
    @c(a = "publish_time")
    private String mPublishTime;
    @c(a = "singer_name")
    private String mSingerName;
    @c(a = "song_ids")
    private String mSongIds;

    public long getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getSingerName() {
        return this.mSingerName;
    }

    public String getPublishTime() {
        return this.mPublishTime;
    }

    public List<Long> getSongIds() {
        return m.b(this.mSongIds, SelectCountryActivity.SPLITTER);
    }

    public String getLang() {
        return this.mLang;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public String getPic200() {
        return this.mPic200;
    }

    public String getPic500() {
        return this.mPic500;
    }

    public void setId(long j) {
        this.mId = j;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setSingerName(String str) {
        this.mSingerName = str;
    }

    public void setPublishTime(String str) {
        this.mPublishTime = str;
    }

    public void setSongIds(String str) {
        this.mSongIds = str;
    }

    public void setLang(String str) {
        this.mLang = str;
    }

    public void setDesc(String str) {
        this.mDesc = str;
    }

    public void setPic200(String str) {
        this.mPic200 = str;
    }

    public void setPic500(String str) {
        this.mPic500 = str;
    }
}
