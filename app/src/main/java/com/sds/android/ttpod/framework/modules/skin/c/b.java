package com.sds.android.ttpod.framework.modules.skin.c;

import android.support.v4.internal.view.SupportMenu;

/* Directives */
public class b {
    private int a = 0;
    private int[] b;

    /* Directives */
    public final class a {
        final /* synthetic */ b a;
        private int b;
        private int c;
        private int d;
        private int e;

        private a(b bVar) {
            this.a = bVar;
        }

        private void a(int i) {
            int i2;
            this.d = i;
            int i3 = i + 1;
            this.b = this.a.b[i];
            if (this.b == 0 || this.b == -1 || i3 >= this.a.b.length) {
                this.c = 0;
                i2 = i3;
            } else {
                i2 = i3 + 1;
                this.c = this.a.b[i3];
            }
            this.e = i2;
        }

        public boolean a() {
            return this.e < this.d + f() && this.e < this.a.b.length;
        }

        public int b() {
            int[] a = this.a.b;
            int i = this.e;
            this.e = i + 1;
            return a[i];
        }

        public boolean c() {
            return (this.b & SupportMenu.CATEGORY_MASK) != 0;
        }

        public int d() {
            return this.b;
        }

        public int e() {
            return this.d;
        }

        private int f() {
            return this.b == 0 ? 1 : this.c + 2;
        }
    }

    public b(int[] iArr) {
        this.b = iArr;
    }

    public boolean a() {
        return this.a < this.b.length && this.b[this.a] != -1;
    }

    public void a(a aVar) {
        aVar.a(this.a);
        this.a += aVar.f();
    }

    public a b() {
        return new a();
    }

    public void a(a aVar, int i) {
        aVar.a(i);
    }

    public void c() {
        this.a = 0;
    }
}
