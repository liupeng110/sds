package com.sds.android.ttpod.activities.user.retrievepassword;

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
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.e;
import com.sds.android.ttpod.framework.modules.a;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import java.lang.reflect.Method;
import java.util.Map;

public class PhoneRetrievePasswordCaptchaActivity extends PhoneCaptchaActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCaptchaName(getString(R.string.captcha));
        setTitle((int) R.string.retrieve_password_via_phone);
        getActionBarController().d();
        setStatisticPage(s.PAGE_PHONE_RETRIEVE_PASSWORD_CAPTCHA);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.VALIDATE_PHONE_CAPTCHA_FINISHED, i.a(getClass(), "validatePhoneCaptchaFinished", d.class));
        map.put(a.SEND_CAPTCHA_MESSAGE_FINISHED, i.a(getClass(), "reSendCaptchaMessageFinished", d.class, String.class));
    }

    private String getRequestId() {
        return getClass().getName() + getPhoneNumber();
    }

    protected void validateCaptcha() {
        f.a((Context) this, (int) R.string.login_wait_message);
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.VALIDATE_PHONE_CAPTCHA, getPhoneNumber(), getCaptcha()));
    }

    protected void reSendCaptcha() {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SEND_CAPTCHA_MESSAGE, getPhoneNumber(), getRequestId()));
        t.a(r.ACTION_PHONE_RETRIEVE_PASSWORD_VIA_PHONE_RESEND_CAPTCHA, s.PAGE_NONE);
    }

    public void reSendCaptchaMessageFinished(d dVar, String str) {
        if (!m.a(getRequestId(), str)) {
            return;
        }
        if (dVar.a() == e.ErrNone) {
            f.a((int) R.string.captcha_sent);
        } else {
            f.a(dVar.b());
        }
    }

    public void validatePhoneCaptchaFinished(d dVar) {
        f.a();
        doValidatePhoneCaptchaStatistic(dVar.a() == e.ErrNone);
        if (dVar.a() == e.ErrNone) {
            gotoResetPassword();
        } else {
            f.a(dVar.b());
        }
    }

    private void doValidatePhoneCaptchaStatistic(boolean z) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_PHONE_RETRIEVE_PASSWORD_VIA_PHONE_VALIDATE_CAPTCHA.getValue(), s.PAGE_NONE.getValue(), z ? s.PAGE_PHONE_RETRIEVE_PASSWORD_RESET.getValue() : s.PAGE_NONE.getValue());
        sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(z ? 1 : 0));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private void gotoResetPassword() {
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        intent.putExtra(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME, getPhoneNumber());
        startActivity(intent);
    }
}
