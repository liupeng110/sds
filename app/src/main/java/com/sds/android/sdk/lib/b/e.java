package com.sds.android.sdk.lib.b;

import com.sds.android.sdk.lib.util.f;

/* KVParser */
public class e<R extends b> extends f<R> {
    private Class<R> a;

    public /* synthetic */ Object b(String str) {
        return a(str);
    }

    public e(Class<R> cls) {
        super(cls);
        this.a = cls;
    }

    public R a(String str) {
        return (b) f.a(str, this.a);
    }
}
