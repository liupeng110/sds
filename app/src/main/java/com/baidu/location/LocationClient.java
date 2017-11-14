package com.baidu.location;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public final class LocationClient {
    private static final int B = 4;
    private static final int b = 8;
    private static final int f = 9;
    private static final String for = "baidu_location_Client";
    private static final int goto = 1000;
    private static final int h = 7;
    private static final int if = 10;
    private static final int m = 5;
    private static final int n = 12;
    private static final int o = 6;
    private static final int p = 2;
    private static final int s = 6000;
    private static final int try = 1;
    private static final int void = 3;
    private static final int y = 11;
    private String A = null;
    private ArrayList C = null;
    private boolean a = false;
    private b byte = null;
    private BDLocationListener c = null;
    private Boolean case = Boolean.valueOf(false);
    private long char = 0;
    private boolean d = false;
    private a do = new a();
    private String e = "3.3";
    private i else = null;
    private boolean g = false;
    private final Messenger i = new Messenger(this.do);
    private boolean int = false;
    private Context j = null;
    private Messenger k = null;
    private LocationClientOption l = new LocationClientOption();
    private Boolean long = Boolean.valueOf(false);
    private final Object new = new Object();
    private boolean q = false;
    private long r = 0;
    private ServiceConnection t = new ServiceConnection(this) {
        final /* synthetic */ LocationClient a;

        {
            this.a = r1;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.a.k = new Messenger(iBinder);
            if (this.a.k == null) {
                j.if(LocationClient.for, "server not connected");
                return;
            }
            this.a.q = true;
            Log.d("baidu_location_client", "baidu location connected ...");
            try {
                Message obtain = Message.obtain(null, 11);
                obtain.replyTo = this.a.i;
                obtain.setData(this.a.if());
                this.a.k.send(obtain);
                this.a.q = true;
                this.a.int = true;
                j.if(LocationClient.for, "bindService ...");
                if (this.a.l != null) {
                    this.a.do.obtainMessage(4).sendToTarget();
                }
            } catch (Exception e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.a.k = null;
            this.a.q = false;
            j.if(LocationClient.for, "unbindservice...");
        }
    };
    private String u = "http://loc.map.baidu.com/sdk.php";
    private boolean v = false;
    private boolean w = false;
    private BDLocation x = null;
    private String z = null;

    private class a extends Handler {
        final /* synthetic */ LocationClient a;

        private a(LocationClient locationClient) {
            this.a = locationClient;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.for();
                    return;
                case 2:
                    this.a.int();
                    return;
                case 3:
                    this.a.do(message);
                    return;
                case 4:
                    this.a.byte();
                    return;
                case 5:
                    this.a.new(message);
                    return;
                case 6:
                    this.a.if(message);
                    return;
                case 7:
                    this.a.do();
                    return;
                case 8:
                    this.a.try(message);
                    return;
                case 9:
                    this.a.a(message);
                    return;
                case 10:
                    this.a.int(message);
                    return;
                case 11:
                    this.a.new();
                    return;
                case 12:
                    this.a.try();
                    return;
                case 21:
                    this.a.a(message, 21);
                    return;
                case 26:
                    this.a.a(message, 26);
                    return;
                case 27:
                    this.a.for(message);
                    return;
                case 54:
                    if (this.a.l.void) {
                        this.a.a = true;
                        return;
                    }
                    return;
                case 55:
                    if (this.a.l.void) {
                        this.a.a = false;
                        return;
                    }
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    }

    private class b implements Runnable {
        final /* synthetic */ LocationClient a;

        private b(LocationClient locationClient) {
            this.a = locationClient;
        }

        /* JADX err: Inconsistent code. */
        public void run() {
            /*
            r3 = this;
            r0 = r3.a;
            r1 = r0.new;
            monitor-enter(r1);
            r0 = r3.a;	 Catch:{ all -> 0x0036 }
            r2 = 0;
            r0.g = r2;	 Catch:{ all -> 0x0036 }
            r0 = r3.a;	 Catch:{ all -> 0x0036 }
            r0 = r0.k;	 Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x001d;
        L_0x0015:
            r0 = r3.a;	 Catch:{ all -> 0x0036 }
            r0 = r0.i;	 Catch:{ all -> 0x0036 }
            if (r0 != 0) goto L_0x001f;
        L_0x001d:
            monitor-exit(r1);	 Catch:{ all -> 0x0036 }
        L_0x001e:
            return;
        L_0x001f:
            r0 = r3.a;	 Catch:{ all -> 0x0036 }
            r0 = r0.C;	 Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x0034;
        L_0x0027:
            r0 = r3.a;	 Catch:{ all -> 0x0036 }
            r0 = r0.C;	 Catch:{ all -> 0x0036 }
            r0 = r0.size();	 Catch:{ all -> 0x0036 }
            r2 = 1;
            if (r0 >= r2) goto L_0x0039;
        L_0x0034:
            monitor-exit(r1);	 Catch:{ all -> 0x0036 }
            goto L_0x001e;
        L_0x0036:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0036 }
            throw r0;
        L_0x0039:
            r0 = "baidu_location_Client";
            r2 = "request location ...";
            com.baidu.location.j.if(r0, r2);	 Catch:{ all -> 0x0036 }
            r0 = r3.a;	 Catch:{ all -> 0x0036 }
            r0 = r0.do;	 Catch:{ all -> 0x0036 }
            r2 = 4;
            r0 = r0.obtainMessage(r2);	 Catch:{ all -> 0x0036 }
            r0.sendToTarget();	 Catch:{ all -> 0x0036 }
            monitor-exit(r1);	 Catch:{ all -> 0x0036 }
            goto L_0x001e;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.LocationClient.b.run():void");
        }
    }

    public LocationClient(Context context) {
        this.j = context;
        this.l = new LocationClientOption();
        this.else = new i(this.j, this);
    }

    public LocationClient(Context context, LocationClientOption locationClientOption) {
        this.j = context;
        this.l = locationClientOption;
        this.else = new i(this.j, this);
    }

    private Bundle a() {
        if (this.l == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("num", this.l.a);
        bundle.putFloat("distance", this.l.do);
        bundle.putBoolean("extraInfo", this.l.if);
        return bundle;
    }

    private void a(int i) {
        Iterator it;
        if (i == 26 && this.v) {
            it = this.C.iterator();
            while (it.hasNext()) {
                ((BDLocationListener) it.next()).onReceivePoi(this.x);
            }
            this.v = false;
        }
        if (!this.d && ((!this.l.void || this.x.getLocType() != 61) && this.x.getLocType() != 66 && this.x.getLocType() != 67)) {
            return;
        }
        if (this.l == null || !this.l.isDisableCache() || this.x.getLocType() != 65) {
            it = this.C.iterator();
            while (it.hasNext()) {
                ((BDLocationListener) it.next()).onReceiveLocation(this.x);
            }
            if (this.int) {
            }
            if (this.x.getLocType() != 66 && this.x.getLocType() != 67) {
                this.d = false;
            }
        }
    }

    private void a(Message message) {
        if (message != null && message.obj != null) {
            this.else.if((BDNotifyListener) message.obj);
        }
    }

    private void a(Message message, int i) {
        String string = message.getData().getString("locStr");
        j.if(for, "on receive new location : " + string);
        j.a(for, "on receive new location : " + string);
        this.x = new BDLocation(string);
        a(i);
    }

    private void byte() {
        if (this.k == null) {
            j.if(for, "server not connected");
            return;
        }
        if (!(this.a && this.l.void)) {
            Message obtain = Message.obtain(null, 22);
            try {
                obtain.replyTo = this.i;
                this.k.send(obtain);
                this.r = System.currentTimeMillis();
                this.d = true;
                j.if(for, "send request to server...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        synchronized (this.new) {
            if (!(this.l == null || this.l.int < 1000 || this.g)) {
                if (this.byte == null) {
                    this.byte = new b();
                }
                this.do.postDelayed(this.byte, (long) this.l.int);
                this.g = true;
            }
        }
    }

    private void do() {
        if (this.k != null) {
            Message obtain = Message.obtain(null, 25);
            try {
                obtain.replyTo = this.i;
                obtain.setData(a());
                this.k.send(obtain);
                this.char = System.currentTimeMillis();
                this.v = true;
                j.if(for, "send poi request to server...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void do(Message message) {
        j.if(for, "onSetOption...");
        if (message == null || message.obj == null) {
            j.if(for, "setOption, but msg.obj is null");
            return;
        }
        LocationClientOption locationClientOption = (LocationClientOption) message.obj;
        if (!this.l.equals(locationClientOption)) {
            if (this.l.int != locationClientOption.int) {
                try {
                    synchronized (this.new) {
                        if (this.g) {
                            this.do.removeCallbacks(this.byte);
                            this.g = false;
                        }
                        if (locationClientOption.int >= 1000 && !this.g) {
                            if (this.byte == null) {
                                this.byte = new b();
                            }
                            this.do.postDelayed(this.byte, (long) locationClientOption.int);
                            this.g = true;
                        }
                    }
                } catch (Exception e) {
                    j.if(for, "set location excpetion...");
                }
            }
            this.l = new LocationClientOption(locationClientOption);
            if (this.k == null) {
                j.if(for, "server not connected");
                return;
            }
            try {
                Message obtain = Message.obtain(null, 15);
                obtain.replyTo = this.i;
                obtain.setData(if());
                this.k.send(obtain);
                j.if(for, "change option ...");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void for() {
        if (!this.q) {
            j.try();
            this.A = this.j.getPackageName();
            this.z = this.A + "_bdls_v2.9";
            j.if(for, this.z);
            Intent intent = new Intent(this.j, f.class);
            if (this.l == null) {
                this.l = new LocationClientOption();
            }
            try {
                this.j.bindService(intent, this.t, 1);
            } catch (Exception e) {
                e.printStackTrace();
                this.q = false;
            }
        }
    }

    private void for(Message message) {
        BDLocation bDLocation = new BDLocation(message.getData().getString("locStr"));
        if (this.c == null) {
            return;
        }
        if (this.l == null || !this.l.isDisableCache() || bDLocation.getLocType() != 65) {
            this.c.onReceiveLocation(bDLocation);
        }
    }

    private Bundle if() {
        if (this.l == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("packName", this.A);
        bundle.putString("prodName", this.l.new);
        bundle.putString("coorType", this.l.try);
        bundle.putString("addrType", this.l.char);
        bundle.putString("Url", this.u);
        bundle.putBoolean("openGPS", this.l.case);
        bundle.putBoolean("location_change_notify", this.l.void);
        bundle.putInt("scanSpan", this.l.int);
        bundle.putInt("timeOut", this.l.long);
        bundle.putInt("priority", this.l.goto);
        bundle.putBoolean("map", this.long.booleanValue());
        bundle.putBoolean("import", this.case.booleanValue());
        return bundle;
    }

    private void if(Message message) {
        if (message != null && message.obj != null) {
            BDLocationListener bDLocationListener = (BDLocationListener) message.obj;
            if (this.C != null && this.C.contains(bDLocationListener)) {
                this.C.remove(bDLocationListener);
            }
        }
    }

    private void int() {
        if (this.q && this.k != null) {
            Message obtain = Message.obtain(null, 12);
            obtain.replyTo = this.i;
            try {
                this.k.send(obtain);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.j.unbindService(this.t);
            synchronized (this.new) {
                try {
                    if (this.g) {
                        this.do.removeCallbacks(this.byte);
                        this.g = false;
                    }
                } catch (Exception e2) {
                }
            }
            this.else.if();
            this.k = null;
            j.new();
            this.q = false;
        }
    }

    private void int(Message message) {
        if (message != null && message.obj != null) {
            this.else.do((BDNotifyListener) message.obj);
        }
    }

    private void new() {
        if (this.k == null) {
            j.if(for, "server not connected");
            return;
        }
        Message obtain = Message.obtain(null, 22);
        try {
            obtain.replyTo = this.i;
            this.k.send(obtain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void new(Message message) {
        if (message != null && message.obj != null) {
            BDLocationListener bDLocationListener = (BDLocationListener) message.obj;
            if (this.C == null) {
                this.C = new ArrayList();
            }
            this.C.add(bDLocationListener);
        }
    }

    private void try() {
        Message obtain = Message.obtain(null, 28);
        try {
            obtain.replyTo = this.i;
            this.k.send(obtain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void try(Message message) {
        if (message != null && message.obj != null) {
            this.c = (BDLocationListener) message.obj;
        }
    }

    public BDLocation getLastKnownLocation() {
        return this.x;
    }

    public LocationClientOption getLocOption() {
        return this.l;
    }

    public String getVersion() {
        return this.e;
    }

    public boolean isStarted() {
        return this.q;
    }

    public void registerLocationListener(BDLocationListener bDLocationListener) {
        Message obtainMessage = this.do.obtainMessage(5);
        obtainMessage.obj = bDLocationListener;
        obtainMessage.sendToTarget();
    }

    public void registerNotify(BDNotifyListener bDNotifyListener) {
        Message obtainMessage = this.do.obtainMessage(9);
        obtainMessage.obj = bDNotifyListener;
        obtainMessage.sendToTarget();
    }

    public void registerNotifyLocationListener(BDLocationListener bDLocationListener) {
        Message obtainMessage = this.do.obtainMessage(8);
        obtainMessage.obj = bDLocationListener;
        obtainMessage.sendToTarget();
    }

    public void removeNotifyEvent(BDNotifyListener bDNotifyListener) {
        Message obtainMessage = this.do.obtainMessage(10);
        obtainMessage.obj = bDNotifyListener;
        obtainMessage.sendToTarget();
    }

    public int requestLocation() {
        if (this.k == null || this.i == null) {
            return 1;
        }
        if (this.C == null || this.C.size() < 1) {
            return 2;
        }
        if (System.currentTimeMillis() - this.r < 1000) {
            return 6;
        }
        j.if(for, "request location ...");
        this.do.obtainMessage(4).sendToTarget();
        return 0;
    }

    public void requestNotifyLocation() {
        this.do.obtainMessage(11).sendToTarget();
    }

    public int requestOfflineLocation() {
        if (this.k == null || this.i == null) {
            return 1;
        }
        if (this.C == null || this.C.size() < 1) {
            return 2;
        }
        this.do.obtainMessage(12).sendToTarget();
        return 0;
    }

    public int requestPoi() {
        if (this.k == null || this.i == null) {
            return 1;
        }
        if (this.C == null || this.C.size() < 1) {
            return 2;
        }
        if (System.currentTimeMillis() - this.char < 6000) {
            return 6;
        }
        if (this.l.a < 1) {
            return 7;
        }
        j.if(for, "request location ...");
        this.do.obtainMessage(7).sendToTarget();
        return 0;
    }

    public void setForBaiduMap(boolean z) {
        this.long = Boolean.valueOf(z);
    }

    public void setLocOption(LocationClientOption locationClientOption) {
        Object locationClientOption2;
        if (locationClientOption == null) {
            locationClientOption2 = new LocationClientOption();
        }
        Message obtainMessage = this.do.obtainMessage(3);
        obtainMessage.obj = locationClientOption2;
        obtainMessage.sendToTarget();
    }

    public void start() {
        this.do.obtainMessage(1).sendToTarget();
    }

    public void stop() {
        this.do.obtainMessage(2).sendToTarget();
    }

    public void unRegisterLocationListener(BDLocationListener bDLocationListener) {
        Message obtainMessage = this.do.obtainMessage(6);
        obtainMessage.obj = bDLocationListener;
        obtainMessage.sendToTarget();
    }

    public boolean updateLocation(Location location) {
        if (this.k == null || this.i == null || location == null) {
            return false;
        }
        try {
            Message obtain = Message.obtain(null, 57);
            obtain.obj = location;
            this.k.send(obtain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
