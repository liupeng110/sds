package com.sds.android.ttpod.activities.user.utils;

import android.content.Context;
import android.content.Intent;
import com.sds.android.cloudapi.ttpod.result.ResultCode;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.login.LoginActivity;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.storage.environment.b;

/* ResultUtils */
public class d {
    public static boolean a(Context context, BaseResult baseResult) {
        if (baseResult.getCode() != ResultCode.ERROR_AUTHENTICATION && baseResult.getCode() != ResultCode.ERROR_ACCESSTOKEN_INVALID) {
            return false;
        }
        f.a((int) R.string.userinfo_access_token_invalid);
        b.a(null);
        context.startActivity(new Intent(context, LoginActivity.class));
        return true;
    }
}
