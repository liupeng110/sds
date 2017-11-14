package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.util.ArrayList;

public class AppDetail extends AppBaseInfo {
    public static final int CHECKING = 2;
    public static final int SECURITY = 3;
    public static final int UN_AUTHENTICATION = 4;
    public static final int UN_CHECKED = 1;
    @c(a = "approve")
    private int mApprove;
    @c(a = "category_id")
    private int mCategoryId;
    @c(a = "category_name")
    private String mCategoryName;
    @c(a = "pics")
    private ArrayList<String> mPics;
    @c(a = "recommend_count")
    private int mRecommendCount;
    @c(a = "upload_date")
    private String mUpdateDate;
    @c(a = "upload_info")
    private String mUpdateInfo;

    public int getApprove() {
        return this.mApprove;
    }

    public String getUpdateDate() {
        return this.mUpdateDate;
    }

    public String getUpdateInfo() {
        return this.mUpdateInfo;
    }

    public int getRecommendCount() {
        return this.mRecommendCount;
    }

    public ArrayList<String> getPics() {
        return this.mPics;
    }

    public String getCategoryName() {
        return this.mCategoryName;
    }

    public int getCategoryId() {
        return this.mCategoryId;
    }
}
