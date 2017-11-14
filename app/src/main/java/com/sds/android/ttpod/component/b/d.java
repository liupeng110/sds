package com.sds.android.ttpod.component.b;

import android.widget.Checkable;

/* CheckableActionItem */
public class d extends a implements Checkable {
    private boolean a;

    public d(int i, int i2) {
        this(i, i2, false);
    }

    public d(int i, int i2, boolean z) {
        super(i, 0, i2);
        this.a = false;
        this.a = z;
    }

    public d(int i, int i2, int i3, int i4, boolean z) {
        super(i, i2, i3, i4);
        this.a = false;
        this.a = z;
    }

    public boolean isChecked() {
        return this.a;
    }

    public void setChecked(boolean z) {
        this.a = z;
    }

    public void toggle() {
        this.a = !this.a;
    }
}
