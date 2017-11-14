package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.a.b.aa.b;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.modules.h.e;
import com.sds.android.ttpod.framework.storage.a.a;

public class UnSubscribeGuideActivity extends SlidingClosableActivity {
    private b mOpenOrigin = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.layout_unsubscribe_guide);
        setTitle((int) R.string.unicom_unsubscribe_title);
        this.mOpenOrigin = (b) b.a((Activity) this, b.ORDER_DETAIL);
        bindViews();
        aa.h();
        setStatisticPage(s.PAGE_UNICOM_UNSUBSCRIBE);
        setTBSPage("tt_unicom_unsubscribe");
    }

    private void bindViews() {
        ((TextView) findViewById(R.id.text_number)).setText(e.l());
        ((TextView) findViewById(R.id.text_start_time)).setText(a.a().A());
    }

    public void unSubScribeOnClick(View view) {
        com.sds.android.ttpod.framework.a.b.b.a("unicom_unsubscribe");
        aa.e(this.mOpenOrigin);
        t.a(1109, s.PAGE_UNICOM_UNSUBSCRIBE_FEEDBACK);
        b.a((Activity) this, UnsubscribeActivity.class, null, false);
    }
}
