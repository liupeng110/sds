package com.sds.android.ttpod.fragment.main.findsong;

import android.content.Context;
import android.content.Intent;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.unicomflow.OrderFlowDetailActivity;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.modules.h.e;
import com.sds.android.ttpod.framework.storage.environment.b;

/* MvManager */
public class a {
    private static boolean a;

    public static void a(Context context, b bVar) {
        b(context, bVar, 1);
    }

    public static void b(Context context, b bVar) {
        b(context, bVar, 0);
    }

    private static void b(Context context, b bVar, int i) {
        if (!c.e()) {
            f.a(context.getString(R.string.cannot_play_for_network_error));
        } else if (2 == c.d() || e.d()) {
            bVar.a();
        } else {
            c(context, bVar, i);
        }
    }

    private static void c(final Context context, final b bVar, final int i) {
        if (a) {
            d(context, bVar, i);
            return;
        }
        final boolean z = i == 0;
        int i2 = z ? R.string.play_mv_warning : R.string.download_mv_warning;
        int i3 = z ? R.string.continue_play : R.string.continue_download;
        if (z) {
            t.a(r.ACTION_MV_2G3G_PLAY, s.PAGE_NONE);
        } else {
            t.a(r.ACTION_MV_2G3G_DOWNLAD, s.PAGE_NONE);
        }
        h hVar = new h(context, i2, new com.sds.android.ttpod.common.a.a.a<h>() {
            public void a(h hVar) {
                a.a = true;
                hVar.dismiss();
                if (z) {
                    t.a(r.ACTION_MV_2G3G_PLAY_CONTINUE, s.PAGE_NONE);
                } else {
                    t.a(r.ACTION_MV_2G3G_DOWNLAD_CONTINUE, s.PAGE_NONE);
                }
                a.d(context, bVar, i);
            }
        }, null);
        hVar.b(i3);
        hVar.setTitle((int) R.string.prompt_title);
        hVar.show();
    }

    private static void d(Context context, b bVar, int i) {
        if (e.f()) {
            e(context, bVar, i);
        } else {
            bVar.a();
        }
    }

    private static void e(final Context context, final b bVar, int i) {
        if (b.bt()) {
            String string = context.getString(R.string.mv_download_unicom_net_prompt);
            String string2 = context.getString(R.string.prompt_title);
            aa.y();
            t.a(1126, s.PAGE_NONE);
            com.sds.android.ttpod.activities.unicomflow.b.a(context, string, string2, (int) R.string.unicom_open_service, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.activities.unicomflow.a>() {
                public void a(com.sds.android.ttpod.activities.unicomflow.a aVar) {
                    boolean b = aVar.b();
                    if (b) {
                        aa.B();
                        t.a(1129, s.PAGE_NONE);
                    }
                    b.af(!b);
                    aa.A();
                    context.startActivity(new Intent(context, OrderFlowDetailActivity.class));
                    t.a(1127, s.PAGE_UNICOM_ORDER);
                }
            }, (int) R.string.cancel, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.activities.unicomflow.a>() {
                public void a(com.sds.android.ttpod.activities.unicomflow.a aVar) {
                    boolean b = aVar.b();
                    if (b) {
                        aa.B();
                        t.a(1129, s.PAGE_NONE);
                    }
                    b.af(!b);
                    aa.z();
                    t.a(1128, s.PAGE_NONE);
                    bVar.a();
                }
            }, new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                }
            });
            return;
        }
        bVar.a();
    }
}
