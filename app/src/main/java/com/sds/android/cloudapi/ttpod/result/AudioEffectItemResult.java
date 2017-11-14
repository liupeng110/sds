package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.LinkedList;
import java.util.List;

public class AudioEffectItemResult extends BaseResult {
    @c(a = "all_page")
    private int mAllPage = 0;
    @c(a = "data")
    private List<AudioEffectItem> mAudioEffect = new LinkedList();
    @c(a = "count")
    private int mCount = 0;

    public List<AudioEffectItem> getEffectList() {
        return this.mAudioEffect;
    }

    public int getAllPage() {
        return this.mAllPage;
    }

    public int getCount() {
        return this.mCount;
    }
}
