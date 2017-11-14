package com.baidu.location;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.tencent.open.yyb.TitleBar;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.io.File;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.UUID;

class j {
    public static String A = "no";
    public static final boolean B = false;
    public static float C = TitleBar.SHAREBTN_RIGHT_MARGIN;
    public static float D = 2.0f;
    private static boolean E = false;
    public static float F = 2.3f;
    public static boolean G = true;
    public static boolean H = false;
    public static int I = 0;
    public static double J = 0.0d;
    public static int K = 10;
    public static int L = 1000;
    public static long M = 1200000;
    public static String N = "http://loc.map.baidu.com/sdk_ep.php";
    public static boolean O = true;
    public static boolean P = true;
    public static float Q = 200.0f;
    public static boolean R = true;
    public static int S = 5000;
    private static final int T = 128;
    public static float U = 3.8f;
    public static int V = 20;
    public static float W = TitleBar.SHAREBTN_RIGHT_MARGIN;
    public static int X = 2;
    public static int Y = 16;
    public static double Z = 0.0d;
    public static float a = TTFMImageUtils.Middle_Scale;
    public static int aa = SecExceptionCode.SEC_ERROR_STA_ENC;
    public static boolean ab = false;
    public static long ac = 20;
    public static int ad = 70;
    public static int ae = 70;
    public static int af = 0;
    public static boolean ag = false;
    public static String ah = "gcj02";
    public static float ai = 50.0f;
    private static Process aj = null;
    public static String ak = null;
    public static long al = 300000;
    public static float am = 1.1f;
    public static String b = null;
    public static float byte = 0.9f;
    public static float c = 2.2f;
    public static final boolean case = false;
    private static String char = "http://loc.map.baidu.com/sdk.php";
    public static boolean d = false;
    public static String do = null;
    public static int e = 0;
    private static String else = "[baidu_location_service]";
    private static String f = f.v;
    public static int for = 20;
    public static int g = 0;
    public static int goto = 6;
    public static int h = 0;
    private static final String i = (f.ac + "/con.dat");
    public static double if = 0.0d;
    public static int int = 7;
    public static byte[] j = null;
    private static boolean k = true;
    public static int l = 3;
    public static int long = 120;
    public static boolean m = true;
    public static boolean n = false;
    public static int new = -1;
    public static double o = 0.0d;
    public static int p = 3;
    public static int q = 100;
    public static int r = 30;
    public static float s = 0.1f;
    public static int t = -1;
    public static boolean try = true;
    public static float u = 0.0f;
    public static int v = 60;
    public static boolean void = true;
    public static float w = 6.0f;
    public static boolean x = true;
    public static final boolean y = false;
    public static boolean z = true;

    public static class a {
        private static final boolean a = false;
        private static final String if = a.class.getSimpleName();

        private static String a(Context context) {
            return b.a(context);
        }

        public static String if(Context context) {
            String a = a(context);
            String str = b.do(context);
            if (TextUtils.isEmpty(str)) {
                str = FeedbackItem.STATUS_WAITING;
            }
            return a + "|" + new StringBuffer(str).reverse().toString();
        }
    }

    public static class b {
        private static final String a = "a";
        private static final String do = "bids";
        private static final String for = "i";
        private static final String if = "DeviceId";

        private b() {
        }

        public static String a(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(do, 0);
            String string = sharedPreferences.getString(for, null);
            if (string == null) {
                string = do(context);
                Editor edit = sharedPreferences.edit();
                edit.putString(for, string);
                edit.commit();
            }
            String string2 = sharedPreferences.getString(a, null);
            if (string2 == null) {
                string2 = if(context);
                Editor edit2 = sharedPreferences.edit();
                edit2.putString(a, string2);
                edit2.commit();
            }
            String str = "";
            str = j.a(("com.baidu" + string + string2).getBytes(), true);
            CharSequence string3 = System.getString(context.getContentResolver(), str);
            if (!TextUtils.isEmpty(string3)) {
                return string3;
            }
            string = j.a((string + string2 + UUID.randomUUID().toString()).getBytes(), true);
            System.putString(context.getContentResolver(), str, string);
            return !string.equals(System.getString(context.getContentResolver(), str)) ? str : string;
        }

        public static String do(Context context) {
            String str = "";
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return str;
            }
            Object deviceId = telephonyManager.getDeviceId();
            return TextUtils.isEmpty(deviceId) ? "" : deviceId;
        }

        public static String if(Context context) {
            String str = "";
            Object string = Secure.getString(context.getContentResolver(), "android_id");
            return TextUtils.isEmpty(string) ? "" : string;
        }
    }

    j() {
    }

    static float a(String str, String str2, String str3) {
        float f = Float.MIN_VALUE;
        if (!(str == null || str.equals(""))) {
            int indexOf = str.indexOf(str2);
            if (indexOf != -1) {
                indexOf += str2.length();
                int indexOf2 = str.indexOf(str3, indexOf);
                if (indexOf2 != -1) {
                    String substring = str.substring(indexOf, indexOf2);
                    if (!(substring == null || substring.equals(""))) {
                        try {
                            f = Float.parseFloat(substring);
                        } catch (NumberFormatException e) {
                            if(f, "util numberFormatException, intStr : " + substring);
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return f;
    }

    static String a() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(5);
        int i2 = instance.get(1);
        int i3 = instance.get(2) + 1;
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        return String.format("%d_%d_%d_%d_%d_%d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)});
    }

    public static String a(com.baidu.location.c.a aVar, c cVar, Location location, String str, int i) {
        String aVar2;
        StringBuffer stringBuffer = new StringBuffer();
        if (aVar != null) {
            aVar2 = aVar.toString();
            if (aVar2 != null) {
                stringBuffer.append(aVar2);
            }
        }
        if (cVar != null) {
            aVar2 = i == 0 ? cVar.char() : cVar.byte();
            if (aVar2 != null) {
                stringBuffer.append(aVar2);
            }
        }
        if (location != null) {
            aVar2 = (h == 0 || i == 0) ? b.do(location) : b.if(location);
            if (aVar2 != null) {
                stringBuffer.append(aVar2);
            }
        }
        boolean z = false;
        if (i == 0) {
            z = true;
        }
        aVar2 = c.a(z);
        if (aVar2 != null) {
            stringBuffer.append(aVar2);
        }
        if (str != null) {
            stringBuffer.append(str);
        }
        if (aVar != null) {
            aVar2 = aVar.int();
            if (aVar2 != null && aVar2.length() + stringBuffer.length() < 750) {
                stringBuffer.append(aVar2);
            }
        }
        aVar2 = stringBuffer.toString();
        if(f, "util format : " + aVar2);
        if (location == null || cVar == null) {
            l = 3;
        } else {
            try {
                float speed = location.getSpeed();
                int i2 = h;
                int i3 = cVar.do();
                int i4 = cVar.try();
                boolean z2 = cVar.case();
                if (speed < w && ((i2 == 1 || i2 == 0) && (i3 < v || z2))) {
                    l = 1;
                } else if (speed >= W || (!(i2 == 1 || i2 == 0 || i2 == 3) || (i3 >= ae && i4 <= goto))) {
                    l = 3;
                } else {
                    l = 2;
                }
            } catch (Exception e) {
                l = 3;
            }
        }
        return aVar2;
    }

    static String a(String str, String str2, String str3, double d) {
        if (str == null || str.equals("")) {
            return null;
        }
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return null;
        }
        indexOf += str2.length();
        int indexOf2 = str.indexOf(str3, indexOf);
        if (indexOf2 == -1) {
            return null;
        }
        String substring = str.substring(0, indexOf);
        substring = substring + String.format("%.7f", new Object[]{Double.valueOf(d)}) + str.substring(indexOf2);
        if(f, "NEW:" + substring);
        return substring;
    }

    public static String a(byte[] bArr, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & MotionEventCompat.ACTION_MASK);
            if (z) {
                toHexString = toHexString.toUpperCase();
            }
            if (toHexString.length() == 1) {
                stringBuilder.append(FeedbackItem.STATUS_WAITING);
            }
            stringBuilder.append(toHexString).append(str);
        }
        return stringBuilder.toString();
    }

    public static String a(byte[] bArr, boolean z) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            return a(instance.digest(), "", z);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static void a(int i) {
        boolean z = true;
        O = (i & 1) == 1;
        P = (i & 2) == 2;
        n = (i & 4) == 4;
        x = (i & 8) == 8;
        z = (i & 65536) == 65536;
        if ((i & 131072) != 131072) {
            z = false;
        }
        m = z;
        if(f, "A1~C3:" + O + P + n + x + z + m);
    }

    public static void a(String str) {
        if (k) {
            Log.d(else, str);
        }
    }

    public static void a(String str, String str2) {
        if (E) {
            Log.d(str, str2);
        }
    }

    static double do(String str, String str2, String str3) {
        double d = Double.MIN_VALUE;
        if (!(str == null || str.equals(""))) {
            int indexOf = str.indexOf(str2);
            if (indexOf != -1) {
                indexOf += str2.length();
                int indexOf2 = str.indexOf(str3, indexOf);
                if (indexOf2 != -1) {
                    String substring = str.substring(indexOf, indexOf2);
                    if (!(substring == null || substring.equals(""))) {
                        try {
                            d = Double.parseDouble(substring);
                        } catch (NumberFormatException e) {
                            if(f, "util numberFormatException, doubleStr : " + substring);
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return d;
    }

    public static String do() {
        return char;
    }

    public static boolean do(String str) {
        int i = if(str, "error\":\"", "\"");
        return i > 100 && i < 200;
    }

    static String for() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(5);
        int i2 = instance.get(1);
        int i3 = instance.get(2) + 1;
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        return String.format("%d-%d-%d %d:%d:%d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)});
    }

    static int if(String str, String str2, String str3) {
        int i = ExploreByTouchHelper.INVALID_ID;
        if (!(str == null || str.equals(""))) {
            int indexOf = str.indexOf(str2);
            if (indexOf != -1) {
                indexOf += str2.length();
                int indexOf2 = str.indexOf(str3, indexOf);
                if (indexOf2 != -1) {
                    String substring = str.substring(indexOf, indexOf2);
                    if (!(substring == null || substring.equals(""))) {
                        try {
                            i = Integer.parseInt(substring);
                        } catch (NumberFormatException e) {
                            if(f, "util numberFormatException, intStr : " + substring);
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return i;
    }

    public static void if() {
        int i = 0;
        try {
            File file = new File(i);
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(4);
                int readInt = randomAccessFile.readInt();
                int readInt2 = randomAccessFile.readInt();
                randomAccessFile.seek(128);
                byte[] bArr = new byte[readInt];
                while (i < readInt2) {
                    randomAccessFile.seek((long) ((readInt * i) + 128));
                    int readInt3 = randomAccessFile.readInt();
                    if (readInt3 > 0 && readInt3 < readInt) {
                        randomAccessFile.read(bArr, 0, readInt3);
                        if (bArr[readInt3 - 1] == (byte) 0) {
                            String str = new String(bArr, 0, readInt3 - 1);
                            if(f, "a:" + str);
                            if (str.equals(ak)) {
                                new = randomAccessFile.readInt();
                                e = i;
                                if(f, str + new);
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                    i++;
                }
                if (i == readInt2) {
                    e = readInt2;
                }
                randomAccessFile.close();
            }
        } catch (Exception e) {
        }
    }

    public static void if(int i) {
        File file = new File(i);
        if (!file.exists()) {
            int();
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(4);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            randomAccessFile.seek((long) ((readInt * e) + 128));
            byte[] bytes = (ak + '\u0000').getBytes();
            randomAccessFile.writeInt(bytes.length);
            randomAccessFile.write(bytes, 0, bytes.length);
            randomAccessFile.writeInt(i);
            if (readInt2 == e) {
                randomAccessFile.seek(8);
                randomAccessFile.writeInt(readInt2 + 1);
            }
            randomAccessFile.close();
        } catch (Exception e) {
        }
    }

    public static void if(String str) {
        if (E && str != null) {
            char = str;
        }
    }

    public static void if(String str, String str2) {
    }

    public static void int() {
        try {
            File file = new File(i);
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
                randomAccessFile.writeInt(0);
                randomAccessFile.writeInt(128);
                randomAccessFile.writeInt(0);
                randomAccessFile.close();
            }
        } catch (Exception e) {
        }
    }

    public static void new() {
        if (aj != null) {
            try {
                if(f, "logcat stop...");
                aj.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void try() {
        if (E) {
            try {
                if (aj != null) {
                    aj.destroy();
                    aj = null;
                }
                File file = new File(f.ac);
                if (file.exists()) {
                    if("sdkdemo_applocation", "directory already exists...");
                } else {
                    file.mkdirs();
                    if("sdkdemo_applocation", "directory not exists, make dirs...");
                }
                if(f, "logcat start ...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
