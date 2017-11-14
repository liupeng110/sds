package com.sds.android.ttpod.component.landscape.b;

import android.content.Context;
import android.util.Xml;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;

/* TTPodParticleSystem */
public class k extends g {
    private static final String n = (com.sds.android.ttpod.framework.a.y() + File.separator + "ttpod_particle_effect_configuration.xml");
    private static final String o = (g + "ttpod_particle_effect_configuration.xml");
    private ArrayList<b> p = ((ArrayList) this.q.get(this.h));
    private ArrayList<ArrayList<b>> q;
    private float r;
    private float s;

    /* TTPodParticleSystem */
    private class a extends b {
        final /* synthetic */ k g;
        private com.sds.android.ttpod.component.landscape.b.b.b h;
        private b i;

        public a(k kVar, com.sds.android.ttpod.component.landscape.b.b.b bVar) {
            this.g = kVar;
            super(bVar);
            this.h = new com.sds.android.ttpod.component.landscape.b.b.b(bVar);
        }

        protected com.sds.android.ttpod.component.landscape.b.b.b h() {
            return this.h;
        }

        public void a(float f) {
            if (this.i == null) {
                this.i = this.g.c(this.d.a);
            }
            if (this.i != null) {
                float c;
                if (this.i.a) {
                    this.h.y = this.d.y * this.g.r;
                }
                if (this.i.c) {
                    this.h.e = (this.d.e * this.g.r) * this.g.s;
                }
                if (this.i.d > 1.0f) {
                    c = this.d.b - this.i.d;
                    this.h.b = c + ((this.i.d * this.g.r) * 2.0f);
                }
                if (this.i.e) {
                    this.h.d = this.d.d * this.g.r;
                }
                if (this.i.f > 1.0f) {
                    c = this.d.g.x - this.i.f;
                    this.h.g.x = c + ((this.i.f * this.g.r) * 2.0f);
                } else if (this.i.g > 1.0f) {
                    c = this.d.g.y - this.i.g;
                    this.h.g.y = c + ((this.i.g * this.g.r) * 2.0f);
                }
                if (this.i.h) {
                    this.h.k = this.d.k * this.g.r;
                }
                if (this.i.i && this.d.m != this.d.o) {
                    c = Math.min(this.d.m, this.d.o);
                    c += (Math.max(this.d.m, this.d.o) - c) * this.g.r;
                    this.h.m = c;
                    this.h.o = c;
                }
                if (this.i.j) {
                    this.h.s = this.d.s * this.g.r;
                }
                if (this.i.k && this.d.u != this.d.w) {
                    c = Math.min(this.d.u, this.d.w);
                    c += (Math.max(this.d.u, this.d.w) - c) * this.g.r;
                    this.h.u = c;
                    this.h.w = c;
                }
                super.a(f);
            }
        }

        protected float b(float f) {
            if (this.i.b) {
                return ((this.d.F * f) * this.g.r) + this.c;
            }
            return super.b(f);
        }
    }

    /* TTPodParticleSystem */
    private static class b {
        private boolean a;
        private boolean b;
        private boolean c;
        private float d;
        private boolean e;
        private float f;
        private float g;
        private boolean h;
        private boolean i;
        private boolean j;
        private boolean k;

        private b() {
        }
    }

    public k(Context context) {
        super(context);
        o();
    }

    protected b a(com.sds.android.ttpod.component.landscape.b.b.b bVar) {
        return new a(this, bVar);
    }

    protected void o() {
        try {
            InputStream open = this.k.getAssets().open(o);
            this.q = a(open);
            open.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private ArrayList<ArrayList<b>> a(InputStream inputStream) throws Throwable {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "UTF-8");
        ArrayList arrayList = null;
        ArrayList<ArrayList<b>> arrayList2 = null;
        b bVar = null;
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            switch (eventType) {
                case 0:
                    arrayList2 = new ArrayList();
                    break;
                case 2:
                    String name = newPullParser.getName();
                    if (!"ttpod_particle_effect_list".equals(name)) {
                        if (arrayList != null) {
                            if (!"ttpod_particle_effect".equals(name)) {
                                if (bVar != null) {
                                    if (!"impact_emit_count".equals(name)) {
                                        if (!"impact_life".equals(name)) {
                                            if (!"impact_speed".equals(name)) {
                                                if (!"impact_emit_angle".equals(name)) {
                                                    if (!"impact_emit_angle_delta".equals(name)) {
                                                        if (!"impact_position_x".equals(name)) {
                                                            if (!"impact_position_y".equals(name)) {
                                                                if (!"impact_revolution_angle".equals(name)) {
                                                                    if (!"impact_revolution_radius".equals(name)) {
                                                                        if (!"impact_rotate_angle".equals(name)) {
                                                                            if (!"impact_size".equals(name)) {
                                                                                break;
                                                                            }
                                                                            bVar.k = true;
                                                                            break;
                                                                        }
                                                                        bVar.j = true;
                                                                        break;
                                                                    }
                                                                    bVar.i = true;
                                                                    break;
                                                                }
                                                                bVar.h = true;
                                                                break;
                                                            }
                                                            bVar.g = a(newPullParser);
                                                            break;
                                                        }
                                                        bVar.f = a(newPullParser);
                                                        break;
                                                    }
                                                    bVar.e = true;
                                                    break;
                                                }
                                                bVar.d = a(newPullParser);
                                                break;
                                            }
                                            bVar.c = true;
                                            break;
                                        }
                                        bVar.a = true;
                                        break;
                                    }
                                    bVar.b = true;
                                    break;
                                }
                                break;
                            }
                            bVar = new b();
                            break;
                        }
                        break;
                    }
                    arrayList = new ArrayList();
                    break;
                case 3:
                    if (!"ttpod_particle_effect".equals(newPullParser.getName())) {
                        if (!"ttpod_particle_effect_list".equals(newPullParser.getName())) {
                            break;
                        }
                        arrayList2.add(arrayList);
                        arrayList = null;
                        break;
                    }
                    arrayList.add(bVar);
                    bVar = null;
                    break;
                default:
                    break;
            }
        }
        return arrayList2;
    }

    private b c(int i) {
        if (this.p == null) {
            return null;
        }
        if (i < this.p.size()) {
            return (b) this.p.get(i);
        }
        return (b) this.p.get(0);
    }

    public void a(int i) {
        this.s = ((float) i) / 800.0f;
    }

    public void c(float f) {
        this.r = 0.0f;
        if (f > 0.0f) {
            this.r = f / 80.0f;
            if (this.r > 1.0f) {
                this.r = 1.0f;
            }
        }
    }

    public void f_() {
        super.f_();
        int i = 0;
        if (this.q.size() > this.h) {
            i = this.h;
        }
        this.p = (ArrayList) this.q.get(i);
    }

    public void a() {
        super.a();
        Iterator it = this.q.iterator();
        while (it.hasNext()) {
            ((ArrayList) it.next()).clear();
        }
        this.q.clear();
        this.p.clear();
    }
}
