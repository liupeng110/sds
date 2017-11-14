package com.sds.android.ttpod.framework.modules;

import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.a.ad;
import com.sds.android.cloudapi.ttpod.result.FindSongModuleResult;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.lib.request.a;
import com.sds.android.sdk.lib.request.b;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.k;

/* ModuleRequestHelper */
public class e {
    public static <Result extends b, TargetResult extends b> void a(a<Result> aVar, a aVar2, c cVar, f<Result, TargetResult> fVar) {
        a(aVar, aVar2, cVar, fVar, null);
    }

    public static <Result extends b, TargetResult extends b> void a(a<Result> aVar, a aVar2, c cVar, f<Result, TargetResult> fVar, String str) {
        final String a = a((a) aVar);
        d.a((Object) aVar, "do request is null");
        final long currentTimeMillis = System.currentTimeMillis();
        final a<Result> aVar3 = aVar;
        final f<Result, TargetResult> fVar2 = fVar;
        final String str2 = str;
        final a aVar4 = aVar2;
        final c cVar2 = cVar;
        com.sds.android.sdk.lib.e.a.a(new Runnable() {
            public void run() {
                b a;
                k a2 = com.sds.android.ttpod.framework.storage.a.a.a().a(a);
                if (e.a(a2, c.e())) {
                    long currentTimeMillis = System.currentTimeMillis();
                    a = aVar3.a();
                    g.b("ModuleRequestHelper", "request.execute cost--> " + (System.currentTimeMillis() - currentTimeMillis) + "ms  " + aVar3.b());
                    if (!a.isSuccess()) {
                        currentTimeMillis = System.currentTimeMillis();
                        a = aVar3.a();
                        g.c("ModuleRequestHelper", "request.execute cost--> " + (System.currentTimeMillis() - currentTimeMillis) + "ms  [RETRY]" + aVar3.b());
                    }
                    new SSystemEvent("SYS_PAGE_REQUEST", "finish").append(Downloads.COLUMN_URI, aVar3.b()).append("duration", Long.valueOf(System.currentTimeMillis() - currentTimeMillis)).append("error_code", Integer.valueOf(a.getCode())).post();
                    if (!e.a(a)) {
                        int i = ((a instanceof com.sds.android.sdk.lib.request.d) && j.a(((com.sds.android.sdk.lib.request.d) a).getDataList())) ? 1 : 0;
                        if (a.isSuccess() && i == 0) {
                            com.sds.android.ttpod.framework.storage.a.a.a().a(a, new k(a, a));
                        }
                    }
                } else {
                    a = a2.a();
                }
                if (fVar2 != null) {
                    a = fVar2.a(a);
                }
                if (str2 == null) {
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(aVar4, a), cVar2);
                } else {
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(aVar4, a, str2), cVar2);
                }
            }
        });
    }

    protected static String a(a aVar) {
        if (aVar.toString().contains(ad.e)) {
            return ad.e;
        }
        return aVar.toString();
    }

    protected static boolean a(b bVar) {
        return (bVar instanceof FindSongModuleResult) && bVar.isSuccess() && ((FindSongModuleResult) bVar).size() == 0;
    }

    protected static boolean a(k kVar, boolean z) {
        return kVar == null || kVar.a() == null || (kVar.b() && z);
    }
}
