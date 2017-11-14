package c.a;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/* UError */
public class c extends k implements ch {
    public c() {
        a(System.currentTimeMillis());
        a(l.LEGIT);
    }

    public c(Throwable th) {
        this();
        a(a(th));
    }

    private String a(Throwable th) {
        String str = null;
        if (th != null) {
            try {
                Writer stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                th.printStackTrace(printWriter);
                for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                    cause.printStackTrace(printWriter);
                }
                str = stringWriter.toString();
                printWriter.close();
                stringWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public void a(ae aeVar, String str) {
        if (aeVar.b() > 0) {
            for (t tVar : aeVar.c()) {
                if (str.equals(tVar.a())) {
                    break;
                }
            }
        }
        t tVar2 = null;
        if (tVar2 == null) {
            tVar2 = new t();
            tVar2.a(str);
            aeVar.a(tVar2);
        }
        tVar2.a((k) this);
    }
}
