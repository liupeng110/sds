package com.sds.android.ttpod.framework.modules.theme;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import com.sds.android.cloudapi.ttpod.a.aa;
import com.sds.android.cloudapi.ttpod.result.BackgroundMoreCheckResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.base.j;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.modules.skin.q;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@j(a = {a.LOAD_SKIN_FINISHED})
/* ThemeModule */
public final class d extends b {
    private boolean a = false;
    private int b;
    private int c;
    private com.sds.android.sdk.lib.e.b d = new com.sds.android.sdk.lib.e.b("themeWorkThreadPool", 1, 0);

    protected c id() {
        return c.THEME;
    }

    public void onCreate() {
        super.onCreate();
        this.b = q.a;
        this.c = q.c;
        com.sds.android.ttpod.framework.a.b.a(sContext);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(a.LOAD_BACKGROUND_LIST, i.a(cls, "loadBackgroundList", Boolean.class));
        map.put(a.SET_BACKGROUND, i.a(cls, "setBackground", String.class));
        map.put(a.DELETE_BACKGROUND, i.a(cls, "deleteBackground", String.class));
        map.put(a.LOAD_BACKGROUND, i.a(cls, "loadBackground", new Class[0]));
        map.put(a.GET_BACKGROUND, i.a(cls, "getBackground", new Class[0]));
        map.put(a.LOAD_THEME_LIST, i.a(cls, "loadThemeList", new Class[0]));
        map.put(a.LOAD_SKIN_FINISHED, i.a(cls, "loadSkinFinished", com.sds.android.ttpod.framework.modules.skin.j.class));
        map.put(a.DECODE_BACKGROUND_THUMBNAIL, i.a(cls, "decodeBackgroundThumbnail", a.class));
        map.put(a.REQUEST_BACKGROUND_MORE, i.a(cls, "requestBackgroundMore", new Class[0]));
    }

    public void loadThemeList() {
        List arrayList = new ArrayList();
        arrayList.add("cool_white");
        arrayList.add("transparent_glass");
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_THEME_LIST, arrayList), c.THEME);
    }

    public void loadSkinFinished(com.sds.android.ttpod.framework.modules.skin.j jVar) {
        if (com.sds.android.ttpod.framework.storage.environment.b.ab()) {
            a aVar = new a(com.sds.android.ttpod.framework.storage.environment.b.aa());
            aVar.a(jVar.a(sContext));
            com.sds.android.ttpod.framework.storage.a.a.a().a(aVar);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_BACKGROUND, aVar.e()), c.THEME);
        }
    }

    public Drawable getBackground() {
        Drawable b = b();
        if (b != null) {
            return b;
        }
        loadBackground();
        return null;
    }

    public void setBackground(String str) {
        com.sds.android.ttpod.framework.storage.environment.b.i(str);
        loadBackground();
    }

    public void loadBackground() {
        if (!this.a) {
            this.d.a(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    a a;
                    Throwable th;
                    this.a.a = true;
                    a aVar = new a(com.sds.android.ttpod.framework.storage.environment.b.aa());
                    Bitmap bitmap = null;
                    try {
                        switch (aVar.a()) {
                            case FOLLOW_SKIN:
                                a = this.a.a(aVar);
                                if (a == null) {
                                    return;
                                }
                                break;
                            case ADD_BY_USER:
                                bitmap = com.sds.android.ttpod.framework.a.b.a(aVar, 1);
                            case ORIGINAL:
                                if (bitmap != null) {
                                    aVar.a(new BitmapDrawable(bitmap));
                                    a = aVar;
                                    break;
                                }
                                com.sds.android.ttpod.framework.storage.environment.b.i("follow_skin");
                                a aVar2 = new a("follow_skin");
                                try {
                                    a = this.a.a(aVar2);
                                    if (a == null) {
                                        return;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    a = aVar2;
                                    th.printStackTrace();
                                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_BACKGROUND, a.e()), c.THEME);
                                    this.a.a = false;
                                }
                                break;
                            default:
                                throw new IllegalArgumentException("the background type not supported!");
                        }
                        try {
                            com.sds.android.ttpod.framework.storage.a.a.a().a(a);
                        } catch (Throwable th3) {
                            th = th3;
                            th.printStackTrace();
                            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_BACKGROUND, a.e()), c.THEME);
                            this.a.a = false;
                        }
                    } catch (Throwable th22) {
                        Throwable th4 = th22;
                        a = aVar;
                        th = th4;
                        th.printStackTrace();
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_BACKGROUND, a.e()), c.THEME);
                        this.a.a = false;
                    }
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_BACKGROUND, a.e()), c.THEME);
                    this.a.a = false;
                }
            });
        }
    }

    private a a(a aVar) {
        com.sds.android.ttpod.framework.modules.skin.j l = com.sds.android.ttpod.framework.storage.a.a.a().l();
        if (l == null || l.a(sContext) == null) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.LOAD_SKIN, new Object[0]));
            this.a = false;
            return null;
        }
        aVar.a(l.a(sContext));
        return aVar;
    }

    public void loadBackgroundList(Boolean bool) {
        this.d.a(new com.sds.android.ttpod.framework.modules.skin.b(bool));
    }

    public void deleteBackground(String str) {
        a aVar = new a(str);
        com.sds.android.ttpod.framework.a.b.b(aVar);
        g.b(aVar.toString(), this.b, this.c);
    }

    public void decodeBackgroundThumbnail(final a aVar) {
        this.d.a(new Runnable(this) {
            final /* synthetic */ d b;

            public void run() {
                Bitmap a;
                if (a.a.FOLLOW_SKIN != aVar.a() || com.sds.android.ttpod.framework.storage.a.a.a().l() == null) {
                    String aVar = aVar.toString();
                    a = g.a(aVar, this.b.b, this.b.c);
                    if (a == null) {
                        a = com.sds.android.ttpod.framework.a.b.a(aVar);
                        g.a(aVar, this.b.b, this.b.c, a);
                    }
                } else {
                    a = this.b.a(com.sds.android.ttpod.framework.storage.a.a.a().l().a(d.sContext));
                }
                aVar.a(a);
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.BACKGROUND_THUMBNAIL_CREATED, aVar), c.THEME);
            }
        });
    }

    private Drawable b() {
        a m = com.sds.android.ttpod.framework.storage.a.a.a().m();
        if (m == null || !com.sds.android.ttpod.framework.storage.environment.b.aa().equals(m.toString())) {
            return null;
        }
        Drawable drawable;
        Drawable e = m.e();
        if (e != null && (e instanceof BitmapDrawable)) {
            Bitmap bitmap = ((BitmapDrawable) e).getBitmap();
            if (bitmap == null || bitmap.isRecycled()) {
                drawable = null;
                return drawable != null ? null : drawable;
            }
        }
        drawable = e;
        if (drawable != null) {
        }
    }

    private Bitmap a(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int d = com.sds.android.ttpod.common.c.a.d() / 3;
        if (drawable == null) {
            drawable = new ColorDrawable(ViewCompat.MEASURED_STATE_MASK);
            drawable.setBounds(0, 0, d, d);
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = d;
        }
        if (intrinsicHeight <= 0) {
            intrinsicHeight = d;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public void requestBackgroundMore() {
        this.d.a(new Runnable(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void run() {
                boolean z = true;
                BackgroundMoreCheckResult backgroundMoreCheckResult = (BackgroundMoreCheckResult) aa.d().g();
                if (backgroundMoreCheckResult.getCode() == 1) {
                    if (1 != backgroundMoreCheckResult.getData().getStatus()) {
                        z = false;
                    }
                    com.sds.android.ttpod.framework.storage.environment.b.ai(z);
                    com.sds.android.ttpod.framework.storage.environment.b.d(backgroundMoreCheckResult.getCreateTime());
                }
            }
        });
    }
}
