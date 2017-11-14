package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.a.b.aa.c;
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.storage.a.a;

public class OrderFlowDetailActivity extends UnicomFlowActivity implements OnClickListener {
    private Button mButtonOpen;
    private Button mButtonTrial;
    private TextView mTextViewFaq;
    private TextView mTextViewServerDetail;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_unicom_flow_order_detail);
        setTitle((CharSequence) "天天动听-包流量畅听");
        this.mTextViewFaq = (TextView) findViewById(R.id.textview_flow_faq);
        this.mTextViewServerDetail = (TextView) findViewById(R.id.textview_server_detail);
        this.mButtonTrial = (Button) findViewById(R.id.button_trial);
        this.mButtonOpen = (Button) findViewById(R.id.button_open);
        bindView();
        aa.q();
        setStatisticPage(s.PAGE_UNICOM_ORDER);
        setTBSPage("tt_unicom_order");
        trackModule("unicom_flow");
    }

    private void bindView() {
        this.mTextViewFaq.setText(a.a().g(getString(R.string.unicom_matters_attention)));
        this.mButtonOpen.setOnClickListener(this);
        this.mButtonTrial.setOnClickListener(this);
        this.mTextViewServerDetail.setOnClickListener(this);
        Button button = this.mButtonTrial;
        int i = (a.a().I() && a.a().x() == com.sds.android.ttpod.framework.modules.h.a.UN_TRIAL.ordinal()) ? 0 : 8;
        button.setVisibility(i);
    }

    public void onClick(View view) {
        if (view == this.mButtonOpen) {
            b.a("unicom_open");
            startOpenActivity(aa.b.ORDER_DETAIL, false);
        } else if (view == this.mButtonTrial) {
            aa.n();
            b.a((Activity) this, TrialActivity.class, c.ORDER_DETAIL, false);
        } else if (view == this.mTextViewServerDetail) {
            b.a("unicom_faq");
            aa.p();
            t.a(1102, s.PAGE_UNICOM_FAQ);
            startActivity(new Intent(this, FaqActivity.class));
        }
    }
}
