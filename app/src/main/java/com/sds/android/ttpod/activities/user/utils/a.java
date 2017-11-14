package com.sds.android.ttpod.activities.user.utils;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/* BirthdayDate */
public class a {
    private final int a;
    private final int b;
    private final int c;

    public a(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    public a(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(TimeUnit.SECONDS.toMillis(j));
        this.a = instance.get(1);
        this.b = instance.get(2);
        this.c = instance.get(5);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public String toString() {
        return String.format("%d-%d-%d", new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b + 1), Integer.valueOf(this.c)});
    }

    public long d() {
        return TimeUnit.MILLISECONDS.toSeconds(e());
    }

    public long e() {
        Calendar instance = Calendar.getInstance();
        instance.set(this.a, this.b, this.c);
        return instance.getTimeInMillis();
    }

    public boolean equals(Object obj) {
        return (obj instanceof a) && this.a == ((a) obj).a && this.b == ((a) obj).b && this.c == ((a) obj).c;
    }

    public int hashCode() {
        return (int) d();
    }
}
