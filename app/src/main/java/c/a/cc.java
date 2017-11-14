package c.a;

import android.content.Context;
import com.c.a.h;
import com.c.a.i;

/* CacheService */
public final class cc implements cg {
    private static cc c;
    private cg a = new cb(this.b);
    private Context b;

    private cc(Context context) {
        this.b = context.getApplicationContext();
    }

    public static synchronized cc a(Context context) {
        cc ccVar;
        synchronized (cc.class) {
            if (c == null && context != null) {
                c = new cc(context);
            }
            ccVar = c;
        }
        return ccVar;
    }

    public void a(final ch chVar) {
        h.b(new i(this) {
            final /* synthetic */ cc a;

            public void a() {
                this.a.a.a(chVar);
            }
        });
    }

    public void b(ch chVar) {
        this.a.b(chVar);
    }

    public void a() {
        h.b(new i(this) {
            final /* synthetic */ cc a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a.a();
            }
        });
    }
}
