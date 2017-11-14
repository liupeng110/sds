package com.sds.android.ttpod.framework.base;

import android.content.Context;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.modules.d;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* BaseModule */
public abstract class b {
    private static final String TAG = "BaseModule";
    public static final long TIME_OUT_FOREVER = Long.MIN_VALUE;
    protected static Context sContext = null;

    protected abstract c id();

    protected abstract void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException;

    public static void setContext(Context context) {
        sContext = context;
    }

    public long timeOutInMills() {
        return Long.MIN_VALUE;
    }

    public void onCreate() {
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "onCreate " + getClass());
        Map requestCommandMap = requestCommandMap();
        assertCommandMap(requestCommandMap);
        com.sds.android.ttpod.framework.base.a.b.a().a((Object) this, requestCommandMap);
    }

    public void onPreDestroy() {
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "onPreDestroy " + getClass());
    }

    public void onDestroy() {
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "onDestroy " + getClass());
        com.sds.android.ttpod.framework.base.a.b.a().a((Object) this);
    }

    private Map<a, Method> requestCommandMap() {
        Map<a, Method> hashMap = new HashMap();
        try {
            onLoadCommandMap(hashMap);
            return hashMap;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private void assertCommandMap(Map<a, Method> map) {
        if (EnvironmentUtils.a.j()) {
            for (a aVar : map.keySet()) {
                if (aVar.getCommandType().equals(c.TO_MODULE) && !aVar.getModuleID().equals(id())) {
                    throw new IllegalArgumentException("the CommandID." + aVar.name() + " is not belong to this module(ModuleID." + id().name() + ")!");
                } else if (aVar.getCommandType().equals(c.FROM_MODULE) && !d.a(aVar, id())) {
                    throw new IllegalArgumentException("the CommandID." + aVar.name() + " can not register in Module, because the CommandType is CommandType.FROM_MODULE and not be Declared in " + getClass() + " with annotation ObserverCommand !");
                }
            }
        }
    }
}
