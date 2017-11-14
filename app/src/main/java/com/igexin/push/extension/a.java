package com.igexin.push.extension;

import android.content.Context;
import com.igexin.push.a.j;
import com.igexin.push.a.k;
import com.igexin.push.core.bean.e;
import com.igexin.push.core.g;
import com.igexin.push.extension.stub.IPushExtension;
import dalvik.system.DexClassLoader;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class a {
    private static String a = j.a;
    private static a c;
    private List b = new ArrayList();

    private a() {
    }

    public static a a() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    public void a(File file, File file2, String str) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream;
        FileOutputStream fileOutputStream2;
        FileInputStream fileInputStream2;
        Throwable th;
        Throwable th2;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                } catch (Exception e) {
                    bufferedOutputStream = null;
                    fileOutputStream2 = fileOutputStream;
                    fileInputStream2 = fileInputStream;
                    try {
                        if (file2.exists()) {
                            file2.delete();
                        }
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Exception e2) {
                                return;
                            }
                        }
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        if (fileOutputStream2 == null) {
                            fileOutputStream2.close();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream = fileInputStream2;
                        fileOutputStream = fileOutputStream2;
                        bufferedOutputStream2 = bufferedOutputStream;
                        th2 = th;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e3) {
                                throw th2;
                            }
                        }
                        if (bufferedOutputStream2 != null) {
                            bufferedOutputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th2;
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th2;
                }
            } catch (Exception e4) {
                bufferedOutputStream = null;
                fileInputStream2 = fileInputStream;
                if (file2.exists()) {
                    file2.delete();
                }
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (fileOutputStream2 == null) {
                    fileOutputStream2.close();
                }
            } catch (Throwable th5) {
                th2 = th5;
                fileOutputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th2;
            }
            try {
                Object obj = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(obj);
                    if (read == -1) {
                        break;
                    }
                    byte[] bArr = new byte[read];
                    System.arraycopy(obj, 0, bArr, 0, read);
                    bufferedOutputStream.write(com.igexin.a.a.a.a.a(bArr, str));
                }
                fileInputStream.close();
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                fileOutputStream.close();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e5) {
                        return;
                    }
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e6) {
                fileOutputStream2 = fileOutputStream;
                fileInputStream2 = fileInputStream;
                if (file2.exists()) {
                    file2.delete();
                }
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (fileOutputStream2 == null) {
                    fileOutputStream2.close();
                }
            } catch (Throwable th6) {
                th = th6;
                bufferedOutputStream2 = bufferedOutputStream;
                th2 = th;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th2;
            }
        } catch (Exception e7) {
            bufferedOutputStream = null;
            fileInputStream2 = null;
            if (file2.exists()) {
                file2.delete();
            }
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (fileOutputStream2 == null) {
                fileOutputStream2.close();
            }
        } catch (Throwable th7) {
            th2 = th7;
            fileOutputStream = null;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th2;
        }
    }

    public boolean a(Context context) {
        try {
            if (k.x != null) {
                Map b = k.x.b();
                List<Integer> arrayList = new ArrayList();
                for (Entry entry : b.entrySet()) {
                    int intValue = ((Integer) entry.getKey()).intValue();
                    e eVar = (e) entry.getValue();
                    String str = g.ad + "/" + eVar.c();
                    File file = new File(str);
                    if (file.exists()) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (eVar.h() == 0 || eVar.i() + eVar.h() >= currentTimeMillis) {
                            if (a(context, str, eVar.d(), eVar.j(), eVar.c()) && eVar.i() != 0) {
                                eVar.b(currentTimeMillis);
                            }
                            if (eVar.g()) {
                                file.delete();
                                arrayList.add(Integer.valueOf(intValue));
                            }
                        } else {
                            file.delete();
                            arrayList.add(Integer.valueOf(intValue));
                        }
                    } else {
                        arrayList.add(Integer.valueOf(intValue));
                    }
                }
                if (arrayList != null && arrayList.size() > 0) {
                    for (Integer intValue2 : arrayList) {
                        b.remove(Integer.valueOf(intValue2.intValue()));
                    }
                    com.igexin.push.a.a.a().g();
                }
            } else {
                List<String> arrayList2 = new ArrayList();
                arrayList2.add("com.igexin.push.extension.distribution.basic.stub.PushExtension");
                arrayList2.add("com.igexin.push.extension.distribution.gbd.stub.PushExtension");
                for (String loadClass : arrayList2) {
                    try {
                        IPushExtension iPushExtension = (IPushExtension) context.getClassLoader().loadClass(loadClass).newInstance();
                        iPushExtension.init(g.i);
                        this.b.add(iPushExtension);
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e2) {
        }
        return true;
    }

    public boolean a(Context context, String str, String str2, String str3, String str4) {
        Class cls = null;
        File file = new File(str);
        File file2 = new File(str + ".jar");
        File file3 = new File(context.getFilesDir().getAbsolutePath() + "/" + str4 + ".dex");
        a(file, file2, str3);
        if (file2.exists()) {
            try {
                try {
                    cls = new DexClassLoader(file2.getAbsolutePath(), context.getFilesDir().getAbsolutePath(), null, context.getClassLoader()).loadClass(str2);
                } catch (Exception e) {
                }
                file2.delete();
                if (file3.exists()) {
                    file3.delete();
                }
                if (cls == null) {
                    return false;
                }
                IPushExtension iPushExtension = (IPushExtension) cls.newInstance();
                if (iPushExtension != null) {
                    iPushExtension.init(g.i);
                    this.b.add(iPushExtension);
                    return true;
                }
            } catch (Exception e2) {
                if (file2.exists()) {
                    file2.delete();
                }
                if (file3.exists()) {
                    file3.delete();
                }
            }
        }
        return false;
    }

    public void b() {
        for (IPushExtension onDestroy : this.b) {
            onDestroy.onDestroy();
        }
    }

    public List c() {
        return this.b;
    }
}
