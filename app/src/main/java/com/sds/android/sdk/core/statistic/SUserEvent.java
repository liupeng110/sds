package com.sds.android.sdk.core.statistic;

public class SUserEvent extends SEvent {
    public SUserEvent(String str, int i, int i2) {
        this(str, i, i2, 0);
    }

    public SUserEvent(String str, int i, int i2, int i3) {
        super(str, Integer.valueOf(i));
        append("page", Integer.valueOf(i2));
        append(SEvent.FIELD_TO, Integer.valueOf(i3));
    }

    public SUserEvent(String str, int i, String str2, String str3) {
        super(str, Integer.valueOf(i));
        append("page", str2);
        append(SEvent.FIELD_TO, str3);
    }
}
