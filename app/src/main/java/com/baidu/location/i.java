package com.baidu.location;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.support.v4.widget.AutoScrollHelper;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.util.ArrayList;
import java.util.Iterator;

public class i {
    public static final String new = "android.com.baidu.location.TIMER.NOTIFY";
    private int a = 0;
    private long b = 0;
    private ArrayList byte = null;
    private boolean c = false;
    private BDLocation case = null;
    private long char = 0;
    private LocationClient d = null;
    private String do = f.v;
    private b else = null;
    private AlarmManager for = null;
    private float goto = AutoScrollHelper.NO_MAX;
    private Context if = null;
    private a int = new a(this);
    private boolean long = false;
    private PendingIntent try = null;
    private boolean void = false;

    public class a implements BDLocationListener {
        final /* synthetic */ i a;

        public a(i iVar) {
            this.a = iVar;
        }

        public void onReceiveLocation(BDLocation bDLocation) {
            this.a.a(bDLocation);
        }

        public void onReceivePoi(BDLocation bDLocation) {
        }
    }

    public class b extends BroadcastReceiver {
        final /* synthetic */ i a;

        public b(i iVar) {
            this.a = iVar;
        }

        public void onReceive(Context context, Intent intent) {
            j.if(this.a.do, "timer expire,request location...");
            if (this.a.byte != null && !this.a.byte.isEmpty()) {
                this.a.d.requestNotifyLocation();
            }
        }
    }

    public i(Context context, LocationClient locationClient) {
        this.if = context;
        this.d = locationClient;
        this.d.registerNotifyLocationListener(this.int);
        this.for = (AlarmManager) this.if.getSystemService("alarm");
        this.else = new b(this);
        this.c = false;
    }

    private void a() {
        int i = 10000;
        if (do()) {
            boolean z;
            int i2 = this.goto > 5000.0f ? 600000 : this.goto > 1000.0f ? 120000 : this.goto > 500.0f ? 60000 : 10000;
            if (this.long) {
                this.long = false;
            } else {
                i = i2;
            }
            if (this.a != 0) {
                if (((long) i) > (this.char + ((long) this.a)) - System.currentTimeMillis()) {
                    z = false;
                    if (z) {
                        this.a = i;
                        this.char = System.currentTimeMillis();
                        a((long) this.a);
                    }
                }
            }
            z = true;
            if (z) {
                this.a = i;
                this.char = System.currentTimeMillis();
                a((long) this.a);
            }
        }
    }

    private void a(long j) {
        if (this.void) {
            this.for.cancel(this.try);
        }
        this.try = PendingIntent.getBroadcast(this.if, 0, new Intent(new), 134217728);
        this.for.set(0, System.currentTimeMillis() + j, this.try);
        j.if(this.do, "timer start:" + j);
    }

    private void a(BDLocation bDLocation) {
        j.if(this.do, "notify new loation");
        this.void = false;
        if (bDLocation.getLocType() != 61 && bDLocation.getLocType() != BDLocation.TypeNetWorkLocation && bDLocation.getLocType() != 65) {
            a(120000);
        } else if (System.currentTimeMillis() - this.b >= 5000 && this.byte != null) {
            this.case = bDLocation;
            this.b = System.currentTimeMillis();
            float[] fArr = new float[1];
            Iterator it = this.byte.iterator();
            float f = AutoScrollHelper.NO_MAX;
            while (it.hasNext()) {
                BDNotifyListener bDNotifyListener = (BDNotifyListener) it.next();
                Location.distanceBetween(bDLocation.getLatitude(), bDLocation.getLongitude(), bDNotifyListener.mLatitudeC, bDNotifyListener.mLongitudeC, fArr);
                float radius = (fArr[0] - bDNotifyListener.mRadius) - bDLocation.getRadius();
                j.if(this.do, "distance:" + radius);
                if (radius > 0.0f) {
                    if (radius < f) {
                    }
                    radius = f;
                } else {
                    if (bDNotifyListener.Notified < 3) {
                        bDNotifyListener.Notified++;
                        bDNotifyListener.onNotify(bDLocation, fArr[0]);
                        if (bDNotifyListener.Notified < 3) {
                            this.long = true;
                        }
                    }
                    radius = f;
                }
                f = radius;
            }
            if (f < this.goto) {
                this.goto = f;
            }
            this.a = 0;
            a();
        }
    }

    private boolean do() {
        if (this.byte == null || this.byte.isEmpty()) {
            return false;
        }
        Iterator it = this.byte.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z = ((BDNotifyListener) it.next()).Notified < 3 ? true : z;
        }
        return z;
    }

    public void a(BDNotifyListener bDNotifyListener) {
        j.if(this.do, bDNotifyListener.mCoorType + "2gcj");
        if (bDNotifyListener.mCoorType != null) {
            if (!bDNotifyListener.mCoorType.equals("gcj02")) {
                double[] dArr = Jni.if(bDNotifyListener.mLongitude, bDNotifyListener.mLatitude, bDNotifyListener.mCoorType + "2gcj");
                bDNotifyListener.mLongitudeC = dArr[0];
                bDNotifyListener.mLatitudeC = dArr[1];
                j.if(this.do, bDNotifyListener.mCoorType + "2gcj");
                j.if(this.do, "coor:" + bDNotifyListener.mLongitude + SelectCountryActivity.SPLITTER + bDNotifyListener.mLatitude + ":" + bDNotifyListener.mLongitudeC + SelectCountryActivity.SPLITTER + bDNotifyListener.mLatitudeC);
            }
            if (this.case == null || System.currentTimeMillis() - this.b > 300000) {
                this.d.requestNotifyLocation();
            } else {
                float[] fArr = new float[1];
                Location.distanceBetween(this.case.getLatitude(), this.case.getLongitude(), bDNotifyListener.mLatitudeC, bDNotifyListener.mLongitudeC, fArr);
                float radius = (fArr[0] - bDNotifyListener.mRadius) - this.case.getRadius();
                if (radius > 0.0f) {
                    if (radius < this.goto) {
                        this.goto = radius;
                    }
                } else if (bDNotifyListener.Notified < 3) {
                    bDNotifyListener.Notified++;
                    bDNotifyListener.onNotify(this.case, fArr[0]);
                    if (bDNotifyListener.Notified < 3) {
                        this.long = true;
                    }
                }
            }
            a();
        }
    }

    public int do(BDNotifyListener bDNotifyListener) {
        if (this.byte == null) {
            return 0;
        }
        if (this.byte.contains(bDNotifyListener)) {
            this.byte.remove(bDNotifyListener);
        }
        if (this.byte.size() == 0 && this.void) {
            this.for.cancel(this.try);
        }
        return 1;
    }

    public int if(BDNotifyListener bDNotifyListener) {
        if (this.byte == null) {
            this.byte = new ArrayList();
        }
        this.byte.add(bDNotifyListener);
        bDNotifyListener.isAdded = true;
        bDNotifyListener.mNotifyCache = this;
        if (!this.c) {
            this.if.registerReceiver(this.else, new IntentFilter(new));
            this.c = true;
        }
        if (bDNotifyListener.mCoorType != null) {
            if (!bDNotifyListener.mCoorType.equals("gcj02")) {
                double[] dArr = Jni.if(bDNotifyListener.mLongitude, bDNotifyListener.mLatitude, bDNotifyListener.mCoorType + "2gcj");
                bDNotifyListener.mLongitudeC = dArr[0];
                bDNotifyListener.mLatitudeC = dArr[1];
                j.if(this.do, bDNotifyListener.mCoorType + "2gcj");
                j.if(this.do, "coor:" + bDNotifyListener.mLongitude + SelectCountryActivity.SPLITTER + bDNotifyListener.mLatitude + ":" + bDNotifyListener.mLongitudeC + SelectCountryActivity.SPLITTER + bDNotifyListener.mLatitudeC);
            }
            if (this.case == null || System.currentTimeMillis() - this.b > 30000) {
                this.d.requestNotifyLocation();
            } else {
                float[] fArr = new float[1];
                Location.distanceBetween(this.case.getLatitude(), this.case.getLongitude(), bDNotifyListener.mLatitudeC, bDNotifyListener.mLongitudeC, fArr);
                float radius = (fArr[0] - bDNotifyListener.mRadius) - this.case.getRadius();
                if (radius > 0.0f) {
                    if (radius < this.goto) {
                        this.goto = radius;
                    }
                } else if (bDNotifyListener.Notified < 3) {
                    bDNotifyListener.Notified++;
                    bDNotifyListener.onNotify(this.case, fArr[0]);
                    if (bDNotifyListener.Notified < 3) {
                        this.long = true;
                    }
                }
            }
            a();
        }
        return 1;
    }

    public void if() {
        if (this.void) {
            this.for.cancel(this.try);
        }
        this.case = null;
        this.b = 0;
        if (this.c) {
            j.if(this.do, "unregister...");
            this.if.unregisterReceiver(this.else);
        }
        this.c = false;
    }
}
