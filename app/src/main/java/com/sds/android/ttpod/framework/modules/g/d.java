package com.sds.android.ttpod.framework.modules.g;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.sds.android.cloudapi.ttpod.a.ab;
import com.sds.android.cloudapi.ttpod.data.SplashItem;
import com.sds.android.cloudapi.ttpod.result.SplashDataResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.sdk.lib.util.p;
import com.sds.android.ttpod.framework.a.b.v;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* SplashModule */
public final class d extends b {
    private long a;

    private boolean a(String str, String str2) {
        return false;
    }

    protected c id() {
        return c.SPLASH;
    }

    public long timeOutInMills() {
        return 15000;
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(a.LOAD_SPLASH, i.a(cls, "loadSplash", Integer.class, Integer.class));
        map.put(a.SET_AUDIO_ENABLED, i.a(cls, "setAudioEnabled", Boolean.class));
    }

    public void onCreate() {
        super.onCreate();
    }

    public void loadSplash(final Integer num, final Integer num2) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ d c;

            public void run() {
                Bitmap b;
                long j;
                int i;
                Object obj;
                Bitmap bitmap;
                long j2 = 1500;
                this.c.a(num2);
                SplashDataResult c = com.sds.android.ttpod.framework.storage.a.a.a().c();
                String str;
                if (c != null) {
                    c a = c.a(c);
                    int a2 = a.a();
                    SplashItem b2 = a.b();
                    if (b2 != null) {
                        String a3 = this.c.c(b2.getSuitFile(com.sds.android.ttpod.common.c.a.i()));
                        if (!e.d(a3)) {
                            a2 = 0;
                        }
                        String str2 = a3 + File.separator + "background.jpg";
                        a3 = a3 + File.separator + "index.html";
                        b = this.c.e(str2);
                        if (e.b(a3)) {
                            j2 = 4000;
                        }
                        String str3;
                        if (b != null && e.a(null) && com.sds.android.ttpod.framework.storage.environment.b.a()) {
                            this.c.a = System.currentTimeMillis();
                            this.c.a(null);
                            str3 = a3;
                            j = 10000;
                            i = a2;
                            obj = str3;
                            bitmap = b;
                        } else {
                            str3 = a3;
                            j = j2;
                            i = a2;
                            str = str3;
                            bitmap = b;
                        }
                    } else {
                        int i2 = a2;
                        bitmap = null;
                        j = 1500;
                        str = null;
                        i = i2;
                    }
                } else {
                    bitmap = null;
                    i = 0;
                    str = null;
                    j = 1500;
                }
                if (bitmap == null) {
                    bitmap = this.c.a(num.intValue());
                }
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_SPLASH, this.c.b(), bitmap, obj, Boolean.valueOf(false)), c.SPLASH);
                if (bitmap == null) {
                    j = b != null ? 200 : 0;
                }
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.FINISH_SPLASH, new Object[0]), c.SPLASH, (int) j);
                v.d();
                try {
                    Thread.sleep(20000);
                    this.c.b(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Bitmap a(int i) {
        Bitmap a = a();
        return a != null ? a : c(i);
    }

    private void b(int i) {
        if (EnvironmentUtils.c.d() == 2) {
            SplashDataResult splashDataResult = (SplashDataResult) ab.a(i).g();
            if (splashDataResult != null && 1 == splashDataResult.getCode()) {
                List c = c.a(splashDataResult).c();
                if (a(c)) {
                    com.sds.android.ttpod.framework.storage.a.a.a().a(splashDataResult);
                    b(c);
                }
            }
        }
    }

    private boolean a(List<SplashItem> list) {
        String b;
        Exception e;
        InputStream inputStream;
        Throwable th;
        FileInputStream fileInputStream;
        if (list != null) {
            int i = com.sds.android.ttpod.common.c.a.i();
            for (SplashItem suitFile : list) {
                String suitFile2 = suitFile.getSuitFile(i);
                b = b(suitFile2);
                if (!a(suitFile2, b)) {
                    return false;
                }
                try {
                    InputStream fileInputStream2 = new FileInputStream(b);
                    try {
                        p.a(fileInputStream2, c(suitFile2));
                        e.h(b);
                        try {
                            fileInputStream2.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } catch (Exception e3) {
                        e2 = e3;
                        inputStream = fileInputStream2;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = fileInputStream2;
                    }
                } catch (Exception e4) {
                    e2 = e4;
                    fileInputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = null;
                }
            }
        }
        return true;
        try {
            g.c("SplashModule", "downloadNewSplashInfo doUnZipFolder exception=%s", e2.toString());
            e.h(b);
            try {
                fileInputStream.close();
            } catch (Exception e22) {
                e22.printStackTrace();
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
            e.h(b);
            try {
                fileInputStream.close();
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            throw th;
        }
        return false;
    }

    private void a(Integer num) {
        String str = com.sds.android.ttpod.framework.a.l() + File.separator + "帮助.txt";
        if (!e.a(str)) {
            e.a(BaseApplication.e().getString(num.intValue()), str);
        }
    }

    private void b(List<SplashItem> list) {
        String str = com.sds.android.ttpod.framework.a.l() + File.separator;
        String[] list2 = new File(str).list();
        if (list2 != null) {
            Set hashSet = new HashSet();
            if (list != null) {
                for (SplashItem suitFile : list) {
                    hashSet.add(d(suitFile.getSuitFile(com.sds.android.ttpod.common.c.a.i())));
                }
            }
            hashSet.add("帮助.txt");
            hashSet.add(".nomedia");
            for (Object add : b.a) {
                hashSet.add(add);
            }
            for (String toLowerCase : list2) {
                String toLowerCase2 = toLowerCase2.toLowerCase();
                if (!hashSet.contains(toLowerCase2)) {
                    String str2 = str + toLowerCase2;
                    if (!toLowerCase2.startsWith(b.a[0]) || e.d(str2)) {
                        e.h(str2);
                    }
                }
            }
        }
    }

    private void a(String str) {
        a.a().a(new a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a() {
                long currentTimeMillis = System.currentTimeMillis() - this.a.a;
                if (currentTimeMillis < 1500) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.FINISH_SPLASH, new Object[0]), c.SPLASH, (int) (1500 - currentTimeMillis));
                } else {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.FINISH_SPLASH, new Object[0]));
                }
            }
        });
        a.a().a(str);
    }

    private Bitmap a() {
        Bitmap bitmap = null;
        for (String str : b.a) {
            String str2 = com.sds.android.ttpod.framework.a.l() + File.separator + str2;
            if (e.a(str2)) {
                bitmap = e(str2);
                if (bitmap != null) {
                    break;
                }
            }
        }
        return bitmap;
    }

    private String b(String str) {
        String j = e.j(str);
        return m.a(j) ? null : com.sds.android.ttpod.framework.a.l() + File.separator + j;
    }

    private String c(String str) {
        return com.sds.android.ttpod.framework.a.l() + File.separator + d(str);
    }

    private String d(String str) {
        String k = e.k(str);
        return m.a(k) ? null : k + "dir";
    }

    public void setAudioEnabled(Boolean bool) {
        com.sds.android.ttpod.framework.storage.environment.b.a(bool.booleanValue());
        if (!bool.booleanValue() && a.a().c()) {
            a.a().a(0.0f, 0.0f);
            a.a().b();
        }
    }

    private Bitmap c(int i) {
        if (i == 0) {
            return null;
        }
        return new com.sds.android.sdk.lib.util.b().a(BaseApplication.e().getResources(), i, com.sds.android.ttpod.common.c.a.d(), com.sds.android.ttpod.common.c.a.e());
    }

    private Bitmap e(String str) {
        return com.sds.android.sdk.lib.util.b.b(str, com.sds.android.ttpod.common.c.a.d(), com.sds.android.ttpod.common.c.a.e());
    }

    private Bitmap b() {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(BaseApplication.e().getResources().getAssets().open("channel_logo.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
