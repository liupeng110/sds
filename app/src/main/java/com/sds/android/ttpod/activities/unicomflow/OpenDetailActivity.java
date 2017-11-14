package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.a.b.aa.b;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.modules.h.e;
import com.sds.android.ttpod.framework.storage.a.a;
import java.lang.reflect.Method;
import java.util.Map;

public class OpenDetailActivity extends UnicomFlowActivity implements OnClickListener {
    private static final double FLOW_PRICE = 0.3d;
    private static final String TAG = OpenDetailActivity.class.getSimpleName();
    private Button mButtonShare;
    private b mOpenOrigin = null;
    private TextView mTextUnSubscribe;
    private TextView mTextViewAttention;
    private TextView mTextViewBeginTime;
    private TextView mTextViewFlow;
    private TextView mTextViewPrice;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_unicom_flow_open_detail);
        setTitle((CharSequence) "天天动听-包流量畅听");
        this.mButtonShare = (Button) findViewById(R.id.button_share);
        this.mTextViewFlow = (TextView) findViewById(R.id.textview_flow);
        this.mTextViewPrice = (TextView) findViewById(R.id.textview_price);
        this.mTextViewAttention = (TextView) findViewById(R.id.textview_attention);
        this.mTextViewBeginTime = (TextView) findViewById(R.id.textview_begin_time);
        this.mTextUnSubscribe = (TextView) findViewById(R.id.textview_unsubscribe);
        this.mTextViewBeginTime.setText("开启时间:" + a.a().A());
        this.mButtonShare.setOnClickListener(this);
        this.mTextViewAttention.setOnClickListener(this);
        this.mTextUnSubscribe.setOnClickListener(this);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_UNICOM_PROXY_TOTAL_FLOW, new Object[0]));
        this.mOpenOrigin = (b) b.a((Activity) this, b.ORDER_DETAIL);
        g.a(TAG, "unicom flow open detail isUse proxy:" + com.sds.android.sdk.lib.a.a.c());
        aa.f(this.mOpenOrigin);
        setStatisticPage(s.PAGE_UNICOM_OPEN_DETAIL);
        setTBSPage("tt_unicom_open_detail");
        trackModule("unicom_flow");
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.GET_UNICOM_TOTAL_FLOW_RESULT, i.a(cls, "unicomTotalFlowResult", Long.class));
    }

    public void unicomTotalFlowResult(Long l) {
        double a = e.a(FLOW_PRICE * e.a(l.longValue()));
        this.mTextViewFlow.setText(getString(R.string.unicom_open_flow_msg, new Object[]{String.valueOf(r0)}));
        this.mTextViewPrice.setText(getString(R.string.unicom_open_flow_price, new Object[]{String.valueOf(a)}));
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SAVE_UNICOM_TOTAL_FLOW, new Object[0]));
    }

    public void onClick(View view) {
        if (view == this.mTextViewAttention) {
            com.sds.android.ttpod.framework.a.b.b.a("unicom_attention");
            aa.g(this.mOpenOrigin);
            t.a(1110, s.PAGE_UNICOM_ATTENTION);
            startActivity(new Intent(this, AttentionActivity.class));
        } else if (view == this.mTextUnSubscribe) {
            com.sds.android.ttpod.framework.a.b.b.a("unicom_unsubscribe");
            aa.i();
            t.a(1108, s.PAGE_UNICOM_UNSUBSCRIBE);
            startActivity(new Intent(this, UnSubscribeGuideActivity.class));
        }
    }
}
