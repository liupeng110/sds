package com.baidu.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

class e {
    private static final int byte = 15;
    private static String try = f.v;
    private final long a = 5000;
    private long b = 0;
    private a c = null;
    private c case = null;
    private b char = null;
    private boolean d = false;
    private boolean do = false;
    private Method e = null;
    private boolean else = false;
    private final long f = 3000;
    private boolean for = true;
    private Object g = null;
    private Context goto;
    private Handler if = null;
    private boolean int = false;
    private long long = 0;
    private final long new = 3000;
    private WifiManager void = null;

    private class a extends BroadcastReceiver {
        final /* synthetic */ e a;

        private a(e eVar) {
            this.a = eVar;
        }

        public void onReceive(Context context, Intent intent) {
            if (context != null && this.a.if != null) {
                this.a.goto();
            }
        }
    }

    private class b extends BroadcastReceiver {
        final /* synthetic */ e a;

        private b(e eVar) {
            this.a = eVar;
        }

        public void onReceive(Context context, Intent intent) {
            if (context != null && this.a.if != null) {
                this.a.if();
                this.a.if.obtainMessage(41).sendToTarget();
                j.if(e.try, "wifi manager receive new wifi...");
            }
        }
    }

    protected class c {
        final /* synthetic */ e a;
        private boolean do = false;
        public List for = null;
        private long if = 0;
        private long int = 0;

        public c(e eVar, List list, long j) {
            this.a = eVar;
            this.if = j;
            this.for = list;
            this.int = System.currentTimeMillis();
            a();
            j.a(e.try, int());
        }

        private void a() {
            if (try() >= 1) {
                Object obj = 1;
                for (int size = this.for.size() - 1; size >= 1 && r2 != null; size--) {
                    int i = 0;
                    obj = null;
                    while (i < size) {
                        Object obj2;
                        if (((ScanResult) this.for.get(i)).level < ((ScanResult) this.for.get(i + 1)).level) {
                            ScanResult scanResult = (ScanResult) this.for.get(i + 1);
                            this.for.set(i + 1, this.for.get(i));
                            this.for.set(i, scanResult);
                            obj2 = 1;
                        } else {
                            obj2 = obj;
                        }
                        i++;
                        obj = obj2;
                    }
                }
            }
        }

        public String a(int i) {
            if (try() < 1) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(512);
            String str = this.a.char();
            int size = this.for.size();
            if (size <= i) {
                i = size;
            }
            int i2 = 0;
            boolean z = true;
            int i3 = 0;
            int i4 = 0;
            while (i2 < i) {
                boolean z2;
                int i5;
                if (((ScanResult) this.for.get(i2)).level == 0) {
                    z2 = z;
                    i5 = i4;
                } else if (z) {
                    stringBuffer.append("&wf=");
                    r10 = ((ScanResult) this.for.get(i2)).BSSID.replace(":", "");
                    stringBuffer.append(r10);
                    size = ((ScanResult) this.for.get(i2)).level;
                    if (size < 0) {
                        size = -size;
                    }
                    stringBuffer.append(String.format(";%d;", new Object[]{Integer.valueOf(size)}));
                    i5 = i4 + 1;
                    size = (str == null || !str.equals(r10)) ? i3 : i5;
                    i3 = size;
                    z2 = false;
                } else {
                    stringBuffer.append("|");
                    r10 = ((ScanResult) this.for.get(i2)).BSSID.replace(":", "");
                    stringBuffer.append(r10);
                    size = ((ScanResult) this.for.get(i2)).level;
                    if (size < 0) {
                        size = -size;
                    }
                    stringBuffer.append(String.format(";%d;", new Object[]{Integer.valueOf(size)}));
                    size = i4 + 1;
                    boolean z3;
                    if (str == null || !str.equals(r10)) {
                        z3 = z;
                        i5 = size;
                        z2 = z3;
                    } else {
                        i3 = size;
                        z3 = z;
                        i5 = size;
                        z2 = z3;
                    }
                }
                i2++;
                i4 = i5;
                z = z2;
            }
            if (z) {
                return null;
            }
            j.if(e.try, str + i3);
            stringBuffer.append("&wf_n=" + i3);
            stringBuffer.append("&wf_st=");
            stringBuffer.append(this.if);
            stringBuffer.append("&wf_et=");
            stringBuffer.append(this.int);
            if (i3 > 0) {
                this.do = true;
            }
            return stringBuffer.toString();
        }

        public boolean a(c cVar) {
            return a(cVar, this, j.a);
        }

        public boolean a(c cVar, c cVar2, float f) {
            if (cVar == null || cVar2 == null) {
                return false;
            }
            List list = cVar.for;
            List list2 = cVar2.for;
            if (list == list2) {
                return true;
            }
            if (list == null || list2 == null) {
                return false;
            }
            int size = list.size();
            int size2 = list2.size();
            float f2 = (float) (size + size2);
            if (size == 0 && size2 == 0) {
                return true;
            }
            if (size == 0 || size2 == 0) {
                return false;
            }
            int i = 0;
            int i2 = 0;
            while (i < size) {
                int i3;
                String str = ((ScanResult) list.get(i)).BSSID;
                if (str == null) {
                    i3 = i2;
                } else {
                    for (int i4 = 0; i4 < size2; i4++) {
                        if (str.equals(((ScanResult) list2.get(i4)).BSSID)) {
                            i3 = i2 + 1;
                            break;
                        }
                    }
                    i3 = i2;
                }
                i++;
                i2 = i3;
            }
            j.if(e.try, String.format("same %d,total %f,rate %f...", new Object[]{Integer.valueOf(i2), Float.valueOf(f2), Float.valueOf(f)}));
            return ((float) (i2 * 2)) >= f2 * f;
        }

        public String byte() {
            try {
                return a(15);
            } catch (Exception e) {
                return null;
            }
        }

        public boolean case() {
            return this.do;
        }

        public String char() {
            try {
                return a(j.Y);
            } catch (Exception e) {
                return null;
            }
        }

        public int do() {
            for (int i = 0; i < try(); i++) {
                int i2 = -((ScanResult) this.for.get(i)).level;
                if (i2 > 0) {
                    return i2;
                }
            }
            return 0;
        }

        public boolean do(c cVar) {
            if (this.for == null || cVar == null || cVar.for == null) {
                return false;
            }
            int size = this.for.size() < cVar.for.size() ? this.for.size() : cVar.for.size();
            for (int i = 0; i < size; i++) {
                String str = ((ScanResult) this.for.get(i)).BSSID;
                int i2 = ((ScanResult) this.for.get(i)).level;
                String str2 = ((ScanResult) cVar.for.get(i)).BSSID;
                int i3 = ((ScanResult) cVar.for.get(i)).level;
                if (!str.equals(str2) || i2 != i3) {
                    return false;
                }
            }
            return true;
        }

        public String else() {
            StringBuffer stringBuffer = new StringBuffer(512);
            stringBuffer.append("wifi info:");
            if (try() < 1) {
                return stringBuffer.toString();
            }
            int size = this.for.size();
            if (size > 10) {
                size = 10;
            }
            int i = 0;
            int i2 = 1;
            while (i < size) {
                int i3;
                if (((ScanResult) this.for.get(i)).level == 0) {
                    i3 = i2;
                } else if (i2 != 0) {
                    stringBuffer.append("wifi=");
                    stringBuffer.append(((ScanResult) this.for.get(i)).BSSID.replace(":", ""));
                    stringBuffer.append(String.format(";%d;", new Object[]{Integer.valueOf(((ScanResult) this.for.get(i)).level)}));
                    i3 = 0;
                } else {
                    stringBuffer.append(";");
                    stringBuffer.append(((ScanResult) this.for.get(i)).BSSID.replace(":", ""));
                    stringBuffer.append(String.format(",%d;", new Object[]{Integer.valueOf(((ScanResult) this.for.get(i)).level)}));
                    i3 = i2;
                }
                i++;
                i2 = i3;
            }
            return stringBuffer.toString();
        }

        public boolean for() {
            return System.currentTimeMillis() - this.int < 3000;
        }

        public String if(int i) {
            if (i == 0 || try() < 1) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(256);
            int i2 = 0;
            int i3 = 1;
            for (int i4 = 0; i4 < j.Y; i4++) {
                if ((i3 & i) != 0) {
                    if (i2 == 0) {
                        stringBuffer.append("&ssid=");
                    } else {
                        stringBuffer.append("|");
                    }
                    stringBuffer.append(((ScanResult) this.for.get(i4)).BSSID);
                    stringBuffer.append(";");
                    stringBuffer.append(((ScanResult) this.for.get(i4)).SSID);
                    i2++;
                }
                i3 <<= 1;
            }
            return stringBuffer.toString();
        }

        public boolean if() {
            return System.currentTimeMillis() - this.if < 3000;
        }

        public boolean if(c cVar) {
            if (this.for == null || cVar == null || cVar.for == null) {
                return false;
            }
            int size = this.for.size() < cVar.for.size() ? this.for.size() : cVar.for.size();
            for (int i = 0; i < size; i++) {
                if (!((ScanResult) this.for.get(i)).BSSID.equals(((ScanResult) cVar.for.get(i)).BSSID)) {
                    return false;
                }
            }
            return true;
        }

        public String int() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("wifi=");
            if (this.for == null) {
                return stringBuilder.toString();
            }
            for (int i = 0; i < this.for.size(); i++) {
                int i2 = ((ScanResult) this.for.get(i)).level;
                stringBuilder.append(((ScanResult) this.for.get(i)).BSSID.replace(":", ""));
                stringBuilder.append(String.format(",%d;", new Object[]{Integer.valueOf(i2)}));
            }
            return stringBuilder.toString();
        }

        public boolean new() {
            return System.currentTimeMillis() - this.int < 5000;
        }

        public int try() {
            return this.for == null ? 0 : this.for.size();
        }
    }

    private class d implements Runnable {
        final /* synthetic */ e a;

        private d(e eVar) {
            this.a = eVar;
        }

        public void run() {
            if (this.a.d && j.R) {
                this.a.if.obtainMessage(91).sendToTarget();
                this.a.if.postDelayed(this, (long) j.S);
                this.a.do = true;
                return;
            }
            this.a.do = false;
        }
    }

    public e(Context context, Handler handler) {
        this.goto = context;
        this.if = handler;
    }

    private void goto() {
        State state;
        State state2 = State.UNKNOWN;
        try {
            state = ((ConnectivityManager) this.goto.getSystemService("connectivity")).getNetworkInfo(1).getState();
        } catch (Exception e) {
            state = state2;
        }
        if (State.CONNECTED != state) {
            this.d = false;
        } else if (!this.d) {
            this.d = true;
            this.if.postDelayed(new d(), (long) j.S);
            this.do = true;
        }
    }

    private void if() {
        if (this.void != null) {
            try {
                c cVar = new c(this, this.void.getScanResults(), this.b);
                this.b = 0;
                if (this.case == null || !cVar.if(this.case)) {
                    this.case = cVar;
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.long <= 10000) {
            return false;
        }
        this.long = currentTimeMillis;
        return new();
    }

    public c byte() {
        if ((this.case != null && this.case.new()) || this.void == null) {
            return this.case;
        }
        try {
            return new c(this, this.void.getScanResults(), 0);
        } catch (Exception e) {
            return new c(this, null, 0);
        }
    }

    public void case() {
        if (this.c == null) {
            this.c = new a();
        }
    }

    public String char() {
        String str = null;
        WifiInfo connectionInfo = this.void.getConnectionInfo();
        if (connectionInfo != null) {
            try {
                String bssid = connectionInfo.getBSSID();
                if (bssid != null) {
                    str = bssid.replace(":", "");
                }
            } catch (Exception e) {
            }
        }
        return str;
    }

    public void else() {
        if (this.int) {
            try {
                this.goto.unregisterReceiver(this.char);
            } catch (Exception e) {
            }
            this.char = null;
            this.void = null;
            this.c = null;
            this.int = false;
            j.if(try, "wifimanager stop ...");
        }
    }

    public void for() {
        if (!this.do) {
        }
    }

    public c int() {
        if ((this.case != null && this.case.for()) || this.void == null) {
            return this.case;
        }
        try {
            return new c(this, this.void.getScanResults(), 0);
        } catch (Exception e) {
            return new c(this, null, 0);
        }
    }

    public boolean new() {
        if (this.void == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.b <= 3000) {
            return false;
        }
        try {
            if (this.void.isWifiEnabled()) {
                if (this.e == null || this.g == null) {
                    this.void.startScan();
                } else {
                    try {
                        this.e.invoke(this.g, new Object[]{Boolean.valueOf(this.for)});
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.void.startScan();
                    }
                }
                this.b = currentTimeMillis;
                j.if(try, "wifimanager start scan ...");
                return true;
            }
            this.b = 0;
            return false;
        } catch (Exception e2) {
            return false;
        }
    }

    public void try() {
        if (!this.int) {
            this.void = (WifiManager) this.goto.getSystemService("wifi");
            this.char = new b();
            try {
                this.goto.registerReceiver(this.char, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                this.c = new a();
            } catch (Exception e) {
            }
            this.int = true;
            j.if(try, "wifimanager start ...");
            try {
                Field declaredField = Class.forName("android.net.wifi.WifiManager").getDeclaredField("mService");
                if (declaredField == null) {
                    j.if(try, "android.net.wifi.WifiManager.mService  NOT  found ...");
                    return;
                }
                declaredField.setAccessible(true);
                this.g = declaredField.get(this.void);
                Class cls = this.g.getClass();
                j.if(try, "mserviceClass : " + cls.getName());
                this.e = cls.getDeclaredMethod("startScan", new Class[]{Boolean.TYPE});
                if (this.e == null) {
                    j.if(try, "mService.startScan NOT  found ...");
                } else {
                    this.e.setAccessible(true);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
