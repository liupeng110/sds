package com.sds.android.ttpod.activities.unicomflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.storage.a.a;

public class UnsubscribeDetailActivity extends UnicomFlowActivity implements OnClickListener {
    private Button mButtonOpen;
    private TextView mTextViewAttention;
    private TextView mTextViewBeginTime;
    private TextView mTextViewEndTime;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_unicom_flow_unsubscribe_detail);
        setTitle((CharSequence) "天天动听-包流量畅听");
        this.mTextViewEndTime = (TextView) findViewById(R.id.text_end_time);
        this.mTextViewBeginTime = (TextView) findViewById(R.id.text_begin_time);
        this.mTextViewAttention = (TextView) findViewById(R.id.textview_attention);
        this.mButtonOpen = (Button) findViewById(R.id.button_open);
        this.mButtonOpen.setOnClickListener(this);
        this.mTextViewBeginTime.setText(a.a().A());
        this.mTextViewEndTime.setText(a.a().B());
        this.mTextViewAttention.setOnClickListener(this);
        setStatisticPage(s.PAGE_UNICOM_RESET_OPEN);
        setTBSPage("tt_unicom_reset_open");
        trackModule("unicom_flow");
    }

    public void onClick(View view) {
        if (view == this.mButtonOpen) {
            b.a("unicom_open");
            aa.w();
            t.a(1115, s.PAGE_UNICOM_OPEN_VERIFY);
            startOpenActivity(aa.b.UNSUBSCRIBE_DETAIL, false);
        } else if (view == this.mTextViewAttention) {
            b.a("unicom_attention");
            aa.x();
            startActivity(new Intent(this, AttentionActivity.class));
        }
    }
}
