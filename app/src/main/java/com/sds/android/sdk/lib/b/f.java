package com.sds.android.sdk.lib.b;

/* Parser */
public abstract class f<R> {
    private Class<R> a;

    public abstract R b(String str);

    public f(Class<R> cls) {
        this.a = cls;
    }

    public R a() {
        R r = null;
        try {
            r = this.a.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        return r;
    }
}
