package com.baidu.location;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.tencent.open.yyb.TitleBar;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

class b {
    private static File A = new File(f.ac, N);
    private static final int B = 750;
    private static final int G = 5;
    private static int I = 0;
    private static long J = 0;
    private static long L = 0;
    private static final int M = 5;
    private static String N = "Temp_in.dat";
    private static int b = 0;
    private static long byte = 0;
    private static int c = 0;
    private static int case = 0;
    private static boolean char = true;
    private static final double else = 1.0E-5d;
    private static final int goto = 3000;
    private static final int h = 1024;
    private static final int i = 1000;
    private static final String if = "baidu_location_service";
    private static int j = 0;
    private static int k = 0;
    private static final int l = 12;
    private static double m = 0.0d;
    private static double o = 0.0d;
    private static final int p = 1;
    private static String r = null;
    private static final int s = 3;
    private static final int v = 100;
    private static final int void = 3600;
    private static StringBuffer y = null;
    private static final int z = 5;
    private boolean C = false;
    private String D = null;
    private long E = 0;
    private Location F;
    private Handler H = null;
    private final int K = 400;
    private GpsStatus a;
    private long d = 0;
    private LocationManager do = null;
    private boolean e = false;
    private Context f;
    private d for = null;
    private String g = null;
    private boolean int = false;
    private long long = 0;
    private String n = null;
    private a new = null;
    private final long q = 1000;
    private boolean t = false;
    private String try = null;
    private List u = new ArrayList();
    private boolean w = true;
    private b x = null;

    private class a implements Listener, NmeaListener {
        final /* synthetic */ b a;

        private a(b bVar) {
            this.a = bVar;
        }

        public void onGpsStatusChanged(int i) {
            if (this.a.do != null) {
                switch (i) {
                    case 2:
                        this.a.a(null);
                        this.a.a(false);
                        b.k = 0;
                        return;
                    case 4:
                        j.if("baidu_location_service", "gps status change");
                        if (this.a.a == null) {
                            this.a.a = this.a.do.getGpsStatus(null);
                        } else {
                            this.a.do.getGpsStatus(this.a.a);
                        }
                        int i2 = 0;
                        for (GpsSatellite usedInFix : this.a.a.getSatellites()) {
                            i2 = usedInFix.usedInFix() ? i2 + 1 : i2;
                        }
                        j.if("baidu_location_service", "gps nunmber in count:" + i2);
                        if (b.k >= 3 && i2 < 3) {
                            this.a.d = System.currentTimeMillis();
                        }
                        if (i2 < 3) {
                            this.a.a(false);
                        }
                        if (b.k <= 3 && i2 > 3) {
                            this.a.a(true);
                        }
                        b.k = i2;
                        return;
                    default:
                        return;
                }
            }
        }

        public void onNmeaReceived(long j, String str) {
            if (!j.m) {
                j.h = 0;
            } else if (str != null && !str.equals("") && str.length() >= 9 && str.length() <= 150 && this.a.new()) {
                long currentTimeMillis = System.currentTimeMillis();
                j.if("baidu_location_service", "gps manager onNmeaReceived : " + currentTimeMillis + " " + str);
                if (currentTimeMillis - this.a.E > 400 && this.a.e && this.a.u.size() > 0) {
                    try {
                        c cVar = new c(this.a.u, this.a.g, this.a.try, this.a.n);
                        if (cVar.if()) {
                            j.h = cVar.case();
                            if (j.h > 0) {
                                b.r = String.format("&nmea=%.1f|%.1f&g_tp=%d", new Object[]{Double.valueOf(cVar.a()), Double.valueOf(cVar.byte()), Integer.valueOf(j.h)});
                            }
                        } else {
                            j.h = 0;
                            j.if("baidu_location_service", "nmea invalid");
                        }
                    } catch (Exception e) {
                        j.h = 0;
                    }
                    this.a.u.clear();
                    this.a.g = this.a.try = this.a.n = null;
                    this.a.e = false;
                }
                if (str.startsWith("$GPGGA")) {
                    this.a.e = true;
                    this.a.g = str.trim();
                } else if (str.startsWith("$GPGSV")) {
                    this.a.u.add(str.trim());
                } else if (str.startsWith("$GPGSA")) {
                    this.a.n = str.trim();
                }
                this.a.E = System.currentTimeMillis();
            }
        }
    }

    private class b implements LocationListener {
        final /* synthetic */ b a;

        private b(b bVar) {
            this.a = bVar;
        }

        public void onLocationChanged(Location location) {
            this.a.a(location);
            this.a.int = false;
            if (this.a.C) {
                this.a.a(true);
            }
        }

        public void onProviderDisabled(String str) {
            this.a.a(null);
            this.a.a(false);
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            switch (i) {
                case 0:
                    this.a.a(null);
                    this.a.a(false);
                    return;
                case 1:
                    this.a.long = System.currentTimeMillis();
                    this.a.int = true;
                    this.a.a(false);
                    return;
                case 2:
                    this.a.int = false;
                    return;
                default:
                    return;
            }
        }
    }

    private class c {
        public int a;
        private String b;
        private double byte;
        final /* synthetic */ b c;
        private char case;
        private String char;
        private int d;
        private List do;
        private int e;
        private List else;
        private String f;
        private double for;
        private String g;
        private boolean goto;
        private boolean h;
        private int if;
        private String int;
        private int long;
        private boolean new;
        private boolean try;
        private boolean void;

        private class a {
            private int a = 0;
            private int do = 0;
            final /* synthetic */ c for;
            private int if = 0;
            private int int = 0;

            public a(c cVar, int i, int i2, int i3, int i4) {
                this.for = cVar;
                this.int = i;
                this.a = i2;
                this.if = i3;
                this.do = i4;
            }

            public int a() {
                return this.a;
            }

            public int do() {
                return this.do;
            }

            public int if() {
                return this.if;
            }
        }

        private c(b bVar, List list, String str, String str2, String str3) {
            this.c = bVar;
            this.goto = false;
            this.char = "";
            this.h = false;
            this.int = "";
            this.long = 0;
            this.d = 0;
            this.f = "";
            this.new = false;
            this.b = "";
            this.case = 'N';
            this.g = "";
            this.void = false;
            this.if = 1;
            this.byte = 0.0d;
            this.for = 0.0d;
            this.do = null;
            this.try = false;
            this.else = null;
            this.e = 0;
            this.a = 0;
            this.do = list;
            this.char = str;
            this.f = str2;
            this.g = str3;
            this.else = new ArrayList();
            try();
        }

        private double a() {
            return this.for;
        }

        private int a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
            if (!this.goto) {
                return 0;
            }
            if (z && this.h) {
                this.a = 1;
                if (this.d >= j.K) {
                    return 1;
                }
                if (this.d <= j.p) {
                    return 4;
                }
            }
            if (z2 && this.void) {
                this.a = 2;
                if (this.for <= ((double) j.am)) {
                    return 1;
                }
                if (this.for >= ((double) j.c)) {
                    return 4;
                }
            }
            if (z3 && this.void) {
                this.a = 3;
                if (this.byte <= ((double) j.F)) {
                    return 1;
                }
                if (this.byte >= ((double) j.U)) {
                    return 4;
                }
            }
            if (!this.try) {
                return 0;
            }
            int i;
            if (z4) {
                this.a = 4;
                i = 0;
                for (a aVar : this.else) {
                    i = aVar.do() >= j.for ? i + 1 : i;
                }
                if (i >= j.int) {
                    return 1;
                }
                if (i <= j.X) {
                    return 4;
                }
            }
            if (z5) {
                int i2;
                this.a = 5;
                List arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                List arrayList3 = new ArrayList();
                for (i2 = 0; i2 < 10; i2++) {
                    arrayList.add(new ArrayList());
                }
                int i3 = 0;
                for (a aVar2 : this.else) {
                    if (aVar2.do() < 10 || aVar2.if() < 1) {
                        i2 = i3;
                    } else {
                        ((List) arrayList.get((aVar2.do() - 10) / 5)).add(aVar2);
                        i2 = i3 + 1;
                    }
                    i3 = i2;
                }
                if (i3 < 4) {
                    return 4;
                }
                for (i = 0; i < arrayList.size(); i++) {
                    if (((List) arrayList.get(i)).size() != 0) {
                        Object a = a((List) arrayList.get(i));
                        if (a != null) {
                            arrayList2.add(a);
                            arrayList3.add(Integer.valueOf(i));
                        }
                    }
                }
                if (arrayList2 == null || arrayList2.size() <= 0) {
                    return 4;
                }
                double[] dArr = (double[]) arrayList2.get(0);
                dArr[0] = dArr[0] * ((double) ((Integer) arrayList3.get(0)).intValue());
                dArr[1] = dArr[1] * ((double) ((Integer) arrayList3.get(0)).intValue());
                if (arrayList2.size() > 1) {
                    for (int i4 = 1; i4 < arrayList2.size(); i4++) {
                        double[] dArr2 = (double[]) arrayList2.get(i4);
                        dArr2[0] = dArr2[0] * ((double) ((Integer) arrayList3.get(i4)).intValue());
                        dArr2[1] = dArr2[1] * ((double) ((Integer) arrayList3.get(i4)).intValue());
                        dArr[0] = dArr[0] + dArr2[0];
                        dArr[1] = dArr[1] + dArr2[1];
                    }
                    dArr[0] = dArr[0] / ((double) arrayList2.size());
                    dArr[1] = dArr[1] / ((double) arrayList2.size());
                }
                dArr = a(dArr[0], dArr[1]);
                if (dArr[0] <= ((double) j.ad)) {
                    return 1;
                }
                if (dArr[0] >= ((double) j.long)) {
                    return 4;
                }
            }
            this.a = 0;
            return 3;
        }

        private boolean a(String str) {
            if (str == null || str.length() <= 8) {
                return false;
            }
            int i = 0;
            for (int i2 = 1; i2 < str.length() - 3; i2++) {
                i ^= str.charAt(i2);
            }
            return Integer.toHexString(i).equalsIgnoreCase(str.substring(str.length() + -2, str.length()));
        }

        private double[] a(double d, double d2) {
            double d3 = 0.0d;
            if (d2 != 0.0d) {
                d3 = Math.toDegrees(Math.atan(d / d2));
            } else if (d > 0.0d) {
                d3 = 90.0d;
            } else if (d < 0.0d) {
                d3 = 270.0d;
            }
            return new double[]{Math.sqrt((d * d) + (d2 * d2)), d3};
        }

        private double[] a(List list) {
            if (list == null || list.size() <= 0) {
                return null;
            }
            double[] dArr = if((double) (90 - ((a) list.get(0)).if()), (double) ((a) list.get(0)).a());
            if (list.size() > 1) {
                for (int i = 1; i < list.size(); i++) {
                    double[] dArr2 = if((double) (90 - ((a) list.get(i)).if()), (double) ((a) list.get(i)).a());
                    dArr[0] = dArr[0] + dArr2[0];
                    dArr[1] = dArr[1] + dArr2[1];
                }
                dArr[0] = dArr[0] / ((double) list.size());
                dArr[1] = dArr[1] / ((double) list.size());
            }
            return dArr;
        }

        private double byte() {
            return this.byte;
        }

        private int case() {
            return a(true, true, true, true, true);
        }

        private String char() {
            return this.g;
        }

        private boolean for() {
            return this.h;
        }

        private boolean goto() {
            return this.void;
        }

        private boolean if() {
            return this.goto;
        }

        private double[] if(double d, double d2) {
            return new double[]{Math.sin(Math.toRadians(d2)) * d, Math.cos(Math.toRadians(d2)) * d};
        }

        private List int() {
            return this.do;
        }

        private boolean long() {
            return this.new;
        }

        private boolean new() {
            return this.try;
        }

        private void try() {
            String[] split;
            String substring;
            int i;
            int i2;
            boolean z = true;
            if (a(this.char)) {
                split = this.char.split(SelectCountryActivity.SPLITTER);
                if (split.length == 15) {
                    if (!(split[6].equals("") || split[7].equals(""))) {
                        this.long = Integer.valueOf(split[6]).intValue();
                        this.d = Integer.valueOf(split[7]).intValue();
                        this.h = true;
                    }
                } else {
                    return;
                }
            }
            if (a(this.g)) {
                substring = this.g.substring(0, this.g.length() - 3);
                i = 0;
                for (i2 = 0; i2 < substring.length(); i2++) {
                    if (substring.charAt(i2) == ',') {
                        i++;
                    }
                }
                split = substring.split(SelectCountryActivity.SPLITTER, i + 1);
                if (split.length < 6) {
                    return;
                }
                if (!(split[2].equals("") || split[split.length - 3].equals("") || split[split.length - 2].equals("") || split[split.length - 1].equals(""))) {
                    this.if = Integer.valueOf(split[2]).intValue();
                    this.byte = Double.valueOf(split[split.length - 3]).doubleValue();
                    this.for = Double.valueOf(split[split.length - 2]).doubleValue();
                    this.void = true;
                }
            }
            if (this.do != null && this.do.size() > 0) {
                for (String str : this.do) {
                    if (!a(str)) {
                        this.try = false;
                        break;
                    }
                    substring = str.substring(0, str.length() - 3);
                    i = 0;
                    for (i2 = 0; i2 < substring.length(); i2++) {
                        if (substring.charAt(i2) == ',') {
                            i++;
                        }
                    }
                    String[] split2 = substring.split(SelectCountryActivity.SPLITTER, i + 1);
                    boolean z2 = Integer.valueOf(split2[1]).intValue() == this.do.size() && split2.length > 8;
                    this.try = z2;
                    if (!this.try) {
                        break;
                    }
                    int i3 = 4;
                    while (i3 < split2.length) {
                        if (!(split2[i3].equals("") || split2[i3 + 1].equals("") || split2[i3 + 2].equals(""))) {
                            this.e++;
                            this.else.add(new a(this, Integer.valueOf(split2[i3]).intValue(), Integer.valueOf(split2[i3 + 2]).intValue(), Integer.valueOf(split2[i3 + 1]).intValue(), split2[i3 + 3].equals("") ? 0 : Integer.valueOf(split2[i3 + 3]).intValue()));
                        }
                        i3 += 4;
                    }
                }
            } else {
                this.try = false;
            }
            if (!(this.h && this.void)) {
                z = false;
            }
            this.goto = z;
        }

        private String void() {
            return this.f;
        }

        public String do() {
            return this.int;
        }

        public int else() {
            return this.long;
        }
    }

    public static class d {
        private boolean a = true;
        private String if = null;

        public d(String str) {
            if (str == null) {
                str = "";
            } else if (str.length() > 100) {
                str = str.substring(0, 100);
            }
            this.if = str;
        }

        private String a(int i) {
            if (!b.A.exists()) {
                return null;
            }
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(b.A, "rw");
                randomAccessFile.seek(0);
                int readInt = randomAccessFile.readInt();
                if (!b.if(readInt, randomAccessFile.readInt(), randomAccessFile.readInt())) {
                    randomAccessFile.close();
                    b.h();
                    return null;
                } else if (i == 0 || i == readInt + 1) {
                    randomAccessFile.close();
                    return null;
                } else {
                    long j = (12 + 0) + ((long) ((i - 1) * 1024));
                    randomAccessFile.seek(j);
                    int readInt2 = randomAccessFile.readInt();
                    byte[] bArr = new byte[readInt2];
                    randomAccessFile.seek(j + 4);
                    for (readInt = 0; readInt < readInt2; readInt++) {
                        bArr[readInt] = randomAccessFile.readByte();
                    }
                    randomAccessFile.close();
                    return new String(bArr);
                }
            } catch (IOException e) {
                return null;
            }
        }

        private void a() {
            if (b.y != null && b.y.length() >= 100) {
                a(b.y.toString());
            }
            b.f();
        }

        private boolean a(Location location) {
            return a(location, j.V, j.aa);
        }

        private boolean a(Location location, int i, int i2) {
            if (location == null || !j.void || !this.a || !j.P) {
                return false;
            }
            if (j.V < 5) {
                j.V = 5;
            } else if (j.V > 1000) {
                j.V = 1000;
            }
            if (j.aa < 5) {
                j.aa = 5;
            } else if (j.aa > b.void) {
                j.aa = b.void;
            }
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            long time = location.getTime() / 1000;
            if (b.char) {
                b.case = 1;
                b.y = new StringBuffer("");
                b.y.append(String.format("&nr=%s&traj=%d,%.5f,%.5f|", new Object[]{this.if, Long.valueOf(time), Double.valueOf(longitude), Double.valueOf(latitude)}));
                b.c = b.y.length();
                b.byte = time;
                b.o = longitude;
                b.m = latitude;
                b.J = (long) Math.floor((longitude * 100000.0d) + 0.5d);
                b.L = (long) Math.floor((latitude * 100000.0d) + 0.5d);
                b.char = false;
                return true;
            }
            float[] fArr = new float[1];
            Location.distanceBetween(latitude, longitude, b.m, b.o, fArr);
            long g = time - b.byte;
            if (fArr[0] < ((float) j.V) && g < ((long) j.aa)) {
                return false;
            }
            if (b.y == null) {
                b.i();
                b.c = 0;
                b.y = new StringBuffer("");
                b.y.append(String.format("&nr=%s&traj=%d,%.5f,%.5f|", new Object[]{this.if, Long.valueOf(time), Double.valueOf(longitude), Double.valueOf(latitude)}));
                b.c = b.y.length();
                b.byte = time;
                b.o = longitude;
                b.m = latitude;
                b.J = (long) Math.floor((longitude * 100000.0d) + 0.5d);
                b.L = (long) Math.floor((latitude * 100000.0d) + 0.5d);
            } else {
                b.o = longitude;
                b.m = latitude;
                long floor = (long) Math.floor((longitude * 100000.0d) + 0.5d);
                long floor2 = (long) Math.floor((latitude * 100000.0d) + 0.5d);
                b.j = (int) (time - b.byte);
                b.I = (int) (floor - b.J);
                b.b = (int) (floor2 - b.L);
                b.y.append(String.format("%d,%d,%d|", new Object[]{Integer.valueOf(b.j), Integer.valueOf(b.I), Integer.valueOf(b.b)}));
                b.c = b.y.length();
                b.byte = time;
                b.J = floor;
                b.L = floor2;
            }
            if (b.c + 15 > b.B) {
                a(b.y.toString());
                b.y = null;
            }
            if (b.case >= j.L) {
                this.a = false;
            }
            return true;
        }

        private boolean a(String str) {
            if (str == null || !str.startsWith("&nr")) {
                return false;
            }
            if (!b.A.exists() && !b.h()) {
                return false;
            }
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(b.A, "rw");
                randomAccessFile.seek(0);
                int readInt = randomAccessFile.readInt();
                int readInt2 = randomAccessFile.readInt();
                int readInt3 = randomAccessFile.readInt();
                if (b.if(readInt, readInt2, readInt3)) {
                    if (j.try) {
                        if (readInt == j.L) {
                            if (str.equals(a(readInt3 == 1 ? j.L : readInt3 - 1))) {
                                randomAccessFile.close();
                                return false;
                            }
                        } else if (readInt3 > 1 && str.equals(a(readInt3 - 1))) {
                            randomAccessFile.close();
                            return false;
                        }
                    }
                    randomAccessFile.seek(((long) (((readInt3 - 1) * 1024) + 12)) + 0);
                    if (str.length() > b.B) {
                        randomAccessFile.close();
                        return false;
                    }
                    String str2 = Jni.if(str);
                    int length = str2.length();
                    if (length > 1020) {
                        randomAccessFile.close();
                        return false;
                    }
                    randomAccessFile.writeInt(length);
                    randomAccessFile.writeBytes(str2);
                    if (readInt == 0) {
                        randomAccessFile.seek(0);
                        randomAccessFile.writeInt(1);
                        randomAccessFile.writeInt(1);
                        randomAccessFile.writeInt(2);
                    } else if (readInt < j.L - 1) {
                        randomAccessFile.seek(0);
                        randomAccessFile.writeInt(readInt + 1);
                        randomAccessFile.seek(8);
                        randomAccessFile.writeInt(readInt + 2);
                    } else if (readInt == j.L - 1) {
                        randomAccessFile.seek(0);
                        randomAccessFile.writeInt(j.L);
                        if (readInt2 == 0 || readInt2 == 1) {
                            randomAccessFile.writeInt(2);
                        }
                        randomAccessFile.seek(8);
                        randomAccessFile.writeInt(1);
                    } else if (readInt3 == readInt2) {
                        readInt = readInt3 == j.L ? 1 : readInt3 + 1;
                        r2 = readInt == j.L ? 1 : readInt + 1;
                        randomAccessFile.seek(4);
                        randomAccessFile.writeInt(r2);
                        randomAccessFile.writeInt(readInt);
                    } else {
                        readInt = readInt3 == j.L ? 1 : readInt3 + 1;
                        if (readInt == readInt2) {
                            r2 = readInt == j.L ? 1 : readInt + 1;
                            randomAccessFile.seek(4);
                            randomAccessFile.writeInt(r2);
                        }
                        randomAccessFile.seek(8);
                        randomAccessFile.writeInt(readInt);
                    }
                    randomAccessFile.close();
                    return true;
                }
                randomAccessFile.close();
                b.h();
                return false;
            } catch (IOException e) {
                return false;
            }
        }

        private boolean if() {
            if (b.A.exists()) {
                b.A.delete();
            }
            b.f();
            return !b.A.exists();
        }
    }

    public b(Context context, Handler handler) {
        this.f = context;
        this.H = handler;
        try {
            if (j.do != null) {
                this.for = new d(j.do);
            } else {
                this.for = new d("NULL");
            }
        } catch (Exception e) {
            this.for = null;
        }
    }

    private void a(double d, double d2, float f) {
        int i = 0;
        j.if("baidu_location_service", "check...gps ...");
        if (j.z) {
            if (d >= 73.146973d && d <= 135.252686d && d2 <= 54.258807d && d2 >= 14.604847d && f <= 18.0f) {
                j.if("baidu_location_service", "check...gps2 ...");
                int i2 = (int) ((d - j.if) * 1000.0d);
                int i3 = (int) ((j.o - d2) * 1000.0d);
                j.if("baidu_location_service", "check...gps ..." + i2 + i3);
                if (i2 <= 0 || i2 >= 50 || i3 <= 0 || i3 >= 50) {
                    String str = String.format("&ll=%.5f|%.5f", new Object[]{Double.valueOf(d), Double.valueOf(d2)}) + "&im=" + j.do;
                    j.J = d;
                    j.Z = d2;
                    g.a(str, true);
                } else {
                    j.if("baidu_location_service", "check...gps ..." + i2 + i3);
                    i2 += i3 * 50;
                    i3 = i2 >> 2;
                    i2 &= 3;
                    if (j.ag) {
                        i = (j.j[i3] >> (i2 * 2)) & 3;
                        j.if("baidu_location_service", "check gps scacity..." + i);
                    }
                }
            }
            if (j.I != i) {
                j.I = i;
                try {
                    if (j.I == 3) {
                        this.do.removeUpdates(this.x);
                        this.do.requestLocationUpdates("gps", 1000, 1.0f, this.x);
                        return;
                    }
                    this.do.removeUpdates(this.x);
                    this.do.requestLocationUpdates("gps", 1000, 5.0f, this.x);
                } catch (Exception e) {
                }
            }
        }
    }

    private void a(Location location) {
        j.if("baidu_location_service", "set new gpsLocation ...");
        this.F = location;
        if (this.F == null) {
            this.D = null;
        } else {
            this.F.setTime(System.currentTimeMillis());
            this.D = String.format("&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_n=%d&ll_t=%d", new Object[]{Double.valueOf(this.F.getLongitude()), Double.valueOf(this.F.getLatitude()), Float.valueOf((float) (((double) this.F.getSpeed()) * 3.6d)), Float.valueOf(this.F.getBearing()), Integer.valueOf(k), Long.valueOf(r0)});
            a(this.F.getLongitude(), this.F.getLatitude(), r6);
        }
        if (this.for != null) {
            try {
                this.for.a(this.F);
            } catch (Exception e) {
            }
        }
        this.H.obtainMessage(51).sendToTarget();
    }

    private void a(boolean z) {
        this.C = z;
        if ((!z || new()) && j.ab != z) {
            j.ab = z;
            if (j.H) {
                this.H.obtainMessage(53).sendToTarget();
            }
        }
    }

    public static boolean a(Location location, Location location2, boolean z) {
        if (location == location2) {
            return false;
        }
        if (location == null || location2 == null) {
            return true;
        }
        float speed = location2.getSpeed();
        if (z && j.I == 3 && speed < 5.0f) {
            return true;
        }
        float distanceTo = location2.distanceTo(location);
        return speed > j.C ? distanceTo > j.Q : speed > j.D ? distanceTo > j.ai : distanceTo > 5.0f;
    }

    public static String do(Location location) {
        String str = for(location);
        return str != null ? str + "&g_tp=0" : str;
    }

    private static void f() {
        char = true;
        y = null;
        case = 0;
        c = 0;
        byte = 0;
        J = 0;
        L = 0;
        o = 0.0d;
        m = 0.0d;
        j = 0;
        I = 0;
        b = 0;
    }

    public static String for(Location location) {
        if (location == null) {
            return null;
        }
        float speed = (float) (((double) location.getSpeed()) * 3.6d);
        int accuracy = (int) (location.hasAccuracy() ? location.getAccuracy() : -1.0f);
        double altitude = location.hasAltitude() ? location.getAltitude() : 555.0d;
        return String.format("&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_r=%d&ll_n=%d&ll_h=%.2f&ll_t=%d", new Object[]{Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude()), Float.valueOf(speed), Float.valueOf(location.getBearing()), Integer.valueOf(accuracy), Integer.valueOf(k), Double.valueOf(altitude), Long.valueOf(location.getTime() / 1000)});
    }

    private static boolean h() {
        if (A.exists()) {
            A.delete();
        }
        if (!A.getParentFile().exists()) {
            A.getParentFile().mkdirs();
        }
        try {
            A.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(A, "rw");
            randomAccessFile.seek(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(1);
            randomAccessFile.close();
            f();
            return A.exists();
        } catch (IOException e) {
            return false;
        }
    }

    static /* synthetic */ int i() {
        int i = case + 1;
        case = i;
        return i;
    }

    public static String if(Location location) {
        String str = for(location);
        return str != null ? str + r : str;
    }

    private static boolean if(int i, int i2, int i3) {
        return (i < 0 || i > j.L) ? false : (i2 < 0 || i2 > i + 1) ? false : i3 >= 1 && i3 <= i + 1 && i3 <= j.L;
    }

    public static String j() {
        j.if("baidu_location_service", "GPS readline...");
        if (A == null) {
            return null;
        }
        if (!A.exists()) {
            return null;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(A, "rw");
            randomAccessFile.seek(0);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            if (if(readInt, readInt2, readInt3)) {
                j.if("baidu_location_service", "GPS readline1...");
                if (readInt2 == 0 || readInt2 == readInt3) {
                    randomAccessFile.close();
                    return null;
                }
                j.if("baidu_location_service", "GPS readline2...");
                long j = 0 + ((long) (((readInt2 - 1) * 1024) + 12));
                randomAccessFile.seek(j);
                int readInt4 = randomAccessFile.readInt();
                byte[] bArr = new byte[readInt4];
                randomAccessFile.seek(j + 4);
                for (readInt3 = 0; readInt3 < readInt4; readInt3++) {
                    bArr[readInt3] = randomAccessFile.readByte();
                }
                String str = new String(bArr);
                readInt3 = readInt < j.L ? readInt2 + 1 : readInt2 == j.L ? 1 : readInt2 + 1;
                randomAccessFile.seek(4);
                randomAccessFile.writeInt(readInt3);
                randomAccessFile.close();
                return str;
            }
            randomAccessFile.close();
            h();
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public boolean case() {
        return (this.F == null || this.F.getLatitude() == 0.0d || this.F.getLongitude() == 0.0d) ? false : true;
    }

    public String char() {
        return this.D;
    }

    public void goto() {
    }

    public String int() {
        if (this.F != null) {
            String str = "{\"result\":{\"time\":\"" + j.for() + "\",\"error\":\"61\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%d\",\"d\":\"%f\"," + "\"s\":\"%f\",\"n\":\"%d\"}}";
            int accuracy = (int) (this.F.hasAccuracy() ? this.F.getAccuracy() : TitleBar.SHAREBTN_RIGHT_MARGIN);
            float speed = (float) (((double) this.F.getSpeed()) * 3.6d);
            double[] dArr = Jni.if(this.F.getLongitude(), this.F.getLatitude(), "gps2gcj");
            if (dArr[0] <= 0.0d && dArr[1] <= 0.0d) {
                dArr[0] = this.F.getLongitude();
                dArr[1] = this.F.getLatitude();
            }
            String format = String.format(str, new Object[]{Double.valueOf(dArr[0]), Double.valueOf(dArr[1]), Integer.valueOf(accuracy), Float.valueOf(this.F.getBearing()), Float.valueOf(speed), Integer.valueOf(k)});
            j.if("baidu_location_service", "wgs84: " + this.F.getLongitude() + " " + this.F.getLatitude() + " gcj02: " + dArr[0] + " " + dArr[1]);
            return format;
        }
        j.if("baidu_location_service", "gps man getGpsJson but gpslocation is null");
        return null;
    }

    public void k() {
        if (!this.t) {
            try {
                this.do = (LocationManager) this.f.getSystemService("location");
                this.x = new b();
                this.new = new a();
                this.do.requestLocationUpdates("gps", 1000, 5.0f, this.x);
                this.do.addGpsStatusListener(this.new);
                this.do.addNmeaListener(this.new);
                this.t = true;
            } catch (Exception e) {
            }
        }
    }

    public void l() {
        if (this.t) {
            if (this.do != null) {
                try {
                    if (this.x != null) {
                        this.do.removeUpdates(this.x);
                    }
                    if (this.new != null) {
                        this.do.removeGpsStatusListener(this.new);
                        this.do.removeNmeaListener(this.new);
                    }
                    if (this.for != null) {
                        this.for.a();
                    }
                } catch (Exception e) {
                }
            }
            this.x = null;
            this.new = null;
            this.do = null;
            this.t = false;
            a(false);
        }
    }

    public boolean new() {
        if (!case()) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        return (!this.int || currentTimeMillis - this.long <= 3000) ? k >= 3 ? true : currentTimeMillis - this.d < 3000 : false;
    }

    public Location try() {
        return this.F;
    }
}
