package com.sds.android.ttpod.framework.modules.skin.e;

import com.sds.android.cloudapi.ttpod.data.VIPPolicy.Entry;
import java.util.ArrayList;
import java.util.Iterator;

/* LrcFormattedLyric */
public class b implements a {
    protected final c a;
    protected final int b;
    protected final l c;
    protected ArrayList<f> d;
    private int e;

    public b(c cVar, int i, l lVar) {
        this.a = cVar;
        this.b = i;
        this.c = lVar;
    }

    public m a(int i) {
        return i < 0 ? null : (f) this.d.get(i);
    }

    public int a() {
        return this.d.size();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            stringBuilder.append(((f) it.next()).toString());
        }
        return stringBuilder.toString();
    }

    public a c() {
        if (this.a == null || this.b < 10 || this.c == null) {
            return null;
        }
        int b = this.a.b();
        this.d = new ArrayList((b >> 1) + b);
        for (int i = 0; i < b; i++) {
            a(this.a.b(i), i);
        }
        k.a(this.d, this.a.g());
        return this;
    }

    protected void a(f fVar, int i) {
        String g = fVar.g();
        long d = fVar.d();
        int f = fVar.f();
        int a = this.c.a(g);
        if (a <= this.b) {
            a(g, d, f, i, a);
            return;
        }
        float f2 = ((float) a) / ((float) this.b);
        int i2 = 0;
        int i3 = f;
        String str = g;
        while (i2 <= 2) {
            int b;
            if (f2 <= 2.0f) {
                b = b(str);
            } else {
                b = a(str);
            }
            g = str.substring(0, b);
            String trim = str.substring(b).trim();
            a = this.c.a(g);
            int a2 = this.c.a(trim);
            f = (a * i3) / (a + a2);
            if (f <= 0) {
                f = 1;
            }
            a(g, d, f, i, a);
            d += (long) f;
            f = i3 - f;
            if (a2 <= this.b) {
                a(trim, d, f, i, a2);
                return;
            }
            i2++;
            i3 = f;
            f2 = ((float) a2) / ((float) this.b);
            str = trim;
        }
    }

    protected int a(String str) {
        int lastIndexOf = str.lastIndexOf(32);
        while (lastIndexOf > 0) {
            String substring = str.substring(0, lastIndexOf);
            if (this.c.a(substring) <= this.b) {
                return lastIndexOf;
            }
            lastIndexOf = substring.lastIndexOf(32);
        }
        lastIndexOf = Math.min(this.b / ((int) this.c.a()), str.length() - 1);
        while (lastIndexOf > 0) {
            substring = str.substring(0, lastIndexOf);
            if (this.c.a(substring) <= this.b && lastIndexOf > 1) {
                return a(substring, str.charAt(lastIndexOf));
            }
            lastIndexOf--;
        }
        return 1;
    }

    private int a(String str, char c) {
        int length = str.length();
        if (k.a(str.charAt(length - 1)) && k.a(c)) {
            do {
                length--;
                if (length <= 0) {
                    break;
                }
            } while (k.a(str.charAt(length - 1)));
        }
        return length > 0 ? length : str.length();
    }

    protected int b(String str) {
        int lastIndexOf = str.lastIndexOf(32);
        int i = Entry.MAX_LIMIT;
        int i2 = -1;
        int i3 = lastIndexOf;
        while (i3 > 0) {
            String substring = str.substring(0, i3);
            if (this.c.a(substring) <= this.b) {
                int[] a = a(str, i3, i2, i);
                i2 = a[1];
                if (a[0] == 1) {
                    return i2;
                }
                i = a[2];
            }
            i3 = substring.lastIndexOf(32);
        }
        if (i2 > 0) {
            return i2;
        }
        i3 = i2;
        i2 = i;
        i = str.length() - 1;
        while (i > 0) {
            substring = str.substring(0, i);
            if (this.c.a(substring) <= this.b) {
                i = a(substring, str.charAt(i));
                int[] a2 = a(str, i, i3, i2);
                i3 = a2[1];
                if (a2[0] == 1) {
                    return i3;
                }
                i2 = a2[2];
            }
            i--;
        }
        return 1;
    }

    private int[] a(String str, int i, int i2, int i3) {
        int a = this.c.a(str.substring(0, i));
        int a2 = this.c.a(str.substring(i).trim());
        if (a2 < this.b) {
            a = Math.abs(a - a2);
            if (i2 <= 0 || a < i3) {
                a2 = a;
                i2 = i;
                a = 0;
            } else {
                a2 = a;
                a = 1;
            }
        } else if (i2 <= 0) {
            a = 1;
            a2 = 0;
            i2 = i;
        } else {
            a = 1;
            a2 = 0;
        }
        return new int[]{a, i2, a2};
    }

    protected void a(String str, long j, int i, int i2, int i3) {
        this.d.add(new f(j, str, i, i2, i3));
    }

    public int b() {
        return this.e;
    }

    public int a(long j) {
        this.e = k.a(this.d, j);
        return this.e;
    }
}
