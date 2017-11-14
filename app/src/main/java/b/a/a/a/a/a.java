package b.a.a.a.a;

import b.a.a.a.a.a.b;
import com.sds.android.sdk.core.statistic.HttpClientProxy;

/* FormBodyPart */
public class a {
    private final String a;
    private final b b;
    private final b c;

    public a(String str, b bVar) {
        if (str == null) {
            throw new IllegalArgumentException("Name may not be null");
        } else if (bVar == null) {
            throw new IllegalArgumentException("Body may not be null");
        } else {
            this.a = str;
            this.c = bVar;
            this.b = new b();
            a(bVar);
            b(bVar);
            c(bVar);
        }
    }

    public String a() {
        return this.a;
    }

    public b b() {
        return this.c;
    }

    public b c() {
        return this.b;
    }

    public void a(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Field name may not be null");
        }
        this.b.a(new f(str, str2));
    }

    protected void a(b bVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("form-data; name=\"");
        stringBuilder.append(a());
        stringBuilder.append("\"");
        if (bVar.b() != null) {
            stringBuilder.append("; filename=\"");
            stringBuilder.append(bVar.b());
            stringBuilder.append("\"");
        }
        a("Content-Disposition", stringBuilder.toString());
    }

    protected void b(b bVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(bVar.a());
        if (bVar.c() != null) {
            stringBuilder.append("; charset=");
            stringBuilder.append(bVar.c());
        }
        a(HttpClientProxy.HEADER_CONTENT_TYPE, stringBuilder.toString());
    }

    protected void c(b bVar) {
        a("Content-Transfer-Encoding", bVar.d());
    }
}
