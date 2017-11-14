package com.ut.mini.core.sign;

import android.content.Context;
import com.sina.weibo.sdk.constant.WBConstants;
import com.ut.mini.b.a;
import com.ut.mini.base.c;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class UTSecuritySDKRequestAuthentication implements IUTRequestAuthentication {
    private static Object b = null;
    private static Object c = null;
    private static Class d = null;
    private static Field e = null;
    private static Field f = null;
    private static Field g = null;
    private static Method h = null;
    private static int i = 1;
    private String a = null;

    public String getAppkey() {
        return this.a;
    }

    public UTSecuritySDKRequestAuthentication(String str) {
        this.a = str;
    }

    static {
        _initSecurityCheck();
    }

    private static void _initSecurityCheck() {
        Class cls;
        Throwable th;
        Method method;
        boolean booleanValue;
        Class cls2 = null;
        try {
            cls = Class.forName("com.taobao.wireless.security.sdk.SecurityGuardManager");
            try {
                b = cls.getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{c.a().k()});
                c = cls.getMethod("getSecureSignatureComp", new Class[0]).invoke(b, new Object[0]);
            } catch (Throwable th2) {
                th = th2;
                a.a(2, "initSecurityCheck", th.getMessage());
                if (cls == null) {
                    try {
                        d = Class.forName("com.taobao.wireless.security.sdk.SecurityGuardParamContext");
                        e = d.getDeclaredField(WBConstants.SSO_APP_KEY);
                        f = d.getDeclaredField("paramMap");
                        g = d.getDeclaredField("requestType");
                        method = cls.getMethod("isOpen", new Class[0]);
                    } catch (Throwable th3) {
                        a.a(2, "initSecurityCheck", th3.getMessage());
                        return;
                    }
                    if (method != null) {
                        booleanValue = ((Boolean) method.invoke(b, new Object[0])).booleanValue();
                    } else {
                        try {
                            cls2 = Class.forName("com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent");
                        } catch (Throwable th32) {
                            a.a(2, "initSecurityCheck", th32.getMessage());
                        }
                        if (cls2 == null) {
                            booleanValue = true;
                        } else {
                            booleanValue = false;
                        }
                    }
                    i = booleanValue ? 1 : 12;
                    h = Class.forName("com.taobao.wireless.security.sdk.securesignature.ISecureSignatureComponent").getMethod("signRequest", new Class[]{d});
                }
            }
        } catch (Throwable th4) {
            th32 = th4;
            cls = cls2;
            a.a(2, "initSecurityCheck", th32.getMessage());
            if (cls == null) {
                d = Class.forName("com.taobao.wireless.security.sdk.SecurityGuardParamContext");
                e = d.getDeclaredField(WBConstants.SSO_APP_KEY);
                f = d.getDeclaredField("paramMap");
                g = d.getDeclaredField("requestType");
                method = cls.getMethod("isOpen", new Class[0]);
                if (method != null) {
                    cls2 = Class.forName("com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent");
                    if (cls2 == null) {
                        booleanValue = false;
                    } else {
                        booleanValue = true;
                    }
                } else {
                    booleanValue = ((Boolean) method.invoke(b, new Object[0])).booleanValue();
                }
                if (booleanValue) {
                }
                i = booleanValue ? 1 : 12;
                h = Class.forName("com.taobao.wireless.security.sdk.securesignature.ISecureSignatureComponent").getMethod("signRequest", new Class[]{d});
            }
        }
        if (cls == null) {
            d = Class.forName("com.taobao.wireless.security.sdk.SecurityGuardParamContext");
            e = d.getDeclaredField(WBConstants.SSO_APP_KEY);
            f = d.getDeclaredField("paramMap");
            g = d.getDeclaredField("requestType");
            method = cls.getMethod("isOpen", new Class[0]);
            if (method != null) {
                booleanValue = ((Boolean) method.invoke(b, new Object[0])).booleanValue();
            } else {
                cls2 = Class.forName("com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent");
                if (cls2 == null) {
                    booleanValue = true;
                } else {
                    booleanValue = false;
                }
            }
            if (booleanValue) {
            }
            i = booleanValue ? 1 : 12;
            h = Class.forName("com.taobao.wireless.security.sdk.securesignature.ISecureSignatureComponent").getMethod("signRequest", new Class[]{d});
        }
    }

    public String getSign(String str) {
        if (this.a == null) {
            a.c(1, "UTSecuritySDKRequestAuthentication:getSign", "There is no appkey,please check it!");
            return null;
        } else if (str == null) {
            return null;
        } else {
            String str2;
            if (!(b == null || d == null || e == null || f == null || g == null || h == null || c == null)) {
                try {
                    Object newInstance = d.newInstance();
                    e.set(newInstance, this.a);
                    ((Map) f.get(newInstance)).put("INPUT", str);
                    g.set(newInstance, Integer.valueOf(i));
                    str2 = (String) h.invoke(c, new Object[]{newInstance});
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    str2 = null;
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                    str2 = null;
                } catch (IllegalArgumentException e3) {
                    e3.printStackTrace();
                    str2 = null;
                } catch (InvocationTargetException e4) {
                    e4.printStackTrace();
                }
                return str2;
            }
            str2 = null;
            return str2;
        }
    }
}
