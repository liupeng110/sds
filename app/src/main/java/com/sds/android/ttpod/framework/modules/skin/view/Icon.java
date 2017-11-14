package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import java.util.Vector;

public class Icon extends MaskImageView {
    private int a;
    private Vector<Drawable> b;
    private a c;

    public interface a {
        void a(int i);
    }

    public Icon(Context context) {
        super(context);
        this.a = 0;
        this.b = new Vector();
    }

    public Icon(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Icon(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 0;
        this.b = new Vector();
    }

    public void setStateChangedListener(a aVar) {
        this.c = aVar;
    }

    public void setState(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i >= this.b.size()) {
            i = this.b.size() - 1;
        }
        if (i >= 0) {
            this.a = i;
            setImageDrawable((Drawable) this.b.get(this.a));
        }
        if (this.c != null) {
            this.c.a(i);
        }
    }

    public int getState() {
        return this.a;
    }

    public void a(int i, Drawable drawable) {
        int i2;
        this.b.add(i, drawable);
        if (i < this.a) {
            i2 = this.a;
            this.a = i2 + 1;
        } else {
            i2 = this.a;
        }
        this.a = i2;
        setState(this.a);
    }

    public void a(Drawable drawable) {
        this.b.add(drawable);
        setState(this.a);
    }
}
