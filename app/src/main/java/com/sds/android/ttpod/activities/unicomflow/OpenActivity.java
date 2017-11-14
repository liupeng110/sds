package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.a.b.aa.b;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.h.e;
import java.lang.reflect.Method;
import java.util.Map;

public class OpenActivity extends SlidingClosableActivity implements OnClickListener {
    private static final int DELAY_TIME = 1000;
    private static final float MONTH_END_FONT_SCALE = 1.2f;
    private static final int MONTH_END_START_INDEX = 13;
    private static final int PHONE_NUMBER_LENGTH = 11;
    public static final int SEND_VERIFY_CODE_VALID_TIME = 60;
    private static final String TAG = OpenActivity.class.getName();
    private Button mButtonOpen;
    private Button mButtonVerifyCode;
    private Runnable mCountRunnable = new Runnable(this) {
        final /* synthetic */ OpenActivity a;

        {
            this.a = r1;
        }

        public void run() {
            OpenActivity.access$012(this.a, 1);
            this.a.mButtonVerifyCode.setText("" + (60 - this.a.mWaitTime) + "s后可再次获取");
            if (this.a.mWaitTime < 60) {
                this.a.mTimeHandler.postDelayed(this, 1000);
                return;
            }
            this.a.mButtonVerifyCode.setText("获取验证码");
            this.a.mButtonVerifyCode.setEnabled(true);
        }
    };
    private EditText mEditViewPhone;
    private EditText mEditViewVerfidyCode;
    private b mOpenOrigin = null;
    private TextView mTextViewFlowFaq;
    private Handler mTimeHandler = new Handler();
    private int mWaitTime = 0;

    static /* synthetic */ int access$012(OpenActivity openActivity, int i) {
        int i2 = openActivity.mWaitTime + i;
        openActivity.mWaitTime = i2;
        return i2;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_unicom_flow_open);
        setTitle((CharSequence) "验证手机号");
        this.mEditViewPhone = (EditText) findViewById(R.id.edit_phone);
        this.mButtonVerifyCode = (Button) findViewById(R.id.button_verify_code);
        this.mEditViewVerfidyCode = (EditText) findViewById(R.id.edit_verify_code);
        this.mButtonOpen = (Button) findViewById(R.id.button_open);
        this.mTextViewFlowFaq = (TextView) findViewById(R.id.text_flow_faq);
        this.mButtonOpen.setBackgroundResource(getOpenButtonBackground());
        setTBSPage("tt_unicom_open_verify");
        initOrigin();
        bindView();
    }

    private void checkShowMonthEndInformation() {
        findViewById(R.id.layout_month_end_hint_message).setVisibility(0);
        TextView textView = (TextView) findViewById(R.id.text_flow_faq);
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

    public void initOrigin() {
        setStatisticPage(s.PAGE_UNICOM_OPEN_VERIFY);
        this.mOpenOrigin = (b) b.a((Activity) this, b.ORDER_DETAIL);
        aa.a(this.mOpenOrigin);
    }

    public int getOpenButtonBackground() {
        return R.drawable.unicom_open_button;
    }

    private void bindView() {
        this.mButtonOpen.setOnClickListener(this);
        this.mButtonVerifyCode.setOnClickListener(this);
        this.mEditViewPhone.setText(e.l());
        this.mButtonOpen.setText(getButtonText());
        e.p();
        if (e.j()) {
            checkShowMonthEndInformation();
        }
    }

    public String getButtonText() {
        return "确定开通";
    }

    private boolean validPhone(String str) {
        if (!m.a(str) && str.length() == 11) {
            return true;
        }
        f.a("手机号输入有误");
        return false;
    }

    public boolean valid(String str, String str2) {
        if (!m.a(str2)) {
            return validPhone(str);
        }
        f.a("验证码有问题");
        return false;
    }

    private void updateVerifyCodeButtonStatus() {
        this.mWaitTime = 0;
        this.mButtonVerifyCode.setEnabled(false);
        this.mTimeHandler.postDelayed(this.mCountRunnable, 1000);
    }

    private void resetVerifyCodeStatus() {
        try {
            this.mWaitTime = 0;
            this.mButtonVerifyCode.setText("获取验证码");
            this.mButtonVerifyCode.setEnabled(true);
            this.mTimeHandler.removeCallbacks(this.mCountRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mTimeHandler.removeCallbacksAndMessages(null);
    }

    protected void onStop() {
        super.onStop();
        resetVerifyCodeStatus();
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.OPEN_UNICOM_FLOW_RESULT, i.a(cls, "openUnicomFlowResult", d.class));
        map.put(a.SEND_VERIFY_CODE_RESULT, i.a(cls, "sendVerifyCodeResult", d.class));
    }

    public void openUnicomFlowResult(d dVar) {
        b.a();
        if (com.sds.android.ttpod.framework.base.e.ErrNone == dVar.a() || com.sds.android.ttpod.framework.base.e.ErrAlreadyExists == dVar.a()) {
            new com.sds.android.ttpod.framework.a.b.b().b("unicom_open").a(Downloads.COLUMN_STATUS, "1").a();
            aa.c(this.mOpenOrigin);
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", 1107, 0, s.PAGE_UNICOM_OPEN_DETAIL.getValue());
            sUserEvent.setPageParameter(true);
            sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(1)).post();
            e.a((Context) this);
            b.b();
            b.a((Activity) this, OpenDetailActivity.class, this.mOpenOrigin, true);
            return;
        }
        new com.sds.android.ttpod.framework.a.b.b().b("unicom_open").a(Downloads.COLUMN_STATUS, FeedbackItem.STATUS_WAITING).a();
        sUserEvent = new SUserEvent("PAGE_CLICK", 1107, 0, s.PAGE_UNICOM_OPEN_DETAIL.getValue());
        sUserEvent.setPageParameter(true);
        sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(0)).post();
        aa.d(this.mOpenOrigin);
        f.a(dVar.b());
    }

    public void sendVerifyCodeResult(d dVar) {
        if (com.sds.android.ttpod.framework.base.e.ErrNone != dVar.a()) {
            new com.sds.android.ttpod.framework.a.b.b().b("unicom_send_valid_code").a(Downloads.COLUMN_STATUS, FeedbackItem.STATUS_WAITING).a();
            t.a(1106, s.PAGE_UNICOM_OPEN_VERIFY);
            f.a("发送验证码失败，请重新发送");
            resetVerifyCodeStatus();
            aa.J();
            return;
        }
        new com.sds.android.ttpod.framework.a.b.b().b("unicom_send_valid_code").a(Downloads.COLUMN_STATUS, "1").a();
        t.a(1105, s.PAGE_UNICOM_OPEN_VERIFY);
        aa.I();
    }

    public void openUnicomFlow(String str, String str2) {
        b.a((Context) this, "正在开通,请等待...");
        aa.b(this.mOpenOrigin);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.OPEN_UNICOM_FLOW, str, str2, null));
    }

    public void sendVerifyCode(String str, int i) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SEND_VERIFY_CODE, str, new Integer(i)));
    }

    public void onClick(View view) {
        String obj = this.mEditViewPhone.getText().toString();
        String obj2 = this.mEditViewVerfidyCode.getText().toString();
        if (view == this.mButtonOpen) {
            if (valid(obj, obj2)) {
                openUnicomFlow(obj, obj2);
            }
        } else if (view == this.mButtonVerifyCode && validPhone(obj)) {
            aa.H();
            updateVerifyCodeButtonStatus();
            sendVerifyCode(obj, 1);
            this.mEditViewVerfidyCode.requestFocus();
        }
    }
}
