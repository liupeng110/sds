package com.sds.android.ttpod.activities.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Checkable;
import android.widget.LinearLayout;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.d.a.m;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.l;

public class DisplaySettingActivity extends SlidingClosableActivity {
    private static final int ID_AUTO_ORIENTATE = 4;
    private static final int ID_BACKLIGHT = 6;
    private static final int ID_DESKTOP_LYRIC = 0;
    private static final int ID_HIDE_STATUS_BAR = 3;
    private static final int ID_LOCK_SCREEN_ENABLE = 2;
    private static final int ID_LYRIC_LOCKED = 1;
    private static final int ID_NOTIFICATION = 5;
    private b mOnItemClickListener = new b(this) {
        final /* synthetic */ DisplaySettingActivity a;

        {
            this.a = r1;
        }

        public void a(a aVar, int i) {
            boolean z = false;
            if (aVar instanceof Checkable) {
                z = ((Checkable) aVar).isChecked();
            }
            Activity activity = this.a;
            switch (aVar.g()) {
                case 0:
                    this.a.setDesktopLyric(aVar);
                    return;
                case 1:
                    com.sds.android.ttpod.framework.storage.environment.b.B(((Checkable) aVar).isChecked());
                    return;
                case 2:
                    com.sds.android.ttpod.framework.storage.environment.b.i(z);
                    return;
                case 3:
                    com.sds.android.ttpod.framework.storage.environment.b.w(z);
                    return;
                case 4:
                    com.sds.android.ttpod.framework.storage.environment.b.g(((Checkable) aVar).isChecked());
                    l.b(((Checkable) aVar).isChecked());
                    return;
                case 5:
                    d.a(activity, NotificationSettingActivity.class, aVar.d());
                    l.n();
                    return;
                case 6:
                    d.a(activity, BacklightSettingActivity.class, aVar.d());
                    l.o();
                    return;
                default:
                    return;
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_setting_main);
        d.a(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.setting_card_container);
        linearLayout.addView(buildDesktopLyricSettingCard().e());
        linearLayout.addView(buildLockscreenCard().e());
        linearLayout.addView(buildLandscapeCard().e());
        linearLayout.addView(buildNotificationCard().e());
        linearLayout.addView(buildBacklightCard().e());
    }

    private b buildLockscreenCard() {
        r7 = new c[2];
        r7[0] = new a(2, 0, R.string.setting_lockscreen_enable, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.j(com.sds.android.ttpod.framework.modules.e.a.isAllowDefaultLockScreen()));
        r7[1] = new a(3, 0, R.string.setting_hide_status_bar, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.G());
        return new b(this, r7, R.string.setting_lockscreen, this.mOnItemClickListener);
    }

    private b buildLandscapeCard() {
        c[] cVarArr = new c[1];
        cVarArr[0] = new a(4, 0, R.string.setting_auto_orientate, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.r());
        return new b(this, cVarArr, R.string.setting_lockscreen, this.mOnItemClickListener);
    }

    private b buildNotificationCard() {
        return new b(this, new c[]{new c(5, 0, (int) R.string.setting_notification, 0, (int) R.string.icon_arrow_right, true)}, R.string.setting_lockscreen, this.mOnItemClickListener);
    }

    private b buildBacklightCard() {
        return new b(this, new c[]{new c(6, 0, (int) R.string.setting_backlight, 0, (int) R.string.icon_arrow_right, true)}, R.string.setting_lockscreen, this.mOnItemClickListener);
    }

    private b buildDesktopLyricSettingCard() {
        c[] cVarArr = new c[2];
        cVarArr[0] = new a(0, 0, R.string.show_desktop_lyric, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.s());
        cVarArr[1] = new a(1, 0, R.string.minilyric_button_lock, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.ad());
        return new b(this, cVarArr, R.string.setting_desktop_lyric, this.mOnItemClickListener);
    }

    private void setDesktopLyric(a aVar) {
        boolean isChecked = ((Checkable) aVar).isChecked();
        com.sds.android.ttpod.framework.storage.environment.b.h(isChecked);
        if (isChecked && com.sds.android.ttpod.framework.storage.environment.b.aH()) {
            m a = f.a((Context) this, (int) R.string.never_show_again, (CharSequence) "提示", (CharSequence) "如果遇到MIUI V5系统无法显示桌面歌词的情况，请找到设置->应用->天天动听->打开悬浮窗即可", null);
            if (a != null) {
                a.a((int) R.string.set_at_once, new com.sds.android.ttpod.common.a.a.a<m>(this) {
                    final /* synthetic */ DisplaySettingActivity a;

                    {
                        this.a = r1;
                    }

                    private Intent a() {
                        if (j.b()) {
                            return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + EnvironmentUtils.a()));
                        } else if (j.a()) {
                            r0 = new Intent("android.intent.action.VIEW");
                            r0.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                            r0.putExtra("pkg", EnvironmentUtils.a());
                            return r0;
                        } else {
                            r0 = new Intent("android.intent.action.VIEW");
                            r0.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                            r0.putExtra("com.android.settings.ApplicationPkgName", EnvironmentUtils.a());
                            return r0;
                        }
                    }

                    public void a(m mVar) {
                        this.a.startActivity(a());
                        com.sds.android.ttpod.framework.storage.environment.b.L(!mVar.b());
                    }
                }, (int) R.string.i_known, new com.sds.android.ttpod.common.a.a.a<m>(this) {
                    final /* synthetic */ DisplaySettingActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(m mVar) {
                        com.sds.android.ttpod.framework.storage.environment.b.L(!mVar.b());
                    }
                });
                a.show();
            }
        }
    }
}
