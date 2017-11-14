package com.alibaba.wireless.security.open.umid;

import android.content.Context;
import com.alibaba.wireless.security.SecExceptionCode;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.dp.DeviceSecuritySDK;
import com.taobao.dp.client.IInitResultListener;
import java.util.ArrayList;
import java.util.Iterator;

public final class a implements IUMIDComponent, IInitResultListener {
    private static final Object e = new Object();
    private Context a;
    private short b;
    private volatile boolean c = false;
    private ArrayList d;

    public a(Context context) {
        this.a = context;
        this.b = (short) -1;
        this.d = new ArrayList();
    }

    public final void a() {
        this.c = true;
        try {
            String appKeyByIndex = com.alibaba.wireless.security.open.a.a(this.a).b().getAppKeyByIndex(0, "");
            if (appKeyByIndex == null || appKeyByIndex.length() <= 0) {
                synchronized (e) {
                    this.b = (short) 0;
                }
                return;
            }
            DeviceSecuritySDK.getInstance(this.a).initAsync(appKeyByIndex, 0, null, this);
        } catch (SecException e) {
            synchronized (e) {
                this.b = (short) 0;
            }
        }
    }

    public final String getSecurityToken() throws SecException {
        if (this.c) {
            return DeviceSecuritySDK.getInstance(this.a).getSecurityToken();
        }
        throw new SecException(SecExceptionCode.SEC_ERROR_UMID_NO_NETWORK_INIT);
    }

    public final void initUMID(String str, int i, String str2, IUMIDInitListenerEx iUMIDInitListenerEx) throws SecException {
        DeviceSecuritySDK.getInstance(this.a).initAsync(str, str2, i, null, new b(iUMIDInitListenerEx));
    }

    public final void onInitFinished(String str, int i) {
        short s = (short) 1;
        synchronized (e) {
            boolean z;
            Iterator it;
            IUMIDInitListener iUMIDInitListener;
            if (str != null) {
                if (str.length() > 0 && !"000000000000000000000000".equals(str)) {
                    z = true;
                    if (!z) {
                        s = (short) 0;
                    }
                    this.b = s;
                    it = this.d.iterator();
                    while (it.hasNext()) {
                        iUMIDInitListener = (IUMIDInitListener) it.next();
                        if (iUMIDInitListener != null) {
                            iUMIDInitListener.onUMIDInitFinished(z);
                        }
                    }
                }
            }
            z = false;
            if (z) {
                s = (short) 0;
            }
            this.b = s;
            it = this.d.iterator();
            while (it.hasNext()) {
                iUMIDInitListener = (IUMIDInitListener) it.next();
                if (iUMIDInitListener != null) {
                    iUMIDInitListener.onUMIDInitFinished(z);
                }
            }
        }
    }

    /* JADX err: Inconsistent code. */
    public final void registerInitListener(com.alibaba.wireless.security.open.umid.IUMIDInitListener r5) throws com.alibaba.wireless.security.open.SecException {
        /*
        r4 = this;
        r0 = 1;
        r1 = r4.c;
        if (r1 != 0) goto L_0x000d;
    L_0x0005:
        r0 = new com.alibaba.wireless.security.open.SecException;
        r1 = 901; // 0x385 float:1.263E-42 double:4.45E-321;
        r0.<init>(r1);
        throw r0;
    L_0x000d:
        r1 = e;
        monitor-enter(r1);
        r2 = r4.b;	 Catch:{ all -> 0x0029 }
        r3 = -1;
        if (r2 != r3) goto L_0x001e;
    L_0x0015:
        if (r5 == 0) goto L_0x001c;
    L_0x0017:
        r0 = r4.d;	 Catch:{ all -> 0x0029 }
        r0.add(r5);	 Catch:{ all -> 0x0029 }
    L_0x001c:
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
    L_0x001d:
        return;
    L_0x001e:
        monitor-exit(r1);
        if (r5 == 0) goto L_0x001d;
    L_0x0021:
        r1 = r4.b;
        if (r1 != r0) goto L_0x002c;
    L_0x0025:
        r5.onUMIDInitFinished(r0);
        goto L_0x001d;
    L_0x0029:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x002c:
        r0 = 0;
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.wireless.security.open.umid.a.registerInitListener(com.alibaba.wireless.security.open.umid.IUMIDInitListener):void");
    }
}
