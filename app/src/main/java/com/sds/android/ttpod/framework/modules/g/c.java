package com.sds.android.ttpod.framework.modules.g;

import com.sds.android.cloudapi.ttpod.data.SplashItem;
import com.sds.android.cloudapi.ttpod.result.SplashDataResult;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* SplashInfoParser */
final class c {
    private SplashDataResult a;

    private c(SplashDataResult splashDataResult) {
        this.a = splashDataResult;
    }

    static c a(SplashDataResult splashDataResult) {
        if (splashDataResult == null) {
            return null;
        }
        return new c(splashDataResult);
    }

    int a() {
        return this.a != null ? this.a.getVersion() : 0;
    }

    SplashItem b() {
        List<SplashItem> c = c();
        if (c != null) {
            int toSeconds = (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            for (SplashItem splashItem : c) {
                if (splashItem.isContain(toSeconds)) {
                    return splashItem;
                }
            }
        }
        return null;
    }

    List<SplashItem> c() {
        if (this.a == null || !this.a.isSuccess() || this.a.isDataListEmpty()) {
            return null;
        }
        int toSeconds = (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        List<SplashItem> dataList = this.a.getDataList();
        List<SplashItem> arrayList = new ArrayList();
        for (SplashItem splashItem : dataList) {
            if (splashItem.isValid(toSeconds)) {
                arrayList.add(splashItem);
            }
        }
        return arrayList;
    }
}
