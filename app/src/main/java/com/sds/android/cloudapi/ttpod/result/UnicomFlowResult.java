package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.UnicomFlow;
import com.sds.android.sdk.lib.request.BaseResult;

public class UnicomFlowResult extends BaseResult {
    @c(a = "data")
    private UnicomFlow mUnicomFlow = new UnicomFlow();

    public UnicomFlow getUnicomFlow() {
        return this.mUnicomFlow;
    }
}
