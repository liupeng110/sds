package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.util.ArrayList;

public class MarketAppCategory {
    @c(a = "artifact_count")
    private int mArtifactCount;
    @c(a = "category_id")
    private int mCategoryId;
    @c(a = "category_logo")
    private String mCategoryLogo;
    @c(a = "category_name")
    private String mCategoryName;
    @c(a = "sub_categories")
    private ArrayList<MarketAppCategory> mSubCategories;

    public int getCategoryId() {
        return this.mCategoryId;
    }

    public String getCategoryName() {
        return this.mCategoryName;
    }

    public int getArtifactCount() {
        return this.mArtifactCount;
    }

    public String getCategoryLogoUrl() {
        return this.mCategoryLogo;
    }

    public ArrayList<MarketAppCategory> getSubCategories() {
        return this.mSubCategories;
    }
}
