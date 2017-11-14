package com.sds.android.ttpod.framework.modules.core.b.a;

import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.core.b.a.a.a;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.player.PlayStatus;

/* ShakeMonitor */
public class b implements a {
    private a a;

    public void a() {
        this.a = new a(BaseApplication.e(), false, -2);
        this.a.a((a) this);
        int value = 600 - com.sds.android.ttpod.framework.storage.environment.b.y().value();
        this.a.a(-value, value);
    }

    public void b() {
        this.a.a();
    }

    public void a(c cVar) {
        int value = 600 - cVar.value();
        this.a.a(-value, value);
    }

    public void a(int i) {
        if (e.a(BaseApplication.e()).n() == PlayStatus.STATUS_PLAYING) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.NEXT, new Object[0]));
            new SSystemEvent("SYS_SHAKE", "shake").post();
        }
    }

    public void b(int i) {
    }

    public void c(int i) {
    }
}
