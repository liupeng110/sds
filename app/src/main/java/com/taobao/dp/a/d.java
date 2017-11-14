package com.taobao.dp.a;

import android.content.Context;
import com.sina.weibo.sdk.constant.WBConstants;
import java.util.HashMap;

public final class d {
    public static String a(Context context) {
        new StringBuilder("SecurityGuardManagerUtil getAppKey context").append(context);
        try {
            String str = "";
            Class cls = Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
            Object invoke = cls.getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{context});
            if (invoke == null) {
                return str;
            }
            Object invoke2 = cls.getMethod("getStaticDataStoreComp", new Class[0]).invoke(invoke, new Object[0]);
            return (String) invoke2.getClass().getMethod("getAppKeyByIndex", new Class[]{Integer.TYPE, String.class}).invoke(invoke2, new Object[]{Integer.valueOf(0), ""});
        } catch (Exception e) {
            return "";
        }
    }

    public static String a(String str, String str2, String str3, Context context) {
        new StringBuilder("SecurityGuardManagerUtil wXEncrypt context =").append(context);
        try {
            String str4 = "";
            Class cls = Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
            Object invoke = cls.getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{context});
            if (invoke == null) {
                return str4;
            }
            Object invoke2 = cls.getMethod("getStaticDataEncryptComp", new Class[0]).invoke(invoke, new Object[0]);
            if (invoke2 == null) {
                return str4;
            }
            return (String) invoke2.getClass().getMethod("staticSafeEncrypt", new Class[]{Integer.TYPE, String.class, String.class, String.class}).invoke(invoke2, new Object[]{Integer.valueOf(invoke2.getClass().getField("OPEN_ENUM_CIPHER_AES128").getInt(null)), str2, str, str3});
        } catch (Exception e) {
            return "";
        }
    }

    public static String b(String str, String str2, String str3, Context context) {
        new StringBuilder("SecurityGuardManagerUtil wXdecrypt context =").append(context);
        try {
            String str4 = "";
            Class cls = Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
            Object invoke = cls.getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{context});
            if (invoke == null) {
                return str4;
            }
            Object invoke2 = cls.getMethod("getStaticDataEncryptComp", new Class[0]).invoke(invoke, new Object[0]);
            if (invoke2 == null) {
                return str4;
            }
            return (String) invoke2.getClass().getMethod("staticSafeDecrypt", new Class[]{Integer.TYPE, String.class, String.class, String.class}).invoke(invoke2, new Object[]{Integer.valueOf(invoke2.getClass().getField("OPEN_ENUM_CIPHER_AES128").getInt(null)), str2, str, str3});
        } catch (Exception e) {
            return "";
        }
    }

    public static String c(String str, String str2, String str3, Context context) {
        new StringBuilder("SecurityGuardManagerUtil signatureHash context =").append(context);
        try {
            String str4 = "";
            Class cls = Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
            Object invoke = cls.getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{context});
            if (invoke == null) {
                return str4;
            }
            Object invoke2 = cls.getMethod("getSecureSignatureComp", new Class[0]).invoke(invoke, new Object[0]);
            if (invoke2 == null) {
                return str4;
            }
            HashMap hashMap = new HashMap();
            Class cls2 = Class.forName("com.alibaba.wireless.security.open.securesignature.SecureSignatureDefine");
            hashMap.put((String) cls2.getField("OPEN_KEY_SIGN_INPUT").get(null), str);
            int i = cls2.getField("OPEN_ENUM_SIGN_UMID").getInt(null);
            cls2 = Class.forName("com.alibaba.wireless.security.open.SecurityGuardParamContext");
            Object newInstance = cls2.getConstructor(new Class[0]).newInstance(new Object[0]);
            cls2.getField(WBConstants.SSO_APP_KEY).set(newInstance, str2);
            cls2.getField("requestType").setInt(newInstance, i);
            cls2.getField("paramMap").set(newInstance, hashMap);
            return (String) invoke2.getClass().getMethod("signRequest", new Class[]{cls2, String.class}).invoke(invoke2, new Object[]{newInstance, str3});
        } catch (Exception e) {
            return "";
        }
    }
}
