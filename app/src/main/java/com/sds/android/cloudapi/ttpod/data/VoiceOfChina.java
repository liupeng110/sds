package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class VoiceOfChina implements Serializable {
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    @c(a = "id")
    private int mId;
    @c(a = "title")
    private String mTitle;

    public int getId() {
        return this.mId;
    }

    public String getTitle() {
        return this.mTitle;
    }
}
