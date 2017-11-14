package com.sds.android.ttpod.framework.a;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.sds.android.sdk.core.a.b;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.k;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.base.BaseApplication;
import java.io.File;
import java.lang.ref.WeakReference;

/* ImageCacheUtils */
public final class g {
    private static b a;
    private static com.sds.android.sdk.lib.util.b b;

    /* ImageCacheUtils */
    public interface a {
        Bitmap a(Bitmap bitmap);
    }

    public static synchronized b a() {
        b bVar;
        synchronized (g.class) {
            if (a == null) {
                a = b.a(0.05f, com.sds.android.ttpod.framework.a.i());
            }
            b = new com.sds.android.sdk.lib.util.b();
            bVar = a;
        }
        return bVar;
    }

    public static String a(String str) {
        if (m.a(str)) {
            return "";
        }
        return com.sds.android.ttpod.framework.a.i() + File.separator + k.b.b(str);
    }

    public static b b() {
        return a;
    }

    public static final void a(ImageView imageView, String str, int i, int i2, int i3) {
        a(imageView, str, i, i2, i3, 0);
    }

    public static final void a(ImageView imageView, String str, int i, int i2, int i3, final int i4) {
        final WeakReference weakReference = new WeakReference(imageView);
        String a = b.a(str, i, i2, imageView.getScaleType());
        if (m.a(a)) {
            a((ImageView) weakReference.get(), i3);
            return;
        }
        Bitmap a2 = a(a, i, i2, null);
        ImageView imageView2 = (ImageView) weakReference.get();
        if (imageView2 != null) {
            imageView2.setTag(a);
            if (a2 == null) {
                a(imageView2, i3);
                a.a(a, i, i2, imageView2.getScaleType(), new com.sds.android.sdk.core.a.b.a() {
                    public void imageLoaded(String str, int i, int i2, Bitmap bitmap) {
                        ImageView imageView = (ImageView) weakReference.get();
                        if (imageView != null && imageView.getTag() != null && imageView.getTag().equals(str)) {
                            g.c(imageView, bitmap, i4);
                        }
                    }
                });
                return;
            }
            c(imageView2, a2, i4);
        }
    }

    public static void a(String str, String str2, int i, int i2) {
        a.a(str, i, i2);
        e.b(a(str), str2);
    }

    public static final Bitmap a(String str, int i, int i2, boolean z) {
        Bitmap b = b(str, i, i2, null);
        if (b == null && !z) {
            b = b.a(str, i, i2);
            if (!(b == null || a == null)) {
                a.a(str, null, i, i2, b);
            }
        }
        return b;
    }

    public static final void a(ImageView imageView, String str, int i, int i2) {
        if (!m.a(str)) {
            final WeakReference weakReference = new WeakReference(imageView);
            Bitmap a = a(str, i, i2, null);
            ImageView imageView2 = (ImageView) weakReference.get();
            if (imageView2 != null) {
                if (a != null) {
                    imageView2.setImageBitmap(a);
                    return;
                }
                imageView2.setTag(str);
                a.a(str, i, i2, new com.sds.android.sdk.core.a.b.a() {
                    public void imageLoaded(String str, int i, int i2, Bitmap bitmap) {
                        ImageView imageView = (ImageView) weakReference.get();
                        if (imageView != null && imageView.getTag() != null && imageView.getTag().equals(str)) {
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                });
            }
        }
    }

    public static final void a(String str, int i, int i2, Bitmap bitmap) {
        a.a(str, i, i2);
        a.a(str, null, i, i2, bitmap);
        com.sds.android.sdk.lib.util.b bVar = b;
        com.sds.android.sdk.lib.util.b.a(a(str), bitmap);
    }

    public static final Bitmap a(String str, int i, int i2) {
        return a(str, i, i2, null);
    }

    public static final void b(String str, int i, int i2) {
        a.a(str, i, i2);
    }

    private static void a(ImageView imageView, int i) {
        try {
            imageView.setImageResource(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void c(ImageView imageView, Bitmap bitmap, int i) {
        if (i != 0) {
            imageView.setAnimation(AnimationUtils.loadAnimation(imageView.getContext(), i));
        }
        imageView.setImageBitmap(bitmap);
    }

    public static final void b(ImageView imageView, String str, int i, int i2, int i3, final int i4) {
        ImageView imageView2;
        if (m.a(str)) {
            imageView2 = (ImageView) new WeakReference(imageView).get();
            if (imageView2 != null) {
                a(imageView2, i, i2, i3, i4);
                return;
            }
            return;
        }
        Bitmap a = a(str, i, i2, null);
        final WeakReference weakReference = new WeakReference(imageView);
        imageView2 = (ImageView) weakReference.get();
        if (imageView2 != null) {
            imageView2.setTag(str);
            if (a == null) {
                a.a(str, i, i2, new com.sds.android.sdk.core.a.b.a() {
                    public void imageLoaded(String str, int i, int i2, Bitmap bitmap) {
                        ImageView imageView = (ImageView) weakReference.get();
                        if (imageView != null && imageView.getTag() != null && imageView.getTag().equals(str)) {
                            g.d(imageView, bitmap, i4);
                            g.a.a(str, null, i, i2, bitmap);
                        }
                    }
                });
            } else {
                d(imageView2, a, i4);
            }
        }
    }

    public static final void a(final ImageView imageView, String str, int i, int i2, final a aVar) {
        if (!m.a(str)) {
            Bitmap a = a(str, i, i2, null);
            if (a != null) {
                imageView.setImageBitmap(c(a, aVar));
                return;
            }
            imageView.setTag(str);
            a.a(str, i, i2, new com.sds.android.sdk.core.a.b.a() {
                public void imageLoaded(String str, int i, int i2, Bitmap bitmap) {
                    if (m.a((String) imageView.getTag(), str)) {
                        imageView.setImageBitmap(g.c(bitmap, aVar));
                    }
                }
            });
        }
    }

    private static Bitmap c(Bitmap bitmap, a aVar) {
        return aVar != null ? aVar.a(bitmap) : bitmap;
    }

    private static void a(ImageView imageView, int i, int i2, int i3, int i4) {
        try {
            d(imageView, com.sds.android.sdk.lib.util.b.b(BaseApplication.e().getResources(), i3, i, i2), i4);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void d(final ImageView imageView, Bitmap bitmap, final int i) {
        if (imageView != null && bitmap != null) {
            com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<Bitmap, Bitmap>(bitmap) {
                protected /* synthetic */ Object onDoInBackground(Object obj) {
                    return a((Bitmap) obj);
                }

                protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                    b((Bitmap) obj);
                }

                protected Bitmap a(Bitmap bitmap) {
                    try {
                        return com.sds.android.sdk.lib.util.b.a(BaseApplication.e(), bitmap, i);
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                protected void b(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            });
        }
    }

    public static final void a(IconTextView iconTextView, String str, int i, int i2, final a aVar) {
        if (!m.a(str)) {
            final WeakReference weakReference = new WeakReference(iconTextView);
            Bitmap a = a(str, i, i2, null);
            IconTextView iconTextView2 = (IconTextView) weakReference.get();
            if (iconTextView2 != null) {
                if (a != null) {
                    iconTextView2.setImageDrawable(d(a, aVar));
                    return;
                }
                iconTextView2.setTag(str);
                a.a(str, i, i2, new com.sds.android.sdk.core.a.b.a() {
                    public void imageLoaded(String str, int i, int i2, Bitmap bitmap) {
                        IconTextView iconTextView = (IconTextView) weakReference.get();
                        if (iconTextView != null && iconTextView.getTag() != null && iconTextView.getTag().equals(str)) {
                            iconTextView.setImageDrawable(g.d(bitmap, aVar));
                        }
                    }
                });
            }
        }
    }

    private static Bitmap a(String str, int i, int i2, String str2) {
        Bitmap b = b(str, i, i2, str2);
        if (b == null) {
            return a.b(str, str2, i, i2);
        }
        return b;
    }

    private static Bitmap b(String str, int i, int i2, String str2) {
        if (a == null) {
            a();
        }
        return a.a(str, str2, i, i2);
    }

    private static BitmapDrawable d(Bitmap bitmap, a aVar) {
        if (aVar != null) {
            bitmap = aVar.a(bitmap);
        }
        return new BitmapDrawable(BaseApplication.e().getResources(), bitmap);
    }
}
