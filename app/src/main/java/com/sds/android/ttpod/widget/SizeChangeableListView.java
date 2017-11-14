package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import com.sds.android.ttpod.framework.modules.skin.d.b;

public class SizeChangeableListView extends ListView {
    private a a;

    public interface a {
        void a(View view, int i, int i2, int i3, int i4);
    }

    public SizeChangeableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SizeChangeableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnSizeChangedListener(a aVar) {
        this.a = aVar;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Drawable background = getBackground();
        if (background instanceof b) {
            ((b) background).a((float) i, (float) i2);
        }
        if (this.a != null) {
            this.a.a(this, i, i2, i3, i4);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if ((drawable instanceof BitmapDrawable) && !(drawable instanceof b)) {
            drawable = new b(getResources(), ((BitmapDrawable) drawable).getBitmap());
        }
        super.setBackgroundDrawable(drawable);
    }
}
