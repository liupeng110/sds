package com.sds.android.cloudapi.ttpod.b;

import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.lib.b.b;

/* PostFeedbackMessageResult */
public class c extends b {
    public c(b bVar) {
        setCode(bVar.getCode());
        setContent(bVar.getContent());
        setLocation(bVar.getLocation());
    }

    public boolean parseCode(int i) {
        return SecExceptionCode.SEC_ERROR_STA_STORE_INVALID_PARAM == i;
    }
}
