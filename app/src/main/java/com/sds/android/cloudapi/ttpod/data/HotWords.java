package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class HotWords implements Serializable {
    @c(a = "pic")
    private String mPictureUrl;
    @c(a = "word")
    private String mWord;

    public String getWord() {
        return this.mWord;
    }

    public String getPictureUrl() {
        return this.mPictureUrl;
    }
}
