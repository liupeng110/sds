package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.util.ArrayList;

public class DataTopList {
    @c(a = "list")
    private ArrayList<MarketApp> mListApps = new ArrayList();
    @c(a = "top")
    private ArrayList<MarketApp> mTopApps = new ArrayList();

    public ArrayList<MarketApp> getListApps() {
        return this.mListApps;
    }

    public ArrayList<MarketApp> getTopApps() {
        return this.mTopApps;
    }
}
