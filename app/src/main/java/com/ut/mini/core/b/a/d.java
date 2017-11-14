package com.ut.mini.core.b.a;

import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* UTMCSimpleEventIDStrategier */
public class d {
    protected static final Object a = new Object();
    protected Map<String, a> b = new HashMap();
    private Random c = new Random();

    /* UTMCSimpleEventIDStrategier */
    class a {
        final /* synthetic */ d a;
        private int b;
        private int c;

        a(d dVar) {
            this.a = dVar;
        }

        public void a(int i) {
            this.b = i;
        }

        public void b(int i) {
            this.c = i;
        }
    }

    /* UTMCSimpleEventIDStrategier */
    public static class b {
        private boolean a;
        private int b;
        private boolean c = false;
        private boolean d = false;

        public void a() {
            this.d = true;
        }

        public boolean b() {
            return this.d;
        }

        public void a(boolean z) {
            this.a = z;
        }

        public int c() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        public void b(boolean z) {
            this.c = z;
        }

        public boolean d() {
            return this.c;
        }
    }

    public void a(int i, int i2) {
        synchronized (a) {
            if (i2 >= 0) {
                String str = "" + i;
                if (this.b.get(str) != null) {
                    this.b.remove(str);
                }
                a aVar = new a(this);
                aVar.a(i);
                aVar.b(i2);
                this.b.put(str, aVar);
            }
        }
    }

    public b a(a aVar) {
        b bVar = new b();
        bVar.b(true);
        if (aVar != null && aVar.c <= 100 && aVar.c > 0) {
            bVar.a(aVar.c);
            if (this.c.nextInt(100) % 100 < aVar.c) {
                bVar.a(true);
                bVar.a();
                return bVar;
            }
        }
        bVar.a(false);
        return bVar;
    }

    public b a(int i) {
        b bVar;
        synchronized (a) {
            if (i < 0) {
                bVar = new b();
                bVar.a(false);
            } else {
                a aVar = (a) this.b.get("" + i);
                if (aVar != null) {
                    bVar = a(aVar);
                } else {
                    aVar = (a) this.b.get("" + (i - (i % 100)));
                    if (aVar != null) {
                        bVar = a(aVar);
                    } else {
                        aVar = (a) this.b.get("" + (i - (i % 1000)));
                        if (aVar != null) {
                            bVar = a(aVar);
                        } else {
                            if (i > 20000) {
                                aVar = (a) this.b.get("-3");
                                if (aVar != null) {
                                    bVar = a(aVar);
                                }
                            }
                            if (i > 10000) {
                                aVar = (a) this.b.get("-2");
                                if (aVar != null) {
                                    bVar = a(aVar);
                                }
                            }
                            aVar = (a) this.b.get(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
                            if (aVar == null) {
                                bVar = new b();
                                bVar.a(false);
                                bVar.a(0);
                            } else {
                                bVar = a(aVar);
                            }
                        }
                    }
                }
            }
        }
        return bVar;
    }
}
