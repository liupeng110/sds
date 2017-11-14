package com.sds.android.ttpod.activities.user.register;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import com.igexin.download.Downloads;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.PhoneValidatorActivity;
import com.sds.android.ttpod.activities.user.utils.e;
import com.sds.android.ttpod.activities.web.WebActivity;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.modules.a;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import java.lang.reflect.Method;
import java.util.Map;

public class RegisterActivity extends PhoneValidatorActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.string.register_via_phone);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(getString(R.string.accepted_if_registered));
        spannableStringBuilder.append(Html.fromHtml("<font color=#5A9BFF>" + getString(R.string.ttpod_user_agreement) + "</font>"));
        setTipText(spannableStringBuilder);
        setTipClickListener(new OnClickListener(this) {
            final /* synthetic */ RegisterActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.checkUserAgreement();
            }
        });
        getActionBarController().d();
        setStatisticPage(s.PAGE_PHONE_REGISTER);
        setTBSPage("tt_register");
    }

    private String getRequestId() {
        return getClass().getName() + getPhoneNumber();
    }

    private void checkUserAgreement() {
        Intent intent = new Intent(this, WebActivity.class);
        intent.setData(Uri.parse("http://www.ttpod.com/website/propotal?display=mobile"));
        intent.putExtra(WebFragment.EXTRA_TITLE, getString(R.string.register_user_agreement));
        intent.putExtra(WebFragment.EXTRA_HINT_BANNER_SHOW, false);
        intent.addFlags(268435456);
        startActivity(intent);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.SEND_INITIAL_PASSWORD_MESSAGE_FINISHED, i.a(getClass(), "sendInitialPasswordMessageFinished", d.class, String.class));
    }

    public void handleValidateResult(d dVar) {
        switch (dVar.a()) {
            case ErrAlreadyExists:
                f.a();
                showRegisteredDialog(getString(R.string.remind_user_to_login, new Object[]{getRegionCode() + " " + getPhoneNumber()}));
                return;
            case ErrNotFound:
                b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SEND_INITIAL_PASSWORD_MESSAGE, e.a(getRegionCode(), getPhoneNumber()), getRequestId()));
                return;
            case ErrGeneral:
                doSendInitialPasswordStatistic(false);
                f.a();
                f.a(dVar.b());
                return;
            default:
                return;
        }
    }

    private void showRegisteredDialog(String str) {
        h hVar = new h((Context) this, str, null, null);
        hVar.setTitle((int) R.string.registered);
        hVar.a((int) R.string.goto_login, new com.sds.android.ttpod.common.a.a.a<h>(this) {
            final /* synthetic */ RegisterActivity a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                com.sds.android.ttpod.b.f.a(this.a.getPhoneNumber(), true);
                t.a(r.ACTION_PHONE_REGISTERED_GOTO_LOGIN, s.PAGE_LOGIN_MAIL_PHONE);
            }
        }, (int) R.string.cancel, null);
        hVar.show();
    }

    public void sendInitialPasswordMessageFinished(d dVar, String str) {
        if (m.a(getRequestId(), str)) {
            doSendInitialPasswordStatistic(dVar.a() == com.sds.android.ttpod.framework.base.e.ErrNone);
            f.a();
            if (dVar.a() == com.sds.android.ttpod.framework.base.e.ErrNone) {
                gotoRegister();
            } else {
                f.a(dVar.b());
            }
        }
    }

    private void doSendInitialPasswordStatistic(boolean z) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_PHONE_REGISTER_VALIDATE.getValue(), s.PAGE_NONE.getValue(), z ? s.PAGE_PHONE_REGISTER_CAPTCHA.getValue() : s.PAGE_NONE.getValue());
        sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(z ? 1 : 0));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private void gotoRegister() {
        Intent intent = new Intent(this, RegisterCaptchaActivity.class);
        intent.putExtra(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME, e.a(getRegionCode(), getPhoneNumber()));
        intent.putExtra("region", getRegion());
        startActivity(intent);
    }
}
