package com.sds.android.ttpod.framework.modules.skin.b;

import android.text.TextUtils;
import com.sds.android.ttpod.framework.modules.search.a.a;

/* SBitmap */
public class h extends f {
    protected String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private boolean i = false;

    public /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    h() {
    }

    h(a aVar) {
        super(aVar);
        this.c = aVar.getAttributeValue(null, "File");
        this.d = aVar.getAttributeValue(null, "Empty");
        this.e = aVar.getAttributeValue(null, "Pressed");
        this.f = aVar.getAttributeValue(null, "Enabled");
        this.g = aVar.getAttributeValue(null, "Checked");
        this.h = aVar.getAttributeValue(null, "Focused");
        b();
    }

    h(String str) {
        this.c = str;
    }

    public void b() {
        boolean z = (TextUtils.isEmpty(this.d) && TextUtils.isEmpty(this.e) && TextUtils.isEmpty(this.f) && TextUtils.isEmpty(this.g) && TextUtils.isEmpty(this.h)) ? false : true;
        this.i = z;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.d = str;
    }

    public String d() {
        return this.d;
    }

    public void b(String str) {
        this.e = str;
    }

    public String e() {
        return this.e;
    }

    public void c(String str) {
        this.f = str;
    }

    public String f() {
        return this.f;
    }

    public void d(String str) {
        this.h = str;
    }

    public String g() {
        return this.h;
    }

    public void e(String str) {
        this.g = str;
    }

    public String h() {
        return this.g;
    }

    public boolean i() {
        return this.i;
    }
}
