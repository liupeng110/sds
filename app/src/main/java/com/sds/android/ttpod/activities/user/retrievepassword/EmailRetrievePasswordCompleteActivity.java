package com.sds.android.ttpod.activities.user.retrievepassword;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.e;
import com.sds.android.ttpod.framework.modules.a;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import java.lang.reflect.Method;
import java.util.Map;

public class EmailRetrievePasswordCompleteActivity extends SlidingClosableActivity {
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ EmailRetrievePasswordCompleteActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.finish:
                    f.a(this.a.mUserEmail, true);
                    return;
                default:
                    this.a.reSendCaptchaEmail();
                    return;
            }
        }
    };
    private String mUserEmail;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.string.retrieve_password_via_email);
        setContentView((int) R.layout.activity_email_retrieve_password_complete);
        getActionBarController().d();
        this.mUserEmail = getIntent().getExtras().getString(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME);
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(getString(R.string.retrieve_password_email_sent));
        spannableStringBuilder.append(Html.fromHtml("<font color=#3EB1FF>" + this.mUserEmail + "</font>"));
        spannableStringBuilder.append("\n");
        spannableStringBuilder.append(getString(R.string.operate_via_email_tip));
        ((TextView) findViewById(R.id.email_sent_prompt)).setText(spannableStringBuilder);
        findViewById(R.id.finish).setOnClickListener(this.mOnClickListener);
        findViewById(R.id.send_again).setOnClickListener(this.mOnClickListener);
        setStatisticPage(s.PAGE_MAIL_RETRIEVE_PASSWORD_COMPLETE);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.SEND_CAPTCHA_EMAIL_FINISHED, i.a(getClass(), "reSendCaptchaEmailFinished", d.class, String.class));
    }

    private String getRequestId() {
        return getClass().getName();
    }

    private void reSendCaptchaEmail() {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SEND_CAPTCHA_EMAIL, this.mUserEmail, getRequestId()));
        t.a(r.ACTION_PHONE_RETRIEVE_PASSWORD_VIA_EMAIL_RESEND, s.PAGE_NONE);
    }

    public void reSendCaptchaEmailFinished(d dVar, String str) {
        if (!m.a(getRequestId(), str)) {
            return;
        }
        if (dVar.a() == e.ErrNone) {
            com.sds.android.ttpod.component.d.f.a((int) R.string.email_sent_hint);
        } else {
            com.sds.android.ttpod.component.d.f.a(dVar.b());
        }
    }
}
