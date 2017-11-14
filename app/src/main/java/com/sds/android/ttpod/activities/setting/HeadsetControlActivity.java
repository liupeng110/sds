package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.b.c;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;

public class HeadsetControlActivity extends SlidingClosableActivity {
    private static final int ID_EXCHANGE_DOUBLE_CLICK_LONG_CLICK = 4;
    private static final int ID_EXTRACT_TO_STOP = 1;
    private static final int ID_HEADSET_CONTROL_ENABLE = 3;
    private static final int ID_INSERT_TO_PLAY = 2;
    private b mOnItemClickListener = new b(this) {
        final /* synthetic */ HeadsetControlActivity a;

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
                    com.sds.android.ttpod.framework.storage.environment.b.k(z);
                    t.a(r.ACTION_SETTING_HEADSET_PAUSE, z);
                    com.sds.android.ttpod.framework.a.b.b.a("plug_out_stop", z);
                    return;
                case 2:
                    com.sds.android.ttpod.framework.storage.environment.b.l(z);
                    t.a(r.ACTION_SETTING_HEADSET_PALY, z);
                    com.sds.android.ttpod.framework.a.b.b.a("plug_in_play", z);
                    return;
                case 3:
                    com.sds.android.ttpod.framework.storage.environment.b.m(z);
                    t.a(r.ACTION_SETTING_ALLOW_LINE_CONTROL, z);
                    com.sds.android.ttpod.framework.a.b.b.a("earphone_control_change", z);
                    return;
                case 4:
                    com.sds.android.ttpod.framework.storage.environment.b.n(z);
                    t.a(r.ACTION_SETTING_ALLOW_UP_DOWN, z);
                    com.sds.android.ttpod.framework.a.b.b.a("change_song_mode", z);
                    return;
                default:
                    return;
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_HEADSET_CONTROL);
        setContentView((int) R.layout.activity_setting_main);
        d.a(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.setting_card_container);
        linearLayout.addView(buildPlugHeadsetCard().e());
        linearLayout.addView(buildHeadSetControlCard().e());
        linearLayout.addView(buildHeadSetControlDescription());
    }

    private c buildPlugHeadsetCard() {
        c[] cVarArr = new c[2];
        cVarArr[0] = new a(1, 0, R.string.headset_extract_to_stop, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.t());
        cVarArr[1] = new a(2, 0, R.string.headset_insert_to_play, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.u());
        return new b(this, cVarArr, R.string.headset_extract_insert, this.mOnItemClickListener);
    }

    private c buildHeadSetControlCard() {
        c[] cVarArr = new c[2];
        cVarArr[0] = new a(3, 0, R.string.headset_control_enable, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.v());
        cVarArr[1] = new a(4, 0, R.string.headset_exchange_longclick_doubleclick, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.w());
        return new b(this, cVarArr, R.string.headset_control, this.mOnItemClickListener);
    }

    private View buildHeadSetControlDescription() {
        View inflate = getLayoutInflater().inflate(R.layout.setting_container_desc, null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.id_desc_sub_title);
        textView.setText(R.string.headset_exchange_song_desc);
        textView.setVisibility(0);
        return inflate;
    }
}
