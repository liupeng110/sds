package com.sds.android.ttpod.component.landscape.b;

import java.util.ArrayList;
import java.util.Iterator;

/* Node */
public class f {
    protected ArrayList<f> e = new ArrayList(5);
    protected int f;

    public void a(f fVar, int i) {
        int size = this.e.size();
        if (size == 0 || ((f) this.e.get(size - 1)).f <= i) {
            this.e.add(fVar);
        } else {
            Iterator it = this.e.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                if (((f) it.next()).f > i) {
                    this.e.add(i2, fVar);
                    break;
                }
                i2++;
            }
        }
        fVar.f = i;
    }

    public void a() {
        if (this.e != null) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                ((f) it.next()).a();
            }
        }
    }

    public void j() {
    }
}
