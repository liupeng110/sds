package com.sds.android.ttpod.activities.setting;

import android.graphics.drawable.Drawable;
import com.sds.android.ttpod.component.b.a;

/* SettingItem */
public class c extends a {
    private boolean a = true;
    private Drawable b;
    private int c;

    public c(int i, int i2, int i3, int i4, int i5) {
        super(i, i2, i3);
        c(i4);
        if (i5 != 0) {
            this.b = k().getDrawable(i5);
        }
    }

    public c(int i, int i2, int i3, int i4, int i5, boolean z) {
        super(i, 0, i3, i2);
        c(i4);
        this.c = i5;
    }

    public c(int i, int i2, int i3, CharSequence charSequence, int i4) {
        super(i, i2, i3);
        if (i4 != 0) {
            this.b = k().getDrawable(i4);
        }
        a(charSequence);
    }

    public c(int i, int i2, int i3, CharSequence charSequence, int i4, boolean z) {
        super(i, 0, i3, i2);
        a(charSequence);
        this.c = i4;
    }

    public Drawable a() {
        return this.b;
    }

    public void a(Drawable drawable) {
        this.b = drawable;
    }

    public int b() {
        return this.c;
    }

    public boolean c() {
        return this.a;
    }
}
