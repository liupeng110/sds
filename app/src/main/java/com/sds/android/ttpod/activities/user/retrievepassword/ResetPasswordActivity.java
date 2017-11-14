package com.sds.android.ttpod.activities.user.retrievepassword;

import android.content.Context;
import android.os.Bundle;
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
import com.sds.android.ttpod.framework.modules.a;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import java.lang.reflect.Method;
import java.util.Map;

public class ResetPasswordActivity extends SlidingClosableActivity {
    private EditText mPasswordView;
    private String mPhoneNumber;
    private EditText mTwicePasswordView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.string.retrieve_password_via_phone);
        setContentView((int) R.layout.activity_reset_password);
        getActionBarController().d();
        this.mPasswordView = (EditText) findViewById(R.id.password);
        this.mTwicePasswordView = (EditText) findViewById(R.id.password_again);
        this.mPhoneNumber = getIntent().getExtras().getString(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME);
        findViewById(R.id.finish).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ResetPasswordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.uploadNewPassword();
            }
        });
        setStatisticPage(s.PAGE_PHONE_RETRIEVE_PASSWORD_RESET);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.RESET_PASSWORD_FINISHED, i.a(getClass(), "resetPasswordFinished", d.class));
    }

    public void resetPasswordFinished(d dVar) {
        f.a();
        doResetPasswordStatistic(dVar.a() == e.ErrNone);
        if (dVar.a() == e.ErrNone) {
            f.a((int) R.string.modify_password_success);
            com.sds.android.ttpod.b.f.a(com.sds.android.ttpod.activities.user.utils.e.b(this.mPhoneNumber), true);
            return;
        }
        f.a(dVar.b());
    }

    private void doResetPasswordStatistic(boolean z) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_PHONE_RETRIEVE_PASSWORD_VIA_PHONE_RESET_PASSWORD.getValue(), s.PAGE_NONE.getValue(), z ? s.PAGE_LOGIN_MAIL_PHONE.getValue() : s.PAGE_NONE.getValue());
        sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(z ? 1 : 0));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private void uploadNewPassword() {
        if (m.a(getPassword()) || m.a(getTwicePassword())) {
            f.a((int) R.string.input_password_hint);
        } else if (m.a(getPassword(), getTwicePassword())) {
            f.a((Context) this, (int) R.string.login_wait_message);
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.RESET_PASSWORD, this.mPhoneNumber, getPassword()));
        } else {
            f.a((int) R.string.password_not_match);
        }
    }

    private String getPassword() {
        return this.mPasswordView.getText().toString().trim();
    }

    private String getTwicePassword() {
        return this.mTwicePasswordView.getText().toString().trim();
    }
}
