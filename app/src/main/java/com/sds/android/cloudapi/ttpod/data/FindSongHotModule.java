package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class FindSongHotModule implements Serializable {
    @c(a = "name")
    private String mName;
    @c(a = "prm")
    private int mPrm;
    @c(a = "size")
    private int mSize;
    @c(a = "url")
    private String mUrl;

    public FindSongHotModule(String str, int i, String str2, int i2) {
        this.mName = str;
        this.mSize = i;
        this.mUrl = str2;
        this.mPrm = i2;
    }

    public String getName() {
        return this.mName;
    }

    public int getSize() {
        return this.mSize;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public boolean needRequestParam() {
        return this.mPrm == 1;
    }
}
