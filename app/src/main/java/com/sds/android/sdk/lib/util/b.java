package com.sds.android.sdk.lib.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Locale;

/* BitmapUtils */
public class b {
    private Options a = b();
    private Options b;
    private boolean c = j.e();
    private boolean d = false;

    public void a(boolean z) {
        this.c = z;
    }

    private Options b() {
        Options options = new Options();
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inDither = this.c;
        return options;
    }

    private void b(boolean z) {
        if (z && this.b != null) {
            this.b.requestCancelDecode();
        }
        this.b = c();
        this.d = false;
    }

    private Options c() {
        Options options = new Options();
        options.inDither = this.a.inDither;
        options.inJustDecodeBounds = this.a.inJustDecodeBounds;
        options.inPreferredConfig = this.a.inPreferredConfig;
        options.inDensity = this.a.inDensity;
        options.inInputShareable = this.a.inInputShareable;
        options.inTempStorage = this.a.inTempStorage;
        options.inTargetDensity = this.a.inTargetDensity;
        options.inScreenDensity = this.a.inScreenDensity;
        options.inSampleSize = this.a.inSampleSize;
        options.inPurgeable = this.a.inPurgeable;
        return options;
    }

    public Options a() {
        return this.a;
    }

    public Bitmap a(String str) {
        Bitmap bitmap = null;
        if (!TextUtils.isEmpty(str)) {
            b(false);
            if (d()) {
                this.b.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str, this.b);
                a(this.b);
                this.b.inJustDecodeBounds = false;
            }
            try {
                bitmap = b(BitmapFactory.decodeFile(str, this.b));
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public Bitmap a(String str, int i, int i2) {
        return a(str, i, i2, false);
    }

    public Bitmap a(String str, int i, int i2, boolean z) {
        try {
            if (!TextUtils.isEmpty(str) && i > 0 && i2 > 0) {
                b(z);
                this.b.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str, this.b);
                if (a(this.b, i, i2)) {
                    if (d()) {
                        a(this.b);
                    }
                    g.a("BitmapUtils", "decodeBitmap, filePath: " + str);
                    return b(BitmapFactory.decodeFile(str, this.b));
                }
            }
        } catch (OutOfMemoryError e) {
            g.a("BitmapUtils", "decodeBitmap OutOfMemoryError filePath=" + str);
        }
        return null;
    }

    private Bitmap b(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        if (VERSION.SDK_INT <= 11) {
            return bitmap;
        }
        bitmap.setHasAlpha(!b(this.b));
        return bitmap;
    }

    private boolean d() {
        return this.b.inPreferredConfig == Config.ARGB_8888 && !this.c;
    }

    public static boolean a(Options options, int i, int i2) {
        if (!options.inJustDecodeBounds || options.outHeight <= 0 || options.outWidth <= 0) {
            return false;
        }
        if (options.outWidth > (i << 1) || options.outHeight > (i2 << 1)) {
            options.inSampleSize = Math.max(options.outWidth / i, options.outHeight / i2);
        }
        options.inJustDecodeBounds = false;
        return true;
    }

    public static boolean a(Options options) {
        if (TextUtils.isEmpty(options.outMimeType)) {
            return false;
        }
        if (b(options)) {
            options.inPreferredConfig = Config.RGB_565;
            options.inDither = false;
        } else {
            options.inPreferredConfig = Config.ARGB_8888;
        }
        return true;
    }

    public static boolean b(Options options) {
        Object obj = options.outMimeType;
        return (TextUtils.isEmpty(obj) || obj.toLowerCase(Locale.US).endsWith("png")) ? false : true;
    }

    public Bitmap a(Resources resources, int i) {
        if (i != 0) {
            b(false);
            if (d()) {
                this.b.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(resources, i, this.b);
                a(this.b);
                this.b.inJustDecodeBounds = false;
            }
            try {
                return b(BitmapFactory.decodeResource(resources, i, this.b));
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Bitmap a(Resources resources, int i, int i2, int i3) {
        if (i != 0 && i3 > 0 && i2 > 0) {
            b(false);
            this.b.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(resources, i, this.b);
            if (a(this.b, i2, i3)) {
                if (d()) {
                    a(this.b);
                }
                try {
                    return b(BitmapFactory.decodeResource(resources, i, this.b));
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public Bitmap a(InputStream inputStream) {
        Bitmap bitmap = null;
        if (inputStream != null) {
            if (!this.d) {
                b(false);
                if (d()) {
                    this.b.inJustDecodeBounds = true;
                    BitmapFactory.decodeStream(inputStream, bitmap, this.b);
                    a(this.b);
                    this.b.inJustDecodeBounds = false;
                }
            }
            try {
                bitmap = b(BitmapFactory.decodeStream(inputStream, null, this.b));
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        if (bitmap != null) {
            int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
            float min2 = ((float) Math.min(min, i)) / ((float) min);
            Matrix matrix = new Matrix();
            matrix.setScale(min2, min2);
            g.a("BitmapUtils", String.format("cropBitmapToSquare bitmapW=%d H=%d squareLen=%d scale=%f", new Object[]{Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), Integer.valueOf(i), Float.valueOf(min2)}));
            try {
                Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, min, min, matrix, true);
                if (!j.d()) {
                    return createBitmap;
                }
                createBitmap.setHasAlpha(bitmap.hasAlpha());
                return createBitmap;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static Bitmap a(String str, int i) {
        Bitmap b = b(str, i, i);
        if (b == null) {
            return null;
        }
        Bitmap a = a(b, i);
        if (a == b) {
            return a;
        }
        b.recycle();
        return a;
    }

    public static Bitmap b(Resources resources, int i, int i2, int i3) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = b(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static Bitmap a(Context context, Bitmap bitmap, int i) {
        return b(bitmap, i);
    }

    private static Bitmap b(Bitmap bitmap, int i) {
        if (i < 1 || bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        return Bitmap.createBitmap(c(bitmap, i), 0, width, width, height, bitmap.getConfig());
    }

    private static int[] c(Bitmap bitmap, int i) {
        int i2;
        int i3;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int i4 = width - 1;
        int i5 = height - 1;
        int i6 = width * height;
        int i7 = (i + i) + 1;
        int[] iArr2 = new int[i6];
        int[] iArr3 = new int[i6];
        int[] iArr4 = new int[i6];
        int[] iArr5 = new int[Math.max(width, height)];
        i6 = (i7 + 1) >> 1;
        int i8 = i6 * i6;
        int[] iArr6 = new int[(i8 * 256)];
        int i9 = 0;
        int i10 = 0;
        while (i9 < 256) {
            for (i6 = 0; i6 < i8; i6++) {
                iArr6[i10 + i6] = i9;
            }
            i9++;
            i10 += i8;
        }
        int i11 = 0;
        int[][] iArr7 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i7, 3});
        int i12 = i + 1;
        int i13 = 0;
        for (i2 = 0; i2 < height; i2++) {
            int i14;
            i8 = 0;
            i9 = 0;
            i10 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            for (i14 = -i; i14 <= i; i14++) {
                i3 = iArr[Math.min(i4, Math.max(i14, 0)) + i11];
                int[] iArr8 = iArr7[i14 + i];
                iArr8[0] = (16711680 & i3) >> 16;
                iArr8[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i3) >> 8;
                iArr8[2] = i3 & MotionEventCompat.ACTION_MASK;
                i3 = i12 - Math.abs(i14);
                i18 += iArr8[0] * i3;
                i19 += iArr8[1] * i3;
                i20 += i3 * iArr8[2];
                if (i14 > 0) {
                    i8 += iArr8[0];
                    i9 += iArr8[1];
                    i10 += iArr8[2];
                } else {
                    i15 += iArr8[0];
                    i16 += iArr8[1];
                    i17 += iArr8[2];
                }
            }
            i3 = i18;
            i18 = i19;
            i19 = i20;
            i14 = i11;
            i11 = i;
            for (i20 = 0; i20 < width; i20++) {
                iArr2[i14] = iArr6[i3];
                iArr3[i14] = iArr6[i18];
                iArr4[i14] = iArr6[i19];
                i3 -= i15;
                i18 -= i16;
                i19 -= i17;
                iArr8 = iArr7[((i11 - i) + i7) % i7];
                i15 -= iArr8[0];
                i16 -= iArr8[1];
                i17 -= iArr8[2];
                if (i2 == 0) {
                    iArr5[i20] = Math.min((i20 + i) + 1, i4);
                }
                int i21 = iArr[iArr5[i20] + i13];
                iArr8[0] = (16711680 & i21) >> 16;
                iArr8[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i21) >> 8;
                iArr8[2] = i21 & MotionEventCompat.ACTION_MASK;
                i8 += iArr8[0];
                i9 += iArr8[1];
                i10 += iArr8[2];
                i3 += i8;
                i18 += i9;
                i19 += i10;
                i11 = (i11 + 1) % i7;
                iArr8 = iArr7[i11 % i7];
                i15 += iArr8[0];
                i16 += iArr8[1];
                i17 += iArr8[2];
                i8 -= iArr8[0];
                i9 -= iArr8[1];
                i10 -= iArr8[2];
                i14++;
            }
            i13 += width;
            i11 = i14;
        }
        for (i20 = 0; i20 < width; i20++) {
            i17 = 0;
            i8 = 0;
            i9 = 0;
            i11 = 0;
            i15 = 0;
            i16 = 0;
            i10 = (-i) * width;
            i14 = 0;
            i19 = 0;
            i18 = 0;
            i3 = -i;
            while (i3 <= i) {
                i2 = Math.max(0, i10) + i20;
                int[] iArr9 = iArr7[i3 + i];
                iArr9[0] = iArr2[i2];
                iArr9[1] = iArr3[i2];
                iArr9[2] = iArr4[i2];
                int abs = i12 - Math.abs(i3);
                i13 = (iArr2[i2] * abs) + i18;
                i18 = (iArr3[i2] * abs) + i19;
                i19 = (iArr4[i2] * abs) + i14;
                if (i3 > 0) {
                    i17 += iArr9[0];
                    i8 += iArr9[1];
                    i9 += iArr9[2];
                } else {
                    i11 += iArr9[0];
                    i15 += iArr9[1];
                    i16 += iArr9[2];
                }
                if (i3 < i5) {
                    i10 += width;
                }
                i3++;
                i14 = i19;
                i19 = i18;
                i18 = i13;
            }
            i3 = i19;
            i13 = i18;
            i18 = i14;
            i14 = i20;
            i10 = i9;
            i9 = i8;
            i8 = i17;
            i17 = i16;
            i16 = i15;
            i15 = i11;
            i11 = i;
            for (i19 = 0; i19 < height; i19++) {
                iArr[i14] = (((ViewCompat.MEASURED_STATE_MASK & iArr[i14]) | (iArr6[i13] << 16)) | (iArr6[i3] << 8)) | iArr6[i18];
                i13 -= i15;
                i3 -= i16;
                i18 -= i17;
                int[] iArr10 = iArr7[((i11 - i) + i7) % i7];
                i15 -= iArr10[0];
                i16 -= iArr10[1];
                i17 -= iArr10[2];
                if (i20 == 0) {
                    iArr5[i19] = Math.min(i19 + i12, i5) * width;
                }
                i4 = iArr5[i19] + i20;
                iArr10[0] = iArr2[i4];
                iArr10[1] = iArr3[i4];
                iArr10[2] = iArr4[i4];
                i8 += iArr10[0];
                i9 += iArr10[1];
                i10 += iArr10[2];
                i13 += i8;
                i3 += i9;
                i18 += i10;
                i11 = (i11 + 1) % i7;
                iArr10 = iArr7[i11];
                i15 += iArr10[0];
                i16 += iArr10[1];
                i17 += iArr10[2];
                i8 -= iArr10[0];
                i9 -= iArr10[1];
                i10 -= iArr10[2];
                i14 += width;
            }
        }
        return iArr;
    }

    public static void a(String str, Bitmap bitmap) {
        Exception e;
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(new File(str));
            try {
                OutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                bitmap.compress(CompressFormat.PNG, 0, bufferedOutputStream);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    public static Bitmap a(Bitmap bitmap, boolean z) {
        Throwable th;
        int i = 0;
        if (bitmap == null) {
            return null;
        }
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        if (width > height) {
            i = (width - height) >> 1;
            width = i + height;
        } else {
            height = width;
        }
        Bitmap createBitmap;
        try {
            createBitmap = Bitmap.createBitmap(bitmap, i, 0, width, height);
            if (createBitmap == bitmap || !z) {
                return createBitmap;
            }
            try {
                bitmap.recycle();
                return createBitmap;
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return createBitmap;
            }
        } catch (Throwable th3) {
            th = th3;
            createBitmap = null;
            th.printStackTrace();
            return createBitmap;
        }
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2, boolean z) {
        Bitmap bitmap2 = null;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > height) {
                i = (width * i2) / height;
            } else {
                i2 = (height * i) / width;
            }
            try {
                bitmap2 = Bitmap.createScaledBitmap(bitmap, i, i2, false);
                if (bitmap2 != bitmap && z) {
                    bitmap.recycle();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return bitmap2;
    }

    public static Bitmap a(Bitmap bitmap, int i, boolean z) {
        Bitmap createBitmap;
        Throwable th;
        Config config = Config.ARGB_8888;
        if (!j.c()) {
            config = Config.ARGB_4444;
        }
        try {
            createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config);
            try {
                Canvas canvas = new Canvas(createBitmap);
                Paint paint = new Paint();
                Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                RectF rectF = new RectF(rect);
                paint.setAntiAlias(true);
                canvas.drawRoundRect(rectF, (float) i, (float) i, paint);
                paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
                canvas.drawBitmap(bitmap, null, rect, paint);
                if (createBitmap != bitmap && z) {
                    bitmap.recycle();
                }
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return createBitmap;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            createBitmap = null;
            th = th4;
            th.printStackTrace();
            return createBitmap;
        }
        return createBitmap;
    }

    public static Bitmap a(Drawable drawable, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, i, i2);
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap b(String str, int i, int i2) {
        Bitmap bitmap = null;
        if (!TextUtils.isEmpty(str)) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            options.inPurgeable = true;
            options.inInputShareable = true;
            BitmapFactory.decodeFile(str, options);
            options.inSampleSize = b(options, i, i2);
            options.inJustDecodeBounds = false;
            if (b(options)) {
                options.inPreferredConfig = Config.RGB_565;
            }
            try {
                bitmap = BitmapFactory.decodeFile(str, options);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return bitmap;
    }

    public static int b(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i <= 0 || i2 <= 0 || (i3 <= i2 && i4 <= i)) {
            i3 = 1;
        } else if (i4 > i3) {
            i3 = Math.round(((float) i3) / ((float) i2));
        } else {
            i3 = Math.round(((float) i4) / ((float) i));
        }
        if (i3 == 0) {
            return 1;
        }
        return i3;
    }

    public static int a(Bitmap bitmap) {
        if (bitmap != null) {
            return j.d() ? bitmap.getByteCount() : bitmap.getRowBytes() * bitmap.getHeight();
        } else {
            throw new IllegalArgumentException("bitmap must be not null!");
        }
    }

    public static void a(Bitmap bitmap, String str) {
        Exception e;
        Throwable th;
        d.a((Object) bitmap, "bitmap");
        d.a((Object) str, "savePath");
        e.h(str);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(str);
            try {
                bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e22 = e5;
            fileOutputStream = null;
            e22.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public static void a(ImageView imageView) {
        if (imageView != null) {
            int intrinsicWidth;
            Drawable drawable = imageView.getDrawable();
            int intrinsicHeight = drawable != null ? drawable.getIntrinsicHeight() : 0;
            if (drawable != null) {
                intrinsicWidth = drawable.getIntrinsicWidth();
            } else {
                intrinsicWidth = 0;
            }
            int width = imageView.getWidth();
            int height = imageView.getHeight();
            if (intrinsicHeight > 0 && intrinsicWidth > 0 && width > 0 && height > 0) {
                g.a("BitmapUtils", String.format("amendMatrixForCenterCrop tag=%s view=%d,%d drawable=%d,%d", new Object[]{imageView.getTag(), Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(intrinsicWidth), Integer.valueOf(intrinsicHeight)}));
                float f = (((float) width) * 1.0f) / ((float) intrinsicWidth);
                float f2 = (((float) height) * 1.0f) / ((float) intrinsicHeight);
                if (f2 >= f) {
                    imageView.setScaleType(ScaleType.CENTER_CROP);
                    g.a("BitmapUtils", String.format("use system center_crop %f %f", new Object[]{Float.valueOf(f), Float.valueOf(f2)}));
                    return;
                }
                imageView.setScaleType(ScaleType.MATRIX);
                float max = Math.max(f, f2);
                Matrix matrix = new Matrix();
                matrix.postScale(max, max);
                imageView.setImageMatrix(matrix);
                g.a("BitmapUtils", String.format("use my matrix %f %f scale=%f", new Object[]{Float.valueOf(f), Float.valueOf(f2), Float.valueOf(max)}));
            }
        }
    }

    public static Bitmap b(Bitmap bitmap, int i, int i2, boolean z) {
        Throwable th;
        if (bitmap == null || i <= 0 || i2 <= 0) {
            return bitmap;
        }
        Bitmap createScaledBitmap;
        try {
            createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, true);
            if (createScaledBitmap == bitmap || !z) {
                return createScaledBitmap;
            }
            try {
                bitmap.recycle();
                return createScaledBitmap;
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return createScaledBitmap;
            }
        } catch (Throwable th3) {
            th = th3;
            createScaledBitmap = bitmap;
            th.printStackTrace();
            return createScaledBitmap;
        }
    }

    public static Bitmap c(Bitmap bitmap, int i, int i2, boolean z) {
        if (bitmap == null || i <= 0 || i2 <= 0) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > i) {
            height = (height * i) / width;
            width = i;
        }
        if (height > i2) {
            height = (width * i2) / height;
        } else {
            i2 = height;
            height = width;
        }
        return b(bitmap, height, i2, z);
    }

    public static Bitmap d(Bitmap bitmap, int i, int i2, boolean z) {
        boolean z2 = false;
        if (bitmap == null || i == 0 || i2 == 0) {
            return bitmap;
        }
        int i3;
        int i4;
        int i5;
        int i6;
        Bitmap bitmap2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = null;
        if (i2 * width > height * i) {
            try {
                i3 = (height * i) / i2;
                i4 = (width - i3) / 2;
                i5 = 0;
                i6 = height;
            } catch (Throwable th) {
                Throwable th2 = th;
                bitmap2 = bitmap;
                th2.printStackTrace();
                return bitmap2;
            }
        }
        i6 = (i2 * width) / i;
        i5 = (height - i6) / 2;
        i4 = 0;
        i3 = width;
        if (i3 > i) {
            matrix = new Matrix();
            float f = ((float) i) / ((float) i3);
            matrix.postScale(f, f);
            z2 = true;
        }
        bitmap2 = Bitmap.createBitmap(bitmap, i4, i5, i3, i6, matrix, z2);
        try {
            g.a("BitmapUtils", "BitmapUtils src=" + width + SelectCountryActivity.SPLITTER + height + " new=" + i3 + SelectCountryActivity.SPLITTER + i6 + " dst=" + i + SelectCountryActivity.SPLITTER + i2 + " dstBitmap=" + bitmap2.getWidth() + SelectCountryActivity.SPLITTER + bitmap2.getHeight() + " tryRecycleSource=" + z);
            if (bitmap2 == bitmap || !z) {
                return bitmap2;
            }
            bitmap.recycle();
            return bitmap2;
        } catch (Throwable th3) {
            th2 = th3;
            th2.printStackTrace();
            return bitmap2;
        }
    }
}
