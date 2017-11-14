package com.sds.android.sdk.core.a;

import android.support.v4.util.LruCache;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* ObjectCache */
public final class f {
    private static f a;
    private static final ReentrantLock b = new ReentrantLock();
    private static final ReentrantReadWriteLock c = new ReentrantReadWriteLock();
    private File d;
    private boolean e = false;
    private b f = new b();
    private LruCache<String, a> g;
    private HashMap<String, a> h;

    /* ObjectCache */
    private static final class a implements Serializable {
        private Object a;
        private long b;

        private a(Object obj, long j) {
            this.a = obj;
            this.b = j;
        }

        public Object a() {
            return this.a;
        }

        public long b() {
            return this.b;
        }
    }

    /* ObjectCache */
    private final class b extends Thread {
        final /* synthetic */ f a;
        private LinkedHashMap<String, a> b;
        private long c;
        private ReentrantLock d;

        private b(f fVar) {
            this.a = fVar;
            this.b = new LinkedHashMap();
            this.c = 0;
            this.d = new ReentrantLock();
        }

        public void a(String str, a aVar) {
            this.d.lock();
            this.b.put(str, aVar);
            this.d.unlock();
        }

        private void a() {
            this.d.lock();
            LinkedHashMap linkedHashMap = new LinkedHashMap(this.b);
            this.d.unlock();
            for (String str : linkedHashMap.keySet()) {
                this.a.a(str, (a) linkedHashMap.get(str));
                this.d.lock();
                this.b.remove(str);
                this.d.unlock();
            }
        }

        private void b() {
            FileInputStream fileInputStream;
            FileInputStream fileInputStream2;
            ObjectInputStream objectInputStream;
            int i;
            Exception e;
            Throwable th;
            f.b.lock();
            File[] listFiles = this.a.d.listFiles();
            f.b.unlock();
            ObjectInputStream objectInputStream2;
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (file.lastModified() <= System.currentTimeMillis()) {
                        Object obj;
                        f.b.lock();
                        try {
                            fileInputStream = new FileInputStream(file);
                            try {
                                objectInputStream2 = new ObjectInputStream(fileInputStream);
                            } catch (ClassCastException e2) {
                                fileInputStream2 = fileInputStream;
                                objectInputStream = null;
                                try {
                                    fileInputStream2.close();
                                    objectInputStream.close();
                                    i = 1;
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                    i = 1;
                                }
                                if (obj != null) {
                                    e.c(file);
                                }
                                f.b.unlock();
                            } catch (Exception e4) {
                                e3 = e4;
                                objectInputStream2 = null;
                                try {
                                    e3.printStackTrace();
                                    try {
                                        fileInputStream.close();
                                        objectInputStream2.close();
                                        obj = null;
                                    } catch (Exception e32) {
                                        e32.printStackTrace();
                                        obj = null;
                                    }
                                    if (obj != null) {
                                        e.c(file);
                                    }
                                    f.b.unlock();
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                objectInputStream2 = null;
                            }
                            try {
                                if (((a) objectInputStream2.readObject()).b() <= System.currentTimeMillis()) {
                                    obj = 1;
                                } else {
                                    obj = null;
                                }
                                try {
                                    fileInputStream.close();
                                    objectInputStream2.close();
                                } catch (Exception e5) {
                                    e5.printStackTrace();
                                }
                            } catch (ClassCastException e6) {
                                fileInputStream2 = fileInputStream;
                                objectInputStream = objectInputStream2;
                                fileInputStream2.close();
                                objectInputStream.close();
                                i = 1;
                                if (obj != null) {
                                    e.c(file);
                                }
                                f.b.unlock();
                            } catch (Exception e7) {
                                e32 = e7;
                                e32.printStackTrace();
                                fileInputStream.close();
                                objectInputStream2.close();
                                obj = null;
                                if (obj != null) {
                                    e.c(file);
                                }
                                f.b.unlock();
                            }
                        } catch (ClassCastException e8) {
                            fileInputStream2 = null;
                            objectInputStream = null;
                            fileInputStream2.close();
                            objectInputStream.close();
                            i = 1;
                            if (obj != null) {
                                e.c(file);
                            }
                            f.b.unlock();
                        } catch (Exception e9) {
                            e32 = e9;
                            fileInputStream = null;
                            objectInputStream2 = null;
                            e32.printStackTrace();
                            fileInputStream.close();
                            objectInputStream2.close();
                            obj = null;
                            if (obj != null) {
                                e.c(file);
                            }
                            f.b.unlock();
                        } catch (Throwable th4) {
                            th = th4;
                            fileInputStream = null;
                            objectInputStream2 = null;
                        }
                        if (obj != null) {
                            e.c(file);
                        }
                        f.b.unlock();
                    }
                }
                return;
            }
            return;
            try {
                fileInputStream.close();
                objectInputStream2.close();
            } catch (Exception e10) {
                e10.printStackTrace();
            }
            throw th;
            throw th;
        }

        public void run() {
            while (!isInterrupted()) {
                try {
                    if (System.currentTimeMillis() > this.c + 1800000) {
                        if (this.c != 0) {
                            b();
                        }
                        this.c = System.currentTimeMillis();
                    }
                    a();
                    synchronized (this) {
                        if (this.b.size() <= 0) {
                            wait();
                        }
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public static synchronized f a(float f, String str) {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f(f, str);
                fVar = a;
            } else {
                throw new IllegalStateException("ObjectCache already existed!");
            }
        }
        return fVar;
    }

    private f(float f, String str) {
        this.d = e.f(str);
        if (this.d == null) {
            this.d = new File("");
        }
        if (f < 0.005f || f > TTFMImageUtils.Middle_Scale) {
            throw new IllegalArgumentException("memCacheSizePercent - percent must be between0.005and0.5 (inclusive)");
        }
        g.d("ObjectCache", "MaxSize:" + (Math.round(((float) Runtime.getRuntime().maxMemory()) * f) / 1024));
        this.g = new LruCache<String, a>(this, Math.round(((float) Runtime.getRuntime().maxMemory()) * f) / 1024) {
            final /* synthetic */ f a;

            protected /* synthetic */ int sizeOf(Object obj, Object obj2) {
                return a((String) obj, (a) obj2);
            }

            protected int a(String str, a aVar) {
                int i = 0;
                if (aVar.a() instanceof a) {
                    i = ((a) aVar.a()).a() / 1024;
                }
                return i == 0 ? 1 : i;
            }
        };
        this.h = new HashMap();
        this.f.setPriority(10);
        this.f.setDaemon(true);
        this.f.start();
    }

    public void a(String str, Object obj) {
        a(str, obj, 315360000000L);
    }

    public void a(String str, Object obj, boolean z) {
        a(str, obj, 315360000000L, z);
    }

    public void a(String str, Object obj, long j) {
        a(str, obj, j, true);
    }

    public void a(String str, Object obj, long j, boolean z) {
        try {
            c.writeLock().lock();
            if (this.e) {
                throw new IllegalStateException("Cache has been closed!");
            }
            long currentTimeMillis = System.currentTimeMillis() + j;
            if (z && (obj instanceof Serializable)) {
                a aVar = new a(obj, currentTimeMillis);
                this.g.put(str, aVar);
                synchronized (this.f) {
                    this.f.a(str, aVar);
                    this.f.notify();
                }
            } else {
                if (z) {
                    g.c("ObjectCache", "Object must be implement Serializable if can be serialized");
                }
                this.h.put(str, new a(obj, currentTimeMillis));
            }
            c.writeLock().unlock();
        } catch (Throwable th) {
            c.writeLock().unlock();
        }
    }

    public synchronized void a() {
        this.g.evictAll();
    }

    public synchronized boolean a(String str) {
        boolean z;
        z = (c(str) == null && d(str) == null) ? false : true;
        return z;
    }

    public <T> T b(String str, T t) throws Exception {
        return b(str, t, true);
    }

    public <T> T b(String str, T t, boolean z) throws Exception {
        try {
            c.readLock().lock();
            T c = c(str);
            if (z && c == null) {
                c = d(str);
            }
            if (c == null) {
                return t;
            }
            c.readLock().unlock();
            return c;
        } finally {
            c.readLock().unlock();
        }
    }

    private synchronized Object c(String str) {
        Object a;
        if (this.h.containsKey(str)) {
            if (((a) this.h.get(str)).b() >= System.currentTimeMillis()) {
                a = ((a) this.h.get(str)).a();
            } else {
                this.h.remove(str);
            }
        }
        a = null;
        return a;
    }

    private synchronized Object d(String str) {
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        Object a;
        Exception e;
        Throwable th;
        Object obj = null;
        synchronized (this) {
            if (this.g.get(str) == null) {
                b.lock();
                File file = new File(f(str));
                if (file.isFile()) {
                    Object obj2 = null;
                    try {
                        fileInputStream = new FileInputStream(file);
                        try {
                            objectInputStream = new ObjectInputStream(fileInputStream);
                            try {
                                a aVar = (a) objectInputStream.readObject();
                                if (aVar.b() > System.currentTimeMillis()) {
                                    this.g.put(str, aVar);
                                    a = aVar.a();
                                } else {
                                    int i = 1;
                                    a = null;
                                }
                                try {
                                    fileInputStream.close();
                                    objectInputStream.close();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                                if (obj2 != null) {
                                    file.delete();
                                }
                                b.unlock();
                            } catch (Exception e3) {
                                e = e3;
                                try {
                                    e.printStackTrace();
                                    try {
                                        fileInputStream.close();
                                        objectInputStream.close();
                                    } catch (Exception e4) {
                                        e4.printStackTrace();
                                    }
                                    b.unlock();
                                    a = null;
                                    obj = a;
                                    return obj;
                                } catch (Throwable th2) {
                                    th = th2;
                                    try {
                                        fileInputStream.close();
                                        objectInputStream.close();
                                    } catch (Exception e22) {
                                        e22.printStackTrace();
                                    }
                                    b.unlock();
                                    throw th;
                                }
                            }
                        } catch (Exception e5) {
                            e4 = e5;
                            objectInputStream = null;
                            e4.printStackTrace();
                            fileInputStream.close();
                            objectInputStream.close();
                            b.unlock();
                            a = null;
                            obj = a;
                            return obj;
                        } catch (Throwable th3) {
                            th = th3;
                            objectInputStream = null;
                            fileInputStream.close();
                            objectInputStream.close();
                            b.unlock();
                            throw th;
                        }
                    } catch (Exception e6) {
                        e4 = e6;
                        fileInputStream = null;
                        objectInputStream = null;
                        e4.printStackTrace();
                        fileInputStream.close();
                        objectInputStream.close();
                        b.unlock();
                        a = null;
                        obj = a;
                        return obj;
                    } catch (Throwable th4) {
                        th = th4;
                        fileInputStream = null;
                        objectInputStream = null;
                        fileInputStream.close();
                        objectInputStream.close();
                        b.unlock();
                        throw th;
                    }
                }
                b.unlock();
                a = null;
                obj = a;
            } else if (((a) this.g.get(str)).b() >= System.currentTimeMillis()) {
                obj = ((a) this.g.get(str)).a();
            }
        }
        return obj;
    }

    public synchronized void b(String str) {
        if (this.h.remove(str) == null) {
            this.g.remove(str);
            e(str);
        }
    }

    private void e(String str) {
        b.lock();
        e.h(f(str));
        b.unlock();
    }

    private String f(String str) {
        return this.d.getAbsolutePath() + File.separator + str;
    }

    private void a(String str, a aVar) {
        Exception e;
        Throwable th;
        ObjectOutputStream objectOutputStream = null;
        b.lock();
        FileOutputStream fileOutputStream;
        try {
            File file = new File(f(str));
            fileOutputStream = new FileOutputStream(file);
            try {
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(fileOutputStream);
                try {
                    objectOutputStream2.writeObject(aVar);
                    file.setLastModified(aVar.b());
                    try {
                        fileOutputStream.close();
                        objectOutputStream2.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    objectOutputStream = objectOutputStream2;
                    try {
                        e2.printStackTrace();
                        try {
                            fileOutputStream.close();
                            objectOutputStream.close();
                        } catch (Exception e22) {
                            e22.printStackTrace();
                        }
                        b.unlock();
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            fileOutputStream.close();
                            objectOutputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    objectOutputStream = objectOutputStream2;
                    fileOutputStream.close();
                    objectOutputStream.close();
                    throw th;
                }
            } catch (Exception e5) {
                e22 = e5;
                e22.printStackTrace();
                fileOutputStream.close();
                objectOutputStream.close();
                b.unlock();
            }
        } catch (Exception e6) {
            e22 = e6;
            fileOutputStream = null;
            e22.printStackTrace();
            fileOutputStream.close();
            objectOutputStream.close();
            b.unlock();
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            fileOutputStream.close();
            objectOutputStream.close();
            throw th;
        }
        b.unlock();
    }
}
