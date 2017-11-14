package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.RelatedSingerItem;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.ArrayList;
import java.util.List;

public class OnlineRelatedSingersResult extends BaseResult {
    @c(a = "data")
    private List<RelatedSingerItem> mItems;

    public List<RelatedSingerItem> getOnlineRelatedSingerItems() {
        if (this.mItems == null) {
            this.mItems = new ArrayList();
        }
        return this.mItems;
    }
}
