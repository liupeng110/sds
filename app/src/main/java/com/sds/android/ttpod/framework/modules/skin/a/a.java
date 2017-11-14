package com.sds.android.ttpod.framework.modules.skin.a;

import com.b.a.a.c;
import java.io.Serializable;

/* CategoryItem */
public class a implements Serializable, Comparable<a> {
    @c(a = "id")
    private int a;
    @c(a = "name")
    private String b;
    @c(a = "orderNum")
    private int c;
    @c(a = "recommendPicUrl")
    private String d;
    private String e;

    public /* synthetic */ int compareTo(Object obj) {
        return a((a) obj);
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public int a(a aVar) {
        return c() - aVar.c();
    }
}
