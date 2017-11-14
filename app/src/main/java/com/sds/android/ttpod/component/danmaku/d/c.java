package com.sds.android.ttpod.component.danmaku.d;

import com.sds.android.ttpod.component.danmaku.c.a.a;
import com.sds.android.ttpod.component.danmaku.c.a.b;
import java.io.File;

/* TTPodLoader */
public class c implements a {
    private static volatile c a;
    private e b;

    public /* synthetic */ com.sds.android.ttpod.component.danmaku.c.c.c a() {
        return c();
    }

    private c() {
    }

    public static a b() {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c();
                }
            }
        }
        return a;
    }

    public e c() {
        return this.b;
    }

    public void a(File file) throws b {
        try {
            this.b = new e(file);
        } catch (Throwable e) {
            throw new b(e);
        }
    }

    public void a(String str) throws b {
        try {
            this.b = new e(str);
        } catch (Throwable e) {
            throw new b(e);
        }
    }
}
