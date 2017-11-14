package com.ut.mini.internal.utprivate;

import android.os.Build.VERSION;
import com.ut.mini.b.a;
import com.ut.mini.base.d;
import com.ut.mini.core.a.c;
import com.ut.mini.core.f.b;
import com.ut.mini.internal.UTPlugin;
import com.ut.mini.plugin.UTMCPlugin;
import java.util.Map;

public class UTFunctionAdjuster {
    private static UTFunctionAdjuster sInstance = null;

    private UTFunctionAdjuster() {
    }

    public static synchronized UTFunctionAdjuster getInstance() {
        UTFunctionAdjuster uTFunctionAdjuster;
        synchronized (UTFunctionAdjuster.class) {
            if (sInstance == null) {
                sInstance = new UTFunctionAdjuster();
            }
            uTFunctionAdjuster = sInstance;
        }
        return uTFunctionAdjuster;
    }

    public void setLogPrefix(String str) {
        a.a(str);
    }

    public void turnOnDevLogMode() {
        a.a(true);
    }

    public void turnOnDebugLogMode() {
        a.b(true);
    }

    public void registerPlugin(UTPlugin uTPlugin, boolean z) {
        com.ut.mini.plugin.a.a().a((UTMCPlugin) uTPlugin, z);
    }

    public void unregisterPlugin(UTPlugin uTPlugin) {
        com.ut.mini.plugin.a.a().a(uTPlugin);
    }

    public String getSignedConfigurationUrl(String str, Map<String, Object> map, Map<String, Object> map2) throws Exception {
        return b.b(str, map, map2);
    }

    public void turnOf2001and1010() {
        if (VERSION.SDK_INT < 14) {
            com.ut.mini.plugin.a.a().a(d.b().a());
        } else {
            c.b(d.b().a());
        }
    }
}
