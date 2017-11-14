package com.sds.android.ttpod.framework.base.a;

import android.os.Handler;
import android.os.Looper;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.ttpod.framework.modules.c;
import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* CommandCenter */
public final class b {
    private static b a = new b();
    private Handler b = new Handler(Looper.getMainLooper());
    private Map<Object, Map<com.sds.android.ttpod.framework.modules.a, a>> c = new HashMap();
    private Map<c, Map<com.sds.android.ttpod.framework.modules.a, Set<Object>>> d = new EnumMap(c.class);

    /* CommandCenter */
    private static final class a {
        private Method a;
        private Class[] b;
        private Class c;

        private a(Method method, Class[] clsArr, Class cls) {
            this.a = method;
            this.b = clsArr;
            this.c = cls;
        }

        private <Result> Result a(Object obj, a aVar, Class<Result> cls) {
            Result invoke;
            a(aVar, (Class) cls);
            if (com.sds.android.sdk.lib.util.EnvironmentUtils.a.j() || com.sds.android.sdk.lib.util.EnvironmentUtils.a.i()) {
                try {
                    invoke = this.a.invoke(obj, aVar.b());
                } catch (IllegalArgumentException e) {
                    throw e;
                } catch (Throwable e2) {
                    throw new RuntimeException(e2);
                } catch (Exception e3) {
                    e3.printStackTrace();
                    invoke = null;
                }
            } else {
                try {
                    invoke = this.a.invoke(obj, aVar.b());
                } catch (Throwable e22) {
                    e22.printStackTrace();
                    invoke = null;
                }
            }
            if (invoke != null) {
                return invoke;
            }
            return null;
        }

        private <Result> void a(a aVar, Class<Result> cls) {
            if (com.sds.android.sdk.lib.util.EnvironmentUtils.a.j()) {
                Object[] b = aVar.b();
                int i = 0;
                while (i < b.length) {
                    Class cls2 = b[i] == null ? null : b[i].getClass();
                    if (a(this.b[i], cls2)) {
                        i++;
                    } else {
                        throw new IllegalArgumentException("Command(CommandID." + aVar.a().name() + ") param " + i + " Type not match, " + this.b[i].getName() + " while expected to be " + cls2.getName());
                    }
                }
                if (cls != null && !a((Class) cls, this.c)) {
                    throw new IllegalArgumentException("Command(CommandID." + aVar.a().name() + ") Return Type not match, " + cls.getName() + " while expected to be " + this.c.getName());
                }
            }
        }

        private static boolean a(Class cls, Class cls2) {
            return cls2 == null || cls.isAssignableFrom(cls2);
        }
    }

    public static b a() {
        com.sds.android.ttpod.framework.a.a.b.a("CommandCenter", "instance, caller is -->" + com.sds.android.ttpod.framework.a.a.a.a());
        return a;
    }

    private b() {
    }

    public void a(Object obj, Map<com.sds.android.ttpod.framework.modules.a, Method> map) {
        d.a(obj, "target");
        d.a();
        if (this.c.containsKey(obj)) {
            throw new IllegalArgumentException("the target Already registered!");
        } else if (map == null) {
            throw new IllegalArgumentException("the commandMap must not be null!");
        } else if (!map.isEmpty()) {
            b(obj, (Map) map);
        }
    }

    private void b(Object obj, Map<com.sds.android.ttpod.framework.modules.a, Method> map) {
        Map enumMap = new EnumMap(com.sds.android.ttpod.framework.modules.a.class);
        for (com.sds.android.ttpod.framework.modules.a aVar : map.keySet()) {
            Method method = (Method) map.get(aVar);
            enumMap.put(aVar, new a(method, method.getParameterTypes(), method.getReturnType()));
            a(obj, aVar);
        }
        this.c.put(obj, enumMap);
    }

    private void a(Object obj, com.sds.android.ttpod.framework.modules.a aVar) {
        Map enumMap;
        Map map = (Map) this.d.get(aVar.getModuleID());
        if (map == null) {
            enumMap = new EnumMap(com.sds.android.ttpod.framework.modules.a.class);
        } else {
            enumMap = map;
        }
        Set set = (Set) enumMap.get(aVar);
        if (set == null) {
            set = new HashSet();
        }
        set.add(obj);
        enumMap.put(aVar, set);
        this.d.put(aVar.getModuleID(), enumMap);
    }

    public void a(Object obj) {
        d.a(obj, "target");
        d.a();
        b(obj);
    }

    private void b(Object obj) {
        Iterator it = this.c.keySet().iterator();
        while (it.hasNext()) {
            if (it.next() == obj) {
                it.remove();
                c(obj);
                return;
            }
        }
    }

    private void c(Object obj) {
        Iterator it = this.d.keySet().iterator();
        while (it.hasNext()) {
            Map map = (Map) this.d.get(it.next());
            Iterator it2 = map.keySet().iterator();
            while (it2.hasNext()) {
                Set set = (Set) map.get(it2.next());
                if (set == null) {
                    it2.remove();
                } else {
                    if (set.contains(obj)) {
                        set.remove(obj);
                    }
                    if (set.isEmpty()) {
                        it2.remove();
                    }
                }
            }
            if (map.isEmpty()) {
                it.remove();
            }
        }
    }

    public void a(a aVar) {
        c(aVar, null);
        b(aVar, null);
    }

    public void a(a aVar, c cVar) {
        a(cVar);
        c(aVar, cVar);
        b(aVar, null);
    }

    public <Result> Result a(a aVar, Class<Result> cls) {
        c(aVar, null);
        if (aVar.a().getCommandType().equals(com.sds.android.ttpod.framework.base.c.TO_MODULE)) {
            return b(aVar, (Class) cls);
        }
        throw new IllegalArgumentException("id of Command with result must be CommandType.TO_MODULE");
    }

    public void b(a aVar) {
        a(aVar, 0);
    }

    public void a(final a aVar, int i) {
        c(aVar, null);
        this.b.postDelayed(new Runnable(this) {
            final /* synthetic */ b b;

            public void run() {
                this.b.b(aVar, null);
            }
        }, (long) i);
    }

    public void b(a aVar, c cVar) {
        a(aVar, cVar, 0);
    }

    public void a(final a aVar, c cVar, int i) {
        a(cVar);
        c(aVar, cVar);
        this.b.postDelayed(new Runnable(this) {
            final /* synthetic */ b b;

            public void run() {
                this.b.b(aVar, null);
            }
        }, (long) i);
    }

    private <Result> Result b(a aVar, Class<Result> cls) {
        com.sds.android.ttpod.framework.a.a.b.a("CommandCenter", "doExeCommand " + aVar.a());
        d.a();
        com.sds.android.ttpod.framework.modules.a a = aVar.a();
        c moduleID = a.getModuleID();
        com.sds.android.ttpod.framework.modules.d.a().a(a);
        com.sds.android.ttpod.framework.modules.d.a().b(moduleID);
        Map map = (Map) this.d.get(moduleID);
        if (map != null && !map.isEmpty()) {
            Set set = (Set) map.get(a);
            if (set == null || set.isEmpty()) {
                if (!com.sds.android.sdk.lib.util.EnvironmentUtils.a.j() || cls == null) {
                    map.remove(a);
                    if (!map.isEmpty()) {
                        return null;
                    }
                    this.d.remove(moduleID);
                    return null;
                }
                throw new IllegalArgumentException("exeCommand(CommandID." + aVar.a().name() + ") with a result must have one target!");
            } else if (!com.sds.android.sdk.lib.util.EnvironmentUtils.a.j() || cls == null || set.size() == 1) {
                Iterator it = new HashSet(set).iterator();
                Result result = null;
                while (it.hasNext()) {
                    Object next = it.next();
                    result = ((a) ((Map) this.c.get(next)).get(a)).a(next, aVar, cls);
                }
                return result;
            } else {
                throw new IllegalArgumentException("exeCommand(CommandID." + aVar.a().name() + ") with a result must only have one target!");
            }
        } else if (!com.sds.android.sdk.lib.util.EnvironmentUtils.a.j() || cls == null) {
            this.d.remove(moduleID);
            return null;
        } else {
            throw new IllegalArgumentException("exeCommand(CommandID." + aVar.a().name() + ") with a result must have one target!");
        }
    }

    private void a(c cVar) {
        if (cVar == null) {
            throw new IllegalArgumentException("fromModuleID must not be null!");
        }
    }

    private void c(a aVar, c cVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("command can not be null!");
        } else if (!com.sds.android.sdk.lib.util.EnvironmentUtils.a.j()) {
        } else {
            if (aVar.a().getCommandType().equals(com.sds.android.ttpod.framework.base.c.FROM_MODULE)) {
                if (cVar == null) {
                    throw new IllegalArgumentException("Command with CommandType.FROM_MODULE should assign fromModuleID");
                } else if (!aVar.a().getModuleID().equals(cVar)) {
                    throw new IllegalArgumentException("command(CommandID." + aVar.a().name() + ") can not send from " + "module(ModuleID." + cVar.name() + ")");
                }
            } else if (cVar != null) {
                throw new IllegalArgumentException("Command with CommandType.TO_MODULE should not assign fromModuleID");
            }
        }
    }
}
