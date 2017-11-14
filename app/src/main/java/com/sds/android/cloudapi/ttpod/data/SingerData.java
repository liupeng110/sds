package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class SingerData implements Serializable {
    @c(a = "singer_id")
    private int mId;
    @c(a = "singer_name")
    private String mName;
    @c(a = "pic_url")
    private String mPicUrl;
    @c(a = "pic_url_140")
    private String mPicUrl140;

    public SingerData(int i, String str, String str2, String str3) {
        this.mId = i;
        this.mName = str;
        this.mPicUrl = str2;
        this.mPicUrl140 = str3;
    }

    public int getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public String getPicUrl140() {
        return this.mPicUrl140;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SingerData)) {
            return false;
        }
        if (this.mId != ((SingerData) obj).mId) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mId;
    }
}
