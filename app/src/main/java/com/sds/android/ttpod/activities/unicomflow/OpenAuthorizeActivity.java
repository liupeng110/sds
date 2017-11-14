package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.h.e;
import java.lang.reflect.Method;
import java.util.Map;

public class OpenAuthorizeActivity extends UnicomFlowActivity implements OnClickListener {
    private static final float FONT_SCALE = 1.5f;
    public static final String KEY_PHONE = "key_phone";
    public static final String KEY_TOKEN = "key_token";
    private static final float MONTH_END_FONT_SCALE = 1.2f;
    private static final int MONTH_END_START_INDEX = 13;
    private Button mButtonOpen;
    private String mPhone;
    private TextView mTextModifyPhone;
    private TextView mTextViewPhoneMessage;
    private String mToken;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_unicom_flow_authorize_open);
        setTitle((CharSequence) "天天动听-包流量畅听");
        this.mTextViewPhoneMessage = (TextView) findViewById(R.id.textview_phone);
        this.mButtonOpen = (Button) findViewById(R.id.button_open);
        this.mTextModifyPhone = (TextView) findViewById(R.id.textview_modify_phone);
        this.mTextModifyPhone.setOnClickListener(this);
        this.mButtonOpen.setOnClickListener(this);
        checkShowPhoneNumber();
        aa.C();
        e.p();
        if (e.j()) {
            checkShowMonthEndInformation();
        }
        setStatisticPage(s.PAGE_UNICOM_NET_GET_PHONE);
        setTBSPage("tt_unicom_net_get_phone");
    }

    private void checkShowPhoneNumber() {
        this.mPhone = getIntentExtraValue(KEY_PHONE);
        this.mToken = getIntentExtraValue(KEY_TOKEN);
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        StyleSpan styleSpan = new StyleSpan(1);
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(FONT_SCALE);
        spannableStringBuilder.append(getString(R.string.unicom_phone_message));
        int length = spannableStringBuilder.length();
        if (!m.a(this.mPhone)) {
            spannableStringBuilder.append(this.mPhone + " ");
            spannableStringBuilder.setSpan(styleSpan, length, this.mPhone.length() + length, 18);
            spannableStringBuilder.setSpan(relativeSizeSpan, length, this.mPhone.length() + length, 33);
        }
        this.mTextViewPhoneMessage.setText(spannableStringBuilder);
    }

    private void checkShowMonthEndInformation() {
        findViewById(R.id.layout_month_end_hint_message).setVisibility(0);
        TextView textView = (TextView) findViewById(R.id.textview_month_end_message);
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        CharSequence string = getString(R.string.unicom_dialog_month_end_content, new Object[]{Integer.valueOf(e.i())});
        int length = String.valueOf(e.i()).length() + 13;
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#f1605e"));
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(MONTH_END_FONT_SCALE);
        spannableStringBuilder.append(string);
        spannableStringBuilder.setSpan(foregroundColorSpan, 13, length, 33);
        spannableStringBuilder.setSpan(relativeSizeSpan, 13, length, 33);
        textView.setText(spannableStringBuilder);
    }

    private String getIntentExtraValue(String str) {
        String str2 = "";
        if (getIntent() == null) {
            return str2;
        }
        str2 = getIntent().getStringExtra(str);
        if (m.a(str2)) {
            return "";
        }
        return str2;
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.OPEN_UNICOM_FLOW_RESULT, i.a(cls, "openUnicomFlowResult", d.class));
    }

    public void openUnicomFlowResult(d dVar) {
        b.a();
        if (com.sds.android.ttpod.framework.base.e.ErrNone == dVar.a() || com.sds.android.ttpod.framework.base.e.ErrAlreadyExists == dVar.a()) {
            b bVar = new b();
            bVar.b("unicom_open");
            bVar.a(Downloads.COLUMN_STATUS, "1").a();
            b.b();
            aa.c(aa.b.OPEN_AUTHORIZE);
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", 1104, 0, s.PAGE_UNICOM_OPEN_DETAIL.getValue());
            sUserEvent.setPageParameter(true);
            sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(1)).post();
            b.a((Activity) this, OpenDetailActivity.class, aa.b.OPEN_AUTHORIZE, true);
            return;
        }
        new b().b("unicom_open").a(Downloads.COLUMN_STATUS, FeedbackItem.STATUS_WAITING).a();
        sUserEvent = new SUserEvent("PAGE_CLICK", 1104, 0, s.PAGE_UNICOM_OPEN_DETAIL.getValue());
        sUserEvent.setPageParameter(true);
        sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(0)).post();
        aa.d(aa.b.OPEN_AUTHORIZE);
        f.a(dVar.b());
    }

    public void onClick(View view) {
        if (view == this.mTextModifyPhone) {
            b.a("unicom_modify");
            aa.D();
            t.a(1103, s.PAGE_UNICOM_OPEN_VERIFY);
            b.a((Activity) this, OpenActivity.class, aa.b.OPEN_AUTHORIZE, true);
        } else if (view == this.mButtonOpen) {
            aa.E();
            b.a((Context) this, "正在开通,请等待...");
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.OPEN_UNICOM_FLOW, this.mPhone, "", this.mToken));
        }
    }
}
