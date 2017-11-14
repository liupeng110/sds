package com.sds.android.ttpod.component.lockscreen.a.a;

import java.util.ArrayList;

/* Animator */
public abstract class a implements Cloneable {
    ArrayList<a> a = null;

    /* Animator */
    public interface a {
        void a(a aVar);

        void b(a aVar);

        void c(a aVar);

        void d(a aVar);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return c();
    }

    public void a() {
    }

    public void b() {
    }

    public void a(a aVar) {
        if (this.a == null) {
            this.a = new ArrayList();
        }
        this.a.add(aVar);
    }

    public a c() {
        try {
            a aVar = (a) super.clone();
            if (this.a != null) {
                ArrayList arrayList = this.a;
                aVar.a = new ArrayList();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    aVar.a.add(arrayList.get(i));
                }
            }
            return aVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
