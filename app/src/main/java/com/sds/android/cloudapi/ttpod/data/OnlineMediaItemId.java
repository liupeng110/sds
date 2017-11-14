package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.util.ArrayList;
import java.util.List;

public class OnlineMediaItemId {
    private static final String KEY_SONG_IDS = "song_ids";
    @c(a = "song_ids")
    private ArrayList<Long> mOnlineMediaItemIdList = new ArrayList();

    public List<Long> getOnlineMediaItemIdList() {
        return this.mOnlineMediaItemIdList;
    }
}
