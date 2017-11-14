package com.sds.android.sdk.lib.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* FileUtils */
public class e {
    private static String a = File.separator;
    private static char b = File.separatorChar;

    public static boolean a(String str) {
        return !m.a(str) && new File(str).exists();
    }

    public static boolean b(String str) {
        if (m.a(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            return true;
        }
        return false;
    }

    public static long c(String str) {
        if (b(str)) {
            return new File(str).length();
        }
        return 0;
    }

    public static boolean d(String str) {
        if (m.a(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        return false;
    }

    public static synchronized File e(String str) {
        File file = null;
        synchronized (e.class) {
            if (!m.a(str)) {
                File file2 = new File(str);
                if (file2.isFile()) {
                    file = file2;
                } else {
                    File parentFile = file2.getParentFile();
                    if (parentFile != null && (parentFile.isDirectory() || parentFile.mkdirs())) {
                        try {
                            if (file2.createNewFile()) {
                                file = file2;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return file;
    }

    public static synchronized File f(String str) {
        File file = null;
        synchronized (e.class) {
            if (!m.a(str)) {
                File file2 = new File(str);
                if (file2.isDirectory() || file2.mkdirs()) {
                    file = file2;
                }
            }
        }
        return file;
    }

    public static synchronized long g(String str) {
        long j;
        synchronized (e.class) {
            if (m.a(str)) {
                j = 0;
            } else {
                j = a(new File(str));
            }
        }
        return j;
    }

    public static synchronized long a(File file) {
        long j = 0;
        synchronized (e.class) {
            if (file != null) {
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        long j2 = 0;
                        for (File file2 : listFiles) {
                            j2 += file2.isDirectory() ? a(file2) : file2.length();
                        }
                        j = j2;
                    }
                } else {
                    j = file.length();
                }
            }
        }
        return j;
    }

    public static synchronized void a(String str, long j, String[] strArr) {
        synchronized (e.class) {
            long g = g(str);
            if (g > j) {
                List arrayList = strArr == null ? new ArrayList() : Arrays.asList(strArr);
                File[] listFiles = new File(str).listFiles();
                if (listFiles != null) {
                    List<File> asList = Arrays.asList(listFiles);
                    try {
                        Collections.sort(asList, new Comparator<File>() {
                            public /* synthetic */ int compare(Object obj, Object obj2) {
                                return a((File) obj, (File) obj2);
                            }

                            public int a(File file, File file2) {
                                if (file.lastModified() == file2.lastModified()) {
                                    return 0;
                                }
                                return file.lastModified() > file2.lastModified() ? -1 : 1;
                            }
                        });
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                    for (File file : asList) {
                        if (g <= j) {
                            break;
                        }
                        if (!arrayList.contains(file.getAbsolutePath())) {
                            file.lastModified();
                            g -= file.length();
                            file.delete();
                        }
                        g = g;
                    }
                }
            }
        }
    }

    public static synchronized int b(File file) {
        int i = 0;
        synchronized (e.class) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        i += b(file2);
                    }
                    if (file2.delete()) {
                        i++;
                    }
                }
            }
        }
        return i;
    }

    public static synchronized boolean h(String str) {
        boolean z;
        synchronized (e.class) {
            z = !m.a(str) && c(new File(str));
        }
        return z;
    }

    public static synchronized boolean c(File file) {
        boolean z = false;
        synchronized (e.class) {
            if (file == null) {
                z = true;
            } else {
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        for (File c : listFiles) {
                            if (!c(c)) {
                                break;
                            }
                        }
                    }
                }
                if (!file.exists() || file.delete()) {
                    z = true;
                }
            }
        }
        return z;
    }

    public static String i(String str) {
        if (str == null) {
            throw new NullPointerException("path should not be null.");
        }
        String a;
        try {
            a = m.a(new FileInputStream(str));
        } catch (Exception e) {
            e.printStackTrace();
            a = null;
        }
        return a != null ? a : "";
    }

    public static synchronized boolean a(String str, String str2) {
        IOException e;
        Throwable th;
        ArrayIndexOutOfBoundsException e2;
        boolean z = false;
        synchronized (e.class) {
            if (str2 == null) {
                throw new NullPointerException("path should not be null.");
            }
            BufferedWriter bufferedWriter = null;
            try {
                File e3 = e(str2);
                if (e3 == null) {
                    g.a("FileUtils", "file == null path=%s", str2);
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                } else {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(e3));
                    if (str == null) {
                        str = "";
                    }
                    try {
                        bufferedWriter2.write(str);
                        bufferedWriter2.flush();
                        if (bufferedWriter2 != null) {
                            try {
                                bufferedWriter2.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        z = true;
                    } catch (IOException e6) {
                        e4 = e6;
                        bufferedWriter = bufferedWriter2;
                        try {
                            e4.printStackTrace();
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.close();
                                } catch (IOException e42) {
                                    e42.printStackTrace();
                                }
                            }
                            return z;
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.close();
                                } catch (IOException e422) {
                                    e422.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (ArrayIndexOutOfBoundsException e7) {
                        e2 = e7;
                        bufferedWriter = bufferedWriter2;
                        e2.printStackTrace();
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e4222) {
                                e4222.printStackTrace();
                            }
                        }
                        return z;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        throw th;
                    }
                }
            } catch (IOException e8) {
                e4222 = e8;
                e4222.printStackTrace();
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                return z;
            } catch (ArrayIndexOutOfBoundsException e9) {
                e2 = e9;
                e2.printStackTrace();
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                return z;
            }
        }
        return z;
    }

    public static synchronized boolean a(InputStream inputStream, String str) {
        Exception e;
        Throwable th;
        boolean z = false;
        synchronized (e.class) {
            if (str == null) {
                throw new NullPointerException("path should not be null.");
            }
            FileOutputStream fileOutputStream = null;
            try {
                File e2 = e(str);
                if (e2 == null) {
                    g.a("FileUtils", "inputStream file == null path=%s", str);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    try {
                        inputStream.close();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                } else {
                    byte[] bArr = new byte[4096];
                    FileOutputStream fileOutputStream2 = new FileOutputStream(e2);
                    while (true) {
                        try {
                            int read = inputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream2.write(bArr, 0, read);
                        } catch (Exception e5) {
                            e4 = e5;
                            fileOutputStream = fileOutputStream2;
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream = fileOutputStream2;
                        }
                    }
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    try {
                        inputStream.close();
                    } catch (Exception e7) {
                        e7.printStackTrace();
                    }
                    z = true;
                }
            } catch (Exception e8) {
                e4 = e8;
                try {
                    e4.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    try {
                        inputStream.close();
                    } catch (Exception e42) {
                        e42.printStackTrace();
                    }
                    return z;
                } catch (Throwable th3) {
                    th = th3;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                    try {
                        inputStream.close();
                    } catch (Exception e422) {
                        e422.printStackTrace();
                    }
                    throw th;
                }
            }
        }
        return z;
    }

    public static boolean b(String str, String str2) {
        FileNotFoundException e;
        if (str == null || str2 == null) {
            throw new NullPointerException("path should not be null.");
        }
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(str2);
            try {
                return a((InputStream) fileInputStream, str);
            } catch (FileNotFoundException e2) {
                e = e2;
                e.printStackTrace();
                try {
                    fileInputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                return false;
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            fileInputStream = null;
            e.printStackTrace();
            fileInputStream.close();
            return false;
        }
    }

    public static String j(String str) {
        if (m.a(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(63);
        if (lastIndexOf > 0) {
            str = str.substring(0, lastIndexOf);
        }
        lastIndexOf = str.lastIndexOf(b);
        return lastIndexOf >= 0 ? str.substring(lastIndexOf + 1) : str;
    }

    public static String k(String str) {
        String j = j(str);
        int lastIndexOf = j.lastIndexOf(46);
        return lastIndexOf > 0 ? j.substring(0, lastIndexOf) : j;
    }

    public static String l(String str) {
        if (m.a(str)) {
            return "";
        }
        int i;
        if (str == null || !str.startsWith(a)) {
            i = -1;
        } else {
            i = str.lastIndexOf(b);
        }
        return i == -1 ? a : str.substring(0, i);
    }

    public static String m(String str) {
        if (!m.a(str)) {
            int lastIndexOf = str.lastIndexOf(63);
            if (lastIndexOf > 0) {
                str = str.substring(0, lastIndexOf);
            }
            lastIndexOf = str.lastIndexOf(47);
            if (lastIndexOf >= 0) {
                str = str.substring(lastIndexOf + 1);
            }
            if (str.length() > 0) {
                lastIndexOf = str.lastIndexOf(46);
                if (lastIndexOf >= 0) {
                    return str.substring(lastIndexOf + 1);
                }
            }
        }
        return "";
    }

    public static long n(String str) {
        if (m.a(str)) {
            return 0;
        }
        return new File(str).lastModified();
    }

    public static boolean c(String str, String str2) {
        File file = new File(str);
        return file.isFile() && file.renameTo(new File(str2));
    }

    public static String o(String str) {
        return str == null ? null : str.replaceAll("([{/\\\\:*?\"<>|}\\u0000-\\u001f\\uD7B0-\\uFFFF]+)", "");
    }

    public static String p(String str) {
        try {
            str = new File(str).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static boolean d(String str, String str2) {
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        String str3 = str + File.separator + valueOf.toString();
        boolean z = false;
        e(str3);
        if (a(str3) && a(str2 + File.separator + valueOf)) {
            z = true;
        }
        h(str3);
        return z;
    }
}
