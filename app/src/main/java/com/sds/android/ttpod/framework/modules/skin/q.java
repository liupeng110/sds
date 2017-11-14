package com.sds.android.ttpod.framework.modules.skin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.common.c.a;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.modules.skin.b.x;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* SkinThumbnailCreator */
public class q extends p implements Runnable {
    public static final int a = (a.d() >> 2);
    public static final int c = ((int) ((((float) a) * 1.5555556f) + TTFMImageUtils.Middle_Scale));
    private static final String[] d = new String[]{"/Preview", "/Preview.jpg", "/Preview.png", "/Background", "/Background.jpg", "/Background.png"};
    private m e = null;

    q(m mVar) {
        this.e = mVar;
    }

    private static boolean a(String str, String str2) {
        OutputStream fileOutputStream;
        BufferedInputStream bufferedInputStream;
        Exception e;
        BufferedOutputStream bufferedOutputStream;
        Throwable th;
        BufferedOutputStream bufferedOutputStream2;
        boolean z = false;
        BufferedInputStream bufferedInputStream2 = null;
        com.sds.android.sdk.lib.a.a.a a = com.sds.android.sdk.lib.a.a.a(str, null, null);
        if (a != null && a.c() == 200) {
            try {
                fileOutputStream = new FileOutputStream(str2);
                try {
                    bufferedInputStream = new BufferedInputStream(a.e(), 8192);
                } catch (Exception e2) {
                    e = e2;
                    bufferedOutputStream = null;
                    try {
                        e.printStackTrace();
                        try {
                            a.e().close();
                            if (bufferedInputStream2 != null) {
                                bufferedInputStream2.close();
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream2 = bufferedOutputStream;
                        try {
                            a.e().close();
                            if (bufferedInputStream2 != null) {
                                bufferedInputStream2.close();
                            }
                            if (bufferedOutputStream2 != null) {
                                bufferedOutputStream2.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Exception e32) {
                            e32.printStackTrace();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream2 = null;
                    a.e().close();
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
                try {
                    bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream, 8192);
                    int i = 0;
                    while (true) {
                        try {
                            int read = bufferedInputStream.read();
                            if (read < 0) {
                                if (i >= 10) {
                                    break;
                                }
                                i++;
                            } else {
                                bufferedOutputStream2.write(read);
                            }
                        } catch (Exception e4) {
                            e32 = e4;
                            bufferedInputStream2 = bufferedInputStream;
                            bufferedOutputStream = bufferedOutputStream2;
                        } catch (Throwable th4) {
                            th = th4;
                            bufferedInputStream2 = bufferedInputStream;
                        }
                    }
                    z = true;
                    try {
                        a.e().close();
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (bufferedOutputStream2 != null) {
                            bufferedOutputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Exception e322) {
                        e322.printStackTrace();
                    }
                } catch (Exception e5) {
                    e322 = e5;
                    BufferedInputStream bufferedInputStream3 = bufferedInputStream;
                    bufferedOutputStream = null;
                    bufferedInputStream2 = bufferedInputStream3;
                    e322.printStackTrace();
                    a.e().close();
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return z;
                } catch (Throwable th5) {
                    th = th5;
                    bufferedOutputStream2 = null;
                    bufferedInputStream2 = bufferedInputStream;
                    a.e().close();
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e322 = e6;
                fileOutputStream = null;
                bufferedOutputStream = null;
                e322.printStackTrace();
                a.e().close();
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return z;
            } catch (Throwable th6) {
                th = th6;
                fileOutputStream = null;
                bufferedOutputStream2 = null;
                a.e().close();
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }
        return z;
    }

    public void run() {
        Bitmap a = g.a(this.e.b(), a, c);
        if ((a == null && 4 == this.e.a()) || 3 == this.e.a()) {
            OnlineSkinItem f = this.e.f();
            if (f != null) {
                a(f);
                return;
            }
            return;
        }
        boolean e;
        if (this.e.a == 0) {
            e = e(this.e.c);
        } else {
            e = a(a(this.e.a, this.e.c));
        }
        if (e && a()) {
            if (a == null) {
                b();
            }
            a(this.e);
        }
        j();
    }

    private boolean a() {
        x c = c();
        if (c == null) {
            return false;
        }
        this.e.b = c.d();
        return true;
    }

    private boolean b() {
        Bitmap a;
        for (String b : d) {
            byte[] b2;
            try {
                b2 = this.b.b(b);
            } catch (IOException e) {
                e.printStackTrace();
                b2 = null;
            }
            if (b2 != null) {
                a = a(b2);
                break;
            }
        }
        a = null;
        if (a == null) {
            return false;
        }
        g.a(this.e.b(), a, c, a);
        return true;
    }

    private void a(OnlineSkinItem onlineSkinItem) {
        String str = com.sds.android.ttpod.framework.a.k() + File.separator + e.j(onlineSkinItem.getPictureUrl());
        if (a(onlineSkinItem.getPictureUrl(), str)) {
            Bitmap decodeFile = BitmapFactory.decodeFile(str);
            if (decodeFile != null) {
                g.a(this.e.b(), a, c, decodeFile);
            }
        }
        a(this.e);
    }

    /* JADX err: Inconsistent code. */
    private com.sds.android.ttpod.framework.modules.skin.b.x c() {
        /*
        r3 = this;
        r0 = 0;
        r1 = r3.k();
        if (r1 == 0) goto L_0x000e;
    L_0x0007:
        r0 = com.sds.android.ttpod.framework.modules.skin.b.aa.a(r1);	 Catch:{ Exception -> 0x0014 }
        r1.close();	 Catch:{ Exception -> 0x000f }
    L_0x000e:
        return r0;
    L_0x000f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000e;
    L_0x0014:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x0021 }
        r1.close();	 Catch:{ Exception -> 0x001c }
        goto L_0x000e;
    L_0x001c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000e;
    L_0x0021:
        r0 = move-exception;
        r1.close();	 Catch:{ Exception -> 0x0026 }
    L_0x0025:
        throw r0;
    L_0x0026:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.framework.modules.skin.q.c():com.sds.android.ttpod.framework.modules.skin.b.x");
    }

    private Bitmap a(byte[] bArr) {
        int i = a;
        int i2 = c;
        Bitmap bitmap = null;
        if (bArr != null) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            options.inPurgeable = true;
            options.inInputShareable = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (i <= 0 || i2 <= 0) {
                options.inSampleSize = 4;
            } else if (options.outWidth >= (i2 << 1) || options.outHeight >= (i << 1)) {
                options.inSampleSize = Math.max(options.outWidth / i2, options.outHeight / i);
            }
            o.logD("SkinDecoder.decodeByteArrayBitmap-----inSampleSize: " + options.inSampleSize + " options.outWidth: " + options.outWidth + " options.outHeight: " + options.outHeight);
            options.inJustDecodeBounds = false;
            try {
                bitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return bitmap;
    }

    private void a(m mVar) {
        o.logD("SkinThumbnailCreator.notifySkinThumbnailCreated--->");
        b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DECODE_SKIN_THUMBNAIL_FINISHED, mVar), c.SKIN);
    }
}
