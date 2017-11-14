package com.sds.android.sdk.core.statistic;

import android.os.Handler;
import com.sds.android.sdk.core.statistic.SUtils.PostResult;
import com.sds.android.sdk.lib.util.g;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SCacheStrategy implements IStrategy {
    private static final int MAX_CACHE_EVENTS = 20;
    private static final int MAX_REMAIN_FILE = 100;
    private static final long MAX_TIME = 45000;
    private static String mCacheFilePath;
    private static long mDeep;
    private static String mDeepFilePath;
    private static LinkedList<String> mFilePathList = new LinkedList();
    private static Handler mHandler;
    private static HashMap<String, Object> mMap;
    private static String mURL;
    private LinkedList<SEvent> mCacheEventList = new LinkedList();
    private int mCurrentWriteCount;
    private String mCurrentWriteFilePath;
    private long mStartTime;

    static class LoadFileEventRunnable implements Runnable {
        LoadFileEventRunnable() {
        }

        public void run() {
            long initEventFiles = SUtils.initEventFiles(SCacheStrategy.mFilePathList, SCacheStrategy.mCacheFilePath, SManager.PREFIX_STATISTIC_FILE_NAME, 100);
            SCacheStrategy.mDeep = SUtils.readDeep(SCacheStrategy.mDeepFilePath);
            SSystemEvent sSystemEvent = new SSystemEvent("SYS_LOG", "loss");
            sSystemEvent.setPostStrategy(SPostStrategy.IMMEDIATELAY_WRITE_FILE);
            sSystemEvent.append("totals", Long.valueOf(initEventFiles));
            sSystemEvent.post();
            g.c("Loss", "loss: " + initEventFiles);
        }
    }

    static class PostFileRunnable implements Runnable {
        private String mFilePath;

        PostFileRunnable(String str) {
            this.mFilePath = str;
        }

        public void run() {
            SCacheStrategy.handleResult(SUtils.postFileEvent(SCacheStrategy.mMap, SCacheStrategy.mURL, this.mFilePath), this.mFilePath);
        }
    }

    static class PostListEventRunnable implements Runnable {
        private String mFilePath;
        private LinkedList<SEvent> mList;

        PostListEventRunnable(LinkedList<SEvent> linkedList, String str) {
            this.mList = linkedList;
            this.mFilePath = str;
        }

        public void run() {
            SCacheStrategy.handleResult(SUtils.postListEvent(this.mList, SCacheStrategy.mMap, SCacheStrategy.mURL), this.mFilePath);
        }
    }

    static class WriteEventRunnable implements Runnable {
        private String mFileName;
        private SEvent mSEvent;

        WriteEventRunnable(String str, SEvent sEvent) {
            this.mFileName = str;
            this.mSEvent = sEvent;
        }

        public void run() {
            if (SCacheStrategy.mDeep < Long.MAX_VALUE) {
                SCacheStrategy.access$308();
            } else {
                SCacheStrategy.mDeep = 0;
            }
            this.mSEvent.append(SEvent.FIELD_DEEP, Long.valueOf(SCacheStrategy.mDeep));
            g.c("SCacheStrategy", "WriteEventRunnable: " + this.mSEvent);
            SUtils.writeEventToFile(this.mFileName, this.mSEvent);
            SUtils.writeDeep(SCacheStrategy.mDeepFilePath, SCacheStrategy.mDeep);
        }
    }

    static /* synthetic */ long access$308() {
        long j = mDeep;
        mDeep = 1 + j;
        return j;
    }

    SCacheStrategy(Handler handler, String str) {
        mHandler = handler;
        mCacheFilePath = str;
        mDeepFilePath = mCacheFilePath + File.separator + SEvent.FIELD_DEEP;
    }

    public void onCreate() {
        sendLoadFileEventMessage();
        this.mCurrentWriteFilePath = SUtils.buildCurrentWirteFilePath(mCacheFilePath, SManager.PREFIX_STATISTIC_FILE_NAME);
    }

    public void setURL(String str) {
        mURL = str;
    }

    public void setGeneralParameters(Map map) {
        mMap = (HashMap) map;
    }

    public LinkedList<SEvent> getLastCacheEventList() {
        return new LinkedList(this.mCacheEventList);
    }

    public void onAddEvent(SEvent sEvent) {
        long timestamp = sEvent.timestamp();
        this.mCurrentWriteCount++;
        if (this.mStartTime == 0) {
            this.mStartTime = timestamp;
        }
        this.mCacheEventList.offer(sEvent);
        sendWriteEventMessage(this.mCurrentWriteFilePath, sEvent);
        if (20 <= this.mCurrentWriteCount || MAX_TIME <= timestamp - this.mStartTime) {
            this.mCurrentWriteCount = 0;
            sendPostListEventMessage(new LinkedList(this.mCacheEventList), this.mCurrentWriteFilePath);
            this.mCacheEventList.clear();
            this.mCurrentWriteFilePath = SUtils.buildCurrentWirteFilePath(mCacheFilePath, SManager.PREFIX_STATISTIC_FILE_NAME);
        }
        this.mStartTime = System.currentTimeMillis();
    }

    public void onDestroy() {
    }

    private static void sendWriteEventMessage(String str, SEvent sEvent) {
        mHandler.post(new WriteEventRunnable(str, sEvent));
    }

    private static void sendPostListEventMessage(LinkedList<SEvent> linkedList, String str) {
        mHandler.post(new PostListEventRunnable(linkedList, str));
    }

    private static void sendPostFileMessage(String str) {
        mHandler.post(new PostFileRunnable(str));
    }

    private static void sendLoadFileEventMessage() {
        mHandler.post(new LoadFileEventRunnable());
    }

    private static void handleResult(PostResult postResult, String str) {
        if (PostResult.POST_SUCCESSED == postResult) {
            handlePostSuccessed(str);
        } else if (PostResult.POST_FAILED == postResult) {
            handlePostFailed(str);
        }
    }

    private static void handlePostFailed(String str) {
        mFilePathList.add(str);
        if (mFilePathList.size() > 100) {
            mFilePathList.removeFirst();
        }
    }

    private static void handlePostSuccessed(String str) {
        mFilePathList.remove(str);
        SUtils.deleteFile(str);
        if (mFilePathList.size() > 0) {
            sendPostFileMessage((String) mFilePathList.remove());
        }
    }
}
