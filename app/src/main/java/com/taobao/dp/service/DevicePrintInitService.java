package com.taobao.dp.service;

import android.content.Context;
import com.taobao.dp.a.c;
import com.taobao.dp.bean.DeviceInfo;
import com.taobao.dp.client.IInitResultListener;
import com.taobao.dp.client.a;
import com.taobao.dp.http.DefaultUrlRequestService;
import com.taobao.dp.http.IUrlRequestService;
import com.taobao.dp.util.b;
import com.taobao.dp.util.d;
import com.taobao.dp.util.e;
import com.ttfm.android.sdk.http.HttpChannelSongListGetV2;
import org.json.JSONException;
import org.json.JSONObject;

public final class DevicePrintInitService implements Runnable {
    private Context a;
    private IUrlRequestService b;
    private String c = null;
    private b d;
    private String e;
    private String f;
    private IInitResultListener g;
    private a h;
    private IInitServiceCallBack i;
    private boolean j;
    private volatile int k;

    public interface IInitServiceCallBack {
        void notifyDidChanged(DevicePrintInitService devicePrintInitService, String str);

        void onInitFinished(DevicePrintInitService devicePrintInitService, int i);
    }

    public DevicePrintInitService(Context context, String str, String str2, IInitServiceCallBack iInitServiceCallBack, a aVar, IUrlRequestService iUrlRequestService, IInitResultListener iInitResultListener, boolean z) {
        this.a = context;
        this.e = str;
        if (str2 == null) {
            str2 = "";
        }
        this.f = str2;
        this.i = iInitServiceCallBack;
        this.d = new b(context);
        this.h = aVar;
        this.g = iInitResultListener;
        if (iUrlRequestService != null) {
            this.b = iUrlRequestService;
        } else {
            this.b = new DefaultUrlRequestService();
        }
        this.j = z;
        this.k = 200;
    }

    private String a(String str) {
        String str2 = null;
        if (str != null) {
            try {
                if (!"".equals(str)) {
                    DeviceInfo a = this.d.a();
                    String str3 = a.getAid() + a.getCpu() + a.getResolution() + a.getRam() + a.getStorage() + a.getImei();
                    c.a();
                    Object a2 = c.a(str, "MD5");
                    c.a();
                    Object a3 = c.a(str3, "MD5");
                    Object obj = new byte[(a2.length + 7)];
                    System.arraycopy(a2, 0, obj, 0, a2.length);
                    System.arraycopy(a3, 4, obj, a2.length, 7);
                    byte a4 = b.a(obj);
                    a2 = new byte[(obj.length + 1)];
                    System.arraycopy(obj, 0, a2, 0, obj.length);
                    a2[obj.length] = a4;
                    str2 = com.taobao.dp.a.b.a(a2);
                }
            } catch (Exception e) {
            }
        }
        return str2;
    }

    private synchronized void a(String str, String str2) {
        this.c = str2;
        this.i.notifyDidChanged(this, a(str2));
        if (!(com.taobao.dp.http.c.c.equals(str) || com.taobao.dp.http.c.a.equals(str))) {
            b(str2);
        }
    }

    private void b(String str) {
        DeviceInfo a = this.d.a();
        String str2 = a.getAid() + a.getOs() + a.getCpu() + a.getResolution() + a.getRam() + a.getStorage() + a.getImei() + a.getImsi() + a.getWifi() + a.getUtdid();
        c.a();
        String a2 = com.taobao.dp.util.c.a(c.a(str2, "MD5"));
        if (a2 != null && !"".equals(a2)) {
            if ((System.currentTimeMillis() - this.d.b() >= HttpChannelSongListGetV2.CACHE_TIME || !a2.equals(this.d.a(this.e, this.f))) && str != null) {
                try {
                    JSONObject jSONObject = new JSONObject(d.a(e.a(this.d.a())));
                    jSONObject.put("uuid", str);
                    new com.taobao.dp.http.a(this.a, this.e, this.f, this.h, this.b, new d(this, a2, str)).a(com.taobao.dp.http.c.c, jSONObject);
                } catch (JSONException e) {
                    new StringBuilder("DevicePrintInitService updateDeviceInfoOnServer JSONException=").append(e.toString());
                }
            }
        }
    }

    private void f() {
        try {
            new com.taobao.dp.http.a(this.a, this.e, this.f, this.h, this.b, new c(this)).a(com.taobao.dp.http.c.a, new JSONObject(d.a(e.a(this.d.a()))));
        } catch (Exception e) {
            new StringBuilder("DevicePrintInitService initUuidFromServer :").append(e);
        }
        new StringBuilder("DevicePrintInitService initUuidFromServer end service=").append(this);
    }

    public final IInitResultListener a() {
        return this.g;
    }

    public final void a(int i) {
        this.k = i;
    }

    public final a b() {
        return this.h;
    }

    public final boolean c() {
        return this.j;
    }

    public final int d() {
        return this.k;
    }

    public final String e() {
        return this.c;
    }

    public final void run() {
        new StringBuilder("DevicePrintInitService initUUIDSync : ").append(Thread.currentThread().getName()).append(" initializing initUUID");
        try {
            String a = this.d.a(this.e, this.f, this.h);
            if ((a == null || "".equals(a)) && "".equals(this.f) && this.e.equals(com.taobao.dp.a.d.a(this.a))) {
                b.a b = this.d.b(this.h);
                String str = b.a;
                String str2 = b.b;
                a = this.d.a(str, str2, this.h);
                if (a != null && a.length() > 0) {
                    this.e = str;
                    this.f = str2;
                }
            }
            if (a == null || "".equals(a)) {
                a = this.d.a(this.h);
                if (a == null || "".equals(a)) {
                    f();
                    return;
                }
                try {
                    com.taobao.dp.a.a.a();
                    new com.taobao.dp.http.a(this.a, this.e, this.f, this.h, this.b, new e(this)).a(com.taobao.dp.http.c.b, new JSONObject(com.taobao.dp.a.a.b(a)));
                    return;
                } catch (Exception e) {
                    new StringBuilder("DevicePrintInitService decryptUUIDFromServer ").append(e);
                    f();
                    return;
                }
            }
            this.c = a;
            this.i.notifyDidChanged(this, a(a));
            this.i.onInitFinished(this, 200);
            this.d.b(a, this.e, this.f, this.h);
            b(a);
        } catch (Exception e2) {
            new StringBuilder("DevicePrintInitService initUUIDSync ").append(e2);
        }
    }
}
