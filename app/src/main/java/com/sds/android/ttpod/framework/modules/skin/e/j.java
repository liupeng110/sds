package com.sds.android.ttpod.framework.modules.skin.e;

import com.sds.android.ttpod.framework.a.c;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

/* LyricParser */
public abstract class j {
    private static final HashMap<String, Class<? extends j>> a = new HashMap();

    protected abstract g a(String str);

    protected abstract void a(g gVar);

    protected abstract void a(g gVar, String str) throws Exception;

    static {
        a.put("lrc", e.class);
        a.put("trc", q.class);
    }

    public static g b(String str) {
        try {
            return ((j) ((Class) a.get(d(str))).newInstance()).c(str);
        } catch (Exception e) {
            return null;
        }
    }

    private static String d(String str) {
        String str2 = "";
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf > -1) {
            return str.substring(lastIndexOf + 1);
        }
        return str2;
    }

    protected final g c(String str) {
        BufferedReader bufferedReader;
        Exception e;
        Throwable th;
        g gVar = null;
        try {
            bufferedReader = new BufferedReader(new c(new FileInputStream(str)));
            try {
                gVar = a(bufferedReader, str);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return gVar;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e4) {
            e = e4;
            bufferedReader = gVar;
            e.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return gVar;
        } catch (Throwable th3) {
            bufferedReader = gVar;
            th = th3;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        return gVar;
    }

    public final g a(BufferedReader bufferedReader, String str) throws Exception {
        g a = a(str);
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                readLine = readLine.trim();
                if (readLine.length() > 0) {
                    a(a, readLine);
                }
            } else {
                Collections.sort(a.h());
                a(a);
                return a;
            }
        }
    }
}
