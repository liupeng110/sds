package com.sds.android.ttpod.framework.modules.skin;

import android.text.TextUtils;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.ttpod.framework.a;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.modules.skin.a.b;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* CategoryListLoader */
public class d implements Runnable {
    public static final String a = (a.o() + File.separator + "category_skin.json");
    public static final String b = (a.n() + File.separator + "category_bkg.json");
    static final /* synthetic */ boolean c = (!d.class.desiredAssertionStatus());
    private static final String d = ("category_list" + File.separator);
    private String[] e = new String[]{a, b};
    private String[] f = new String[]{d + "category_skin.json", d + "category_bkg.json"};
    private com.sds.android.ttpod.framework.modules.a[] g = new com.sds.android.ttpod.framework.modules.a[]{com.sds.android.ttpod.framework.modules.a.ON_SKIN_CATEGORY_LIST_PARSED, com.sds.android.ttpod.framework.modules.a.ON_BACKGROUND_CATEGORY_LIST_PARSED};
    private String h;
    private String i;
    private com.sds.android.ttpod.framework.modules.a j;

    public d(int i) {
        if (c || i < this.e.length) {
            this.h = this.e[i];
            this.i = this.f[i];
            this.j = this.g[i];
            return;
        }
        throw new AssertionError();
    }

    public void run() {
        a();
    }

    private void a() {
        b bVar;
        long b;
        List arrayList = new ArrayList();
        String a = c.a(this.h, this.i);
        if (TextUtils.isEmpty(a)) {
            bVar = null;
        } else {
            bVar = (b) f.a(a, b.class);
        }
        if (bVar != null) {
            String substring;
            ArrayList a2 = bVar.a();
            a = bVar.c();
            if (a.endsWith("/")) {
                substring = a.substring(0, a.length() - 1);
            } else {
                substring = a;
            }
            Iterator it = a2.iterator();
            while (it.hasNext()) {
                com.sds.android.ttpod.framework.modules.skin.a.a aVar = (com.sds.android.ttpod.framework.modules.skin.a.a) it.next();
                aVar.a(a(substring, aVar.d()));
                arrayList.add(aVar);
            }
            Collections.sort(arrayList);
            b = bVar.b();
        } else {
            b = 0;
        }
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(this.j, arrayList, Long.valueOf(b)), c.SKIN);
    }

    private String a(String str, String str2) {
        if (str2.startsWith("/")) {
            str2 = str2.substring(1, str2.length());
        }
        return str + "/" + str2;
    }
}
