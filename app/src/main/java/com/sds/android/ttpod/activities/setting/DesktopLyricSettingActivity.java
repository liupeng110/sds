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
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;

public class DesktopLyricSettingActivity extends SlidingClosableActivity {
    private static final int ID_DESKTOP_LYRIC = 0;
    private static final int ID_LYRIC_LOCKED = 1;
    private b mOnItemClickListener = new b(this) {
        final /* synthetic */ DesktopLyricSettingActivity a;

        {
            this.a = r1;
        }

        public void a(a aVar, int i) {
            boolean z = false;
            if (aVar instanceof Checkable) {
                z = ((Checkable) aVar).isChecked();
            }
            switch (aVar.g()) {
                case 0:
                    com.sds.android.ttpod.framework.storage.environment.b.h(z);
                    t.a(r.ACTION_SETTING_SHOW_DESTOP_LYRIC, z);
                    com.sds.android.ttpod.framework.a.b.b.a("show_desktop_lrc", z);
                    return;
                case 1:
                    com.sds.android.ttpod.framework.storage.environment.b.B(z);
                    t.a(r.ACTION_SETTING_DESTOP_LYRIC_LOCK_SELECT, z);
                    com.sds.android.ttpod.framework.a.b.b.a("block_desktop_lrc", z);
                    return;
                default:
                    return;
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_DESKTOP_LYRIC);
        setContentView((int) R.layout.activity_setting_desktop_lyric);
        d.a(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.setting_card_container);
        linearLayout.addView(buildDesktopLyricSettingCard().e());
        linearLayout.addView(buildDescriptionView(R.string.ask_why_no_desktop_lyric, R.string.answer_why_no_desktop_lyric));
        linearLayout.addView(buildDescriptionView(R.string.ask_what_is_lock_desktop_lyric, R.string.answer_what_is_lock_desktop_lyric));
    }

    private b buildDesktopLyricSettingCard() {
        c[] cVarArr = new c[2];
        cVarArr[0] = new a(0, 0, R.string.show_desktop_lyric, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.s());
        cVarArr[1] = new a(1, 0, R.string.minilyric_lock_desktop_lyric, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.ad());
        b bVar = new b(this, cVarArr, R.string.setting_desktop_lyric, this.mOnItemClickListener);
        bVar.a(false);
        return bVar;
    }

    private View buildDescriptionView(int i, int i2) {
        View inflate = getLayoutInflater().inflate(R.layout.setting_container_desc, null, false);
        setViewText(inflate, R.id.id_desc_title, i);
        setViewText(inflate, R.id.id_desc_sub_title, i2);
        return inflate;
    }

    private void setViewText(View view, int i, int i2) {
        TextView textView = (TextView) view.findViewById(i);
        textView.setText(i2);
        textView.setVisibility(0);
    }
}
