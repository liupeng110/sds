package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Checkable;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;

public class BacklightSettingActivity extends SlidingClosableActivity {
    private static final int ID_LANDSCAPE = 4;
    private static final int ID_LIST = 1;
    private static final int ID_LOCK_SCREEN = 3;
    private static final int ID_PLAYER = 2;
    private b mOnItemClickListener = new b(this) {
        final /* synthetic */ BacklightSettingActivity a;

        {
            this.a = r1;
        }

        public void a(a aVar, int i) {
            if (aVar instanceof Checkable) {
                boolean isChecked = ((a) aVar).isChecked();
                switch (aVar.g()) {
                    case 1:
                        com.sds.android.ttpod.framework.storage.environment.b.p(isChecked);
                        t.a(r.ACTION_SETTING_LIST_LIGHT, isChecked);
                        com.sds.android.ttpod.framework.a.b.b.a("list_shine", isChecked);
                        return;
                    case 2:
                        com.sds.android.ttpod.framework.storage.environment.b.q(isChecked);
                        t.a(r.ACTION_SETTING_PLAY_LIGHT, isChecked);
                        com.sds.android.ttpod.framework.a.b.b.a("play_page_shine", isChecked);
                        return;
                    case 3:
                        com.sds.android.ttpod.framework.storage.environment.b.r(isChecked);
                        t.a(r.ACTION_SETTING_LOCK_LIGHT, isChecked);
                        com.sds.android.ttpod.framework.a.b.b.a("lock_screen_shine", isChecked);
                        return;
                    case 4:
                        com.sds.android.ttpod.framework.storage.environment.b.s(isChecked);
                        t.a(r.ACTION_SETTING_LANDSCAPE_LIGHT, isChecked);
                        com.sds.android.ttpod.framework.a.b.b.a("cross_screen_shine", isChecked);
                        return;
                    default:
                        throw new IllegalArgumentException("illegal ID");
                }
            }
            throw new IllegalArgumentException("must be Checkable!");
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_SETTING_BACKLIGHT);
        setContentView((int) R.layout.activity_setting_backlight);
        d.a(this);
        c[] cVarArr = new c[4];
        cVarArr[0] = new a(1, 0, R.string.backlight_list, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.z());
        cVarArr[1] = new a(2, 0, R.string.backlight_player, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.A());
        cVarArr[2] = new a(3, 0, R.string.backlight_lockscreen, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.B());
        cVarArr[3] = new a(4, 0, R.string.backlight_landscape, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.C());
        ((ViewGroup) findViewById(R.id.setting_card_container_backlight)).addView(new b(this, cVarArr, R.string.setting_backlight, this.mOnItemClickListener).e());
    }
}
