package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class StarCategory {
    public static final String KEY_STAR_CATEGORY_ID = "id";
    public static final String KEY_STAR_CATEGORY_NAME = "name";
    @c(a = "id")
    private int mId;
    @c(a = "name")
    private String mName;

    public int getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public void setName(String str) {
        this.mName = str;
    }
}
