package com.igexin.a.a.c;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import com.igexin.push.a.m;
import com.igexin.push.core.g;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

public class a extends Activity implements UncaughtExceptionHandler {
    public static boolean a = m.b.equals("debug");

    public static synchronized void a(String str) {
        synchronized (a.class) {
            if (a || (g.Q && g.R >= System.currentTimeMillis())) {
                String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String str2 = g.g;
                if (!(str2 == null || "".equals(str2) || !Environment.getExternalStorageState().equals("mounted"))) {
                    String str3 = "/sdcard/libs/";
                    File file = new File(str3);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    try {
                        file = new File(str3 + str2 + "." + format + ".log");
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                        fileOutputStream.write(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "|" + str).toString() + "\r\n").getBytes());
                        fileOutputStream.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    public static final void a(String str, String str2) {
        if (a) {
            Log.d(str, str2);
        }
    }

    public static final void a(String str, String str2, Throwable th) {
        if (a) {
            Log.d(str, str2, th);
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
    }
}
