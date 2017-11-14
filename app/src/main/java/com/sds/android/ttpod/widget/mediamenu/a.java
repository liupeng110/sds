package com.sds.android.ttpod.widget.mediamenu;

import android.view.View.OnClickListener;

/* MenuItem */
public class a implements Comparable<a> {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private OnClickListener f;

    public /* synthetic */ int compareTo(Object obj) {
        return a((a) obj);
    }

    public a(int i, int i2, int i3, int i4) {
        this.b = i;
        this.e = i2;
        this.a = i3;
        this.c = 0;
        this.d = i4;
    }

    public a(int i, int i2, int i3, int i4, int i5) {
        this.b = i;
        this.e = i2;
        this.a = i3;
        this.c = i4;
        this.d = i5;
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public OnClickListener e() {
        return this.f;
    }

    public int a(a aVar) {
        if (aVar != null) {
            return this.e - aVar.e;
        }
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        if (this.b != ((a) obj).b) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.b;
    }
}
