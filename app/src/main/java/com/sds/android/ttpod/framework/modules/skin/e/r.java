package com.sds.android.ttpod.framework.modules.skin.e;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* TrcSentence */
public class r extends f {
    private ArrayList<s> c;

    public r(r rVar) {
        super(rVar);
        this.c = (ArrayList) a(rVar.c);
    }

    private static <T> List<T> a(List<T> list) {
        if (list != null) {
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                new ObjectOutputStream(byteArrayOutputStream).writeObject(list);
                return (List) new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public r(long j, String str, int i, int i2, int i3) {
        super(j, str, i, i2, i3);
    }

    public ArrayList<s> h() {
        return this.c;
    }

    public void a(s sVar) {
        if (this.c == null) {
            this.c = new ArrayList(8);
        }
        this.c.add(sVar);
    }

    public int i() {
        return this.c == null ? this.a : j();
    }

    private int j() {
        Iterator it = this.c.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((s) it.next()).c() + i;
        }
        return i;
    }

    public void a(int i, int i2) {
        a(new s(i, i2));
    }

    protected String c() {
        if (this.c == null) {
            return this.b;
        }
        StringBuilder stringBuilder = new StringBuilder(this.c.size() * 7);
        Iterator it = this.c.iterator();
        int i = 0;
        while (it.hasNext()) {
            stringBuilder.append(String.format("<%d>%s", new Object[]{Integer.valueOf(((s) it.next()).c()), this.b.substring(i, ((s) it.next()).b() + i)}));
            i = r3;
        }
        return stringBuilder.toString();
    }

    public int c(int i) {
        return this.c == null ? super.c(i) : d(i);
    }

    private int d(int i) {
        Iterator it = this.c.iterator();
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            s sVar = (s) it.next();
            int c = sVar.c();
            if (i <= i2 + c) {
                return i3 + ((sVar.a() * (i - i2)) / c);
            }
            i2 += c;
            i3 = sVar.a() + i3;
        }
        return i3;
    }

    public int[] a(r rVar, int i, int i2) {
        int c;
        int i3 = (i + i2) - 1;
        Iterator it = this.c.iterator();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (it.hasNext()) {
            int i7;
            s sVar = (s) it.next();
            int b = sVar.b();
            c = sVar.c();
            if (b == 0) {
                i7 = i5;
            } else {
                i7 = (i5 + b) - 1;
            }
            if (i < i5 || i3 > i7) {
                if (i >= i5 && i <= i7) {
                    int i8 = (b - i) + i5;
                    if (i8 == 1 && this.b.charAt(i) == ' ') {
                        i6 = 1;
                    } else {
                        if (i != i5) {
                            c = (c * i8) / b;
                        }
                        rVar.a(i8, c);
                        i4 = c;
                    }
                    c = i6;
                    i5 = i4;
                } else if (i >= i5 || i3 < i5) {
                    c = i6;
                    i5 = i4;
                } else if (b == 0) {
                    rVar.a(0, c);
                    c = i6;
                    i5 = i4;
                } else {
                    i5 = (i3 - i5) + 1;
                    if (i5 >= b) {
                        rVar.a(b, c);
                        c += i4;
                        if (i5 == b) {
                            break;
                        }
                        i5 = c;
                        c = i6;
                    } else {
                        if (!(b - i5 == 1 && this.b.charAt(i7) == ' ')) {
                            c = (c * i5) / b;
                        }
                        rVar.a(i5, c);
                        c += i4;
                    }
                }
                i4 = i5;
                i5 = (b == 0 ? 0 : 1) + i7;
                i6 = c;
            } else {
                if (b > 0) {
                    c = (c * i2) / b;
                }
                rVar.a(i2, c);
            }
        }
        c = i4;
        return new int[]{c, i6};
    }

    public void a(l lVar) {
        if (this.c != null) {
            Iterator it = this.c.iterator();
            int i = 0;
            while (it.hasNext()) {
                s sVar = (s) it.next();
                int b = sVar.b() + i;
                sVar.a(lVar.a(this.b.substring(i, b)));
                i = b;
            }
        }
    }

    public void b(int i) {
        super.b(i);
        if (this.c != null && !this.c.isEmpty()) {
            int size = this.c.size();
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                int c = i3 + ((s) this.c.get(i2)).c();
                if (c > this.a) {
                    b(i2, this.a - i3);
                    return;
                } else {
                    i2++;
                    i3 = c;
                }
            }
        }
    }

    private void b(int i, int i2) {
        s sVar;
        int i3 = 0;
        int i4 = 0;
        for (int size = this.c.size() - 1; size >= i; size--) {
            sVar = (s) this.c.get(size);
            i4 += sVar.a();
            i3 += sVar.b();
            this.c.remove(size);
        }
        sVar = new s(i3, i2);
        sVar.a(i4);
        this.c.add(sVar);
    }
}
