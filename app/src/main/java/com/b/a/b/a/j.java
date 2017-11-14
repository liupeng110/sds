package com.b.a.b.a;

import com.b.a.c.a;
import com.b.a.d.b;
import com.b.a.d.c;
import com.b.a.f;
import com.b.a.t;
import com.b.a.w;
import com.b.a.x;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* SqlDateTypeAdapter */
public final class j extends w<Date> {
    public static final x a = new x() {
        public <T> w<T> a(f fVar, a<T> aVar) {
            return aVar.a() == Date.class ? new j() : null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("MMM d, yyyy");

    public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
        return a(aVar);
    }

    public synchronized Date a(com.b.a.d.a aVar) throws IOException {
        Date date;
        if (aVar.f() == b.NULL) {
            aVar.j();
            date = null;
        } else {
            try {
                date = new Date(this.b.parse(aVar.h()).getTime());
            } catch (Throwable e) {
                throw new t(e);
            }
        }
        return date;
    }

    public synchronized void a(c cVar, Date date) throws IOException {
        cVar.b(date == null ? null : this.b.format(date));
    }
}
