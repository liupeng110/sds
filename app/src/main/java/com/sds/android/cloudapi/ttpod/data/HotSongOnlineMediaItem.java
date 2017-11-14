package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class HotSongOnlineMediaItem extends OnlineMediaItem implements Serializable {
    @c(a = "desc")
    private String mDescription;
    @c(a = "pic_url")
    private String mPicUrl;

    public String getDescription() {
        return this.mDescription;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }
}
