package com.mradar.sdk.record;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.util.HashMap;
import java.util.Map;

/* DoresoUtils */
public class c {
    public static Map<String, String> a(Context context) {
        Map<String, String> hashMap = new HashMap();
        switch (c(context)) {
            case 0:
                hashMap.put("compress_quality", "10");
                hashMap.put("network_type", "wifi");
                break;
            case 1:
                hashMap.put("compress_quality", "8");
                hashMap.put("network_type", "2g");
                break;
            case 2:
                hashMap.put("compress_quality", "8");
                hashMap.put("network_type", "3g");
                break;
            default:
                hashMap.put("compress_quality", "8");
                break;
        }
        hashMap.put("deviceId", g(context));
        hashMap.put("device_model", a());
        hashMap.put("device_os", b());
        hashMap.put("pre_recorder", f.g ? "1" : FeedbackItem.STATUS_WAITING);
        hashMap.put("app_version", f(context));
        if (f.f) {
            hashMap.put("coordinate", d(context));
        }
        return hashMap;
    }

    public static boolean b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    protected static int c(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 0;
        }
        if (activeNetworkInfo.getType() == 1) {
            return 0;
        }
        if (activeNetworkInfo.getSubtype() == 1 || activeNetworkInfo.getSubtype() == 4 || activeNetworkInfo.getSubtype() == 2) {
            return 1;
        }
        return 2;
    }

    private static String f(Context context) {
        String str = "";
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return str;
        }
    }

    private static String g(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
    }

    private static String a() {
        return Build.MODEL.replaceAll(" ", "_");
    }

    private static String b() {
        return VERSION.RELEASE;
    }

    protected static double a(byte[] bArr, int i) {
        float f = 0.0f;
        int i2 = i >> 3;
        float f2 = 0.0f;
        for (int i3 = 0; i3 < i2; i3++) {
            short s = (short) (((short) (bArr[(i3 << 3) + 1] << 8)) | (bArr[i3 << 3] & MotionEventCompat.ACTION_MASK));
            int i4 = ((s >> 15) ^ s) - (s >> 15);
            f2 += (float) (i4 * i4);
            f += (float) i4;
        }
        f /= (float) i2;
        return Math.min(Math.log10((double) (((f2 / ((float) i2)) - (f * f)) + 1.0f)), 8.0d) / 8.0d;
    }

    public static String d(Context context) {
        String str = "";
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(1);
        String bestProvider = locationManager.getBestProvider(criteria, true);
        if (bestProvider == null) {
            return "(-1,-1)";
        }
        Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);
        if (lastKnownLocation == null) {
            return "(-1,-1)";
        }
        double latitude = lastKnownLocation.getLatitude();
        double longitude = lastKnownLocation.getLongitude();
        latitude = a(latitude, 2);
        return "(" + latitude + SelectCountryActivity.SPLITTER + a(longitude, 2) + ")";
    }

    public static double a(double d, int i) {
        if (i < 0) {
            return d;
        }
        int i2 = 1;
        for (int i3 = 0; i3 < i; i3++) {
            i2 *= 10;
        }
        return ((double) Math.round(((double) i2) * d)) / ((double) i2);
    }

    public static String e(Context context) {
        return "http://" + context.getSharedPreferences("ip", 0).getString("default", f.b);
    }

    public static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Editor edit = context.getSharedPreferences("ip", 0).edit();
        edit.putString("default", str);
        f.h = true;
        e.a("save ip default", str);
        return edit.commit();
    }
}
