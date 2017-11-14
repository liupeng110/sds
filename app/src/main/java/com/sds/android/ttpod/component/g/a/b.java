package com.sds.android.ttpod.component.g.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint.Align;
import android.view.View;
import com.sds.android.ttpod.framework.modules.skin.e.g;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView.c;
import com.sds.android.ttpod.framework.modules.skin.view.TTPodButton;
import com.sds.android.ttpod.media.mediastore.MediaItem;

/* LyricPanelViewController */
public class b extends d implements c {
    protected TTPodButton a;
    private Align b = Align.CENTER;

    public b(Context context, String str) {
        super(context, str);
    }

    public void b_() {
        super.b_();
        if (this.K != null) {
            a(this.K, true);
            this.b = this.K.getAlign();
            this.K.setEnabled(true);
            this.K.setTouchListener(this);
        }
    }

    public void a(long j) {
        if (this.g != null) {
            this.g.a(15, Long.valueOf(j));
            this.g.a(4, null);
        }
    }

    public void a(int i) {
        if (this.g != null) {
            this.g.a(8, null);
        }
    }

    public void b() {
        if (this.K != null) {
            this.K.setTouchListener(null);
        }
        super.b();
    }

    public void a(MediaItem mediaItem, Bitmap bitmap, g gVar) {
        super.a(mediaItem, bitmap, gVar);
        c(com.sds.android.ttpod.framework.storage.environment.b.V());
        i(com.sds.android.ttpod.framework.storage.environment.b.T());
    }

    private void c(int i) {
        if (this.K == null) {
            return;
        }
        if (i < 0) {
            this.K.setAlign(this.b);
        } else {
            this.K.setAlign(Align.values()[i]);
        }
    }

    public void a(View view) {
        e c = c();
        if (view != this.a || c == null) {
            super.a(view);
            return;
        }
        c.z();
        com.sds.android.ttpod.framework.a.b.b.a("portrait_setting_lyric");
    }

    protected void a(Object obj, TTPodButton tTPodButton) {
        super.a(obj, tTPodButton);
        if ("LyricAdjustButton".equals(obj)) {
            this.a = tTPodButton;
            this.a.setContentDescription("play_page_lyric_adjust");
        }
    }
}
