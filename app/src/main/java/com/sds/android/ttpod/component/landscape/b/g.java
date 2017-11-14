package com.sds.android.ttpod.component.landscape.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.util.Xml;
import com.sds.android.ttpod.framework.a;
import com.sds.android.ttpod.framework.storage.environment.b;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;

/* ParticleSystemWithConfig */
public class g extends b implements e {
    protected static final String g = ("landscape" + File.separator);
    private static final String n = (a.y() + File.separator + "particle_effect_configuration.xml");
    private static final String o = (g + "particle_effect_configuration.xml");
    protected int h = b.aB();
    protected ArrayList<b.b> i;
    protected ArrayList<b> j;
    protected Context k;
    private ArrayList<ArrayList<b.b>> p;

    public void a() {
        super.a();
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((ArrayList) it.next()).clear();
        }
        this.p.clear();
        this.i.clear();
        if (this.j != null) {
            this.j.clear();
        }
    }

    public g(Context context) {
        this.k = context;
        k();
        if (this.h >= this.p.size()) {
            this.h = this.p.size() - 1;
        }
        this.i = (ArrayList) this.p.get(this.h);
        l();
    }

    protected void k() {
        try {
            InputStream open = this.k.getAssets().open(o);
            this.p = a(open);
            open.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected float a(XmlPullParser xmlPullParser) throws Throwable {
        String nextText = xmlPullParser.nextText();
        if (nextText != null) {
            return Float.parseFloat(nextText);
        }
        return 0.0f;
    }

    protected void a(XmlPullParser xmlPullParser, PointF pointF) throws Throwable {
        String attributeValue = xmlPullParser.getAttributeValue(null, "x");
        if (attributeValue != null) {
            pointF.x = Float.parseFloat(attributeValue);
        }
        attributeValue = xmlPullParser.getAttributeValue(null, "y");
        if (attributeValue != null) {
            pointF.y = Float.parseFloat(attributeValue);
        }
    }

    protected void a(XmlPullParser xmlPullParser, com.sds.android.ttpod.component.landscape.d.a aVar) throws Throwable {
        String attributeValue = xmlPullParser.getAttributeValue(null, "r");
        if (attributeValue != null) {
            aVar.a(Float.parseFloat(attributeValue));
        }
        attributeValue = xmlPullParser.getAttributeValue(null, "g");
        if (attributeValue != null) {
            aVar.b(Float.parseFloat(attributeValue));
        }
        attributeValue = xmlPullParser.getAttributeValue(null, "b");
        if (attributeValue != null) {
            aVar.c(Float.parseFloat(attributeValue));
        }
        attributeValue = xmlPullParser.getAttributeValue(null, "a");
        if (attributeValue != null) {
            aVar.d(Float.parseFloat(attributeValue));
        }
    }

    protected int b(XmlPullParser xmlPullParser) throws Throwable {
        String nextText = xmlPullParser.nextText();
        if (nextText != null) {
            return Integer.parseInt(nextText);
        }
        return 0;
    }

    private void a(XmlPullParser xmlPullParser, b.b bVar, String str) throws Throwable {
        if ("life".equals(str)) {
            bVar.y = a(xmlPullParser);
        } else if ("life_var".equals(str)) {
            bVar.z = a(xmlPullParser);
        } else if ("emit_angle".equals(str)) {
            bVar.b = a(xmlPullParser);
        } else if ("emit_angle_var".equals(str)) {
            bVar.c = a(xmlPullParser);
        } else if ("emit_angle_delta".equals(str)) {
            bVar.d = a(xmlPullParser);
        } else if ("speed".equals(str)) {
            bVar.e = a(xmlPullParser);
        } else if ("speed_var".equals(str)) {
            bVar.f = a(xmlPullParser);
        } else if ("position".equals(str)) {
            a(xmlPullParser, bVar.g);
        } else if ("position_var".equals(str)) {
            a(xmlPullParser, bVar.h);
        } else if ("revolution_angle".equals(str)) {
            bVar.i = a(xmlPullParser);
        } else if ("revolution_angle_var".equals(str)) {
            bVar.j = a(xmlPullParser);
        } else if ("revolution_angle_delta".equals(str)) {
            bVar.k = a(xmlPullParser);
        } else if ("revolution_angle_delta_var".equals(str)) {
            bVar.l = a(xmlPullParser);
        } else if ("start_revolution_radius".equals(str)) {
            bVar.m = a(xmlPullParser);
        } else if ("start_revolution_radius_var".equals(str)) {
            bVar.n = a(xmlPullParser);
        } else if ("end_revolution_radius".equals(str)) {
            bVar.o = a(xmlPullParser);
        } else if ("end_revolution_radius_var".equals(str)) {
            bVar.p = a(xmlPullParser);
        } else if ("rotate_angle".equals(str)) {
            bVar.q = a(xmlPullParser);
        } else if ("rotate_angle_var".equals(str)) {
            bVar.r = a(xmlPullParser);
        } else if ("rotate_angle_delta".equals(str)) {
            bVar.s = a(xmlPullParser);
        } else if ("rotate_angle_delta_var".equals(str)) {
            bVar.t = a(xmlPullParser);
        } else if ("start_width_size".equals(str)) {
            bVar.u = a(xmlPullParser);
        } else if ("start_width_size_var".equals(str)) {
            bVar.v = a(xmlPullParser);
        } else if ("end_width_size".equals(str)) {
            bVar.w = a(xmlPullParser);
        } else if ("end_width_size_var".equals(str)) {
            bVar.x = a(xmlPullParser);
        } else if ("start_color".equals(str)) {
            a(xmlPullParser, bVar.A);
        } else if ("start_color_var".equals(str)) {
            a(xmlPullParser, bVar.B);
        } else if ("end_color".equals(str)) {
            a(xmlPullParser, bVar.C);
        } else if ("end_color_var".equals(str)) {
            a(xmlPullParser, bVar.D);
        } else if ("total_count".equals(str)) {
            bVar.E = b(xmlPullParser);
        } else if ("emit_count".equals(str)) {
            bVar.F = a(xmlPullParser);
        } else if ("particle_image".equals(str)) {
            bVar.G = xmlPullParser.nextText();
        }
    }

    private ArrayList<ArrayList<b.b>> a(InputStream inputStream) throws Throwable {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "UTF-8");
        ArrayList arrayList = null;
        ArrayList<ArrayList<b.b>> arrayList2 = null;
        b.b bVar = null;
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            switch (eventType) {
                case 0:
                    arrayList2 = new ArrayList();
                    break;
                case 2:
                    String name = newPullParser.getName();
                    if (!"particle_effect_list".equals(name)) {
                        if (arrayList != null) {
                            if (!"particle_effect".equals(name)) {
                                if (bVar == null) {
                                    break;
                                }
                                a(newPullParser, bVar, name);
                                break;
                            }
                            bVar = new b.b();
                            break;
                        }
                        break;
                    }
                    arrayList = new ArrayList();
                    break;
                case 3:
                    if (!"particle_effect".equals(newPullParser.getName())) {
                        if (!"particle_effect_list".equals(newPullParser.getName())) {
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

    protected void l() {
        if (this.j != null) {
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                ((b) it.next()).a();
            }
            this.j.clear();
        }
        this.j = new ArrayList();
        Bitmap bitmap = null;
        Iterator it2 = this.i.iterator();
        int i = 0;
        while (it2.hasNext()) {
            Bitmap decodeStream;
            b.b bVar = (b.b) it2.next();
            bVar.a = i;
            b a = a(bVar);
            try {
                decodeStream = BitmapFactory.decodeStream(this.k.getAssets().open(g + bVar.G));
            } catch (IOException e) {
                e.printStackTrace();
                decodeStream = bitmap;
            }
            a.a(decodeStream, true);
            this.j.add(a);
            i++;
            bitmap = decodeStream;
        }
    }

    protected b a(b.b bVar) {
        return new b(bVar);
    }

    public void a(float f) {
        if (this.j != null) {
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                ((b) it.next()).a(f);
            }
        }
    }

    public void b() {
        if (this.j != null) {
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                ((b) it.next()).b();
            }
        }
    }

    public synchronized void f_() {
        int size = this.p.size();
        this.h++;
        if (this.h >= size) {
            this.h = 0;
        }
        b.o(this.h);
        this.i = (ArrayList) this.p.get(this.h);
        l();
    }
}
