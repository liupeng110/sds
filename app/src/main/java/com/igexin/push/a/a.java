package com.igexin.push.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.igexin.a.a.b.d;
import com.igexin.push.core.a.f;
import com.igexin.push.core.bean.e;
import com.igexin.push.core.g;
import com.igexin.sdk.PushBuildConfig;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import java.io.File;
import org.json.JSONObject;

public class a implements com.igexin.push.core.c.a {
    private static a a;

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
        sQLiteDatabase.delete("config", "id = ?", new String[]{String.valueOf(i)});
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StarCategory.KEY_STAR_CATEGORY_ID, Integer.valueOf(i));
        contentValues.put("value", str);
        sQLiteDatabase.replace("config", null, contentValues);
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i, byte[] bArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StarCategory.KEY_STAR_CATEGORY_ID, Integer.valueOf(i));
        contentValues.put("value", bArr);
        sQLiteDatabase.replace("config", null, contentValues);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
    }

    public void b() {
        d.c().a(new b(this), false, true);
    }

    public void b(SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        File file;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select id, value from config order by id", null);
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    try {
                        byte[] blob;
                        String str;
                        int i = rawQuery.getInt(0);
                        if (i == 20 || i == 21 || i == 22) {
                            blob = rawQuery.getBlob(1);
                            str = null;
                        } else {
                            str = rawQuery.getString(1);
                            blob = null;
                        }
                        switch (i) {
                            case 1:
                                k.e = (str.equals("null") ? null : Integer.valueOf(str)).intValue();
                                break;
                            case 2:
                                k.f = (str.equals("null") ? null : Integer.valueOf(str)).intValue();
                                break;
                            case 3:
                                k.g = (str.equals("null") ? null : Long.valueOf(str)).longValue();
                                break;
                            case 4:
                                if (!str.equals("null")) {
                                    k.j = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            case 5:
                                if (!str.equals("null")) {
                                    k.k = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            case 6:
                                if (!str.equals("null")) {
                                    k.l = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            case 7:
                                if (!str.equals("null")) {
                                    k.m = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            case 8:
                                if (!str.equals("null")) {
                                    k.n = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            case 9:
                                if (!str.equals("null")) {
                                    k.o = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            case 10:
                                if (!str.equals("null")) {
                                    k.r = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            case 11:
                                if (!str.equals("null")) {
                                    k.s = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            case 12:
                                if (!str.equals("null")) {
                                    k.t = Long.valueOf(str).longValue();
                                    break;
                                }
                                break;
                            case 13:
                                if (!str.equals("null")) {
                                    k.p = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            case 14:
                                if (!str.equals("null")) {
                                    k.q = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            case 15:
                                if (!str.equals("null")) {
                                    k.h = (str.equals("null") ? null : Integer.valueOf(str)).intValue();
                                    break;
                                }
                                break;
                            case 16:
                                if (!str.equals("null")) {
                                    k.i = (str.equals("null") ? null : Integer.valueOf(str)).intValue();
                                    break;
                                }
                                break;
                            case 17:
                                if (!str.equals("null")) {
                                    k.u = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            case 18:
                                if (!str.equals("null")) {
                                    k.v = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            case 19:
                                if (!str.equals("null")) {
                                    k.w = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            case 20:
                                if (blob == null) {
                                    break;
                                }
                                str = new String(com.igexin.a.a.a.a.a(blob, g.D));
                                if (str == null) {
                                    break;
                                }
                                k.x = f.a().a(new JSONObject(str));
                                break;
                            case 21:
                                if (blob == null) {
                                    break;
                                }
                                k.y = new String(com.igexin.a.a.a.a.a(blob, g.D));
                                break;
                            case 22:
                                if (blob == null) {
                                    break;
                                }
                                k.z = new String(com.igexin.a.a.a.a.a(blob, g.D));
                                break;
                            case 23:
                                if (!str.equals("null")) {
                                    k.A = Boolean.valueOf(str).booleanValue();
                                    break;
                                }
                                break;
                            default:
                                break;
                        }
                    } catch (Exception e) {
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        cursor = rawQuery;
                        th = th3;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e2) {
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (!PushBuildConfig.sdk_conf_version.equals(g.P)) {
                if (k.x != null) {
                    if (k.x.b().size() > 0) {
                        for (Object obj : k.x.b().keySet()) {
                            file = new File(g.ad + "/" + ((e) k.x.b().get(obj)).c());
                            if (file.exists()) {
                                file.delete();
                            }
                        }
                    }
                    k.x = null;
                    h();
                }
                com.igexin.push.core.c.f.a().b(PushBuildConfig.sdk_conf_version);
                com.igexin.push.core.c.f.a().f(0);
            }
        } catch (Throwable th4) {
            th = th4;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        if (!PushBuildConfig.sdk_conf_version.equals(g.P)) {
            if (k.x != null) {
                if (k.x.b().size() > 0) {
                    while (r2.hasNext()) {
                        file = new File(g.ad + "/" + ((e) k.x.b().get(obj)).c());
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
                k.x = null;
                h();
            }
            com.igexin.push.core.c.f.a().b(PushBuildConfig.sdk_conf_version);
            com.igexin.push.core.c.f.a().f(0);
        }
    }

    public void c() {
        d.c().a(new c(this), false, true);
    }

    public void c(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase, 1, String.valueOf(k.e));
        a(sQLiteDatabase, 2, String.valueOf(k.f));
        a(sQLiteDatabase, 3, String.valueOf(k.g));
        a(sQLiteDatabase, 4, String.valueOf(k.j));
        a(sQLiteDatabase, 5, String.valueOf(k.k));
        a(sQLiteDatabase, 6, String.valueOf(k.l));
        a(sQLiteDatabase, 7, String.valueOf(k.m));
        a(sQLiteDatabase, 8, String.valueOf(k.n));
        a(sQLiteDatabase, 9, String.valueOf(k.o));
        a(sQLiteDatabase, 10, String.valueOf(k.r));
        a(sQLiteDatabase, 11, String.valueOf(k.s));
        a(sQLiteDatabase, 12, String.valueOf(k.t));
        a(sQLiteDatabase, 13, String.valueOf(k.p));
        a(sQLiteDatabase, 14, String.valueOf(k.q));
        a(sQLiteDatabase, 15, String.valueOf(k.h));
        a(sQLiteDatabase, 3, String.valueOf(k.g));
        a(sQLiteDatabase, 17, String.valueOf(k.u));
        a(sQLiteDatabase, 18, String.valueOf(k.v));
        a(sQLiteDatabase, 19, String.valueOf(k.w));
    }

    public void d() {
        d.c().a(new d(this), false, true);
    }

    public void e() {
        d.c().a(new e(this), false, true);
    }

    public void f() {
        d.c().a(new f(this), false, true);
    }

    public void g() {
        d.c().a(new g(this), true, false);
    }

    public void h() {
        d.c().a(new h(this), true, false);
    }
}
