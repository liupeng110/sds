package com.sds.android.sdk.core.statistic;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import com.sds.android.sdk.core.statistic.IService.Stub;
import com.sds.android.sdk.lib.util.g;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SService extends Service {
    public static final String KEY_SEVENT = "sEvent";
    public static final String STATISTIC_DATA = "data";
    public static final String STATISTIC_PARAM = "param";
    private static long mIndex;
    private SBinder mSBinder;
    private SManager mSManager;
    private String mSessionID;

    class SBinder extends Stub {
        SBinder() {
        }

        public void post(SEvent sEvent) throws RemoteException {
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (SService.this) {
                SService.mIndex = SService.this.increaseNumber(SService.mIndex);
                sEvent.append(SEvent.FIELD_SESSION_ID, SService.this.mSessionID);
                sEvent.append(SEvent.FIELD_INDEX, Long.valueOf(SService.mIndex));
                SService.this.mSManager.addEvent(sEvent);
            }
            g.c("SService", "post durtion: " + (System.currentTimeMillis() - currentTimeMillis));
        }

        public void setURL(String str) throws RemoteException {
            SService.this.mSManager.setURL(str);
        }

        public void setGeneralParameters(Map map) throws RemoteException {
            SService.this.mSManager.setGeneralParameters(map);
        }

        public List<SEvent> getLastCacheEventList() throws RemoteException {
            List lastCacheEventList;
            synchronized (SService.this) {
                lastCacheEventList = SService.this.mSManager.getLastCacheEventList();
            }
            return lastCacheEventList;
        }
    }

    private long increaseNumber(long j) {
        if (j < Long.MAX_VALUE) {
            return 1 + j;
        }
        return 0;
    }

    public IBinder onBind(Intent intent) {
        if (this.mSBinder == null) {
            this.mSBinder = new SBinder();
        }
        return this.mSBinder;
    }

    public void onCreate() {
        super.onCreate();
        String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + getPackageName() + "/cache/";
        this.mSessionID = buildSessionID();
        this.mSManager = new SManager(str);
        this.mSManager.onCreate();
    }

    private String buildSessionID() {
        return UUID.randomUUID().toString();
    }

    public void onDestroy() {
        super.onDestroy();
        mIndex = 0;
        this.mSManager.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                this.mSManager.addEvent((SEvent) extras.getParcelable(KEY_SEVENT));
            }
        }
        return super.onStartCommand(intent, i, i2);
    }
}
