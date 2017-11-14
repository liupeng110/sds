package com.taobao.wireless.security.adapter.datacollection;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.alibaba.wireless.security.open.a.b;
import com.taobao.wireless.security.adapter.JNICLibrary;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class a implements b {
    private static final Object b = new Object();
    private static final Object c = new Object();
    private static final Object d = new Object();
    private Context a;

    public a(Context context) {
        this.a = context.getApplicationContext();
        DeviceInfoCapturer.initialize(this.a, this);
        AppInfoCapturer.initialize(this.a);
    }

    private static boolean a(Editor editor) {
        try {
            Method method = Editor.class.getMethod("apply", new Class[0]);
            if (method == null) {
                return false;
            }
            method.invoke(editor, new Object[0]);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        } catch (IllegalAccessException e2) {
            return false;
        } catch (IllegalArgumentException e3) {
            return false;
        } catch (InvocationTargetException e4) {
            return false;
        }
    }

    private SharedPreferences c() {
        return this.a.getSharedPreferences("DataCollectionData", 4);
    }

    public final String a() {
        String string;
        synchronized (b) {
            string = c().getString("key_umid", "");
        }
        return string;
    }

    public final String a(int i, String str) {
        String str2;
        synchronized (d) {
            byte[] encryptedDevAndEnvInfoNative = JNICLibrary.getInstance().getEncryptedDevAndEnvInfoNative(i, str);
            if (encryptedDevAndEnvInfoNative == null || encryptedDevAndEnvInfoNative.length == 0) {
                str2 = null;
            } else {
                str2 = b.a(encryptedDevAndEnvInfoNative);
            }
        }
        return str2;
    }

    public final boolean a(String str) {
        boolean z;
        synchronized (c) {
            if (str == null) {
                str = "";
            }
            String b = b();
            if (str.equals(b)) {
                z = false;
            } else {
                Editor edit = c().edit();
                edit.putString("key_nick", str);
                if (VERSION.SDK_INT < 9) {
                    edit.commit();
                } else if (!a(edit)) {
                    edit.commit();
                }
                if (!(b == null || b.length() == 0)) {
                    JNICLibrary.getInstance().updateNickNative();
                }
                z = true;
            }
        }
        return z;
    }

    public final String b() {
        String string;
        synchronized (c) {
            string = c().getString("key_nick", "");
        }
        return string;
    }
}
