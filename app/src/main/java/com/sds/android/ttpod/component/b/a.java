package com.sds.android.ttpod.component.b;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseApplication;

/* ActionItem */
public class a {
    private int a;
    private int b;
    private int c;
    private CharSequence d;
    private CharSequence e;
    private Drawable f;
    private Object g;
    private int h;
    private a i;
    private Resources j;

    /* ActionItem */
    public interface b {
        void a(a aVar, int i);
    }

    /* ActionItem */
    public enum a {
        NO_ICON,
        LEFT_ICON,
        TITLE_ICON
    }

    public a(int i, int i2, int i3) {
        this.i = a.LEFT_ICON;
        this.j = BaseApplication.e().getResources();
        this.a = i;
        this.b = i2;
        this.c = this.j.getColor(R.color.media_menu_icon_text);
        if (i3 != 0) {
            this.e = this.j.getText(i3, "");
        }
    }

    public a(int i, int i2, int i3, int i4) {
        this(i, i2, i3);
        this.h = i4;
    }

    public a(int i, int i2, CharSequence charSequence) {
        this.i = a.LEFT_ICON;
        this.j = BaseApplication.e().getResources();
        this.a = i;
        this.e = charSequence;
        this.b = i2;
    }

    public a(int i, int i2, CharSequence charSequence, CharSequence charSequence2) {
        this(i, i2, charSequence);
        this.d = charSequence2;
    }

    public CharSequence d() {
        return this.e;
    }

    public int e() {
        return this.b;
    }

    public a a(int i) {
        this.c = this.j.getColor(i);
        return this;
    }

    public int f() {
        return this.c;
    }

    public int g() {
        return this.a;
    }

    public <T extends a> T a(Object obj) {
        this.g = obj;
        return this;
    }

    public Object h() {
        return this.g;
    }

    public <T extends a> T b(Drawable drawable) {
        this.f = drawable;
        return this;
    }

    public Drawable i() {
        return this.f;
    }

    public <T extends a> T b(int i) {
        this.h = i;
        return this;
    }

    public <T extends a> T c(int i) {
        if (i != 0) {
            this.d = k().getText(i);
        }
        return this;
    }

    public <T extends a> T a(CharSequence charSequence) {
        this.d = charSequence;
        return this;
    }

    public CharSequence j() {
        return this.d;
    }

    public Resources k() {
        return this.j;
    }

    public int l() {
        return this.h;
    }

    public a m() {
        return this.i;
    }

    public void a(a aVar) {
        this.i = aVar;
    }

    public String toString() {
        return this.e.toString();
    }
}
