package com.b.a.b.a;

import com.b.a.c.a;
import com.b.a.d.b;
import com.b.a.d.c;
import com.b.a.f;
import com.b.a.t;
import com.b.a.w;
import com.b.a.x;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* TimeTypeAdapter */
public final class k extends w<Time> {
    public static final x a = new x() {
        public <T> w<T> a(f fVar, a<T> aVar) {
            return aVar.a() == Time.class ? new k() : null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");

    public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
        return a(aVar);
    }

    public synchronized Time a(com.b.a.d.a aVar) throws IOException {
        Time time;
        if (aVar.f() == b.NULL) {
            aVar.j();
            time = null;
        } else {
            try {
                time = new Time(this.b.parse(aVar.h()).getTime());
            } catch (Throwable e) {
                throw new t(e);
            }
        }
        return time;
    }

    public synchronized void a(c cVar, Time time) throws IOException {
        cVar.b(time == null ? null : this.b.format(time));
    }
}
