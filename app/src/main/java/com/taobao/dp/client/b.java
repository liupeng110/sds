package com.taobao.dp.client;

import android.content.Context;
import com.igexin.sdk.PushConsts;
import com.taobao.dp.a.d;
import com.taobao.dp.http.IUrlRequestService;
import com.taobao.dp.service.DevicePrintInitService;
import com.taobao.dp.service.DevicePrintInitService.IInitServiceCallBack;
import com.taobao.dp.service.b.a;
import com.taobao.dp.service.f;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class b implements IDeviceSecurity, IInitServiceCallBack {
    public static final String OS = "android";
    public static final String PROTOCAL_VERSION = "1.0.1";
    private static final int SECURITY_TOKEN_LENGTH = 32;
    public static final String SERVICE = "com.taobao.tdp";
    private volatile String mAppName = null;
    private Context mContext;
    private a mEnvironment = a.c;
    private Object mInternalLock = new Object();
    private volatile String mSecToken = null;
    private List mServiceList = new ArrayList();
    private Executor mSinglThreadExcutor = Executors.newSingleThreadExecutor();
    private volatile String mUuid = null;

    protected b(Context context) {
        this.mContext = context;
    }

    public String didStrategyChanged(String str) {
        return this.mSecToken;
    }

    public String getAppName() {
        new StringBuilder("DeviceSecurity getAppName appName=").append(this.mAppName);
        return this.mAppName;
    }

    public String getSecurityToken() {
        String str;
        new StringBuilder("DeviceSecurity getSecurityToken start:").append(this.mSecToken);
        synchronized (this.mInternalLock) {
            if (this.mSecToken == null || "".equals(this.mSecToken) || this.mSecToken.length() < 32) {
                init(null);
                if (this.mSecToken == null || "".equals(this.mSecToken)) {
                    this.mSecToken = com.taobao.dp.service.b.a(this.mContext);
                }
                if (this.mSecToken == null || "".equals(this.mSecToken)) {
                    this.mSecToken = "000000000000000000000000";
                }
            }
            new StringBuilder("DeviceSecurity getSecurityToken end:").append(this.mSecToken);
            str = this.mSecToken;
        }
        return str;
    }

    /* JADX err: Inconsistent code. */
    protected int init(java.lang.String r11, java.lang.String r12, com.taobao.dp.http.IUrlRequestService r13, com.taobao.dp.client.IInitResultListener r14, boolean r15) {
        /*
        r10 = this;
        r0 = new java.lang.StringBuilder;
        r1 = "DeviceSecurity init reqService=";
        r0.<init>(r1);
        r0.append(r13);
        r0 = new java.lang.StringBuilder;
        r1 = "DeviceSecurity init listener  =";
        r0.<init>(r1);
        r0.append(r14);
        if (r11 == 0) goto L_0x001c;
    L_0x0016:
        r0 = r11.length();
        if (r0 != 0) goto L_0x0027;
    L_0x001c:
        if (r14 == 0) goto L_0x0024;
    L_0x001e:
        r0 = 0;
        r1 = 1005; // 0x3ed float:1.408E-42 double:4.965E-321;
        r14.onInitFinished(r0, r1);
    L_0x0024:
        r0 = 1005; // 0x3ed float:1.408E-42 double:4.965E-321;
    L_0x0026:
        return r0;
    L_0x0027:
        r10.mAppName = r11;
        if (r12 != 0) goto L_0x00ad;
    L_0x002b:
        r3 = "";
    L_0x002d:
        r9 = r10.mInternalLock;
        monitor-enter(r9);
        r0 = r10.mUuid;	 Catch:{ all -> 0x004c }
        r1 = r10.mSecToken;	 Catch:{ all -> 0x004c }
        r5 = r10.mEnvironment;	 Catch:{ all -> 0x004c }
        if (r0 == 0) goto L_0x005a;
    L_0x0038:
        if (r1 == 0) goto L_0x005a;
    L_0x003a:
        r0 = r0.length();	 Catch:{ all -> 0x004c }
        if (r0 <= 0) goto L_0x005a;
    L_0x0040:
        r0 = r1.length();	 Catch:{ all -> 0x004c }
        if (r0 <= 0) goto L_0x005a;
    L_0x0046:
        if (r15 == 0) goto L_0x004f;
    L_0x0048:
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        monitor-exit(r9);	 Catch:{ all -> 0x004c }
        goto L_0x0026;
    L_0x004c:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x004f:
        if (r14 == 0) goto L_0x0056;
    L_0x0051:
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r14.onInitFinished(r1, r0);	 Catch:{ all -> 0x004c }
    L_0x0056:
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        monitor-exit(r9);	 Catch:{ all -> 0x004c }
        goto L_0x0026;
    L_0x005a:
        r0 = new com.taobao.dp.service.DevicePrintInitService;	 Catch:{ all -> 0x004c }
        r1 = r10.mContext;	 Catch:{ all -> 0x004c }
        r2 = r11;
        r4 = r10;
        r6 = r13;
        r7 = r14;
        r8 = r15;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x004c }
        r1 = r10.mServiceList;	 Catch:{ all -> 0x004c }
        r1 = r1.size();	 Catch:{ all -> 0x004c }
        if (r1 != 0) goto L_0x00a2;
    L_0x006e:
        r1 = 1;
    L_0x006f:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004c }
        r3 = "DeviceSecurity init mServiceList.add size()=";
        r2.<init>(r3);	 Catch:{ all -> 0x004c }
        r3 = r10.mServiceList;	 Catch:{ all -> 0x004c }
        r3 = r3.size();	 Catch:{ all -> 0x004c }
        r2.append(r3);	 Catch:{ all -> 0x004c }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004c }
        r3 = "DeviceSecurity init mServiceList.add service=";
        r2.<init>(r3);	 Catch:{ all -> 0x004c }
        r2.append(r0);	 Catch:{ all -> 0x004c }
        r2 = r10.mServiceList;	 Catch:{ all -> 0x004c }
        r2.add(r0);	 Catch:{ all -> 0x004c }
        if (r1 == 0) goto L_0x0095;
    L_0x0090:
        r1 = r10.mSinglThreadExcutor;	 Catch:{ all -> 0x004c }
        r1.execute(r0);	 Catch:{ all -> 0x004c }
    L_0x0095:
        monitor-exit(r9);	 Catch:{ all -> 0x004c }
        if (r15 == 0) goto L_0x00a7;
    L_0x0098:
        monitor-enter(r0);
        r0.wait();	 Catch:{ Exception -> 0x00ab }
    L_0x009c:
        monitor-exit(r0);	 Catch:{ all -> 0x00a4 }
        r0 = r0.d();
        goto L_0x0026;
    L_0x00a2:
        r1 = 0;
        goto L_0x006f;
    L_0x00a4:
        r1 = move-exception;
        monitor-exit(r0);
        throw r1;
    L_0x00a7:
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        goto L_0x0026;
    L_0x00ab:
        r1 = move-exception;
        goto L_0x009c;
    L_0x00ad:
        r3 = r12;
        goto L_0x002d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.dp.client.b.init(java.lang.String, java.lang.String, com.taobao.dp.http.IUrlRequestService, com.taobao.dp.client.IInitResultListener, boolean):int");
    }

    public void init() {
        this.mAppName = d.a(this.mContext);
        new StringBuilder("DeviceSecurity init appName=").append(this.mAppName);
    }

    public void init(IUrlRequestService iUrlRequestService) {
        new StringBuilder("DeviceSecurity init reqService=").append(iUrlRequestService);
        String str = this.mAppName;
        String str2 = "";
        if (str == null || str.length() == 0) {
            a b = new com.taobao.dp.service.b(this.mContext).b(this.mEnvironment);
            str = b.a;
            str2 = b.b;
        }
        if (str != null && str.length() != 0) {
            this.mAppName = str;
            init(str, str2, iUrlRequestService, null, false);
        }
    }

    public void notifyDidChanged(DevicePrintInitService devicePrintInitService, String str) {
        new StringBuilder("DeviceSecurity notifyDidChanged service=").append(devicePrintInitService);
        synchronized (this.mInternalLock) {
            if (devicePrintInitService.b() == this.mEnvironment) {
                this.mSecToken = str;
                this.mUuid = devicePrintInitService.e();
            }
        }
    }

    public void notifyDidChanged(String str) {
    }

    public void onInitFinished(int i) {
    }

    public void onInitFinished(DevicePrintInitService devicePrintInitService, int i) {
        Runnable runnable;
        new StringBuilder("DeviceSecurity onInitFinished service=").append(devicePrintInitService);
        List arrayList = new ArrayList();
        synchronized (this.mInternalLock) {
            new StringBuilder("DeviceSecurity onInitFinished mServiceList.contains=").append(this.mServiceList.contains(devicePrintInitService));
            if (this.mServiceList.contains(devicePrintInitService)) {
                List list;
                if (i == 200) {
                    list = this.mServiceList;
                    this.mServiceList = new ArrayList();
                } else {
                    arrayList.add(devicePrintInitService);
                    this.mServiceList.remove(devicePrintInitService);
                    list = arrayList;
                }
                if (this.mServiceList.size() > 0) {
                    arrayList = list;
                    runnable = (DevicePrintInitService) this.mServiceList.get(0);
                } else {
                    arrayList = list;
                    runnable = null;
                }
            } else {
                runnable = null;
            }
        }
        for (DevicePrintInitService devicePrintInitService2 : r0) {
            if (devicePrintInitService2.c()) {
                devicePrintInitService2.a(i);
                synchronized (devicePrintInitService2) {
                    devicePrintInitService2.notify();
                }
            } else if (devicePrintInitService2.a() != null) {
                if (i == 200) {
                    devicePrintInitService2.a().onInitFinished(this.mSecToken, i);
                } else {
                    devicePrintInitService2.a().onInitFinished(null, i);
                }
            }
        }
        if (runnable != null) {
            this.mSinglThreadExcutor.execute(runnable);
        }
        new StringBuilder("DeviceSecurity onInitFinished end service = ").append(devicePrintInitService);
    }

    public void sendLoginResult(String str) {
        new f(this.mContext, str, getSecurityToken()).a();
    }

    protected synchronized void setEnvironment(a aVar) {
        new StringBuilder("DeviceSecurity setEnvironment mEnvironment=").append(this.mEnvironment.g());
        new StringBuilder("DeviceSecurity setEnvironment env=").append(aVar.g());
        if (aVar != this.mEnvironment) {
            synchronized (this.mInternalLock) {
                this.mEnvironment = aVar;
                this.mUuid = null;
                this.mSecToken = null;
                List<DevicePrintInitService> list = this.mServiceList;
                this.mServiceList = new ArrayList();
            }
            for (DevicePrintInitService devicePrintInitService : list) {
                if (devicePrintInitService.c()) {
                    devicePrintInitService.a((int) PushConsts.GET_SDKONLINESTATE);
                    synchronized (devicePrintInitService) {
                        devicePrintInitService.notify();
                    }
                } else if (devicePrintInitService.a() != null) {
                    devicePrintInitService.a().onInitFinished(null, PushConsts.GET_SDKONLINESTATE);
                }
            }
        }
        new StringBuilder("DeviceSecurity setEnvironment this.mServiceList size=").append(this.mServiceList.size());
    }
}
