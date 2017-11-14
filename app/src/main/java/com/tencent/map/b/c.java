package com.tencent.map.b;

import android.net.wifi.ScanResult;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public final class c {
    private static c a;
    private long b = 0;
    private List<b> c = new ArrayList();
    private List<a> d = new ArrayList();
    private String e;

    static class a {
        public String a;

        private a() {
            this.a = null;
        }
    }

    static class b {
        public int a;
        public int b;
        public int c;
        public int d;

        private b() {
            this.a = -1;
            this.b = -1;
            this.c = -1;
            this.d = -1;
        }
    }

    public static c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    private static boolean a(StringBuffer stringBuffer) {
        try {
            return new JSONObject(stringBuffer.toString()).getJSONObject("location").getDouble("accuracy") < 5000.0d;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean a(List<ScanResult> list) {
        if (list == null) {
            return false;
        }
        int i;
        if (this.d != null) {
            i = 0;
            for (int i2 = 0; i2 < this.d.size(); i2++) {
                String str = ((a) this.d.get(i2)).a;
                int i3 = 0;
                while (str != null && i3 < list.size()) {
                    if (str.equals(((ScanResult) list.get(i3)).BSSID)) {
                        i++;
                        break;
                    }
                    i3++;
                }
            }
        } else {
            i = 0;
        }
        int size = list.size();
        return (size < 6 || i < (size / 2) + 1) ? (size >= 6 || i < 2) ? this.d.size() <= 2 && list.size() <= 2 && Math.abs(System.currentTimeMillis() - this.b) <= 30000 : true : true;
    }

    public final void a(int i, int i2, int i3, int i4, List<ScanResult> list) {
        this.b = System.currentTimeMillis();
        this.e = null;
        this.c.clear();
        b bVar = new b();
        bVar.a = i;
        bVar.b = i2;
        bVar.c = i3;
        bVar.d = i4;
        this.c.add(bVar);
        if (list != null) {
            this.d.clear();
            for (int i5 = 0; i5 < list.size(); i5++) {
                a aVar = new a();
                aVar.a = ((ScanResult) list.get(i5)).BSSID;
                int i6 = ((ScanResult) list.get(i5)).level;
                this.d.add(aVar);
            }
        }
    }

    public final void a(String str) {
        this.e = str;
    }

    public final String b(int i, int i2, int i3, int i4, List<ScanResult> list) {
        if (this.e == null || this.e.length() < 10) {
            return null;
        }
        String str = this.e;
        if (str == null || list == null) {
            str = null;
        } else {
            long abs = Math.abs(System.currentTimeMillis() - this.b);
            if ((abs > 30000 && list.size() > 2) || ((abs > 45000 && list.size() <= 2) || !a(new StringBuffer(str)))) {
                str = null;
            }
        }
        this.e = str;
        if (this.e == null) {
            return null;
        }
        if (this.c != null && this.c.size() > 0) {
            b bVar = (b) this.c.get(0);
            if (bVar.a != i || bVar.b != i2 || bVar.c != i3 || bVar.d != i4) {
                return null;
            }
            if ((this.d == null || this.d.size() == 0) && (list == null || list.size() == 0)) {
                return this.e;
            }
            if (a((List) list)) {
                return this.e;
            }
        }
        return a((List) list) ? this.e : null;
    }

    public final void b() {
        this.e = null;
    }
}
