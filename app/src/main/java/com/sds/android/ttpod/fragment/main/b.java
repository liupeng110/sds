package com.sds.android.ttpod.fragment.main;

import android.widget.ImageView;
import com.sds.android.ttpod.framework.a.g;
import java.util.ArrayList;
import java.util.Iterator;

/* DelayRequestImage */
public class b {
    private ArrayList<a> a = new ArrayList();

    /* DelayRequestImage */
    protected static class a {
        private ImageView a;
        private String b;
        private int c;
        private int d;
        private int e;

        public a(ImageView imageView, String str, int i, int i2, int i3) {
            this.a = imageView;
            this.b = str;
            this.c = i;
            this.d = i2;
            this.e = i3;
        }

        public void a() {
            g.a(this.a, this.b, this.c, this.d, this.e);
        }
    }

    public void a(ImageView imageView, String str, int i, int i2, int i3) {
        this.a.add(new a(imageView, str, i, i2, i3));
    }

    public void b(ImageView imageView, String str, int i, int i2, int i3) {
        new a(imageView, str, i, i2, i3).a();
    }

    public void a() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a();
        }
        this.a.clear();
    }
}
