package com.igexin.push.core.c;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.igexin.a.a.a.a;
import com.igexin.a.a.b.d;
import com.igexin.push.a.j;
import com.igexin.push.core.g;
import com.igexin.push.f.b;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.ttfm.android.sdk.http.TTPodFMHttpClient;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class f implements a {
    private static final String a = j.a;
    private static f b;
    private Map c = new HashMap();

    private f() {
    }

    public static f a() {
        if (b == null) {
            b = new f();
        }
        return b;
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StarCategory.KEY_STAR_CATEGORY_ID, Integer.valueOf(i));
        contentValues.put("value", str);
        sQLiteDatabase.replace("runtime", null, contentValues);
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i, byte[] bArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StarCategory.KEY_STAR_CATEGORY_ID, Integer.valueOf(i));
        contentValues.put("value", bArr);
        sQLiteDatabase.replace("runtime", null, contentValues);
    }

    private void d() {
        Throwable th;
        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStream2;
        try {
            File file = new File(g.Y);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream2 = new FileOutputStream(g.Y);
            try {
                fileOutputStream2.write(a.b((("v01" + g.z) + String.valueOf(g.t) + "|" + g.c).getBytes(), g.D));
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e3) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            fileOutputStream2 = null;
            th = th4;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    private long e() {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        long j = 0;
        FileInputStream fileInputStream = null;
        if (new File(g.Y).exists()) {
            byte[] bArr = new byte[1024];
            FileInputStream fileInputStream2;
            try {
                fileInputStream2 = new FileInputStream(g.Y);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int read = fileInputStream2.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        } catch (Exception e) {
                            fileInputStream = fileInputStream2;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    String str = new String(a.a(byteArrayOutputStream.toByteArray(), g.D));
                    if (str != null) {
                        String substring = str.indexOf("null") >= 0 ? str.substring(7) : str.substring(20);
                        if (substring != null) {
                            int indexOf = substring.indexOf("|");
                            if (indexOf >= 0) {
                                substring = substring.substring(0, indexOf);
                            }
                            long parseLong = Long.parseLong(substring);
                            if (parseLong != 0) {
                                j = parseLong;
                            }
                        }
                    }
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Exception e2) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                } catch (Exception e4) {
                    byteArrayOutputStream = null;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e5) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e6) {
                        }
                    }
                    return j;
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayOutputStream = null;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Exception e7) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e8) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e9) {
                byteArrayOutputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return j;
            } catch (Throwable th4) {
                th = th4;
                byteArrayOutputStream = null;
                fileInputStream2 = null;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        }
        return j;
    }

    private void f() {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        try {
            File file = new File(g.Z);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(g.Z);
            try {
                if (g.B != null) {
                    fileOutputStream.write(g.B.getBytes(TTPodFMHttpClient.AppEncode));
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e) {
                        }
                    }
                } else if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e2) {
                    }
                }
            } catch (Exception e3) {
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e4) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e5) {
                    }
                }
                throw th;
            }
        } catch (Exception e6) {
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            fileOutputStream = null;
            th = th4;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    private String g() {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        FileInputStream fileInputStream2;
        Throwable th;
        if (new File(g.Z).exists()) {
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream2;
            try {
                fileInputStream = new FileInputStream(g.Z);
                try {
                    byteArrayOutputStream2 = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream2.write(bArr, 0, read);
                        } catch (Exception e) {
                            byteArrayOutputStream = byteArrayOutputStream2;
                            fileInputStream2 = fileInputStream;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    String str = new String(byteArrayOutputStream2.toByteArray(), TTPodFMHttpClient.AppEncode);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e2) {
                        }
                    }
                    if (byteArrayOutputStream2 == null) {
                        return str;
                    }
                    try {
                        byteArrayOutputStream2.close();
                        return str;
                    } catch (Exception e3) {
                        return str;
                    }
                } catch (Exception e4) {
                    byteArrayOutputStream = null;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Exception e5) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                            return null;
                        } catch (Exception e6) {
                            return null;
                        }
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayOutputStream2 = null;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e7) {
                        }
                    }
                    if (byteArrayOutputStream2 != null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e8) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e9) {
                byteArrayOutputStream = null;
                fileInputStream2 = null;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                    return null;
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                byteArrayOutputStream2 = null;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                throw th;
            }
        }
        return null;
    }

    private String h() {
        String str = "";
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 15; i++) {
            str = str + random.nextInt(10);
        }
        return str;
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
    }

    public boolean a(long j) {
        g.a(j);
        d.c().a(new j(this), false, true);
        return true;
    }

    public boolean a(String str) {
        g.B = str;
        d.c().a(new k(this), false, true);
        return true;
    }

    public boolean a(boolean z) {
        if (g.Q == z) {
            return false;
        }
        g.Q = z;
        d.c().a(new h(this), false, true);
        return true;
    }

    public void b() {
        d.c().a(new g(this), false, true);
    }

    public void b(SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        long e;
        String g;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select id, value from runtime order by id", null);
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    try {
                        byte[] blob;
                        String str;
                        int i = rawQuery.getInt(0);
                        if (i == 1 || i == 14) {
                            blob = rawQuery.getBlob(1);
                            str = null;
                        } else {
                            str = rawQuery.getString(1);
                            blob = null;
                        }
                        switch (i) {
                            case 1:
                                long parseLong;
                                str = new String(a.a(blob, g.D));
                                if (str != null) {
                                    try {
                                        if (!str.equals("null")) {
                                            parseLong = Long.parseLong(str);
                                            g.v = com.igexin.a.b.a.a(String.valueOf(parseLong));
                                            g.a(parseLong);
                                            break;
                                        }
                                    } catch (NumberFormatException e2) {
                                        g.t = 0;
                                        break;
                                    }
                                }
                                parseLong = 0;
                                g.v = com.igexin.a.b.a.a(String.valueOf(parseLong));
                                g.a(parseLong);
                            case 2:
                                if (str.equals("null")) {
                                    str = null;
                                }
                                g.B = str;
                                break;
                            case 3:
                                if (str.equals("null")) {
                                    str = null;
                                }
                                g.C = str;
                                break;
                            case 4:
                                g.n = str.equals("null") ? true : Boolean.parseBoolean(str);
                                break;
                            case 5:
                                g.H = str.equals("null") ? 0 : Long.parseLong(str);
                                break;
                            case 6:
                                g.I = str.equals("null") ? 0 : Long.parseLong(str);
                                break;
                            case 7:
                                g.J = str.equals("null") ? 0 : Long.parseLong(str);
                                break;
                            case 8:
                                g.K = str.equals("null") ? 0 : Long.parseLong(str);
                                break;
                            case 9:
                                g.S = str.equals("null") ? 0 : Long.parseLong(str);
                                break;
                            case 10:
                                g.T = str.equals("null") ? 0 : Long.parseLong(str);
                                break;
                            case 11:
                                g.N = str.equals("null") ? 0 : Long.parseLong(str);
                                break;
                            case 12:
                                g.O = str.equals("null") ? 0 : Long.parseLong(str);
                                break;
                            case 13:
                                if (str.equals("null")) {
                                    str = null;
                                }
                                g.P = str;
                                break;
                            case 14:
                                g.ax = new String(a.a(blob, g.D));
                                break;
                            case 15:
                                if (!str.equals("null")) {
                                    g.Q = Boolean.parseBoolean(str);
                                    break;
                                }
                                break;
                            case 16:
                                g.R = str.equals("null") ? 0 : Long.parseLong(str);
                                break;
                            default:
                                break;
                        }
                    } catch (Exception e3) {
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
        } catch (Exception e4) {
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (g.t == 0) {
                e = e();
                if (e != 0) {
                    g.v = com.igexin.a.b.a.a(String.valueOf(e));
                    g.a(e);
                    a(sQLiteDatabase, 1, b.a(String.valueOf(e).getBytes()));
                }
            }
            g.ax = com.igexin.a.b.a.a(32);
            a(sQLiteDatabase, 14, b.a(g.ax.getBytes()));
            g = g();
            g.B = g;
            a(sQLiteDatabase, 2, String.valueOf(g.B));
            if (g.C == null) {
                g = g.w;
                if (g == null) {
                    g = "V" + h();
                }
                g.C = "A-" + g + "-" + System.currentTimeMillis();
                a(sQLiteDatabase, 3, String.valueOf(g.C));
            }
        } catch (Throwable th4) {
            th = th4;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        if (g.t == 0) {
            e = e();
            if (e != 0) {
                g.v = com.igexin.a.b.a.a(String.valueOf(e));
                g.a(e);
                a(sQLiteDatabase, 1, b.a(String.valueOf(e).getBytes()));
            }
        }
        if (g.ax == null || "".equals(g.ax) || "null".equals(g.ax)) {
            g.ax = com.igexin.a.b.a.a(32);
            a(sQLiteDatabase, 14, b.a(g.ax.getBytes()));
        }
        g = g();
        if (g.B == null && g != null && g.length() > 5) {
            g.B = g;
            a(sQLiteDatabase, 2, String.valueOf(g.B));
        }
        if (g.C == null) {
            g = g.w;
            if (g == null) {
                g = "V" + h();
            }
            g.C = "A-" + g + "-" + System.currentTimeMillis();
            a(sQLiteDatabase, 3, String.valueOf(g.C));
        }
    }

    public boolean b(long j) {
        if (g.S == j) {
            return false;
        }
        g.S = j;
        d.c().a(new l(this), false, true);
        return true;
    }

    public boolean b(String str) {
        if (str == null || str.equals(g.P)) {
            return false;
        }
        g.P = str;
        d.c().a(new q(this), false, true);
        return true;
    }

    public Map c() {
        return this.c;
    }

    public void c(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase, 1, a.b(String.valueOf(g.t).getBytes(), g.D));
        a(sQLiteDatabase, 4, String.valueOf(g.n));
        a(sQLiteDatabase, 8, String.valueOf(g.K));
        a(sQLiteDatabase, 7, String.valueOf(g.J));
        a(sQLiteDatabase, 6, String.valueOf(g.I));
        a(sQLiteDatabase, 9, String.valueOf(g.S));
        a(sQLiteDatabase, 10, String.valueOf(g.T));
        a(sQLiteDatabase, 5, String.valueOf(g.H));
        a(sQLiteDatabase, 3, String.valueOf(g.C));
        a(sQLiteDatabase, 11, String.valueOf(g.N));
        a(sQLiteDatabase, 12, String.valueOf(g.O));
    }

    public boolean c(long j) {
        if (g.O == j) {
            return false;
        }
        g.O = j;
        d.c().a(new m(this), false, true);
        return true;
    }

    public boolean d(long j) {
        if (g.T == j) {
            return false;
        }
        g.T = j;
        d.c().a(new n(this), false, true);
        return true;
    }

    public boolean e(long j) {
        if (g.J == j) {
            return false;
        }
        g.J = j;
        d.c().a(new o(this), false, true);
        return true;
    }

    public boolean f(long j) {
        if (g.N == j) {
            return false;
        }
        g.N = j;
        d.c().a(new p(this), false, true);
        return true;
    }

    public boolean g(long j) {
        if (g.R == j) {
            return false;
        }
        g.R = j;
        d.c().a(new i(this), false, true);
        return true;
    }
}
