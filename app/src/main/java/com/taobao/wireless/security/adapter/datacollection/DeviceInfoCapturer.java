package com.taobao.wireless.security.adapter.datacollection;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.view.MotionEventCompat;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.taobao.dp.DeviceSecuritySDK;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class DeviceInfoCapturer {
    private static Context a;
    private static b b;
    private static PackageManager c = null;
    private static Object d = new Object();
    private static SensorManager e = null;
    private static Object f = new Object();
    private static TelephonyManager g = null;
    private static Object h = new Object();
    private static ConnectivityManager i = null;
    private static Object j = new Object();
    private static LocationManager k = null;
    private static Object l = new Object();
    private static WifiManager m = null;
    private static Object n = new Object();

    private DeviceInfoCapturer() {
    }

    private static PackageManager a() {
        Context context = a;
        if (context != null && c == null) {
            synchronized (d) {
                if (c == null) {
                    c = context.getPackageManager();
                }
            }
        }
        return c;
    }

    private static boolean a(int i) {
        SensorManager b = b();
        return (b == null || b.getDefaultSensor(i) == null) ? false : true;
    }

    private static boolean a(String str) {
        PackageManager a = a();
        return a != null ? a.hasSystemFeature(str) : false;
    }

    private static SensorManager b() {
        Context context = a;
        if (context != null && e == null) {
            synchronized (f) {
                if (e == null) {
                    e = (SensorManager) context.getSystemService("sensor");
                }
            }
        }
        return e;
    }

    private static String b(String str) {
        String str2 = null;
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() != 0) {
            str2 = trim;
        }
        return (str2.charAt(0) == '\"' || str2.charAt(0) == '[' || str2.charAt(0) == '{') ? str2 : "\"" + str2 + "\"";
    }

    private static TelephonyManager c() {
        Context context = a;
        if (context != null && g == null) {
            synchronized (h) {
                if (g == null) {
                    g = (TelephonyManager) context.getSystemService("phone");
                }
            }
        }
        return g;
    }

    private static ConnectivityManager d() {
        Context context = a;
        if (context != null && i == null) {
            synchronized (j) {
                if (i == null) {
                    i = (ConnectivityManager) context.getSystemService("connectivity");
                }
            }
        }
        return i;
    }

    private static LocationManager e() {
        Context context = a;
        if (context != null && k == null) {
            synchronized (l) {
                if (k == null) {
                    k = (LocationManager) context.getSystemService("location");
                }
            }
        }
        return k;
    }

    private static WifiManager f() {
        Context context = a;
        if (context != null && m == null) {
            synchronized (n) {
                if (m == null) {
                    m = (WifiManager) context.getSystemService("wifi");
                }
            }
        }
        return m;
    }

    public static String getAccelerometerName() {
        try {
            SensorManager b = b();
            if (b != null) {
                Sensor defaultSensor = b.getDefaultSensor(1);
                if (!(defaultSensor == null || defaultSensor.getName() == null)) {
                    return defaultSensor.getName();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String getAccelerometerVendor() {
        try {
            SensorManager b = b();
            if (b != null) {
                Sensor defaultSensor = b.getDefaultSensor(1);
                if (!(defaultSensor == null || defaultSensor.getVendor() == null)) {
                    return defaultSensor.getVendor();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String getAccessSubtype() {
        try {
            if (c() != null) {
                switch (c().getNetworkType()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return "2g";
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return "3g";
                    case 13:
                        return "4g";
                    default:
                        return EnvironmentCompat.MEDIA_UNKNOWN;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static int getBatteryLevel() {
        int i = -1;
        try {
            Context context = a;
            if (context != null) {
                Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                if (registerReceiver != null) {
                    i = registerReceiver.getIntExtra("level", -1);
                }
            }
        } catch (Exception e) {
        }
        return i;
    }

    public static int getBatteryTemp() {
        int i = -1;
        try {
            Context context = a;
            if (context != null) {
                Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                if (registerReceiver != null) {
                    i = registerReceiver.getIntExtra("temperature", -1);
                }
            }
        } catch (Exception e) {
        }
        return i;
    }

    public static int getBatteryVoltage() {
        int i = -1;
        try {
            Context context = a;
            if (context != null) {
                Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                if (registerReceiver != null) {
                    i = registerReceiver.getIntExtra("voltage", -1);
                }
            }
        } catch (Exception e) {
        }
        return i;
    }

    public static String getCarrier() {
        try {
            TelephonyManager c = c();
            if (c != null) {
                String networkOperatorName = c.getNetworkOperatorName();
                if (!(networkOperatorName == null || networkOperatorName.length() == 0)) {
                    return networkOperatorName;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    @TargetApi(17)
    public static String getCellInfo() {
        try {
            TelephonyManager c = c();
            if (c == null) {
                return null;
            }
            int i;
            String b;
            try {
                if (VERSION.SDK_INT >= 17) {
                    List allCellInfo = c.getAllCellInfo();
                    if (!(allCellInfo == null || allCellInfo.size() == 0)) {
                        for (i = 0; i < allCellInfo.size(); i++) {
                            CellInfo cellInfo = (CellInfo) allCellInfo.get(i);
                            if (cellInfo.isRegistered()) {
                                b = b(DeviceInfoHelper.a(cellInfo));
                                break;
                            }
                        }
                    }
                }
                b = null;
                if (b != null) {
                    return b;
                }
            } catch (Throwable th) {
            }
            try {
                CellLocation cellLocation = c.getCellLocation();
                if (cellLocation == null) {
                    return null;
                }
                if (cellLocation instanceof GsmCellLocation) {
                    i = ((GsmCellLocation) cellLocation).getCid();
                    b = String.format("{\"cellType\":\"GSM\", \"param\":{\"lac\":%d, \"cid\":%d}}", new Object[]{Integer.valueOf(r0.getLac()), Integer.valueOf(i)});
                } else if (cellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                    i = cdmaCellLocation.getBaseStationLatitude();
                    int baseStationLongitude = cdmaCellLocation.getBaseStationLongitude();
                    int networkId = cdmaCellLocation.getNetworkId();
                    int systemId = cdmaCellLocation.getSystemId();
                    int baseStationId = cdmaCellLocation.getBaseStationId();
                    b = String.format("{\"cellType\":\"CDMA\", \"param\":{\"lat\":%d, \"lon\":%d, \"bid\":%d, \"nid\":%d, \"sid\":%d}}", new Object[]{Integer.valueOf(i), Integer.valueOf(baseStationLongitude), Integer.valueOf(baseStationId), Integer.valueOf(networkId), Integer.valueOf(systemId)});
                } else {
                    b = null;
                }
                return b(b);
            } catch (Throwable th2) {
                return null;
            }
        } catch (Throwable th3) {
            return null;
        }
    }

    @SuppressLint({"DefaultLocale"})
    public static String getGPS() {
        LocationManager e = e();
        if (e == null) {
            return null;
        }
        try {
            Boolean valueOf = Boolean.valueOf(e.isProviderEnabled("gps"));
            Boolean valueOf2 = Boolean.valueOf(e.isProviderEnabled("network"));
            if (!valueOf.booleanValue() && !valueOf2.booleanValue()) {
                return null;
            }
            Location lastKnownLocation;
            try {
                lastKnownLocation = valueOf2.booleanValue() ? e.getLastKnownLocation("network") : null;
                if (lastKnownLocation == null) {
                    try {
                        if (valueOf.booleanValue()) {
                            lastKnownLocation = e.getLastKnownLocation("gps");
                        }
                    } catch (Exception e2) {
                    }
                }
            } catch (Exception e3) {
                lastKnownLocation = null;
            }
            if (lastKnownLocation == null) {
                return null;
            }
            double latitude = lastKnownLocation.getLatitude();
            double longitude = lastKnownLocation.getLongitude();
            double altitude = lastKnownLocation.getAltitude();
            return String.format("{\"latitude\":%.8f, \"longtitude\":%.8f, \"altitude\":%.0f}", new Object[]{Double.valueOf(latitude), Double.valueOf(longitude), Double.valueOf(altitude)});
        } catch (Exception e4) {
            return null;
        }
    }

    public static String getGravityName() {
        try {
            SensorManager b = b();
            if (b != null) {
                Sensor defaultSensor = b.getDefaultSensor(9);
                if (!(defaultSensor == null || defaultSensor.getName() == null)) {
                    return defaultSensor.getName();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String getGravityVendor() {
        try {
            SensorManager b = b();
            if (b != null) {
                Sensor defaultSensor = b.getDefaultSensor(9);
                if (!(defaultSensor == null || defaultSensor.getVendor() == null)) {
                    return defaultSensor.getVendor();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String getImei() {
        try {
            TelephonyManager c = c();
            if (c != null) {
                String deviceId = c.getDeviceId();
                if (!(deviceId == null || deviceId.length() == 0)) {
                    return deviceId;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String getImsi() {
        try {
            TelephonyManager c = c();
            if (c != null) {
                String subscriberId = c.getSubscriberId();
                if (!(subscriberId == null || subscriberId.length() == 0)) {
                    return subscriberId;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String getIpAddr() {
        try {
            ConnectivityManager d = d();
            if (d == null) {
                return null;
            }
            NetworkInfo activeNetworkInfo = d.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (!activeNetworkInfo.isAvailable()) {
                return null;
            }
            activeNetworkInfo = d.getNetworkInfo(1);
            NetworkInfo networkInfo = d.getNetworkInfo(0);
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                WifiManager f = f();
                if (f == null) {
                    return null;
                }
                if (!f.isWifiEnabled()) {
                    return null;
                }
                WifiInfo connectionInfo = f.getConnectionInfo();
                if (connectionInfo != null) {
                    int ipAddress = connectionInfo.getIpAddress();
                    return (ipAddress & MotionEventCompat.ACTION_MASK) + "." + ((ipAddress >> 8) & MotionEventCompat.ACTION_MASK) + "." + ((ipAddress >> 16) & MotionEventCompat.ACTION_MASK) + "." + ((ipAddress >> 24) & MotionEventCompat.ACTION_MASK);
                }
            }
            if (networkInfo != null && networkInfo.isAvailable()) {
                Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    if (networkInterfaces != null) {
                        NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                        if (networkInterface != null) {
                            Enumeration inetAddresses = networkInterface.getInetAddresses();
                            while (inetAddresses.hasMoreElements()) {
                                if (inetAddresses != null) {
                                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                                    if (!(inetAddress == null || inetAddress.isLoopbackAddress())) {
                                        return inetAddress.getHostAddress().toString();
                                    }
                                }
                            }
                            continue;
                        } else {
                            continue;
                        }
                    }
                }
            }
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static String getMacAddress() {
        try {
            WifiManager f = f();
            if (f != null) {
                WifiInfo connectionInfo = f.getConnectionInfo();
                if (connectionInfo != null) {
                    return connectionInfo.getMacAddress();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String getNick() {
        b bVar = b;
        return bVar != null ? bVar.b() : null;
    }

    public static String getResolution() {
        try {
            Context context = a;
            if (context != null) {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                return displayMetrics.heightPixels + "*" + displayMetrics.widthPixels;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    public static String getRomTotal() {
        String str = null;
        try {
            File dataDirectory = Environment.getDataDirectory();
            if (dataDirectory == null || dataDirectory.getAbsolutePath().length() == 0) {
                return str;
            }
            StatFs statFs = new StatFs(dataDirectory.getPath());
            if (VERSION.SDK_INT >= 18) {
                Long l = (Long) statFs.getClass().getDeclaredMethod("getTotalBytes", new Class[0]).invoke(statFs, new Object[0]);
                return l != null ? l.toString() : str;
            }
            return (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
        } catch (NoSuchMethodException e) {
            return str;
        } catch (IllegalAccessException e2) {
            return str;
        } catch (IllegalArgumentException e3) {
            return str;
        } catch (InvocationTargetException e4) {
            return str;
        } catch (Throwable th) {
            return str;
        }
    }

    @SuppressLint({"NewApi"})
    public static String getSDCardTotal() {
        String str = null;
        try {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                return str;
            }
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory == null || externalStorageDirectory.getAbsolutePath().length() == 0) {
                return str;
            }
            StatFs statFs = new StatFs(externalStorageDirectory.getPath());
            if (VERSION.SDK_INT >= 18) {
                Long l = (Long) statFs.getClass().getDeclaredMethod("getTotalBytes", new Class[0]).invoke(statFs, new Object[0]);
                return l != null ? l.toString() : str;
            }
            return (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
        } catch (NoSuchMethodException e) {
            return str;
        } catch (IllegalAccessException e2) {
            return str;
        } catch (IllegalArgumentException e3) {
            return str;
        } catch (InvocationTargetException e4) {
            return str;
        } catch (Exception e5) {
            return str;
        } catch (Throwable th) {
            return str;
        }
    }

    public static String getUmid() {
        String str = null;
        b bVar = b;
        if (bVar != null) {
            str = bVar.a();
        }
        if (str != null && str.length() != 0) {
            return str;
        }
        Context context = a;
        str = "";
        if (context == null) {
            return str;
        }
        DeviceSecuritySDK instance = DeviceSecuritySDK.getInstance(context);
        return instance != null ? instance.getSecurityToken() : str;
    }

    public static String getWifiBSSID() {
        try {
            WifiManager f = f();
            if (f != null) {
                WifiInfo connectionInfo = f.getConnectionInfo();
                if (connectionInfo != null) {
                    return connectionInfo.getBSSID();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String getWifiSSID() {
        try {
            WifiManager f = f();
            if (f != null) {
                WifiInfo connectionInfo = f.getConnectionInfo();
                if (connectionInfo != null) {
                    return connectionInfo.getSSID();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void initialize(Context context, b bVar) {
        a = context;
        b = bVar;
    }

    public static boolean isAccelerometerSupported() {
        try {
            return a(1);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isBlueToothLeSupported() {
        try {
            return a("android.hardware.bluetooth_le");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isBlueToothSupported() {
        try {
            return a("android.hardware.bluetooth");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isGpsLocationSupported() {
        try {
            return a("android.hardware.location.gps");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isGravitySupported() {
        try {
            return a(9);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isGyroscopeSupported() {
        try {
            return a(4);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNfcSupported() {
        try {
            return a("android.hardware.nfc");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isProxyEnabled() {
        try {
            String property;
            if (VERSION.SDK_INT > 14) {
                property = System.getProperty("http.proxyHost");
                return property != null && property.length() > 0;
            } else {
                property = Proxy.getHost(a);
                return property != null && property.length() > 0;
            }
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean isTelephonySupported() {
        try {
            return a("android.hardware.telephony");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isUsbAccessorySupported() {
        try {
            return a("android.hardware.usb.accessory");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isWifiSupported() {
        try {
            return a("android.hardware.wifi");
        } catch (Exception e) {
            return false;
        }
    }
}
