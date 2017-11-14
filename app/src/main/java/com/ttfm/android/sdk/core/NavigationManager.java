package com.ttfm.android.sdk.core;

import android.content.Context;
import android.util.Log;
import com.sds.android.sdk.lib.util.f;
import com.ttfm.android.sdk.entity.NavigationEntity;
import com.ttfm.android.sdk.entity.NavigationGetResult;
import com.ttfm.android.sdk.storage.NavigationDB;
import java.util.List;

public class NavigationManager {
    private static NavigationGetResult mNavigationResult;

    private NavigationManager() {
    }

    public static void initNavigation(Context context) {
        String str = NavigationDB.get(context);
        if (str != null) {
            mNavigationResult = (NavigationGetResult) f.a(str, NavigationGetResult.class);
        }
    }

    public static boolean updateNavigation(Context context, long j) {
        try {
            String dynamicTagList = TTFMSDK.getInstance().getDynamicTagList(j);
            Log.i("navservices", dynamicTagList);
            NavigationGetResult navigationGetResult = (NavigationGetResult) f.a(dynamicTagList, NavigationGetResult.class);
            if (navigationGetResult.isSuccess()) {
                mNavigationResult = navigationGetResult;
                NavigationDB.save(context, dynamicTagList);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static NavigationGetResult getNavigationGetResult() {
        return mNavigationResult;
    }

    public static List<NavigationEntity> getNavigations() {
        if (mNavigationResult != null) {
            return mNavigationResult.getNavigations();
        }
        return null;
    }
}
