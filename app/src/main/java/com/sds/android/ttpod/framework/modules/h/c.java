package com.sds.android.ttpod.framework.modules.h;

import com.sds.android.cloudapi.ttpod.a.ac;
import com.sds.android.cloudapi.ttpod.data.UnicomFlow;
import com.sds.android.cloudapi.ttpod.result.UnicomFlowResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.j;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.support.e;
import com.ttfm.android.sdk.http.HttpChannelSongListGetV2;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@j(a = {a.NET_WORK_TYPE_CHANGED})
/* UnicomFlowModule */
public class c extends b {
    public static final Integer HTTP_PROXY_PORT = Integer.valueOf(8080);
    public static final String PASSWORD = "BDAAAD9B739D3B3F";
    public static final String PROXY_HOST = "58.254.132.93";
    public static final String PROXY_WAP_HOST = "10.123.254.46";
    public static final Integer TCP_PROXY_PORT = Integer.valueOf(8143);
    public static final String USERNAME = "3000004550";
    private static final String a = c.class.getName();

    protected com.sds.android.ttpod.framework.modules.c id() {
        return com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW;
    }

    public long timeOutInMills() {
        return 60000;
    }

    public void netWorkTypeChanged() {
        if (e.f() && com.sds.android.sdk.lib.util.EnvironmentUtils.c.e()) {
            aa.F();
            new SUserEvent("PAGE_CLICK", 1143, 0).post();
            com.sds.android.sdk.lib.a.a.b(e.g());
            a(e.d());
            b();
            c();
            checkStatus();
            g.a(a, "unicom flow save imsi:" + com.sds.android.sdk.lib.util.EnvironmentUtils.c.b());
            com.sds.android.ttpod.framework.storage.a.a.a().k(com.sds.android.sdk.lib.util.EnvironmentUtils.c.b());
        }
    }

    public void checkUseGprsPopDialog() {
        boolean z;
        if (new Date().getTime() < com.sds.android.ttpod.framework.storage.a.a.a().F().getTime() || !com.sds.android.ttpod.framework.storage.a.a.a().D()) {
            z = false;
        } else {
            int i = 1;
        }
        if (!e.h() || r0 == 0 || e.c()) {
            z = false;
        } else {
            z = true;
        }
        g.a(a, "unicom flow check use popup dialog :" + z);
        if (z) {
            com.sds.android.ttpod.framework.storage.a.a.a().b(false);
            e.q();
            b bVar = (com.sds.android.ttpod.framework.storage.a.a.a().I() && a.UN_TRIAL.ordinal() == com.sds.android.ttpod.framework.storage.a.a.a().x()) ? b.DIALOG_TRIAL_TYPE : b.DIALOG_OPEN_TYPE;
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.UNICOM_FLOW_POPUP_DIALOG, bVar), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
        }
    }

    public void popupFlowGreaterThan30MDialog() {
        boolean z = e.h() && !e.c() && com.sds.android.ttpod.framework.storage.a.a.a().E();
        g.a(a, "unicom flow greater than 30M popup dialog :" + z);
        if (z) {
            com.sds.android.ttpod.framework.storage.a.a.a().c(false);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.UNICOM_FLOW_POPUP_DIALOG, b.DIALOG_30M_TYPE), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
        }
    }

    public void checkBeginMonthPopDialog() {
        boolean z;
        if (new Date().getTime() >= com.sds.android.ttpod.framework.storage.a.a.a().b(e.o()).getTime() && com.sds.android.ttpod.framework.storage.a.a.a().C() && e.k()) {
            int i = 1;
        } else {
            z = false;
        }
        if (!e.a() || r0 == 0 || com.sds.android.ttpod.framework.storage.a.a.a().y() == d.OPEN.ordinal()) {
            z = false;
        } else {
            z = true;
        }
        g.a(a, "unicom flow checkStatus begin month popup dialog:" + z);
        if (z) {
            com.sds.android.ttpod.framework.storage.a.a.a().a(false);
            e.p();
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.UNICOM_FLOW_POPUP_DIALOG, b.DIALOG_MONTH_TYPE), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
        }
    }

    private void b() {
        if (new Date().getTime() <= com.sds.android.ttpod.framework.storage.environment.b.br().getTime() + HttpChannelSongListGetV2.CACHE_TIME) {
            g.a(a, "unicom flow already request config:");
            return;
        }
        g.a(a, "unicom flow request config:");
        ac.a().a(new p<UnicomFlowResult>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((UnicomFlowResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((UnicomFlowResult) baseResult);
            }

            public void a(UnicomFlowResult unicomFlowResult) {
                boolean isValidOpen = unicomFlowResult.getUnicomFlow().isValidOpen();
                boolean isTrial = unicomFlowResult.getUnicomFlow().isTrial();
                boolean z = isValidOpen && com.sds.android.ttpod.framework.storage.a.a.a().H();
                g.a(c.a, "unicom flow request config success: " + isValidOpen + " isEnable:" + z + "   trial:" + isTrial);
                com.sds.android.ttpod.framework.storage.environment.b.b(new Date());
                com.sds.android.ttpod.framework.storage.a.a.a().d(isValidOpen);
                com.sds.android.ttpod.framework.storage.a.a.a().f(isTrial);
                com.sds.android.ttpod.framework.storage.a.a.a().c(this.a.a(unicomFlowResult.getUnicomFlow().getPrice(), 9));
                com.sds.android.ttpod.framework.storage.a.a.a().d(this.a.a(unicomFlowResult.getUnicomFlow().getTrialDay(), 3));
                com.sds.android.ttpod.framework.storage.a.a.a().f(unicomFlowResult.getUnicomFlow().getAttention());
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_UNICOM_FLOW_STATUS, Boolean.valueOf(z)), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
            }

            public void b(UnicomFlowResult unicomFlowResult) {
                g.a(c.a, "unicom flow request config fail:");
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_UNICOM_FLOW_STATUS, Boolean.valueOf(e.a())), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
            }
        });
    }

    private int a(String str, int i) {
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
        }
        return i;
    }

    private boolean c() {
        String O = com.sds.android.ttpod.framework.storage.a.a.a().O();
        if (m.a(O) || m.a(com.sds.android.sdk.lib.util.EnvironmentUtils.c.b()) || O.equals(com.sds.android.sdk.lib.util.EnvironmentUtils.c.b())) {
            return false;
        }
        com.sds.android.ttpod.framework.storage.a.a.a().d(e.m());
        com.sds.android.ttpod.framework.storage.a.a.a().b(d.UN_OPEN.ordinal());
        com.sds.android.ttpod.framework.storage.a.a.a().a(a.UN_TRIAL.ordinal());
        return true;
    }

    public void checkStatus() {
        String m = e.m();
        if (m.a(m)) {
            g.a(a, "unicom flow checkStatus cache phone:" + m);
            m = com.sds.android.ttpod.framework.storage.a.a.a().z();
        } else {
            com.sds.android.ttpod.framework.storage.a.a.a().d(m);
        }
        if (m.a(m) && m.a(com.sds.android.sdk.lib.util.EnvironmentUtils.c.b())) {
            g.a(a, "unicom flow already checkStatus imsi:" + com.sds.android.sdk.lib.util.EnvironmentUtils.c.b() + "  phone:" + m);
            return;
        }
        g.a(a, "unicom flow checkStatus phone:" + m + "  imsi:" + com.sds.android.sdk.lib.util.EnvironmentUtils.c.b());
        ac.b(m, com.sds.android.sdk.lib.util.EnvironmentUtils.c.b()).a(new p<UnicomFlowResult>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((UnicomFlowResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((UnicomFlowResult) baseResult);
            }

            public void a(UnicomFlowResult unicomFlowResult) {
                com.sds.android.ttpod.framework.storage.environment.b.a(new Date());
                String openTime = unicomFlowResult.getUnicomFlow().getOpenTime();
                String trialTime = unicomFlowResult.getUnicomFlow().getTrialTime();
                String serverTime = unicomFlowResult.getUnicomFlow().getServerTime();
                String unsubscribeTime = unicomFlowResult.getUnicomFlow().getUnsubscribeTime();
                String phone = unicomFlowResult.getUnicomFlow().getPhone();
                int openStatus = unicomFlowResult.getUnicomFlow().getOpenStatus();
                int trialStatus = unicomFlowResult.getUnicomFlow().getTrialStatus();
                com.sds.android.ttpod.framework.storage.a.a.a().b(openStatus);
                com.sds.android.ttpod.framework.storage.a.a.a().a(trialStatus);
                if (!m.a(phone)) {
                    com.sds.android.ttpod.framework.storage.a.a.a().d(phone);
                }
                if (!m.a(openTime)) {
                    com.sds.android.ttpod.framework.storage.a.a.a().h(openTime);
                }
                if (!m.a(trialTime)) {
                    com.sds.android.ttpod.framework.storage.a.a.a().i(trialTime);
                }
                if (!m.a(unsubscribeTime)) {
                    com.sds.android.ttpod.framework.storage.a.a.a().j(unsubscribeTime);
                }
                if (!m.a(serverTime)) {
                    this.a.a(serverTime);
                    com.sds.android.ttpod.framework.storage.a.a.a().l(serverTime);
                }
                boolean isValidOpen = unicomFlowResult.getUnicomFlow().isValidOpen();
                com.sds.android.ttpod.framework.storage.a.a.a().e(isValidOpen);
                boolean z = isValidOpen && com.sds.android.ttpod.framework.storage.a.a.a().G();
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_UNICOM_FLOW_STATUS, Boolean.valueOf(z)), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
                boolean d = e.d();
                if (d) {
                    aa.G();
                    new SUserEvent("PAGE_CLICK", 1144, 0).post();
                }
                this.a.a(d);
                g.a(c.a, "unicom flow checkStatus success openTime:" + openTime + " trialTime:" + trialTime + " unsubscribeTime:" + unsubscribeTime + " openStatus:" + openStatus + " trialStatus:" + trialStatus + " valid:" + isValidOpen + " isEnable:" + z);
            }

            public void b(UnicomFlowResult unicomFlowResult) {
                g.a(c.a, "unicom flow checkStatus failure");
            }
        });
    }

    private void a(String str) {
        try {
            g.a(a, "unicom flow handler current month flow size:" + str);
            String P = com.sds.android.ttpod.framework.storage.a.a.a().P();
            if (!m.a(str) && !m.a(P)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                int month = simpleDateFormat.parse(str).getMonth();
                int month2 = simpleDateFormat.parse(P).getMonth();
                if (month != month2) {
                    g.a(a, "unicom flow handler change month clear flow size currentMonth:" + (month + 1) + "  lastMonth:" + (month2 + 1));
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.CLEAR_UNICOM_TOTAL_FLOW, new Object[0]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(boolean z) {
        String str = e.e() ? PROXY_WAP_HOST : PROXY_HOST;
        com.sds.android.sdk.lib.a.a.a(str, HTTP_PROXY_PORT.intValue(), USERNAME, PASSWORD);
        com.sds.android.sdk.lib.a.a.a(z);
        g.a(a, "unicom flow set http proxy host:" + str + " isUseProxy:" + z);
        f fVar = new f();
        fVar.a(str);
        fVar.a(TCP_PROXY_PORT.intValue());
        fVar.b(HTTP_PROXY_PORT.intValue());
        fVar.b(USERNAME);
        fVar.c(PASSWORD);
        e.a(sContext).a(fVar, z);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(a.CHECK_STATUS, i.a(cls, "checkStatus", new Class[0]));
        map.put(a.SEND_VERIFY_CODE, i.a(cls, "sendVerifyCode", String.class, Integer.class));
        map.put(a.OPEN_UNICOM_FLOW, i.a(cls, "open", String.class, String.class, String.class));
        map.put(a.TRIAL_UNICOM_FLOW, i.a(cls, "trial", String.class, String.class));
        map.put(a.UNSUBSCRIBE_UNICOM_FLOW, i.a(cls, "unsubscribe", String.class, Integer.class, String.class, String.class));
        map.put(a.CHECK_USE_GPRS_POPUP_DIALOG, i.a(cls, "checkUseGprsPopDialog", new Class[0]));
        map.put(a.UNICOM_FLOW_30M_DIALOG, i.a(cls, "popupFlowGreaterThan30MDialog", new Class[0]));
        map.put(a.CHECK_BEGIN_MONTH_POPUP_DIALOG, i.a(cls, "checkBeginMonthPopDialog", new Class[0]));
        map.put(a.NET_AUTHORIZE, i.a(cls, "netAuthorize", new Class[0]));
        map.put(a.NET_WORK_TYPE_CHANGED, i.a(cls, "netWorkTypeChanged", new Class[0]));
        map.put(a.SAVE_UNICOM_TOTAL_FLOW, i.a(cls, "saveTotalFlow", new Class[0]));
        map.put(a.CLEAR_UNICOM_TOTAL_FLOW, i.a(cls, "clearTotalFlow", new Class[0]));
        map.put(a.GET_UNICOM_PROXY_TOTAL_FLOW, i.a(cls, "getProxyTotalFlow", new Class[0]));
    }

    public void open(final String str, String str2, String str3) {
        ac.a(str, str2, str3, com.sds.android.sdk.lib.util.EnvironmentUtils.c.b()).a(new p<UnicomFlowResult>(this) {
            final /* synthetic */ c b;

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((UnicomFlowResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((UnicomFlowResult) baseResult);
            }

            public void a(UnicomFlowResult unicomFlowResult) {
                d dVar = new d(com.sds.android.ttpod.framework.base.e.ErrNone, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow());
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.OPEN_UNICOM_FLOW_RESULT, dVar), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
                this.b.a(str, unicomFlowResult.getUnicomFlow());
            }

            public void b(UnicomFlowResult unicomFlowResult) {
                com.sds.android.ttpod.framework.base.e eVar = unicomFlowResult.getCode() == 2 ? com.sds.android.ttpod.framework.base.e.ErrAlreadyExists : com.sds.android.ttpod.framework.base.e.ErrGeneral;
                if (com.sds.android.ttpod.framework.base.e.ErrAlreadyExists == eVar) {
                    this.b.a(str, unicomFlowResult.getUnicomFlow());
                }
                d dVar = new d(eVar, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow());
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.OPEN_UNICOM_FLOW_RESULT, dVar), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
            }
        });
    }

    private void a(String str, UnicomFlow unicomFlow) {
        g.a(a, "unicom flow set open status");
        com.sds.android.ttpod.framework.storage.a.a.a().d(str);
        com.sds.android.ttpod.framework.storage.a.a.a().k(com.sds.android.sdk.lib.util.EnvironmentUtils.c.b());
        String openTime = unicomFlow.getOpenTime();
        if (!m.a(openTime)) {
            com.sds.android.ttpod.framework.storage.a.a.a().h(openTime);
        }
        com.sds.android.ttpod.framework.storage.a.a.a().b(d.OPEN.ordinal());
        com.sds.android.ttpod.framework.storage.a.a.a().a(e.o());
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.CLEAR_UNICOM_TOTAL_FLOW, new Object[0]));
        a(e.d());
    }

    private void b(String str, UnicomFlow unicomFlow) {
        g.a(a, "unicom flow set trial status");
        com.sds.android.ttpod.framework.storage.a.a.a().d(str);
        com.sds.android.ttpod.framework.storage.a.a.a().k(com.sds.android.sdk.lib.util.EnvironmentUtils.c.b());
        String trialTime = unicomFlow.getTrialTime();
        if (!m.a(trialTime)) {
            com.sds.android.ttpod.framework.storage.a.a.a().i(trialTime);
        }
        com.sds.android.ttpod.framework.storage.a.a.a().a(a.TRIAL.ordinal());
        a(e.d());
    }

    private void a(UnicomFlow unicomFlow) {
        g.a(a, "unicom flow set unsubscribe status");
        String unsubscribeTime = unicomFlow.getUnsubscribeTime();
        if (!m.a(unsubscribeTime)) {
            com.sds.android.ttpod.framework.storage.a.a.a().j(unsubscribeTime);
        }
        com.sds.android.ttpod.framework.storage.a.a.a().b(d.UNSUBSCRIBE.ordinal());
        a(e.d());
    }

    public void trial(final String str, String str2) {
        ac.a(str, str2).a(new p<UnicomFlowResult>(this) {
            final /* synthetic */ c b;

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((UnicomFlowResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((UnicomFlowResult) baseResult);
            }

            public void a(UnicomFlowResult unicomFlowResult) {
                g.a(c.a, "unicom flow trial sucess");
                d dVar = new d(com.sds.android.ttpod.framework.base.e.ErrNone, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow());
                this.b.b(str, unicomFlowResult.getUnicomFlow());
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.TRIAL_UNICOM_FLOW_RESULT, dVar), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
            }

            public void b(UnicomFlowResult unicomFlowResult) {
                g.a(c.a, "unicom flow trial fail");
                com.sds.android.ttpod.framework.base.e eVar = com.sds.android.ttpod.framework.base.e.ErrGeneral;
                if (unicomFlowResult.getCode() == 2) {
                    this.b.b(str, unicomFlowResult.getUnicomFlow());
                    eVar = com.sds.android.ttpod.framework.base.e.ErrAlreadyExists;
                }
                d dVar = new d(eVar, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow());
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.TRIAL_UNICOM_FLOW_RESULT, dVar), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
            }
        });
    }

    public void sendVerifyCode(String str, Integer num) {
        ac.a(str, num.intValue()).a(new p<UnicomFlowResult>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((UnicomFlowResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((UnicomFlowResult) baseResult);
            }

            public void a(UnicomFlowResult unicomFlowResult) {
                g.a(c.a, "unicom flow sendVerifyCode sucess");
                d dVar = new d(com.sds.android.ttpod.framework.base.e.ErrNone, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow());
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SEND_VERIFY_CODE_RESULT, dVar), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
            }

            public void b(UnicomFlowResult unicomFlowResult) {
                g.a(c.a, "unicom flow sendVerifyCode fail");
                d dVar = new d(com.sds.android.ttpod.framework.base.e.ErrGeneral, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow());
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SEND_VERIFY_CODE_RESULT, dVar), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
            }
        });
    }

    public void unsubscribe(String str, Integer num, String str2, String str3) {
        ac.a(str, num.intValue(), str2, str3).a(new p<UnicomFlowResult>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((UnicomFlowResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((UnicomFlowResult) baseResult);
            }

            public void a(UnicomFlowResult unicomFlowResult) {
                g.a(c.a, "unicom flow unsubscribe success");
                this.a.a(unicomFlowResult.getUnicomFlow());
                d dVar = new d(com.sds.android.ttpod.framework.base.e.ErrNone, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow());
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.UNSUBSCRIBE_UNICOM_FLOW_RESULT, dVar), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
            }

            public void b(UnicomFlowResult unicomFlowResult) {
                g.a(c.a, "unicom flow unsubscribe fail");
                com.sds.android.ttpod.framework.base.e eVar = unicomFlowResult.getCode() == 2 ? com.sds.android.ttpod.framework.base.e.ErrAlreadyExists : com.sds.android.ttpod.framework.base.e.ErrGeneral;
                if (com.sds.android.ttpod.framework.base.e.ErrAlreadyExists == eVar) {
                    this.a.a(unicomFlowResult.getUnicomFlow());
                }
                d dVar = new d(eVar, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow());
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.UNSUBSCRIBE_UNICOM_FLOW_RESULT, dVar), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
            }
        });
    }

    public void netAuthorize() {
        ac.c(d(), com.sds.android.sdk.lib.util.EnvironmentUtils.c.b()).a(new p<UnicomFlowResult>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((UnicomFlowResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((UnicomFlowResult) baseResult);
            }

            public void a(UnicomFlowResult unicomFlowResult) {
                g.a(c.a, "unicom flow net auth success");
                d dVar = new d(com.sds.android.ttpod.framework.base.e.ErrNone, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow());
                String phone = unicomFlowResult.getUnicomFlow().getPhone();
                String token = unicomFlowResult.getUnicomFlow().getToken();
                if (!m.a(phone)) {
                    com.sds.android.ttpod.framework.storage.a.a.a().d(phone);
                }
                if (!m.a(token)) {
                    com.sds.android.ttpod.framework.storage.a.a.a().e(token);
                }
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.NET_AUTHORIZE_RESULT, dVar), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
            }

            public void b(UnicomFlowResult unicomFlowResult) {
                g.a(c.a, "unicom flow net auth fail");
                d dVar = new d(com.sds.android.ttpod.framework.base.e.ErrGeneral, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow());
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.NET_AUTHORIZE_RESULT, dVar), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
            }
        });
    }

    public void saveTotalFlow() {
        if (com.sds.android.sdk.lib.a.a.d()) {
            long x = e.a(sContext).x() + com.sds.android.sdk.lib.a.a.b();
            if (com.sds.android.sdk.lib.a.a.c()) {
                x += com.sds.android.ttpod.framework.storage.a.a.a().K();
                g.a(a, "unicom flow proxy save total flow size:" + x);
                com.sds.android.ttpod.framework.storage.a.a.a().c(x);
            } else {
                x += com.sds.android.ttpod.framework.storage.a.a.a().J();
                com.sds.android.ttpod.framework.storage.a.a.a().b(x);
                g.a(a, "unicom flow save total flow size:" + x);
            }
            e.a(sContext).a(0);
            com.sds.android.sdk.lib.a.a.a(0);
        }
    }

    public void clearTotalFlow() {
        g.a(a, "unicom flow clear total flow size:");
        e.a(sContext).a(0);
        com.sds.android.sdk.lib.a.a.a(0);
        com.sds.android.ttpod.framework.storage.a.a.a().c(0);
        com.sds.android.ttpod.framework.storage.a.a.a().b(0);
    }

    public void getProxyTotalFlow() {
        long x = e.a(sContext).x();
        long b = com.sds.android.sdk.lib.a.a.b();
        long K = com.sds.android.ttpod.framework.storage.a.a.a().K();
        long j = (x + b) + K;
        g.a(a, "unicom flow get Total supportFlow:" + x + " httpFlow:" + b + " cacheFlow:" + K);
        aa.a(x + b);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.GET_UNICOM_TOTAL_FLOW_RESULT, new Long(j)), com.sds.android.ttpod.framework.modules.c.UNICOM_FLOW);
    }

    private String d() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
