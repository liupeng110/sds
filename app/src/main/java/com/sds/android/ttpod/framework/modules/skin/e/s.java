package com.sds.android.ttpod.framework.modules.skin.e;

import java.io.Serializable;

/* TrcTimeRegion */
public final class s implements Serializable {
    private final int a;
    private final int b;
    private int c;

    public int a() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public s(int i, int i2) {
        this.a = i;
        if (i2 < 1) {
            i2 = 1;
        }
        this.b = i2;
    }

    public String toString() {
        return "TrcTimeRegion [mCharsCount=" + this.a + ", mDuration=" + this.b + ", mTextWidth=" + this.c + "]";
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.b;
    }
}
