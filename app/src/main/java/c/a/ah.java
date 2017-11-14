package c.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.ta.utdid2.android.utils.NetworkUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Locale;

/* DeviceConfig */
public class ah {
    protected static final String a = ah.class.getName();

    public static String a(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public static boolean a(Context context, String str) {
        if (context.getPackageManager().checkPermission(str, context.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    public static String a() {
        String str = null;
        try {
            Reader fileReader = new FileReader("/proc/cpuinfo");
            if (fileReader != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                    str = bufferedReader.readLine();
                    bufferedReader.close();
                    fileReader.close();
                } catch (Exception e) {
                    ai.b(a, "Could not read from file /proc/cpuinfo", e);
                }
            }
        } catch (Exception e2) {
            ai.b(a, "Could not open file /proc/cpuinfo", e2);
        }
        if (str != null) {
            return str.substring(str.indexOf(58) + 1).trim();
        }
        return "";
    }

    public static String c(Context context) {
        String deviceId;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            ai.d(a, "No IMEI.");
        }
        String str = "";
        try {
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                deviceId = telephonyManager.getDeviceId();
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                ai.d(a, "No IMEI.");
                deviceId = k(context);
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                ai.d(a, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
                deviceId = Secure.getString(context.getContentResolver(), "android_id");
                ai.a(a, "getDeviceId: Secure.ANDROID_ID: " + deviceId);
                return deviceId;
            }
        } catch (Exception e) {
            ai.c(a, "No IMEI.", e);
        }
        deviceId = str;
        if (TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        ai.d(a, "No IMEI.");
        deviceId = k(context);
        if (TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        ai.d(a, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
        deviceId = Secure.getString(context.getContentResolver(), "android_id");
        ai.a(a, "getDeviceId: Secure.ANDROID_ID: " + deviceId);
        return deviceId;
    }

    public static String d(Context context) {
        return ak.b(c(context));
    }

    public static String[] e(Context context) {
        String[] strArr = new String[]{"", ""};
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
                strArr[0] = "";
                return strArr;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                strArr[0] = "";
                return strArr;
            } else if (connectivityManager.getNetworkInfo(1).getState() == State.CONNECTED) {
                strArr[0] = NetworkUtils.WIFI;
                return strArr;
            } else {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
                if (networkInfo.getState() == State.CONNECTED) {
                    strArr[0] = "2G/3G";
                    strArr[1] = networkInfo.getSubtypeName();
                    return strArr;
                }
                return strArr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean f(Context context) {
        return NetworkUtils.WIFI.equals(e(context)[0]);
    }

    public static boolean g(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static int h(Context context) {
        try {
            Calendar instance = Calendar.getInstance(r(context));
            if (instance != null) {
                return instance.getTimeZone().getRawOffset() / 3600000;
            }
        } catch (Exception e) {
            ai.a(a, "error in getTimeZone", e);
        }
        return 8;
    }

    public static String[] i(Context context) {
        String[] strArr = new String[2];
        try {
            Locale r = r(context);
            if (r != null) {
                strArr[0] = r.getCountry();
                strArr[1] = r.getLanguage();
            }
            if (TextUtils.isEmpty(strArr[0])) {
                strArr[0] = "Unknown";
            }
            if (TextUtils.isEmpty(strArr[1])) {
                strArr[1] = "Unknown";
            }
        } catch (Exception e) {
            ai.b(a, "error in getLocaleInfo", e);
        }
        return strArr;
    }

    private static Locale r(Context context) {
        Locale locale = null;
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            System.getConfiguration(context.getContentResolver(), configuration);
            if (configuration != null) {
                locale = configuration.locale;
            }
        } catch (Exception e) {
            ai.b(a, "fail to read user config locale");
        }
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }

    public static String j(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("UMENG_APPKEY");
                if (string != null) {
                    return string.trim();
                }
                ai.b(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
            }
        } catch (Exception e) {
            ai.b(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", e);
        }
        return null;
    }

    public static String k(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            ai.d(a, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            return "";
        } catch (Exception e) {
            ai.d(a, "Could not get mac address." + e.toString());
        }
    }

    public static int[] l(Context context) {
        try {
            int a;
            int a2;
            int i;
            Object displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            if ((context.getApplicationInfo().flags & 8192) == 0) {
                a = a(displayMetrics, "noncompatWidthPixels");
                a2 = a(displayMetrics, "noncompatHeightPixels");
            } else {
                a2 = -1;
                a = -1;
            }
            if (a == -1 || a2 == -1) {
                i = displayMetrics.widthPixels;
                a = displayMetrics.heightPixels;
            } else {
                i = a;
                a = a2;
            }
            int[] iArr = new int[2];
            if (i > a) {
                iArr[0] = a;
                iArr[1] = i;
                return iArr;
            }
            iArr[0] = i;
            iArr[1] = a;
            return iArr;
        } catch (Exception e) {
            ai.b(a, "read resolution fail", e);
            return null;
        }
    }

    private static int a(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String m(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Exception e) {
            ai.a(a, "read carrier fail", e);
            return "Unknown";
        }
    }

    public static String n(Context context) {
        String str = "Unknown";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                Object obj = applicationInfo.metaData.get("UMENG_CHANNEL");
                if (obj != null) {
                    String obj2 = obj.toString();
                    if (obj2 != null) {
                        return obj2;
                    }
                    ai.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
                    return str;
                }
            }
        } catch (Exception e) {
            ai.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
            e.printStackTrace();
        }
        return str;
    }

    public static String o(Context context) {
        return context.getPackageName();
    }

    public static String p(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static boolean q(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}
