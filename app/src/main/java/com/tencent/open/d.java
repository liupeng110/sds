package com.tencent.open;

import android.content.Context;
import android.location.Location;

/* ProGuard */
public class d {
    private f a;

    /* ProGuard */
    public interface a {
        void onLocationUpdate(Location location);
    }

    public boolean a() {
        return com.tencent.map.a.a.a.a().a("OpenSdk", "WQMPF-XMH66-ISQXP-OIGMM-BNL7M");
    }

    public void a(Context context, a aVar) {
        this.a = new f(aVar);
        com.tencent.map.a.a.a.a().a(context, this.a);
    }

    public void b() {
        com.tencent.map.a.a.a.a().b();
        this.a = null;
    }
}
