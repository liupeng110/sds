package com.sds.android.ttpod.fragment.skinmanager.base;

import com.sds.android.ttpod.framework.modules.skin.m;
import java.util.ArrayList;
import java.util.Iterator;

/* ThemeListObserver */
public class c {
    private static c a;
    private static ArrayList<b> b = new ArrayList();

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    public void a(m mVar) {
        Iterator it = b.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar != null) {
                bVar.onSkinDownloaded(mVar);
            }
        }
    }

    public void b(m mVar) {
        Iterator it = b.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar != null) {
                bVar.onSkinDownloadError(mVar);
            }
        }
    }

    public void c(m mVar) {
        Iterator it = b.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar != null) {
                bVar.onSkinDownloading(mVar);
            }
        }
    }

    public void d(m mVar) {
        Iterator it = b.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar != null) {
                bVar.onSkinDeleted(mVar);
            }
        }
    }

    public void a(String str) {
        Iterator it = b.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar != null) {
                bVar.onCurrentSkinChanged(str);
            }
        }
    }

    public void b() {
        Iterator it = b.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar != null) {
                bVar.onSkinInfoLoaded();
            }
        }
    }

    public void a(String str, int i) {
        Iterator it = b.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar != null) {
                bVar.onSkinItemStateChange(str, i);
            }
        }
    }

    public void a(b bVar) {
        if (!b.contains(bVar)) {
            b.add(bVar);
        }
    }

    public void b(b bVar) {
        b.remove(bVar);
    }
}
