package com.sds.android.ttpod.activities.setting;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Checkable;
import com.igexin.sdk.PushManager;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.EntryActivity;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.SoundSearchActivity;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.b.n;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.b.c;
import com.sds.android.ttpod.component.b.d;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;

public class MoreSettingActivity extends SlidingClosableActivity {
    private static final int CREATE_RECOGNIZER_ICON = 2;
    private static final int CREATE_TTPOD_ICON = 1;
    private static final int ID_AIRPLANE_MODE_WHILE_SLEEPING = 5;
    private static final int ID_AUTO_ORIENTATE = 1;
    private static final int ID_BACKLIGHT = 3;
    private static final int ID_CREATE_SHORTCUT = 7;
    private static final int ID_DELETE_LOGCAT = 9;
    private static final int ID_LOGCAT = 8;
    private static final int ID_NOTIFICATION = 2;
    private static final int ID_PLAY_WHILE_STARTING = 4;
    private static final int ID_RECEIVE_PUSH_MESSAGE = 6;
    private b mOnItemClickListener = new b(this) {
        final /* synthetic */ MoreSettingActivity a;

        {
            this.a = r1;
        }

        public void a(a aVar, int i) {
            boolean z = false;
            if (aVar instanceof Checkable) {
                z = ((Checkable) aVar).isChecked();
            }
            switch (aVar.g()) {
                case 1:
                    com.sds.android.ttpod.framework.storage.environment.b.g(z);
                    l.b(z);
                    t.a(r.ACTION_SETTING_LANDSCAPE, z);
                    com.sds.android.ttpod.framework.a.b.b.a("cross_screen", z);
                    return;
                case 2:
                    d.a(this.a, NotificationSettingActivity.class, aVar.d());
                    l.n();
                    t.a(r.ACTION_SETTING_NOTIFICATION, s.PAGE_SETTING_NOTIFICATION);
                    return;
                case 3:
                    d.a(this.a, BacklightSettingActivity.class, aVar.d());
                    l.o();
                    t.a(r.ACTION_SETTING_BACKLIGHT, s.PAGE_SETTING_BACKLIGHT);
                    return;
                case 4:
                    com.sds.android.ttpod.framework.storage.environment.b.t(z);
                    t.a(r.ACTION_SETTING_APPLICATION_AUTO_PLAY, z);
                    com.sds.android.ttpod.framework.a.b.b.a("start_auto_play", z);
                    return;
                case 5:
                    com.sds.android.ttpod.framework.storage.environment.b.u(z);
                    return;
                case 6:
                    com.sds.android.ttpod.framework.storage.environment.b.x(z);
                    t.a(r.ACTION_SETTING_RECEIVE_PUSH_MESSAGE, z);
                    com.sds.android.ttpod.framework.a.b.b.a("push", z);
                    if (z) {
                        try {
                            PushManager.getInstance().initialize(this.a);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    PushManager.getInstance().stopService(this.a);
                    l.c(z);
                    return;
                case 7:
                    this.a.showCreateDesktopShortcutDialog();
                    t.a(r.ACTION_SETTING_CREATE_DESKTOP_ICON, s.PAGE_NONE);
                    return;
                case 8:
                    n.a();
                    f.a(this.a.getResources().getString(R.string.setting_logcat_msg) + n.b());
                    t.a(r.ACTION_SETTING_SAVE_LOG, s.PAGE_NONE);
                    return;
                case 9:
                    n.c();
                    f.a(this.a.getResources().getString(R.string.setting_log_clear));
                    t.a(r.ACTION_SETTING_DELETE_LOG, s.PAGE_NONE);
                    return;
                default:
                    return;
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_SETTING_MORE);
        setContentView((int) R.layout.activity_setting_other);
        d.a(this);
        ((ViewGroup) findViewById(R.id.setting_card_container_other)).addView(buildMoreCard().e());
        ((ViewGroup) findViewById(R.id.setting_card_container_other)).addView(buildSaveLogCard().e());
    }

    private b buildMoreCard() {
        a aVar = new a(1, 0, R.string.setting_auto_orientate, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.r());
        c cVar = new c(2, 0, (int) R.string.setting_notification, 0, (int) R.string.icon_arrow_right, true);
        c cVar2 = new c(3, 0, (int) R.string.setting_backlight, 0, (int) R.string.icon_arrow_right, true);
        a aVar2 = new a(4, 0, R.string.play_while_starting, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.D());
        a aVar3 = new a(5, 0, R.string.airplane_while_sleeping, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.E());
        a aVar4 = new a(6, 0, R.string.recevie_push_message, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.H());
        c cVar3 = new c(7, 0, (int) R.string.create_desktop_icon, 0, (int) R.string.icon_arrow_right, true);
        List arrayList = new ArrayList();
        arrayList.add(aVar);
        arrayList.add(cVar);
        arrayList.add(cVar2);
        arrayList.add(aVar2);
        if (!j.h()) {
            arrayList.add(aVar3);
        }
        arrayList.add(aVar4);
        arrayList.add(cVar3);
        return new b(this, (c[]) arrayList.toArray(new c[arrayList.size()]), R.string.more, this.mOnItemClickListener);
    }

    private c buildSaveLogCard() {
        return new b(this, new c[]{new c(8, 0, (int) R.string.setting_logcat, 0, (int) R.string.icon_arrow_right, true), new c(9, 0, (int) R.string.setting_delete_log, 0, R.string.icon_arrow_right, true)}, R.string.setting_logcat, this.mOnItemClickListener);
    }

    private void showCreateDesktopShortcutDialog() {
        com.sds.android.ttpod.component.d.a.j jVar = new com.sds.android.ttpod.component.d.a.j((Context) this, new d[]{new d(1, R.string.ttpod, true), new d(2, R.string.search_sound_search, true)}, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.j>(this) {
            final /* synthetic */ MoreSettingActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.d.a.j jVar) {
                List<d> d = jVar.d();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("[");
                int i = 0;
                for (d g : d) {
                    int i2;
                    int g2 = g.g();
                    if (1 == g2) {
                        com.sds.android.ttpod.b.t.a(this.a, EntryActivity.class, R.drawable.img_ttpod, R.string.ttpod);
                        i2 = i + 1;
                    } else if (2 == g2) {
                        com.sds.android.ttpod.b.t.a(this.a, SoundSearchActivity.class, R.drawable.img_recognizer_song, R.string.search_sound_search);
                        i2 = i + 2;
                    } else {
                        i2 = i;
                    }
                    stringBuilder.append(g2);
                    stringBuilder.append(',');
                    i = i2;
                }
                stringBuilder.append("]");
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_SETTING_CREATE_DESKTOP_ICON_OK.getValue(), 0, 0);
                sUserEvent.setPageParameter(true);
                sUserEvent.append(SocialConstants.PARAM_TYPE, stringBuilder.toString());
                sUserEvent.post();
                com.sds.android.ttpod.framework.a.b.b.a("create_desktop_coin", i);
            }
        }, null);
        jVar.setTitle(getString(R.string.select_create_icon));
        jVar.show();
    }
}
