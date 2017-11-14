package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.sds.android.cloudapi.ttpod.data.UnicomFlow;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.a.b.aa.b;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.e;
import java.lang.reflect.Method;
import java.util.Map;

public class UnicomFlowActivity extends SlidingClosableActivity {
    private boolean mIsNeedFinish;
    private b mOpenOrigin;

    public void startOpenActivity(b bVar, boolean z) {
        this.mOpenOrigin = bVar;
        this.mIsNeedFinish = z;
        if (c.d() == 0 || c.d() == 3 || c.d() == 1) {
            b.a((Context) this, "数据加载中...");
            com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.NET_AUTHORIZE, new Object[0]));
            return;
        }
        gotoOpenActivity(bVar, z);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.NET_AUTHORIZE_RESULT, i.a(cls, "netAuthorizeResult", d.class));
    }

    private void gotoOpenActivity(b bVar, boolean z) {
        aa.o();
        t.a(1101, s.PAGE_UNICOM_OPEN_VERIFY);
        b.a((Activity) this, OpenActivity.class, (Enum) bVar, z);
    }

    private void gotoOpenAuthorizeActivity(UnicomFlow unicomFlow) {
        new SUserEvent("PAGE_CLICK", 1147, s.PAGE_UNICOM_ORDER.getValue(), s.PAGE_UNICOM_NET_GET_PHONE.getValue()).post();
        Intent intent = new Intent(this, OpenAuthorizeActivity.class);
        intent.putExtra(OpenAuthorizeActivity.KEY_PHONE, unicomFlow.getPhone());
        intent.putExtra(OpenAuthorizeActivity.KEY_TOKEN, unicomFlow.getToken());
        b.a((Activity) this, intent, this.mOpenOrigin, false);
    }

    public void netAuthorizeResult(d dVar) {
        Object obj;
        b.a();
        if (e.ErrNone == dVar.a() && (dVar.d() instanceof UnicomFlow)) {
            UnicomFlow unicomFlow = (UnicomFlow) dVar.d();
            if (!(unicomFlow == null || m.a(unicomFlow.getPhone()) || m.a(unicomFlow.getToken()))) {
                gotoOpenAuthorizeActivity(unicomFlow);
                obj = 1;
                if (obj == null) {
                    gotoOpenActivity(this.mOpenOrigin, this.mIsNeedFinish);
                }
            }
        }
        obj = null;
        if (obj == null) {
            gotoOpenActivity(this.mOpenOrigin, this.mIsNeedFinish);
        }
    }
}
