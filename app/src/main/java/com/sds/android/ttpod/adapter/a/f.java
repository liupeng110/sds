package com.sds.android.ttpod.adapter.a;

import java.text.NumberFormat;

/* Velocity */
public class f {
    private double a;
    private long b;
    private long c;
    private double d = 0.0d;

    public f(long j) {
        this.b = j;
    }

    public String a(long j, long j2) {
        this.c = j2 - this.b;
        this.a = (double) j;
        if (this.c != 0) {
            this.d = (((this.a / 1024.0d) / 1024.0d) / ((double) this.c)) * 1000.0d;
        }
        return a(this.d, 2);
    }

    public String a() {
        return a(this.d, 2) + "MB/s";
    }

    public String toString() {
        return "transfer: " + this.a + ", duaring: " + this.c + ", speed: " + a() + "MB/s";
    }

    private String a(double d, int i) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMaximumIntegerDigits(i);
        return numberInstance.format(d);
    }
}
