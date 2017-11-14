package com.sds.android.ttpod.framework.modules.b;

import java.io.Serializable;

/* ScenePlayedRecord */
public class d implements Serializable {
    public static final Long a = Long.valueOf(-1);
    private static final int[] b = new int[]{7, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 6, 7};
    private long[] c = new long[8];

    public void a(int i, Long l) {
        this.c[b(i)] = l.longValue();
    }

    public long a(int i) {
        return this.c[b(i)];
    }

    private int b(int i) {
        return b[i];
    }
}
