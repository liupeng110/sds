package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.sds.android.ttpod.R;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

public class UserAvatarView extends RoundedImageView {
    private Bitmap b;
    private boolean c;

    public UserAvatarView(Context context) {
        super(context);
        this.c = false;
    }

    public UserAvatarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UserAvatarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = false;
        this.b = BitmapFactory.decodeResource(getResources(), R.drawable.img_user_v);
    }

    public void setVFlagVisible(boolean z) {
        this.c = z;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        f fVar = (f) getDrawable();
        if (this.c && fVar != null) {
            canvas.drawBitmap(this.b, (((float) getWidth()) * 0.84f) - (((float) this.b.getWidth()) * TTFMImageUtils.Large_Scale), (((float) getHeight()) * 0.84f) - (((float) this.b.getHeight()) * TTFMImageUtils.Large_Scale), fVar.a());
        }
    }
}
