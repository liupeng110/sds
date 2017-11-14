package com.sds.android.ttpod.framework.modules.skin;

import android.content.res.AssetManager;
import android.text.TextUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.framework.base.BaseApplication;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* CachedOnlineListReader */
public class c {
    public static String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || b(str2)) {
            str2 = b(str3, str);
        }
        return a(str2);
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str) || b(str)) {
            c(str2, str);
        }
        return a(str);
    }

    private static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (e.g(str) != 0) {
            return false;
        }
        e.h(str);
        return true;
    }

    public static String a(String str) {
        Closeable bufferedReader;
        FileNotFoundException e;
        Throwable th;
        IOException e2;
        Exception e3;
        String str2 = null;
        if (!(TextUtils.isEmpty(str) || b(str))) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str))));
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                    }
                    str2 = stringBuilder.toString();
                    a(bufferedReader);
                } catch (FileNotFoundException e4) {
                    e = e4;
                    try {
                        e.printStackTrace();
                        a(bufferedReader);
                        return str2;
                    } catch (Throwable th2) {
                        th = th2;
                        a(bufferedReader);
                        throw th;
                    }
                } catch (IOException e5) {
                    e2 = e5;
                    e2.printStackTrace();
                    a(bufferedReader);
                    return str2;
                } catch (Exception e6) {
                    e3 = e6;
                    e3.printStackTrace();
                    a(bufferedReader);
                    return str2;
                }
            } catch (FileNotFoundException e7) {
                e = e7;
                Object obj = str2;
                e.printStackTrace();
                a(bufferedReader);
                return str2;
            } catch (IOException e8) {
                e2 = e8;
                bufferedReader = str2;
                e2.printStackTrace();
                a(bufferedReader);
                return str2;
            } catch (Exception e9) {
                e3 = e9;
                bufferedReader = str2;
                e3.printStackTrace();
                a(bufferedReader);
                return str2;
            } catch (Throwable th3) {
                bufferedReader = str2;
                th = th3;
                a(bufferedReader);
                throw th;
            }
        }
        return str2;
    }

    private static String b(String str, String str2) {
        Closeable bufferedInputStream;
        IOException e;
        Throwable th;
        Closeable closeable = null;
        AssetManager assets = BaseApplication.e().getResources().getAssets();
        Closeable bufferedOutputStream;
        try {
            String[] list = assets.list(str);
            if (list == null || list.length <= 0) {
                a(null);
                a(null);
                return null;
            }
            String str3 = str2 + File.separator + e.j(list[0]);
            e.e(str3);
            bufferedInputStream = new BufferedInputStream(assets.open(str + File.separator + list[0]));
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str3));
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read != -1) {
                            bufferedOutputStream.write(bArr, 0, read);
                        } else {
                            a(bufferedInputStream);
                            a(bufferedOutputStream);
                            return str3;
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        a(bufferedInputStream);
                        a(bufferedOutputStream);
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        closeable = bufferedOutputStream;
                        a(bufferedInputStream);
                        a(closeable);
                        throw th;
                    }
                }
            } catch (IOException e3) {
                e = e3;
                bufferedOutputStream = null;
                e.printStackTrace();
                a(bufferedInputStream);
                a(bufferedOutputStream);
                return null;
            } catch (Throwable th3) {
                th = th3;
                a(bufferedInputStream);
                a(closeable);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            bufferedOutputStream = null;
            bufferedInputStream = null;
            e.printStackTrace();
            a(bufferedInputStream);
            a(bufferedOutputStream);
            return null;
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
            a(bufferedInputStream);
            a(closeable);
            throw th;
        }
    }

    private static void c(String str, String str2) {
        IOException e;
        Throwable th;
        Closeable closeable = null;
        AssetManager assets = BaseApplication.e().getResources().getAssets();
        Closeable bufferedInputStream;
        Closeable bufferedOutputStream;
        try {
            e.e(str2);
            bufferedInputStream = new BufferedInputStream(assets.open(str));
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read != -1) {
                            bufferedOutputStream.write(bArr, 0, read);
                        } else {
                            a(bufferedInputStream);
                            a(bufferedOutputStream);
                            return;
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    closeable = bufferedInputStream;
                    try {
                        e.printStackTrace();
                        a(closeable);
                        a(bufferedOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedInputStream = closeable;
                        closeable = bufferedOutputStream;
                        a(bufferedInputStream);
                        a(closeable);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    closeable = bufferedOutputStream;
                    a(bufferedInputStream);
                    a(closeable);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                bufferedOutputStream = null;
                closeable = bufferedInputStream;
                e.printStackTrace();
                a(closeable);
                a(bufferedOutputStream);
            } catch (Throwable th4) {
                th = th4;
                a(bufferedInputStream);
                a(closeable);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            bufferedOutputStream = null;
            e.printStackTrace();
            a(closeable);
            a(bufferedOutputStream);
        } catch (Throwable th5) {
            th = th5;
            bufferedInputStream = null;
            a(bufferedInputStream);
            a(closeable);
            throw th;
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
