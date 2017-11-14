package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.a.b.aa.c;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.e;
import com.sds.android.ttpod.framework.modules.a;
import java.lang.reflect.Method;
import java.util.Map;

public class TrialActivity extends OpenActivity {
    private c mTrialOrigin = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void initOrigin() {
        this.mTrialOrigin = (c) b.a((Activity) this, c.ORDER_DETAIL);
        aa.a(this.mTrialOrigin);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.TRIAL_UNICOM_FLOW_RESULT, i.a(cls, "trialUnicomFlowResult", d.class));
    }

    public int getOpenButtonBackground() {
        return R.drawable.unicom_trial_button;
    }

    public void trialUnicomFlowResult(d dVar) {
        b.a();
        if (e.ErrNone == dVar.a()) {
            aa.d(this.mTrialOrigin);
            b.b();
            b.a((Activity) this, TrialDetailActivity.class, this.mTrialOrigin, true);
            return;
        }
        aa.c(this.mTrialOrigin);
        f.a(dVar.b());
        if (e.ErrAlreadyExists == dVar.a()) {
            b.b();
            finish();
        }
    }

    public String getButtonText() {
        return "确定试用";
    }

    public void openUnicomFlow(String str, String str2) {
        b.a((Context) this, "正在开通试用,请等待...");
        aa.b(this.mTrialOrigin);
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.TRIAL_UNICOM_FLOW, str, str2));
    }

    public void sendVerifyCode(String str, int i) {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SEND_VERIFY_CODE, str, new Integer(3)));
    }
}
