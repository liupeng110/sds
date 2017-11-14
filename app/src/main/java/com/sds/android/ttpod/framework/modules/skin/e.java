package com.sds.android.ttpod.framework.modules.skin;

import com.sds.android.ttpod.framework.a.v;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.c;
import java.io.File;
import java.util.ArrayList;

/* DownloadedSkinListCreator */
public class e implements Runnable {
    public void run() {
        a();
    }

    private void a() {
        ArrayList arrayList = null;
        File[] a = v.a();
        if (a != null && a.length > 0) {
            arrayList = new ArrayList();
            for (File absolutePath : a) {
                m mVar = new m(absolutePath.getAbsolutePath(), 0);
                mVar.a(new l(mVar.b()).a());
                arrayList.add(mVar);
            }
        }
        b.a().b(new a(com.sds.android.ttpod.framework.modules.a.UPDATE_DOWNLOADED_SKIN_LIST, arrayList), c.SKIN);
    }
}
