package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.MusicCircleFirstPublish;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.ArrayList;

public class FirstPublishNewSongCategoryResult extends BaseResult {
    private static final String KEY_DATA = "data";
    private static final String KEY_PAGE_COUNT = "pages";
    private static final String KEY_ROWS = "rows";
    @c(a = "pages")
    private int mPageCount = 0;
    @c(a = "rows")
    private int mRows = 0;
    @c(a = "data")
    private ArrayList<MusicCircleFirstPublish> mSingleList = new ArrayList();

    public int getPageCount() {
        return this.mPageCount;
    }

    public ArrayList<MusicCircleFirstPublish> getSingleList() {
        return this.mSingleList;
    }

    public int getRows() {
        return this.mRows;
    }
}
