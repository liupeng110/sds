package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.FirstPublishAlbumData;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.ArrayList;

public class FirstPublishNewAlbumResult extends BaseResult {
    private static final String KEY_DATA = "data";
    private static final String KEY_PAGE_COUNT = "pages";
    private static final String KEY_ROWS = "rows";
    @c(a = "data")
    private ArrayList<FirstPublishAlbumData> mAlbumList = new ArrayList();
    @c(a = "pages")
    private int mPageCount = 0;
    @c(a = "rows")
    private int mRows = 0;

    public int getPageCount() {
        return this.mPageCount;
    }

    public ArrayList<FirstPublishAlbumData> getDataList() {
        return this.mAlbumList;
    }

    public int getRows() {
        return this.mRows;
    }
}
