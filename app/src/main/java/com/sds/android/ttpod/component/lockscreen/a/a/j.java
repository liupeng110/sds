package com.sds.android.ttpod.component.lockscreen.a.a;

import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.component.lockscreen.a.b.c;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* PropertyValuesHolder */
public class j implements Cloneable {
    private static final k i = new d();
    private static final k j = new b();
    private static Class[] k = new Class[]{Float.TYPE, Float.class, Double.TYPE, Integer.TYPE, Double.class, Integer.class};
    private static Class[] l = new Class[]{Integer.TYPE, Integer.class, Float.TYPE, Double.TYPE, Float.class, Double.class};
    private static Class[] m = new Class[]{Double.TYPE, Double.class, Float.TYPE, Integer.TYPE, Float.class, Integer.class};
    private static final HashMap<Class, HashMap<String, Method>> n = new HashMap();
    private static final HashMap<Class, HashMap<String, Method>> o = new HashMap();
    String a;
    protected c b;
    Method c;
    Class d;
    g e;
    final ReentrantReadWriteLock f;
    final Object[] g;
    private Method h;
    private k p;
    private Object q;

    /* PropertyValuesHolder */
    static class a extends j {
        c h;
        float i;
        private com.sds.android.ttpod.component.lockscreen.a.b.a j;

        public /* synthetic */ j a() {
            return e();
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return e();
        }

        public a(String str, float... fArr) {
            super(str);
            a(fArr);
        }

        public a(c cVar, float... fArr) {
            super(cVar);
            a(fArr);
            if (cVar instanceof com.sds.android.ttpod.component.lockscreen.a.b.a) {
                this.j = (com.sds.android.ttpod.component.lockscreen.a.b.a) this.b;
            }
        }

        public void a(float... fArr) {
            super.a(fArr);
            this.h = (c) this.e;
        }

        void a(float f) {
            this.i = this.h.b(f);
        }

        Object d() {
            return Float.valueOf(this.i);
        }

        public a e() {
            a aVar = (a) super.a();
            aVar.h = (c) aVar.e;
            return aVar;
        }

        void b(Object obj) {
            if (this.j != null) {
                this.j.a(obj, this.i);
            } else if (this.b != null) {
                this.b.a(obj, Float.valueOf(this.i));
            } else if (this.c != null) {
                try {
                    this.g[0] = Float.valueOf(this.i);
                    this.c.invoke(obj, this.g);
                } catch (InvocationTargetException e) {
                    g.c("PropertyValuesHolder", e.toString());
                } catch (IllegalAccessException e2) {
                    g.c("PropertyValuesHolder", e2.toString());
                }
            }
        }

        void a(Class cls) {
            if (this.b == null) {
                super.a(cls);
            }
        }
    }

    /* PropertyValuesHolder */
    static class b extends j {
        e h;
        int i;
        private com.sds.android.ttpod.component.lockscreen.a.b.b j;

        public /* synthetic */ j a() {
            return e();
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return e();
        }

        public b(String str, int... iArr) {
            super(str);
            a(iArr);
        }

        public b(c cVar, int... iArr) {
            super(cVar);
            a(iArr);
            if (cVar instanceof com.sds.android.ttpod.component.lockscreen.a.b.b) {
                this.j = (com.sds.android.ttpod.component.lockscreen.a.b.b) this.b;
            }
        }

        public void a(int... iArr) {
            super.a(iArr);
            this.h = (e) this.e;
        }

        void a(float f) {
            this.i = this.h.b(f);
        }

        Object d() {
            return Integer.valueOf(this.i);
        }

        public b e() {
            b bVar = (b) super.a();
            bVar.h = (e) bVar.e;
            return bVar;
        }

        void b(Object obj) {
            if (this.j != null) {
                this.j.a(obj, this.i);
            } else if (this.b != null) {
                this.b.a(obj, Integer.valueOf(this.i));
            } else if (this.c != null) {
                try {
                    this.g[0] = Integer.valueOf(this.i);
                    this.c.invoke(obj, this.g);
                } catch (InvocationTargetException e) {
                    g.c("PropertyValuesHolder", e.toString());
                } catch (IllegalAccessException e2) {
                    g.c("PropertyValuesHolder", e2.toString());
                }
            }
        }

        void a(Class cls) {
            if (this.b == null) {
                super.a(cls);
            }
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }

    private j(String str) {
        this.c = null;
        this.h = null;
        this.e = null;
        this.f = new ReentrantReadWriteLock();
        this.g = new Object[1];
        this.a = str;
    }

    private j(c cVar) {
        this.c = null;
        this.h = null;
        this.e = null;
        this.f = new ReentrantReadWriteLock();
        this.g = new Object[1];
        this.b = cVar;
        if (cVar != null) {
            this.a = cVar.a();
        }
    }

    public static j a(String str, int... iArr) {
        return new b(str, iArr);
    }

    public static j a(c<?, Integer> cVar, int... iArr) {
        return new b((c) cVar, iArr);
    }

    public static j a(String str, float... fArr) {
        return new a(str, fArr);
    }

    public static j a(c<?, Float> cVar, float... fArr) {
        return new a((c) cVar, fArr);
    }

    public void a(int... iArr) {
        this.d = Integer.TYPE;
        this.e = g.a(iArr);
    }

    public void a(float... fArr) {
        this.d = Float.TYPE;
        this.e = g.a(fArr);
    }

    private Method a(Class cls, String str, Class cls2) {
        Method method = null;
        String a = a(str, this.a);
        Class[] clsArr = null;
        if (cls2 == null) {
            try {
                return cls.getMethod(a, clsArr);
            } catch (NoSuchMethodException e) {
                Method declaredMethod;
                try {
                    declaredMethod = cls.getDeclaredMethod(a, clsArr);
                    try {
                        declaredMethod.setAccessible(true);
                        return declaredMethod;
                    } catch (NoSuchMethodException e2) {
                        g.c("PropertyValuesHolder", "Couldn't find no-arg method for property " + this.a + ": " + e);
                        return declaredMethod;
                    }
                } catch (NoSuchMethodException e3) {
                    declaredMethod = null;
                    g.c("PropertyValuesHolder", "Couldn't find no-arg method for property " + this.a + ": " + e);
                    return declaredMethod;
                }
            }
        }
        Class[] clsArr2 = new Class[1];
        if (this.d.equals(Float.class)) {
            clsArr = k;
        } else if (this.d.equals(Integer.class)) {
            clsArr = l;
        } else {
            clsArr = this.d.equals(Double.class) ? m : new Class[]{this.d};
        }
        int length = clsArr.length;
        int i = 0;
        while (i < length) {
            Class cls3 = clsArr[i];
            clsArr2[0] = cls3;
            try {
                method = cls.getMethod(a, clsArr2);
                this.d = cls3;
                return method;
            } catch (NoSuchMethodException e4) {
                try {
                    method = cls.getDeclaredMethod(a, clsArr2);
                    method.setAccessible(true);
                    this.d = cls3;
                    return method;
                } catch (NoSuchMethodException e5) {
                    NoSuchMethodException noSuchMethodException = e5;
                    Method method2 = method;
                    noSuchMethodException.printStackTrace();
                    i++;
                    method = method2;
                }
            }
        }
        g.c("PropertyValuesHolder", "Couldn't find setter/getter for property " + this.a + " with value type " + this.d);
        return method;
    }

    private Method a(Class cls, HashMap<Class, HashMap<String, Method>> hashMap, String str, Class cls2) {
        Method method = null;
        try {
            this.f.writeLock().lock();
            HashMap hashMap2 = (HashMap) hashMap.get(cls);
            if (hashMap2 != null) {
                method = (Method) hashMap2.get(this.a);
            }
            if (method == null) {
                method = a(cls, str, cls2);
                if (hashMap2 == null) {
                    hashMap2 = new HashMap();
                    hashMap.put(cls, hashMap2);
                }
                hashMap2.put(this.a, method);
            }
            Method method2 = method;
            this.f.writeLock().unlock();
            return method2;
        } catch (Throwable th) {
            this.f.writeLock().unlock();
        }
    }

    void a(Class cls) {
        this.c = a(cls, n, "set", this.d);
    }

    private void b(Class cls) {
        this.h = a(cls, o, "get", null);
    }

    void a(Object obj) {
        f fVar;
        if (this.b != null) {
            try {
                this.b.a(obj);
                Iterator it = this.e.e.iterator();
                while (it.hasNext()) {
                    fVar = (f) it.next();
                    if (!fVar.a()) {
                        fVar.a(this.b.a(obj));
                    }
                }
                return;
            } catch (ClassCastException e) {
                g.c("PropertyValuesHolder", "No such property (" + this.b.a() + ") on target object " + obj + ". Trying reflection instead");
                this.b = null;
            }
        }
        Class cls = obj.getClass();
        if (this.c == null) {
            a(cls);
        }
        Iterator it2 = this.e.e.iterator();
        while (it2.hasNext()) {
            fVar = (f) it2.next();
            if (!fVar.a()) {
                if (this.h == null) {
                    b(cls);
                }
                try {
                    fVar.a(this.h.invoke(obj, new Object[0]));
                } catch (InvocationTargetException e2) {
                    g.c("PropertyValuesHolder", e2.toString());
                } catch (IllegalAccessException e3) {
                    g.c("PropertyValuesHolder", e3.toString());
                }
            }
        }
    }

    public j a() {
        try {
            j jVar = (j) super.clone();
            jVar.a = this.a;
            jVar.b = this.b;
            jVar.e = this.e.b();
            jVar.p = this.p;
            return jVar;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    void b(Object obj) {
        if (this.b != null) {
            this.b.a(obj, d());
        }
        if (this.c != null) {
            try {
                this.g[0] = d();
                this.c.invoke(obj, this.g);
            } catch (InvocationTargetException e) {
                g.c("PropertyValuesHolder", e.toString());
            } catch (IllegalAccessException e2) {
                g.c("PropertyValuesHolder", e2.toString());
            }
        }
    }

    void b() {
        if (this.p == null) {
            k kVar = this.d == Integer.class ? i : this.d == Float.class ? j : null;
            this.p = kVar;
        }
        if (this.p != null) {
            this.e.a(this.p);
        }
    }

    void a(float f) {
        this.q = this.e.a(f);
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(c cVar) {
        this.b = cVar;
    }

    public String c() {
        return this.a;
    }

    Object d() {
        return this.q;
    }

    public String toString() {
        return this.a + ": " + this.e.toString();
    }

    static String a(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return str;
        }
        char toUpperCase = Character.toUpperCase(str2.charAt(0));
        return str + toUpperCase + str2.substring(1);
    }
}
