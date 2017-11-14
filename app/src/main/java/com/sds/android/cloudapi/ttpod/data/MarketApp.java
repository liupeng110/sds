package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class MarketApp extends AppBaseInfo {
    @c(a = "category_id")
    private int mCategoryId;
    @c(a = "category_name")
    private String mCategoryName;

    public int getCategoryId() {
        return this.mCategoryId;
    }

    public String getCategoryName() {
        return this.mCategoryName;
    }
}
