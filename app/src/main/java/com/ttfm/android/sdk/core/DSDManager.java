package com.ttfm.android.sdk.core;

import android.content.Context;
import android.util.Log;
import com.sds.android.sdk.lib.util.f;
import com.ttfm.android.sdk.entity.DSDInfoResult;
import com.ttfm.android.sdk.http.HttpDSDServicesGet;
import com.ttfm.android.sdk.http.TTPodFMHttpClient;
import com.ttfm.android.sdk.storage.DSDServicesDB;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;

public class DSDManager {
    private static DSDInfoResult mDSDInfoResult;

    private DSDManager() {
    }

    public static void initDSD(Context context, long j) {
        String str = DSDServicesDB.get(context);
        if (str != null) {
            mDSDInfoResult = (DSDInfoResult) f.a(str, DSDInfoResult.class);
            setDSDInfo(mDSDInfoResult);
        }
    }

    public static void updateDSD(Context context, long j) {
        try {
            String str = new String(HttpDSDServicesGet.getInstance().get(j), TTPodFMHttpClient.AppEncode);
            Log.i("dsdservices", str);
            DSDInfoResult dSDInfoResult = (DSDInfoResult) f.a(str, DSDInfoResult.class);
            if (dSDInfoResult.isSuccess()) {
                setDSDInfo(dSDInfoResult);
                mDSDInfoResult = dSDInfoResult;
                DSDServicesDB.save(context, str);
            }
        } catch (Exception e) {
        }
    }

    public static String getPlayStreamDSDByFilter(HashMap<String, String> hashMap) {
        if (mDSDInfoResult != null) {
            String fMPlayStreamUrlFilter = mDSDInfoResult.getFMPlayStreamUrlFilter();
            if (fMPlayStreamUrlFilter != null) {
                fMPlayStreamUrlFilter = (String) hashMap.get(fMPlayStreamUrlFilter);
                if (fMPlayStreamUrlFilter != null) {
                    try {
                        return mDSDInfoResult.getFMPlayStreamUrl(Long.valueOf(fMPlayStreamUrlFilter).longValue());
                    } catch (IllegalFormatConversionException e) {
                    }
                }
            }
        }
        return GlobalEnv.FMPlaySteam;
    }

    private static void setDSDInfo(DSDInfoResult dSDInfoResult) {
        if (dSDInfoResult != null && dSDInfoResult.isSuccess()) {
            GlobalEnv.FMCenterUrl = dSDInfoResult.getFMCenterApiUrl();
            GlobalEnv.FMPlaySteam = dSDInfoResult.getFMPlayStreamUrl();
        }
    }
}
