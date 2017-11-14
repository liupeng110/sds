package com.sds.android.ttpod.activities.user.retrievepassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.igexin.download.Downloads;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.e;
import com.sds.android.ttpod.widget.a;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import java.lang.reflect.Method;
import java.util.Map;

public class EmailRetrievePasswordActivity extends SlidingClosableActivity {
    private a mAutoCompleteView;
    private EditText mUserNameEditText;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.string.retrieve_password_via_email);
        setContentView((int) R.layout.activity_user_email_find_password);
        getActionBarController().d();
        initUserNameView();
        findViewById(R.id.next).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ EmailRetrievePasswordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.retrievePassword(this.a.getUserEmail(), this.a.mUserNameEditText);
            }
        });
        setStatisticPage(s.PAGE_MAIL_RETRIEVE_PASSWORD);
        setTBSPage("tt_findcode_email");
    }

    private String getUserEmail() {
        return this.mUserNameEditText.getText().toString().trim();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.VALIDATE_USERNAME_EXIST_FINISHED, i.a(getClass(), "validateUserNameExistFinished", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SEND_CAPTCHA_EMAIL_FINISHED, i.a(getClass(), "sendCaptchaEmailFinished", d.class, String.class));
    }

    protected void onStop() {
        f.a(this.mAutoCompleteView);
        super.onStop();
    }

    private void retrievePassword(String str, View view) {
        if (com.sds.android.ttpod.activities.user.utils.f.a(str, R.string.user_name_hint_text, R.string.email_format, view, R.anim.shake, com.sds.android.ttpod.activities.user.utils.f.a)) {
            f.a((Context) this, (int) R.string.find_password_waiting);
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.VALIDATE_USERNAME_EXIST, str));
        }
    }

    private void initUserNameView() {
        this.mUserNameEditText = (EditText) findViewById(R.id.email_address);
        this.mAutoCompleteView = new a(this);
        TextWatcher bVar = new com.sds.android.ttpod.activities.user.utils.b(this, this.mUserNameEditText, this.mAutoCompleteView);
        this.mAutoCompleteView.a(new a.a(this) {
            final /* synthetic */ EmailRetrievePasswordActivity a;

            {
                this.a = r1;
            }

            public void a(String str) {
                this.a.mUserNameEditText.setText(str);
                this.a.mUserNameEditText.setSelection(str.length());
                this.a.mAutoCompleteView.c();
            }
        });
        this.mUserNameEditText.addTextChangedListener(bVar);
    }

    private String getRequestId() {
        return getClass().getName() + getUserEmail();
    }

    public void validateUserNameExistFinished(d dVar) {
        if (dVar.a() == e.ErrAlreadyExists) {
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SEND_CAPTCHA_EMAIL, getUserEmail(), getRequestId()));
            return;
        }
        f.a();
        f.a(dVar.b());
        doSendCaptchaEmailStatistic(false);
    }

    public void sendCaptchaEmailFinished(d dVar, String str) {
        if (m.a(getRequestId(), str)) {
            doSendCaptchaEmailStatistic(dVar.a() == e.ErrNone);
            f.a();
            if (dVar.a() == e.ErrNone) {
                gotoCompleteActivity();
            } else {
                f.a(dVar.b());
            }
        }
    }

    private void doSendCaptchaEmailStatistic(boolean z) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_PHONE_RETRIEVE_PASSWORD_VIA_EMAIL_NEXT.getValue(), s.PAGE_NONE.getValue(), z ? s.PAGE_MAIL_RETRIEVE_PASSWORD_COMPLETE.getValue() : s.PAGE_NONE.getValue());
        sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(z ? 1 : 0));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private void gotoCompleteActivity() {
        Intent intent = new Intent(this, EmailRetrievePasswordCompleteActivity.class);
        intent.setFlags(268435456);
        intent.putExtra(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME, getUserEmail());
        startActivity(intent);
    }
}
