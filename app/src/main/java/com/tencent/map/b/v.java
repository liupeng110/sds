package com.tencent.map.b;

/* ProGuard */
public final class v {
    private static v b;
    private int a = 0;

    public static v a() {
        if (b == null) {
            b = new v();
        }
        return b;
    }

    private v() {
    }
}
