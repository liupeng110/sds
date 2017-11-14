package com.alibaba.wireless.security.open;

import android.content.Context;
import android.content.ContextWrapper;
import com.taobao.wireless.security.adapter.dynamicupdatelib.DynamicUpdateLibAdapter;
import java.util.ArrayList;
import java.util.List;

public final class a {
    private static a a;
    private Context b;
    private List c;

    private a(Context context) {
        this.b = context;
        c();
    }

    public static a a(Context context) {
        if (a == null) {
            a = new a(context);
        }
        return a;
    }

    private void c() {
        this.c = new ArrayList(13);
        for (int i = 0; i < 13; i++) {
            this.c.add(null);
        }
        DynamicUpdateLibAdapter.setApplicationContext(this.b.getApplicationContext());
        List list = this.c;
        Context context = this.b;
        list.set(1, new com.alibaba.wireless.security.open.securesignature.a());
        this.c.set(2, new com.alibaba.wireless.security.open.dynamicdatastore.a((ContextWrapper) this.b));
        this.c.set(3, new com.alibaba.wireless.security.open.staticdatastore.a());
        this.c.set(5, new com.alibaba.wireless.security.open.datacollection.a((ContextWrapper) this.b));
        this.c.set(6, new com.alibaba.wireless.security.open.staticdataencrypt.a());
        this.c.set(7, new com.alibaba.wireless.security.open.dynamicdataencrypt.a((ContextWrapper) this.b));
        this.c.set(9, new com.alibaba.wireless.security.open.statickeyencrypt.a((ContextWrapper) this.b));
        list = this.c;
        context = this.b;
        list.set(10, new com.alibaba.wireless.security.open.opensdk.a());
        this.c.set(11, new com.alibaba.wireless.security.open.umid.a((ContextWrapper) this.b));
        this.c.set(12, new com.alibaba.wireless.security.open.pkgvaliditycheck.a((ContextWrapper) this.b));
    }

    public final IComponent a(int i) {
        return (IComponent) this.c.get(i);
    }

    public final com.alibaba.wireless.security.open.umid.a a() {
        return (com.alibaba.wireless.security.open.umid.a) this.c.get(11);
    }

    public final com.alibaba.wireless.security.open.staticdatastore.a b() {
        return (com.alibaba.wireless.security.open.staticdatastore.a) this.c.get(3);
    }
}
