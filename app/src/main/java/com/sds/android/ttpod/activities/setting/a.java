package com.sds.android.ttpod.activities.setting;

import android.widget.Checkable;

/* CheckableSettingItem */
public class a extends c implements Checkable {
    private boolean a;

    public a(int i, int i2, int i3, int i4, int i5, boolean z) {
        super(i, i2, i3, i4, i5);
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
