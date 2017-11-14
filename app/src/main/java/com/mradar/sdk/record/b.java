package com.mradar.sdk.record;

import android.content.Context;
import java.io.File;
import java.util.concurrent.Future;

/* DoresoManager */
public class b {
    private Context a;
    private String b;
    private a c;
    private Future<?> d;
    private k e;
    private i f;
    private j g;
    private long h;
    private int i;

    public b(Context context, String str) {
        this.a = context;
        this.b = str;
        if (!f.h) {
            new d(this.a).execute(new Void[0]);
        }
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(File file) {
        if (this.i == 0) {
            this.i = 1;
            if (this.g != null) {
                this.g.b();
                this.g = null;
                this.d.cancel(false);
            }
            this.g = new j(this.a, this.b, this.h, file, this.c);
            this.d = h.a().b().submit(this.g);
            if (!f.h) {
                new d(this.a).execute(new Void[0]);
            }
        }
    }

    public void a() {
        if (this.i == 1) {
            if (this.g != null) {
                this.g.a();
            }
        } else if (this.i == 2) {
            if (this.e != null) {
                this.e.a();
            }
        } else if (this.i == 3 && this.f != null) {
            this.f.a();
        }
        this.i = 0;
    }

    public void b() {
        if (this.i == 1) {
            if (this.g != null) {
                this.g.b();
            }
        } else if (this.i == 2) {
            if (this.e != null) {
                this.e.b();
            }
        } else if (this.i == 3 && this.f != null) {
            this.f.b();
        }
        this.i = 0;
    }
}
