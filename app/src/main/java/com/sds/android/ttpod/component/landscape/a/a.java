package com.sds.android.ttpod.component.landscape.a;

import com.sds.android.ttpod.component.landscape.b.i;

/* Action */
public class a implements Cloneable {
    protected i a;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return b();
    }

    protected a() {
    }

    public boolean a() {
        return true;
    }

    public void a(i iVar) {
        this.a = iVar;
    }

    public void a(float f) {
    }

    protected void b(float f) {
    }

    public a b() {
        try {
            return (a) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
