package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class FindSongCategory extends FindSongCommonCategory implements Serializable {
    @c(a = "category")
    private int mCategoryId;
    @c(a = "count")
    private int mCount;

    public int getCategoryId() {
        return this.mCategoryId;
    }

    public void setCategoryId(int i) {
        this.mCategoryId = i;
    }

    public int getCount() {
        return this.mCount;
    }
}
