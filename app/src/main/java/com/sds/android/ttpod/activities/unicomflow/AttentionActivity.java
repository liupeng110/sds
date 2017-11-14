package com.sds.android.ttpod.activities.unicomflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;

public class AttentionActivity extends SlidingClosableActivity implements OnClickListener {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((CharSequence) "注意事项");
        setContentView((int) R.layout.activity_unicom_flow_attention_message);
        ((TextView) findViewById(R.id.textview_attention)).setOnClickListener(this);
        aa.L();
        setStatisticPage(s.PAGE_UNICOM_ATTENTION);
        setTBSPage("tt_unicom_attention");
    }

    public void onClick(View view) {
        b.a("unicom_faq");
        t.a(1111, s.PAGE_UNICOM_FAQ);
        startActivity(new Intent(this, FaqActivity.class));
    }
}
