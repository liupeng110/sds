package com.sds.android.ttpod.framework.modules.skin.d;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;

/* BitmapDrawableCreator */
public class a extends e {
    private Bitmap a;
    private int b = -1;
    private int c = -1;

    public a(Bitmap bitmap) {
        this.a = bitmap;
    }

    public Drawable a(Resources resources) {
        Bitmap bitmap = this.a;
        if (bitmap == null) {
            return null;
        }
        byte[] ninePatchChunk = bitmap.getNinePatchChunk();
        if (ninePatchChunk == null || !NinePatch.isNinePatchChunk(ninePatchChunk)) {
            Drawable bitmapDrawable = new BitmapDrawable(resources, bitmap);
            bitmapDrawable.setTileModeXY(a(this.b), a(this.c));
            return bitmapDrawable;
        }
        return new NinePatchDrawable(resources, bitmap, ninePatchChunk, g.a(ninePatchChunk).a(), null);
    }

    public Bitmap a() {
        return this.a;
    }

    public void a(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    private static TileMode a(int i) {
        switch (i) {
            case 1:
                return TileMode.CLAMP;
            case 2:
                return TileMode.REPEAT;
            case 3:
                return TileMode.MIRROR;
            default:
                return null;
        }
    }
}
