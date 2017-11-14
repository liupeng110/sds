package com.sds.android.ttpod.framework.support.a;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;
import com.sds.android.cloudapi.ttpod.result.AudioEffectItemResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.modules.core.audioeffect.a;
import com.sds.android.ttpod.framework.modules.core.audioeffect.d;
import com.sds.android.ttpod.framework.modules.core.audioeffect.e;
import com.sds.android.ttpod.media.audiofx.TTEqualizer.Settings;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.text.SimpleDateFormat;
import java.util.Date;

/* AudioEffectLoader */
public class b {
    private int a = 0;
    private c b;
    private Settings c = null;
    private Handler d = new Handler();
    private String e = "";
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private float j = 0.0f;
    private boolean k = true;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private String o = "";
    private String p = "";
    private int q = 0;
    private Context r = null;

    public b(Context context, c cVar) {
        this.r = context;
        if (cVar == null) {
            throw new IllegalArgumentException("attachedMediaPlayerProxy must not be null!");
        }
        this.b = cVar;
        this.a = 4;
        this.c = new Settings(e.b(), (short) 10, e.b(e.b()));
    }

    public void a(boolean z) {
        this.l = z;
    }

    String a() {
        Object audioEffectParam = new AudioEffectParam();
        audioEffectParam.b(this.f);
        audioEffectParam.c(this.g);
        audioEffectParam.d(this.h);
        audioEffectParam.e(this.i);
        audioEffectParam.a(this.j);
        audioEffectParam.a(this.k);
        audioEffectParam.b(com.sds.android.ttpod.framework.storage.environment.b.an());
        audioEffectParam.a(this.q);
        audioEffectParam.a(this.p);
        audioEffectParam.f(this.a);
        audioEffectParam.c(this.e);
        if (this.c == null) {
            this.c = new Settings(e.b(), (short) 10, e.b(e.b()));
        }
        audioEffectParam.b(this.c.toString());
        return f.a(audioEffectParam);
    }

    void b() {
        this.c = i();
        this.g = com.sds.android.ttpod.framework.storage.environment.b.ap();
        this.f = com.sds.android.ttpod.framework.storage.environment.b.am();
        this.h = com.sds.android.ttpod.framework.storage.environment.b.aq();
        this.i = e(com.sds.android.ttpod.framework.storage.environment.b.al());
        this.j = com.sds.android.ttpod.framework.storage.environment.b.ar();
        this.k = com.sds.android.ttpod.framework.storage.environment.b.ao();
        this.p = "";
        this.q = 0;
        this.a = 4;
        b(true);
    }

    void a(Boolean bool) {
        this.n = bool.booleanValue();
    }

    boolean c() {
        return this.n;
    }

    void b(Boolean bool) {
        this.m = bool.booleanValue();
    }

    void a(AudioEffectItem audioEffectItem, boolean z) {
        this.m = z;
        if (z) {
            this.f = audioEffectItem.getDataBass();
            this.g = audioEffectItem.getDataTreble();
            this.h = audioEffectItem.getDataVirtualizer();
            this.i = e(audioEffectItem.getDataReverb());
            this.j = audioEffectItem.getDataBalance();
            this.k = audioEffectItem.getDataIsLimit();
            this.p = audioEffectItem.getNickName();
            this.q = audioEffectItem.getStyle();
            this.e = audioEffectItem.getID();
            this.c = new Settings(e.a(this.q), (short) 10, audioEffectItem.getDataEqualizer());
            this.l = false;
            b(true);
            this.a = 3;
            return;
        }
        b(audioEffectItem, false);
    }

    public void a(MediaItem mediaItem, int i) {
        if (mediaItem == null) {
            com.sds.android.ttpod.framework.storage.environment.b.D(false);
            if (this.c == null) {
                this.c = new Settings(e.b(), (short) 10, e.b(e.b()));
            }
            switch (i) {
                case 1:
                    this.f = com.sds.android.ttpod.framework.storage.environment.b.am();
                    b(this.f);
                    break;
                case 2:
                    this.g = com.sds.android.ttpod.framework.storage.environment.b.ap();
                    c(this.g);
                    break;
                case 3:
                    this.h = com.sds.android.ttpod.framework.storage.environment.b.aq();
                    d(this.h);
                    break;
                case 4:
                    this.i = e(com.sds.android.ttpod.framework.storage.environment.b.al());
                    a(this.i);
                    break;
                case 5:
                    this.j = com.sds.android.ttpod.framework.storage.environment.b.ar();
                    a(this.j);
                    break;
                case 6:
                    this.k = com.sds.android.ttpod.framework.storage.environment.b.ao();
                    c(this.k);
                    break;
                case 7:
                    this.c = new Settings(com.sds.android.ttpod.framework.storage.environment.b.aj());
                    a(this.c);
                    break;
                case 8:
                    b(false);
                    break;
            }
            com.sds.android.ttpod.framework.storage.environment.b.j(this.c.toString());
            com.sds.android.ttpod.framework.storage.environment.b.l(this.f);
            com.sds.android.ttpod.framework.storage.environment.b.m(this.g);
            com.sds.android.ttpod.framework.storage.environment.b.n(this.h);
            com.sds.android.ttpod.framework.storage.environment.b.k(this.i);
            com.sds.android.ttpod.framework.storage.environment.b.a(this.j);
            com.sds.android.ttpod.framework.storage.environment.b.F(this.k);
            this.p = "";
            this.q = 0;
            this.e = "";
            this.a = 4;
        } else if (this.m) {
            this.m = false;
        } else if (this.n) {
            this.n = false;
            b(false);
        } else if (this.l) {
            b();
            this.a = 4;
        } else {
            if (mediaItem.isOnline() && m.a(mediaItem.getLocalDataSource())) {
                com.sds.android.ttpod.framework.storage.environment.b.E(false);
                b(mediaItem);
            } else {
                com.sds.android.ttpod.framework.storage.environment.b.E(false);
                a(mediaItem);
            }
            b(true);
        }
    }

    private void b(boolean z) {
        a(this.c);
        a(this.i);
        b(this.f);
        c(this.g);
        d(this.h);
        c(this.k);
        a(this.j);
        if (z) {
            this.r.sendBroadcast(new Intent(Action.AUDIOEFFECT_CHANGED));
        }
    }

    private void b(AudioEffectItem audioEffectItem, boolean z) {
        a(new Settings(e.a(this.q), (short) 10, audioEffectItem.getDataEqualizer()));
        a(audioEffectItem.getDataReverb());
        b(audioEffectItem.getDataBass());
        c(audioEffectItem.getDataTreble());
        d(audioEffectItem.getDataVirtualizer());
        c(audioEffectItem.getDataIsLimit());
        a(audioEffectItem.getDataBalance());
        if (z) {
            this.r.sendBroadcast(new Intent(Action.AUDIOEFFECT_CHANGED));
        }
    }

    private void a(final MediaItem mediaItem) {
        if (com.sds.android.ttpod.framework.storage.environment.b.ag()) {
            String b = d.b(mediaItem.getSongID(), mediaItem.getTitle(), mediaItem.getArtist());
            if (mediaItem.getSongID() != null && d.b(mediaItem) && com.sds.android.sdk.lib.util.e.b(b)) {
                this.a = 1;
                b(b);
                if (com.sds.android.ttpod.framework.storage.environment.b.ah()) {
                    com.sds.android.ttpod.framework.storage.environment.b.D(false);
                    return;
                }
                return;
            }
            h();
            if (2 == c.d() || !com.sds.android.ttpod.framework.storage.environment.b.I() || com.sds.android.ttpod.framework.storage.environment.b.ah()) {
                if (com.sds.android.ttpod.framework.storage.environment.b.ah()) {
                    com.sds.android.ttpod.framework.storage.environment.b.D(false);
                }
                this.d.post(new Runnable(this) {
                    final /* synthetic */ b b;

                    public void run() {
                        com.sds.android.cloudapi.ttpod.a.e.a(mediaItem.getTitle(), mediaItem.getArtist(), 1, 1).a(new p<AudioEffectItemResult>(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                                b((AudioEffectItemResult) baseResult);
                            }

                            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                                a((AudioEffectItemResult) baseResult);
                            }

                            public void a(AudioEffectItemResult audioEffectItemResult) {
                                if (audioEffectItemResult.getEffectList().size() > 0) {
                                    AudioEffectItem audioEffectItem = (AudioEffectItem) audioEffectItemResult.getEffectList().get(0);
                                    if (audioEffectItem != null && audioEffectItem.getPickCount() > 1) {
                                        this.a.b.a(mediaItem.getSongID(), mediaItem.getTitle(), mediaItem.getArtist(), audioEffectItem);
                                    }
                                }
                            }

                            public void b(AudioEffectItemResult audioEffectItemResult) {
                            }
                        });
                    }
                });
                return;
            }
            return;
        }
        f();
    }

    private void f() {
        this.f = com.sds.android.ttpod.framework.storage.environment.b.am();
        this.g = com.sds.android.ttpod.framework.storage.environment.b.ap();
        this.h = com.sds.android.ttpod.framework.storage.environment.b.aq();
        this.i = e(com.sds.android.ttpod.framework.storage.environment.b.al());
        this.j = com.sds.android.ttpod.framework.storage.environment.b.ar();
        this.k = com.sds.android.ttpod.framework.storage.environment.b.ao();
        this.p = "";
        this.q = 0;
        this.e = "";
        this.c = new Settings(e.b(), (short) 10, e.b(e.b()));
        String aj = com.sds.android.ttpod.framework.storage.environment.b.aj();
        if (!m.a(aj)) {
            this.c = new Settings(aj);
        }
        this.a = 4;
    }

    private String a(long j) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(j));
    }

    private boolean a(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        try {
            if ((new Date(System.currentTimeMillis()).getTime() - new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(str).getTime()) / 3600000 <= 24) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void g() {
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0.0f;
        this.k = true;
        this.p = "";
        this.q = 0;
        this.e = "";
        this.c = new Settings(e.b(), (short) 10, e.b(e.b()));
    }

    private void h() {
        g();
        this.a = 0;
    }

    private void b(String str) {
        String i = com.sds.android.sdk.lib.util.e.i(str);
        if (i != null) {
            a aVar = (a) f.a(i, a.class);
            if (aVar != null) {
                this.p = aVar.g();
                this.q = aVar.e();
                this.f = aVar.h();
                this.g = aVar.i();
                this.h = aVar.j();
                this.i = e(aVar.k());
                this.j = aVar.l();
                this.k = aVar.m();
                this.e = aVar.a();
                this.c = new Settings(e.a(this.q), (short) 10, aVar.n());
                this.o = a(aVar.o());
                return;
            }
            h();
            return;
        }
        h();
    }

    private void b(MediaItem mediaItem) {
        if (com.sds.android.ttpod.framework.storage.environment.b.ag()) {
            try {
                String b = d.b(mediaItem.getSongID(), mediaItem.getTitle(), mediaItem.getArtist());
                if (com.sds.android.sdk.lib.util.e.b(b) && d.b(mediaItem)) {
                    this.a = 1;
                    b(b);
                    return;
                }
                h();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        f();
    }

    private Settings i() {
        try {
            String aj = com.sds.android.ttpod.framework.storage.environment.b.aj();
            if (aj == null || m.a(aj)) {
                return new Settings(e.b(), (short) 10, e.b(e.b()));
            }
            return new Settings(aj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String d() {
        if (this.c == null) {
            this.c = new Settings(e.b(), (short) 10, e.b(e.b()));
        }
        return this.c.toString();
    }

    private void a(Settings settings) {
        if (settings != null) {
            this.b.a(settings);
        }
        if (settings == null || settings.isFlat()) {
            this.b.a(false);
        } else {
            this.b.a(true);
        }
    }

    private void a(int i) {
        this.b.g(i);
        if (i == 0) {
            this.b.f(false);
        } else {
            this.b.f(true);
        }
    }

    private void b(int i) {
        this.b.d(i);
        if (i == 0) {
            this.b.b(false);
        } else {
            this.b.b(true);
        }
    }

    private void c(boolean z) {
        this.b.c(z);
    }

    private void c(int i) {
        this.b.e(i);
        if (i == 0) {
            this.b.d(false);
        } else {
            this.b.d(true);
        }
    }

    private void d(int i) {
        this.b.f(i);
        if (i == 0) {
            this.b.e(false);
        } else {
            this.b.e(true);
        }
    }

    private void a(float f) {
        this.b.a(f);
    }

    public void e() {
        g();
        com.sds.android.ttpod.framework.storage.environment.b.j(this.c.toString());
        com.sds.android.ttpod.framework.storage.environment.b.l(this.f);
        com.sds.android.ttpod.framework.storage.environment.b.m(this.g);
        com.sds.android.ttpod.framework.storage.environment.b.n(this.h);
        com.sds.android.ttpod.framework.storage.environment.b.k(this.i);
        com.sds.android.ttpod.framework.storage.environment.b.a(this.j);
        com.sds.android.ttpod.framework.storage.environment.b.F(this.k);
        b(true);
        this.a = 1;
    }

    private void a(Long l, String str, String str2, AudioEffectItem audioEffectItem) {
        String a = d.a(l, str, str2);
        Object aVar = new a();
        aVar.a(audioEffectItem.getID());
        aVar.a(l);
        aVar.b(str2);
        aVar.c(str);
        aVar.a(audioEffectItem.getStyle());
        aVar.b(audioEffectItem.getOutput());
        aVar.d(audioEffectItem.getDevice());
        aVar.c(audioEffectItem.getTotal());
        aVar.d(audioEffectItem.getPickCount());
        aVar.f(audioEffectItem.getNickName());
        aVar.e(audioEffectItem.getPic());
        aVar.a((long) audioEffectItem.getUserId());
        aVar.e(audioEffectItem.getDataBass());
        aVar.f(audioEffectItem.getDataTreble());
        aVar.g(audioEffectItem.getDataVirtualizer());
        aVar.h(audioEffectItem.getDataReverb());
        aVar.a(audioEffectItem.getDataBalance());
        aVar.a(audioEffectItem.getDataIsLimit());
        aVar.a(audioEffectItem.getDataEqualizer());
        aVar.b(System.currentTimeMillis());
        com.sds.android.sdk.lib.util.e.a(f.a(aVar), a);
    }

    private int e(int i) {
        return i > com.sds.android.ttpod.framework.storage.environment.b.ai() + -1 ? 0 : i;
    }
}
