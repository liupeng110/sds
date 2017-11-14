package com.sds.android.ttpod.framework.a;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.framework.modules.theme.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* BackgroundCreateUtils */
public class b {
    private static Context a;

    public static void a(Context context) {
        a = context;
    }

    public static Bitmap a(a aVar) {
        return a(aVar, 3);
    }

    public static Bitmap a(a aVar, int i) {
        Bitmap bitmap = null;
        float d = (float) (com.sds.android.ttpod.common.c.a.d() / i);
        float e = (float) (com.sds.android.ttpod.common.c.a.e() / i);
        InputStream c = c(aVar);
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        options.inInputShareable = true;
        BitmapFactory.decodeStream(c, bitmap, options);
        if (((float) options.outWidth) >= d || ((float) options.outHeight) >= e) {
            options.inSampleSize = (int) Math.max(((float) options.outWidth) / d, ((float) options.outHeight) / e);
            options.outWidth = (int) d;
            options.outHeight = (int) e;
        }
        options.inJustDecodeBounds = false;
        try {
            if (aVar.a() == a.a.ADD_BY_USER) {
                bitmap = BitmapFactory.decodeFile(com.sds.android.ttpod.framework.a.p() + File.separator + aVar.b(), options);
            } else {
                bitmap = BitmapFactory.decodeStream(c, null, options);
            }
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    private static InputStream c(a aVar) {
        if (a.a.ORIGINAL == aVar.a()) {
            return a(a.getAssets(), aVar.b());
        }
        if (a.a.ADD_BY_USER == aVar.a()) {
            return a(aVar.b());
        }
        return null;
    }

    private static InputStream a(AssetManager assetManager, String str) {
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(com.sds.android.ttpod.framework.a.q() + File.separator + str, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    private static InputStream a(String str) {
        try {
            return new FileInputStream(com.sds.android.ttpod.framework.a.p() + File.separator + str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void b(a aVar) {
        File file = new File(com.sds.android.ttpod.framework.a.p() + File.separator + aVar.b());
        if (file.exists()) {
            String str = com.sds.android.ttpod.framework.a.k() + File.separator + com.sds.android.sdk.lib.util.k.b.a(String.valueOf(file.lastModified()));
            if (e.a(str)) {
                new File(str).delete();
            }
            file.delete();
        }
        Bitmap f = aVar.f();
        if (!(f == null || f.isRecycled())) {
            f.recycle();
        }
        Drawable e = aVar.e();
        if (e != null && (e instanceof BitmapDrawable)) {
            f = ((BitmapDrawable) e).getBitmap();
            if (f != null && !f.isRecycled()) {
                f.recycle();
            }
        }
    }
}
