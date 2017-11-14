package c.a;

import android.support.v4.os.EnvironmentCompat;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* AbstractIdTracker */
public abstract class a {
    private final int a = 10;
    private final int b = 20;
    private final String c;
    private List<o> d;
    private p e;

    public abstract String f();

    public a(String str) {
        this.c = str;
    }

    public boolean a() {
        return g();
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        if (this.e == null || this.e.d() <= 20) {
            return true;
        }
        return false;
    }

    private boolean g() {
        p pVar = this.e;
        String a = pVar == null ? null : pVar.a();
        int d = pVar == null ? 0 : pVar.d();
        String a2 = a(f());
        if (a2 == null || a2.equals(a)) {
            return false;
        }
        if (pVar == null) {
            pVar = new p();
        }
        pVar.a(a2);
        pVar.a(System.currentTimeMillis());
        pVar.a(d + 1);
        o oVar = new o();
        oVar.a(this.c);
        oVar.c(a2);
        oVar.b(a);
        oVar.a(pVar.b());
        if (this.d == null) {
            this.d = new ArrayList(2);
        }
        this.d.add(oVar);
        if (this.d.size() > 10) {
            this.d.remove(0);
        }
        this.e = pVar;
        return true;
    }

    public p d() {
        return this.e;
    }

    public List<o> e() {
        return this.d;
    }

    public void a(List<o> list) {
        this.d = list;
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0 || FeedbackItem.STATUS_WAITING.equals(trim) || EnvironmentCompat.MEDIA_UNKNOWN.equals(trim.toLowerCase(Locale.US))) {
            return null;
        }
        return trim;
    }

    public void a(q qVar) {
        this.e = (p) qVar.a().get("mName");
        List<o> b = qVar.b();
        if (b != null && b.size() > 0) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            for (o oVar : b) {
                if (this.c.equals(oVar.a)) {
                    this.d.add(oVar);
                }
            }
        }
    }
}
