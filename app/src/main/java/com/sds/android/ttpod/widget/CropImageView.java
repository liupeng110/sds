package com.sds.android.ttpod.widget;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.lib.util.b;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.tencent.open.yyb.TitleBar;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CropImageView extends View {
    private static final int n = Color.argb(100, 0, 0, 0);
    private Uri a;
    private Bitmap b;
    private Paint c;
    private Paint d;
    private Paint e;
    private RectF f;
    private RectF g;
    private RectF h;
    private RectF i;
    private RectF j;
    private Matrix k;
    private int l;
    private int m;
    private float o;
    private float p;
    private float q;
    private int r;
    private float s;
    private PointF t;
    private PointF u;
    private PointF v;
    private float[] w;

    public CropImageView(Context context) {
        super(context);
        this.c = new Paint();
        this.d = new Paint();
        this.e = new Paint();
        this.f = new RectF();
        this.g = new RectF();
        this.h = new RectF();
        this.i = new RectF();
        this.j = new RectF();
        this.k = new Matrix();
        this.l = 4;
        this.m = 4;
        this.r = 0;
        this.t = new PointF();
        this.u = new PointF();
        this.v = new PointF();
        this.w = new float[9];
        a(context);
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new Paint();
        this.d = new Paint();
        this.e = new Paint();
        this.f = new RectF();
        this.g = new RectF();
        this.h = new RectF();
        this.i = new RectF();
        this.j = new RectF();
        this.k = new Matrix();
        this.l = 4;
        this.m = 4;
        this.r = 0;
        this.t = new PointF();
        this.u = new PointF();
        this.v = new PointF();
        this.w = new float[9];
        a(context);
    }

    public void a(int i, int i2) {
        if (i <= 0) {
            i = 4;
        }
        this.l = i;
        if (i2 <= 0) {
            i2 = 4;
        }
        this.m = i2;
    }

    private void a(Context context) {
        this.d.setColor(-1);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float applyDimension = TypedValue.applyDimension(1, 2.0f, displayMetrics);
        this.o = TypedValue.applyDimension(1, 8.0f, displayMetrics);
        this.p = TypedValue.applyDimension(1, 30.0f, displayMetrics);
        this.q = this.p * 4.0f;
        this.d.setStrokeWidth(applyDimension);
        this.d.setStyle(Style.STROKE);
        this.c.setStyle(Style.FILL);
        this.c.setColor(n);
        this.e.setColor(-16711936);
        this.e.setStyle(Style.FILL);
        this.e.setAntiAlias(true);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        g.a("CropImageView", "onSizeChanged onBitmapDecodeComplete w=" + i + " h=" + i2);
        this.h.set(0.0f, 0.0f, (float) i, (float) i2);
        this.i.set(this.h);
        this.i.inset(this.o, this.o);
        a();
    }

    public void setImageURI(Uri uri) {
        this.a = uri;
        a();
    }

    private Bitmap b(String str) throws Exception {
        Bitmap b = b.b(str, (int) this.h.width(), (int) this.h.height());
        if (new ExifInterface(str).getAttributeInt("Orientation", 0) != 6) {
            return b;
        }
        this.k.reset();
        this.k.postRotate(90.0f);
        Bitmap createBitmap = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), this.k, true);
        b.recycle();
        g.a("CropImageView", "getSDcardPic Rotate_90 bitmap=%b bitmapRotated=%b", Boolean.valueOf(b.isRecycled()), Boolean.valueOf(createBitmap.isRecycled()));
        return createBitmap;
    }

    private Bitmap getContentPic() throws Exception {
        Cursor query = getContext().getContentResolver().query(this.a, null, null, null, null);
        query.moveToFirst();
        String string = query.getString(query.getColumnIndex("_data"));
        query.close();
        return b(string);
    }

    private void a() {
        if (this.h.width() > 0.0f && this.h.height() > 0.0f && this.a != null) {
            Bitmap contentPic;
            if (!(this.b == null || this.b.isRecycled())) {
                this.b.recycle();
                this.b = null;
                g.c("CropImageView", "showNewImage recycle old image");
            }
            try {
                if (this.a.getScheme().equals("content")) {
                    contentPic = getContentPic();
                } else {
                    contentPic = b(this.a.getPath());
                }
            } catch (OutOfMemoryError e) {
                g.c("CropImageView", "show NewImage OutOfMemoryError: " + e.toString());
                contentPic = null;
            } catch (Exception e2) {
                g.c("CropImageView", "show NewImage Exception e=" + e2.toString());
                contentPic = null;
            }
            a(contentPic, 0, null);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b != null && !this.b.isRecycled()) {
            canvas.drawBitmap(this.b, this.k, null);
            canvas.save(2);
            canvas.clipRect(this.j, Op.DIFFERENCE);
            canvas.drawRect(this.h, this.c);
            canvas.restore();
            canvas.drawRect(this.j, this.d);
        }
    }

    private void a(Bitmap bitmap, int i, Context context) {
        this.b = bitmap;
        if (bitmap == null) {
            f.a((int) R.string.userinfo_can_not_open_image);
        } else {
            float f;
            this.f.set(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
            this.g.set(this.f);
            float width = (this.i.width() / 4.0f) * 3.0f;
            float height = (this.i.height() / 4.0f) * 3.0f;
            if (this.m >= this.l) {
                f = (((float) this.l) * height) / ((float) this.m);
                if (f > this.i.width()) {
                    height = (((float) this.m) * width) / ((float) this.l);
                    f = width;
                }
            } else {
                f = (((float) this.m) * width) / ((float) this.l);
                if (f > this.i.height()) {
                    f = (((float) this.l) * height) / ((float) this.m);
                } else {
                    height = f;
                    f = width;
                }
            }
            width = (this.h.width() - f) / 2.0f;
            float height2 = (this.h.height() - height) / 2.0f;
            this.j.set(width, height2, f + width, height + height2);
            g.d("CropImageView", "onBitmapDecodeComplete view(" + this.h.width() + ", " + this.h.height() + ") bitamap(" + bitmap.getWidth() + ", " + bitmap.getHeight() + ") chooseFrame=" + this.j.toString() + " outPutWidth=" + this.l + " outputHeight=" + this.m);
            this.k.reset();
            if (this.f.width() > this.h.width() && this.f.height() > this.h.height()) {
                this.k.setRectToRect(this.f, this.h, ScaleToFit.CENTER);
                b();
            }
            if (this.g.width() < this.j.width() || this.g.height() < this.j.height()) {
                height = Math.max(this.j.width() / this.g.width(), this.j.height() / this.g.height());
                this.k.postScale(height, height);
                b();
            }
            this.k.postTranslate(((this.h.width() - this.g.width()) / 2.0f) - this.g.left, ((this.h.height() - this.g.height()) / 2.0f) - this.g.top);
            b();
        }
        invalidate();
    }

    private void b() {
        this.k.getValues(this.w);
        this.g.left = this.w[2];
        this.g.top = this.w[5];
        this.g.right = this.g.left + (this.f.width() * this.w[0]);
        this.g.bottom = this.g.top + (this.f.height() * this.w[4]);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.b == null || this.b.isRecycled()) {
            return false;
        }
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
            case 0:
                this.r = 1;
                this.t.set(rawX, rawY);
                this.u.set(this.t);
                return true;
            case 1:
            case 6:
                this.r = 0;
                return true;
            case 2:
                if (this.r == 1) {
                    c(rawX - this.u.x, rawY - this.u.y);
                    this.u.set(rawX, rawY);
                    invalidate();
                    b();
                    return true;
                } else if (this.r == 2) {
                    a(rawX - this.u.x, rawY - this.u.y);
                    this.u.set(rawX, rawY);
                    invalidate();
                    return true;
                } else if (this.r == 3) {
                    float a = a(motionEvent);
                    if (a <= TitleBar.SHAREBTN_RIGHT_MARGIN || !e(a)) {
                        return true;
                    }
                    b();
                    c();
                    invalidate();
                    return true;
                } else if (this.r < 101 || this.r > 108) {
                    return true;
                } else {
                    b(rawX - this.u.x, rawY - this.u.y);
                    this.u.set(rawX, rawY);
                    invalidate();
                    return true;
                }
            case 5:
                this.s = a(motionEvent);
                this.r = 3;
                this.v.set(this.j.centerX(), this.j.centerY());
                return true;
            default:
                return true;
        }
    }

    private void c() {
        if (this.g.left > this.j.left) {
            this.k.postTranslate(this.j.left - this.g.left, 0.0f);
        } else if (this.g.right < this.j.right) {
            this.k.postTranslate(this.j.right - this.g.right, 0.0f);
        }
        if (this.g.top > this.j.top) {
            this.k.postTranslate(0.0f, this.j.top - this.g.top);
        } else if (this.g.bottom < this.j.bottom) {
            this.k.postTranslate(0.0f, this.j.bottom - this.g.bottom);
        }
        b();
    }

    private void a(float f, float f2) {
        if (this.j.left + f < this.i.left) {
            f = this.i.left - this.j.left;
        } else if (this.j.right + f > this.i.right) {
            f = this.i.right - this.j.right;
        }
        if (this.j.top + f2 < this.i.top) {
            f2 = this.i.top - this.j.top;
        } else if (this.j.bottom + f2 > this.i.bottom) {
            f2 = this.i.bottom - this.j.bottom;
        }
        this.j.offset(f, f2);
    }

    private float a(float f) {
        float f2 = this.j.left + f;
        if (f2 < this.i.left) {
            return this.i.left - this.j.left;
        }
        if (this.j.right - f2 < this.q) {
            return (this.j.right - this.q) - this.j.left;
        }
        return f;
    }

    private float b(float f) {
        float f2 = this.j.top + f;
        if (f2 < this.i.top) {
            return this.i.top - this.j.top;
        }
        if (this.j.bottom - f2 < this.q) {
            return (this.j.bottom - this.q) - this.j.top;
        }
        return f;
    }

    private float c(float f) {
        float f2 = this.j.right + f;
        if (f2 > this.i.right) {
            return this.i.right - this.j.right;
        }
        if (f2 - this.j.left < this.q) {
            return (this.j.left + this.q) - this.j.right;
        }
        return f;
    }

    private float d(float f) {
        float f2 = this.j.bottom + f;
        if (f2 > this.i.bottom) {
            return this.i.bottom - this.j.bottom;
        }
        if (f2 - this.j.top < this.q) {
            return (this.j.top + this.q) - this.j.bottom;
        }
        return f;
    }

    private void b(float f, float f2) {
        RectF rectF;
        switch (this.r) {
            case 101:
                rectF = this.j;
                rectF.left += a(f);
                break;
            case 102:
                rectF = this.j;
                rectF.left += a(f);
                rectF = this.j;
                rectF.top += b(f2);
                break;
            case SecExceptionCode.SEC_ERROR_INIT_LOADSO_FAIL /*103*/:
                rectF = this.j;
                rectF.top += b(f2);
                break;
            case SecExceptionCode.SEC_ERROR_INIT_NO_RSA_FILE_ERROR /*104*/:
                rectF = this.j;
                rectF.right += c(f);
                rectF = this.j;
                rectF.top += b(f2);
                break;
            case SecExceptionCode.SEC_ERROR_INIT_PUBLICKKEY_FIND_ERROR /*105*/:
                rectF = this.j;
                rectF.right += c(f);
                break;
            case SecExceptionCode.SEC_ERROR_INIT_SO_NOT_EXIST /*106*/:
                rectF = this.j;
                rectF.right += c(f);
                rectF = this.j;
                rectF.bottom += d(f2);
                break;
            case 107:
                rectF = this.j;
                rectF.bottom += d(f2);
                break;
            case 108:
                rectF = this.j;
                rectF.left += a(f);
                rectF = this.j;
                rectF.bottom += d(f2);
                break;
        }
        this.j.intersect(this.i);
    }

    private void c(float f, float f2) {
        float f3;
        float f4;
        if (f <= 0.0f || this.g.left + f < this.j.left) {
            f3 = f;
        } else {
            f3 = this.j.left - this.g.left;
        }
        if (f3 >= 0.0f || this.g.right + f3 > this.j.right) {
            f4 = f3;
        } else {
            f4 = this.j.right - this.g.right;
        }
        if (f2 <= 0.0f || this.g.top + f2 < this.j.top) {
            f3 = f2;
        } else {
            f3 = this.j.top - this.g.top;
        }
        if (f3 < 0.0f && this.g.bottom + f3 <= this.j.bottom) {
            f3 = this.j.bottom - this.g.bottom;
        }
        this.k.postTranslate(f4, f3);
    }

    private boolean e(float f) {
        float f2;
        boolean z = true;
        boolean z2 = false;
        float f3 = f / this.s;
        if (f > this.s) {
            if (this.g.width() < ((float) (((int) this.f.width()) << 2)) || this.g.width() < this.j.width() || this.g.height() < this.j.height()) {
                z2 = true;
            }
            z = z2;
            f2 = f3;
        } else if (this.g.width() <= this.j.width() || this.g.height() <= this.j.height()) {
            z = false;
            f2 = f3;
        } else {
            f2 = (this.g.width() * f3 < this.j.width() || this.g.height() * f3 < this.j.height()) ? Math.max(this.j.width() / this.g.width(), this.j.height() / this.g.height()) : f3;
        }
        if (z) {
            this.k.postScale(f2, f2, this.v.x, this.v.y);
            this.s = f;
        }
        return z;
    }

    private float a(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return FloatMath.sqrt((x * x) + (y * y));
    }

    public boolean a(String str) {
        Bitmap createBitmap;
        FileOutputStream fileOutputStream;
        OutOfMemoryError e;
        IOException e2;
        Throwable th;
        Exception e3;
        if (this.b == null || this.b.isRecycled()) {
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        Bitmap bitmap = null;
        long n = e.n(str);
        try {
            float width = (((float) this.b.getWidth()) * this.j.width()) / this.g.width();
            g.a("CropImageView", "saveImage (%.2f %.2f, %.2f %.2f) %.2f recycyle=%b path=%s", Float.valueOf((this.j.left - this.g.left) / this.w[0]), Float.valueOf((this.j.top - this.g.top) / this.w[4]), Float.valueOf(width), Float.valueOf((((float) this.b.getHeight()) * this.j.height()) / this.g.height()), Float.valueOf(this.w[0]), Boolean.valueOf(this.b.isRecycled()), str);
            Matrix matrix = null;
            if (width > ((float) this.l)) {
                float f = ((float) this.l) / width;
                matrix = new Matrix();
                matrix.setScale(f, f);
            }
            createBitmap = Bitmap.createBitmap(this.b, Math.round(r1), Math.round(r2), Math.round(width), Math.round(r4), matrix, matrix != null);
            try {
                fileOutputStream = new FileOutputStream(str);
            } catch (OutOfMemoryError e4) {
                e = e4;
                fileOutputStream = null;
                try {
                    g.c("CropImageView", "saveImage OutOfMemoryError:" + e.toString());
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                        if (n > 0) {
                            new File(str).setLastModified(1000 + n);
                        }
                    }
                    if (createBitmap != null) {
                        createBitmap.recycle();
                    }
                    this.b.recycle();
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    bitmap = createBitmap;
                    fileOutputStream2 = fileOutputStream;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                        if (n > 0) {
                            new File(str).setLastModified(1000 + n);
                        }
                    }
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    this.b.recycle();
                    throw th;
                }
            } catch (IOException e6) {
                e22 = e6;
                bitmap = createBitmap;
                try {
                    g.c("CropImageView", "saveImage IOException " + e22.toString());
                    e22.printStackTrace();
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                        if (n > 0) {
                            new File(str).setLastModified(1000 + n);
                        }
                    }
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    this.b.recycle();
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                        if (n > 0) {
                            new File(str).setLastModified(1000 + n);
                        }
                    }
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    this.b.recycle();
                    throw th;
                }
            } catch (Exception e7) {
                e3 = e7;
                bitmap = createBitmap;
                g.c("CropImageView", "saveImage Exception " + e3.toString());
                e3.printStackTrace();
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                    if (n > 0) {
                        new File(str).setLastModified(1000 + n);
                    }
                }
                if (bitmap != null) {
                    bitmap.recycle();
                }
                this.b.recycle();
                return false;
            } catch (Throwable th4) {
                th = th4;
                bitmap = createBitmap;
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                    if (n > 0) {
                        new File(str).setLastModified(1000 + n);
                    }
                }
                if (bitmap != null) {
                    bitmap.recycle();
                }
                this.b.recycle();
                throw th;
            }
            try {
                g.d("CropImageView", "compressSuccess %b %d %d", Boolean.valueOf(createBitmap.compress(CompressFormat.JPEG, 70, fileOutputStream)), Integer.valueOf(createBitmap.getWidth()), Integer.valueOf(createBitmap.getHeight()));
                fileOutputStream.flush();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e8) {
                        e8.printStackTrace();
                    }
                    if (n > 0) {
                        new File(str).setLastModified(1000 + n);
                    }
                }
                if (createBitmap != null) {
                    createBitmap.recycle();
                }
                this.b.recycle();
                return true;
            } catch (OutOfMemoryError e9) {
                e = e9;
                g.c("CropImageView", "saveImage OutOfMemoryError:" + e.toString());
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    if (n > 0) {
                        new File(str).setLastModified(1000 + n);
                    }
                }
                if (createBitmap != null) {
                    createBitmap.recycle();
                }
                this.b.recycle();
                return false;
            } catch (IOException e10) {
                e2222 = e10;
                bitmap = createBitmap;
                fileOutputStream2 = fileOutputStream;
                g.c("CropImageView", "saveImage IOException " + e2222.toString());
                e2222.printStackTrace();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                    if (n > 0) {
                        new File(str).setLastModified(1000 + n);
                    }
                }
                if (bitmap != null) {
                    bitmap.recycle();
                }
                this.b.recycle();
                return false;
            } catch (Exception e11) {
                e3 = e11;
                bitmap = createBitmap;
                fileOutputStream2 = fileOutputStream;
                g.c("CropImageView", "saveImage Exception " + e3.toString());
                e3.printStackTrace();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                    if (n > 0) {
                        new File(str).setLastModified(1000 + n);
                    }
                }
                if (bitmap != null) {
                    bitmap.recycle();
                }
                this.b.recycle();
                return false;
            }
        } catch (OutOfMemoryError e12) {
            e = e12;
            createBitmap = null;
            fileOutputStream = null;
            g.c("CropImageView", "saveImage OutOfMemoryError:" + e.toString());
            if (fileOutputStream != null) {
                fileOutputStream.close();
                if (n > 0) {
                    new File(str).setLastModified(1000 + n);
                }
            }
            if (createBitmap != null) {
                createBitmap.recycle();
            }
            this.b.recycle();
            return false;
        } catch (IOException e13) {
            e2222 = e13;
            g.c("CropImageView", "saveImage IOException " + e2222.toString());
            e2222.printStackTrace();
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
                if (n > 0) {
                    new File(str).setLastModified(1000 + n);
                }
            }
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.b.recycle();
            return false;
        } catch (Exception e14) {
            e3 = e14;
            g.c("CropImageView", "saveImage Exception " + e3.toString());
            e3.printStackTrace();
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
                if (n > 0) {
                    new File(str).setLastModified(1000 + n);
                }
            }
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.b.recycle();
            return false;
        }
    }
}
