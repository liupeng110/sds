package com.sds.android.ttpod.framework.modules;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.base.j;
import com.sds.android.ttpod.framework.modules.core.a.a;
import com.sds.android.ttpod.framework.modules.f.c;
import com.sds.android.ttpod.framework.modules.skin.o;
import com.sds.android.ttpod.framework.modules.version.VersionUpdateModule;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* ModuleManager */
public final class d {
    private static d a = new d();
    private static final Map<a, Set<c>> e = new EnumMap(a.class);
    private static final Map<c, Class> f = new EnumMap(c.class);
    private Map<c, b> b = new EnumMap(c.class);
    private Map<c, Long> c = new EnumMap(c.class);
    private Handler d = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ d a;

        public void handleMessage(Message message) {
            long currentTimeMillis = System.currentTimeMillis();
            Set<c> hashSet = new HashSet();
            for (c cVar : this.a.c.keySet()) {
                if (((Long) this.a.c.get(cVar)).longValue() + ((b) this.a.b.get(cVar)).timeOutInMills() < currentTimeMillis) {
                    hashSet.add(cVar);
                }
            }
            for (c cVar2 : hashSet) {
                this.a.a(cVar2);
            }
            this.a.d.removeMessages(1);
            if (!this.a.c.isEmpty()) {
                this.a.d.sendEmptyMessageDelayed(1, 15000);
            }
        }
    };

    static {
        d();
        e();
    }

    private static void d() {
        f.put(c.DOWNLOAD_MANAGER, a.class);
        f.put(c.FAVORITE, com.sds.android.ttpod.framework.modules.a.a.class);
        f.put(c.MEDIA_SCAN, com.sds.android.ttpod.framework.modules.core.d.a.class);
        f.put(c.MEDIA_ACCESS, com.sds.android.ttpod.framework.modules.core.c.a.class);
        f.put(c.MONITOR, com.sds.android.ttpod.framework.modules.core.monitor.b.class);
        f.put(c.MUSIC_CIRCLE, c.class);
        f.put(c.SUPPORT, com.sds.android.ttpod.framework.modules.core.e.a.class);
        f.put(c.FIND_SONG, com.sds.android.ttpod.framework.modules.b.a.class);
        f.put(c.SEARCH, com.sds.android.ttpod.framework.modules.search.a.class);
        f.put(c.SKIN, o.class);
        f.put(c.SPLASH, com.sds.android.ttpod.framework.modules.g.d.class);
        f.put(c.USER_SYSTEM, com.sds.android.ttpod.framework.modules.core.f.c.class);
        f.put(c.VERSION, VersionUpdateModule.class);
        f.put(c.GLOBAL, com.sds.android.ttpod.framework.modules.core.b.b.class);
        f.put(c.LOCK_SCREEN, com.sds.android.ttpod.framework.modules.e.a.class);
        f.put(c.AUDIO_EFFECT, com.sds.android.ttpod.framework.modules.core.audioeffect.c.class);
        f.put(c.THEME, com.sds.android.ttpod.framework.modules.theme.d.class);
        f.put(c.VERSION_COMPACT, com.sds.android.ttpod.framework.modules.core.g.b.class);
        f.put(c.MUSIC_LIBRARY, com.sds.android.ttpod.framework.modules.b.b.class);
        f.put(c.UNICOM_FLOW, com.sds.android.ttpod.framework.modules.h.c.class);
        f.put(c.FEEDBACK, com.sds.android.ttpod.framework.modules.c.a.class);
    }

    private static void e() {
        try {
            for (c cVar : f.keySet()) {
                j jVar = (j) ((Class) f.get(cVar)).getAnnotation(j.class);
                if (jVar != null) {
                    a[] a = jVar.a();
                    int length = a.length;
                    int i = 0;
                    while (i < length) {
                        a aVar = a[i];
                        if (!EnvironmentUtils.a.i() || aVar.getCommandType() == com.sds.android.ttpod.framework.base.c.FROM_MODULE) {
                            Set set = (Set) e.get(aVar);
                            if (set == null) {
                                set = new HashSet();
                            }
                            set.add(cVar);
                            e.put(aVar, set);
                            i++;
                        } else {
                            throw new IllegalArgumentException("ObserverCommandID must contain command with CommandType = FROM_MODULE");
                        }
                    }
                    continue;
                }
            }
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static boolean a(a aVar, c cVar) {
        com.sds.android.sdk.lib.util.d.a((Object) aVar, "commandID");
        com.sds.android.sdk.lib.util.d.a((Object) cVar, "moduleClass");
        Set set = (Set) e.get(aVar);
        return set != null && set.contains(cVar);
    }

    public static d a() {
        return a;
    }

    private d() {
    }

    public void a(a aVar) {
        if (aVar.getCommandType().equals(com.sds.android.ttpod.framework.base.c.TO_MODULE)) {
            c(aVar.getModuleID());
            return;
        }
        Set<c> set = (Set) e.get(aVar);
        if (set != null) {
            for (c c : set) {
                c(c);
            }
        }
    }

    private void c(c cVar) {
        com.sds.android.ttpod.framework.a.a.b.a("ModuleManager", "tryLoadModule " + cVar);
        if (!d(cVar)) {
            g.a("ModuleManager", "LoadModule:" + cVar.name());
            b e = e(cVar);
            e.onCreate();
            this.b.put(cVar, e);
            if (e.timeOutInMills() != Long.MIN_VALUE) {
                this.c.put(cVar, Long.valueOf(System.currentTimeMillis()));
                if (!this.d.hasMessages(1)) {
                    this.d.sendEmptyMessageDelayed(1, 15000);
                }
            }
        }
    }

    public void a(c cVar) {
        g.d("ModuleManager", "unloadModule:" + cVar.name());
        com.sds.android.sdk.lib.util.d.a();
        b bVar = (b) this.b.get(cVar);
        if (bVar != null) {
            bVar.onPreDestroy();
            bVar.onDestroy();
            this.b.remove(cVar);
            this.c.remove(cVar);
        }
    }

    public void b(c cVar) {
        if (this.c.containsKey(cVar)) {
            this.c.put(cVar, Long.valueOf(System.currentTimeMillis()));
        }
    }

    public void a(Context context) {
        b.setContext(context);
        f();
    }

    private void f() {
        c(c.GLOBAL);
        c(c.FAVORITE);
        c(c.SUPPORT);
        c(c.MONITOR);
    }

    public void b() {
        for (b onPreDestroy : this.b.values()) {
            onPreDestroy.onPreDestroy();
        }
    }

    public void c() {
        com.sds.android.sdk.lib.util.d.a();
        this.d.removeMessages(1);
        g.a("ModuleManager", "unInitModule search lookLyricPic");
        for (b onDestroy : this.b.values()) {
            onDestroy.onDestroy();
        }
        this.b.clear();
    }

    private boolean d(c cVar) {
        com.sds.android.sdk.lib.util.d.a();
        return this.b.containsKey(cVar);
    }

    private b e(c cVar) {
        Class cls = (Class) f.get(cVar);
        if (cls == null) {
            throw new IllegalArgumentException("Module(" + cVar.name() + " not existed or not be register!");
        }
        try {
            return (b) cls.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Module(" + cVar.name() + " can not be loaded!");
        }
    }
}
