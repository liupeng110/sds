package com.sds.android.ttpod.widget.wheelview.a;

import android.content.Context;

/* ArrayWheelAdapter */
public class c<T> extends b {
    private T[] f;

    public c(Context context, T[] tArr) {
        super(context);
        this.f = tArr;
    }

    public CharSequence e(int i) {
        if (i < 0 || i >= this.f.length) {
            return null;
        }
        Object obj = this.f[i];
        if (obj instanceof CharSequence) {
            return (CharSequence) obj;
        }
        return obj.toString();
    }

    public int a() {
        return this.f.length;
    }
}
