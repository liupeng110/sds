package com.tencent.map.b;

/* ProGuard */
public final class u {
    public byte[] a;
    public String b = "GBK";

    public final String toString() {
        try {
            return new String(this.a, this.b);
        } catch (Exception e) {
            return "";
        }
    }
}
