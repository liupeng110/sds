package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import com.sds.android.ttpod.framework.modules.skin.j;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/* SerializableSkin */
public class aa implements a<g>, Serializable {
    private String a;
    private long b;
    private x c;
    private int d;
    private g[] e;

    /* SerializableSkin */
    private static class a {
        private final ArrayList a = new ArrayList(10);
        private final ArrayList<m> b = new ArrayList(10);
        private a c;

        a(a aVar) {
            this.c = aVar;
        }

        void a(Object obj) {
            if (obj instanceof m) {
                this.b.add((m) obj);
            } else {
                this.a.add(obj);
            }
        }

        void a() {
            int size = this.a.size();
            if (size <= 0) {
                this.c.a(null);
            } else if (this.c instanceof t) {
                if (this.a.get(0) instanceof s) {
                    s[] sVarArr = new s[size];
                    this.a.toArray(sVarArr);
                    this.c.a(sVarArr);
                }
            } else if ((this.c instanceof l) || (this.c instanceof s) || (this.c instanceof k)) {
                j[] jVarArr = new j[size];
                this.a.toArray(jVarArr);
                this.c.a(jVarArr);
            } else if (this.c instanceof m) {
                r[] rVarArr = new r[size];
                this.a.toArray(rVarArr);
                this.c.a(rVarArr);
            } else if (this.c instanceof aa) {
                g[] gVarArr = new g[size];
                this.a.toArray(gVarArr);
                this.c.a(gVarArr);
            } else {
                this.c.a(null);
            }
            if (this.c instanceof b) {
                m[] mVarArr;
                size = this.b.size();
                if (size > 0) {
                    m[] mVarArr2 = new m[size];
                    this.b.toArray(mVarArr2);
                    mVarArr = mVarArr2;
                } else {
                    mVarArr = null;
                }
                ((b) this.c).a(mVarArr);
            }
        }
    }

    public aa(String str, long j, int i) {
        this.a = str;
        this.b = j;
        this.d = i;
    }

    public void a(com.sds.android.ttpod.framework.modules.search.a.a aVar) {
        this.c = new x(aVar);
    }

    public void a(g[] gVarArr) {
        this.e = gVarArr;
    }

    public x a() {
        return this.c == null ? null : new x(this.c);
    }

    public g a(String str, int i) {
        if (this.e == null || this.e.length <= 0) {
            return null;
        }
        g gVar;
        g[] gVarArr = this.e;
        int length = gVarArr.length;
        int i2 = 0;
        g gVar2 = null;
        while (i2 < length) {
            gVar = gVarArr[i2];
            if (!TextUtils.equals(str, gVar.a)) {
                gVar = gVar2;
            } else if (gVar.c == i) {
                gVar2 = gVar;
                break;
            }
            i2++;
            gVar2 = gVar;
        }
        gVar = gVar2;
        gVar2 = null;
        return gVar2 == null ? gVar : gVar2;
    }

    public t a(int i) {
        g a = a("Player", i);
        if (a instanceof t) {
            return (t) a;
        }
        return null;
    }

    public u b(int i) {
        g a = a("Playlist", i);
        if (a instanceof u) {
            return (u) a;
        }
        return null;
    }

    public Drawable a(Context context, j jVar, int i) {
        Drawable a;
        if (this.c != null) {
            a = jVar.a(context.getResources(), this.c.f);
        } else {
            a = null;
        }
        if (!a(a)) {
            t a2 = a(i);
            if (a2 != null) {
                a = a2.d(context, jVar);
            }
        }
        if (a != null) {
            return a.getConstantState().newDrawable();
        }
        return null;
    }

    private boolean a(Drawable drawable) {
        return (drawable == null || ((drawable instanceof ColorDrawable) && ((ColorDrawable) drawable).getAlpha() == 0)) ? false : true;
    }

    public static x a(Reader reader) throws Exception {
        com.sds.android.ttpod.framework.modules.search.a.a aVar = new com.sds.android.ttpod.framework.modules.search.a.a();
        aVar.setInput(reader);
        aVar.nextTag();
        aVar.require(2, null, "Theme");
        return new x(aVar);
    }

    public static aa a(String str, long j, Reader reader, int i) throws Exception {
        Object aaVar = new aa(str, j, i);
        com.sds.android.ttpod.framework.modules.search.a.a aVar = new com.sds.android.ttpod.framework.modules.search.a.a();
        aVar.setInput(reader);
        aVar.nextTag();
        aVar.require(2, null, "Theme");
        aaVar.a(aVar);
        int i2 = aaVar.c.h;
        ArrayList arrayList = new ArrayList(10);
        HashMap hashMap = new HashMap(10);
        HashMap hashMap2 = new HashMap(10);
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray(10);
        arrayList.add(new a(aaVar));
        sparseBooleanArray.put(aVar.getDepth(), true);
        int next;
        do {
            next = aVar.next();
            int size;
            switch (next) {
                case 2:
                    String name = aVar.getName();
                    if (!"Bitmap".equals(name)) {
                        if (!"Font".equals(name)) {
                            size = arrayList.size();
                            a aVar2 = size > 0 ? (a) arrayList.get(size - 1) : null;
                            if (aVar2 != null) {
                                Object a;
                                if ("View".equals(name)) {
                                    a = a(aVar, hashMap, i2);
                                } else if ("Panel".equals(name)) {
                                    a = new s(aVar);
                                } else {
                                    a = a(name, aVar, hashMap, hashMap2, i2);
                                    if (a == null) {
                                        if ("Event".equals(name)) {
                                            a = new m(aVar);
                                        } else if ("Motion".equals(name)) {
                                            a = new r(aVar);
                                        }
                                    }
                                }
                                if (a != null) {
                                    aVar2.a(a);
                                    if (a instanceof a) {
                                        arrayList.add(new a((a) a));
                                        sparseBooleanArray.put(aVar.getDepth(), true);
                                        break;
                                    }
                                }
                            }
                        }
                        n nVar = new n(aVar);
                        hashMap2.put(nVar.e, nVar);
                        break;
                    }
                    h hVar = new h(aVar);
                    hashMap.put(hVar.a, hVar);
                    break;
                    break;
                case 3:
                    int depth = aVar.getDepth();
                    if (sparseBooleanArray.get(depth)) {
                        size = arrayList.size();
                        a aVar3 = size > 0 ? (a) arrayList.remove(size - 1) : null;
                        if (aVar3 != null) {
                            aVar3.a();
                        }
                        sparseBooleanArray.put(depth, false);
                        break;
                    }
                    break;
            }
        } while (next != 1);
        return aaVar;
    }

    private static g a(com.sds.android.ttpod.framework.modules.search.a.a aVar, HashMap<String, h> hashMap, int i) {
        if ("Playlist".equals(aVar.getAttributeValue(null, "ID"))) {
            return new u(aVar, hashMap, i);
        }
        return new t(aVar, hashMap, i);
    }

    private static j a(String str, com.sds.android.ttpod.framework.modules.search.a.a aVar, HashMap<String, h> hashMap, HashMap<String, n> hashMap2, int i) {
        if ("Button".equals(str)) {
            return new i(aVar, hashMap, i);
        }
        if ("Region".equals(str)) {
            return new v(aVar, hashMap, i);
        }
        if ("Text".equals(str)) {
            return new w(aVar, hashMap, hashMap2, i);
        }
        if ("Icon".equals(str)) {
            return new o(aVar, hashMap, i);
        }
        if ("Slide".equals(str)) {
            return new y(aVar, hashMap, i);
        }
        if ("Animation".equals(str)) {
            return new e(aVar, hashMap, i);
        }
        if ("LyricShow".equals(str)) {
            return new q(aVar, hashMap, hashMap2, i);
        }
        if ("Analyzer".equals(str)) {
            return new c(aVar, hashMap, i);
        }
        if ("Image".equals(str)) {
            return new d(aVar, hashMap, i);
        }
        if ("ComponentGroup".equals(str)) {
            return new k(aVar, hashMap, i);
        }
        return null;
    }
}
