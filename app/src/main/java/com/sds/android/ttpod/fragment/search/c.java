package com.sds.android.ttpod.fragment.search;

import com.igexin.download.Downloads;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;

/* SearchUtils */
public final class c {
    public static void a(r rVar, int i) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", rVar.getValue(), s.PAGE_NONE.getValue(), s.PAGE_NONE.getValue());
        sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(i));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    protected static void a(SUserEvent sUserEvent, String str, int i) {
        sUserEvent.append("input_word", str);
        sUserEvent.append(ParamKey.COUNT, Integer.valueOf(i));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }
}
