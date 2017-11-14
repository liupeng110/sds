package com.sds.android.ttpod.framework.modules.skin;

import android.content.pm.ApplicationInfo;
import com.sds.android.ttpod.framework.a.v;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.modules.skin.b.x;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* AllLocalSkinListLoader */
public class a implements Runnable {
    private static ArrayList<m> a = b();

    /* AllLocalSkinListLoader */
    private class a extends p {
        final /* synthetic */ a a;
        private String c;

        public a(a aVar, String str) {
            this.a = aVar;
            this.c = str;
        }

        public x a() {
            x xVar;
            if (a(a(1, this.c))) {
                com.sds.android.ttpod.framework.modules.search.a.a aVar = new com.sds.android.ttpod.framework.modules.search.a.a();
                Reader k = k();
                if (k != null) {
                    try {
                        aVar.setInput(k);
                        aVar.nextTag();
                        aVar.require(2, null, "Theme");
                        xVar = new x(aVar);
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                        xVar = null;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    j();
                    return xVar;
                }
            }
            xVar = null;
            j();
            return xVar;
        }
    }

    public static m a() {
        return (m) a.get(0);
    }

    public void run() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(c());
        arrayList.addAll(d());
        arrayList.addAll(e());
        b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_ALL_LOCAL_SKIN_LIST, arrayList), c.SKIN);
    }

    private static ArrayList<m> b() {
        ArrayList<m> arrayList = new ArrayList();
        try {
            String[] list = BaseApplication.e().getAssets().list("skin");
            if (list.length > 0) {
                for (String str : list) {
                    arrayList.add(new m("skin" + File.separator + str, 1));
                }
            }
        } catch (IOException e) {
            o.logE("SkinListLoader.readInternalSkinList read internal skin list error.");
            e.printStackTrace();
        }
        return arrayList;
    }

    private ArrayList<m> c() {
        ArrayList<m> arrayList = new ArrayList();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            m mVar = (m) it.next();
            m mVar2 = new m(mVar.b(), 1);
            mVar2.a(new a(this, mVar.b()).a());
            arrayList.add(mVar2);
        }
        return arrayList;
    }

    private ArrayList<m> d() {
        ArrayList<m> arrayList = new ArrayList();
        List list = null;
        try {
            list = BaseApplication.e().getPackageManager().getInstalledApplications(0);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (r0 != null && r0.size() > 0) {
            for (ApplicationInfo applicationInfo : r0) {
                if (applicationInfo != null && applicationInfo.packageName.startsWith("com.sds.android.ttpod.skin.")) {
                    arrayList.add(new m(applicationInfo.packageName, 2));
                }
            }
        }
        return arrayList;
    }

    private ArrayList<m> e() {
        ArrayList<m> arrayList = new ArrayList();
        File[] a = v.a();
        if (a != null && a.length > 0) {
            for (File absolutePath : a) {
                m mVar = new m(absolutePath.getAbsolutePath(), 0);
                mVar.a(new l(mVar.b()).a());
                arrayList.add(mVar);
            }
        }
        return arrayList;
    }
}
