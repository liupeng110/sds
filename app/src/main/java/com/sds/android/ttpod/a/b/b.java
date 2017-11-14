package com.sds.android.ttpod.a.b;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.a.c.a;

/* AuthHandler */
public abstract class b {
    protected Activity a;

    public abstract String a();

    public abstract void a(int i, int i2, Intent intent);

    public abstract void c(a aVar);

    public b(Activity activity) {
        this.a = activity;
    }

    public final void a(final a aVar) {
        c(new a(this) {
            final /* synthetic */ b b;

            public void a(Bundle bundle) {
                this.b.a(aVar, bundle);
            }

            public void a(String str) {
                this.b.b(aVar);
            }

            public void a() {
                aVar.a();
            }
        });
    }

    public void b(a aVar) {
        try {
            String a = a();
            if (!this.a.isFinishing() && !m.a(a)) {
                new a(this.a, a, aVar).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void a(final a aVar, final String str) {
        if (this.a != null && !this.a.isFinishing() && aVar != null) {
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ b c;

                public void run() {
                    aVar.a(str);
                }
            });
        }
    }

    protected void a(final a aVar, final Bundle bundle) {
        if (this.a != null && !this.a.isFinishing() && aVar != null) {
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ b c;

                public void run() {
                    aVar.a(bundle);
                }
            });
        }
    }
}
