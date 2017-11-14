package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import com.sds.android.sdk.lib.b.b;
import java.io.Serializable;

public class ShortUrl extends b implements Serializable {
    @c(a = "url")
    private String mUrl;

    public String getUrl() {
        return this.mUrl;
    }
}
