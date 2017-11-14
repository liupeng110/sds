package com.sds.android.sdk.core.statistic;

import com.sds.android.sdk.lib.util.e;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StatisticCacheList {
    public static final int CACHE_BLOCK_SIZE = 200;
    private static final int DEFAULT_NUM = 5;
    private static final String PREFIX_STATISTIC_FILE_NAME = "statistic_";
    private LinkedList<File> mCacheBlockList;
    private int mCacheBlockNum;
    private File mStatisticDir;

    public StatisticCacheList(String str, int i) {
        this.mCacheBlockList = new LinkedList();
        this.mCacheBlockNum = i;
        if (!e.d(str)) {
            e.f(str);
        }
        this.mStatisticDir = new File(str);
        this.mCacheBlockList.addAll(Arrays.asList(this.mStatisticDir.listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.contains("statistic_");
            }
        })));
        Collections.sort(this.mCacheBlockList);
        while (this.mCacheBlockList.size() > this.mCacheBlockNum) {
            e.c((File) this.mCacheBlockList.removeFirst());
        }
    }

    public StatisticCacheList(String str) {
        this(str, 5);
    }

    public List<StatisticEvent> getFirst() {
        if (this.mCacheBlockList.isEmpty()) {
            return null;
        }
        return StatisticHelper.loadEventList(((File) this.mCacheBlockList.getFirst()).getAbsolutePath());
    }

    public boolean removeFirst() {
        return this.mCacheBlockList.isEmpty() || e.c((File) this.mCacheBlockList.removeFirst());
    }

    public void addLast(List<StatisticEvent> list) {
        if (list != null) {
            if (!this.mCacheBlockList.isEmpty() && this.mCacheBlockNum <= this.mCacheBlockList.size()) {
                e.c((File) this.mCacheBlockList.poll());
            }
            String str = this.mStatisticDir.getAbsolutePath() + File.separator + "statistic_" + System.currentTimeMillis();
            if (e.a(StatisticHelper.transJSONArray(list).toString(), str)) {
                this.mCacheBlockList.addLast(new File(str));
            }
        }
    }

    public boolean isEmpty() {
        return this.mCacheBlockList.size() == 0;
    }
}
