package com.a.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* GetAppInfo */
public class d {
    public static String a(Context context) {
        String str = "";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData.getString("miguopen_appid").substring(6, 24);
            }
            return "";
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = str;
            Throwable th2 = th;
            Log.e("SDK_LW_CMM", th2.getMessage(), th2);
            return str2;
        }
    }

    public static String b(Context context) {
        String string;
        Throwable e;
        String str = "";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                string = applicationInfo.metaData.getString("excode");
                if (string != null) {
                    try {
                        if (string.length() > 0) {
                            Log.d("SDK_LW_CMM", string);
                            string = string.substring(7, 11);
                        }
                    } catch (NameNotFoundException e2) {
                        e = e2;
                        Log.e("SDK_LW_CMM", e.getMessage(), e);
                        return string;
                    }
                }
            }
            string = "";
        } catch (Throwable e3) {
            Throwable th = e3;
            string = str;
            e = th;
            Log.e("SDK_LW_CMM", e.getMessage(), e);
            return string;
        }
        return string;
    }

    public static String c(Context context) {
        byte[] bArr = null;
        try {
            bArr = d(context).getBytes("UTF-8");
        } catch (Throwable e) {
            Log.e("SDK_LW_CMM", e.getMessage(), e);
        }
        return i.b(i.a(bArr));
    }

    public static String d(Context context) {
        String str = "";
        try {
            str = a(context.getPackageManager().getPackageInfo(e(context), 64).signatures[0].toByteArray());
            return str.toLowerCase();
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = str;
            Throwable th2 = th;
            Log.e("SDK_LW_CMM", th2.getMessage(), th2);
            return str2;
        }
    }

    public static String a(byte[] bArr) {
        String str = "";
        try {
            String toLowerCase = m.a(((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey().toString()).replace(SelectCountryActivity.SPLITTER, "").toLowerCase();
            return toLowerCase.substring(toLowerCase.indexOf("modulus") + 8, toLowerCase.indexOf("publicexponent"));
        } catch (Throwable e) {
            Log.e("SDK_LW_CMM", e.getMessage(), e);
            return str;
        }
    }

    public static String e(Context context) {
        String str = "";
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        } catch (Throwable e) {
            Log.e("SDK_LW_CMM", e.getMessage(), e);
            return str;
        }
    }

    public static String a() {
        return "S2.1";
    }

    public static String a(String str, Context context) {
        byte[] bArr = null;
        if (str != null) {
            try {
                if (!"".equals(str)) {
                    if (str.length() != 15) {
                        str = b.a().a(context);
                    }
                    bArr = str.getBytes("UTF-8");
                    return i.b(i.a(bArr));
                }
            } catch (Throwable e) {
                Log.e("SDK_LW_CMM", e.getMessage(), e);
            }
        }
        return "";
    }

    public static String f(Context context) {
        String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        byte[] bArr = null;
        if (subscriberId != null) {
            try {
                if (!"".equals(subscriberId)) {
                    if (subscriberId.length() != 15) {
                        subscriberId = b.a().a(context);
                    }
                    bArr = subscriberId.getBytes("UTF-8");
                    return i.b(i.a(bArr));
                }
            } catch (Throwable e) {
                Log.e("SDK_LW_CMM", e.getMessage(), e);
            }
        }
        return "";
    }
}
