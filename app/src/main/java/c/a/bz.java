package c.a;

import android.content.Context;

/* SDKContext */
public class bz {
    private static bi a = null;
    private static bx b = null;

    public static synchronized bi a(Context context) {
        bi biVar;
        synchronized (bz.class) {
            if (a == null) {
                a = new bi(context);
                a.a(new bw(context));
                a.a(new by(context));
                a.a(new y(context));
                a.a(new ca(context));
                a.d();
            }
            biVar = a;
        }
        return biVar;
    }

    public static synchronized bx b(Context context) {
        bx bxVar;
        synchronized (bz.class) {
            if (b == null) {
                b = new bx(context);
                b.b();
            }
            bxVar = b;
        }
        return bxVar;
    }
}
