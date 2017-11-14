package com.sds.android.sdk.lib.util;

import android.os.Process;
import android.util.Log;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/* LogUtils */
public final class g {
    private static boolean a = true;
    private static boolean b = false;
    private static final SimpleDateFormat c = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
    private static String d;

    public static void a(boolean z) {
        a = z;
    }

    public static void a(String str, String str2) {
        if (a) {
            String str3 = "TTPOD:" + str2;
            Log.d(str, str3);
            f(str, str3);
        }
    }

    public static void a(String str, String str2, Object... objArr) {
        if (a) {
            String format = String.format("TTPOD:" + str2, objArr);
            Log.d(str, format);
            f(str, format);
        }
    }

    public static void b(String str, String str2) {
        if (a) {
            String str3 = "TTPOD:" + str2;
            Log.w(str, str3);
            f(str, str3);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (a) {
            String str3 = "TTPOD:" + str2;
            Log.w(str, str3, th);
            f(str, str3 + "\n" + Log.getStackTraceString(th));
        }
    }

    public static void b(String str, String str2, Object... objArr) {
        if (a) {
            String format = String.format("TTPOD:" + str2, objArr);
            Log.w(str, format);
            f(str, format);
        }
    }

    public static void c(String str, String str2) {
        if (a) {
            String str3 = "TTPOD:" + str2;
            Log.e(str, str3);
            f(str, str3);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (a) {
            String str3 = "TTPOD:" + str2;
            Log.e(str, str3, th);
            f(str, str3 + "\n" + Log.getStackTraceString(th));
        }
    }

    public static void c(String str, String str2, Object... objArr) {
        if (a) {
            String format = String.format("TTPOD:" + str2, objArr);
            Log.e(str, format);
            f(str, format);
        }
    }

    public static void d(String str, String str2) {
        if (a) {
            String str3 = "TTPOD:" + str2;
            Log.i(str, str3);
            f(str, str3);
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (a) {
            String format = String.format("TTPOD:" + str2, objArr);
            Log.i(str, format);
            f(str, format);
        }
    }

    public static void e(String str, String str2) {
        if (a) {
            String str3 = "TTPOD:" + str2;
            Log.v(str, str3);
            f(str, str3);
        }
    }

    private static void f(String str, String str2) {
        Throwable th;
        if (b) {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(d, true);
                try {
                    fileWriter.write(String.format("%s pid=%d %s: %s\n", new Object[]{c.format(new Date()), Integer.valueOf(Process.myPid()), str, str2}));
                    fileWriter.flush();
                    fileWriter.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                fileWriter = null;
                th.printStackTrace();
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
