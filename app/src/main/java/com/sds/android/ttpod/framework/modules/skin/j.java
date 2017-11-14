package com.sds.android.ttpod.framework.modules.skin;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.TextUtils.SimpleStringSplitter;
import com.sds.android.sdk.lib.util.b;
import com.sds.android.ttpod.framework.modules.skin.b.aa;
import com.sds.android.ttpod.framework.modules.skin.b.h;
import com.sds.android.ttpod.framework.modules.skin.b.n;
import com.sds.android.ttpod.framework.modules.skin.b.x;
import com.sds.android.ttpod.framework.modules.skin.d.a;
import com.sds.android.ttpod.framework.modules.skin.d.c;
import com.sds.android.ttpod.framework.modules.skin.d.e;
import com.sds.android.ttpod.framework.modules.skin.d.k;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/* SkinCache */
public class j extends p {
    private HashMap<String, Bitmap> a = new HashMap();
    private HashMap<String, e> c = new HashMap();
    private HashMap<String, Typeface> d = new HashMap();
    private String e = null;
    private aa f = null;
    private x g = null;
    private long h = 0;
    private String i;
    private int j;

    public j(String str) {
        a(str);
    }

    public void a(String str) {
        if (!TextUtils.equals(str, this.e)) {
            i();
            this.e = str;
            if (str.startsWith("assets://")) {
                this.j = 1;
                this.i = str.substring("assets://".length());
            } else if (str.startsWith("package://")) {
                this.j = 2;
                this.i = str.substring("package://".length());
            } else if (str.startsWith("file://")) {
                this.j = 0;
                this.i = str.substring("file://".length());
            } else {
                this.j = 0;
                this.i = str;
            }
            m();
        }
    }

    private void m() {
        if (this.j == 0) {
            this.h = new File(this.i).lastModified();
        }
    }

    public String a() {
        return this.e;
    }

    public aa b() {
        return this.f;
    }

    public long c() {
        return this.h;
    }

    public boolean d() {
        return this.f != null;
    }

    public Typeface a(n nVar) {
        if (nVar == null || this.d == null) {
            return null;
        }
        Object valueOf;
        String b = nVar.b();
        int max = Math.max(0, nVar.c());
        if (b == null) {
            valueOf = String.valueOf(max);
        } else {
            String str = b + max;
        }
        Typeface typeface = (Typeface) this.d.get(valueOf);
        if (typeface != null) {
            return typeface;
        }
        if (b != null) {
            if (b.startsWith("file://")) {
                typeface = Typeface.createFromFile(b);
            }
        } else if (max > 0) {
            typeface = Typeface.create(b, max);
        }
        if (typeface == null) {
            return typeface;
        }
        this.d.put(valueOf, typeface);
        if (max != typeface.getStyle()) {
            return Typeface.create(typeface, max);
        }
        return typeface;
    }

    public final e a(h hVar) {
        if (hVar == null) {
            return null;
        }
        if (!hVar.i()) {
            return b(hVar.c());
        }
        e kVar = new k();
        kVar.a(com.sds.android.ttpod.framework.modules.skin.d.n.x, b(hVar.e()));
        kVar.a(com.sds.android.ttpod.framework.modules.skin.d.n.b, b(hVar.f()));
        kVar.a(com.sds.android.ttpod.framework.modules.skin.d.n.d, b(hVar.h()));
        kVar.a(com.sds.android.ttpod.framework.modules.skin.d.n.c, b(hVar.g()));
        kVar.a(com.sds.android.ttpod.framework.modules.skin.d.n.a, b(hVar.d()));
        return kVar;
    }

    public final e b(String str) {
        if (str == null || this.c == null) {
            return null;
        }
        e eVar = (e) this.c.get(str);
        if (eVar != null) {
            return eVar;
        }
        String substring;
        String substring2;
        int lastIndexOf = str.lastIndexOf(124);
        if (lastIndexOf > 0) {
            substring = str.substring(0, lastIndexOf);
            substring2 = str.substring(lastIndexOf + 1);
        } else {
            substring2 = null;
            substring = str;
        }
        Bitmap bitmap = (Bitmap) this.a.get(substring);
        if (bitmap == null) {
            bitmap = c(substring);
        }
        if (bitmap != null) {
            this.a.put(substring, bitmap);
            e aVar = new a(bitmap);
            if (substring2 != null) {
                SimpleStringSplitter simpleStringSplitter = new SimpleStringSplitter(',');
                simpleStringSplitter.setString(substring2);
                if (simpleStringSplitter.hasNext()) {
                    int a = m.a(simpleStringSplitter.next(), -1);
                    if (simpleStringSplitter.hasNext()) {
                        lastIndexOf = m.a(simpleStringSplitter.next(), -1);
                    } else {
                        lastIndexOf = -1;
                    }
                    aVar.a(a, lastIndexOf);
                }
            }
            eVar = aVar;
        } else {
            eVar = new c(m.a(substring, null), m.a(substring2, 0));
        }
        this.c.put(str, eVar);
        return eVar;
    }

    public final Drawable a(Resources resources, h hVar) {
        e a = a(hVar);
        if (a != null) {
            return a.a(resources);
        }
        return null;
    }

    public final Drawable a(Resources resources, String str) {
        e b = b(str);
        if (b != null) {
            return b.a(resources);
        }
        return null;
    }

    public Bitmap c(String str) {
        Options options = new Options();
        options.inPurgeable = true;
        options.inInputShareable = true;
        if (str.startsWith("file://")) {
            return BitmapFactory.decodeFile(str.substring("file://".length()), options);
        }
        if (str.startsWith("assets://")) {
            throw new UnsupportedOperationException("not support yet");
        } else if (this.b == null || !this.b.a()) {
            return null;
        } else {
            String str2;
            int indexOf = str.indexOf(File.separatorChar);
            options.inTargetDensity = com.sds.android.ttpod.common.c.a.g();
            options.inScaled = true;
            options.inDensity = com.sds.android.ttpod.common.c.a.i();
            String h = com.sds.android.ttpod.common.c.a.h();
            if (indexOf >= 0) {
                str2 = "/" + h + str;
            } else {
                str2 = str + h;
            }
            Bitmap a = a(str2, options);
            if (a == null) {
                return a(str, options);
            }
            return a;
        }
    }

    public Bitmap a(String str, Options options) {
        Bitmap bitmap = null;
        boolean z = true;
        try {
            byte[] b = this.b.b(str);
            if (b != null) {
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(b, 0, b.length, options);
                if (b.a(options, com.sds.android.ttpod.common.c.a.d(), com.sds.android.ttpod.common.c.a.e())) {
                    bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, options);
                    if (com.sds.android.sdk.lib.util.j.d()) {
                        if (b.b(options)) {
                            z = false;
                        }
                        bitmap.setHasAlpha(z);
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return bitmap;
    }

    public byte[] d(String str) throws IOException {
        return this.b.b(str);
    }

    public Iterable<String> e() {
        return this.b;
    }

    public x f() {
        return this.f == null ? this.g : this.f.a();
    }

    public boolean g() {
        if (this.i == null || TextUtils.isEmpty(this.i)) {
            this.i = (String) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_SKIN_PROTOCOL_PATH, new Object[0]), String.class);
        }
        return this.j == 0 ? e(this.i) : a(a(this.j, this.i));
    }

    /* JADX err: Inconsistent code. */
    public void h() {
        /*
        r5 = this;
        r0 = r5.g();
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r1 = r5.k();
        if (r1 == 0) goto L_0x0006;
    L_0x000d:
        r0 = r5.a();	 Catch:{ Exception -> 0x0027 }
        r2 = r5.c();	 Catch:{ Exception -> 0x0027 }
        r4 = 65552; // 0x10010 float:9.1858E-41 double:3.2387E-319;
        r0 = com.sds.android.ttpod.framework.modules.skin.b.aa.a(r0, r2, r1, r4);	 Catch:{ Exception -> 0x0027 }
        r5.f = r0;	 Catch:{ Exception -> 0x0027 }
        r1.close();	 Catch:{ Exception -> 0x0022 }
        goto L_0x0006;
    L_0x0022:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0006;
    L_0x0027:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0034 }
        r1.close();	 Catch:{ Exception -> 0x002f }
        goto L_0x0006;
    L_0x002f:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0006;
    L_0x0034:
        r0 = move-exception;
        r1.close();	 Catch:{ Exception -> 0x0039 }
    L_0x0038:
        throw r0;
    L_0x0039:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0038;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.framework.modules.skin.j.h():void");
    }

    public void i() {
        this.a.clear();
        this.c.clear();
        this.d.clear();
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = 0;
        this.i = null;
        this.j = 0;
        j();
        System.gc();
    }

    public Drawable a(Context context) {
        if (!g()) {
            return null;
        }
        Drawable a;
        Bitmap c = c("/background.jpg");
        if (c == null) {
            a = b().a(context, this, 0);
        } else {
            a = new BitmapDrawable(c);
        }
        j();
        return a;
    }

    public Drawable b(Context context) {
        if (!g()) {
            return null;
        }
        Drawable a = b().a(context, this, 0);
        j();
        return a;
    }
}
