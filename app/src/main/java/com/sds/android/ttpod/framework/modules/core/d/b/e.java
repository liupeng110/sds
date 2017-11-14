package com.sds.android.ttpod.framework.modules.core.d.b;

import com.sds.android.sdk.lib.util.h;
import com.sds.android.sdk.lib.util.p;
import com.sds.android.ttpod.framework.a;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.modules.c;
import java.io.File;
import java.io.IOException;

/* WifiTransmission */
public final class e implements a {
    private static e a;
    private b b;

    public void b() {
        if (this.b == null) {
            String a = h.a(BaseApplication.e());
            if (a != null) {
                String d = d();
                if (d != null) {
                    try {
                        this.b = new b(a, 8888, d, a.m(), this);
                        this.b.a();
                        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_WIFI_TRANSMISSION_STATE, new d(com.sds.android.ttpod.framework.base.e.ErrNone, "ok", "http://" + a + ":" + 8888)), c.MEDIA_SCAN);
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_WIFI_TRANSMISSION_STATE, new d(com.sds.android.ttpod.framework.base.e.ErrPathNotFound, e.getMessage())), c.MEDIA_SCAN);
                        return;
                    }
                }
                return;
            }
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_WIFI_TRANSMISSION_STATE, new d(com.sds.android.ttpod.framework.base.e.ErrNotReady, "getLocalWiFiIPAddress Error")), c.MEDIA_SCAN);
        }
    }

    public void c() {
        if (this.b != null) {
            this.b.b();
            this.b = null;
        }
        a = null;
    }

    private String d() {
        File file = new File(BaseApplication.e().getFilesDir() + "/www_700/");
        if (!file.exists()) {
            try {
                file.mkdirs();
                p.a(BaseApplication.e().getAssets().open("www.zip"), file.getPath());
            } catch (Exception e) {
                e.printStackTrace();
                file.delete();
                return null;
            }
        }
        return file.getPath();
    }

    public void a() {
        b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_WIFI_TRANSMISSION_STATE, new d(com.sds.android.ttpod.framework.base.e.ErrCompletion, "transmission completion")), c.MEDIA_SCAN);
    }
}
