package com.sds.android.ttpod.component.apshare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Build;
import android.text.TextUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* WifiAPManager */
public final class j {
    public static final String a = ("TTPODShare-" + Build.MODEL);
    public static final int b = j;
    public static final int c = k;
    public static final int d = l;
    public static final int e = m;
    public static final int f = n;
    public static final String g = o;
    public static final String h = p;
    public static final String i = q;
    private static int j;
    private static int k;
    private static int l;
    private static int m;
    private static int n;
    private static String o;
    private static String p;
    private static String q;
    private static j w = null;
    private WifiManager r;
    private ConnectivityManager s;
    private a t;
    private WifiLock u;
    private Context v;
    private BroadcastReceiver x = new BroadcastReceiver(this) {
        final /* synthetic */ j a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.wifi.SCAN_RESULTS".equals(action)) {
                List a = this.a.b(this.a.r.getScanResults());
                if (this.a.t != null) {
                    this.a.t.a(a);
                }
            } else if ("android.net.wifi.RSSI_CHANGED".equals(action)) {
                if (this.a.t != null) {
                    this.a.t.a(intent.getIntExtra("newRssi", 0));
                }
            } else if ("android.net.wifi.STATE_CHANGE".equals(action)) {
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                WifiInfo wifiInfo = (WifiInfo) intent.getParcelableExtra("wifiInfo");
                if (this.a.t != null) {
                    if (networkInfo.isAvailable() && networkInfo.isConnected() && wifiInfo != null) {
                        this.a.t.a(networkInfo, wifiInfo);
                    } else {
                        this.a.t.a(networkInfo);
                    }
                }
            } else if ("android.net.wifi.supplicant.CONNECTION_CHANGE".equals(action)) {
                if (this.a.t != null) {
                    this.a.t.a(intent.getBooleanExtra("connected", false));
                }
            } else if ("android.net.wifi.supplicant.STATE_CHANGE".equals(action)) {
                if (this.a.t != null) {
                    this.a.t.a((SupplicantState) intent.getParcelableExtra("newState"), intent.getIntExtra("supplicantError", 1));
                }
            } else if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action) && this.a.t != null) {
                this.a.t.a(intent.getIntExtra("wifi_state", 4), intent.getIntExtra("previous_wifi_state", 4));
            }
            if (!m.a(j.g) && j.g.equals(action) && this.a.t != null) {
                this.a.t.a(this.a, intent.getIntExtra(j.h, j.f));
            }
        }
    };

    /* WifiAPManager */
    public interface a {
        void a(int i);

        void a(int i, int i2);

        void a(NetworkInfo networkInfo);

        void a(NetworkInfo networkInfo, WifiInfo wifiInfo);

        void a(SupplicantState supplicantState, int i);

        void a(j jVar, int i);

        void a(String str);

        void a(List<ScanResult> list);

        void a(boolean z);

        void a(boolean z, String str);
    }

    static {
        try {
            j = WifiManager.class.getField("WIFI_AP_STATE_DISABLED").getInt(WifiManager.class);
            k = WifiManager.class.getField("WIFI_AP_STATE_DISABLING").getInt(WifiManager.class);
            l = WifiManager.class.getField("WIFI_AP_STATE_ENABLED").getInt(WifiManager.class);
            m = WifiManager.class.getField("WIFI_AP_STATE_ENABLING").getInt(WifiManager.class);
            n = WifiManager.class.getField("WIFI_AP_STATE_FAILED").getInt(WifiManager.class);
            o = (String) WifiManager.class.getField("WIFI_AP_STATE_CHANGED_ACTION").get(WifiManager.class);
            p = (String) WifiManager.class.getField("EXTRA_WIFI_AP_STATE").get(WifiManager.class);
            q = (String) WifiManager.class.getField("EXTRA_PREVIOUS_WIFI_AP_STATE").get(WifiManager.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized j a(Context context) {
        j jVar;
        synchronized (j.class) {
            if (w == null) {
                if (context == null) {
                    throw new IllegalArgumentException("Create WifiAPManager instances. context can NOT be null");
                }
                w = new j(context, null);
            }
            jVar = w;
        }
        return jVar;
    }

    private j(Context context, a aVar) {
        this.r = (WifiManager) context.getSystemService("wifi");
        this.s = (ConnectivityManager) context.getSystemService("connectivity");
        this.t = aVar;
        this.u = this.r.createWifiLock(3, "TTPODShare-");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
        intentFilter.addAction("android.net.wifi.NETWORK_IDS_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.supplicant.CONNECTION_CHANGE");
        intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.PICK_WIFI_NETWORK");
        if (!TextUtils.isEmpty(g)) {
            intentFilter.addAction(g);
        }
        this.v = context;
        context.registerReceiver(this.x, intentFilter);
    }

    public void a(a aVar) {
        this.t = aVar;
    }

    public boolean a() {
        try {
            return ((Boolean) this.s.getClass().getMethod("getMobileDataEnabled", new Class[0]).invoke(this.s, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void a(boolean z) {
        try {
            if (z != a()) {
                this.s.getClass().getMethod("setMobileDataEnabled", new Class[]{Boolean.TYPE}).invoke(this.s, new Object[]{Boolean.valueOf(z)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a(WifiConfiguration wifiConfiguration, boolean z) {
        if (z) {
            try {
                this.r.setWifiEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        g.d("WifiAPManager", "set wifi ap configuration: " + b(wifiConfiguration));
        return ((Boolean) this.r.getClass().getMethod("setWifiApEnabled", new Class[]{WifiConfiguration.class, Boolean.TYPE}).invoke(this.r, new Object[]{wifiConfiguration, Boolean.valueOf(z)})).booleanValue();
    }

    public WifiConfiguration b() {
        try {
            return (WifiConfiguration) this.r.getClass().getMethod("getWifiApConfiguration", new Class[0]).invoke(this.r, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean b(WifiConfiguration wifiConfiguration) {
        try {
            return ((Boolean) this.r.getClass().getMethod("setWifiApConfiguration", new Class[]{WifiConfiguration.class}).invoke(this.r, new Object[]{wifiConfiguration})).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean c() {
        try {
            return ((Boolean) this.r.getClass().getMethod("isWifiApEnabled", new Class[0]).invoke(this.r, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean d() {
        return this.r.getWifiState() == 3;
    }

    public boolean e() {
        return m();
    }

    public void f() {
        l();
        if (this.u.isHeld()) {
            this.u.release();
        }
    }

    public void g() {
        if (this.r.isWifiEnabled()) {
            this.r.setWifiEnabled(false);
        }
    }

    public void h() {
        if (!this.r.isWifiEnabled()) {
            this.r.setWifiEnabled(true);
        }
    }

    private void l() {
        if (c()) {
            a(b(), false);
        }
    }

    private boolean m() {
        g();
        l();
        this.u.acquire();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = a;
        wifiConfiguration.preSharedKey = "ttpod_apshare";
        c(wifiConfiguration);
        return a(wifiConfiguration, true);
    }

    private void c(WifiConfiguration wifiConfiguration) {
        wifiConfiguration.hiddenSSID = false;
        wifiConfiguration.status = 2;
        wifiConfiguration.allowedAuthAlgorithms.set(0);
        wifiConfiguration.allowedGroupCiphers.set(2);
        wifiConfiguration.allowedGroupCiphers.set(3);
        wifiConfiguration.allowedPairwiseCiphers.set(1);
        wifiConfiguration.allowedPairwiseCiphers.set(2);
        wifiConfiguration.allowedKeyManagement.set(1);
        wifiConfiguration.allowedProtocols.set(0);
        wifiConfiguration.allowedProtocols.set(1);
        d(wifiConfiguration);
    }

    private List<ScanResult> b(List<ScanResult> list) {
        return a((List) list);
    }

    public List<ScanResult> a(List<ScanResult> list) {
        if (list == null) {
            return null;
        }
        List<ScanResult> arrayList = new ArrayList();
        for (ScanResult scanResult : list) {
            if (scanResult.SSID.contains("TTPODShare-")) {
                arrayList.add(scanResult);
            }
        }
        return arrayList;
    }

    public boolean a(ScanResult scanResult) {
        if (scanResult == null) {
            return false;
        }
        if (this.t != null) {
            this.t.a(scanResult.SSID);
        }
        b("TTPODShare-");
        if (!scanResult.SSID.equals(this.r.getConnectionInfo().getSSID())) {
            this.r.disconnect();
        }
        List<WifiConfiguration> configuredNetworks = this.r.getConfiguredNetworks();
        if (configuredNetworks != null) {
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                if (!wifiConfiguration.SSID.equals(scanResult.SSID)) {
                    wifiConfiguration.priority = 0;
                    this.r.updateNetwork(wifiConfiguration);
                }
            }
        }
        WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
        wifiConfiguration2.SSID = "\"" + scanResult.SSID + "\"";
        wifiConfiguration2.preSharedKey = "\"ttpod_apshare\"";
        wifiConfiguration2.BSSID = scanResult.BSSID;
        c(wifiConfiguration2);
        try {
            Field field = wifiConfiguration2.getClass().getField("ipAssignment");
            field.set(wifiConfiguration2, Enum.valueOf(field.getType(), "DHCP"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean enableNetwork = this.r.enableNetwork(this.r.addNetwork(wifiConfiguration2), true);
        this.r.saveConfiguration();
        this.r.reconnect();
        if (this.t == null) {
            return enableNetwork;
        }
        this.t.a(enableNetwork, scanResult.SSID);
        return enableNetwork;
    }

    public void a(String str) {
        List<WifiConfiguration> configuredNetworks = this.r.getConfiguredNetworks();
        if (!m.a(str) && configuredNetworks != null) {
            String str2 = "\"" + str + "\"";
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                if (wifiConfiguration != null && wifiConfiguration.SSID.equals(str2)) {
                    this.r.enableNetwork(wifiConfiguration.networkId, false);
                    this.r.saveConfiguration();
                    this.r.reconnect();
                    this.r.reassociate();
                    return;
                }
            }
        }
    }

    public List<ScanResult> i() {
        l();
        h();
        try {
            this.u.acquire();
            this.r.startScan();
            List<ScanResult> a = a(this.r.getScanResults());
            return a;
        } finally {
            this.u.release();
        }
    }

    private void d(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration != null) {
            try {
                Field declaredField = WifiConfiguration.class.getDeclaredField("mWifiApProfile");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(wifiConfiguration);
                declaredField.setAccessible(false);
                if (obj != null) {
                    declaredField = obj.getClass().getDeclaredField("SSID");
                    declaredField.setAccessible(true);
                    declaredField.set(obj, wifiConfiguration.SSID);
                    declaredField.setAccessible(false);
                    declaredField = obj.getClass().getDeclaredField("BSSID");
                    declaredField.setAccessible(true);
                    declaredField.set(obj, wifiConfiguration.BSSID);
                    declaredField.setAccessible(false);
                    declaredField = obj.getClass().getDeclaredField("secureType");
                    declaredField.setAccessible(true);
                    if (TextUtils.isEmpty(wifiConfiguration.preSharedKey)) {
                        declaredField.set(obj, "open");
                    } else {
                        declaredField.set(obj, "wpa2-psk");
                    }
                    declaredField.setAccessible(false);
                    declaredField = obj.getClass().getDeclaredField("dhcpEnable");
                    declaredField.setAccessible(true);
                    declaredField.set(obj, Integer.valueOf(1));
                    declaredField.setAccessible(false);
                    declaredField = obj.getClass().getDeclaredField("key");
                    declaredField.setAccessible(true);
                    declaredField.set(obj, wifiConfiguration.preSharedKey);
                    declaredField.setAccessible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            return;
        }
        if (TextUtils.isEmpty(wifiConfiguration.SSID) || TextUtils.isEmpty(wifiConfiguration.BSSID)) {
            try {
                Field declaredField = WifiConfiguration.class.getDeclaredField("mWifiApProfile");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(wifiConfiguration);
                declaredField.setAccessible(false);
                if (obj != null) {
                    Field declaredField2 = obj.getClass().getDeclaredField("SSID");
                    declaredField2.setAccessible(true);
                    Object obj2 = declaredField2.get(obj);
                    if (obj2 != null) {
                        wifiConfiguration.SSID = (String) obj2;
                    }
                    declaredField2.setAccessible(false);
                    declaredField2 = obj.getClass().getDeclaredField("BSSID");
                    declaredField2.setAccessible(true);
                    obj2 = declaredField2.get(obj);
                    if (obj2 != null) {
                        wifiConfiguration.BSSID = (String) obj2;
                    }
                    declaredField2.setAccessible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public WifiManager j() {
        return this.r;
    }

    public WifiInfo k() {
        return this.r.getConnectionInfo();
    }

    public void b(String str) {
        List<WifiConfiguration> configuredNetworks = this.r.getConfiguredNetworks();
        if (!(configuredNetworks == null || str == null)) {
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                if (!(wifiConfiguration == null || wifiConfiguration.SSID == null || !wifiConfiguration.SSID.contains(str))) {
                    g.d("WifiAPManager", "disable: " + wifiConfiguration.SSID);
                    this.r.disableNetwork(wifiConfiguration.networkId);
                    this.r.removeNetwork(wifiConfiguration.networkId);
                }
            }
        }
        this.r.saveConfiguration();
    }
}
