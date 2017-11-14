package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.result.CirclePosterListResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHandpickResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHotListResultNew;
import com.sds.android.cloudapi.ttpod.result.HotSongOnlineMediaItemsResultNew;
import com.sds.android.cloudapi.ttpod.result.OperationZoneResult;
import java.io.Serializable;

public class ComposedModuleContent implements Serializable {
    @c(a = "banner")
    private FindSongHandpickResult mBanner = new FindSongHandpickResult();
    @c(a = "poster")
    private CirclePosterListResult mCirclePosters = new CirclePosterListResult();
    @c(a = "hotlist")
    private FindSongHotListResultNew mHotList = new FindSongHotListResultNew();
    @c(a = "hotsong")
    private HotSongOnlineMediaItemsResultNew mHotSongs = new HotSongOnlineMediaItemsResultNew();
    @c(a = "operationzone")
    private OperationZoneResult mOperationZone = new OperationZoneResult();

    public CirclePosterListResult getCirclePosters() {
        return this.mCirclePosters;
    }

    public OperationZoneResult getOperationZone() {
        return this.mOperationZone;
    }

    public FindSongHandpickResult getBanner() {
        return this.mBanner;
    }

    public FindSongHotListResultNew getHotList() {
        return this.mHotList;
    }

    public HotSongOnlineMediaItemsResultNew getHotSongs() {
        return this.mHotSongs;
    }
}
