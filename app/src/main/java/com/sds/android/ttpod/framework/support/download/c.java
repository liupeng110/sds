package com.sds.android.ttpod.framework.support.download;

import com.sds.android.sdk.core.statistic.SEngine;
import com.sds.android.sdk.core.statistic.SSystemEvent;

/* SEngineWrapper */
public class c {
    public void a(SSystemEvent sSystemEvent) {
        SEngine.instance();
        SEngine.post(sSystemEvent);
    }
}
