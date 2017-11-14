package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;
import java.util.ArrayList;

public class OnlineMusicCategoryResult extends BaseResult {
    private static final String KEY_DATA = "data";
    private static final String KEY_PAGE_COUNT = "pages";
    private static final String KEY_ROWS = "rows";
    @c(a = "data")
    private ArrayList<CategoryData> mCategoryList = null;
    @c(a = "pages")
    private int mPages = 0;
    @c(a = "rows")
    private int mRows = 0;

    public static class CategoryData implements Serializable {
        private static final String KEY_CATEGORY_ID = "_id";
        private static final String KEY_COUNT = "count";
        private static final String KEY_NAME = "name";
        @c(a = "count")
        private int mCount = 0;
        @c(a = "_id")
        private long mId = 0;
        @c(a = "name")
        private String mName = null;

        public long getId() {
            return this.mId;
        }

        public String getName() {
            return this.mName;
        }

        public int getCount() {
            return this.mCount;
        }
    }

    public ArrayList<CategoryData> getCategoryList() {
        return this.mCategoryList;
    }

    public int getPageCount() {
        return this.mPages;
    }

    public int getRows() {
        return this.mRows;
    }
}
