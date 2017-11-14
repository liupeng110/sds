package com.igexin.push.core;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import com.igexin.a.a.b.d;
import com.igexin.a.a.c.a;
import com.igexin.a.a.d.a.c;
import com.igexin.a.a.d.e;
import com.igexin.push.a.i;
import com.igexin.push.b.b;
import com.igexin.push.d.j;
import com.igexin.push.e.b.g;
import com.igexin.push.e.b.h;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushService;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class f implements c {
    private static f l;
    private Context a;
    private h b = new h();
    private Handler c;
    private ConcurrentLinkedQueue d = new ConcurrentLinkedQueue();
    private com.igexin.push.core.a.f e;
    private ConnectivityManager f;
    private d g;
    private com.igexin.a.a.b.c h;
    private j i;
    private com.igexin.push.d.c j;
    private b k;

    private f() {
    }

    public static f a() {
        if (l == null) {
            l = new f();
        }
        return l;
    }

    private void n() {
        String packageName = this.a.getPackageName();
        List<PackageInfo> installedPackages = this.a.getPackageManager().getInstalledPackages(4);
        if (installedPackages != null) {
            for (PackageInfo packageInfo : installedPackages) {
                if ((packageInfo.applicationInfo.flags & 1) == 0 || (packageInfo.applicationInfo.flags & 128) == 1) {
                    ServiceInfo[] serviceInfoArr = packageInfo.services;
                    if (serviceInfoArr != null) {
                        int length = serviceInfoArr.length;
                        int i = 0;
                        while (i < length) {
                            ServiceInfo serviceInfo = serviceInfoArr[i];
                            if (a.o.equals(serviceInfo.name) || a.n.equals(serviceInfo.name) || a.p.equals(serviceInfo.name)) {
                                String str = packageInfo.packageName;
                                if (!packageName.equals(str)) {
                                    com.igexin.push.core.c.f.a().c().put(str, serviceInfo.name);
                                }
                            } else {
                                i++;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean o() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PushConsts.ACTION_BROADCAST_NETWORK_CHANGE);
        intentFilter.addAction("com.igexin.sdk.action.snlrefresh");
        intentFilter.addAction("com.igexin.sdk.action.snlretire");
        intentFilter.addAction(g.W);
        intentFilter.addAction("com.igexin.sdk.action.execute");
        intentFilter.addAction("com.igexin.sdk.action.doaction");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        if (this.a.registerReceiver(n.a(), intentFilter) == null) {
            a.a("CoreLogic|InternalPublicReceiver|Failed");
        }
        intentFilter = new IntentFilter();
        intentFilter.addDataScheme("package");
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        if (this.a.registerReceiver(m.a(), intentFilter) == null) {
            a.a("CoreLogic|InternalPackageReceiver|Failed");
        }
        return true;
    }

    public void a(e eVar) {
        this.c = eVar;
    }

    public boolean a(Context context) {
        this.a = context;
        this.b.start();
        return true;
    }

    public boolean a(Intent intent) {
        if (g.i == null) {
            return false;
        }
        g.i.sendBroadcast(intent);
        return true;
    }

    public boolean a(Message message) {
        if (g.j.get()) {
            this.c.sendMessage(message);
        } else {
            this.d.add(message);
        }
        return true;
    }

    public boolean a(com.igexin.a.a.d.a.f fVar, e eVar) {
        return this.e != null ? this.e.a((Object) fVar) : false;
    }

    public boolean a(com.igexin.a.a.d.d dVar, e eVar) {
        return this.e != null ? this.e.a(dVar) : false;
    }

    public boolean a(h hVar) {
        return hVar != null ? d.c().a(hVar, false, true) : false;
    }

    public boolean a(String str) {
        String g = com.igexin.push.core.a.f.a().g("ss");
        if (!(g.i == null || this.j == null)) {
            new com.igexin.sdk.a.d(g.i).b();
            g.l = false;
            g.p = false;
            com.igexin.push.d.a aVar = new com.igexin.push.d.a();
            aVar.a(c.stop);
            this.j.a(aVar);
            if (g != null && "1".equals(g)) {
                try {
                    InputStream inputStream = Runtime.getRuntime().exec("ps").getInputStream();
                    if (inputStream != null) {
                        Object split;
                        String packageName = g.i.getPackageName();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        List arrayList = new ArrayList();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            split = readLine.split("\\s+");
                            arrayList.add(split);
                            if (readLine.indexOf(packageName + "/files/gdaemon") != -1 && split.length > 0) {
                                break;
                            }
                        }
                        Process.killProcess(Integer.valueOf(split[1]).intValue());
                        bufferedReader.close();
                        inputStream.close();
                    }
                } catch (Exception e) {
                }
                c();
            }
        }
        return true;
    }

    public boolean a(boolean z) {
        if (!(g.i == null || this.j == null)) {
            new com.igexin.sdk.a.d(g.i).a();
            g.l = true;
            if (!new com.igexin.sdk.a.b(g.i).b()) {
                new com.igexin.sdk.a.c(g.i).a();
                g.m = true;
                new com.igexin.sdk.a.b(g.i).a();
            }
            if (z) {
                new com.igexin.sdk.a.c(g.i).a();
                g.m = true;
            }
            com.igexin.push.d.a aVar = new com.igexin.push.d.a();
            aVar.a(c.start);
            this.j.a(aVar);
        }
        return true;
    }

    public void b() {
        try {
            this.f = (ConnectivityManager) this.a.getSystemService("connectivity");
            g.a(this.a);
            this.k = new b(this.a);
            i.a().b();
            o();
            this.g = d.c();
            this.g.a(new com.igexin.push.c.a(this.a, h()));
            this.g.a((c) this);
            this.g.a(this.a);
            com.igexin.a.a.d.d aVar = new com.igexin.push.b.a();
            aVar.a(com.igexin.push.core.c.f.a());
            aVar.a(com.igexin.push.core.c.c.a());
            aVar.a(com.igexin.push.core.c.b.a());
            aVar.a(com.igexin.push.core.b.e.a());
            aVar.a(com.igexin.push.a.a.a());
            this.g.a(aVar, true, false);
            d.c().a(com.igexin.a.b.a.a(g.C.getBytes()));
            g.af = this.g.a(com.igexin.push.e.b.d.g(), false, true);
            g.ag = this.g.a(com.igexin.push.e.b.f.g(), true, true);
            g.ah = this.g.a(com.igexin.push.e.b.e.g(), false, true);
            g.ai = this.g.a(g.g(), false, true);
            g.aj = this.g.a(com.igexin.push.e.b.a.g(), false, true);
            g.ak = this.g.a(com.igexin.push.e.b.b.g(), false, true);
            this.e = com.igexin.push.core.a.f.a();
            try {
                if (this.e.E()) {
                    WifiInfo connectionInfo = ((WifiManager) this.a.getSystemService("wifi")).getConnectionInfo();
                    if (connectionInfo != null) {
                        g.z = connectionInfo.getMacAddress();
                    }
                }
            } catch (Exception e) {
            }
            this.i = new j();
            this.i.a(this.a, this.g, this.e);
            this.j = new com.igexin.push.d.c();
            this.j.a(this.a);
            com.igexin.push.d.a aVar2 = new com.igexin.push.d.a();
            aVar2.a(c.start);
            this.j.a(aVar2);
            g.g().h();
            g.j.set(true);
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                Message message = (Message) it.next();
                if (this.c != null) {
                    this.c.sendMessage(message);
                }
            }
            com.igexin.push.core.a.f.a().u();
            this.e.a(Process.myPid());
            n();
            com.igexin.push.extension.a.a().a(this.a);
        } catch (Exception e2) {
            a.a("CoreLogic|init|failed");
        }
    }

    public boolean b(String str) {
        if (!(g.i == null || this.j == null)) {
            new com.igexin.sdk.a.c(g.i).b();
            g.m = false;
            g.p = false;
            com.igexin.push.d.a aVar = new com.igexin.push.d.a();
            aVar.a(c.stop);
            this.j.a(aVar);
        }
        return true;
    }

    public void c() {
        this.a.stopService(new Intent(this.a, PushService.class));
    }

    public com.igexin.a.a.b.c d() {
        if (this.h == null) {
            this.h = com.igexin.push.c.a.c.a();
        }
        return this.h;
    }

    public j e() {
        return this.i;
    }

    public com.igexin.push.d.c f() {
        return this.j;
    }

    public com.igexin.push.core.a.f g() {
        return this.e;
    }

    public ConnectivityManager h() {
        return this.f;
    }

    public b i() {
        return this.k;
    }

    public void j() {
        try {
            this.a.unregisterReceiver(m.a());
            this.a.unregisterReceiver(n.a());
            this.a.unregisterReceiver(d.c());
        } catch (Exception e) {
        }
        com.igexin.push.extension.a.a().b();
    }

    public String k() {
        if (this.f == null) {
            return null;
        }
        NetworkInfo activeNetworkInfo = this.f.getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getType() == 1 ? "wifi" : activeNetworkInfo.getType() == 0 ? "mobile" : null : null;
    }

    public boolean l() {
        return true;
    }

    public long m() {
        return 94808;
    }
}
