package com.baidu.location;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Log;
import com.alibaba.wireless.security.SecExceptionCode;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.tencent.open.SocialConstants;
import com.ttfm.android.sdk.http.TTPodFMHttpClient;
import java.io.File;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public final class f extends Service {
    static final int B = 92;
    static final int D = 57;
    static final int G = 52;
    static final int I = 26;
    static final int K = 64;
    static final int L = 27;
    static final int M = 62;
    static final int S = 1000;
    static final int U = 54;
    static final int V = 81;
    static final int W = 25;
    private static String a = (ac + "/glb.dat");
    static final int aa = 21;
    static String ac = (Environment.getExternalStorageDirectory().getPath() + "/baidu/tempdata");
    private static final int af = 200;
    static final int ag = 43;
    static final int ah = 14;
    static final int ai = 3000;
    static final int ak = 56;
    static final int ao = 101;
    static final float ap = 3.3f;
    static final int aq = 61;
    static final int ar = 53;
    private static final int at = 800;
    static final int b = 63;
    private static final int byte = 24;
    static final int c = 12;
    static final int case = 42;
    static final int do = 28;
    static final int e = 65;
    static final int else = 2000;
    static final int for = 22;
    static final int g = 15;
    static final int i = 55;
    static final int int = 31;
    private static File j = null;
    private static File k = null;
    static final int l = 11;
    static final int long = 13;
    static final int p = 41;
    static final int s = 23;
    static final int t = 91;
    public static final String v = "baidu_location_service";
    static final int void = 71;
    static final int w = 24;
    static final int x = 3000;
    static final int z = 51;
    private String A = null;
    private c C = null;
    private long E = 0;
    private e F = null;
    private String H = null;
    private boolean J = true;
    private boolean N = true;
    private boolean O = false;
    private long P = 0;
    private boolean Q = false;
    final Handler R = new d(this);
    private SQLiteDatabase T = null;
    private String X = null;
    private boolean Y = true;
    private int Z = 0;
    private b ab = null;
    private boolean ad = false;
    private c ae = null;
    private boolean aj = false;
    private com.baidu.location.c.a al = null;
    private boolean am = false;
    final Messenger an = new Messenger(this.R);
    private String as = null;
    private a au = null;
    private c char = null;
    private long d = 0;
    private Location f = null;
    private boolean goto = false;
    private String h = null;
    private String if = "bdcltb09";
    private String m = (ac + "/vm.dat");
    private double n = 0.0d;
    private String new = null;
    private double o = 0.0d;
    private double q = 0.0d;
    private c r = null;
    private com.baidu.location.c.a try = null;
    private com.baidu.location.c.a u = null;
    private c y = null;

    public class a implements UncaughtExceptionHandler {
        final /* synthetic */ f a;
        private Context if;

        a(f fVar, Context context) {
            this.a = fVar;
            this.if = context;
            a();
        }

        private String a(Throwable th) {
            Writer stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.close();
            return stringWriter.toString();
        }

        public void a() {
            String str = null;
            try {
                File file = new File((Environment.getExternalStorageDirectory().getPath() + "/traces") + "/error_fs.dat");
                if (file.exists()) {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(280);
                    if (1326 == randomAccessFile.readInt()) {
                        String str2;
                        byte[] bArr;
                        randomAccessFile.seek(308);
                        int readInt = randomAccessFile.readInt();
                        if (readInt <= 0 || readInt >= 2048) {
                            str2 = null;
                        } else {
                            j.if(f.v, "A" + readInt);
                            bArr = new byte[readInt];
                            randomAccessFile.read(bArr, 0, readInt);
                            str2 = new String(bArr, 0, readInt);
                        }
                        randomAccessFile.seek(600);
                        readInt = randomAccessFile.readInt();
                        if (readInt > 0 && readInt < 2048) {
                            j.if(f.v, "A" + readInt);
                            bArr = new byte[readInt];
                            randomAccessFile.read(bArr, 0, readInt);
                            str = new String(bArr, 0, readInt);
                        }
                        j.if(f.v, str2 + str);
                        if (a(str2, str)) {
                            randomAccessFile.seek(280);
                            randomAccessFile.writeInt(12346);
                        }
                    }
                    randomAccessFile.close();
                }
            } catch (Exception e) {
            }
        }

        public void a(File file, String str, String str2) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(280);
                randomAccessFile.writeInt(12346);
                randomAccessFile.seek(300);
                randomAccessFile.writeLong(System.currentTimeMillis());
                byte[] bytes = str.getBytes();
                randomAccessFile.writeInt(bytes.length);
                randomAccessFile.write(bytes, 0, bytes.length);
                randomAccessFile.seek(600);
                bytes = str2.getBytes();
                randomAccessFile.writeInt(bytes.length);
                randomAccessFile.write(bytes, 0, bytes.length);
                if (!a(str, str2)) {
                    randomAccessFile.seek(280);
                    randomAccessFile.writeInt(1326);
                }
                randomAccessFile.close();
            } catch (Exception e) {
            }
        }

        boolean a(String str, String str2) {
            if (!g.a(this.if)) {
                return false;
            }
            try {
                HttpUriRequest httpPost = new HttpPost(j.N);
                List arrayList = new ArrayList();
                arrayList.add(new BasicNameValuePair("e0", str));
                arrayList.add(new BasicNameValuePair("e1", str2));
                httpPost.setEntity(new UrlEncodedFormEntity(arrayList, TTPodFMHttpClient.AppEncode));
                HttpClient defaultHttpClient = new DefaultHttpClient();
                defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(12000));
                defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(12000));
                j.if(f.v, "send begin ...");
                if (defaultHttpClient.execute(httpPost).getStatusLine().getStatusCode() != 200) {
                    return false;
                }
                j.if(f.v, "send ok....");
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public void uncaughtException(Thread thread, Throwable th) {
            String a;
            String str;
            Object obj;
            File file;
            String str2;
            RandomAccessFile randomAccessFile;
            File file2;
            File file3 = null;
            if (j.x) {
                try {
                    a = a(th);
                    try {
                        j.if(f.v, a);
                        this.a.r;
                        String a2 = c.a(false);
                        if (this.a.au != null) {
                            a2 = a2 + this.a.au.byte();
                        }
                        str = a2 != null ? Jni.if(a2) : null;
                    } catch (Exception e) {
                        Object obj2 = a;
                        obj = file;
                        str = null;
                        str2 = Environment.getExternalStorageDirectory().getPath() + "/traces";
                        file = new File(str2 + "/error_fs.dat");
                        if (file.exists()) {
                            randomAccessFile = new RandomAccessFile(file, "rw");
                            randomAccessFile.seek(300);
                            if (System.currentTimeMillis() - randomAccessFile.readLong() > 604800000) {
                                a(file, str, a);
                            }
                        } else {
                            file2 = new File(str2);
                            if (!file2.exists()) {
                                file2.mkdirs();
                            }
                            if (file.createNewFile()) {
                                file3 = file;
                            }
                            a(file3, str, a);
                        }
                        Process.killProcess(Process.myPid());
                        return;
                    }
                } catch (Exception e2) {
                    file = null;
                    obj = file;
                    str = null;
                    str2 = Environment.getExternalStorageDirectory().getPath() + "/traces";
                    file = new File(str2 + "/error_fs.dat");
                    if (file.exists()) {
                        randomAccessFile = new RandomAccessFile(file, "rw");
                        randomAccessFile.seek(300);
                        if (System.currentTimeMillis() - randomAccessFile.readLong() > 604800000) {
                            a(file, str, a);
                        }
                    } else {
                        file2 = new File(str2);
                        if (file2.exists()) {
                            file2.mkdirs();
                        }
                        if (file.createNewFile()) {
                            file3 = file;
                        }
                        a(file3, str, a);
                    }
                    Process.killProcess(Process.myPid());
                    return;
                }
                try {
                    str2 = Environment.getExternalStorageDirectory().getPath() + "/traces";
                    file = new File(str2 + "/error_fs.dat");
                    if (file.exists()) {
                        file2 = new File(str2);
                        if (file2.exists()) {
                            file2.mkdirs();
                        }
                        if (file.createNewFile()) {
                            file3 = file;
                        }
                        a(file3, str, a);
                    } else {
                        randomAccessFile = new RandomAccessFile(file, "rw");
                        randomAccessFile.seek(300);
                        if (System.currentTimeMillis() - randomAccessFile.readLong() > 604800000) {
                            a(file, str, a);
                        }
                    }
                } catch (Exception e3) {
                }
                Process.killProcess(Process.myPid());
                return;
            }
            Process.killProcess(Process.myPid());
        }
    }

    private class b implements Runnable {
        final /* synthetic */ f a;

        private b(f fVar) {
            this.a = fVar;
        }

        public void run() {
            if (this.a.aj) {
                this.a.aj = false;
                this.a.byte();
            }
        }
    }

    public class c {
        public static final String for = "com.baidu.locTest.LocationServer";
        private long[] a = new long[20];
        private com.baidu.location.c.a b = null;
        private int byte = 1;
        private String c = null;
        private a case = null;
        private final int char = 200;
        private PendingIntent d = null;
        private boolean do = false;
        final /* synthetic */ f e;
        private boolean else = false;
        private Context goto = null;
        private boolean if = false;
        private int int = 0;
        private String long = null;
        private final long new = 86100000;
        private AlarmManager try = null;
        private long void = 0;

        public class a extends BroadcastReceiver {
            final /* synthetic */ c a;

            public a(c cVar) {
                this.a = cVar;
            }

            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(c.for)) {
                    this.a.e.R.obtainMessage(101).sendToTarget();
                    return;
                }
                try {
                    if (action.equals("android.intent.action.BATTERY_CHANGED")) {
                        int intExtra = intent.getIntExtra(Downloads.COLUMN_STATUS, 0);
                        int intExtra2 = intent.getIntExtra("plugged", 0);
                        switch (intExtra) {
                            case 2:
                                this.a.c = FeedbackItem.STATUS_SHELVED;
                                break;
                            case 3:
                            case 4:
                                this.a.c = FeedbackItem.STATUS_RECORDED;
                                break;
                            default:
                                this.a.c = null;
                                break;
                        }
                        switch (intExtra2) {
                            case 1:
                                this.a.c = "6";
                                return;
                            case 2:
                                this.a.c = "5";
                                return;
                            default:
                                return;
                        }
                    }
                } catch (Exception e) {
                    this.a.c = null;
                }
            }
        }

        public c(f fVar, Context context) {
            this.e = fVar;
            this.goto = context;
            this.void = System.currentTimeMillis();
            this.try = (AlarmManager) context.getSystemService("alarm");
            this.case = new a(this);
            context.registerReceiver(this.case, new IntentFilter(for));
            this.d = PendingIntent.getBroadcast(context, 0, new Intent(for), 134217728);
            this.try.setRepeating(2, j.M, j.M, this.d);
            fVar.registerReceiver(this.case, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        }

        public void a() {
            if();
            if (f.j != null) {
                try {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(f.j, "rw");
                    if (randomAccessFile.length() < 1) {
                        randomAccessFile.close();
                        return;
                    }
                    randomAccessFile.seek(0);
                    int readInt = randomAccessFile.readInt();
                    int i = (readInt * 200) + 4;
                    readInt++;
                    randomAccessFile.seek(0);
                    randomAccessFile.writeInt(readInt);
                    randomAccessFile.seek((long) i);
                    randomAccessFile.writeLong(System.currentTimeMillis());
                    randomAccessFile.writeInt(this.byte);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.writeInt(this.int);
                    randomAccessFile.writeInt(this.b.do);
                    randomAccessFile.writeInt(this.b.if);
                    randomAccessFile.writeInt(this.b.for);
                    randomAccessFile.writeInt(this.b.try);
                    byte[] bArr = new byte[160];
                    for (int i2 = 0; i2 < this.int; i2++) {
                        bArr[(i2 * 8) + 7] = (byte) ((int) this.a[i2]);
                        bArr[(i2 * 8) + 6] = (byte) ((int) (this.a[i2] >> 8));
                        bArr[(i2 * 8) + 5] = (byte) ((int) (this.a[i2] >> 16));
                        bArr[(i2 * 8) + 4] = (byte) ((int) (this.a[i2] >> 24));
                        bArr[(i2 * 8) + 3] = (byte) ((int) (this.a[i2] >> 32));
                        bArr[(i2 * 8) + 2] = (byte) ((int) (this.a[i2] >> 40));
                        bArr[(i2 * 8) + 1] = (byte) ((int) (this.a[i2] >> 48));
                        bArr[(i2 * 8) + 0] = (byte) ((int) (this.a[i2] >> f.ak));
                    }
                    if (this.int > 0) {
                        randomAccessFile.write(bArr, 0, this.int * 8);
                    }
                    randomAccessFile.writeInt(this.int);
                    randomAccessFile.close();
                } catch (Exception e) {
                }
            }
        }

        public void byte() {
            int i = 0;
            if (this.do) {
                this.byte = 1;
                j.M = (j.ac * 1000) * 60;
                j.al = j.M >> 2;
                Calendar instance = Calendar.getInstance();
                int i2 = instance.get(5);
                int i3 = instance.get(1);
                if (i3 > f.else) {
                    i = i3 - 2000;
                }
                i3 = instance.get(2) + 1;
                int i4 = instance.get(11);
                String str = i + SelectCountryActivity.SPLITTER + i3 + SelectCountryActivity.SPLITTER + i2 + SelectCountryActivity.SPLITTER + i4 + SelectCountryActivity.SPLITTER + instance.get(12) + SelectCountryActivity.SPLITTER + j.ac;
                if (this.if) {
                    this.long = "&tr=" + j.do + SelectCountryActivity.SPLITTER + str;
                } else {
                    this.long += "|T" + str;
                }
                j.if(f.v, "trace begin:" + this.long);
                try {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(f.k, "rw");
                    randomAccessFile.seek(12);
                    randomAccessFile.writeLong(System.currentTimeMillis());
                    randomAccessFile.writeInt(this.byte);
                    randomAccessFile.close();
                    randomAccessFile = new RandomAccessFile(f.j, "rw");
                    randomAccessFile.seek(0);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.close();
                } catch (Exception e) {
                }
            }
        }

        public void case() {
            f.long();
            if (f.k != null) {
                try {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(f.k, "rw");
                    if (randomAccessFile.length() < 1) {
                        randomAccessFile.close();
                        return;
                    }
                    randomAccessFile.seek(0);
                    int readInt = randomAccessFile.readInt();
                    int readInt2 = randomAccessFile.readInt();
                    int readInt3 = randomAccessFile.readInt();
                    if (this.do && this.if) {
                        j.if(f.v, "trace new info:" + readInt + ":" + readInt2 + ":" + readInt3);
                        int i = (readInt2 + 1) % 200;
                        randomAccessFile.seek(4);
                        randomAccessFile.writeInt(i);
                        readInt++;
                        if (readInt >= 200) {
                            readInt = SecExceptionCode.SEC_ERROR_INIT_UNKNOWN_ERROR;
                        }
                        if (i == readInt3 && readInt > 0) {
                            readInt3 = (readInt3 + 1) % 200;
                            randomAccessFile.writeInt(readInt3);
                        }
                        j.if(f.v, "trace new info:" + readInt + ":" + readInt2 + ":" + readInt3);
                        readInt3 = (i * 800) + 24;
                    } else {
                        readInt3 = (readInt2 * 800) + 24;
                    }
                    randomAccessFile.seek((long) (readInt3 + 4));
                    byte[] bytes = this.long.getBytes();
                    for (int i2 = 0; i2 < bytes.length; i2++) {
                        bytes[i2] = (byte) (bytes[i2] ^ 90);
                    }
                    randomAccessFile.write(bytes, 0, bytes.length);
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.seek((long) readInt3);
                    randomAccessFile.writeInt(bytes.length);
                    if (this.do && this.if) {
                        randomAccessFile.seek(0);
                        randomAccessFile.writeInt(readInt);
                    }
                    randomAccessFile.close();
                } catch (Exception e) {
                }
            }
        }

        public void do() {
            int i = 0;
            try {
                j.if(f.v, "regular expire...");
                new();
                if (this.else) {
                    this.else = false;
                    return;
                }
                byte();
                this.int = 0;
                this.b = null;
                if (this.e.F != null) {
                    this.e.F.new();
                }
                if (this.e.F != null) {
                    c cVar = this.e.F.byte();
                    if (!(cVar == null || cVar.for == null)) {
                        int size = cVar.for.size();
                        if (size > 20) {
                            size = 20;
                        }
                        int i2 = 0;
                        while (i2 < size) {
                            int i3;
                            String replace = ((ScanResult) cVar.for.get(i2)).BSSID.replace(":", "");
                            try {
                                i3 = i + 1;
                                try {
                                    this.a[i] = Long.parseLong(replace, 16);
                                } catch (Exception e) {
                                }
                            } catch (Exception e2) {
                                i3 = i;
                            }
                            i2++;
                            i = i3;
                        }
                        this.int = i;
                    }
                }
                if (this.e.r != null) {
                    this.b = this.e.r.a();
                }
                if (this.b != null) {
                    for();
                }
            } catch (Exception e3) {
            }
        }

        public void for() {
            String str;
            Object obj;
            String str2;
            long j;
            String str3;
            if();
            j.if(f.v, "trace1:" + this.long);
            try {
                str = this.e.else() ? "y2" : "y1";
            } catch (Exception e) {
                str = "y";
            }
            if (!this.do) {
                try {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(f.j, "rw");
                    if (randomAccessFile.length() < 1) {
                        randomAccessFile.close();
                        return;
                    }
                    int readInt = randomAccessFile.readInt();
                    for (int i = 0; i < readInt; i++) {
                        randomAccessFile.seek((long) ((i * 200) + 4));
                        randomAccessFile.readLong();
                        int readInt2 = randomAccessFile.readInt();
                        int readInt3 = randomAccessFile.readInt();
                        int readInt4 = randomAccessFile.readInt();
                        byte[] bArr = new byte[200];
                        randomAccessFile.read(bArr, 0, (readInt4 * 8) + 16);
                        int i2 = (((bArr[7] & MotionEventCompat.ACTION_MASK) | ((bArr[6] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | ((bArr[5] << 16) & 16711680)) | ((bArr[4] << 24) & ViewCompat.MEASURED_STATE_MASK);
                        int i3 = (((bArr[11] & MotionEventCompat.ACTION_MASK) | ((bArr[10] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | ((bArr[9] << 16) & 16711680)) | ((bArr[8] << 24) & ViewCompat.MEASURED_STATE_MASK);
                        int i4 = (((bArr[15] & MotionEventCompat.ACTION_MASK) | ((bArr[14] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | ((bArr[13] << 16) & 16711680)) | ((bArr[12] << 24) & ViewCompat.MEASURED_STATE_MASK);
                        if (this.b.do == ((((bArr[3] & MotionEventCompat.ACTION_MASK) | ((bArr[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | ((bArr[1] << 16) & 16711680)) | ((bArr[0] << 24) & ViewCompat.MEASURED_STATE_MASK)) && this.b.if == i2 && this.b.for == i3 && this.b.try == i4) {
                            int i5;
                            long[] jArr = new long[readInt4];
                            for (i5 = 0; i5 < readInt4; i5++) {
                                jArr[i5] = ((((((((((long) bArr[(i5 * 8) + 16]) & 255) << f.ak) | ((((long) bArr[((i5 * 8) + 16) + 1]) & 255) << 48)) | ((((long) bArr[((i5 * 8) + 16) + 2]) & 255) << 40)) | ((((long) bArr[((i5 * 8) + 16) + 3]) & 255) << 32)) | ((((long) bArr[((i5 * 8) + 16) + 4]) & 255) << 24)) | ((((long) bArr[((i5 * 8) + 16) + 5]) & 255) << 16)) | ((((long) bArr[((i5 * 8) + 16) + 6]) & 255) << 8)) | (((long) bArr[((i5 * 8) + 16) + 7]) & 255);
                            }
                            int i6 = 0;
                            i2 = 0;
                            while (i2 < this.int) {
                                i5 = i6;
                                for (i6 = 0; i6 < readInt4; i6++) {
                                    if (this.a[i2] == jArr[i6]) {
                                        i5++;
                                    }
                                }
                                i2++;
                                i6 = i5;
                            }
                            if (i6 > 5 || i6 * 8 > this.int + readInt4 || ((readInt4 == 0 && this.int == 0) || ((readInt4 == 1 && this.int == 1 && this.a[0] == jArr[0]) || (readInt4 > 1 && this.int > 1 && this.a[0] == jArr[0] && this.a[1] == jArr[1])))) {
                                obj = 1;
                                int i7 = readInt3 + 1;
                                randomAccessFile.seek((long) ((i * 200) + 16));
                                randomAccessFile.writeInt(i7);
                                if (this.long != null) {
                                    this.long += "|" + readInt2 + str;
                                    if (this.c != null) {
                                        this.long += this.c;
                                    }
                                }
                                j.if(f.v, "daily info:is same");
                                if (obj == null) {
                                    str2 = (this.b.do != 460 ? "|x," : "|x460,") + this.b.if + SelectCountryActivity.SPLITTER + this.b.for + SelectCountryActivity.SPLITTER + this.b.try;
                                    j = 0;
                                    if (this.e.F != null) {
                                        str3 = this.e.F.char();
                                        if (str3 != null) {
                                            try {
                                                j = Long.parseLong(str3, 16);
                                            } catch (Exception e2) {
                                            }
                                        }
                                    }
                                    if (this.int == 1) {
                                        str2 = str2 + "w" + Long.toHexString(this.a[0]) + "k";
                                        if (this.a[0] == j) {
                                            str2 = str2 + "k";
                                        }
                                    } else if (this.int > 1) {
                                        str2 = str2 + "w" + Long.toHexString(this.a[0]);
                                        if (this.a[0] == j) {
                                            str2 = str2 + "k";
                                            j = 0;
                                        }
                                        str2 = j <= 0 ? str2 + SelectCountryActivity.SPLITTER + Long.toHexString(j) + "k" : str2 + SelectCountryActivity.SPLITTER + Long.toHexString(this.a[1]);
                                    }
                                    this.long += str2 + str;
                                    if (this.c != null) {
                                        this.long += this.c;
                                    }
                                    a();
                                }
                                j.if(f.v, "trace2:" + this.long);
                                case();
                                this.long = null;
                            }
                        }
                    }
                } catch (Exception e3) {
                    return;
                }
            }
            obj = null;
            if (obj == null) {
                if (this.b.do != 460) {
                }
                str2 = (this.b.do != 460 ? "|x," : "|x460,") + this.b.if + SelectCountryActivity.SPLITTER + this.b.for + SelectCountryActivity.SPLITTER + this.b.try;
                j = 0;
                if (this.e.F != null) {
                    str3 = this.e.F.char();
                    if (str3 != null) {
                        j = Long.parseLong(str3, 16);
                    }
                }
                if (this.int == 1) {
                    str2 = str2 + "w" + Long.toHexString(this.a[0]) + "k";
                    if (this.a[0] == j) {
                        str2 = str2 + "k";
                    }
                } else if (this.int > 1) {
                    str2 = str2 + "w" + Long.toHexString(this.a[0]);
                    if (this.a[0] == j) {
                        str2 = str2 + "k";
                        j = 0;
                    }
                    if (j <= 0) {
                    }
                }
                this.long += str2 + str;
                if (this.c != null) {
                    this.long += this.c;
                }
                a();
            }
            j.if(f.v, "trace2:" + this.long);
            case();
            this.long = null;
        }

        public void if() {
            try {
                if (this.e.m != null) {
                    f.j = new File(this.e.m);
                    if (!f.j.exists()) {
                        File file = new File(f.ac);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        f.j.createNewFile();
                        RandomAccessFile randomAccessFile = new RandomAccessFile(f.j, "rw");
                        randomAccessFile.seek(0);
                        randomAccessFile.writeInt(0);
                        randomAccessFile.close();
                        return;
                    }
                    return;
                }
                f.j = null;
            } catch (Exception e) {
                f.j = null;
            }
        }

        public void int() {
        }

        public void new() {
            int i = 0;
            this.do = false;
            this.if = false;
            if();
            f.long();
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(f.k, "rw");
                randomAccessFile.seek(0);
                int readInt = randomAccessFile.readInt();
                int readInt2 = randomAccessFile.readInt();
                randomAccessFile.readInt();
                long readLong = randomAccessFile.readLong();
                int readInt3 = randomAccessFile.readInt();
                if (readInt < 0) {
                    this.do = true;
                    this.if = true;
                    randomAccessFile.close();
                    return;
                }
                randomAccessFile.seek((long) ((readInt2 * 800) + 24));
                readInt = randomAccessFile.readInt();
                if (readInt > 680) {
                    this.do = true;
                    this.if = true;
                    randomAccessFile.close();
                    return;
                }
                byte[] bArr = new byte[800];
                randomAccessFile.read(bArr, 0, readInt);
                if (readInt != randomAccessFile.readInt()) {
                    j.if(f.v, "trace true check fail");
                    this.do = true;
                    this.if = true;
                    randomAccessFile.close();
                    return;
                }
                while (i < bArr.length) {
                    bArr[i] = (byte) (bArr[i] ^ 90);
                    i++;
                }
                this.long = new String(bArr, 0, readInt);
                if (this.long.contains("&tr=")) {
                    long currentTimeMillis = System.currentTimeMillis();
                    readLong = currentTimeMillis - readLong;
                    if (readLong > (j.M * 3) - j.al) {
                        this.do = true;
                    } else if (readLong > (j.M * 2) - j.al) {
                        this.long += "|" + readInt3;
                        this.byte = readInt3 + 2;
                    } else if (readLong > j.M - j.al) {
                        this.byte = readInt3 + 1;
                    } else {
                        this.else = true;
                        randomAccessFile.close();
                        return;
                    }
                    randomAccessFile.seek(12);
                    randomAccessFile.writeLong(currentTimeMillis);
                    randomAccessFile.writeInt(this.byte);
                    randomAccessFile.close();
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(f.j, "rw");
                    randomAccessFile2.seek(0);
                    if (randomAccessFile2.readInt() == 0) {
                        this.do = true;
                        randomAccessFile2.close();
                        j.if(f.v, "Day file number 0");
                        return;
                    }
                    randomAccessFile2.close();
                    return;
                }
                this.do = true;
                this.if = true;
                randomAccessFile.close();
            } catch (Exception e) {
                j.if(f.v, "exception!!!");
                this.do = true;
                this.if = true;
            }
        }

        public void try() {
            this.goto.unregisterReceiver(this.case);
            this.try.cancel(this.d);
            f.j = null;
        }
    }

    public class d extends Handler {
        final /* synthetic */ f a;

        public d(f fVar) {
            this.a = fVar;
        }

        public void handleMessage(Message message) {
            if (this.a.ad) {
                switch (message.what) {
                    case 11:
                        this.a.if(message);
                        break;
                    case 12:
                        this.a.new(message);
                        break;
                    case 15:
                        this.a.try(message);
                        break;
                    case 21:
                        this.a.a(message, 21);
                        break;
                    case 22:
                        this.a.int(message);
                        break;
                    case 25:
                        this.a.do(message);
                        break;
                    case 26:
                        this.a.a(message, 26);
                        break;
                    case 28:
                        this.a.for(message);
                        break;
                    case 31:
                        this.a.goto();
                        break;
                    case f.p /*41*/:
                        this.a.do();
                        break;
                    case f.z /*51*/:
                        this.a.if();
                        break;
                    case f.G /*52*/:
                        this.a.b();
                        break;
                    case f.ar /*53*/:
                        this.a.c();
                        break;
                    case f.D /*57*/:
                        this.a.a(message);
                        break;
                    case 62:
                    case 63:
                        this.a.a(21);
                        break;
                    case 64:
                    case 65:
                        this.a.a(26);
                        break;
                    case f.V /*81*/:
                        this.a.try();
                        break;
                    case f.t /*91*/:
                        this.a.int();
                        break;
                    case f.B /*92*/:
                        this.a.char();
                        break;
                    case 101:
                        if (this.a.y != null) {
                            this.a.y.do();
                            break;
                        }
                        break;
                }
            }
            super.handleMessage(message);
        }
    }

    private String a(String str) {
        String str2;
        String str3 = null;
        j.if(v, "generate locdata ...");
        if ((this.try == null || !this.try.do()) && this.r != null) {
            this.try = this.r.a();
        }
        this.A = this.try.a();
        if (this.try != null) {
            j.a(v, this.try.if());
        } else {
            j.a(v, "cellInfo null...");
        }
        if ((this.C == null || !this.C.for()) && this.F != null) {
            this.C = this.F.byte();
        }
        if (this.C != null) {
            j.a(v, this.C.else());
        } else {
            j.a(v, "wifi list null");
        }
        if (this.ab == null || !this.ab.new()) {
            this.f = null;
        } else {
            this.f = this.ab.try();
        }
        if (this.au != null) {
            str3 = this.au.byte();
        }
        if (3 == g.do((Context) this)) {
            str2 = "&cn=32";
        } else {
            str2 = String.format("&cn=%d", new Object[]{Integer.valueOf(this.r.new())});
        }
        if (this.Y) {
            String str4 = k.if();
            if (str4 != null) {
                str2 = str2 + str4;
            }
        }
        str3 = str2 + str3;
        if (str != null) {
            str3 = str + str3;
        }
        return j.a(this.try, this.C, this.f, str3, 0);
    }

    private String a(boolean z) {
        if ((this.try == null || !this.try.do()) && this.r != null) {
            this.try = this.r.a();
        }
        do(this.try.a());
        return if(z);
    }

    private void a(int i) {
        j.if(v, "on network exception");
        j.a(v, "on network exception");
        this.new = null;
        this.char = null;
        if (this.au != null) {
            this.au.a(a(false), i);
        }
        if (i == 21) {
            case();
        }
    }

    private void a(Message message) {
        if (message == null || message.obj == null) {
            j.if(v, "Gps updateloation is null");
            return;
        }
        Location location = (Location) message.obj;
        if (location != null) {
            j.if(v, "on update gps...");
            if (k.a(location, true) && this.r != null && this.F != null && this.au != null) {
            }
        }
    }

    private void a(Message message, int i) {
        j.if(v, "on network success");
        j.a(v, "on network success");
        String str = (String) message.obj;
        j.if(v, "network:" + str);
        if (this.au != null) {
            this.au.a(str, i);
        }
        if (j.do(str)) {
            if (i == 21) {
                this.new = str;
            } else {
                this.H = str;
            }
        } else if (i == 21) {
            this.new = null;
        } else {
            this.H = null;
        }
        int i2 = j.if(str, "ssid\":\"", "\"");
        if (i2 == ExploreByTouchHelper.INVALID_ID || this.char == null) {
            this.h = null;
        } else {
            this.h = this.char.if(i2);
        }
        if(str);
        double d = j.do(str, "a\":\"", "\"");
        if (d != Double.MIN_VALUE) {
            k.a(d, j.do(str, "b\":\"", "\""), j.do(str, "c\":\"", "\""), j.do(str, "b\":\"", "\""));
        }
        i2 = j.if(str, "rWifiN\":\"", "\"");
        if (i2 > 15) {
            j.Y = i2;
        }
        i2 = j.if(str, "rWifiT\":\"", "\"");
        if (i2 > SecExceptionCode.SEC_ERROR_DYN_STORE) {
            j.S = i2;
        }
        float a = j.a(str, "hSpeedDis\":\"", "\"");
        if (a > 5.0f) {
            j.Q = a;
        }
        a = j.a(str, "mSpeedDis\":\"", "\"");
        if (a > 5.0f) {
            j.ai = a;
        }
        a = j.a(str, "mWifiR\":\"", "\"");
        if (a < 1.0f && ((double) a) > 0.2d) {
            j.byte = a;
        }
        if (i == 21) {
            case();
        }
    }

    private boolean a(com.baidu.location.c.a aVar) {
        boolean z = true;
        if (this.r == null) {
            return false;
        }
        this.try = this.r.a();
        if (this.try == aVar) {
            return false;
        }
        if (this.try == null || aVar == null) {
            return true;
        }
        if (aVar.a(this.try)) {
            z = false;
        }
        return z;
    }

    private boolean a(c cVar) {
        boolean z = true;
        if (this.F == null) {
            return false;
        }
        this.C = this.F.byte();
        if (cVar == this.C) {
            return false;
        }
        if (this.C == null || cVar == null) {
            return true;
        }
        if (cVar.a(this.C)) {
            z = false;
        }
        return z;
    }

    private void b() {
        j.if(v, "on switch gps ...");
        if (this.au != null) {
            if (this.au.for()) {
                if (this.ab == null) {
                    this.ab = new b(this, this.R);
                }
                this.ab.k();
            } else if (this.ab != null) {
                this.ab.l();
                this.ab = null;
            }
        }
    }

    private void byte() {
        if (!this.O) {
            if (System.currentTimeMillis() - this.P < 1000) {
                j.if(v, "request too frequency ...");
                if (this.new != null) {
                    this.au.a(this.new);
                    case();
                    return;
                }
            }
            j.if(v, "start network locating ...");
            j.a(v, "start network locating ...");
            this.O = true;
            this.J = a(this.al);
            if (a(this.char) || this.J || this.new == null) {
                String a = a(null);
                if (a == null) {
                    this.au.a("{\"result\":{\"time\":\"" + j.for() + "\",\"error\":\"62\"}}");
                    case();
                    return;
                }
                if (this.h != null) {
                    a = a + this.h;
                    this.h = null;
                }
                if (g.a(a, this.R)) {
                    this.al = this.try;
                    this.char = this.C;
                } else {
                    j.if(v, "request error ..");
                }
                if (this.Y) {
                    this.Y = false;
                }
                this.P = System.currentTimeMillis();
                return;
            }
            this.au.a(this.new);
            case();
        }
    }

    private void c() {
        if (this.au != null) {
            this.au.new();
        }
    }

    private void case() {
        this.O = false;
        void();
    }

    private void char() {
        try {
            if (j.n && j.G) {
                this.y = new c(this, this);
            }
        } catch (Exception e) {
        }
    }

    private void d() {
        File file = new File(ac);
        File file2 = new File(ac + "/ls.db");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (Exception e) {
            }
        }
        if (file2.exists()) {
            this.T = SQLiteDatabase.openOrCreateDatabase(file2, null);
            this.T.execSQL("CREATE TABLE IF NOT EXISTS " + this.if + "(id CHAR(40) PRIMARY KEY,time DOUBLE,tag DOUBLE, type DOUBLE , ac INT);");
        }
    }

    private void do() {
        j.if(v, "on new wifi ...");
        if (this.aj) {
            byte();
            this.aj = false;
        }
    }

    private void do(Message message) {
        if (System.currentTimeMillis() - this.d < 3000) {
            j.if(v, "request too frequency ...");
            if (this.H != null) {
                this.au.a(this.H, 26);
                return;
            }
        }
        if (this.au != null) {
            String a = a(this.au.a(message));
            if (this.h != null) {
                a = a + this.h;
                this.h = null;
            }
            g.do((Context) this);
            if (g.if(a, this.R)) {
                this.u = this.try;
                this.ae = this.C;
            } else {
                j.if(v, "request poi error ..");
            }
            this.d = System.currentTimeMillis();
        }
    }

    private void do(String str) {
        if (this.T == null || str == null) {
            j.if(v, "db is null...");
            this.Q = false;
            return;
        }
        j.if(v, "LOCATING...");
        if (System.currentTimeMillis() - this.E >= 1500 && !str.equals(this.as)) {
            this.Q = false;
            try {
                Cursor rawQuery = this.T.rawQuery("select * from " + this.if + " where id = \"" + str + "\";", null);
                this.as = str;
                this.E = System.currentTimeMillis();
                if (rawQuery != null) {
                    if (rawQuery.moveToFirst()) {
                        j.if(v, "lookup DB success:" + this.as);
                        this.o = rawQuery.getDouble(1) - 1235.4323d;
                        this.q = rawQuery.getDouble(2) - 4326.0d;
                        this.n = rawQuery.getDouble(3) - 2367.3217d;
                        this.Q = true;
                        j.if(v, "lookup DB success:x" + this.o + "y" + this.n + "r" + this.q);
                    }
                    rawQuery.close();
                }
            } catch (Exception e) {
                this.E = System.currentTimeMillis();
            }
        }
    }

    private void for(Message message) {
        if (this.au != null) {
            this.au.a(a(true), message);
        }
    }

    private void goto() {
        j.if(v, "on new cell ...");
    }

    private String if(boolean z) {
        if (!this.Q) {
            return z ? "{\"result\":{\"time\":\"" + j.for() + "\",\"error\":\"67\"}}" : "{\"result\":{\"time\":\"" + j.for() + "\",\"error\":\"63\"}}";
        } else {
            if (z) {
                return String.format("{\"result\":{\"time\":\"" + j.for() + "\",\"error\":\"66\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%f\",\"isCellChanged\":\"%b\"}}", new Object[]{Double.valueOf(this.o), Double.valueOf(this.n), Double.valueOf(this.q), Boolean.valueOf(true)});
            }
            return String.format("{\"result\":{\"time\":\"" + j.for() + "\",\"error\":\"68\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%f\",\"isCellChanged\":\"%b\"}}", new Object[]{Double.valueOf(this.o), Double.valueOf(this.n), Double.valueOf(this.q), Boolean.valueOf(this.J)});
        }
    }

    private void if() {
        if (this.ab != null) {
            j.if(v, "on new gps...");
            Location location = this.ab.try();
            if (!(!this.ab.new() || !k.a(location, true) || this.r == null || this.F == null || this.au == null)) {
                if (this.F != null) {
                    this.F.a();
                }
                k.a(this.r.a(), this.F.int(), location, this.au.byte());
            }
            if (this.au != null && this.ab.new()) {
                this.au.if(this.ab.int());
            }
        }
    }

    private void if(Message message) {
        if (this.au != null) {
            this.au.int(message);
        }
        if (this.F != null) {
            this.F.case();
        }
        if (this.N) {
            g.for(this.R);
            this.N = false;
        }
    }

    private void if(String str) {
        Object obj = null;
        double d = 0.0d;
        if (this.T != null && this.J) {
            try {
                double parseDouble;
                float parseFloat;
                j.if(v, "DB:" + str);
                JSONObject jSONObject = new JSONObject(str);
                int parseInt = Integer.parseInt(jSONObject.getJSONObject("result").getString("error"));
                int i;
                if (parseInt == BDLocation.TypeNetWorkLocation) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("content");
                    if (jSONObject2.has("clf")) {
                        String string = jSONObject2.getString("clf");
                        if (string.equals(FeedbackItem.STATUS_WAITING)) {
                            JSONObject jSONObject3 = jSONObject2.getJSONObject("point");
                            d = Double.parseDouble(jSONObject3.getString("x"));
                            parseDouble = Double.parseDouble(jSONObject3.getString("y"));
                            parseFloat = Float.parseFloat(jSONObject2.getString("radius"));
                        } else {
                            String[] split = string.split("\\|");
                            d = Double.parseDouble(split[0]);
                            parseDouble = Double.parseDouble(split[1]);
                            parseFloat = Float.parseFloat(split[2]);
                        }
                        j.if(v, "DB PARSE:x" + d + "y" + parseDouble + "R" + parseFloat);
                    }
                    i = 1;
                    parseFloat = 0.0f;
                    parseDouble = 0.0d;
                } else {
                    if (parseInt == BDLocation.TypeServerError) {
                        this.T.delete(this.if, "id = \"" + this.A + "\"", null);
                        return;
                    }
                    i = 1;
                    parseFloat = 0.0f;
                    parseDouble = 0.0d;
                }
                if (obj == null) {
                    d += 1235.4323d;
                    parseDouble += 2367.3217d;
                    float f = 4326.0f + parseFloat;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("time", Double.valueOf(d));
                    contentValues.put("tag", Float.valueOf(f));
                    contentValues.put(SocialConstants.PARAM_TYPE, Double.valueOf(parseDouble));
                    try {
                        if (this.T.update(this.if, contentValues, "id = \"" + this.A + "\"", null) <= 0) {
                            contentValues.put(StarCategory.KEY_STAR_CATEGORY_ID, this.A);
                            this.T.insert(this.if, null, contentValues);
                            j.if(v, "insert DB success!");
                        }
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                j.if(v, "DB PARSE:exp!");
            }
        }
    }

    private void int() {
        if (g.a((Context) this)) {
            g.f();
        }
    }

    private void int(Message message) {
        j.if(v, "on request location ...");
        j.a(v, "on request location ...");
        if (this.au != null) {
            if (this.au.do(message) == 1 && this.ab != null && this.ab.new()) {
                j.if(v, "send gps location to client ...");
                this.au.a(this.ab.int(), message);
            } else if (this.Y) {
                byte();
            } else if (!this.O) {
                if (this.F == null || !this.F.new()) {
                    byte();
                    return;
                }
                this.aj = true;
                this.R.postDelayed(new b(), 2000);
            }
        }
    }

    public static void long() {
        try {
            if (a != null) {
                k = new File(a);
                if (!k.exists()) {
                    File file = new File(ac);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    k.createNewFile();
                    RandomAccessFile randomAccessFile = new RandomAccessFile(k, "rw");
                    randomAccessFile.seek(0);
                    randomAccessFile.writeInt(-1);
                    randomAccessFile.writeInt(-1);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.writeLong(0);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.close();
                    return;
                }
                return;
            }
            k = null;
        } catch (Exception e) {
            k = null;
        }
    }

    public static String new() {
        j.if(v, "read trace log1..");
        return null;
    }

    private void new(Message message) {
        if (this.au != null) {
            this.au.if(message);
        }
    }

    private void try() {
    }

    private void try(Message message) {
        if (!(this.au == null || !this.au.for(message) || this.F == null)) {
            this.F.for();
        }
        this.new = null;
    }

    private void void() {
        if (this.new != null && g.a((Context) this)) {
            g.f();
        }
    }

    public boolean else() {
        return ((KeyguardManager) getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    public IBinder onBind(Intent intent) {
        return this.an.getBinder();
    }

    public void onCreate() {
        Thread.setDefaultUncaughtExceptionHandler(new a(this, this));
        this.r = new c(this, this.R);
        this.F = new e(this, this.R);
        this.au = new a(this.R);
        this.r.do();
        this.F.try();
        this.ad = true;
        this.O = false;
        this.aj = false;
        try {
            d();
        } catch (Exception e) {
        }
        j.if(v, "OnCreate");
        Log.d(v, "baidu location service start1 ..." + Process.myPid());
    }

    public void onDestroy() {
        if (this.r != null) {
            this.r.byte();
        }
        if (this.F != null) {
            this.F.else();
        }
        if (this.ab != null) {
            this.ab.l();
        }
        k.a();
        this.O = false;
        this.aj = false;
        this.ad = false;
        if (this.y != null) {
            this.y.try();
        }
        if (this.T != null) {
            this.T.close();
        }
        j.if(v, "onDestroy");
        Log.d(v, "baidu location service stop ...");
        Process.killProcess(Process.myPid());
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        j.if(v, "onStratCommandNotSticky");
        return 2;
    }
}
