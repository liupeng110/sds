package com.sds.android.ttpod.framework.modules.skin;

import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.c;

/* SkinCacheCreator */
public class k extends p implements Runnable {
    private String a;
    private String c;
    private a d;

    public k(String str, String str2, a aVar) {
        this.a = str;
        this.c = str2;
        this.d = aVar;
    }

    public void run() {
        j a = a(this.a);
        if (a != null) {
            b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.SET_SKIN_PROTOCOL_PATH, this.a));
            com.sds.android.ttpod.framework.storage.a.a.a().a(a);
            b.a().b(new com.sds.android.ttpod.framework.base.a.a(this.d, a), c.SKIN);
        } else if (this.c != null) {
            b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.LOAD_SKIN_ERROR, new Object[0]), c.SKIN);
            b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.SET_SKIN_PROTOCOL_PATH, this.c));
            a = a(this.c);
            if (a != null) {
                com.sds.android.ttpod.framework.storage.a.a.a().a(a);
                b.a().b(new com.sds.android.ttpod.framework.base.a.a(this.d, a), c.SKIN);
            }
        }
    }

    private j a(String str) {
        j jVar = new j(str);
        jVar.h();
        if (jVar.b() != null) {
            jVar.j();
            return jVar;
        }
        jVar.i();
        return null;
    }
}
