package com.igexin.push.d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.igexin.a.a.b.d;
import com.igexin.push.c.b;
import com.igexin.push.c.c.a;
import com.igexin.push.c.c.c;
import com.igexin.push.c.c.e;
import com.igexin.push.c.c.f;
import com.igexin.push.c.c.i;
import com.igexin.push.c.c.k;
import com.igexin.push.c.c.l;
import com.igexin.push.c.c.m;
import com.igexin.push.c.c.n;
import com.igexin.push.c.c.o;
import com.igexin.push.core.c.r;
import com.igexin.push.core.g;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.tencent.open.SocialConstants;
import java.text.SimpleDateFormat;
import java.util.Date;

public class j {
    private static String a = "SNL";
    private Context b;
    private d c;
    private k d;
    private boolean e = false;
    private long f = 0;
    private long g = 0;
    private boolean h = false;

    private long a(long j) {
        long j2 = j / 10;
        return ((long) (((Math.random() * ((double) j2)) * 2.0d) - ((double) j2))) + j;
    }

    private String b(e eVar) {
        String str = "";
        if (eVar instanceof f) {
            return "R-" + ((f) eVar).a();
        }
        if (eVar instanceof o) {
            return "R-" + ((o) eVar).b;
        }
        if (eVar instanceof i) {
            return "S-" + String.valueOf(((i) eVar).a);
        }
        if (eVar instanceof k) {
            if (((k) eVar).e != 0) {
                return "S-" + String.valueOf(((k) eVar).e);
            }
        } else if (eVar instanceof l) {
            return "S-" + String.valueOf(((l) eVar).a);
        } else {
            if (eVar instanceof m) {
                return "S-" + String.valueOf(((m) eVar).e);
            }
            if (eVar instanceof com.igexin.push.c.c.d) {
                return "C-" + ((com.igexin.push.c.c.d) eVar).g;
            }
            if (eVar instanceof n) {
                return "C-" + ((n) eVar).g;
            }
            if (eVar instanceof a) {
                return "C-" + ((a) eVar).d;
            }
            if (eVar instanceof c) {
                return "C-" + ((c) eVar).d;
            }
        }
        return str;
    }

    private boolean d() {
        if (com.igexin.push.a.k.s && this.f + this.g >= com.igexin.push.a.k.t) {
            a aVar = new a();
            aVar.a(com.igexin.push.core.c.check);
            com.igexin.push.core.f.a().f().a(aVar);
        }
        return false;
    }

    private void e() {
        Cursor cursor;
        Throwable th;
        Cursor a;
        try {
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            a = com.igexin.push.core.f.a().i().a("bi", new String[]{SocialConstants.PARAM_TYPE}, new String[]{"1"}, null, null);
            if (a != null) {
                try {
                    if (a.getCount() == 0) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("loginerror_connecterror_count", Integer.valueOf(1));
                        contentValues.put("create_time", format);
                        contentValues.put(SocialConstants.PARAM_TYPE, "1");
                        com.igexin.push.core.f.a().i().a("bi", contentValues);
                    } else {
                        int i = 0;
                        while (a.moveToNext()) {
                            String string = a.getString(a.getColumnIndexOrThrow("create_time"));
                            String string2 = a.getString(a.getColumnIndexOrThrow(StarCategory.KEY_STAR_CATEGORY_ID));
                            ContentValues contentValues2;
                            if (format.equals(string)) {
                                i = a.getInt(a.getColumnIndexOrThrow("loginerror_connecterror_count"));
                                contentValues2 = new ContentValues();
                                contentValues2.put("loginerror_connecterror_count", Integer.valueOf(i + 1));
                                com.igexin.push.core.f.a().i().a("bi", contentValues2, new String[]{StarCategory.KEY_STAR_CATEGORY_ID}, new String[]{string2});
                            } else {
                                contentValues2 = new ContentValues();
                                contentValues2.put(SocialConstants.PARAM_TYPE, FeedbackItem.STATUS_SOLVED);
                                com.igexin.push.core.f.a().i().a("bi", contentValues2, new String[]{StarCategory.KEY_STAR_CATEGORY_ID}, new String[]{string2});
                                contentValues2 = new ContentValues();
                                contentValues2.put("loginerror_connecterror_count", Integer.valueOf(i + 1));
                                contentValues2.put("create_time", format);
                                contentValues2.put(SocialConstants.PARAM_TYPE, "1");
                                com.igexin.push.core.f.a().i().a("bi", contentValues2);
                            }
                        }
                    }
                } catch (Exception e) {
                    cursor = a;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (a != null) {
                a.close();
            }
        } catch (Exception e2) {
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th3) {
            th = th3;
            a = null;
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    public int a(String str, e eVar) {
        if (str == null || eVar == null) {
            return -1;
        }
        if (!this.e) {
            return com.igexin.push.core.f.a().f().a(str, eVar);
        }
        if (eVar.i == 6 || eVar.i == 4 || eVar.i == 36 || eVar.i == 0) {
            int i = 15;
            if (com.igexin.push.a.k.i > 0) {
                i = com.igexin.push.a.k.i;
            }
            if (this.c.a(g.a, 3, com.igexin.push.core.f.a().d(), eVar, true, i, new b()) == null) {
                return -2;
            }
        } else if (this.c.a(g.a, 3, com.igexin.push.core.f.a().d(), eVar, true) == null) {
            return -2;
        }
        byte[] d = eVar.d();
        if (d != null) {
            this.g = (((long) d.length) + 8) + this.g;
        } else {
            this.g += 8;
        }
        d();
        return 0;
    }

    public void a(Context context, d dVar, k kVar) {
        this.b = context;
        this.c = dVar;
        this.d = kVar;
    }

    public void a(e eVar) {
        if (eVar != null) {
            if (this.e) {
                String b = b(eVar);
                if (!b.equals("S-") && !b.equals("R-")) {
                    if (b.length() > 0 && !b.equals("C-") && !b.equals("C-" + g.u) && !b.equals("R-" + g.C) && !b.equals("S-" + g.t)) {
                        com.igexin.push.core.f.a().f().b(b, eVar);
                    } else if (this.d != null) {
                        this.d.a(eVar);
                    }
                    byte[] d = eVar.d();
                    if (d != null) {
                        this.f = (((long) d.length) + 8) + this.f;
                    } else {
                        this.f += 8;
                    }
                    d();
                }
            } else if (this.d != null) {
                this.d.a(eVar);
            }
        }
    }

    public void a(boolean z) {
        if (this.e != z) {
            this.e = z;
            this.h = false;
            if (z) {
                this.g = 0;
                this.f = 0;
                this.c.a((Object) new com.igexin.push.c.b.b());
                this.c.d();
                return;
            }
            this.c.a(g.a.replaceFirst("socket", "disConnect"), 0, null);
        }
    }

    public boolean a() {
        return this.e;
    }

    public void b() {
        g.F = 0;
        if (!this.e && this.d != null) {
            this.d.b();
        }
    }

    public void b(boolean z) {
        if (z) {
            com.igexin.a.a.c.a.a("disconnected|user");
            r.d();
            if (g.o) {
                g.o = false;
                com.igexin.push.core.a.f.a().m();
            }
        } else {
            com.igexin.a.a.c.a.a("disconnected|network");
            com.igexin.push.core.i.a().a(com.igexin.push.core.k.NETWORK_ERROR);
            r.d();
            r.a();
            e();
            if (g.o) {
                g.o = false;
                com.igexin.push.core.a.f.a().m();
            }
            c(true);
        }
        if (this.e) {
            com.igexin.push.core.f.a().f().b();
        } else if (this.d != null) {
            this.d.a(z);
        }
    }

    public long c() {
        return g.F;
    }

    public void c(boolean z) {
        if (z) {
            g.F = 0;
        }
        boolean a = com.igexin.push.core.a.f.a().a(System.currentTimeMillis());
        boolean n = com.igexin.push.core.a.f.a().n();
        if (g.k && g.l && g.m && !a && n) {
            if (g.F <= 0) {
                g.F = 1000;
            } else if (g.F <= 60000) {
                g.F += 10000;
            } else {
                g.F += 120000;
            }
            if (g.F > 3600000) {
                g.F = 3600000;
            }
            g.F = a(g.F);
        } else {
            g.F = 3600000;
        }
        com.igexin.push.e.b.f.g().h();
    }
}
