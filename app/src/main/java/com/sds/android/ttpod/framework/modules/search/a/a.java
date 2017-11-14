package com.sds.android.ttpod.framework.modules.search.a;

import android.support.v4.os.EnvironmentCompat;
import com.baidu.location.BDLocation;
import com.igexin.download.Downloads;
import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* KXmlParser */
public class a implements XmlPullParser {
    private String[] A = new String[16];
    private int B = 0;
    private String C;
    private int[] D = new int[2];
    private int E;
    private boolean F;
    private boolean G;
    private boolean H;
    private Object a;
    private String b;
    private Boolean c;
    private boolean d;
    private boolean e;
    private Hashtable<String, String> f;
    private int g;
    private String[] h = new String[16];
    private String[] i = new String[8];
    private int[] j = new int[4];
    private Reader k;
    private String l;
    private char[] m;
    private int n;
    private int o;
    private int p;
    private int q;
    private char[] r = new char[128];
    private int s;
    private int t;
    private boolean u;
    private String v;
    private String w;
    private String x;
    private boolean y;
    private int z;

    public a() {
        int i = 128;
        if (Runtime.getRuntime().freeMemory() >= 1048576) {
            i = 8192;
        }
        this.m = new char[i];
    }

    private boolean a(String str, boolean z, String str2) {
        if (!str.startsWith("http://xmlpull.org/v1/doc/")) {
            return false;
        }
        if (z) {
            return str.substring(42).equals(str2);
        }
        return str.substring(40).equals(str2);
    }

    private boolean b() throws XmlPullParserException {
        int i = 0;
        boolean z = false;
        while (i < (this.z << 2)) {
            String substring;
            String str = this.A[i + 2];
            int indexOf = str.indexOf(58);
            if (indexOf != -1) {
                substring = str.substring(0, indexOf);
                str = str.substring(indexOf + 1);
            } else if (str.equals("xmlns")) {
                String str2 = str;
                str = null;
                substring = str2;
            } else {
                i += 4;
            }
            if (substring.equals("xmlns")) {
                int[] iArr = this.j;
                indexOf = this.g;
                int i2 = iArr[indexOf];
                iArr[indexOf] = i2 + 1;
                int i3 = i2 << 1;
                this.i = a(this.i, i3 + 2);
                this.i[i3] = str;
                this.i[i3 + 1] = this.A[i + 3];
                if (str != null && this.A[i + 3].equals("")) {
                    a("illegal empty namespace");
                }
                Object obj = this.A;
                int i4 = i + 4;
                Object obj2 = this.A;
                i2 = this.z - 1;
                this.z = i2;
                System.arraycopy(obj, i4, obj2, i, (i2 << 2) - i);
                i -= 4;
            } else {
                z = true;
            }
            i += 4;
        }
        if (z) {
            i = (this.z << 2) - 4;
            while (i >= 0) {
                substring = this.A[i + 2];
                i4 = substring.indexOf(58);
                if (i4 != 0 || this.e) {
                    if (i4 != -1) {
                        String substring2 = substring.substring(0, i4);
                        substring = substring.substring(i4 + 1);
                        str = getNamespace(substring2);
                        if (str != null || this.e) {
                            this.A[i] = str;
                            this.A[i + 1] = substring2;
                            this.A[i + 2] = substring;
                        } else {
                            throw new RuntimeException("Undefined Prefix: " + substring2 + " in " + this);
                        }
                    }
                    i -= 4;
                } else {
                    throw new RuntimeException("illegal attribute name: " + substring + " at " + this);
                }
            }
        }
        i = this.x.indexOf(58);
        if (i == 0) {
            a("illegal tag name: " + this.x);
        }
        if (i != -1) {
            this.w = this.x.substring(0, i);
            this.x = this.x.substring(i + 1);
        }
        this.v = getNamespace(this.w);
        if (this.v == null) {
            if (this.w != null) {
                a("undefined prefix: " + this.w);
            }
            this.v = "";
        }
        return z;
    }

    private String[] a(String[] strArr, int i) {
        if (strArr.length >= i) {
            return strArr;
        }
        Object obj = new String[(i + 16)];
        System.arraycopy(strArr, 0, obj, 0, strArr.length);
        return obj;
    }

    private void a(String str) throws XmlPullParserException {
        if (!this.e) {
            b(str);
        } else if (this.C == null) {
            this.C = "ERR: " + str;
        }
    }

    private void b(String str) throws XmlPullParserException {
        if (str.length() >= 100) {
            str = str.substring(0, 100) + "\n";
        }
        throw new XmlPullParserException(str, this, null);
    }

    private void c() throws IOException, XmlPullParserException {
        boolean z = false;
        if (this.k == null) {
            b("No Input specified");
        }
        if (this.t == 3) {
            this.g--;
        }
        do {
            this.z = -1;
            if (this.y) {
                this.y = false;
                this.t = 3;
                return;
            } else if (this.C != null) {
                while (r0 < this.C.length()) {
                    b(this.C.charAt(r0));
                    r0++;
                }
                this.C = null;
                this.t = 9;
                return;
            } else if (!this.e || (this.B <= 0 && (c(0) != -1 || this.g <= 0))) {
                this.w = null;
                this.x = null;
                this.v = null;
                this.t = e();
                switch (this.t) {
                    case 1:
                        return;
                    case 2:
                        c(false);
                        return;
                    case 3:
                        d();
                        return;
                    case 4:
                        if (!this.H) {
                            z = true;
                        }
                        a(60, z);
                        if (this.g == 0 && this.u) {
                            this.t = 7;
                            return;
                        }
                        return;
                    case 6:
                        f();
                        return;
                    default:
                        this.t = a(this.H);
                        break;
                }
            } else {
                r0 = (this.g - 1) << 2;
                this.t = 3;
                this.v = this.h[r0];
                this.w = this.h[r0 + 1];
                this.x = this.h[r0 + 2];
                if (this.B != 1) {
                    this.C = "missing end tag /" + this.x + " inserted";
                }
                if (this.B > 0) {
                    this.B--;
                    return;
                }
                return;
            }
        } while (this.t == 998);
    }

    private int a(boolean z) throws IOException, XmlPullParserException {
        String str;
        int i;
        int i2;
        String str2 = "";
        g();
        int g = g();
        if (g == 63) {
            if ((c(0) == 120 || c(0) == 88) && (c(1) == 109 || c(1) == 77)) {
                if (z) {
                    b(c(0));
                    b(c(1));
                }
                g();
                g();
                if ((c(0) == 108 || c(0) == 76) && c(1) <= 32) {
                    if (this.p != 1 || this.q > 4) {
                        a("PI must not start with xml");
                    }
                    c(true);
                    if (this.z < 1 || !"version".equals(this.A[2])) {
                        a("version expected");
                    }
                    this.b = this.A[3];
                    if (1 >= this.z || !"encoding".equals(this.A[6])) {
                        g = 1;
                    } else {
                        this.l = this.A[7];
                        g = 2;
                    }
                    if (g < this.z && "standalone".equals(this.A[(g * 4) + 2])) {
                        String str3 = this.A[(g * 4) + 3];
                        if ("yes".equals(str3)) {
                            this.c = Boolean.valueOf(true);
                        } else if ("no".equals(str3)) {
                            this.c = Boolean.valueOf(false);
                        } else {
                            a("illegal standalone value: " + str3);
                        }
                        g++;
                    }
                    if (g != this.z) {
                        a("illegal xmldecl");
                    }
                    this.u = true;
                    this.s = 0;
                    return 998;
                }
            }
            g = 8;
            str = str2;
            i = 63;
        } else if (g != 33) {
            a("illegal: <" + g);
            return 9;
        } else if (c(0) == 45) {
            i = 45;
            str = "--";
            g = 9;
        } else if (c(0) == 91) {
            g = 5;
            str = "[CDATA[";
            i = 93;
            z = true;
        } else {
            g = 10;
            str = "DOCTYPE";
            i = -1;
        }
        for (i2 = 0; i2 < str.length(); i2++) {
            a(str.charAt(i2));
        }
        if (g == 10) {
            b(z);
        } else {
            boolean z2 = false;
            while (true) {
                i2 = g();
                if (i2 == true) {
                    a("Unexpected EOF");
                    return 9;
                }
                if (z) {
                    b(i2);
                }
                if ((i == 63 || i2 == i) && c(0) == i && c(1) == 62) {
                    break;
                }
                z2 = i2;
            }
            if (i == 45 && r6) {
                a("illegal comment delimiter: --->");
            }
            g();
            g();
            if (z && i != 63) {
                this.s--;
            }
        }
        return g;
    }

    private void b(boolean z) throws IOException, XmlPullParserException {
        Object obj = null;
        int i = 1;
        while (true) {
            int g = g();
            switch (g) {
                case -1:
                    a("Unexpected EOF");
                    return;
                case 39:
                    if (obj != null) {
                        obj = null;
                        break;
                    } else {
                        int i2 = 1;
                        break;
                    }
                case 60:
                    if (obj == null) {
                        i++;
                        break;
                    }
                    break;
                case BDLocation.TypeCriteriaException /*62*/:
                    if (obj == null) {
                        i--;
                        if (i == 0) {
                            return;
                        }
                    }
                    break;
            }
            if (z) {
                b(g);
            }
        }
    }

    private void d() throws IOException, XmlPullParserException {
        g();
        g();
        this.x = h();
        i();
        a('>');
        int i = (this.g - 1) << 2;
        if (this.g == 0) {
            a("element stack empty");
            this.t = 9;
            return;
        }
        if (!this.x.equals(this.h[i + 3])) {
            a("expected: /" + this.h[i + 3] + " read: " + this.x);
            int i2 = i;
            while (i2 >= 0 && !this.x.toLowerCase().equals(this.h[i2 + 3].toLowerCase())) {
                this.B++;
                i2 -= 4;
            }
            if (i2 < 0) {
                this.B = 0;
                this.t = 9;
                return;
            }
        }
        this.v = this.h[i];
        this.w = this.h[i + 1];
        this.x = this.h[i + 2];
    }

    private int e() throws IOException {
        switch (c(0)) {
            case -1:
                return 1;
            case 38:
                return 6;
            case 60:
                switch (c(1)) {
                    case 33:
                    case BDLocation.TypeNetWorkException /*63*/:
                        return 999;
                    case 47:
                        return 3;
                    default:
                        return 2;
                }
            default:
                return 4;
        }
    }

    private String a(int i) {
        return new String(this.r, i, this.s - i);
    }

    private void b(int i) {
        this.u = (i <= 32 ? 1 : 0) & this.u;
        if (this.s == this.r.length) {
            Object obj = new char[(((this.s * 4) / 3) + 4)];
            System.arraycopy(this.r, 0, obj, 0, this.s);
            this.r = obj;
        }
        char[] cArr = this.r;
        int i2 = this.s;
        this.s = i2 + 1;
        cArr[i2] = (char) i;
    }

    /* JADX err: Inconsistent code. */
    private void c(boolean r11) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
        r10 = this;
        r9 = 61;
        r1 = 32;
        r8 = 1;
        r7 = 62;
        r6 = 0;
        if (r11 != 0) goto L_0x000d;
    L_0x000a:
        r10.g();
    L_0x000d:
        r0 = r10.h();
        r10.x = r0;
        r10.z = r6;
    L_0x0015:
        r10.i();
        r0 = r10.c(r6);
        if (r11 == 0) goto L_0x0029;
    L_0x001e:
        r2 = 63;
        if (r0 != r2) goto L_0x009d;
    L_0x0022:
        r10.g();
        r10.a(r7);
    L_0x0028:
        return;
    L_0x0029:
        r2 = 47;
        if (r0 != r2) goto L_0x0095;
    L_0x002d:
        r10.y = r8;
        r10.g();
        r10.i();
        r10.a(r7);
    L_0x0038:
        r0 = r10.g;
        r1 = r0 + 1;
        r10.g = r1;
        r0 = r0 << 2;
        r1 = r10.h;
        r2 = r0 + 4;
        r1 = r10.a(r1, r2);
        r10.h = r1;
        r1 = r10.h;
        r2 = r0 + 3;
        r3 = r10.x;
        r1[r2] = r3;
        r1 = r10.g;
        r2 = r10.j;
        r2 = r2.length;
        if (r1 < r2) goto L_0x0069;
    L_0x0059:
        r1 = r10.g;
        r1 = r1 + 4;
        r1 = new int[r1];
        r2 = r10.j;
        r3 = r10.j;
        r3 = r3.length;
        java.lang.System.arraycopy(r2, r6, r1, r6, r3);
        r10.j = r1;
    L_0x0069:
        r1 = r10.j;
        r2 = r10.g;
        r3 = r10.j;
        r4 = r10.g;
        r4 = r4 + -1;
        r3 = r3[r4];
        r1[r2] = r3;
        r1 = r10.d;
        if (r1 == 0) goto L_0x0136;
    L_0x007b:
        r10.b();
    L_0x007e:
        r1 = r10.h;
        r2 = r10.v;
        r1[r0] = r2;
        r1 = r10.h;
        r2 = r0 + 1;
        r3 = r10.w;
        r1[r2] = r3;
        r1 = r10.h;
        r0 = r0 + 2;
        r2 = r10.x;
        r1[r0] = r2;
        goto L_0x0028;
    L_0x0095:
        if (r0 != r7) goto L_0x009d;
    L_0x0097:
        if (r11 != 0) goto L_0x009d;
    L_0x0099:
        r10.g();
        goto L_0x0038;
    L_0x009d:
        r2 = -1;
        if (r0 != r2) goto L_0x00a6;
    L_0x00a0:
        r0 = "Unexpected EOF";
        r10.a(r0);
        goto L_0x0028;
    L_0x00a6:
        r0 = r10.h();
        r2 = r0.length();
        if (r2 != 0) goto L_0x00b6;
    L_0x00b0:
        r0 = "attr name expected";
        r10.a(r0);
        goto L_0x0038;
    L_0x00b6:
        r2 = r10.z;
        r3 = r2 + 1;
        r10.z = r3;
        r2 = r2 << 2;
        r3 = r10.A;
        r4 = r2 + 4;
        r3 = r10.a(r3, r4);
        r10.A = r3;
        r3 = r10.A;
        r4 = r2 + 1;
        r5 = "";
        r3[r2] = r5;
        r2 = r10.A;
        r3 = r4 + 1;
        r5 = 0;
        r2[r4] = r5;
        r2 = r10.A;
        r4 = r3 + 1;
        r2[r3] = r0;
        r10.i();
        r2 = r10.c(r6);
        if (r2 == r9) goto L_0x0104;
    L_0x00e6:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Attr.value missing f. ";
        r2 = r2.append(r3);
        r0 = r2.append(r0);
        r0 = r0.toString();
        r10.a(r0);
        r0 = r10.A;
        r2 = "1";
        r0[r4] = r2;
        goto L_0x0015;
    L_0x0104:
        r10.a(r9);
        r10.i();
        r0 = r10.c(r6);
        r2 = 39;
        if (r0 == r2) goto L_0x0132;
    L_0x0112:
        r2 = 34;
        if (r0 == r2) goto L_0x0132;
    L_0x0116:
        r0 = "attr value delimiter missing!";
        r10.a(r0);
        r0 = r1;
    L_0x011c:
        r2 = r10.s;
        r10.a(r0, r8);
        r3 = r10.A;
        r5 = r10.a(r2);
        r3[r4] = r5;
        r10.s = r2;
        if (r0 == r1) goto L_0x0015;
    L_0x012d:
        r10.g();
        goto L_0x0015;
    L_0x0132:
        r10.g();
        goto L_0x011c;
    L_0x0136:
        r1 = "";
        r10.v = r1;
        goto L_0x007e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.framework.modules.search.a.a.c(boolean):void");
    }

    /* JADX err: Inconsistent code. */
    private void f() throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
        r6 = this;
        r5 = 35;
        r1 = 1;
        r2 = 0;
        r0 = r6.g();
        r6.b(r0);
        r0 = r6.s;
    L_0x000d:
        r3 = r6.g();
        r4 = 59;
        if (r3 != r4) goto L_0x0045;
    L_0x0015:
        r3 = r6.a(r0);
        r0 = r0 + -1;
        r6.s = r0;
        r0 = r6.H;
        if (r0 == 0) goto L_0x0028;
    L_0x0021:
        r0 = r6.t;
        r4 = 6;
        if (r0 != r4) goto L_0x0028;
    L_0x0026:
        r6.x = r3;
    L_0x0028:
        r0 = r3.charAt(r2);
        if (r0 != r5) goto L_0x0088;
    L_0x002e:
        r0 = r3.charAt(r1);
        r2 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        if (r0 != r2) goto L_0x007f;
    L_0x0036:
        r0 = 2;
        r0 = r3.substring(r0);
        r1 = 16;
        r0 = java.lang.Integer.parseInt(r0, r1);
    L_0x0041:
        r6.b(r0);
    L_0x0044:
        return;
    L_0x0045:
        r4 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r3 >= r4) goto L_0x007b;
    L_0x0049:
        r4 = 48;
        if (r3 < r4) goto L_0x0051;
    L_0x004d:
        r4 = 57;
        if (r3 <= r4) goto L_0x007b;
    L_0x0051:
        r4 = 97;
        if (r3 < r4) goto L_0x0059;
    L_0x0055:
        r4 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        if (r3 <= r4) goto L_0x007b;
    L_0x0059:
        r4 = 65;
        if (r3 < r4) goto L_0x0061;
    L_0x005d:
        r4 = 90;
        if (r3 <= r4) goto L_0x007b;
    L_0x0061:
        r4 = 95;
        if (r3 == r4) goto L_0x007b;
    L_0x0065:
        r4 = 45;
        if (r3 == r4) goto L_0x007b;
    L_0x0069:
        if (r3 == r5) goto L_0x007b;
    L_0x006b:
        r0 = r6.e;
        if (r0 != 0) goto L_0x0074;
    L_0x006f:
        r0 = "unterminated entity ref";
        r6.a(r0);
    L_0x0074:
        r0 = -1;
        if (r3 == r0) goto L_0x0044;
    L_0x0077:
        r6.b(r3);
        goto L_0x0044;
    L_0x007b:
        r6.b(r3);
        goto L_0x000d;
    L_0x007f:
        r0 = r3.substring(r1);
        r0 = java.lang.Integer.parseInt(r0);
        goto L_0x0041;
    L_0x0088:
        r0 = r6.f;
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        if (r0 != 0) goto L_0x00b9;
    L_0x0092:
        r6.G = r1;
        r1 = r6.G;
        if (r1 == 0) goto L_0x00bb;
    L_0x0098:
        r0 = r6.H;
        if (r0 != 0) goto L_0x0044;
    L_0x009c:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "unresolved: &";
        r0 = r0.append(r1);
        r0 = r0.append(r3);
        r1 = ";";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r6.a(r0);
        goto L_0x0044;
    L_0x00b9:
        r1 = r2;
        goto L_0x0092;
    L_0x00bb:
        r1 = r0.length();
        if (r2 >= r1) goto L_0x0044;
    L_0x00c1:
        r1 = r0.charAt(r2);
        r6.b(r1);
        r2 = r2 + 1;
        goto L_0x00bb;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.framework.modules.search.a.a.f():void");
    }

    private void a(int i, boolean z) throws IOException, XmlPullParserException {
        int c = c(0);
        int i2 = 0;
        while (c != -1 && c != i) {
            if (i != 32 || (c > 32 && c != 62)) {
                if (c == 38) {
                    if (z) {
                        f();
                    } else {
                        return;
                    }
                } else if (c == 10 && this.t == 2) {
                    g();
                    b(32);
                } else {
                    b(g());
                }
                if (c == 62 && i2 >= 2 && i != 93) {
                    a("Illegal: ]]>");
                }
                if (c == 93) {
                    i2++;
                } else {
                    i2 = 0;
                }
                c = c(0);
            } else {
                return;
            }
        }
    }

    private void a(char c) throws IOException, XmlPullParserException {
        char g = g();
        if (g != c) {
            a("expected: '" + c + "' actual: '" + ((char) g) + "'");
        }
    }

    private int g() throws IOException {
        int c;
        if (this.E == 0) {
            c = c(0);
        } else {
            c = this.D[0];
            this.D[0] = this.D[1];
        }
        this.E--;
        this.q++;
        if (c == 10) {
            this.p++;
            this.q = 1;
        }
        return c;
    }

    private int c(int i) throws IOException {
        while (i >= this.E) {
            int read;
            if (this.m.length <= 1) {
                read = this.k.read();
            } else if (this.n < this.o) {
                char[] cArr = this.m;
                int i2 = this.n;
                this.n = i2 + 1;
                read = cArr[i2];
            } else {
                this.o = this.k.read(this.m, 0, this.m.length);
                if (this.o <= 0) {
                    read = -1;
                } else {
                    read = this.m[0];
                }
                this.n = 1;
            }
            int[] iArr;
            if (read == 13) {
                this.F = true;
                iArr = this.D;
                i2 = this.E;
                this.E = i2 + 1;
                iArr[i2] = 10;
            } else {
                if (read != 10) {
                    int[] iArr2 = this.D;
                    int i3 = this.E;
                    this.E = i3 + 1;
                    iArr2[i3] = read;
                } else if (!this.F) {
                    iArr = this.D;
                    i2 = this.E;
                    this.E = i2 + 1;
                    iArr[i2] = 10;
                }
                this.F = false;
            }
        }
        return this.D[i];
    }

    private String h() throws IOException, XmlPullParserException {
        int i = this.s;
        int c = c(0);
        if ((c < 97 || c > 122) && !((c >= 65 && c <= 90) || c == 95 || c == 58 || c >= Downloads.STATUS_RUNNING || this.e)) {
            a("name expected");
        }
        while (true) {
            b(g());
            c = c(0);
            if ((c < 97 || c > 122) && ((c < 65 || c > 90) && !((c >= 48 && c <= 57) || c == 95 || c == 45 || c == 58 || c == 46 || c >= 183))) {
                String a = a(i);
                this.s = i;
                return a;
            }
        }
    }

    private void i() throws IOException {
        while (true) {
            int c = c(0);
            if (c <= 32 && c != -1) {
                g();
            } else {
                return;
            }
        }
    }

    public void setInput(Reader reader) throws XmlPullParserException {
        this.k = reader;
        this.p = 1;
        this.q = 0;
        this.t = 0;
        this.x = null;
        this.v = null;
        this.y = false;
        this.z = -1;
        this.l = null;
        this.b = null;
        this.c = null;
        if (reader != null) {
            this.n = 0;
            this.o = 0;
            this.E = 0;
            this.g = 0;
            this.f = new Hashtable();
            this.f.put("amp", "&");
            this.f.put("apos", "'");
            this.f.put("gt", ">");
            this.f.put("lt", "<");
            this.f.put("quot", "\"");
        }
    }

    /* JADX err: Inconsistent code. */
    public void setInput(java.io.InputStream r7, java.lang.String r8) throws org.xmlpull.v1.XmlPullParserException {
        /*
        r6 = this;
        r5 = -1;
        r0 = 0;
        r6.n = r0;
        r6.o = r0;
        if (r7 != 0) goto L_0x000e;
    L_0x0008:
        r0 = new java.lang.IllegalArgumentException;
        r0.<init>();
        throw r0;
    L_0x000e:
        if (r8 != 0) goto L_0x015a;
    L_0x0010:
        r1 = r0;
    L_0x0011:
        r0 = r6.o;	 Catch:{ Exception -> 0x006d }
        r2 = 4;
        if (r0 >= r2) goto L_0x001c;
    L_0x0016:
        r2 = r7.read();	 Catch:{ Exception -> 0x006d }
        if (r2 != r5) goto L_0x0057;
    L_0x001c:
        r0 = r6.o;	 Catch:{ Exception -> 0x006d }
        r2 = 4;
        if (r0 != r2) goto L_0x015a;
    L_0x0021:
        switch(r1) {
            case -131072: goto L_0x008b;
            case 60: goto L_0x0091;
            case 65279: goto L_0x0067;
            case 3932223: goto L_0x00ab;
            case 1006632960: goto L_0x009e;
            case 1006649088: goto L_0x00bf;
            case 1010792557: goto L_0x00d4;
            default: goto L_0x0024;
        };	 Catch:{ Exception -> 0x006d }
    L_0x0024:
        r0 = r8;
    L_0x0025:
        r2 = -65536; // 0xffffffffffff0000 float:NaN double:NaN;
        r2 = r2 & r1;
        r3 = -16842752; // 0xfffffffffeff0000 float:-1.6947657E38 double:NaN;
        if (r2 != r3) goto L_0x0121;
    L_0x002c:
        r0 = "UTF-16BE";
        r1 = r6.m;	 Catch:{ Exception -> 0x006d }
        r2 = 0;
        r3 = r6.m;	 Catch:{ Exception -> 0x006d }
        r4 = 2;
        r3 = r3[r4];	 Catch:{ Exception -> 0x006d }
        r3 = r3 << 8;
        r4 = r6.m;	 Catch:{ Exception -> 0x006d }
        r5 = 3;
        r4 = r4[r5];	 Catch:{ Exception -> 0x006d }
        r3 = r3 | r4;
        r3 = (char) r3;	 Catch:{ Exception -> 0x006d }
        r1[r2] = r3;	 Catch:{ Exception -> 0x006d }
        r1 = 1;
        r6.o = r1;	 Catch:{ Exception -> 0x006d }
    L_0x0044:
        if (r0 != 0) goto L_0x0048;
    L_0x0046:
        r0 = "UTF-8";
    L_0x0048:
        r1 = r6.o;	 Catch:{ Exception -> 0x006d }
        r2 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x006d }
        r2.<init>(r7, r0);	 Catch:{ Exception -> 0x006d }
        r6.setInput(r2);	 Catch:{ Exception -> 0x006d }
        r6.l = r8;	 Catch:{ Exception -> 0x006d }
        r6.o = r1;	 Catch:{ Exception -> 0x006d }
        return;
    L_0x0057:
        r0 = r1 << 8;
        r0 = r0 | r2;
        r1 = r6.m;	 Catch:{ Exception -> 0x006d }
        r3 = r6.o;	 Catch:{ Exception -> 0x006d }
        r4 = r3 + 1;
        r6.o = r4;	 Catch:{ Exception -> 0x006d }
        r2 = (char) r2;	 Catch:{ Exception -> 0x006d }
        r1[r3] = r2;	 Catch:{ Exception -> 0x006d }
        r1 = r0;
        goto L_0x0011;
    L_0x0067:
        r0 = "UTF-32BE";
        r1 = 0;
        r6.o = r1;	 Catch:{ Exception -> 0x006d }
        goto L_0x0044;
    L_0x006d:
        r0 = move-exception;
        r1 = new org.xmlpull.v1.XmlPullParserException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Invalid stream or encoding: ";
        r2 = r2.append(r3);
        r3 = r0.toString();
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2, r6, r0);
        throw r1;
    L_0x008b:
        r0 = "UTF-32LE";
        r1 = 0;
        r6.o = r1;	 Catch:{ Exception -> 0x006d }
        goto L_0x0044;
    L_0x0091:
        r0 = "UTF-32BE";
        r1 = r6.m;	 Catch:{ Exception -> 0x006d }
        r2 = 0;
        r3 = 60;
        r1[r2] = r3;	 Catch:{ Exception -> 0x006d }
        r1 = 1;
        r6.o = r1;	 Catch:{ Exception -> 0x006d }
        goto L_0x0044;
    L_0x009e:
        r0 = "UTF-32LE";
        r1 = r6.m;	 Catch:{ Exception -> 0x006d }
        r2 = 0;
        r3 = 60;
        r1[r2] = r3;	 Catch:{ Exception -> 0x006d }
        r1 = 1;
        r6.o = r1;	 Catch:{ Exception -> 0x006d }
        goto L_0x0044;
    L_0x00ab:
        r0 = "UTF-16BE";
        r1 = r6.m;	 Catch:{ Exception -> 0x006d }
        r2 = 0;
        r3 = 60;
        r1[r2] = r3;	 Catch:{ Exception -> 0x006d }
        r1 = r6.m;	 Catch:{ Exception -> 0x006d }
        r2 = 1;
        r3 = 63;
        r1[r2] = r3;	 Catch:{ Exception -> 0x006d }
        r1 = 2;
        r6.o = r1;	 Catch:{ Exception -> 0x006d }
        goto L_0x0044;
    L_0x00bf:
        r0 = "UTF-16LE";
        r1 = r6.m;	 Catch:{ Exception -> 0x006d }
        r2 = 0;
        r3 = 60;
        r1[r2] = r3;	 Catch:{ Exception -> 0x006d }
        r1 = r6.m;	 Catch:{ Exception -> 0x006d }
        r2 = 1;
        r3 = 63;
        r1[r2] = r3;	 Catch:{ Exception -> 0x006d }
        r1 = 2;
        r6.o = r1;	 Catch:{ Exception -> 0x006d }
        goto L_0x0044;
    L_0x00d4:
        r0 = r7.read();	 Catch:{ Exception -> 0x006d }
        if (r0 != r5) goto L_0x00dd;
    L_0x00da:
        r0 = r8;
        goto L_0x0025;
    L_0x00dd:
        r2 = r6.m;	 Catch:{ Exception -> 0x006d }
        r3 = r6.o;	 Catch:{ Exception -> 0x006d }
        r4 = r3 + 1;
        r6.o = r4;	 Catch:{ Exception -> 0x006d }
        r4 = (char) r0;	 Catch:{ Exception -> 0x006d }
        r2[r3] = r4;	 Catch:{ Exception -> 0x006d }
        r2 = 62;
        if (r0 != r2) goto L_0x00d4;
    L_0x00ec:
        r2 = new java.lang.String;	 Catch:{ Exception -> 0x006d }
        r0 = r6.m;	 Catch:{ Exception -> 0x006d }
        r3 = 0;
        r4 = r6.o;	 Catch:{ Exception -> 0x006d }
        r2.<init>(r0, r3, r4);	 Catch:{ Exception -> 0x006d }
        r0 = "encoding";
        r0 = r2.indexOf(r0);	 Catch:{ Exception -> 0x006d }
        if (r0 == r5) goto L_0x0024;
    L_0x00fe:
        r3 = r2.charAt(r0);	 Catch:{ Exception -> 0x006d }
        r4 = 34;
        if (r3 == r4) goto L_0x0111;
    L_0x0106:
        r3 = r2.charAt(r0);	 Catch:{ Exception -> 0x006d }
        r4 = 39;
        if (r3 == r4) goto L_0x0111;
    L_0x010e:
        r0 = r0 + 1;
        goto L_0x00fe;
    L_0x0111:
        r3 = r0 + 1;
        r0 = r2.charAt(r0);	 Catch:{ Exception -> 0x006d }
        r0 = r2.indexOf(r0, r3);	 Catch:{ Exception -> 0x006d }
        r0 = r2.substring(r3, r0);	 Catch:{ Exception -> 0x006d }
        goto L_0x0025;
    L_0x0121:
        r2 = -65536; // 0xffffffffffff0000 float:NaN double:NaN;
        r2 = r2 & r1;
        r3 = -131072; // 0xfffffffffffe0000 float:NaN double:NaN;
        if (r2 != r3) goto L_0x0142;
    L_0x0128:
        r0 = "UTF-16LE";
        r1 = r6.m;	 Catch:{ Exception -> 0x006d }
        r2 = 0;
        r3 = r6.m;	 Catch:{ Exception -> 0x006d }
        r4 = 3;
        r3 = r3[r4];	 Catch:{ Exception -> 0x006d }
        r3 = r3 << 8;
        r4 = r6.m;	 Catch:{ Exception -> 0x006d }
        r5 = 2;
        r4 = r4[r5];	 Catch:{ Exception -> 0x006d }
        r3 = r3 | r4;
        r3 = (char) r3;	 Catch:{ Exception -> 0x006d }
        r1[r2] = r3;	 Catch:{ Exception -> 0x006d }
        r1 = 1;
        r6.o = r1;	 Catch:{ Exception -> 0x006d }
        goto L_0x0044;
    L_0x0142:
        r1 = r1 & -256;
        r2 = -272908544; // 0xffffffffefbbbf00 float:-1.162092E29 double:NaN;
        if (r1 != r2) goto L_0x0044;
    L_0x0149:
        r0 = "UTF-8";
        r1 = r6.m;	 Catch:{ Exception -> 0x006d }
        r2 = 0;
        r3 = r6.m;	 Catch:{ Exception -> 0x006d }
        r4 = 3;
        r3 = r3[r4];	 Catch:{ Exception -> 0x006d }
        r1[r2] = r3;	 Catch:{ Exception -> 0x006d }
        r1 = 1;
        r6.o = r1;	 Catch:{ Exception -> 0x006d }
        goto L_0x0044;
    L_0x015a:
        r0 = r8;
        goto L_0x0044;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.framework.modules.search.a.a.setInput(java.io.InputStream, java.lang.String):void");
    }

    public boolean getFeature(String str) {
        if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(str)) {
            return this.d;
        }
        if (a(str, false, "relaxed")) {
            return this.e;
        }
        return false;
    }

    public String getInputEncoding() {
        return this.l;
    }

    public void defineEntityReplacementText(String str, String str2) throws XmlPullParserException {
        if (this.f == null) {
            throw new RuntimeException("entity replacement text must be defined after setInput!");
        }
        this.f.put(str, str2);
    }

    public Object getProperty(String str) {
        if (a(str, true, "xmldecl-version")) {
            return this.b;
        }
        if (a(str, true, "xmldecl-standalone")) {
            return this.c;
        }
        if (a(str, true, "location")) {
            return this.a != null ? this.a : this.k.toString();
        } else {
            return null;
        }
    }

    public int getNamespaceCount(int i) {
        if (i <= this.g) {
            return this.j[i];
        }
        throw new IndexOutOfBoundsException();
    }

    public String getNamespacePrefix(int i) {
        return this.i[i << 1];
    }

    public String getNamespaceUri(int i) {
        return this.i[(i << 1) + 1];
    }

    public String getNamespace(String str) {
        if ("xml".equals(str)) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        if ("xmlns".equals(str)) {
            return "http://www.w3.org/2000/xmlns/";
        }
        for (int namespaceCount = (getNamespaceCount(this.g) << 1) - 2; namespaceCount >= 0; namespaceCount -= 2) {
            if (str == null) {
                if (this.i[namespaceCount] == null) {
                    return this.i[namespaceCount + 1];
                }
            } else if (str.equals(this.i[namespaceCount])) {
                return this.i[namespaceCount + 1];
            }
        }
        return null;
    }

    public int getDepth() {
        return this.g;
    }

    public String getPositionDescription() {
        StringBuilder stringBuilder = new StringBuilder(this.t < TYPES.length ? TYPES[this.t] : EnvironmentCompat.MEDIA_UNKNOWN);
        stringBuilder.append(' ');
        if (this.t == 2 || this.t == 3) {
            if (this.y) {
                stringBuilder.append("(empty) ");
            }
            stringBuilder.append('<');
            if (this.t == 3) {
                stringBuilder.append('/');
            }
            if (this.w != null) {
                stringBuilder.append("{" + this.v + "}" + this.w + ":");
            }
            stringBuilder.append(this.x);
            int i = this.z << 2;
            for (int i2 = 0; i2 < i; i2 += 4) {
                stringBuilder.append(' ');
                if (this.A[i2 + 1] != null) {
                    stringBuilder.append("{" + this.A[i2] + "}" + this.A[i2 + 1] + ":");
                }
                stringBuilder.append(this.A[i2 + 2] + "='" + this.A[i2 + 3] + "'");
            }
            stringBuilder.append('>');
        } else if (this.t != 7) {
            if (this.t != 4) {
                stringBuilder.append(getText());
            } else if (this.u) {
                stringBuilder.append("(whitespace)");
            } else {
                String text = getText();
                if (text.length() > 16) {
                    text = text.substring(0, 16) + "...";
                }
                stringBuilder.append(text);
            }
        }
        stringBuilder.append("@" + this.p + ":" + this.q);
        if (this.a != null) {
            stringBuilder.append(" in ");
            stringBuilder.append(this.a);
        } else if (this.k != null) {
            stringBuilder.append(" in ");
            stringBuilder.append(this.k.toString());
        }
        return stringBuilder.toString();
    }

    public int getLineNumber() {
        return this.p;
    }

    public int getColumnNumber() {
        return this.q;
    }

    public boolean isWhitespace() throws XmlPullParserException {
        if (!(this.t == 4 || this.t == 7 || this.t == 5)) {
            b("Wrong event type");
        }
        return this.u;
    }

    public String getText() {
        return (this.t < 4 || (this.t == 6 && this.G)) ? null : a(0);
    }

    public char[] getTextCharacters(int[] iArr) {
        if (this.t < 4) {
            iArr[0] = -1;
            iArr[1] = -1;
            return null;
        } else if (this.t == 6) {
            iArr[0] = 0;
            iArr[1] = this.x.length();
            return this.x.toCharArray();
        } else {
            iArr[0] = 0;
            iArr[1] = this.s;
            return this.r;
        }
    }

    public String getNamespace() {
        return this.v;
    }

    public String getName() {
        return this.x;
    }

    public String getPrefix() {
        return this.w;
    }

    public boolean isEmptyElementTag() throws XmlPullParserException {
        if (this.t != 2) {
            b("Wrong event type");
        }
        return this.y;
    }

    public int getAttributeCount() {
        return this.z;
    }

    public String getAttributeType(int i) {
        return "CDATA";
    }

    public boolean isAttributeDefault(int i) {
        return false;
    }

    public String getAttributeNamespace(int i) {
        if (i < this.z) {
            return this.A[i << 2];
        }
        throw new IndexOutOfBoundsException();
    }

    public String getAttributeName(int i) {
        if (i < this.z) {
            return this.A[(i << 2) + 2];
        }
        throw new IndexOutOfBoundsException();
    }

    public String getAttributePrefix(int i) {
        if (i < this.z) {
            return this.A[(i << 2) + 1];
        }
        throw new IndexOutOfBoundsException();
    }

    public String getAttributeValue(int i) {
        if (i < this.z) {
            return this.A[(i << 2) + 3];
        }
        throw new IndexOutOfBoundsException();
    }

    public String getAttributeValue(String str, String str2) {
        int i = (this.z << 2) - 4;
        while (i >= 0) {
            if (this.A[i + 2].equals(str2) && (str == null || this.A[i].equals(str))) {
                return this.A[i + 3];
            }
            i -= 4;
        }
        return null;
    }

    public int getEventType() throws XmlPullParserException {
        return this.t;
    }

    public int next() throws XmlPullParserException, IOException {
        this.s = 0;
        this.u = true;
        int i = 9999;
        this.H = false;
        while (true) {
            c();
            if (this.t < i) {
                i = this.t;
            }
            if (i > 6 || (i >= 4 && e() >= 4)) {
            }
        }
        this.t = i;
        if (this.t > 4) {
            this.t = 4;
        }
        return this.t;
    }

    public int nextToken() throws XmlPullParserException, IOException {
        this.u = true;
        this.s = 0;
        this.H = true;
        c();
        return this.t;
    }

    public int nextTag() throws XmlPullParserException, IOException {
        next();
        if (this.t == 4 && this.u) {
            next();
        }
        if (!(this.t == 3 || this.t == 2)) {
            b("unexpected type");
        }
        return this.t;
    }

    public void require(int i, String str, String str2) throws XmlPullParserException, IOException {
        if (i != this.t || ((str != null && !str.equals(getNamespace())) || (str2 != null && !str2.equals(getName())))) {
            b("expected: " + TYPES[i] + " {" + str + "}" + str2);
        }
    }

    public String nextText() throws XmlPullParserException, IOException {
        String text;
        if (this.t != 2) {
            b("precondition: START_TAG");
        }
        next();
        if (this.t == 4) {
            text = getText();
            next();
        } else {
            text = "";
        }
        if (this.t != 3) {
            b("END_TAG expected");
        }
        return text;
    }

    public void setFeature(String str, boolean z) throws XmlPullParserException {
        if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(str)) {
            this.d = z;
        } else if (a(str, false, "relaxed")) {
            this.e = z;
        } else {
            b("unsupported feature: " + str);
        }
    }

    public void setProperty(String str, Object obj) throws XmlPullParserException {
        if (a(str, true, "location")) {
            this.a = obj;
            return;
        }
        throw new XmlPullParserException("unsupported property: " + str);
    }

    public void a() throws XmlPullParserException, IOException {
        require(2, null, null);
        int i = 1;
        while (i > 0) {
            int next = next();
            if (next == 3) {
                i--;
            } else if (next == 2) {
                i++;
            }
        }
    }
}
