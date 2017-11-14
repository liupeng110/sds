package com.sds.android.ttpod.activities.setting;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.d;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.modules.version.VersionUpdateModule;
import java.lang.reflect.Method;
import java.util.Map;

public class AboutActivity extends SlidingClosableActivity {
    private static final int ID_CONTACT_US = 4;
    private static final int ID_FEEDBACK = 2;
    private static final int ID_RATE = 3;
    private static final int ID_UPGRADE = 1;
    private b mOnItemClickListener = new b(this) {
        final /* synthetic */ AboutActivity a;

        {
            this.a = r1;
        }

        public void a(a aVar, int i) {
            CharSequence d = aVar.d();
            switch (aVar.g()) {
                case 1:
                    if (EnvironmentUtils.a.a()) {
                        if (c.e()) {
                            f.a((int) R.string.version_upgrade_check_toast);
                            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.CHECK_UPGRADE, Boolean.valueOf(false)));
                        } else {
                            f.a((int) R.string.version_update_network_bad);
                        }
                    }
                    t.a(r.ACTION_SETTING_CHECK_UPDATE, s.PAGE_NONE);
                    return;
                case 2:
                    d.a(this.a, HelpFeedbackActivity.class, d);
                    l.s();
                    t.a(r.ACTION_SETTING_HELP_REPLAY, s.PAGE_SETTING_QUESTION);
                    return;
                case 3:
                    this.a.ratingTTPod();
                    t.a(r.ACTION_SETTING_SCORE, s.PAGE_NONE);
                    return;
                case 4:
                    d.a(this.a, ContactUsActivity.class, aVar.d());
                    t.a(r.ACTION_SETTING_CONTACT, s.PAGE_CONTACT_US);
                    return;
                default:
                    return;
            }
        }
    };
    private b mVersionCard;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_setting_about");
        setStatisticPage(s.PAGE_ABOUT_TTPOD);
        setContentView((int) R.layout.activity_setting_about);
        d.a(this);
        ((TextView) findViewById(R.id.id_about_version)).setText(EnvironmentUtils.a.g());
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.setting_card_container_about);
        this.mVersionCard = buildAboutCard();
        linearLayout.addView(this.mVersionCard.e());
    }

    protected void onResume() {
        super.onResume();
        d.c();
    }

    protected void onDestroy() {
        super.onDestroy();
        d.b();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_COMMON_UPGRADE_INFO, i.a(getClass(), "updateCommonUpgradeInfo", com.sds.android.ttpod.framework.base.d.class));
    }

    public void updateCommonUpgradeInfo(com.sds.android.ttpod.framework.base.d dVar) {
        Integer num = (Integer) dVar.c();
        if (num.intValue() == 6) {
            f.a((int) R.string.version_update_no_update);
        } else if (num.intValue() == 2) {
            f.a((int) R.string.version_update_network_bad);
        }
    }

    private b buildAboutCard() {
        int i = VersionUpdateModule.hasNewVersion() ? R.string.version_update_about_upgrade_enable : R.string.version_update_about_already_latest;
        b bVar = new b(this, new c[]{(c) new c(1, 0, (int) R.string.version_update_about_check, 0, 0, true).c(i), new c(2, 0, (int) R.string.setting_help_feedback, 0, (int) R.string.icon_arrow_right, true), new c(3, 0, (int) R.string.app_rate, 0, (int) R.string.icon_arrow_right, true), new c(4, 0, (int) R.string.contact_us, 0, (int) R.string.icon_arrow_right, true)}, R.string.setting_about, this.mOnItemClickListener);
        bVar.a(false);
        return bVar;
    }

    private void ratingTTPod() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=" + getPackageName()));
        try {
            startActivity(Intent.createChooser(intent, getString(R.string.market)));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
