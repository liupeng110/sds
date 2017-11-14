package com.sds.android.ttpod.framework.support;

import android.os.RemoteException;
import com.sds.android.sdk.core.statistic.StatisticEvent;
import com.sds.android.sdk.core.statistic.StatisticManager;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.support.b.a;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Map;

/* SupportServiceStub */
public class f extends a {
    private static final short[] b = new short[2048];
    protected SoftReference<SupportService> a;

    public f(SupportService supportService) {
        this.a = new SoftReference(supportService);
    }

    public void a() throws RemoteException {
        ((SupportService) this.a.get()).c();
    }

    public int b() throws RemoteException {
        return ((SupportService) this.a.get()).d();
    }

    public int c() throws RemoteException {
        return ((SupportService) this.a.get()).e();
    }

    public float d() throws RemoteException {
        return ((SupportService) this.a.get()).f();
    }

    public void a(int i) throws RemoteException {
        ((SupportService) this.a.get()).b(i);
    }

    public int e() throws RemoteException {
        return ((SupportService) this.a.get()).g();
    }

    public boolean a(int[] iArr, int i) throws RemoteException {
        return ((SupportService) this.a.get()).a(iArr, i);
    }

    public boolean b(int[] iArr, int i) throws RemoteException {
        int i2 = 0;
        if (!((SupportService) this.a.get()).a(b, i)) {
            return false;
        }
        while (i2 < i) {
            iArr[i2] = b[i2];
            i2++;
        }
        return true;
    }

    public void g() throws RemoteException {
        ((SupportService) this.a.get()).i();
    }

    public void h() throws RemoteException {
        ((SupportService) this.a.get()).j();
    }

    public void a(boolean z) throws RemoteException {
        ((SupportService) this.a.get()).a(z);
    }

    public void b(long j) throws RemoteException {
        ((SupportService) this.a.get()).b(j);
    }

    public void l() throws RemoteException {
        ((SupportService) this.a.get()).k();
    }

    public void a(String str, boolean z) {
        ((SupportService) this.a.get()).a(str, z);
    }

    public String i() throws RemoteException {
        return ((SupportService) this.a.get()).h();
    }

    public String j() throws RemoteException {
        return ((SupportService) this.a.get()).l();
    }

    public MediaItem f() {
        return ((SupportService) this.a.get()).m();
    }

    public void a(StatisticEvent statisticEvent) {
        g.a("SupportServiceStub", "addStatisticEvent event=" + statisticEvent.toString());
        StatisticManager.getInstance().addEvent(statisticEvent);
    }

    public void a(String str, int i, int i2, String str2, String str3, boolean z) throws RemoteException {
        ((SupportService) this.a.get()).a(str, i, i2, str2, str3, z);
    }

    public long k() throws RemoteException {
        return ((SupportService) this.a.get()).n();
    }

    public void a(long j) throws RemoteException {
        ((SupportService) this.a.get()).c(j);
    }

    public void a(Map map) {
        g.a("SupportServiceStub", "setOnlineMediaOrigin origin = " + map.get("origin"));
        p.a(map);
    }

    public void a(String str) throws RemoteException {
        ((SupportService) this.a.get()).a(str);
    }

    public void b(String str) throws RemoteException {
        ((SupportService) this.a.get()).b(str);
    }

    public void a(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
        ((SupportService) this.a.get()).a(downloadTaskInfo);
    }

    public DownloadTaskInfo b(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
        return ((SupportService) this.a.get()).b(downloadTaskInfo);
    }

    public DownloadTaskInfo c(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
        return ((SupportService) this.a.get()).c(downloadTaskInfo);
    }

    public int d(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
        return ((SupportService) this.a.get()).d(downloadTaskInfo);
    }

    public List<DownloadTaskInfo> a(int[] iArr) {
        return ((SupportService) this.a.get()).a(iArr);
    }
}
