package com.baidu.location;

class Jni {
    private static int a = 14;
    private static int byte = 2;
    private static int case = 0;
    private static int do = 1024;
    private static int for = 11;
    private static int if = 13;
    private static int int = 12;
    private static boolean new;
    private static int try = 1;

    static {
        new = false;
        try {
            System.loadLibrary("locSDK3");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            new = true;
        }
    }

    Jni() {
    }

    private static native String a(byte[] bArr, int i);

    private static native String b(double d, double d2, int i, int i2);

    public static String if(String str) {
        int i = 740;
        int i2 = 0;
        if (new) {
            return "err!";
        }
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[do];
        int length = bytes.length;
        if (length <= 740) {
            i = length;
        }
        length = 0;
        while (i2 < i) {
            if (bytes[i2] != (byte) 0) {
                bArr[length] = bytes[i2];
                length++;
            } else {
                j.if(f.v, "\\0 found in string");
            }
            i2++;
        }
        j.if(f.v, "number:" + bytes.length);
        return a(bArr, 132456) + "|tp=3";
    }

    public static double[] if(double d, double d2, String str) {
        double[] dArr = new double[]{0.0d, 0.0d};
        if (new) {
            return dArr;
        }
        int i = -1;
        if (str.equals("bd09")) {
            i = case;
        } else if (str.equals("bd09ll")) {
            i = try;
        } else if (str.equals("gcj02")) {
            i = byte;
        } else if (str.equals("gps2gcj")) {
            i = for;
        } else if (str.equals("bd092gcj")) {
            i = int;
        } else if (str.equals("bd09ll2gcj")) {
            i = if;
        }
        j.if(f.v, "type:" + i);
        try {
            String[] split = b(d, d2, i, 132456).split(":");
            dArr[0] = Double.parseDouble(split[0]);
            dArr[1] = Double.parseDouble(split[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dArr;
    }
}
