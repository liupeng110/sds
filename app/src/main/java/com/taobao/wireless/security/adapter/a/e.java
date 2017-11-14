package com.taobao.wireless.security.adapter.a;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.taobao.wireless.security.sdk.SecurityGuardManager;
import java.util.Random;

@TargetApi(9)
public final class e {
    private static String a() {
        StringBuffer stringBuffer = new StringBuffer();
        long currentTimeMillis = System.currentTimeMillis();
        String l = Long.toString(currentTimeMillis);
        stringBuffer.append(l.substring(l.length() - 5));
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(Build.MODEL.replaceAll(" ", ""));
        while (stringBuffer2.length() < 6) {
            stringBuffer2.append('0');
        }
        stringBuffer.append(stringBuffer2.substring(0, 6));
        Random random = new Random(currentTimeMillis);
        currentTimeMillis = 0;
        while (currentTimeMillis < 4096) {
            currentTimeMillis = random.nextLong();
        }
        stringBuffer.append(Long.toHexString(currentTimeMillis).substring(0, 4));
        return stringBuffer.toString();
    }

    public static String a(Context context) {
        return a(context, "dynamicreid");
    }

    @TargetApi(9)
    private static String a(Context context, String str) {
        String str2 = null;
        if (str.length() == 0) {
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        String string = sharedPreferences != null ? sharedPreferences.getString(str, null) : null;
        if (string != null && string.length() > 0) {
            return string;
        }
        string = a(context.getPackageName());
        ContentResolver contentResolver = context.getContentResolver();
        if (!(string == null || contentResolver == null)) {
            string = System.getString(contentResolver, string + str);
            if (string != null && string.length() > 0) {
                string = SecurityGuardManager.getInstance(context).getStaticDataEncryptComp().staticSafeDecrypt(16, "wb_sc_int_res_k1", string);
                if (string == null || string.length() > 0) {
                    str2 = string;
                }
            }
        }
        if (str2 == null || str2.length() <= 0) {
            str2 = a();
            a(context, str2, str);
            string = context.getPackageName();
            contentResolver = context.getContentResolver();
            string = a(string);
            String staticSafeEncrypt = SecurityGuardManager.getInstance(context).getStaticDataEncryptComp().staticSafeEncrypt(16, "wb_sc_int_res_k1", str2);
            if (string == null || contentResolver == null || staticSafeEncrypt == null || staticSafeEncrypt.length() <= 0) {
                return str2;
            }
            System.putString(contentResolver, string + str, staticSafeEncrypt);
            return str2;
        }
        a(context, str2, str);
        return str2;
    }

    private static String a(String str) {
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        StringBuilder stringBuilder = new StringBuilder(length * 2);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(cArr[(bytes[i] >> 4) & 15]);
            stringBuilder.append(cArr[bytes[i] & 15]);
        }
        return stringBuilder.toString();
    }

    private static boolean a(Context context, String str, String str2) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(str2, 0);
        if (sharedPreferences == null || str == null) {
            return false;
        }
        Editor edit = sharedPreferences.edit();
        edit.putString(str2, str);
        if (VERSION.SDK_INT >= 9) {
            edit.apply();
        } else {
            edit.commit();
        }
        return true;
    }

    public static String b(Context context) {
        return a(context, "dynamicrsid");
    }

    public static String c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("imei", 0);
        String string = sharedPreferences.getString("imei", null);
        if (string == null || string.length() == 0) {
            try {
                string = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            } catch (SecurityException e) {
                e.printStackTrace();
                string = null;
            } catch (Exception e2) {
                e2.printStackTrace();
                string = null;
            }
            if (string == null || string.length() == 0) {
                string = a();
            }
            while (string.length() < 15) {
                string = new StringBuilder(FeedbackItem.STATUS_WAITING).append(string).toString();
            }
            Editor edit = sharedPreferences.edit();
            edit.putString("imei", string);
            edit.commit();
        }
        return string.trim();
    }

    public static String d(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("imei", 0);
        String string = sharedPreferences.getString("imsi", null);
        if (string == null || string.length() == 0) {
            try {
                string = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            } catch (SecurityException e) {
                e.printStackTrace();
                string = null;
            } catch (Exception e2) {
                e2.printStackTrace();
                string = null;
            }
            if (string == null || string.length() == 0) {
                string = a();
            }
            while (string.length() < 15) {
                string = new StringBuilder(FeedbackItem.STATUS_WAITING).append(string).toString();
            }
            Editor edit = sharedPreferences.edit();
            edit.putString("imsi", string);
            edit.commit();
        }
        return string;
    }
}
