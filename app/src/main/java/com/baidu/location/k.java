package com.baidu.location;

import android.location.Location;
import com.baidu.location.c.a;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

class k {
    private static Location a = null;
    private static int b = 8;
    private static int byte = 8;
    private static int c = 0;
    private static int case = 1024;
    private static int char = 64;
    private static final int d = 1040;
    private static String do = f.v;
    private static Location e = null;
    private static File else = null;
    private static final int f = 32;
    private static final int for = 2048;
    private static c g = null;
    private static final int goto = 128;
    private static ArrayList h = new ArrayList();
    private static int i = 5;
    private static final int if = 2048;
    private static double int = 100.0d;
    private static ArrayList j = new ArrayList();
    private static final String k = (f.ac + "/yol.dat");
    private static int l = 256;
    private static ArrayList long = new ArrayList();
    private static ArrayList m = new ArrayList();
    private static double n = 0.1d;
    private static double new = 0.0d;
    private static int o = 1024;
    private static final String p = (f.ac + "/yor.dat");
    private static Location q = null;
    private static ArrayList r = new ArrayList();
    private static final int s = 2048;
    private static final String t = (f.ac + "/yom.dat");
    private static int try = 16;
    private static final String u = (f.ac + "/yoh.dat");
    private static int v = 128;
    private static double void = 30.0d;
    private static int w = 512;
    private static ArrayList x = new ArrayList();
    private static String y = (f.ac + "/yo.dat");

    k() {
    }

    private static int a(List list, int i) {
        if (list == null || i > 256 || i < 0) {
            return -1;
        }
        try {
            if (else == null) {
                else = new File(y);
                if (!else.exists()) {
                    j.if(do, "upload man readfile does not exist...");
                    else = null;
                    return -2;
                }
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(else, "rw");
            if (randomAccessFile.length() < 1) {
                randomAccessFile.close();
                return -3;
            }
            randomAccessFile.seek((long) i);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            int readInt4 = randomAccessFile.readInt();
            long readLong = randomAccessFile.readLong();
            if (!a(readInt, readInt2, readInt3, readInt4, readLong) || readInt2 < 1) {
                randomAccessFile.close();
                return -4;
            }
            byte[] bArr = new byte[o];
            int i2 = readInt2;
            readInt2 = byte;
            while (readInt2 > 0 && i2 > 0) {
                randomAccessFile.seek(((long) ((((readInt + i2) - 1) % readInt3) * readInt4)) + readLong);
                int readInt5 = randomAccessFile.readInt();
                if (readInt5 > 0 && readInt5 < readInt4) {
                    randomAccessFile.read(bArr, 0, readInt5);
                    if (bArr[readInt5 - 1] == (byte) 0) {
                        list.add(new String(bArr, 0, readInt5 - 1));
                    }
                }
                readInt2--;
                i2--;
            }
            randomAccessFile.seek((long) i);
            randomAccessFile.writeInt(readInt);
            randomAccessFile.writeInt(i2);
            randomAccessFile.writeInt(readInt3);
            randomAccessFile.writeInt(readInt4);
            randomAccessFile.writeLong(readLong);
            randomAccessFile.close();
            return byte - readInt2;
        } catch (Exception e) {
            return -5;
        }
    }

    public static String a(int i) {
        String str;
        List list;
        if (i == 1) {
            str = u;
            list = x;
        } else if (i == 2) {
            str = t;
            list = j;
        } else if (i == 3) {
            str = k;
            list = r;
        } else if (i != 4) {
            return null;
        } else {
            str = p;
            list = r;
        }
        if (list == null) {
            return null;
        }
        if (list.size() < 1) {
            j.if(do, str + ":get data from sd file...");
            a(str, list);
        }
        if (list.size() <= 0) {
            return null;
        }
        String str2 = (String) list.get(0);
        list.remove(0);
        return str2;
    }

    public static void a() {
        b = 0;
        j.if(do, "flush data...");
        a(1, false);
        a(2, false);
        a(3, false);
        b = 8;
    }

    public static void a(double d, double d2, double d3, double d4) {
        if (d <= 0.0d) {
            d = new;
        }
        new = d;
        n = d2;
        if (d3 <= 20.0d) {
            d3 = void;
        }
        void = d3;
        int = d4;
    }

    public static void a(int i, int i2) {
    }

    public static void a(int i, int i2, boolean z) {
    }

    public static void a(int i, boolean z) {
        String str;
        Object obj;
        String str2;
        if (i == 1) {
            str2 = u;
            if (!z) {
                str = str2;
                List list = x;
            } else {
                return;
            }
        } else if (i == 2) {
            str2 = t;
            if (z) {
                str = str2;
                obj = x;
            } else {
                str = str2;
                obj = j;
            }
        } else if (i == 3) {
            str2 = k;
            if (z) {
                str = str2;
                obj = j;
            } else {
                str = str2;
                obj = r;
            }
        } else if (i == 4) {
            str2 = p;
            if (z) {
                str = str2;
                obj = r;
            } else {
                return;
            }
        } else {
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            a(str);
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(4);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            int readInt4 = randomAccessFile.readInt();
            int readInt5 = randomAccessFile.readInt();
            int size = list.size();
            int i2 = readInt5;
            while (size > b) {
                j.if(do, "write new data...");
                readInt5 = z ? i2 + 1 : i2;
                byte[] bytes;
                if (readInt3 >= readInt) {
                    if (!z) {
                        obj = 1;
                        i2 = readInt5;
                        break;
                    }
                    randomAccessFile.seek((long) ((readInt4 * readInt2) + 128));
                    bytes = (((String) list.get(0)) + '\u0000').getBytes();
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.write(bytes, 0, bytes.length);
                    list.remove(0);
                    i2 = readInt4 + 1;
                    if (i2 > readInt3) {
                        i2 = 0;
                    }
                    readInt4 = readInt3;
                } else {
                    randomAccessFile.seek((long) ((readInt2 * readInt3) + 128));
                    bytes = (((String) list.get(0)) + '\u0000').getBytes();
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.write(bytes, 0, bytes.length);
                    list.remove(0);
                    int i3 = readInt4;
                    readInt4 = readInt3 + 1;
                    i2 = i3;
                }
                size--;
                readInt3 = readInt4;
                readInt4 = i2;
                i2 = readInt5;
            }
            obj = null;
            randomAccessFile.seek(12);
            randomAccessFile.writeInt(readInt3);
            randomAccessFile.writeInt(readInt4);
            randomAccessFile.writeInt(i2);
            randomAccessFile.close();
            if (obj != null && i < 4) {
                a(i + 1, true);
            }
        } catch (Exception e) {
        }
    }

    public static void a(a aVar, c cVar, Location location, String str) {
        c cVar2 = null;
        if (!j.O) {
            return;
        }
        if (j.I == 3 && !a(location, cVar) && !a(location, false)) {
            return;
        }
        String a;
        if (aVar != null && aVar.do()) {
            if (!a(location, cVar)) {
                cVar = null;
            }
            a = j.a(aVar, cVar, location, str, 1);
            if (a != null) {
                for(Jni.if(a));
                q = location;
                e = location;
                if (cVar != null) {
                    g = cVar;
                }
            }
        } else if (cVar != null && cVar.if() && a(location, cVar)) {
            a aVar2;
            if (a(location)) {
                aVar2 = aVar;
            }
            a = j.a(aVar2, cVar, location, str, 2);
            if (a != null) {
                a = Jni.if(a);
                j.if(do, "upload size:" + a.length());
                do(a);
                a = location;
                e = location;
                if (cVar != null) {
                    g = cVar;
                }
            }
        } else {
            if (!a(location)) {
                aVar = null;
            }
            if (a(location, cVar)) {
                cVar2 = cVar;
            }
            if (aVar != null || cVar2 != null) {
                String a2 = j.a(aVar, cVar2, location, str, 3);
                if (a2 != null) {
                    int(Jni.if(a2));
                    e = location;
                    if (cVar2 != null) {
                        g = cVar2;
                    }
                }
            }
        }
    }

    public static void a(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                File file2 = new File(f.ac);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!file.createNewFile()) {
                    file = null;
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(0);
                randomAccessFile.writeInt(32);
                randomAccessFile.writeInt(2048);
                randomAccessFile.writeInt(d);
                randomAccessFile.writeInt(0);
                randomAccessFile.writeInt(0);
                randomAccessFile.writeInt(0);
                randomAccessFile.close();
            }
        } catch (Exception e) {
        }
    }

    public static void a(String str, int i) {
    }

    public static void a(String str, int i, boolean z) {
    }

    private static boolean a(int i, int i2, int i3, int i4, long j) {
        return i >= 0 && i < i3 && i2 >= 0 && i2 <= i3 && i3 >= 0 && i3 <= 1024 && i4 >= 128 && i4 <= 1024;
    }

    private static boolean a(Location location) {
        if (location == null) {
            return false;
        }
        if (q == null || e == null) {
            q = location;
            return true;
        }
        double distanceTo = (double) location.distanceTo(q);
        return ((double) location.distanceTo(e)) > ((distanceTo * ((double) j.s)) + ((((double) j.u) * distanceTo) * distanceTo)) + ((double) j.r);
    }

    private static boolean a(Location location, c cVar) {
        if (location == null || cVar == null || cVar.for == null || cVar.for.isEmpty() || cVar.do(g)) {
            return false;
        }
        if (a != null) {
            return true;
        }
        a = location;
        return true;
    }

    public static boolean a(Location location, boolean z) {
        return b.a(e, location, z);
    }

    public static boolean a(String str, List list) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(8);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            byte[] bArr = new byte[o];
            int i = readInt2;
            readInt2 = b + 1;
            boolean z = false;
            while (readInt2 > 0 && i > 0) {
                if (i < readInt3) {
                    readInt3 = 0;
                }
                try {
                    randomAccessFile.seek((long) (((i - 1) * readInt) + 128));
                    int readInt4 = randomAccessFile.readInt();
                    if (readInt4 > 0 && readInt4 < readInt) {
                        randomAccessFile.read(bArr, 0, readInt4);
                        if (bArr[readInt4 - 1] == (byte) 0) {
                            list.add(new String(bArr, 0, readInt4 - 1));
                            z = true;
                        }
                    }
                    readInt2--;
                    i--;
                } catch (Exception e) {
                    return z;
                }
            }
            randomAccessFile.seek(12);
            randomAccessFile.writeInt(i);
            randomAccessFile.writeInt(readInt3);
            randomAccessFile.close();
            return z;
        } catch (Exception e2) {
            return false;
        }
    }

    public static String do() {
        return int();
    }

    private static void do(String str) {
        if(str);
    }

    public static void for() {
    }

    private static void for(String str) {
        if(str);
    }

    public static String if() {
        RandomAccessFile randomAccessFile;
        int readInt;
        File file = new File(t);
        if (file.exists()) {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(20);
                readInt = randomAccessFile.readInt();
                if (readInt > 128) {
                    String str = "&p1=" + readInt;
                    randomAccessFile.seek(20);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.close();
                    return str;
                }
            } catch (Exception e) {
            }
        }
        file = new File(k);
        if (file.exists()) {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(20);
                readInt = randomAccessFile.readInt();
                if (readInt > 256) {
                    str = "&p2=" + readInt;
                    randomAccessFile.seek(20);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.close();
                    return str;
                }
            } catch (Exception e2) {
            }
        }
        file = new File(p);
        if (!file.exists()) {
            return null;
        }
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(20);
            readInt = randomAccessFile.readInt();
            if (readInt <= 512) {
                return null;
            }
            str = "&p3=" + readInt;
            randomAccessFile.seek(20);
            randomAccessFile.writeInt(0);
            randomAccessFile.close();
            return str;
        } catch (Exception e3) {
            return null;
        }
    }

    public static void if(String str) {
        List list;
        int i = j.l;
        if (i == 1) {
            list = x;
        } else if (i == 2) {
            list = j;
        } else if (i == 3) {
            list = r;
        } else {
            return;
        }
        if (list != null) {
            j.if(do, "insert2HighPriorityQueue...");
            if (list.size() <= try) {
                list.add(str);
            }
            if (list.size() >= try) {
                a(i, false);
            }
            while (list.size() > try) {
                list.remove(0);
            }
        }
    }

    public static String int() {
        String str = null;
        for (int i = 1; i < 5; i++) {
            str = a(i);
            if (str != null) {
                return str;
            }
        }
        j.if(do, "read the old file data...");
        a(r, char);
        if (r.size() > 0) {
            str = (String) r.get(0);
            r.remove(0);
        }
        if (str != null) {
            return str;
        }
        a(r, c);
        if (r.size() > 0) {
            str = (String) r.get(0);
            r.remove(0);
        }
        if (str != null) {
            return str;
        }
        a(r, v);
        if (r.size() <= 0) {
            return str;
        }
        str = (String) r.get(0);
        r.remove(0);
        return str;
    }

    private static void int(String str) {
        if(str);
    }

    public static void new() {
    }
}
