package com.taobao.dp;

import android.content.Context;
import com.taobao.dp.client.IInitResultListener;
import com.taobao.dp.client.a;
import com.taobao.dp.client.b;
import com.taobao.dp.http.IUrlRequestService;

public final class DeviceSecuritySDK extends b {
    public static final int ENVIRONMENT_DAILY = 1;
    public static final int ENVIRONMENT_ONLINE = 0;
    public static final int ENVIRONMENT_PRE = 2;
    private static DeviceSecuritySDK instance = null;

    private DeviceSecuritySDK(Context context) {
        super(context);
    }

    public static DeviceSecuritySDK getInstance(Context context) {
        if (instance == null) {
            synchronized (DeviceSecuritySDK.class) {
                if (instance == null) {
                    instance = new DeviceSecuritySDK(context);
                    new StringBuilder("SecurityGuardManagerUtil initSecurityGuardManager context").append(context);
                    try {
                        Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager").getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{context});
                    } catch (Exception e) {
                    }
                }
            }
        }
        return instance;
    }

    public final String getSecurityToken() {
        return super.getSecurityToken();
    }

    public final void init() {
        super.init();
    }

    public final void init(IUrlRequestService iUrlRequestService) {
        new StringBuilder("DeviceSecuritySDK init reqService=").append(iUrlRequestService);
        super.init(iUrlRequestService);
    }

    public final void initAsync(String str, int i, IUrlRequestService iUrlRequestService, IInitResultListener iInitResultListener) {
        initAsync(str, "", i, iUrlRequestService, iInitResultListener);
    }

    public final void initAsync(String str, String str2, int i, IUrlRequestService iUrlRequestService, IInitResultListener iInitResultListener) {
        new StringBuilder("DeviceSecuritySDK initAsync reqService=").append(iUrlRequestService);
        new StringBuilder("DeviceSecuritySDK initAsync listener  =").append(iInitResultListener);
        setEnvironment(i);
        super.init(str, str2, iUrlRequestService, iInitResultListener, false);
    }

    public final int initSync(String str, int i, IUrlRequestService iUrlRequestService) {
        return initSync(str, "", i, iUrlRequestService);
    }

    public final int initSync(String str, String str2, int i, IUrlRequestService iUrlRequestService) {
        new StringBuilder("DeviceSecuritySDK initSync reqService=").append(iUrlRequestService);
        setEnvironment(i);
        return super.init(str, str2, iUrlRequestService, null, true);
    }

    public final void sendLoginResult(String str) {
        new a(this, str).start();
    }

    public final void setEnvironment(int i) {
        switch (i) {
            case 0:
                super.setEnvironment(a.c);
                return;
            case 1:
                super.setEnvironment(a.a);
                return;
            case 2:
                super.setEnvironment(a.b);
                return;
            default:
                return;
        }
    }
}
