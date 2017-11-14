package com.sds.android.sdk.core.statistic;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.sds.android.sdk.lib.util.EnvironmentUtils.a;
import com.sds.android.sdk.lib.util.EnvironmentUtils.b;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.ttfm.android.sdk.http.HttpChannelSongListGetV2;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class StatisticCommitter {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final int COMMIT_TYPE_DAY = 6;
    private static final int COMMIT_TYPE_FIVE_MINUTE = 2;
    private static final int COMMIT_TYPE_HALF_DAY = 5;
    private static final int COMMIT_TYPE_HALF_HOUR = 3;
    private static final int COMMIT_TYPE_HOUR = 4;
    private static final int COMMIT_TYPE_IMMEDIATE = 0;
    private static final int COMMIT_TYPE_MINUTE = 1;
    private static final int MSG_POST = 0;
    private static final String TAG = "StatisticCommit";
    public static final String TEST_URL_MAIN = "http://test.log.ttpod.com/test_log";
    private static final long[] TIME_INTERVAL_MILLIS = new long[]{0, 60000, 300000, 1800000, 3600000, 43200000, HttpChannelSongListGetV2.CACHE_TIME};
    public static final String URL_MAIN = "http://collect.log.ttpod.com/ttpod_client/index.html";
    private CallBack mCallBack;
    private boolean mCommitRunning;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private long mLastMaxDelayTimeMillis;
    private long mLastMinDelayTimeMillis;
    private String mUrl = URL_MAIN;

    public interface CallBack {
        void onError(Iterable<StatisticEvent> iterable, String str);

        void onSuccess(Iterable<StatisticEvent> iterable, String str);
    }

    static {
        boolean z;
        if (StatisticCommitter.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        $assertionsDisabled = z;
    }

    public StatisticCommitter(CallBack callBack) {
        if ($assertionsDisabled || callBack == null) {
            this.mCallBack = callBack;
            this.mHandlerThread = new HandlerThread("postHandlerThread");
            this.mHandlerThread.start();
            this.mHandler = new Handler(this.mHandlerThread.getLooper()) {
                public void handleMessage(Message message) {
                    switch (message.what) {
                        case 0:
                            postStatistic((List) message.obj);
                            return;
                        default:
                            return;
                    }
                }

                private void postStatistic(Iterable<StatisticEvent> iterable) {
                    String uuid = UUID.randomUUID().toString();
                    try {
                        JSONArray jSONArray = new JSONArray();
                        for (StatisticEvent statisticEvent : iterable) {
                            JSONObject jSONObject = new JSONObject();
                            for (Entry entry : statisticEvent.getDateMap().entrySet()) {
                                jSONObject.put((String) entry.getKey(), entry.getValue());
                            }
                            jSONArray.put(jSONObject);
                        }
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("data", jSONArray);
                        jSONObject2.put(SService.STATISTIC_PARAM, b.f());
                        jSONObject2.put("uuid", uuid);
                        jSONObject2.put("time", System.currentTimeMillis());
                        String jSONObject3 = jSONObject2.toString();
                        g.a(StatisticCommitter.TAG, "send statistic url: " + StatisticCommitter.this.mUrl);
                        g.a(StatisticCommitter.TAG, "send statistic data: " + jSONObject3);
                        HttpResponse request = HttpClientProxy.instance().request(StatisticCommitter.this.mUrl, jSONObject3);
                        HttpClientProxy.instance().closePost();
                        if (200 == request.getStatusLine().getStatusCode()) {
                            StatisticCommitter.this.mCallBack.onSuccess(iterable, uuid);
                        } else {
                            StatisticCommitter.this.mCallBack.onError(iterable, uuid);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        StatisticCommitter.this.mCallBack.onError(iterable, uuid + "_" + e.toString());
                        HttpClientProxy.instance().closePost();
                    }
                }
            };
            return;
        }
        throw new AssertionError("callback can not null!");
    }

    protected boolean isCommitting() {
        return this.mCommitRunning;
    }

    protected boolean isMinDelayTimeArrived() {
        boolean z = true;
        long currentTimeMillis = System.currentTimeMillis();
        if (a.i()) {
            if (currentTimeMillis - this.mLastMinDelayTimeMillis < TIME_INTERVAL_MILLIS[0]) {
                z = false;
            }
        } else if (currentTimeMillis - this.mLastMinDelayTimeMillis < TIME_INTERVAL_MILLIS[1]) {
            z = false;
        }
        if (z) {
            this.mLastMinDelayTimeMillis = currentTimeMillis;
        }
        return z;
    }

    protected boolean isMaxDelayTimeArrived() {
        boolean z = true;
        long currentTimeMillis = System.currentTimeMillis();
        if (a.i()) {
            if (currentTimeMillis - this.mLastMaxDelayTimeMillis < TIME_INTERVAL_MILLIS[0]) {
                z = false;
            }
        } else if (currentTimeMillis - this.mLastMaxDelayTimeMillis < TIME_INTERVAL_MILLIS[2]) {
            z = false;
        }
        if (z) {
            this.mLastMaxDelayTimeMillis = currentTimeMillis;
        }
        return z;
    }

    protected void setCommitRunning(boolean z) {
        g.a(TAG, "commitRunning = " + z);
        this.mCommitRunning = z;
    }

    protected void setUrl(String str) {
        this.mUrl = str;
    }

    protected String getUrl() {
        return m.a(this.mUrl) ? URL_MAIN : this.mUrl;
    }

    protected void postStatistic(List<StatisticEvent> list) {
        Message obtainMessage = this.mHandler.obtainMessage(0);
        obtainMessage.obj = list;
        this.mHandler.sendMessage(obtainMessage);
    }
}
