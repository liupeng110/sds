package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.util.ArrayList;
import java.util.List;

public class DailyHotMVData extends MvData {
    @c(a = "bigPicUrl")
    private String mBigPicUrl = "";
    @c(a = "channelId")
    private int mChannelId;
    @c(a = "tag")
    private List<MvTag> mTags = new ArrayList();

    public int getChannelId() {
        return this.mChannelId;
    }

    public String getBigPicUrl() {
        return this.mBigPicUrl;
    }

    public void setBigPicUrl(String str) {
        this.mBigPicUrl = str;
    }

    public List<MvTag> getTags() {
        return this.mTags;
    }
}
