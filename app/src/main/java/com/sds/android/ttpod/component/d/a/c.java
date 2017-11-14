package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AudioEffectUser;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.a.a;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.audiofx.TTEqualizer.Settings;
import com.sds.android.ttpod.media.mediastore.MediaItem;

/* EffectShareDialog */
public class c extends a {
    private View a;
    private CheckedTextView b;
    private TextView c;
    private MediaItem d;
    private int e = -1;
    private int f = 0;
    private String g = "";
    private short[] h = new short[]{(short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0};
    private int i = 0;
    private int j = 0;
    private float k = 0.0f;
    private int l = 0;
    private boolean m = false;
    private int n = 0;
    private AudioEffectUser o;
    private boolean p;

    public c(Context context, MediaItem mediaItem, AudioEffectUser audioEffectUser, boolean z) {
        super(context);
        this.d = mediaItem;
        this.o = audioEffectUser;
        this.p = z;
        b();
        d();
        h();
        a((int) R.string.save, new a.a<g>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(g gVar) {
                this.a.c();
            }
        }, (int) R.string.cancel, null);
        this.g = Build.MODEL;
    }

    private void b() {
        AudioEffectParam t = e.a(BaseApplication.e()).t();
        String str = "";
        if (t != null) {
            this.e = com.sds.android.ttpod.framework.modules.core.audioeffect.e.a(new Settings(t.g()).getName());
        }
    }

    private void c() {
        String title = this.d.getTitle();
        String artist = this.d.getArtist();
        if (this.d != null) {
            if (this.e == -1) {
                e(false);
                f.a("没有选择音乐类型!");
                return;
            }
            e(true);
            Boolean valueOf = Boolean.valueOf(true);
            if (!(b.av() && this.o != null && this.o.getAllowAdd() && this.b.getVisibility() == 0 && this.b.isChecked())) {
                valueOf = Boolean.valueOf(false);
            }
            String a = new com.sds.android.ttpod.framework.modules.core.audioeffect.b(b.at().getUserId(), title, artist, this.h, this.i, this.j, this.l, this.n, this.k, this.m).a();
            com.sds.android.ttpod.framework.modules.core.audioeffect.a aVar = new com.sds.android.ttpod.framework.modules.core.audioeffect.a();
            aVar.a(a);
            aVar.a(this.d.getSongID());
            aVar.b(artist);
            aVar.c(title);
            aVar.a(this.e);
            aVar.b(this.f);
            aVar.d(this.g);
            aVar.f(getContext().getString(R.string.me));
            aVar.e(this.i);
            aVar.f(this.j);
            aVar.g(this.l);
            aVar.h(this.n);
            aVar.a(this.k);
            aVar.a(this.m);
            aVar.a(this.h);
            aVar.b(System.currentTimeMillis());
            aVar.g(this.d.getLocalDataSource());
            g.a("EffectShareDialog", "saveToLocal " + aVar);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SAVE_EFFECT, this.d, aVar, valueOf));
            t.a("PAGE_CLICK", r.ACTION_EFFECT_AJUST_SAVE_OK, s.PAGE_NONE, s.PAGE_NONE);
            com.sds.android.ttpod.framework.a.b.f.v();
        }
    }

    private void d() {
        AudioEffectParam t = e.a(BaseApplication.e()).t();
        if (t != null) {
            this.h = new Settings(t.g()).getBandLevels();
            this.k = t.e();
            this.i = t.a();
            this.j = t.b();
            this.l = t.c();
            this.n = t.d();
            this.m = t.f();
        }
    }

    private boolean g() {
        for (int length = this.h.length - 1; length >= 0; length--) {
            if (this.h[length] != (short) 0) {
                return true;
            }
        }
        if (this.i == 0 && this.j == 0 && this.k == 0.0f && this.l == 0 && this.n == 0) {
            return false;
        }
        return true;
    }

    private void h() {
        int i;
        int i2 = 8;
        this.b = (CheckedTextView) this.a.findViewById(R.id.checked_share);
        this.c = (TextView) this.a.findViewById(R.id.checked_share_noajust);
        if (this.o == null || !this.o.getAllowAdd()) {
            i = 8;
        } else if (g()) {
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.b.toggle();
                }
            });
            i = 8;
            i2 = 0;
        } else {
            i = 0;
        }
        this.b.setVisibility(i2);
        this.c.setVisibility(i);
    }

    protected View a(Context context, ViewGroup viewGroup) {
        this.a = View.inflate(context, R.layout.dialog_effect_share, null);
        setTitle((int) R.string.effect_share_dialog_title);
        return this.a;
    }

    protected <T> T a() {
        return null;
    }
}
