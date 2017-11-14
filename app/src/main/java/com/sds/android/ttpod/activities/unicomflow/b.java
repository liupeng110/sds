package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.MainActivity;
import com.sds.android.ttpod.common.a.a.a;
import com.sds.android.ttpod.component.d.a.s;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.a.b.aa.c;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.modules.h.e;
import java.util.ArrayList;
import java.util.List;

/* UnicomFlowManager */
public class b {
    private static final String a = b.class.getSimpleName();
    private static s b;
    private static List<Activity> c = new ArrayList();
    private static a d;

    public static void a(Context context, String str) {
        b = new s(context);
        b.a((CharSequence) str);
        b.show();
    }

    public static void a() {
        if (b != null) {
            b.dismiss();
        }
    }

    public static void a(Context context, String str, int i, int i2, a<a> aVar, int i3, a<a> aVar2, OnClickListener onClickListener) {
        a aVar3 = new a(context, str, i2, aVar, i3, aVar2, onClickListener);
        aVar3.setTitle((int) R.string.unicom_dialog_month_end_title);
        aVar3.show();
    }

    public static void a(final Activity activity) {
        d = new a((Context) activity, activity.getString(R.string.unicom_flow_trial_message), activity.getString(R.string.unicom_flow_trial_title), (int) R.string.unicom_dialog_button_trial, new a<a>() {
            public void a(a aVar) {
                aa.b();
                aVar.dismiss();
                b.a(activity, TrialActivity.class, c.TRIAL_DIALOG, false);
            }
        }, (int) R.string.unicom_dialog_button_not_need, new a<a>() {
            public void a(a aVar) {
                aVar.dismiss();
                aa.d();
            }
        }, new OnClickListener() {
            public void onClick(View view) {
                b.d.dismiss();
                aa.c();
                activity.startActivity(new Intent(activity, FaqActivity.class));
            }
        });
        d.setTitle((int) R.string.unicom_trial_dialog_title);
        d.setCanceledOnTouchOutside(false);
        d.show();
        aa.a();
    }

    public static void a(final Activity activity, final com.sds.android.ttpod.framework.modules.h.b bVar) {
        a aVar = new a(activity, activity.getString(R.string.unicom_flow_open_message), R.string.unicom_dialog_detail, new a<a>() {
            public void a(a aVar) {
                aVar.dismiss();
                b.a(activity, OrderFlowDetailActivity.class, com.sds.android.ttpod.framework.a.b.aa.b.POP_OPEN_DIALOG, false);
                aa.b(bVar);
                t.a(1124, com.sds.android.ttpod.framework.a.b.s.PAGE_UNICOM_ORDER);
            }
        }, R.string.unicom_dialog_know, new a<a>() {
            public void a(a aVar) {
                aVar.dismiss();
                aa.c(bVar);
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", 1125, 0);
                sUserEvent.setPageParameter(true);
                sUserEvent.post();
            }
        }, null);
        aVar.setTitle((int) R.string.unicom_dialog_title);
        aVar.setCanceledOnTouchOutside(false);
        aVar.show();
        com.sds.android.ttpod.framework.a.b.b.a("unicom_user_flow_dialog");
        aa.a(bVar);
        new SUserEvent("PAGE_CLICK", 1123, 0).post();
    }

    public static void b(final Activity activity, final com.sds.android.ttpod.framework.modules.h.b bVar) {
        a aVar = new a(activity, activity.getString(R.string.unicom_flow_month_message), R.string.unicom_dialog_detail, new a<a>() {
            public void a(a aVar) {
                aVar.dismiss();
                b.a(activity, OrderFlowDetailActivity.class, com.sds.android.ttpod.framework.a.b.aa.b.POP_OPEN_DIALOG, false);
                aa.b(bVar);
                t.a(1134, com.sds.android.ttpod.framework.a.b.s.PAGE_UNICOM_ORDER);
            }
        }, R.string.unicom_dialog_know, new a<a>() {
            public void a(a aVar) {
                aVar.dismiss();
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", 1135, 0);
                sUserEvent.setPageParameter(true);
                sUserEvent.post();
                aa.c(bVar);
            }
        }, null);
        aVar.setTitle((int) R.string.unicom_dialog_title);
        aVar.setCanceledOnTouchOutside(false);
        aVar.show();
        aa.a(bVar);
        com.sds.android.ttpod.framework.a.b.b.a("unicom_month_begin_dialog");
        new SUserEvent("PAGE_CLICK", 1133, 0).post();
    }

    public static void c(final Activity activity, final com.sds.android.ttpod.framework.modules.h.b bVar) {
        float bs = com.sds.android.ttpod.framework.storage.environment.b.bs();
        Context context = activity;
        a aVar = new a(context, activity.getString(R.string.unicom_flow_use_dialog, new Object[]{bs + "MB"}), R.string.unicom_dialog_detail, new a<a>() {
            public void a(a aVar) {
                aa.f();
                aVar.dismiss();
                b.a(activity, OrderFlowDetailActivity.class, com.sds.android.ttpod.framework.a.b.aa.b.POP_OPEN_DIALOG, false);
                aa.b(bVar);
                t.a(1131, com.sds.android.ttpod.framework.a.b.s.PAGE_UNICOM_ORDER);
            }
        }, R.string.unicom_dialog_know, new a<a>() {
            public void a(a aVar) {
                aa.g();
                new SUserEvent("PAGE_CLICK", 1132, 0).post();
                aVar.dismiss();
            }
        }, null);
        aVar.setTitle((int) R.string.unicom_dialog_title);
        aVar.setCanceledOnTouchOutside(false);
        aVar.show();
        aa.e();
        com.sds.android.ttpod.framework.a.b.b.a("unicom_30_dialog");
        new SUserEvent("PAGE_CLICK", 1130, 0).post();
    }

    public static void a(Context context, String str, String str2, int i, a<a> aVar, int i2, a<a> aVar2, OnCheckedChangeListener onCheckedChangeListener) {
        a aVar3 = new a(context, str, i, (a) aVar, i2, (a) aVar2, true, onCheckedChangeListener);
        aVar3.setTitle((CharSequence) str2);
        aVar3.show();
    }

    public static void a(final UnicomFlowActivity unicomFlowActivity, aa.a aVar) {
        aa.a(aVar);
        Context context = unicomFlowActivity;
        a(context, unicomFlowActivity.getString(R.string.unicom_dialog_month_end_content, new Object[]{Integer.valueOf(e.i())}), (int) R.string.unicom_dialog_month_end_title, (int) R.string.unicom_dialog_month_end_button_open, new a<a>() {
            public void a(a aVar) {
                aa.b(aa.a.TRIAL_DETAIL);
                aVar.dismiss();
                unicomFlowActivity.startOpenActivity(com.sds.android.ttpod.framework.a.b.aa.b.POP_MONTH_DIALOG, false);
            }
        }, (int) R.string.unicom_dialog_month_end_button_alert, new a<a>() {
            public void a(a aVar) {
                aa.c(aa.a.TRIAL_DETAIL);
                com.sds.android.ttpod.framework.storage.a.a.a().a(true);
                aVar.dismiss();
                unicomFlowActivity.finish();
            }
        }, null);
    }

    public static void a(Activity activity, Class cls, Enum enumR, boolean z) {
        a(activity, new Intent(activity, cls), enumR, z);
    }

    public static void a(Activity activity, Intent intent, Enum enumR, boolean z) {
        if (enumR != null) {
            intent.putExtra("origin_type", enumR);
        }
        activity.startActivity(intent);
        if (z) {
            activity.finish();
        }
        c.add(activity);
    }

    public static void b() {
        for (Activity activity : c) {
            if (!(activity == null || activity.isFinishing() || (activity instanceof MainActivity))) {
                activity.finish();
            }
        }
        c.clear();
    }

    public static <T> T a(Activity activity, T t) {
        Bundle extras = activity.getIntent().getExtras();
        T t2 = null;
        if (extras != null) {
            t2 = extras.get("origin_type");
        }
        return t2 == null ? t : t2;
    }

    public static void a(Context context) {
        if (!c.a()) {
            return;
        }
        if (com.sds.android.sdk.lib.a.a.c()) {
            c.a(context, com.sds.android.ttpod.framework.modules.h.c.PROXY_HOST, com.sds.android.ttpod.framework.modules.h.c.HTTP_PROXY_PORT.intValue());
        } else {
            c.b(context);
        }
    }

    public static void d(Activity activity, com.sds.android.ttpod.framework.modules.h.b bVar) {
        if (com.sds.android.ttpod.framework.modules.h.b.DIALOG_TRIAL_TYPE == bVar) {
            a(activity);
        } else if (com.sds.android.ttpod.framework.modules.h.b.DIALOG_OPEN_TYPE == bVar) {
            a(activity, bVar);
        } else if (com.sds.android.ttpod.framework.modules.h.b.DIALOG_MONTH_TYPE == bVar) {
            b(activity, bVar);
        } else if (com.sds.android.ttpod.framework.modules.h.b.DIALOG_30M_TYPE == bVar) {
            c(activity, bVar);
        }
    }
}
