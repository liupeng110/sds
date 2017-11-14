package com.sds.android.ttpod.activities.user.retrievepassword;

import android.content.Intent;
import android.os.Bundle;
import com.igexin.download.Downloads;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.PhoneValidatorActivity;
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

public class PhoneRetrievePasswordActivity extends PhoneValidatorActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.string.retrieve_password_via_phone);
        setTipText(getString(R.string.send_captcha_hint));
        getActionBarController().d();
        setStatisticPage(s.PAGE_PHONE_RETRIEVE_PASSWORD);
        setTBSPage("tt_findcode_mobile");
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.SEND_CAPTCHA_MESSAGE_FINISHED, i.a(getClass(), "sendCaptchaMessageFinished", d.class, String.class));
    }

    private String getRequestId() {
        return getClass().getName() + getPhoneNumber();
    }

    public void handleValidateResult(d dVar) {
        if (dVar.a() == e.ErrAlreadyExists) {
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SEND_CAPTCHA_MESSAGE, com.sds.android.ttpod.activities.user.utils.e.a(getRegionCode(), getPhoneNumber()), getRequestId()));
            return;
        }
        doSendCaptchaMessageStatistic(false);
        f.a();
        f.a(dVar.b());
    }

    public void sendCaptchaMessageFinished(d dVar, String str) {
        if (m.a(getRequestId(), str)) {
            doSendCaptchaMessageStatistic(dVar.a() == e.ErrNone);
            f.a();
            if (dVar.a() == e.ErrNone) {
                gotoValidateCaptcha();
            } else {
                f.a(dVar.b());
            }
        }
    }

    private void doSendCaptchaMessageStatistic(boolean z) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_PHONE_RETRIEVE_PASSWORD_VIA_PHONE_VALIDATE.getValue(), s.PAGE_NONE.getValue(), z ? s.PAGE_PHONE_RETRIEVE_PASSWORD_CAPTCHA.getValue() : s.PAGE_NONE.getValue());
        sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(z ? 1 : 0));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private void gotoValidateCaptcha() {
        Intent intent = new Intent(this, PhoneRetrievePasswordCaptchaActivity.class);
        intent.putExtra(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME, com.sds.android.ttpod.activities.user.utils.e.a(getRegionCode(), getPhoneNumber()));
        startActivity(intent);
    }
}
