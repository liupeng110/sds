package com.sds.android.ttpod.framework.modules.skin;

import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.framework.a;
import com.sds.android.ttpod.framework.modules.skin.b.x;
import java.io.File;
import java.io.Serializable;

/* SkinItem */
public class m implements Serializable {
    protected int a;
    protected String b;
    protected String c;
    protected String d;
    protected String e;
    protected String f;
    protected long g;
    protected OnlineSkinItem h;

    public m(OnlineSkinItem onlineSkinItem) {
        this.a = 4;
        this.b = onlineSkinItem.getName();
        this.c = a.o() + File.separator + onlineSkinItem.getName() + ".tsk";
        this.f = onlineSkinItem.getAuthor();
        this.e = onlineSkinItem.getVersionNumber();
        this.h = onlineSkinItem;
        this.g = onlineSkinItem.getDateCreated();
    }

    public m(m mVar) {
        this.a = 0;
        this.b = mVar.g();
        this.c = a.o() + File.separator + mVar.g() + ".tsk";
        this.f = mVar.c();
        this.e = mVar.d();
        this.g = mVar.e();
        this.d = mVar.h();
    }

    public m(String str, int i) {
        b(i);
        this.c = str;
        this.a = i;
    }

    public void a(x xVar) {
        if (xVar != null) {
            this.f = xVar.a();
            this.e = xVar.b();
            this.b = xVar.d();
            this.g = xVar.c();
        }
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
        b(i);
        this.a = i;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.e;
    }

    public long e() {
        return this.g;
    }

    public OnlineSkinItem f() {
        return this.h;
    }

    public String g() {
        if (this.b == null) {
            this.b = h();
        }
        return this.b;
    }

    public String h() {
        if (this.d == null) {
            if (2 == this.a) {
                int i = -1;
                if (this.c != null) {
                    i = this.c.lastIndexOf(46);
                }
                this.d = i < 0 ? this.c : this.c.substring(i + 1);
            } else {
                this.d = e.k(this.c);
            }
        }
        return this.d;
    }

    private void b(int i) {
        if (i < 0 || i > 4) {
            throw new IllegalArgumentException("the skin type is invalid");
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" name: " + this.b);
        stringBuilder.append(" fileName: " + this.d);
        stringBuilder.append(" path: " + this.c);
        stringBuilder.append(" type: " + this.a);
        return stringBuilder.toString();
    }

    public boolean a(m mVar) {
        if (this.b == null || this.e == null) {
            return false;
        }
        boolean z = this.b.equals(mVar.b) && this.e.equals(mVar.e) && this.a == mVar.a;
        if (!z) {
            return false;
        }
        OnlineSkinItem f = mVar.f();
        if (f == null) {
            return z;
        }
        if (this.h != null) {
            return f.getPictureUrl().equals(this.h.getPictureUrl());
        }
        return false;
    }

    public String i() {
        return (g() + c()).toLowerCase();
    }

    public int hashCode() {
        return this.d != null ? this.d.hashCode() : 0;
    }
}
