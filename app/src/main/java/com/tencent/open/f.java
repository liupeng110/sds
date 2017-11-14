package com.tencent.open;

import android.location.Location;
import com.tencent.a.a.d;
import com.tencent.map.a.a.b;
import com.tencent.open.d.a;

/* ProGuard */
public class f extends b {
    private a a;

    public f(a aVar) {
        super(1, 0, 0, 8);
        this.a = aVar;
    }

    public void a(byte[] bArr, int i) {
        super.a(bArr, i);
        d.c("openSDK_LOG", "location: onLocationDataUpdate = " + bArr);
    }

    public void a(com.tencent.map.a.a.d dVar) {
        d.c("openSDK_LOG", "location: onLocationUpdate = " + dVar);
        super.a(dVar);
        if (dVar != null) {
            Location location = new Location("passive");
            location.setLatitude(dVar.b);
            location.setLongitude(dVar.c);
            if (this.a != null) {
                this.a.onLocationUpdate(location);
            }
        }
    }

    public void a(int i) {
        d.c("openSDK_LOG", "location: onStatusUpdate = " + i);
        super.a(i);
    }
}
