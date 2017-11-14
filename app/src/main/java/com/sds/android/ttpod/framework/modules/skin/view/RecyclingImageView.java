package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a.r;

public class RecyclingImageView extends ImageView {
    private static final String a = RecyclingImageView.class.getSimpleName();

    public RecyclingImageView(Context context) {
        super(context);
    }

    public RecyclingImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RecyclingImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setImageBitmap(Bitmap bitmap) {
        if (r.a()) {
            super.setImageBitmap(bitmap);
        } else {
            setImageBitmapWithRecycling(bitmap);
        }
    }

    private void setImageBitmapWithRecycling(Bitmap bitmap) {
        g.a(a, "setImageBitmap " + bitmap);
        Drawable drawable = getDrawable();
        Drawable cVar = new c(getContext().getResources(), bitmap);
        super.setImageDrawable(cVar);
        a(cVar, true);
        a(drawable, false);
    }

    private static void a(Drawable drawable, boolean z) {
        if (drawable instanceof c) {
            ((c) drawable).a(z);
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                a(layerDrawable.getDrawable(i), z);
            }
        }
    }
}
