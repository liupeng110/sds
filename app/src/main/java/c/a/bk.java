package c.a;

import com.sds.android.cloudapi.ttpod.data.VIPPolicy.Entry;

/* TProtocolUtil */
public class bk {
    private static int a = Entry.MAX_LIMIT;

    public static void a(bg bgVar, byte b) throws ar {
        a(bgVar, b, a);
    }

    public static void a(bg bgVar, byte b, int i) throws ar {
        int i2 = 0;
        if (i <= 0) {
            throw new ar("Maximum skip depth exceeded");
        }
        switch (b) {
            case (byte) 2:
                bgVar.p();
                return;
            case (byte) 3:
                bgVar.q();
                return;
            case (byte) 4:
                bgVar.u();
                return;
            case (byte) 6:
                bgVar.r();
                return;
            case (byte) 8:
                bgVar.s();
                return;
            case (byte) 10:
                bgVar.t();
                return;
            case (byte) 11:
                bgVar.w();
                return;
            case (byte) 12:
                bgVar.f();
                while (true) {
                    bd h = bgVar.h();
                    if (h.b == (byte) 0) {
                        bgVar.g();
                        return;
                    } else {
                        a(bgVar, h.b, i - 1);
                        bgVar.i();
                    }
                }
            case (byte) 13:
                bf j = bgVar.j();
                while (i2 < j.c) {
                    a(bgVar, j.a, i - 1);
                    a(bgVar, j.b, i - 1);
                    i2++;
                }
                bgVar.k();
                return;
            case (byte) 14:
                bl n = bgVar.n();
                while (i2 < n.b) {
                    a(bgVar, n.a, i - 1);
                    i2++;
                }
                bgVar.o();
                return;
            case (byte) 15:
                be l = bgVar.l();
                while (i2 < l.b) {
                    a(bgVar, l.a, i - 1);
                    i2++;
                }
                bgVar.m();
                return;
            default:
                return;
        }
    }
}
