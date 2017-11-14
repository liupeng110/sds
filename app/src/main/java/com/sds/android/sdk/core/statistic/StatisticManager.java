package com.sds.android.sdk.core.statistic;

import android.os.Environment;
import com.sds.android.sdk.core.statistic.StatisticCommitter.CallBack;
import com.sds.android.sdk.core.statistic.StatisticHelper.SendMode;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class StatisticManager implements CallBack {
    private static final String CACHE_FILE_DIR = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + EnvironmentUtils.a() + "/.cache/");
    private static final int MAX_INC_NUM = 50;
    private static final Object SYNC_OBJECT = new Object();
    private static final String TAG = "StatisticManager";
    private static StatisticManager mManager;
    private final String mEventLongCacheDir = (CACHE_FILE_DIR + "statisticLongDelay");
    private StatisticCacheList mEventLongCacheList;
    private final String mEventShortCacheDir = (CACHE_FILE_DIR + "statisticShortDelay");
    private StatisticCacheList mEventShortCacheList;
    private List<StatisticEvent> mLongEventList = new ArrayList();
    private final String mLongTimeStatisticTmp = (CACHE_FILE_DIR + "statisticLongDelayTmp");
    private List<StatisticEvent> mPostingEventList = new ArrayList();
    private List<StatisticEvent> mShortEventList = new ArrayList();
    private final String mShortTimeStatisticTmp = (CACHE_FILE_DIR + "statisticShortDelayTmp");
    private StatisticCommitter mStatisticCommitter = new StatisticCommitter(this);
    private StatisticConfig mStatisticConfig = new StatisticConfig();
    private final String mStatisticConfigFile = (CACHE_FILE_DIR + "statisticConfig.json");

    public static StatisticManager getInstance() {
        if (mManager == null) {
            synchronized (SYNC_OBJECT) {
                if (mManager == null) {
                    mManager = new StatisticManager();
                    mManager.init();
                }
            }
        }
        return mManager;
    }

    public static void destroyInstance() {
        if (mManager != null) {
            synchronized (SYNC_OBJECT) {
                if (mManager != null) {
                    mManager.unInit();
                    mManager = null;
                }
            }
        }
    }

    public void setStatisticUrl(String str) {
        this.mStatisticCommitter.setUrl(str);
    }

    public void addEvent(StatisticEvent statisticEvent) {
        if (statisticEvent != null) {
            adjustEventByConfig(statisticEvent);
            synchronized (SYNC_OBJECT) {
                if (this.mLongEventList.size() > 250) {
                    g.a(TAG, "long cache add last" + statisticEvent.toString());
                    transCacheList(this.mLongEventList, this.mEventLongCacheList);
                }
                if (this.mShortEventList.size() > 250) {
                    g.a(TAG, "short cache add last" + statisticEvent.toString());
                    transCacheList(this.mShortEventList, this.mEventShortCacheList);
                }
                switch (SendMode.getSendMode(statisticEvent)) {
                    case IMMEDIATE:
                        g.a(TAG, "add immediate event  = " + statisticEvent.toString());
                        add(statisticEvent, this.mShortEventList);
                        commit();
                        break;
                    case DELAY:
                        g.a(TAG, "add delay event  = " + statisticEvent.toString());
                        add(statisticEvent, this.mLongEventList);
                        commit();
                        break;
                }
            }
        }
    }

    private void transCacheList(List<StatisticEvent> list, StatisticCacheList statisticCacheList) {
        if (statisticCacheList != null && list != null && !list.isEmpty()) {
            List arrayList = new ArrayList();
            int size = list.size() / 200;
            while (true) {
                int i = size - 1;
                if (size > 0) {
                    int size2 = list.size();
                    for (size = size2 - 1; size >= size2 - 200; size--) {
                        arrayList.add(list.get(size));
                        list.remove(size);
                    }
                    statisticCacheList.addLast(arrayList);
                    arrayList.clear();
                    size = i;
                } else {
                    return;
                }
            }
        }
    }

    private void adjustEventByConfig(StatisticEvent statisticEvent) {
        int intValue = ((Integer) statisticEvent.getDateMap().get("key")).intValue();
        if (this.mStatisticConfig.getControlCodeMap().containsKey(Integer.valueOf(intValue))) {
            statisticEvent.setControlCode(((Integer) this.mStatisticConfig.getControlCodeMap().get(Integer.valueOf(intValue))).intValue());
        }
    }

    private void add(StatisticEvent statisticEvent, List<StatisticEvent> list) {
        g.a(TAG, "add");
        StatisticEvent findEqualEvent = findEqualEvent(statisticEvent, list);
        if (findEqualEvent == null || !findEqualEvent.combine(statisticEvent)) {
            list.add(statisticEvent);
        }
    }

    private StatisticEvent findEqualEvent(StatisticEvent statisticEvent, List<StatisticEvent> list) {
        for (StatisticEvent statisticEvent2 : list) {
            if (StatisticHelper.equalData(statisticEvent2, statisticEvent)) {
                return statisticEvent2;
            }
        }
        return null;
    }

    private void transEventPosting(List<StatisticEvent> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            StatisticEvent statisticEvent = (StatisticEvent) list.get(size);
            if (statisticEvent.isCompleted()) {
                this.mPostingEventList.add(statisticEvent);
                list.remove(size);
            }
        }
    }

    private void commit() {
        if (!this.mStatisticCommitter.isCommitting()) {
            this.mStatisticCommitter.setCommitRunning(true);
            if (this.mStatisticCommitter.isMinDelayTimeArrived()) {
                transEventPosting(this.mShortEventList);
            }
            if (this.mStatisticCommitter.isMaxDelayTimeArrived()) {
                transEventPosting(this.mLongEventList);
            }
            g.a(TAG, "commit");
            if (this.mPostingEventList.isEmpty()) {
                this.mStatisticCommitter.setCommitRunning(false);
            } else {
                this.mStatisticCommitter.postStatistic(this.mPostingEventList);
            }
        }
    }

    private void commitCache() {
        if (!this.mEventShortCacheList.isEmpty()) {
            transEventPosting(this.mEventShortCacheList.getFirst());
            this.mEventShortCacheList.removeFirst();
        } else if (!this.mEventLongCacheList.isEmpty()) {
            transEventPosting(this.mEventLongCacheList.getFirst());
            this.mEventLongCacheList.removeFirst();
        }
        g.a(TAG, "commitCache");
        this.mStatisticCommitter.postStatistic(this.mPostingEventList);
    }

    private void restoreEventList(List<StatisticEvent> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            StatisticEvent statisticEvent = (StatisticEvent) list.get(size);
            list.remove(size);
            addEvent(statisticEvent);
        }
    }

    private void init() {
        this.mEventLongCacheList = new StatisticCacheList(this.mEventLongCacheDir);
        this.mEventShortCacheList = new StatisticCacheList(this.mEventShortCacheDir);
        loadEventList();
        if (this.mLongEventList.isEmpty() && !this.mEventLongCacheList.isEmpty()) {
            this.mLongEventList.addAll(this.mEventLongCacheList.getFirst());
        }
        if (this.mShortEventList.isEmpty() && !this.mEventShortCacheList.isEmpty()) {
            this.mShortEventList.addAll(this.mEventShortCacheList.getFirst());
        }
        loadConfig();
    }

    private void unInit() {
        g.a(TAG, "unInit");
        saveEventList();
    }

    private void loadConfig() {
        try {
            if (e.b(this.mStatisticConfigFile)) {
                JSONArray jSONArray = new JSONArray(e.i(this.mStatisticConfigFile));
                if (!jSONArray.isNull(0)) {
                    this.mStatisticConfig.fromJsonObject(jSONArray.getJSONObject(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSuccess(Iterable<StatisticEvent> iterable, String str) {
        synchronized (SYNC_OBJECT) {
            g.a(TAG, "onSuccess");
            this.mPostingEventList.clear();
            saveEventList();
            if (hasEventCache()) {
                commitCache();
            } else {
                this.mStatisticCommitter.setCommitRunning(false);
            }
        }
    }

    private boolean hasEventCache() {
        return (this.mEventShortCacheList.isEmpty() && this.mEventLongCacheList.isEmpty()) ? false : true;
    }

    private void loadEventList() {
        g.a(TAG, "loadEventList");
        this.mLongEventList.addAll(StatisticHelper.loadEventList(this.mLongTimeStatisticTmp));
        this.mShortEventList.addAll(StatisticHelper.loadEventList(this.mShortTimeStatisticTmp));
    }

    private void saveEventList() {
        g.a(TAG, "saveEventList");
        e.a(StatisticHelper.transJSONArray(this.mLongEventList).toString(), this.mLongTimeStatisticTmp);
        e.a(StatisticHelper.transJSONArray(this.mShortEventList).toString(), this.mShortTimeStatisticTmp);
    }

    public void onError(Iterable<StatisticEvent> iterable, String str) {
        synchronized (SYNC_OBJECT) {
            g.a(TAG, "onError");
            restoreEventList(this.mPostingEventList);
            this.mPostingEventList.clear();
            saveEventList();
            this.mStatisticCommitter.setCommitRunning(false);
        }
    }
}
