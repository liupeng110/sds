package com.sds.android.ttpod.activities.musiccircle.shake;

import android.content.Context;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.sds.android.sdk.lib.util.g;

/* BDLBSManager */
public final class a {
    private LocationClient a;
    private BDLocationListener b = new a(this);
    private BDLocation c;

    /* BDLBSManager */
    class a implements BDLocationListener {
        final /* synthetic */ a a;

        a(a aVar) {
            this.a = aVar;
        }

        public void onReceiveLocation(BDLocation bDLocation) {
            if (bDLocation == null) {
                g.c("BDLBSManager", "onReceiveLocation error...");
                return;
            }
            this.a.c = bDLocation;
            g.d("BDLBSManager", "onReceiveLocation=>longitude:" + bDLocation.getLongitude() + ":latitude:" + bDLocation.getLatitude());
        }

        public void onReceivePoi(BDLocation bDLocation) {
            this.a.c = bDLocation;
        }
    }

    public a(Context context) {
        this.a = new LocationClient(context.getApplicationContext());
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setOpenGps(true);
        locationClientOption.setCoorType("gcj02");
        locationClientOption.setScanSpan(2000);
        this.a.setLocOption(locationClientOption);
    }

    public BDLocation a() {
        return this.c;
    }

    public void b() {
        this.a.registerLocationListener(this.b);
        if (!this.a.isStarted()) {
            this.a.start();
        }
    }

    public int c() {
        return this.a.requestLocation();
    }

    public void d() {
        this.a.stop();
    }

    public void e() {
        this.a.unRegisterLocationListener(this.b);
    }
}
