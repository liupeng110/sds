package com.sds.android.sdk.core.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.support.v4.util.LruCache;
import android.widget.ImageView.ScaleType;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.sdk.lib.util.n;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* ImageCache */
public final class b implements com.sds.android.sdk.core.a.c.a {
    private static b c;
    private LruCache<String, Bitmap> a;
    private File b;
    private d d = new d();

    /* ImageCache */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            try {
                a[ScaleType.FIT_XY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ScaleType.CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* ImageCache */
    public interface a {
        void imageLoaded(String str, int i, int i2, Bitmap bitmap);
    }

    public static synchronized b a(float f, String str) {
        b bVar;
        synchronized (b.class) {
            if (c == null) {
                c = new b(f, str);
                bVar = c;
            } else {
                throw new IllegalStateException("ImageCache already existed!");
            }
        }
        return bVar;
    }

    public Bitmap a(String str, String str2, int i, int i2) {
        return (Bitmap) this.a.get(d(str, str2, i, i2));
    }

    public void a(String str, String str2, int i, int i2, Bitmap bitmap) {
        String d = d(str, str2, i, i2);
        if (d != null && bitmap != null) {
            this.a.put(d, bitmap);
        }
    }

    public Bitmap b(String str, String str2, int i, int i2) {
        InputStream fileInputStream;
        Bitmap b;
        FileNotFoundException e;
        Throwable th;
        InputStream inputStream = null;
        try {
            fileInputStream = new FileInputStream(this.b.getAbsolutePath() + File.separator + a(str, str2));
            try {
                b = b(null, fileInputStream, i, i2, null);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                            b = null;
                        }
                    }
                    b = null;
                    a(str, str2, i, i2, b);
                    return b;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = fileInputStream;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            fileInputStream = null;
            e.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            b = null;
            a(str, str2, i, i2, b);
            return b;
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
        a(str, str2, i, i2, b);
        return b;
    }

    public void c(String str, String str2, int i, int i2) {
        if (!m.a(str)) {
            this.a.remove(d(str, str2, i, i2));
            e.h(this.b.getAbsolutePath() + File.separator + a(str, str2));
        }
    }

    public void a(String str, int i, int i2) {
        c(str, null, i, i2);
    }

    public void a() {
        this.d.b();
        this.a.evictAll();
        c = null;
    }

    public String b() {
        return this.b.getAbsolutePath();
    }

    private void a(String str, String str2, int i, int i2, ScaleType scaleType, a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Callback must not be null");
        } else if (!this.d.a() && !m.a(str)) {
            this.d.a(new c(new e(str, this.b.getAbsolutePath(), str2, i, i2, scaleType, aVar), this));
        }
    }

    public void a(String str, int i, int i2, a aVar) {
        a(str, null, i, i2, null, aVar);
    }

    public void a(String str, int i, int i2, ScaleType scaleType, a aVar) {
        a(str, null, i, i2, scaleType, aVar);
    }

    public void c() {
        this.a.evictAll();
    }

    private b(float f, String str) {
        if (f < 0.05f || f > 0.8f) {
            throw new IllegalArgumentException("setMemCacheSizePercent - percent must be between0.05and0.8 (inclusive)");
        }
        this.b = e.f(str);
        if (this.b == null) {
            this.b = new File("");
        }
        a(Math.round(((float) Runtime.getRuntime().maxMemory()) * f));
    }

    private void a(int i) {
        if (!j.d()) {
            i = 1024;
        }
        this.a = new LruCache<String, Bitmap>(this, i / 1024) {
            final /* synthetic */ b a;

            protected /* synthetic */ void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
                a(z, (String) obj, (Bitmap) obj2, (Bitmap) obj3);
            }

            protected /* synthetic */ int sizeOf(Object obj, Object obj2) {
                return a((String) obj, (Bitmap) obj2);
            }

            protected void a(boolean z, String str, Bitmap bitmap, Bitmap bitmap2) {
            }

            protected int a(String str, Bitmap bitmap) {
                int a = com.sds.android.sdk.lib.util.b.a(bitmap) / 1024;
                return a == 0 ? 1 : a;
            }
        };
    }

    public Bitmap a(String str, InputStream inputStream, int i, int i2, ScaleType scaleType) {
        return b(str, inputStream, i, i2, scaleType);
    }

    public void a(e eVar) {
        if (eVar.g() == null) {
            throw new IllegalArgumentException("bitmap must not be null!");
        }
        String b = eVar.b();
        a(b, eVar.c(), eVar.e(), eVar.f(), eVar.g());
        eVar.a().imageLoaded(b, eVar.e(), eVar.f(), eVar.g());
    }

    private Bitmap b(String str, InputStream inputStream, int i, int i2, ScaleType scaleType) {
        if (inputStream != null) {
            try {
                byte[] a = a(inputStream);
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(a, 0, a.length, options);
                options.inSampleSize = com.sds.android.sdk.lib.util.b.b(options, i, i2);
                options.inJustDecodeBounds = false;
                options.inPurgeable = true;
                options.inInputShareable = true;
                if (m.a("image/jpeg", options.outMimeType)) {
                    options.inPreferredConfig = Config.RGB_565;
                }
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(a, 0, a.length, options);
                g.c("ImageCache", "scaleType:" + scaleType);
                if (scaleType == null || i <= 0 || i2 <= 0) {
                    return decodeByteArray;
                }
                if (decodeByteArray.getWidth() <= i && decodeByteArray.getHeight() <= i2) {
                    return decodeByteArray;
                }
                if (scaleType == ScaleType.FIT_XY) {
                    return com.sds.android.sdk.lib.util.b.b(decodeByteArray, i, i2, true);
                }
                if (scaleType == ScaleType.FIT_CENTER) {
                    return com.sds.android.sdk.lib.util.b.c(decodeByteArray, i, i2, true);
                }
                if (scaleType == ScaleType.CENTER_CROP) {
                    return com.sds.android.sdk.lib.util.b.d(decodeByteArray, i, i2, true);
                }
                return decodeByteArray;
            } catch (Throwable th) {
                th.printStackTrace();
                System.gc();
            }
        }
        return null;
    }

    private byte[] a(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.close();
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    static String d(String str, String str2, int i, int i2) {
        if (i >= 0 && i2 >= 0) {
            return a(str, str2) + i + i2;
        }
        throw new IllegalArgumentException("width and height must be > 0!!");
    }

    private static String a(String str, String str2) {
        return m.a(str2) ? com.sds.android.sdk.lib.util.k.b.b(str) : str2;
    }

    public static String a(String str, int i, int i2, ScaleType scaleType) {
        if (!m.a(str) && str.startsWith("http://3p.pic.ttdtweb.com") && i > 0 && i2 > 0 && i <= 4096 && i2 <= 4096) {
            String a = n.a(str);
            String substring = str.substring(a.length());
            int b = b(i);
            int b2 = b(i2);
            String str2 = "1x." + e.m(str).toLowerCase();
            String str3 = "";
            switch (AnonymousClass2.a[scaleType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    str3 = str3 + b + "w_" + b2 + "h_" + "1c_" + "1e_";
                    break;
                case 4:
                    str3 = str3 + b + "w_" + b2 + "h_" + "1c_" + "0e_";
                    break;
                case 5:
                    str3 = str3 + b + "w_" + b2 + "h_" + "1c_" + "1i_";
                    break;
            }
            if (!m.a(str3)) {
                str = a + "@" + str3 + str2;
            }
            str = str + substring;
        }
        g.a("ImageCache", "buildCropUrl url = " + str);
        return str;
    }

    private static int b(int i) {
        return ((i + 2) / 5) * 5;
    }
}
