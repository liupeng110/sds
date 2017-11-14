package com.sds.android.ttpod.widget.a;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;

/* SimpleFloatViewController */
public class d implements c {
    private Bitmap a;
    private ImageView b;
    private int c = ViewCompat.MEASURED_STATE_MASK;
    private ListView d;

    public d(ListView listView) {
        this.d = listView;
    }

    public void d(int i) {
        this.c = i;
    }

    public View c(int i) {
        View childAt = this.d.getChildAt((this.d.getHeaderViewsCount() + i) - this.d.getFirstVisiblePosition());
        if (childAt == null) {
            return null;
        }
        childAt.setPressed(false);
        childAt.setDrawingCacheEnabled(true);
        Bitmap drawingCache = childAt.getDrawingCache();
        if (drawingCache != null) {
            this.a = Bitmap.createBitmap(drawingCache);
        }
        childAt.setDrawingCacheEnabled(false);
        if (this.b == null) {
            this.b = new ImageView(this.d.getContext());
        }
        this.b.setBackgroundColor(this.c);
        this.b.setPadding(0, 0, 0, 0);
        if (this.a != null) {
            this.b.setImageBitmap(this.a);
        }
        this.b.setLayoutParams(new LayoutParams(childAt.getWidth(), childAt.getHeight()));
        return this.b;
    }

    public void a(View view, Point point, Point point2) {
    }

    public void a(View view) {
        ((ImageView) view).setImageDrawable(null);
        if (this.a != null) {
            this.a.recycle();
            this.a = null;
        }
    }
}
