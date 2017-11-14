package com.sds.android.ttpod.fragment.main;

import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.ttpod.framework.base.h;

/* ResultHelper */
public class e {

    /* ResultHelper */
    public interface a<CallbackResult extends BaseResult> {
        void a(CallbackResult callbackResult);
    }

    public static <T extends BaseResult> void a(h hVar, T t, a aVar) {
        d.a((Object) hVar, "LoadFinished");
        d.a((Object) aVar, "Callback");
        if (hVar.isLoadFinished()) {
            aVar.a(t);
        }
    }
}
