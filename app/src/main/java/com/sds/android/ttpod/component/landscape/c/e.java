package com.sds.android.ttpod.component.landscape.c;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Typeface;
import android.support.v4.internal.view.SupportMenu;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.sds.android.ttpod.component.landscape.b;
import com.sds.android.ttpod.component.landscape.d.c;

/* TextureMusicInfo */
public class e extends a {
    private float g;
    private float h;
    private TextPaint i = new TextPaint();
    private TextPaint j;

    public e(String str) {
        super(str);
        this.i.setTextAlign(Align.LEFT);
        this.i.setAntiAlias(true);
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        this.i.setTextSize(TypedValue.applyDimension(2, (float) b.e(), displayMetrics));
        this.i.setColor(-1);
        this.i.setShadowLayer(2.0f, 0.0f, 0.0f, SupportMenu.CATEGORY_MASK);
        this.i.setTypeface(Typeface.DEFAULT);
        FontMetrics fontMetrics = this.i.getFontMetrics();
        this.g = 0.0f - fontMetrics.top;
        float f = fontMetrics.bottom - fontMetrics.top;
        float g = ((float) b.g()) * b.a();
        this.j = new TextPaint();
        this.j.setTextAlign(Align.LEFT);
        this.j.setAntiAlias(true);
        this.j.setTextSize(TypedValue.applyDimension(2, (float) b.f(), displayMetrics));
        this.j.setColor(-1);
        this.j.setShadowLayer(2.0f, 0.0f, 0.0f, SupportMenu.CATEGORY_MASK);
        this.j.setTypeface(Typeface.DEFAULT);
        FontMetrics fontMetrics2 = this.j.getFontMetrics();
        this.h = ((0.0f - fontMetrics2.top) + g) + f;
        float f2 = fontMetrics2.bottom - fontMetrics2.top;
        this.c = new c();
        this.c.b(f2 + (f + g));
        this.d = new com.sds.android.ttpod.component.landscape.d.b();
        this.d.b(a((int) this.c.b()));
        this.f = this.c.b() / ((float) this.d.b());
        if (this.f > 1.0f) {
            this.f = 1.0f;
        }
        a("", "");
    }

    public void a(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        float measureText = this.i.measureText(str);
        float measureText2 = this.j.measureText(str2);
        this.c.a(Math.max(measureText, measureText2));
        if (this.c.a() == 0.0f) {
            this.c.a(1.0f);
        }
        this.d.a(a((int) this.c.a()));
        this.e = this.c.a() / ((float) this.d.a());
        if (this.e > 1.0f) {
            this.e = 1.0f;
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.d.a(), this.d.b(), Config.ARGB_8888);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawText(str, (this.c.a() - measureText) / 2.0f, this.g, this.i);
        canvas.drawText(str2, (this.c.a() - measureText2) / 2.0f, this.h, this.j);
        a(createBitmap, true);
    }
}
