package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class MvTag implements Serializable {
    @c(a = "tagColor")
    private String mTagColor = "";
    @c(a = "tagName")
    private String mTagName = "";

    public String getTagName() {
        return this.mTagName;
    }

    public String getTagColor() {
        return this.mTagColor;
    }
}
