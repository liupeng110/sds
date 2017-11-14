package com.tencent.wxop.stat.a;

import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;

public enum e {
    PAGE_VIEW(1),
    SESSION_ENV(2),
    ERROR(3),
    CUSTOM(1000),
    ADDITION(1001),
    MONITOR_STAT(1002),
    MTA_GAME_USER(1003),
    NETWORK_MONITOR(VersionUpdateConst.UPDATE_TENCENT_TYPE),
    NETWORK_DETECTOR(VersionUpdateConst.UPDATE_ANZHI_TYPE);
    
    private int bG;

    private e(int i) {
        this.bG = i;
    }

    public final int r() {
        return this.bG;
    }
}
