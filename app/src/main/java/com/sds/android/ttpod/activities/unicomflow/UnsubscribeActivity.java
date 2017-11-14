package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
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
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.h.e;
import java.lang.reflect.Method;
import java.util.Map;

public class UnsubscribeActivity extends SlidingClosableActivity implements OnClickListener {
    private static final int DELAY_TIME = 1000;
    private static final int PHONE_NUMBER_LENGTH = 11;
    private Button mButtonUnsubscribe;
    private Button mButtonUse;
    private Button mButtonVerifyCode;
    private Runnable mCountRunnable = new Runnable(this) {
        final /* synthetic */ UnsubscribeActivity a;

        {
            this.a = r1;
        }

        public void run() {
            UnsubscribeActivity.access$012(this.a, 1);
            this.a.mButtonVerifyCode.setText("" + (60 - this.a.mWaitTime) + "s后可再次获取");
            if (this.a.mWaitTime < 60) {
                this.a.mHandler.postDelayed(this, 1000);
                return;
            }
            this.a.mButtonVerifyCode.setText("获取验证码");
            this.a.mButtonVerifyCode.setEnabled(true);
        }
    };
    private EditText mEditViewFaq;
    private EditText mEditViewPhone;
    private EditText mEditViewVerifyCode;
    private Handler mHandler = new Handler();
    private RadioGroup mRadioGroupFaq;
    private TextView mTextViewTitle;
    private int mWaitTime = 0;

    static /* synthetic */ int access$012(UnsubscribeActivity unsubscribeActivity, int i) {
        int i2 = unsubscribeActivity.mWaitTime + i;
        unsubscribeActivity.mWaitTime = i2;
        return i2;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_unicom_flow_unsubscribe);
        setTitle((CharSequence) "退订");
        initView();
        aa.r();
        setStatisticPage(s.PAGE_UNICOM_UNSUBSCRIBE_FEEDBACK);
        setTBSPage("tt_unsubscribe_feedback");
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.UNSUBSCRIBE_UNICOM_FLOW_RESULT, i.a(cls, "unsubscribeUnicomFlowResult", d.class));
        map.put(a.SEND_VERIFY_CODE_RESULT, i.a(cls, "sendVerifyCodeResult", d.class));
    }

    private void initView() {
        this.mTextViewTitle = (TextView) findViewById(R.id.text_title);
        this.mEditViewPhone = (EditText) findViewById(R.id.edit_phone);
        this.mButtonVerifyCode = (Button) findViewById(R.id.button_verify_code);
        this.mEditViewVerifyCode = (EditText) findViewById(R.id.edit_verify_code);
        this.mRadioGroupFaq = (RadioGroup) findViewById(R.id.radio_faq);
        this.mEditViewFaq = (EditText) findViewById(R.id.edit_faq);
        this.mButtonUnsubscribe = (Button) findViewById(R.id.button_unsubscribe);
        this.mButtonUse = (Button) findViewById(R.id.button_use);
        this.mButtonUnsubscribe.setOnClickListener(this);
        this.mButtonUse.setOnClickListener(this);
        this.mButtonVerifyCode.setOnClickListener(this);
        this.mEditViewPhone.setText(e.l());
        this.mTextViewTitle.setText(getString(R.string.unicom_unsubscribe_msg));
    }

    public void sendVerifyCodeResult(d dVar) {
        if (com.sds.android.ttpod.framework.base.e.ErrNone != dVar.a()) {
            new b().b("unicom_send_valid_code").a(Downloads.COLUMN_STATUS, FeedbackItem.STATUS_WAITING).a();
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", 1114, 0);
            sUserEvent.setPageParameter(true);
            sUserEvent.post();
            f.a("发送验证码失败，请重新发送");
            resetVerifyCodeStatus();
            return;
        }
        new b().b("unicom_send_valid_code").a(Downloads.COLUMN_STATUS, "1").a();
        sUserEvent = new SUserEvent("PAGE_CLICK", 1113, 0);
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    public void unsubscribeUnicomFlowResult(d dVar) {
        b.a();
        if (com.sds.android.ttpod.framework.base.e.ErrNone == dVar.a() || com.sds.android.ttpod.framework.base.e.ErrAlreadyExists == dVar.a()) {
            new b().b("unicom_unsubscribe").a(Downloads.COLUMN_STATUS, "1").a();
            aa.u();
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", 1116, 0, s.PAGE_UNICOM_RESET_OPEN.getValue());
            sUserEvent.setPageParameter(true);
            sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(1)).post();
            b.b();
            b.a((Activity) this, UnsubscribeDetailActivity.class, null, true);
            return;
        }
        sUserEvent = new SUserEvent("PAGE_CLICK", 1116, 0, s.PAGE_UNICOM_RESET_OPEN.getValue());
        sUserEvent.setPageParameter(true);
        sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(0)).post();
        aa.v();
        f.a(dVar.b());
        b bVar = new b();
        bVar.b("unicom_unsubscribe");
        bVar.a(Downloads.COLUMN_STATUS, FeedbackItem.STATUS_WAITING);
        bVar.a();
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

    public void onClick(View view) {
        String obj = this.mEditViewPhone.getText().toString();
        String obj2 = this.mEditViewVerifyCode.getText().toString();
        if (view == this.mButtonUnsubscribe && valid(obj, obj2)) {
            String obj3 = this.mEditViewFaq.getText().toString();
            int checkedRadioButtonId = this.mRadioGroupFaq.getCheckedRadioButtonId();
            Object obj4 = null;
            if (checkedRadioButtonId != -1) {
                obj4 = findViewById(checkedRadioButtonId).getTag();
            }
            int parseInt = obj4 == null ? 0 : Integer.parseInt(obj4.toString());
            b.a((Context) this, "正在退订联通流量业务");
            aa.s();
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.UNSUBSCRIBE_UNICOM_FLOW, obj, new Integer(parseInt), obj3, obj2));
        } else if (view == this.mButtonUse) {
            b.a("unicom_cencel");
            t.a(1117, s.PAGE_UNICOM_UNSUBSCRIBE_FEEDBACK);
            aa.t();
            b.b();
            finish();
        } else if (view != this.mButtonVerifyCode) {
        } else {
            if (validPhone(obj)) {
                updateVerifyCodeButtonStatus();
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SEND_VERIFY_CODE, obj, new Integer(2)));
                return;
            }
            f.a("手机号输入有误");
        }
    }

    private void updateVerifyCodeButtonStatus() {
        this.mWaitTime = 0;
        this.mButtonVerifyCode.setEnabled(false);
        this.mHandler.postDelayed(this.mCountRunnable, 1000);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    private void resetVerifyCodeStatus() {
        try {
            this.mWaitTime = 0;
            this.mButtonVerifyCode.setText("获取验证码");
            this.mButtonVerifyCode.setEnabled(true);
            this.mHandler.removeCallbacks(this.mCountRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onStop() {
        super.onStop();
        resetVerifyCodeStatus();
    }
}
