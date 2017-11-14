package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class AppHotWords {
    public static final int SEARCH_RANKING_DOWN = -1;
    public static final int SEARCH_RANKING_KEEP = 0;
    public static final int SEARCH_RANKING_NEW = 2;
    public static final int SEARCH_RANKING_UP = 1;
    @c(a = "ascend")
    private int mAscend;
    @c(a = "ascend_num")
    private int mAscendNum;
    @c(a = "id")
    private int mId;
    @c(a = "search_key_name")
    private String mSearchKeyName;

    public int getId() {
        return this.mId;
    }

    public String getSearchKeyName() {
        return this.mSearchKeyName;
    }

    public int getAscend() {
        return this.mAscend;
    }

    public int getAscendNum() {
        return this.mAscendNum;
    }
}
