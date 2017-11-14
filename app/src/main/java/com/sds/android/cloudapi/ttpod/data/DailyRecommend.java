package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DailyRecommend implements Serializable {
    private static final long serialVersionUID = 8046350060729611541L;
    @c(a = "id")
    private long mId = 0;
    @c(a = "types")
    private List<String> mRecommendTypes = new ArrayList();
    @c(a = "weight")
    private long mWeight = 0;

    public long getId() {
        return this.mId;
    }

    public long getWeight() {
        return this.mWeight;
    }

    public List<String> getRecommendTypes() {
        return this.mRecommendTypes;
    }
}
