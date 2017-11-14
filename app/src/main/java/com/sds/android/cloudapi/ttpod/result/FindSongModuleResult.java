package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.FindSongModuleData;
import com.sds.android.sdk.lib.request.e;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class FindSongModuleResult extends e<FindSongModuleData> {
    private static final int INDEX_INIT_VALUE = -1;
    private int mIndex = -1;
    @c(a = "listen_records_post")
    private int mListenRecordsPost = 1;
    private HashMap<Integer, StyleDataListResult> mResultHashMap;
    @c(a = "usertype")
    private int mUserType;
    @c(a = "version")
    private long mVersion;

    public boolean isRecommendAPINeedListenRecords() {
        return this.mListenRecordsPost == 1;
    }

    public long getVersion() {
        return this.mVersion;
    }

    public int getUserType() {
        return this.mUserType;
    }

    public StyleDataListResult next() {
        setUp();
        HashMap hashMap = this.mResultHashMap;
        int i = this.mIndex + 1;
        this.mIndex = i;
        return (StyleDataListResult) hashMap.get(Integer.valueOf(i));
    }

    public boolean hasNext() {
        setUp();
        return this.mIndex + 1 < this.mResultHashMap.size();
    }

    public void moveTo(int i) {
        setUp();
        if (i >= size()) {
            throw new IndexOutOfBoundsException("index=" + i + ", size=" + size());
        }
        this.mIndex = i;
    }

    public int position() {
        setUp();
        return this.mIndex;
    }

    public int size() {
        setUp();
        return this.mResultHashMap.size();
    }

    private void setUp() {
        if (this.mResultHashMap == null) {
            this.mResultHashMap = new HashMap();
            this.mIndex = -1;
            FindSongModuleData findSongModuleData = (FindSongModuleData) getData();
            if (findSongModuleData != null) {
                TreeMap treeMap = new TreeMap();
                Iterator it = findSongModuleData.getPosts().iterator();
                while (it.hasNext()) {
                    CirclePosterListResult circlePosterListResult = (CirclePosterListResult) it.next();
                    treeMap.put(Integer.valueOf(circlePosterListResult.getOrder()), circlePosterListResult);
                }
                it = findSongModuleData.getSongLists().iterator();
                while (it.hasNext()) {
                    FindSongHotListResultNew findSongHotListResultNew = (FindSongHotListResultNew) it.next();
                    treeMap.put(Integer.valueOf(findSongHotListResultNew.getOrder()), findSongHotListResultNew);
                }
                it = findSongModuleData.getBanners().iterator();
                while (it.hasNext()) {
                    OperationZoneResult operationZoneResult = (OperationZoneResult) it.next();
                    treeMap.put(Integer.valueOf(operationZoneResult.getOrder()), operationZoneResult);
                }
                Iterator it2 = findSongModuleData.getZones().iterator();
                while (it2.hasNext()) {
                    FindSongHandpickResult findSongHandpickResult = (FindSongHandpickResult) it2.next();
                    treeMap.put(Integer.valueOf(findSongHandpickResult.getOrder()), findSongHandpickResult);
                }
                int size = treeMap.size() - 1;
                int i = size;
                for (StyleDataListResult put : treeMap.values()) {
                    int i2 = i - 1;
                    this.mResultHashMap.put(Integer.valueOf(i), put);
                    i = i2;
                }
            }
        }
    }
}
