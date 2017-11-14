package com.taobao.wireless.security.sdk;

import android.content.Context;
import android.content.ContextWrapper;
import com.taobao.wireless.security.adapter.dynamicupdatelib.DynamicUpdateLibAdapter;
import com.taobao.wireless.security.sdk.securitydetect.SecurityDetect;
import java.util.ArrayList;
import java.util.List;

public final class a {
    private static a a;
    private Context b;
    private List c;

    private a(Context context) {
        this.b = context;
        a();
    }

    public static a a(Context context) {
        if (a == null) {
            a = new a(context);
        }
        return a;
    }

    private void a() {
        this.c = new ArrayList(14);
        for (int i = 0; i < 14; i++) {
            this.c.add(null);
        }
        DynamicUpdateLibAdapter.setApplicationContext(this.b.getApplicationContext());
        List list = this.c;
        Context context = this.b;
        list.set(1, new com.taobao.wireless.security.sdk.securesignature.a());
        this.c.set(2, new com.taobao.wireless.security.sdk.dynamicdatastore.a((ContextWrapper) this.b));
        this.c.set(4, new com.taobao.wireless.security.sdk.staticdatastore.a((ContextWrapper) this.b));
        this.c.set(3, new com.taobao.wireless.security.sdk.indiekit.a((ContextWrapper) this.b));
        list = this.c;
        context = this.b;
        list.set(5, new com.taobao.wireless.security.sdk.rootdetect.a());
        this.c.set(6, new com.taobao.wireless.security.sdk.datacollection.a((ContextWrapper) this.b));
        this.c.set(7, new com.taobao.wireless.security.sdk.staticdataencrypt.a((ContextWrapper) this.b));
        this.c.set(8, new com.taobao.wireless.security.sdk.securitybody.a((ContextWrapper) this.b));
        this.c.set(9, new com.taobao.wireless.security.sdk.dynamicdataencrypt.a((ContextWrapper) this.b));
        this.c.set(10, new com.taobao.wireless.security.sdk.simulatordetect.a());
        this.c.set(11, new com.taobao.wireless.security.sdk.securityDNS.a((ContextWrapper) this.b));
        this.c.set(12, new com.taobao.wireless.security.sdk.pkgvaliditycheck.a((ContextWrapper) this.b));
        this.c.set(13, new SecurityDetect());
    }

    public final IComponent a(int i) {
        return (IComponent) this.c.get(i);
    }
}
