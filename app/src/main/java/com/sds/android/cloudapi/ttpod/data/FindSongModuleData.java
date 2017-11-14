package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.result.CirclePosterListResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHandpickResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHotListResultNew;
import com.sds.android.cloudapi.ttpod.result.OperationZoneResult;
import java.io.Serializable;
import java.util.ArrayList;

public class FindSongModuleData implements Serializable {
    @c(a = "banners")
    private ArrayList<OperationZoneResult> mBanners = new ArrayList();
    @c(a = "posters")
    private ArrayList<CirclePosterListResult> mCirclePosts = new ArrayList();
    @c(a = "songlists")
    private ArrayList<FindSongHotListResultNew> mSongLists = new ArrayList();
    @c(a = "promotionzones")
    private ArrayList<FindSongHandpickResult> mZones = new ArrayList();

    public ArrayList<CirclePosterListResult> getPosts() {
        return this.mCirclePosts;
    }

    public ArrayList<FindSongHandpickResult> getZones() {
        return this.mZones;
    }

    public ArrayList<FindSongHotListResultNew> getSongLists() {
        return this.mSongLists;
    }

    public ArrayList<OperationZoneResult> getBanners() {
        return this.mBanners;
    }
}
