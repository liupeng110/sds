package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.util.ArrayList;

public class EssentialApps {
    @c(a = "artifacts")
    private ArrayList<MarketApp> mArtifacts;
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

    public ArrayList<MarketApp> getArtifacts() {
        return this.mArtifacts;
    }
}
