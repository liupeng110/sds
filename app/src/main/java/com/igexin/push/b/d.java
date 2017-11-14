package com.igexin.push.b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.igexin.push.a.j;
import com.igexin.push.core.f;

public abstract class d extends com.igexin.a.a.d.d {
    private static final String a = j.a;
    protected SQLiteDatabase c;
    protected Cursor d;
    protected ContentValues e;
    public c f;

    public d() {
        super(1);
    }

    public d(ContentValues contentValues) {
        super(1);
        this.e = contentValues;
    }

    public abstract void a();

    public void a_() {
        super.a_();
        this.c = f.a().i().getWritableDatabase();
        a();
        if (this.f != null) {
            com.igexin.a.a.b.d.c().a((Object) this.f);
            com.igexin.a.a.b.d.c().d();
        }
    }

    public final int b() {
        return -2147483640;
    }

    public void c() {
        super.c();
        if (this.d != null && !this.d.isClosed()) {
            try {
                this.d.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void d() {
        this.z = true;
        this.U = true;
    }

    protected void e() {
    }
}
