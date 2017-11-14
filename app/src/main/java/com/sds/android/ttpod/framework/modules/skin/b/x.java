package com.sds.android.ttpod.framework.modules.skin.b;

import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import java.io.Serializable;

/* SSkinInfo */
public class x implements Serializable {
    protected String a = null;
    protected String b = null;
    protected String c = null;
    protected String d = null;
    protected String e = null;
    protected String f;
    protected String g;
    protected int h = 0;
    protected long i = 0;

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public long c() {
        return this.i;
    }

    public String d() {
        return this.a;
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.e;
    }

    public x(a aVar) {
        this.a = aVar.getAttributeValue(null, "Name");
        this.b = aVar.getAttributeValue(null, "Author");
        this.c = aVar.getAttributeValue(null, "Ver");
        this.d = aVar.getAttributeValue(null, "EMail");
        this.e = aVar.getAttributeValue(null, "WebPage");
        this.f = aVar.getAttributeValue(null, "Background");
        this.g = aVar.getAttributeValue(null, "Preview");
        this.h = m.a(aVar.getAttributeValue(null, "LoaderVer"), 0);
    }

    public x(x xVar) {
        this.a = xVar.a;
        this.b = xVar.b;
        this.c = xVar.c;
        this.d = xVar.d;
        this.e = xVar.e;
        this.h = xVar.h;
        this.f = xVar.f;
        this.i = xVar.i;
    }
}
