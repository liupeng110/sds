package com.sds.android.sdk.lib.request;

import com.b.a.a.c;
import java.util.ArrayList;

/* DataListResult */
public class d<D> extends BaseResult {
    @c(a = "data")
    private ArrayList<D> mDataList = new ArrayList();

    public ArrayList<D> getDataList() {
        return this.mDataList;
    }

    public boolean isDataListEmpty() {
        return this.mDataList.size() == 0;
    }
}
