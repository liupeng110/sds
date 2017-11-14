package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;
import java.util.ArrayList;

public class OnlineMusicSubCategoryResult extends BaseResult {
    private static final String KEY_DATA = "data";
    private static final String KEY_PAGE_COUNT = "pages";
    private static final String KEY_ROWS = "rows";
    @c(a = "pages")
    private int mPages = 0;
    @c(a = "rows")
    private int mRows = 0;
    @c(a = "data")
    private ArrayList<SubCategoryData> mSubCategoryList = new ArrayList();

    public static class SubCategoryData implements Serializable {
        private static final String KEY_CATEGORY_ID = "_id";
        private static final String KEY_COUNT = "count";
        private static final String KEY_DETAIL = "desc";
        private static final String KEY_LARGE_PIC_URL = "large_pic_url";
        private static final String KEY_NAME = "name";
        private static final String KEY_PIC_URL = "pic_url";
        private static final String KEY_URL = "url";
        @c(a = "count")
        private int mCount = 0;
        @c(a = "desc")
        private String mDetail = null;
        @c(a = "_id")
        private long mId = 0;
        @c(a = "large_pic_url")
        private String mLargePicUrl = null;
        @c(a = "name")
        private String mName = null;
        private String mParentName = null;
        @c(a = "pic_url")
        private String mPicUrl = null;
        @c(a = "url")
        private String mUrl = null;

        public String getParentName() {
            return this.mParentName;
        }

        public void setParentName(String str) {
            this.mParentName = str;
        }

        public String getLargePicUrl() {
            return this.mLargePicUrl;
        }

        public String getPicUrl() {
            return this.mPicUrl;
        }

        public long getId() {
            return this.mId;
        }

        public String getName() {
            return this.mName;
        }

        public int getCount() {
            return this.mCount;
        }

        public String getUrl() {
            return this.mUrl;
        }

        public String getDetail() {
            return this.mDetail;
        }

        public void setDetail(String str) {
            this.mDetail = str;
        }

        public void setId(long j) {
            this.mId = j;
        }

        public void setName(String str) {
            this.mName = str;
        }

        public void setCount(int i) {
            this.mCount = i;
        }

        public void setUrl(String str) {
            this.mUrl = str;
        }

        public void setPicUrl(String str) {
            this.mPicUrl = str;
        }

        public void setLargePicUrl(String str) {
            this.mLargePicUrl = str;
        }
    }

    public ArrayList<SubCategoryData> getSubCategoryList() {
        return this.mSubCategoryList;
    }

    public int getPages() {
        return this.mPages;
    }

    public int getRows() {
        return this.mRows;
    }
}
