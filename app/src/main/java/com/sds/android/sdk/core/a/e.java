package com.sds.android.sdk.core.a;

import android.graphics.Bitmap;
import android.widget.ImageView.ScaleType;
import com.sds.android.sdk.core.a.b.a;
import com.sds.android.sdk.lib.util.k.b;
import java.io.File;

/* ImageRequestInfo */
final class e {
    private a a;
    private int b;
    private int c;
    private String d;
    private String e;
    private String f;
    private Bitmap g;
    private ScaleType h;

    public e(String str, String str2, String str3, int i, int i2, ScaleType scaleType, a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }
        this.a = aVar;
        this.d = str;
        this.f = str2;
        this.e = str3;
        this.b = i;
        this.c = i2;
        this.h = scaleType;
    }

    public a a() {
        return this.a;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.f + File.separator + (this.e == null ? b.b(this.d) : this.e);
    }

    public int e() {
        return this.b;
    }

    public int f() {
        return this.c;
    }

    public Bitmap g() {
        return this.g;
    }

    public void a(Bitmap bitmap) {
        this.g = bitmap;
    }

    public ScaleType h() {
        return this.h;
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        if (this.d != null) {
            if (!this.d.equals(eVar.d)) {
                return false;
            }
        } else if (eVar.d != null) {
            return false;
        }
        if (this.e != null) {
            if (!this.e.equals(eVar.e)) {
                return false;
            }
        } else if (eVar.e != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = ((((this.a.hashCode() * 31) + this.b) * 31) + this.c) * 31;
        if (this.d != null) {
            hashCode = this.d.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.e != null) {
            i = this.e.hashCode();
        }
        return hashCode + i;
    }
}
