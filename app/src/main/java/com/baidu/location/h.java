package com.baidu.location;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

class h {
    private static int a = 100;
    private static String do = (f.ac + "/juz.dat");
    private static float for = 299.0f;
    private static String if = f.v;
    private static ArrayList int = null;
    private static int new = 64;
    private static long try = 64;

    private static class a {
        private int a = 0;
        private int do = 0;
        private float for = 0.0f;
        private int if = 0;
        private double int = 0.0d;
        private double new = 0.0d;
        private int try = 0;

        public a(int i, int i2, int i3, int i4, double d, double d2, float f) {
            this.do = i;
            this.try = i2;
            this.if = i3;
            this.a = i4;
            this.new = d;
            this.int = d2;
            this.for = f;
        }

        public boolean a(int i, int i2, int i3) {
            return this.a == i && this.do == i2 && this.try == i3;
        }
    }

    h() {
    }

    public static String a(int i, int i2, int i3) {
        if (if(i, i2, i3) == null) {
            return null;
        }
        return String.format("{\"result\":{\"time\":\"" + j.for() + "\",\"error\":\"65\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%d\"}}", new Object[]{Double.valueOf(r0.new), Double.valueOf(r0.int), Integer.valueOf((int) r0.for)});
    }

    private static void a() {
        File file = new File(do);
        try {
            if (file.exists()) {
                if (int != null) {
                    int.clear();
                    int = null;
                }
                int = new ArrayList();
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(0);
                int readInt = randomAccessFile.readInt();
                j.if(if, "size of loc cache is " + readInt);
                for (int i = 0; i < readInt; i++) {
                    randomAccessFile.seek(try + ((long) (new * i)));
                    float readFloat = randomAccessFile.readFloat();
                    int readInt2 = randomAccessFile.readInt();
                    double readDouble = randomAccessFile.readDouble();
                    int.add(new a(randomAccessFile.readInt(), randomAccessFile.readInt(), readInt2, randomAccessFile.readInt(), readDouble, randomAccessFile.readDouble(), readFloat));
                }
                randomAccessFile.close();
                return;
            }
            j.if(if, "locCache file does not exists...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(com.baidu.location.c.a aVar, double d, double d2, float f) {
        if (aVar != null) {
            float f2 = f < for ? for : f;
            a aVar2 = if(aVar.if, aVar.for, aVar.try);
            if (aVar2 == null) {
                if (int == null) {
                    int = new ArrayList();
                }
                int.add(new a(aVar.for, aVar.try, aVar.do, aVar.if, d, d2, f2));
                if (int.size() > a) {
                    int.remove(0);
                }
                j.if(if, "locCache add new cell info into loc cache ...");
                return;
            }
            aVar2.new = d;
            aVar2.int = d2;
            aVar2.for = f2;
            j.if(if, "locCache update loc cache ...");
        }
    }

    private static void do() {
        if (int != null) {
            File file = new File(do);
            try {
                if (!file.exists()) {
                    File file2 = new File(f.ac);
                    if (!file2.exists()) {
                        j.if(if, "locCache make dirs " + file2.mkdirs());
                    }
                    if (file.createNewFile()) {
                        j.if(if, "locCache create loc cache file success ...");
                    } else {
                        j.if(if, "locCache create loc cache file failure ...");
                        return;
                    }
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                if (randomAccessFile.length() < 1) {
                    randomAccessFile.writeInt(0);
                }
                int size = int.size() - 1;
                int i = 0;
                while (size >= 0) {
                    a aVar = (a) int.get(size);
                    if (aVar != null) {
                        randomAccessFile.seek(try + ((long) (new * (size % a))));
                        randomAccessFile.writeFloat(aVar.for);
                        randomAccessFile.writeInt(aVar.if);
                        randomAccessFile.writeDouble(aVar.new);
                        randomAccessFile.writeInt(aVar.a);
                        randomAccessFile.writeDouble(aVar.int);
                        randomAccessFile.writeInt(aVar.do);
                        randomAccessFile.writeInt(aVar.try);
                        j.if(if, "add a new cell loc into file ...");
                    }
                    size--;
                    i++;
                }
                randomAccessFile.seek(0);
                randomAccessFile.writeInt(i);
                randomAccessFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static a if(int i, int i2, int i3) {
        try {
            if (int == null || int.size() < 1) {
                a();
            }
            if (int == null || int.size() < 1) {
                return null;
            }
            for (int size = int.size() - 1; size >= 0; size--) {
                a aVar = (a) int.get(size);
                if (aVar != null && aVar.a(i, i2, i3)) {
                    return aVar;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void if() {
        do();
    }
}
