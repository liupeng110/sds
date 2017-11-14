package com.taobao.dp.service;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.taobao.dp.bean.DeviceInfo;
import com.taobao.dp.client.b;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;

public final class a {
    private Context a;
    private TelephonyManager b;

    public a(Context context) {
        this.a = context;
        this.b = (TelephonyManager) context.getSystemService("phone");
    }

    private String a() {
        try {
            int i = VERSION.SDK_INT >= 11 ? 4 : 0;
            String macAddress = ((WifiManager) this.a.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress == null || "".equals(macAddress)) {
                macAddress = this.a.getSharedPreferences("mac_address", i).getString("mac_address", null);
            } else {
                Editor edit = this.a.getSharedPreferences("mac_address", i).edit();
                edit.putString("mac_address", macAddress);
                edit.commit();
            }
            return macAddress != null ? macAddress : "";
        } catch (Exception e) {
            return "";
        }
    }

    private String b() {
        try {
            WifiInfo connectionInfo = ((WifiManager) this.a.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo == null) {
                return "";
            }
            String bssid = connectionInfo.getBSSID();
            return bssid != null ? bssid.trim() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private String c() {
        try {
            WifiInfo connectionInfo = ((WifiManager) this.a.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo == null) {
                return "";
            }
            String ssid = connectionInfo.getSSID();
            return ssid != null ? ssid.trim() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private String d() {
        try {
            String deviceId = this.b.getDeviceId();
            return deviceId != null ? deviceId.trim() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private String e() {
        try {
            String subscriberId = this.b.getSubscriberId();
            return subscriberId != null ? subscriberId.trim() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private static String f() {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", null});
            return str != null ? str.trim() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private String g() {
        try {
            String string = Secure.getString(this.a.getContentResolver(), "android_id");
            return string != null ? string.trim() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private static String h() {
        try {
            return new StringBuilder(b.OS).append(VERSION.RELEASE).append(SelectCountryActivity.SPLITTER).append(VERSION.SDK_INT).toString();
        } catch (Exception e) {
            return "";
        }
    }

    private static String i() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return "";
        }
    }

    private String j() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics = this.a.getApplicationContext().getResources().getDisplayMetrics();
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            return String.valueOf(Math.min(i, i2)) + "*" + String.valueOf(Math.max(i, i2));
        } catch (Exception e) {
            return "";
        }
    }

    private String k() {
        String str = "";
        try {
            String subscriberId = this.b.getSubscriberId();
            return subscriberId == null ? "" : (subscriberId.startsWith("46000") || subscriberId.startsWith("46002")) ? "中国移动" : subscriberId.startsWith("46001") ? "中国联通" : subscriberId.startsWith("46003") ? "中国电信" : str;
        } catch (Exception e) {
            return "";
        }
    }

    private static String l() {
        String str = null;
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null) {
                str = defaultAdapter.getAddress();
            }
            return str != null ? str.trim() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private static String m() {
        String str = null;
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null) {
                str = defaultAdapter.getName();
            }
            return str != null ? str.trim() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private String n() {
        try {
            String simSerialNumber = this.b.getSimSerialNumber();
            return simSerialNumber != null ? simSerialNumber.trim() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private static String o() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
        } catch (Exception e) {
            return "100000";
        }
    }

    private static String p() {
        BufferedReader bufferedReader;
        String str;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        long j = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8);
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    str = readLine;
                }
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                }
            } catch (FileNotFoundException e2) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                    }
                }
                if (str != null) {
                    j = (long) Integer.parseInt(str.substring(str.indexOf(58) + 1, str.indexOf(107)).trim());
                }
                return String.valueOf(j);
            } catch (IOException e4) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e5) {
                    }
                }
                if (str != null) {
                    j = (long) Integer.parseInt(str.substring(str.indexOf(58) + 1, str.indexOf(107)).trim());
                }
                return String.valueOf(j);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e6) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e7) {
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (str != null) {
                j = (long) Integer.parseInt(str.substring(str.indexOf(58) + 1, str.indexOf(107)).trim());
            }
            return String.valueOf(j);
        } catch (IOException e8) {
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (str != null) {
                j = (long) Integer.parseInt(str.substring(str.indexOf(58) + 1, str.indexOf(107)).trim());
            }
            return String.valueOf(j);
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
        if (str != null) {
            j = (long) Integer.parseInt(str.substring(str.indexOf(58) + 1, str.indexOf(107)).trim());
        }
        return String.valueOf(j);
    }

    private String q() {
        if (!r()) {
            return "";
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
        } catch (Exception e) {
            return "";
        }
    }

    private synchronized boolean r() {
        boolean z;
        z = false;
        try {
            File file = new File("/sdcard/" + System.currentTimeMillis() + ".test");
            z = file.createNewFile();
            file.delete();
        } catch (Exception e) {
        }
        return z;
    }

    private static String s() {
        try {
            int a = com.taobao.dp.util.a.a();
            if (a != 0) {
                return String.valueOf(a);
            }
        } catch (Exception e) {
        }
        return "";
    }

    private static String t() {
        String str = null;
        try {
            str = com.taobao.dp.util.a.b();
        } catch (Exception e) {
        }
        return str != null ? str : "";
    }

    private com.taobao.dp.bean.a u() {
        try {
            Location lastKnownLocation = ((LocationManager) this.a.getSystemService("location")).getLastKnownLocation("gps");
            double longitude = lastKnownLocation.getLongitude();
            double latitude = lastKnownLocation.getLatitude();
            com.taobao.dp.bean.a aVar = new com.taobao.dp.bean.a();
            aVar.a(longitude);
            aVar.b(latitude);
            return aVar;
        } catch (Exception e) {
            return null;
        }
    }

    private String v() {
        try {
            CellLocation cellLocation = this.b.getCellLocation();
            if (cellLocation != null) {
                int i;
                int i2 = 0;
                int cid;
                if (cellLocation instanceof GsmCellLocation) {
                    cid = ((GsmCellLocation) cellLocation).getCid();
                    i2 = ((GsmCellLocation) cellLocation).getLac();
                    i = cid;
                } else if (cellLocation instanceof CdmaCellLocation) {
                    cid = ((CdmaCellLocation) cellLocation).getBaseStationId();
                    i2 = ((CdmaCellLocation) cellLocation).getNetworkId();
                    i = cid;
                } else {
                    i = -1;
                }
                if (!(i == -1 || i2 == -1)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(i);
                    stringBuffer.append(i2);
                    return stringBuffer.toString();
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    private String w() {
        try {
            if (this.b != null) {
                switch (this.b.getNetworkType()) {
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
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    public final void a(DeviceInfo deviceInfo) {
        try {
            String d = d();
            String e = e();
            String a = a();
            String g = g();
            String h = h();
            String j = j();
            String l = l();
            String n = n();
            String f = f();
            String t = t();
            String q = q();
            String o = o();
            String p = p();
            String s = s();
            String str = "";
            String i = i();
            String b = b();
            String k = k();
            String c = c();
            String v = v();
            com.taobao.dp.bean.a u = u();
            String w = w();
            String m = m();
            int i2 = 0;
            if (VERSION.SDK_INT >= 11) {
                i2 = 4;
            }
            String string = this.a.getSharedPreferences("DataCollectionData", i2).getString("key_nick", "");
            deviceInfo.setAid(g);
            deviceInfo.setImei(d);
            deviceInfo.setImsi(e);
            deviceInfo.setWifi(a);
            deviceInfo.setOs(h);
            deviceInfo.setResolution(j);
            deviceInfo.setBluetooth(l);
            deviceInfo.setSim(n);
            deviceInfo.setSerial(f);
            deviceInfo.setSd(q);
            deviceInfo.setCpu(t);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("camera2", str);
            deviceInfo.setCamera(jSONObject.toString());
            deviceInfo.setFreq(s);
            deviceInfo.setRam(p);
            deviceInfo.setStorage(o);
            deviceInfo.setModel(i);
            deviceInfo.setBssid(b);
            deviceInfo.setSsid(c);
            deviceInfo.setProvidername(k);
            deviceInfo.setCellId(v);
            if (u != null) {
                deviceInfo.setLongitude(Double.toString(u.a()));
                deviceInfo.setLatitude(Double.toString(u.b()));
            }
            deviceInfo.setNetworktype(w);
            deviceInfo.setDevicename(m);
            deviceInfo.setNick(string);
        } catch (Exception e2) {
        }
    }
}
