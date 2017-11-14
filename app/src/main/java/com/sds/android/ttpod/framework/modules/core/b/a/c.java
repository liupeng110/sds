package com.sds.android.ttpod.framework.modules.core.b.a;

import com.igexin.download.Downloads;

/* ShakeSensitivityType */
public enum c {
    SMOOTH_SHAKE(584),
    EASY_SHAKE(560),
    NORMAL_SHAKE(Downloads.STATUS_HTTP_EXCEPTION),
    HARD_SHAKE(448),
    EXTREME_SHAKE(384);
    
    private int mValue;

    private c(int i) {
        this.mValue = i;
    }

    int value() {
        return this.mValue;
    }
}
