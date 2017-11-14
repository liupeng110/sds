package com.sds.android.ttpod.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.MvListItem;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.AudioEffectFragmentActivity;
import com.sds.android.ttpod.activities.BackgroundManagementActivity;
import com.sds.android.ttpod.activities.SoundSearchActivity;
import com.sds.android.ttpod.activities.ThemeManagementActivity;
import com.sds.android.ttpod.activities.apshare.ApShareEntryActivity;
import com.sds.android.ttpod.activities.ktv.KtvActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanWifiActivity;
import com.sds.android.ttpod.activities.mv.MvActivity;
import com.sds.android.ttpod.activities.mv.c;
import com.sds.android.ttpod.activities.setting.SettingEntryActivity;
import com.sds.android.ttpod.activities.unicomflow.OpenDetailActivity;
import com.sds.android.ttpod.activities.unicomflow.OrderFlowDetailActivity;
import com.sds.android.ttpod.activities.unicomflow.TrialDetailActivity;
import com.sds.android.ttpod.activities.unicomflow.UnsubscribeDetailActivity;
import com.sds.android.ttpod.activities.user.UserInfoActivity;
import com.sds.android.ttpod.activities.user.login.LoginActivity;
import com.sds.android.ttpod.activities.user.login.PhoneOrMailLoginActivity;
import com.sds.android.ttpod.activities.web.WebActivity;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.b.u;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.modules.h.d;
import com.sds.android.ttpod.framework.modules.h.e;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.audiofx.EffectDetect;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import java.util.ArrayList;
import java.util.List;

/* EntryUtils */
public final class f {
    static final /* synthetic */ boolean a = (!f.class.desiredAssertionStatus());

    public static void a(Context context) {
        new SUserEvent("PAGE_CLICK", r.ACTION_GLOBAL_MENU_FM.getValue(), s.PAGE_GLOBAL_MENU.getValue(), s.PAGE_TTPOD_FM.getValue()).append("url", "http://fm.ttpod.com/mindex.html").post();
        Intent intent = new Intent(context, WebActivity.class);
        intent.setData(Uri.parse("http://fm.ttpod.com/mindex.html"));
        intent.putExtra(WebFragment.EXTRA_TITLE, context.getString(R.string.ttpod_fm));
        intent.putExtra(WebFragment.EXTRA_PAGE, "tt_FM");
        intent.addFlags(268435456);
        context.startActivity(intent);
        l.O();
    }

    public static void b(Context context) {
        context.startActivity(new Intent(context, KtvActivity.class));
    }

    public static void c(Context context) {
        context.startActivity(new Intent(context, SettingEntryActivity.class));
        l.N();
    }

    public static void d(Context context) {
        u.f();
        context.startActivity(new Intent(context, SoundSearchActivity.class));
    }

    public static void e(Context context) {
        context.startActivity(new Intent(context, ThemeManagementActivity.class));
        x.g("setting");
        l.K();
        x.t();
    }

    public static void a(Activity activity) {
        b.Z(false);
        if (EffectDetect.usingAudioPlus()) {
            try {
                activity.startActivityForResult(new Intent("android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL").putExtra("android.media.extra.AUDIO_SESSION", b.bi()).addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START), 1);
            } catch (Exception e) {
                activity.startActivity(new Intent(activity, AudioEffectFragmentActivity.class));
                e.printStackTrace();
            }
        } else {
            activity.startActivity(new Intent(activity, AudioEffectFragmentActivity.class));
        }
        l.J();
    }

    public static void a() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SWITCH_PLAY_MODE, new Object[0]));
        l.I();
    }

    public static void a(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, MediaScanWifiActivity.class), i);
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent(context, ApShareEntryActivity.class);
        if (!m.a(str)) {
            intent.putExtra("key_media_id", str);
        }
        context.startActivity(intent);
        l.c();
    }

    public static void f(Context context) {
        context.startActivity(new Intent(context, MediaScanActivity.class));
    }

    public static void g(Context context) {
        context.startActivity(new Intent(context, UserInfoActivity.class));
    }

    public static void b(Activity activity) {
        Class cls;
        b.ae(false);
        int y = com.sds.android.ttpod.framework.storage.a.a.a().y();
        int x = com.sds.android.ttpod.framework.storage.a.a.a().x();
        if (d.UNSUBSCRIBE.ordinal() == y) {
            cls = UnsubscribeDetailActivity.class;
        } else if (d.OPEN.ordinal() == y) {
            cls = OpenDetailActivity.class;
        } else if (com.sds.android.ttpod.framework.modules.h.a.TRIAL.ordinal() == x) {
            cls = TrialDetailActivity.class;
        } else {
            cls = OrderFlowDetailActivity.class;
        }
        activity.startActivity(new Intent(activity, cls));
    }

    public static void c(Activity activity) {
        if (e.f()) {
            b(activity);
            t.a(1137, s.PAGE_UNICOM_ORDER);
            return;
        }
        new SUserEvent("PAGE_CLICK", 1136, 0).post();
        com.sds.android.ttpod.component.d.f.a((int) R.string.not_unicom_user_message);
    }

    public static void h(Context context) {
        if (((Boolean) com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.IS_SLEEP_MODE_ENABLED, new Object[0]), Boolean.class)).booleanValue()) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.STOP_SLEEP_MODE, new Object[0]));
            t.a("PAGE_CLICK", r.ACTION_GLOBAL_MENU_CANCEL_SLEEP, s.PAGE_GLOBAL_MENU, s.PAGE_NONE);
            com.sds.android.ttpod.component.d.f.a(context.getString(R.string.cancel_sleep_mode));
            return;
        }
        com.sds.android.ttpod.component.d.f.b(context);
    }

    public static void b() {
        l.P();
        com.sds.android.ttpod.framework.base.a.a().b();
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.EXIT, new Object[0]));
    }

    public static void i(Context context) {
        context.startActivity(new Intent(context, BackgroundManagementActivity.class));
        x.h();
    }

    public static void j(Context context) {
        context.startActivity(new Intent(context, BackgroundManagementActivity.class));
        x.h();
    }

    public static void a(boolean z) {
        if (z) {
            com.sds.android.ttpod.component.d.f.a((int) R.string.retry_after_login_tip);
        }
        Context e = BaseApplication.e();
        e.startActivity(new Intent(e, LoginActivity.class).addFlags(268435456));
    }

    public static void a(Context context, MvData mvData, MvListItem mvListItem) {
        a(context, mvData, mvListItem, false);
    }

    private static void a(Context context, MvData mvData, MvListItem mvListItem, boolean z) {
        List mvList = mvData.getMvList();
        if (!a && !mvList.contains(mvListItem)) {
            throw new AssertionError();
        } else if (mvListItem != null) {
            a(context, new c(mvData, mvListItem), z);
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        MvData mvData = new MvData();
        mvData.setId(0);
        mvData.setName(str);
        mvData.setSingerName(str2);
        MvListItem mvListItem = new MvListItem(0, "", 0, 0, 0, "", 0, 0, str3, 0, "");
        List arrayList = new ArrayList();
        arrayList.add(mvListItem);
        mvData.setMvList(arrayList);
        a(context, mvData, mvListItem, true);
    }

    private static void a(Context context, c cVar, boolean z) {
        Intent intent = new Intent(context, MvActivity.class);
        intent.putExtra(MvActivity.MV_ITEM_FOR_PLAY, cVar);
        intent.putExtra(MvActivity.MV_PLAY_LANDSCAPE, z);
        context.startActivity(intent);
    }

    public static void a(String str, boolean z) {
        Context e = BaseApplication.e();
        Intent intent = new Intent(e, PhoneOrMailLoginActivity.class);
        intent.addFlags(268435456);
        if (z) {
            intent.addFlags(67108864);
        }
        if (!m.a(str)) {
            intent.putExtra(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME, str);
        }
        e.startActivity(intent);
    }
}
