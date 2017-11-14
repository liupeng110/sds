package com.sds.android.ttpod.activities.setting;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.ViewGroup;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.b.d;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.tencent.open.SocialConstants;

public class NotificationSettingActivity extends SlidingClosableActivity {
    private static final int ID_HIDE_WHILE_PAUSING = 4;
    private static final int ID_PRIORITY = 1;
    private static final int ID_SHOW_CLOSE_BUTTON = 3;
    private static final int ID_SHOW_PREV_BUTTON = 2;
    private b mOnItemClickListener = new b(this) {
        static final /* synthetic */ boolean a = (!NotificationSettingActivity.class.desiredAssertionStatus());
        final /* synthetic */ NotificationSettingActivity b;

        {
            this.b = r1;
        }

        public void a(a aVar, int i) {
            boolean isChecked;
            boolean z = false;
            if (aVar instanceof a) {
                isChecked = ((a) aVar).isChecked();
            } else {
                isChecked = false;
            }
            switch (aVar.g()) {
                case 1:
                    this.b.showPrioritySetting(aVar, i);
                    t.a(r.ACTION_SETTING_NOTIFICATION_PRIORITY, s.PAGE_NONE);
                    return;
                case 2:
                    if (a || (aVar instanceof a)) {
                        com.sds.android.ttpod.framework.storage.environment.b.ab(isChecked);
                        t.a(r.ACTION_SETTING_SHOW_PREVIOUS_BUTTON, isChecked);
                        com.sds.android.ttpod.framework.a.b.b.a("show_pre_button", isChecked);
                        return;
                    }
                    throw new AssertionError();
                case 3:
                    if (a || (aVar instanceof a)) {
                        com.sds.android.ttpod.framework.storage.environment.b.ac(((a) aVar).isChecked());
                        t.a(r.ACTION_SETTING_SHOW_CLOSE_BUTTON, isChecked);
                        com.sds.android.ttpod.framework.a.b.b.a("show_shutup_button", isChecked);
                        return;
                    }
                    throw new AssertionError();
                case 4:
                    if (a || (aVar instanceof a)) {
                        if (!isChecked) {
                            z = true;
                        }
                        com.sds.android.ttpod.framework.storage.environment.b.ad(z);
                        t.a(r.ACTION_SETTING_PAUSE_AUTO_HIDE, isChecked);
                        com.sds.android.ttpod.framework.a.b.b.a("stop_auto_hide", isChecked);
                        return;
                    }
                    throw new AssertionError();
                default:
                    return;
            }
        }
    };
    private SparseIntArray mPriorityResArray = new SparseIntArray();
    private b mSettingCard;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_SETTING_NOTIFICATION);
        setContentView((int) R.layout.activity_setting_notification);
        d.a(this);
        this.mPriorityResArray.put(2, R.string.notification_priority_max);
        this.mPriorityResArray.put(1, R.string.notification_priority_high);
        this.mPriorityResArray.put(0, R.string.notification_priority_default);
        this.mPriorityResArray.put(-1, R.string.notification_priority_low);
        this.mPriorityResArray.put(-2, R.string.notification_priority_min);
        this.mSettingCard = new b(this, buildSettingItems(), R.string.setting_notification, this.mOnItemClickListener);
        ((ViewGroup) findViewById(R.id.setting_card_container_notification)).addView(this.mSettingCard.e());
    }

    private c[] buildSettingItems() {
        c cVar = new c(1, 0, (int) R.string.setting_notification_priority, this.mPriorityResArray.get(com.sds.android.ttpod.framework.storage.environment.b.ba()), 0, true);
        a aVar = new a(2, 0, R.string.setting_notification_prev, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.bb());
        a aVar2 = new a(3, 0, R.string.setting_notification_close, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.bc());
        a aVar3 = new a(4, 0, R.string.setting_notification_hide_while_pausing, 0, 0, !com.sds.android.ttpod.framework.storage.environment.b.bd());
        if (j.g()) {
            return new c[]{cVar, aVar, aVar2, aVar3};
        } else if (j.f()) {
            return new c[]{aVar, aVar2, aVar3};
        } else {
            return new c[]{aVar3};
        }
    }

    private void showPrioritySetting(final a aVar, final int i) {
        f.a((Context) this, getString(R.string.notification_priority_title), new d[]{new d(2, R.string.notification_priority_max), new d(1, R.string.notification_priority_high), new d(0, R.string.notification_priority_default), new d(-1, R.string.notification_priority_low), new d(-2, R.string.notification_priority_min)}, com.sds.android.ttpod.framework.storage.environment.b.ba(), new b(this) {
            final /* synthetic */ NotificationSettingActivity c;

            public void a(a aVar, int i) {
                aVar.a(aVar.d());
                this.c.mSettingCard.a((c) aVar, i);
                com.sds.android.ttpod.framework.storage.environment.b.r(aVar.g());
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_SETTING_NOTIFICATION_PRIORITY_SELECT.getValue(), 0, 0);
                sUserEvent.setPageParameter(true);
                sUserEvent.append(SocialConstants.PARAM_TYPE, Integer.valueOf(i));
                sUserEvent.post();
                com.sds.android.ttpod.framework.a.b.b.a("ad_priority", i);
            }
        }, null);
    }
}
