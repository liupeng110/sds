package com.b.a.b.a;

import com.b.a.c.a;
import com.b.a.d.b;
import com.b.a.f;
import com.b.a.t;
import com.b.a.w;
import com.b.a.x;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* DateTypeAdapter */
public final class c extends w<Date> {
    public static final x a = new x() {
        public <T> w<T> a(f fVar, a<T> aVar) {
            return aVar.a() == Date.class ? new c() : null;
        }
    };
    private final DateFormat b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat c = DateFormat.getDateTimeInstance(2, 2);
    private final DateFormat d = a();

    public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
        return a(aVar);
    }

    private static DateFormat a() {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    public Date a(com.b.a.d.a aVar) throws IOException {
        if (aVar.f() != b.NULL) {
            return a(aVar.h());
        }
        aVar.j();
        return null;
    }

    private synchronized Date a(String str) {
        Date parse;
        try {
            parse = this.c.parse(str);
        } catch (ParseException e) {
            try {
                parse = this.b.parse(str);
            } catch (ParseException e2) {
                try {
                    parse = this.d.parse(str);
                } catch (Throwable e3) {
                    throw new t(str, e3);
                }
            }
        }
        return parse;
    }

    public synchronized void a(com.b.a.d.c cVar, Date date) throws IOException {
        if (date == null) {
            cVar.f();
        } else {
            cVar.b(this.b.format(date));
        }
    }
}
