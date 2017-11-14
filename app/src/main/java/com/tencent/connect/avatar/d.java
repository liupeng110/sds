package com.tencent.connect.avatar;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;

/* ProGuard */
class d implements OnClickListener {
    final /* synthetic */ ImageActivity a;

    d(ImageActivity imageActivity) {
        this.a = imageActivity;
    }

    public void onClick(View view) {
        this.a.j.setVisibility(0);
        this.a.g.setEnabled(false);
        this.a.g.setTextColor(Color.rgb(21, 21, 21));
        this.a.f.setEnabled(false);
        this.a.f.setTextColor(Color.rgb(36, 94, 134));
        new Thread(new Runnable(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a.c();
            }
        }).start();
        if (this.a.l) {
            this.a.a("10657", 0);
            return;
        }
        this.a.a("10655", System.currentTimeMillis() - this.a.m);
        if (this.a.e.b) {
            this.a.a("10654", 0);
        }
    }
}
