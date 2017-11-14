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
import com.sds.android.ttpod.framework.modules.h.e;
import com.sds.android.ttpod.framework.storage.a.a;
import com.sds.android.ttpod.framework.storage.environment.b;

public class TrialDetailActivity extends UnicomFlowActivity implements OnClickListener {
    private Button mButtonFlowOpen;
    private TextView mTextViewFaq;
    private TextView mTextViewFlowDescribe;
    private TextView mTextViewFlowPhone;
    private TextView mTextViewServerDetail;
    private c mTrialOrigin = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_unicom_flow_trial_detail);
        setTitle((CharSequence) "天天动听-包流量畅听");
        this.mButtonFlowOpen = (Button) findViewById(R.id.button_open);
        this.mTextViewFlowDescribe = (TextView) findViewById(R.id.textview_flow_describe);
        this.mTextViewFlowPhone = (TextView) findViewById(R.id.unicom_trial_detail_phone);
        this.mTextViewFaq = (TextView) findViewById(R.id.textview_flow_faq);
        this.mTextViewServerDetail = (TextView) findViewById(R.id.textview_server_detail);
        this.mButtonFlowOpen.setOnClickListener(this);
        this.mTextViewServerDetail.setOnClickListener(this);
        this.mTrialOrigin = (c) b.a((Activity) this, c.ORDER_DETAIL);
        aa.e(this.mTrialOrigin);
        bindView();
        setTBSPage("tt_unicom_trial");
        trackModule("unicom_flow");
    }

    private void bindView() {
        this.mTextViewFlowPhone.setText(getResources().getString(R.string.unicom_trial_detail_phone, new Object[]{e.l()}));
        this.mTextViewFaq.setText(a.a().g(getString(R.string.unicom_matters_attention)));
    }

    public void onClick(View view) {
        if (view == this.mButtonFlowOpen) {
            if (e.j() && !a.a().C() && b.bu()) {
                b.a((UnicomFlowActivity) this, aa.a.ORDER_DETAIL);
                b.ag(false);
                return;
            }
            aa.f(this.mTrialOrigin);
            startOpenActivity(aa.b.TRIAL_DETAIL, true);
        } else if (view == this.mTextViewServerDetail) {
            aa.g(this.mTrialOrigin);
            startActivity(new Intent(this, FaqActivity.class));
        }
    }
}
