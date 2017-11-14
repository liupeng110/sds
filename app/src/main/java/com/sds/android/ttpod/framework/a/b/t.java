package com.sds.android.ttpod.framework.a.b;

import com.igexin.download.Downloads;
import com.sds.android.sdk.core.statistic.SEngine.Page;
import com.sds.android.sdk.core.statistic.SUserEvent;

/* SUserUtils */
public class t {
    @Deprecated
    public static void a(String str, int i, int i2, int i3) {
        new SUserEvent(str, i, i2, i3).post();
    }

    @Deprecated
    public static void a(String str, r rVar, s sVar, s sVar2) {
        a(str, rVar.getValue(), sVar.getValue(), sVar2.getValue());
    }

    public static void b(String str, int i, int i2, int i3) {
        SUserEvent sUserEvent = new SUserEvent(str, i, i2, i3);
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    public static void b(String str, r rVar, s sVar, s sVar2) {
        b(str, rVar.getValue(), sVar.getValue(), sVar2.getValue());
    }

    public static void a(r rVar, s sVar) {
        b("PAGE_CLICK", rVar.getValue(), 0, sVar.getValue());
    }

    public static void a(int i, s sVar) {
        b("PAGE_CLICK", i, 0, sVar.getValue());
    }

    public static void a(int i) {
        Page.updatePageProperties("position", Integer.valueOf(i + 1));
    }

    public static void a(r rVar, boolean z) {
        int i = 1;
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", rVar.getValue(), 0, 0);
        sUserEvent.setPageParameter(true);
        String str = Downloads.COLUMN_STATUS;
        if (!z) {
            i = 0;
        }
        sUserEvent.append(str, Integer.valueOf(i));
        sUserEvent.post();
    }
}
