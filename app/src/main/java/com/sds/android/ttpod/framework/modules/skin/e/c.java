package com.sds.android.ttpod.framework.modules.skin.e;

import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* LrcLyric */
public class c implements g {
    private i a = new i();
    private ArrayList<f> b = new ArrayList(32);

    public /* synthetic */ List h() {
        return c();
    }

    public int hashCode() {
        return ((this.a.hashCode() + 31) * 31) + this.b.size();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        c cVar = (c) obj;
        if (!this.a.equals(cVar.a)) {
            return false;
        }
        if (this.b.size() != cVar.b.size()) {
            z = false;
        }
        return z;
    }

    public c(String str) {
        this.a.a(str);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.a.toString());
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(((f) this.b.get(i)).toString());
            if (i <= 2) {
                g.a("LrcLyric", "Lyric lookLyricPic toString idx=%d sentence=%s timestamp=%d", Integer.valueOf(i), r0.toString(), Long.valueOf(r0.d()));
            }
        }
        return stringBuilder.toString();
    }

    public int a(int i) {
        return this.a.a(i);
    }

    public int a() {
        return this.a.g();
    }

    public boolean a(boolean z) {
        Exception exception;
        Throwable th;
        boolean z2 = true;
        if (this.a.f()) {
            CharSequence a = this.a.a();
            if (z && !k.a(a)) {
                e.h(a);
                FileOutputStream fileOutputStream = null;
                FileOutputStream fileOutputStream2;
                try {
                    fileOutputStream2 = new FileOutputStream(a);
                    try {
                        fileOutputStream2.write(h.a);
                        fileOutputStream2.write(toString().getBytes("UTF8"));
                        fileOutputStream2.flush();
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e2) {
                        fileOutputStream = fileOutputStream2;
                        exception = e2;
                        z2 = false;
                        try {
                            exception.printStackTrace();
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            return z2;
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream2 = fileOutputStream;
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e32) {
                                    e32.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        throw th;
                    }
                } catch (Exception e22) {
                    exception = e22;
                    z2 = false;
                    exception.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return z2;
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream2 = null;
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
            }
        }
        return z2;
    }

    public int b() {
        return this.b.size();
    }

    public ArrayList<f> c() {
        return this.b;
    }

    public f b(int i) {
        return (f) this.b.get(i);
    }

    public long d() {
        return this.a.c();
    }

    public long e() {
        return this.a.d();
    }

    public long f() {
        long e = this.a.e();
        if (e != 0 || b() <= 0) {
            return e;
        }
        return ((f) this.b.get(b() - 1)).d() + 5000;
    }

    public i g() {
        return this.a;
    }

    public a a(int i, int i2, l lVar) {
        switch (i) {
            case 1:
                return new b(this, i2, lVar).c();
            case 2:
                return new d(this, i2, lVar).c();
            default:
                return null;
        }
    }
}
