package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.TypedValue;
import com.sds.android.ttpod.component.landscape.b.i;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.e.g;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;

/* LandscapeMediaHelper */
public class d implements com.sds.android.ttpod.component.landscape.i.a {
    private int[] a = new int[64];
    private boolean b;
    private boolean c;
    private MediaItem d;
    private boolean e;
    private int f = 12;
    private int g = -1;
    private boolean h;
    private Bitmap i;
    private boolean j;
    private long k;
    private g l;

    /* LandscapeMediaHelper */
    public interface a {
        void d();
    }

    /* LandscapeMediaHelper */
    public interface b {
        void a(String str, String str2);
    }

    /* LandscapeMediaHelper */
    public interface c {
        void a(int i);
    }

    /* LandscapeMediaHelper */
    public interface d {
        void a(Bitmap bitmap);
    }

    /* LandscapeMediaHelper */
    public interface e {
        void a(int[] iArr);
    }

    public d(Context context) {
        this.l = new g(context, TypedValue.applyDimension(2, (float) b.d(), context.getResources().getDisplayMetrics()), b.b());
        i.a().a(this, 10);
    }

    public void a(MediaItem mediaItem, int i) {
        if (mediaItem != null) {
            synchronized (this) {
                this.b = true;
                this.c = true;
                this.d = mediaItem;
                this.e = true;
                if (this.g != -1) {
                    if (i == this.g) {
                        this.f = 12;
                    } else if (i > this.g) {
                        this.f = 11;
                    } else {
                        this.f = 10;
                    }
                }
                this.g = i;
            }
        }
    }

    public void a(Bitmap bitmap) {
        synchronized (this) {
            if (bitmap != this.i) {
                this.h = true;
                this.i = bitmap;
            }
        }
    }

    public void a(g gVar) {
        this.l.a(gVar);
    }

    public void a(long j) {
        synchronized (this) {
            this.j = true;
            this.k = j;
        }
    }

    public void a(float f) {
        i b = h.a().b();
        synchronized (this) {
            if (this.b && (b instanceof a)) {
                ((a) b).d();
                this.b = false;
            }
            if (this.c && (b instanceof b)) {
                ((b) b).a(this.d.getTitle(), this.d.getArtist());
                this.c = false;
            }
            if (this.e && (b instanceof c)) {
                ((c) b).a(this.f);
                this.e = false;
            }
            if (this.h && (b instanceof d)) {
                ((d) b).a(this.i);
                this.h = false;
            }
            if (this.j && (b instanceof com.sds.android.ttpod.component.landscape.g.a)) {
                if (this.l.a() == null) {
                    this.l.a((com.sds.android.ttpod.component.landscape.g.a) b);
                }
                this.l.a(this.k);
                this.j = false;
            }
            if (b instanceof e) {
                if (com.sds.android.ttpod.framework.support.e.a(BaseApplication.e()).n() == PlayStatus.STATUS_PLAYING && com.sds.android.ttpod.framework.support.e.a(BaseApplication.e()).a(this.a, 64)) {
                    ((e) b).a(this.a);
                } else {
                    ((e) b).a(null);
                }
            }
        }
    }
}
