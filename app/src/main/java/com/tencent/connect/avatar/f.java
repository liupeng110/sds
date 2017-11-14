package com.tencent.connect.avatar;

import android.view.View;
import android.view.View.OnClickListener;

/* ProGuard */
class f implements OnClickListener {
    final /* synthetic */ ImageActivity a;

    f(ImageActivity imageActivity) {
        this.a = imageActivity;
    }

    public void onClick(View view) {
        this.a.a("10656", System.currentTimeMillis() - this.a.m);
        this.a.setResult(0);
        this.a.d();
    }
}
