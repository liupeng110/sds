package com.taobao.securityjni;

import android.content.Context;
import android.content.ContextWrapper;
import com.taobao.wireless.security.sdk.SecurityGuardManager;
import com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent;

public final class GlobalInit {
    private static String a = null;
    private static Context b = null;

    public static synchronized String GetGlobalAppKey() {
        String str;
        synchronized (GlobalInit.class) {
            str = a;
        }
        return str;
    }

    public static void GlobalSecurityInitAsync(ContextWrapper contextWrapper) {
        GlobalSecurityInitAsync(contextWrapper, null);
        a(contextWrapper);
    }

    public static void GlobalSecurityInitAsync(ContextWrapper contextWrapper, String str) {
        b = contextWrapper;
        SecurityGuardManager.getInitializer().loadLibraryAsync(contextWrapper, str);
        a(contextWrapper);
    }

    public static void GlobalSecurityInitAsyncSDK(ContextWrapper contextWrapper) {
        b = contextWrapper;
        SecurityGuardManager.getInitializer().loadLibraryAsync(contextWrapper, null);
        SecurityGuardManager.getInstance(contextWrapper);
        a(contextWrapper);
    }

    public static void GlobalSecurityInitAsyncSo(ContextWrapper contextWrapper) {
        b = contextWrapper;
        a(contextWrapper);
    }

    public static void GlobalSecurityInitAsyncSo(ContextWrapper contextWrapper, String str) {
        b = contextWrapper;
        SecurityGuardManager.getInitializer().loadLibraryAsync(contextWrapper, str);
        SecurityGuardManager.getInstance(contextWrapper);
        a(contextWrapper);
    }

    public static void GlobalSecurityInitSync(ContextWrapper contextWrapper) {
        GlobalSecurityInitSync(contextWrapper, null);
    }

    public static void GlobalSecurityInitSync(ContextWrapper contextWrapper, String str) {
        b = contextWrapper;
        SecurityGuardManager.getInitializer().loadLibrarySync(contextWrapper, str);
        a(contextWrapper);
    }

    public static void GlobalSecurityInitSyncSDK(ContextWrapper contextWrapper) {
        b = contextWrapper;
        if (SecurityGuardManager.getInitializer().loadLibrarySync(contextWrapper, null) == 0 && SecurityGuardManager.getInstance(contextWrapper) != null) {
            a(contextWrapper);
        }
    }

    public static void GlobalSecurityInitSyncSo(ContextWrapper contextWrapper) {
        GlobalSecurityInitSyncSo(contextWrapper, null);
    }

    public static void GlobalSecurityInitSyncSo(ContextWrapper contextWrapper, String str) {
        b = contextWrapper;
        if (SecurityGuardManager.getInitializer().loadLibrarySync(contextWrapper, str) == 0 && SecurityGuardManager.getInstance(contextWrapper) != null) {
            a(contextWrapper);
        }
    }

    public static synchronized void SetGlobalAppKey(String str) {
        synchronized (GlobalInit.class) {
            a = str;
        }
    }

    private static void a(ContextWrapper contextWrapper) {
        SecurityGuardManager instance = SecurityGuardManager.getInstance(contextWrapper);
        if (instance != null) {
            ISecurityBodyComponent securityBodyComp = instance.getSecurityBodyComp();
            if (securityBodyComp != null) {
                String GetGlobalAppKey = GetGlobalAppKey();
                if (GetGlobalAppKey == null) {
                    GetGlobalAppKey = instance.getStaticDataStoreComp().getAppKeyByIndex(0);
                }
                securityBodyComp.initSecurityBody(GetGlobalAppKey);
            }
        }
    }

    public static Context getGlobalContext() {
        return b;
    }

    public static void setEnableOutPutExpInfo(boolean z) {
    }
}
