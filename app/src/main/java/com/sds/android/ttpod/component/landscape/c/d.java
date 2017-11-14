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
import android.util.TypedValue;
import com.sds.android.ttpod.component.landscape.b;
import com.sds.android.ttpod.component.landscape.d.c;

/* TextureLyric */
public class d extends a {
    private float g;
    private TextPaint h = new TextPaint();

    public d(String str) {
        super(str);
        this.h.setTextAlign(Align.LEFT);
        this.h.setAntiAlias(true);
        this.h.setTextSize(TypedValue.applyDimension(2, (float) b.d(), Resources.getSystem().getDisplayMetrics()));
        this.h.setColor(-1);
        this.h.setShadowLayer(3.0f, 0.0f, 0.0f, SupportMenu.CATEGORY_MASK);
        this.h.setTypeface(Typeface.DEFAULT_BOLD);
        FontMetrics fontMetrics = this.h.getFontMetrics();
        float f = fontMetrics.bottom - fontMetrics.top;
        this.d = new com.sds.android.ttpod.component.landscape.d.b(1024, a((int) f));
        this.c = new c(0.0f, f);
        this.f = this.c.b() / ((float) this.d.b());
        this.g = -fontMetrics.top;
    }

    public void a(String str) {
        Bitmap createBitmap = Bitmap.createBitmap(this.d.a(), this.d.b(), Config.ARGB_8888);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas(createBitmap);
        this.c.a(this.h.measureText(str));
        this.e = this.c.a() / ((float) this.d.a());
        createBitmap.eraseColor(0);
        canvas.drawText(str, 0.0f, this.g, this.h);
        a(createBitmap, true);
    }
}
