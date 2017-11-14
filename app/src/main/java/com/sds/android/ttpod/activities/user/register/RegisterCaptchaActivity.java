package com.sds.android.ttpod.activities.user.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.igexin.download.Downloads;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.PhoneCaptchaActivity;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.e;
import java.lang.reflect.Method;
import java.util.Map;

public class RegisterCaptchaActivity extends PhoneCaptchaActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCaptchaName(getString(R.string.initial_password));
        setTitle((int) R.string.register_via_phone);
        setFinishViewText(R.string.register);
        getActionBarController().d();
        setStatisticPage(s.PAGE_PHONE_REGISTER_CAPTCHA);
    }

    protected void validateCaptcha() {
        register();
    }

    protected void reSendCaptcha() {
        b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SEND_INITIAL_PASSWORD_MESSAGE, getPhoneNumber(), getRequestId()));
        t.a(r.ACTION_PHONE_REGISTER_RESEND_INITIAL_PASSWORD, s.PAGE_NONE);
    }

    private void register() {
        if (!m.a(getCaptcha()) && !m.a(getPhoneNumber())) {
            f.a((Context) this, (int) R.string.login_wait_message);
            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.REGISTER, getPhoneNumber(), getCaptcha()));
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.SEND_INITIAL_PASSWORD_MESSAGE_FINISHED, i.a(getClass(), "reSendInitialPasswordMessageFinished", d.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.REGISTER_FINISHED, i.a(getClass(), "registerFinished", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.LOGIN_FINISHED, i.a(getClass(), "loginFinished", d.class, String.class));
    }

    public void reSendInitialPasswordMessageFinished(d dVar, String str) {
        if (!m.a(getRequestId(), str)) {
            return;
        }
        if (dVar.a() == e.ErrNone) {
            f.a((int) R.string.initial_password_sent);
        } else {
            f.a(dVar.b());
        }
    }

    public void registerFinished(d dVar) {
        doRegisterStatistic(dVar.a() == e.ErrNone);
        if (dVar.a() == e.ErrNone) {
            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.LOGIN, getPhoneNumber(), getCaptcha(), getRequestId()));
            return;
        }
        f.a();
        f.a(dVar.b());
    }

    private void doRegisterStatistic(boolean z) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_PHONE_REGISTER.getValue(), s.PAGE_NONE.getValue(), z ? s.PAGE_PHONE_REGISTER_COMPLETE_PROFILE.getValue() : s.PAGE_NONE.getValue());
        sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(z ? 1 : 0));
        sUserEvent.append("location", getIntent().getStringExtra("region"));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private String getRequestId() {
        return getClass().getName() + getCaptcha();
    }

    public void loginFinished(d dVar, String str) {
        if (m.a(getRequestId(), str)) {
            f.a();
            if (dVar.a() == e.ErrNone) {
                startActivity(new Intent(this, RegisterCompleteProfileActivity.class));
            } else {
                f.a(dVar.b());
            }
        }
    }
}
