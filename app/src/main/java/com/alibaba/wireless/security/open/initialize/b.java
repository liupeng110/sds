package com.alibaba.wireless.security.open.initialize;

import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import com.alibaba.wireless.security.open.a.a;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class b {
    public static String a = null;
    private static String b = Environment.getDataDirectory().getAbsolutePath();
    private static String c = Environment.getRootDirectory().getAbsolutePath();
    private static String d = "/lib/";
    private static String e = (Environment.getRootDirectory().getAbsolutePath() + d);
    private static final String[] f = new String[]{"FileNotFindError", "IOError", "SecurityError", "ExceptionError"};
    private static final String[] g = c.a;
    private static String h = "libsecuritysdk-2.3.39.so";
    private static byte[] i = new byte[0];
    private static boolean j = false;
    private static boolean k = false;

    public static boolean a(ContextWrapper contextWrapper) {
        String str = null;
        if (!k) {
            synchronized (i) {
                if (!k) {
                    boolean z;
                    String[] strArr = new String[6];
                    strArr[2] = null;
                    String packageCodePath = contextWrapper.getPackageCodePath();
                    if (packageCodePath != null) {
                        str = System.getProperty("file.separator", "/");
                        if (packageCodePath.startsWith(b)) {
                            str = b;
                        } else if (packageCodePath.startsWith(c)) {
                            str = c;
                        } else {
                            int lastIndexOf = packageCodePath.lastIndexOf(str);
                            str = "SD";
                            if (lastIndexOf != -1) {
                                str = packageCodePath.substring(0, lastIndexOf);
                            }
                        }
                    }
                    a.a();
                    if (str == null || str.equals(b) || str.equals(c)) {
                        strArr[1] = null;
                    } else {
                        strArr[1] = str + d;
                    }
                    ApplicationInfo applicationInfo = contextWrapper.getApplicationInfo();
                    if (str.equals(c)) {
                        strArr[0] = e;
                    } else {
                        strArr[0] = applicationInfo.dataDir + d;
                    }
                    if (a.a()) {
                        new StringBuilder("getExternalPackagePath:path[0]=").append(strArr[0]);
                        if (strArr[1] != null) {
                            new StringBuilder("getExternalPackagePath:path[1]=").append(strArr[1]);
                        }
                    }
                    if (strArr[1] != null) {
                        strArr[2] = applicationInfo.dataDir + "/files/";
                    } else {
                        strArr[1] = applicationInfo.dataDir + "/files/";
                    }
                    strArr[3] = "cust/preinstalled/public/lib/";
                    strArr[4] = "system/vender/lib/";
                    strArr[5] = a;
                    for (int i = 0; i < 6; i++) {
                        if (strArr[i] != null) {
                            str = b(strArr[i] + h);
                            if (a(str, f)) {
                                packageCodePath = str;
                                break;
                            }
                        }
                    }
                    packageCodePath = "Error";
                    if (packageCodePath != null) {
                        for (Object equals : g) {
                            if (packageCodePath.equals(equals)) {
                                z = true;
                                break;
                            }
                        }
                    }
                    z = false;
                    j = z;
                    k = true;
                }
            }
        }
        return j;
    }

    public static boolean a(String str) {
        String b = b(str);
        if (b == null) {
            return false;
        }
        for (Object equals : g) {
            if (b.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(String str, String[] strArr) {
        if (str == null) {
            return false;
        }
        for (Object equals : strArr) {
            if (str.equals(equals)) {
                return false;
            }
        }
        return true;
    }

    private static String b(String str) {
        FileInputStream fileInputStream;
        String a;
        FileNotFoundException e;
        Throwable th;
        IOException e2;
        SecurityException e3;
        Exception e4;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                a = a.a(fileInputStream);
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                try {
                    e.printStackTrace();
                    a = "FileNotFindError";
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e7) {
                        }
                    }
                    return a;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e8) {
                        }
                    }
                    throw th;
                }
            } catch (IOException e9) {
                e2 = e9;
                fileInputStream2 = fileInputStream;
                try {
                    e2.printStackTrace();
                    a = "IOError";
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e10) {
                        }
                    }
                    return a;
                } catch (Throwable th3) {
                    th = th3;
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    throw th;
                }
            } catch (SecurityException e11) {
                e3 = e11;
                fileInputStream2 = fileInputStream;
                e3.printStackTrace();
                a = "SecurityError";
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e12) {
                    }
                }
                return a;
            } catch (Exception e13) {
                e4 = e13;
                fileInputStream2 = fileInputStream;
                e4.printStackTrace();
                a = "ExceptionError";
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e14) {
                    }
                }
                return a;
            }
        } catch (FileNotFoundException e15) {
            e = e15;
            fileInputStream = null;
            e.printStackTrace();
            a = "FileNotFindError";
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return a;
        } catch (IOException e16) {
            e2 = e16;
            e2.printStackTrace();
            a = "IOError";
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            return a;
        } catch (SecurityException e17) {
            e3 = e17;
            e3.printStackTrace();
            a = "SecurityError";
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            return a;
        } catch (Exception e18) {
            e4 = e18;
            e4.printStackTrace();
            a = "ExceptionError";
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            return a;
        }
        return a;
    }
}
