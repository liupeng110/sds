package com.c.a;

import android.content.Context;
import android.content.SharedPreferences;
import c.a.ah;
import c.a.ai;
import c.a.ak;
import java.io.File;
import java.io.InputStream;

/* StoreHelper */
public final class j {
    private static j a = new j();
    private static Context b;
    private static String c;
    private static long d = 1209600000;
    private static long e = 2097152;

    public static j a(Context context) {
        if (b == null) {
            b = context.getApplicationContext();
        }
        if (c == null) {
            c = context.getPackageName();
        }
        return a;
    }

    private static boolean a(File file) {
        long length = file.length();
        if (!file.exists() || length <= e) {
            return false;
        }
        return true;
    }

    public int[] a() {
        SharedPreferences g = g();
        int[] iArr = new int[2];
        if (g.getInt("umeng_net_report_policy", -1) != -1) {
            iArr[0] = g.getInt("umeng_net_report_policy", 1);
            iArr[1] = (int) g.getLong("umeng_net_report_interval", 0);
        } else {
            iArr[0] = g.getInt("umeng_local_report_policy", 1);
            iArr[1] = (int) g.getLong("umeng_local_report_interval", 0);
        }
        return iArr;
    }

    public byte[] b() {
        InputStream openFileInput;
        Exception e;
        Throwable th;
        byte[] bArr = null;
        String i = i();
        File file = new File(b.getFilesDir(), i);
        if (a(file)) {
            file.delete();
        } else if (file.exists()) {
            try {
                openFileInput = b.openFileInput(i);
                try {
                    bArr = ak.b(openFileInput);
                    ak.c(openFileInput);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        ak.c(openFileInput);
                        return bArr;
                    } catch (Throwable th2) {
                        th = th2;
                        ak.c(openFileInput);
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                openFileInput = bArr;
                e.printStackTrace();
                ak.c(openFileInput);
                return bArr;
            } catch (Throwable th3) {
                openFileInput = bArr;
                th = th3;
                ak.c(openFileInput);
                throw th;
            }
        }
        return bArr;
    }

    public void a(byte[] bArr) {
        try {
            ak.a(new File(b.getFilesDir(), i()), bArr);
        } catch (Exception e) {
            ai.b("MobclickAgent", e.getMessage());
        }
    }

    public void c() {
        b.deleteFile(h());
        b.deleteFile(i());
    }

    public byte[] d() {
        InputStream openFileInput;
        Exception e;
        Throwable th;
        String j = j();
        File file = new File(b.getFilesDir(), j);
        try {
            if (!file.exists() || file.length() <= 0) {
                return null;
            }
            try {
                openFileInput = b.openFileInput(j);
            } catch (Exception e2) {
                e = e2;
                openFileInput = null;
                try {
                    e.printStackTrace();
                    ak.c(openFileInput);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    ak.c(openFileInput);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                openFileInput = null;
                ak.c(openFileInput);
                throw th;
            }
            try {
                byte[] b = ak.b(openFileInput);
                ak.c(openFileInput);
                return b;
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                ak.c(openFileInput);
                return null;
            }
        } catch (Exception e4) {
            file.delete();
            e4.printStackTrace();
        }
    }

    public void b(byte[] bArr) {
        try {
            ak.a(new File(b.getFilesDir(), j()), bArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void e() {
        ai.a("--->", "delete envelope:" + b.deleteFile(j()));
    }

    public boolean f() {
        File file = new File(b.getFilesDir(), j());
        if (!file.exists() || file.length() <= 0) {
            return false;
        }
        return true;
    }

    public SharedPreferences g() {
        return b.getSharedPreferences("mobclick_agent_online_setting_" + c, 0);
    }

    private String h() {
        return "mobclick_agent_header_" + c;
    }

    private String i() {
        return "mobclick_agent_cached_" + c + ah.a(b);
    }

    private String j() {
        return "mobclick_agent_sealed_" + c;
    }
}
