package com.igexin.push.b;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.igexin.a.a.d.d;
import com.igexin.push.a.j;
import com.igexin.push.core.f;
import java.util.LinkedList;
import java.util.List;

public class a extends d {
    private static final String e = j.a;
    protected SQLiteDatabase a;
    protected Cursor b;
    List c = new LinkedList();
    boolean d;

    public a() {
        super(1);
    }

    public void a(com.igexin.push.core.c.a aVar) {
        this.c.add(aVar);
    }

    public void a_() {
        super.a_();
        this.a = f.a().i().getWritableDatabase();
        this.a.setVersion(2);
        for (com.igexin.push.core.c.a a : this.c) {
            a.a(this.a);
        }
        for (com.igexin.push.core.c.a a2 : this.c) {
            if (this.d) {
                a2.c(this.a);
            } else {
                a2.b(this.a);
            }
        }
        com.igexin.a.a.b.d.c().a((Object) new c(-980948));
        com.igexin.a.a.b.d.c().d();
    }

    public final int b() {
        return -2147483639;
    }

    public void c() {
        super.c();
        if (this.b != null) {
            try {
                this.b.close();
            } catch (Exception e) {
            }
        }
    }

    public void d() {
        super.d();
        this.z = true;
        this.U = true;
    }

    protected void e() {
    }
}
